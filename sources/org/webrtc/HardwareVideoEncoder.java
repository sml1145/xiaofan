package org.webrtc;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaFormat;
import android.opengl.GLES20;
import android.os.Bundle;
import android.view.Surface;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import org.webrtc.EglBase14;
import org.webrtc.EncodedImage;
import org.webrtc.ThreadUtils;
import org.webrtc.VideoEncoder;
import org.webrtc.VideoFrame;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public class HardwareVideoEncoder implements VideoEncoder {
    private static final int DEQUEUE_OUTPUT_BUFFER_TIMEOUT_US = 100000;
    private static final int MAX_ENCODER_Q_SIZE = 2;
    private static final int MAX_VIDEO_FRAMERATE = 30;
    private static final int MEDIA_CODEC_RELEASE_TIMEOUT_MS = 5000;
    private static final int REQUIRED_RESOLUTION_ALIGNMENT = 2;
    private static final String TAG = "HardwareVideoEncoder";
    private int adjustedBitrate;
    private boolean automaticResizeOn;
    private final BitrateAdjuster bitrateAdjuster;
    private VideoEncoder.Callback callback;
    private MediaCodecWrapper codec;
    private final String codecName;
    private final VideoCodecMimeType codecType;
    private ByteBuffer configBuffer;
    private final long forcedKeyFrameNs;
    private int frameSizeBytes;
    private int height;
    private boolean isEncodingStatisticsEnabled;
    private boolean isSemiPlanar;
    private final int keyFrameIntervalSec;
    private long lastKeyFrameNs;
    private final MediaCodecWrapperFactory mediaCodecWrapperFactory;
    private long nextPresentationTimestampUs;
    private Thread outputThread;
    private final Map<String, String> params;
    private volatile boolean running;
    private final EglBase14.Context sharedContext;
    private volatile Exception shutdownException;
    private int sliceHeight;
    private int stride;
    private final Integer surfaceColorFormat;
    private EglBase14 textureEglBase;
    private Surface textureInputSurface;
    private boolean useSurfaceMode;
    private int width;
    private final Integer yuvColorFormat;
    private final GlRectDrawer textureDrawer = new GlRectDrawer();
    private final VideoFrameDrawer videoFrameDrawer = new VideoFrameDrawer();
    private final BlockingDeque<EncodedImage.Builder> outputBuilders = new LinkedBlockingDeque();
    private final ThreadUtils.ThreadChecker encodeThreadChecker = new ThreadUtils.ThreadChecker();
    private final ThreadUtils.ThreadChecker outputThreadChecker = new ThreadUtils.ThreadChecker();
    private final BusyCount outputBuffersBusyCount = new BusyCount();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class BusyCount {
        private int count;
        private final Object countLock;

        private BusyCount() {
            this.countLock = new Object();
        }

        public void increment() {
            synchronized (this.countLock) {
                this.count++;
            }
        }

        public void decrement() {
            synchronized (this.countLock) {
                this.count--;
                if (this.count == 0) {
                    this.countLock.notifyAll();
                }
            }
        }

        public void waitForZero() {
            boolean wasInterrupted = false;
            synchronized (this.countLock) {
                while (this.count > 0) {
                    try {
                        this.countLock.wait();
                    } catch (InterruptedException e) {
                        Logging.e(HardwareVideoEncoder.TAG, "Interrupted while waiting on busy count", e);
                        wasInterrupted = true;
                    }
                }
            }
            if (wasInterrupted) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public HardwareVideoEncoder(MediaCodecWrapperFactory mediaCodecWrapperFactory, String codecName, VideoCodecMimeType codecType, Integer surfaceColorFormat, Integer yuvColorFormat, Map<String, String> params, int keyFrameIntervalSec, int forceKeyFrameIntervalMs, BitrateAdjuster bitrateAdjuster, EglBase14.Context sharedContext) {
        this.mediaCodecWrapperFactory = mediaCodecWrapperFactory;
        this.codecName = codecName;
        this.codecType = codecType;
        this.surfaceColorFormat = surfaceColorFormat;
        this.yuvColorFormat = yuvColorFormat;
        this.params = params;
        this.keyFrameIntervalSec = keyFrameIntervalSec;
        this.forcedKeyFrameNs = TimeUnit.MILLISECONDS.toNanos(forceKeyFrameIntervalMs);
        this.bitrateAdjuster = bitrateAdjuster;
        this.sharedContext = sharedContext;
        this.encodeThreadChecker.detachThread();
    }

    @Override // org.webrtc.VideoEncoder
    public VideoCodecStatus initEncode(VideoEncoder.Settings settings, VideoEncoder.Callback callback) {
        this.encodeThreadChecker.checkIsOnValidThread();
        this.callback = callback;
        this.automaticResizeOn = settings.automaticResizeOn;
        if (settings.width % 2 != 0 || settings.height % 2 != 0) {
            Logging.e(TAG, "MediaCodec requires 2x2 alignment.");
            return VideoCodecStatus.ERR_SIZE;
        }
        this.width = settings.width;
        this.height = settings.height;
        this.useSurfaceMode = canUseSurface();
        if (settings.startBitrate != 0 && settings.maxFramerate != 0) {
            this.bitrateAdjuster.setTargets(settings.startBitrate * 1000, settings.maxFramerate);
        }
        this.adjustedBitrate = this.bitrateAdjuster.getAdjustedBitrateBps();
        Logging.d(TAG, "initEncode name: " + this.codecName + " type: " + this.codecType + " width: " + this.width + " height: " + this.height + " framerate_fps: " + settings.maxFramerate + " bitrate_kbps: " + settings.startBitrate + " surface mode: " + this.useSurfaceMode);
        return initEncodeInternal();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private VideoCodecStatus initEncodeInternal() {
        this.encodeThreadChecker.checkIsOnValidThread();
        this.nextPresentationTimestampUs = 0L;
        this.lastKeyFrameNs = -1L;
        char c = 0;
        this.isEncodingStatisticsEnabled = false;
        try {
            this.codec = this.mediaCodecWrapperFactory.createByCodecName(this.codecName);
            int colorFormat = (this.useSurfaceMode ? this.surfaceColorFormat : this.yuvColorFormat).intValue();
            try {
                MediaFormat format = MediaFormat.createVideoFormat(this.codecType.mimeType(), this.width, this.height);
                format.setInteger("bitrate", this.adjustedBitrate);
                format.setInteger("bitrate-mode", 2);
                format.setInteger("color-format", colorFormat);
                format.setFloat("frame-rate", (float) this.bitrateAdjuster.getAdjustedFramerateFps());
                format.setInteger("i-frame-interval", this.keyFrameIntervalSec);
                if (this.codecType == VideoCodecMimeType.H264) {
                    String profileLevelId = this.params.get("profile-level-id");
                    if (profileLevelId == null) {
                        profileLevelId = "42e01f";
                    }
                    switch (profileLevelId.hashCode()) {
                        case 1537948542:
                            if (profileLevelId.equals("42e01f")) {
                                c = 1;
                                break;
                            }
                            c = 65535;
                            break;
                        case 1595523974:
                            if (profileLevelId.equals("640c1f")) {
                                break;
                            }
                            c = 65535;
                            break;
                        default:
                            c = 65535;
                            break;
                    }
                    switch (c) {
                        case 0:
                            format.setInteger("profile", 8);
                            format.setInteger("level", 256);
                            break;
                        case 1:
                            break;
                        default:
                            Logging.w(TAG, "Unknown profile level id: " + profileLevelId);
                            break;
                    }
                }
                if (this.codecName.equals("c2.google.av1.encoder")) {
                    format.setInteger("vendor.google-av1enc.encoding-preset.int32.value", 1);
                }
                if (isEncodingStatisticsSupported()) {
                    format.setInteger("video-encoding-statistics-level", 1);
                    this.isEncodingStatisticsEnabled = true;
                }
                Logging.d(TAG, "Format: " + format);
                this.codec.configure(format, null, null, 1);
                if (this.useSurfaceMode) {
                    this.textureEglBase = EglBase.createEgl14(this.sharedContext, EglBase.CONFIG_RECORDABLE);
                    this.textureInputSurface = this.codec.createInputSurface();
                    this.textureEglBase.createSurface(this.textureInputSurface);
                    this.textureEglBase.makeCurrent();
                }
                updateInputFormat(this.codec.getInputFormat());
                this.codec.start();
                this.running = true;
                this.outputThreadChecker.detachThread();
                this.outputThread = createOutputThread();
                this.outputThread.start();
                return VideoCodecStatus.OK;
            } catch (IllegalArgumentException | IllegalStateException e) {
                Logging.e(TAG, "initEncodeInternal failed", e);
                release();
                return VideoCodecStatus.FALLBACK_SOFTWARE;
            }
        } catch (IOException | IllegalArgumentException e2) {
            Logging.e(TAG, "Cannot create media encoder " + this.codecName);
            return VideoCodecStatus.FALLBACK_SOFTWARE;
        }
    }

    @Override // org.webrtc.VideoEncoder
    public VideoCodecStatus release() {
        VideoCodecStatus returnValue;
        this.encodeThreadChecker.checkIsOnValidThread();
        if (this.outputThread == null) {
            returnValue = VideoCodecStatus.OK;
        } else {
            this.running = false;
            if (!ThreadUtils.joinUninterruptibly(this.outputThread, 5000L)) {
                Logging.e(TAG, "Media encoder release timeout");
                returnValue = VideoCodecStatus.TIMEOUT;
            } else if (this.shutdownException != null) {
                Logging.e(TAG, "Media encoder release exception", this.shutdownException);
                returnValue = VideoCodecStatus.ERROR;
            } else {
                returnValue = VideoCodecStatus.OK;
            }
        }
        this.textureDrawer.release();
        this.videoFrameDrawer.release();
        if (this.textureEglBase != null) {
            this.textureEglBase.release();
            this.textureEglBase = null;
        }
        if (this.textureInputSurface != null) {
            this.textureInputSurface.release();
            this.textureInputSurface = null;
        }
        this.outputBuilders.clear();
        this.codec = null;
        this.outputThread = null;
        this.encodeThreadChecker.detachThread();
        return returnValue;
    }

    @Override // org.webrtc.VideoEncoder
    public VideoCodecStatus encode(VideoFrame videoFrame, VideoEncoder.EncodeInfo encodeInfo) {
        VideoCodecStatus status;
        EncodedImage.FrameType[] frameTypeArr;
        VideoCodecStatus returnValue;
        this.encodeThreadChecker.checkIsOnValidThread();
        if (this.codec == null) {
            return VideoCodecStatus.UNINITIALIZED;
        }
        boolean isTextureBuffer = videoFrame.getBuffer() instanceof VideoFrame.TextureBuffer;
        int frameWidth = videoFrame.getBuffer().getWidth();
        int frameHeight = videoFrame.getBuffer().getHeight();
        boolean shouldUseSurfaceMode = canUseSurface() && isTextureBuffer;
        if ((frameWidth != this.width || frameHeight != this.height || shouldUseSurfaceMode != this.useSurfaceMode) && (status = resetCodec(frameWidth, frameHeight, shouldUseSurfaceMode)) != VideoCodecStatus.OK) {
            return status;
        }
        if (this.outputBuilders.size() > 2) {
            Logging.e(TAG, "Dropped frame, encoder queue full");
            return VideoCodecStatus.NO_OUTPUT;
        }
        boolean requestedKeyFrame = false;
        for (EncodedImage.FrameType frameType : encodeInfo.frameTypes) {
            if (frameType == EncodedImage.FrameType.VideoFrameKey) {
                requestedKeyFrame = true;
            }
        }
        if (requestedKeyFrame || shouldForceKeyFrame(videoFrame.getTimestampNs())) {
            requestKeyFrame(videoFrame.getTimestampNs());
        }
        EncodedImage.Builder builder = EncodedImage.builder().setCaptureTimeNs(videoFrame.getTimestampNs()).setEncodedWidth(videoFrame.getBuffer().getWidth()).setEncodedHeight(videoFrame.getBuffer().getHeight()).setRotation(videoFrame.getRotation());
        this.outputBuilders.offer(builder);
        long presentationTimestampUs = this.nextPresentationTimestampUs;
        long frameDurationUs = (long) (TimeUnit.SECONDS.toMicros(1L) / this.bitrateAdjuster.getAdjustedFramerateFps());
        this.nextPresentationTimestampUs += frameDurationUs;
        if (this.useSurfaceMode) {
            returnValue = encodeTextureBuffer(videoFrame, presentationTimestampUs);
        } else {
            returnValue = encodeByteBuffer(videoFrame, presentationTimestampUs);
        }
        if (returnValue != VideoCodecStatus.OK) {
            this.outputBuilders.pollLast();
        }
        return returnValue;
    }

    private VideoCodecStatus encodeTextureBuffer(VideoFrame videoFrame, long presentationTimestampUs) {
        this.encodeThreadChecker.checkIsOnValidThread();
        try {
            GLES20.glClear(16384);
            VideoFrame derotatedFrame = new VideoFrame(videoFrame.getBuffer(), 0, videoFrame.getTimestampNs());
            this.videoFrameDrawer.drawFrame(derotatedFrame, this.textureDrawer, null);
            this.textureEglBase.swapBuffers(TimeUnit.MICROSECONDS.toNanos(presentationTimestampUs));
            return VideoCodecStatus.OK;
        } catch (RuntimeException e) {
            Logging.e(TAG, "encodeTexture failed", e);
            return VideoCodecStatus.ERROR;
        }
    }

    private VideoCodecStatus encodeByteBuffer(VideoFrame videoFrame, long presentationTimestampUs) {
        this.encodeThreadChecker.checkIsOnValidThread();
        try {
            int index = this.codec.dequeueInputBuffer(0L);
            if (index == -1) {
                Logging.d(TAG, "Dropped frame, no input buffers available");
                return VideoCodecStatus.NO_OUTPUT;
            }
            try {
                ByteBuffer buffer = this.codec.getInputBuffer(index);
                if (buffer.capacity() < this.frameSizeBytes) {
                    Logging.e(TAG, "Input buffer size: " + buffer.capacity() + " is smaller than frame size: " + this.frameSizeBytes);
                    return VideoCodecStatus.ERROR;
                }
                fillInputBuffer(buffer, videoFrame.getBuffer());
                try {
                    this.codec.queueInputBuffer(index, 0, this.frameSizeBytes, presentationTimestampUs, 0);
                    return VideoCodecStatus.OK;
                } catch (IllegalStateException e) {
                    Logging.e(TAG, "queueInputBuffer failed", e);
                    return VideoCodecStatus.ERROR;
                }
            } catch (IllegalStateException e2) {
                Logging.e(TAG, "getInputBuffer with index=" + index + " failed", e2);
                return VideoCodecStatus.ERROR;
            }
        } catch (IllegalStateException e3) {
            Logging.e(TAG, "dequeueInputBuffer failed", e3);
            return VideoCodecStatus.ERROR;
        }
    }

    @Override // org.webrtc.VideoEncoder
    public VideoCodecStatus setRateAllocation(VideoEncoder.BitrateAllocation bitrateAllocation, int framerate) {
        this.encodeThreadChecker.checkIsOnValidThread();
        if (framerate > 30) {
            framerate = 30;
        }
        this.bitrateAdjuster.setTargets(bitrateAllocation.getSum(), framerate);
        return VideoCodecStatus.OK;
    }

    @Override // org.webrtc.VideoEncoder
    public VideoCodecStatus setRates(VideoEncoder.RateControlParameters rcParameters) {
        this.encodeThreadChecker.checkIsOnValidThread();
        this.bitrateAdjuster.setTargets(rcParameters.bitrate.getSum(), rcParameters.framerateFps);
        return VideoCodecStatus.OK;
    }

    @Override // org.webrtc.VideoEncoder
    public VideoEncoder.ScalingSettings getScalingSettings() {
        if (this.automaticResizeOn) {
            if (this.codecType == VideoCodecMimeType.VP8) {
                return new VideoEncoder.ScalingSettings(29, 95);
            }
            if (this.codecType == VideoCodecMimeType.H264) {
                return new VideoEncoder.ScalingSettings(24, 37);
            }
        }
        return VideoEncoder.ScalingSettings.OFF;
    }

    @Override // org.webrtc.VideoEncoder
    public String getImplementationName() {
        return this.codecName;
    }

    @Override // org.webrtc.VideoEncoder
    public VideoEncoder.EncoderInfo getEncoderInfo() {
        return new VideoEncoder.EncoderInfo(2, false);
    }

    private VideoCodecStatus resetCodec(int newWidth, int newHeight, boolean newUseSurfaceMode) {
        this.encodeThreadChecker.checkIsOnValidThread();
        VideoCodecStatus status = release();
        if (status != VideoCodecStatus.OK) {
            return status;
        }
        if (newWidth % 2 != 0 || newHeight % 2 != 0) {
            Logging.e(TAG, "MediaCodec requires 2x2 alignment.");
            return VideoCodecStatus.ERR_SIZE;
        }
        this.width = newWidth;
        this.height = newHeight;
        this.useSurfaceMode = newUseSurfaceMode;
        return initEncodeInternal();
    }

    private boolean shouldForceKeyFrame(long presentationTimestampNs) {
        this.encodeThreadChecker.checkIsOnValidThread();
        return this.forcedKeyFrameNs > 0 && presentationTimestampNs > this.lastKeyFrameNs + this.forcedKeyFrameNs;
    }

    private void requestKeyFrame(long presentationTimestampNs) {
        this.encodeThreadChecker.checkIsOnValidThread();
        try {
            Bundle b = new Bundle();
            b.putInt("request-sync", 0);
            this.codec.setParameters(b);
            this.lastKeyFrameNs = presentationTimestampNs;
        } catch (IllegalStateException e) {
            Logging.e(TAG, "requestKeyFrame failed", e);
        }
    }

    private Thread createOutputThread() {
        return new Thread() { // from class: org.webrtc.HardwareVideoEncoder.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                while (HardwareVideoEncoder.this.running) {
                    HardwareVideoEncoder.this.deliverEncodedImage();
                }
                HardwareVideoEncoder.this.releaseCodecOnOutputThread();
            }
        };
    }

    protected void deliverEncodedImage() {
        ByteBuffer frameBuffer;
        Runnable releaseCallback;
        MediaFormat format;
        this.outputThreadChecker.checkIsOnValidThread();
        try {
            MediaCodec.BufferInfo info = new MediaCodec.BufferInfo();
            final int index = this.codec.dequeueOutputBuffer(info, 100000L);
            if (index < 0) {
                if (index == -3) {
                    this.outputBuffersBusyCount.waitForZero();
                    return;
                }
                return;
            }
            ByteBuffer outputBuffer = this.codec.getOutputBuffer(index);
            outputBuffer.position(info.offset);
            outputBuffer.limit(info.offset + info.size);
            if ((info.flags & 2) != 0) {
                Logging.d(TAG, "Config frame generated. Offset: " + info.offset + ". Size: " + info.size);
                if (info.size > 0) {
                    if (this.codecType == VideoCodecMimeType.H264 || this.codecType == VideoCodecMimeType.H265) {
                        this.configBuffer = ByteBuffer.allocateDirect(info.size);
                        this.configBuffer.put(outputBuffer);
                        return;
                    }
                    return;
                }
                return;
            }
            this.bitrateAdjuster.reportEncodedFrame(info.size);
            if (this.adjustedBitrate != this.bitrateAdjuster.getAdjustedBitrateBps()) {
                updateBitrate();
            }
            boolean z = true;
            if ((info.flags & 1) == 0) {
                z = false;
            }
            boolean isKeyFrame = z;
            if (isKeyFrame) {
                Logging.d(TAG, "Sync frame generated");
            }
            Integer qp = null;
            if (this.isEncodingStatisticsEnabled && (format = this.codec.getOutputFormat(index)) != null && format.containsKey("video-qp-average")) {
                qp = Integer.valueOf(format.getInteger("video-qp-average"));
            }
            if (isKeyFrame && this.configBuffer != null) {
                Logging.d(TAG, "Prepending config buffer of size " + this.configBuffer.capacity() + " to output buffer with offset " + info.offset + ", size " + info.size);
                frameBuffer = ByteBuffer.allocateDirect(info.size + this.configBuffer.capacity());
                this.configBuffer.rewind();
                frameBuffer.put(this.configBuffer);
                frameBuffer.put(outputBuffer);
                frameBuffer.rewind();
                this.codec.releaseOutputBuffer(index, false);
                releaseCallback = null;
            } else {
                frameBuffer = outputBuffer.slice();
                this.outputBuffersBusyCount.increment();
                releaseCallback = new Runnable() { // from class: org.webrtc.HardwareVideoEncoder$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        HardwareVideoEncoder.this.m2107lambda$deliverEncodedImage$0$orgwebrtcHardwareVideoEncoder(index);
                    }
                };
            }
            EncodedImage.FrameType frameType = isKeyFrame ? EncodedImage.FrameType.VideoFrameKey : EncodedImage.FrameType.VideoFrameDelta;
            EncodedImage.Builder builder = this.outputBuilders.poll();
            builder.setBuffer(frameBuffer, releaseCallback);
            builder.setFrameType(frameType);
            builder.setQp(qp);
            EncodedImage encodedImage = builder.createEncodedImage();
            this.callback.onEncodedFrame(encodedImage, new VideoEncoder.CodecSpecificInfo());
            encodedImage.release();
        } catch (IllegalStateException e) {
            Logging.e(TAG, "deliverOutput failed", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$deliverEncodedImage$0$org-webrtc-HardwareVideoEncoder  reason: not valid java name */
    public /* synthetic */ void m2107lambda$deliverEncodedImage$0$orgwebrtcHardwareVideoEncoder(int index) {
        try {
            this.codec.releaseOutputBuffer(index, false);
        } catch (Exception e) {
            Logging.e(TAG, "releaseOutputBuffer failed", e);
        }
        this.outputBuffersBusyCount.decrement();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseCodecOnOutputThread() {
        this.outputThreadChecker.checkIsOnValidThread();
        Logging.d(TAG, "Releasing MediaCodec on output thread");
        this.outputBuffersBusyCount.waitForZero();
        try {
            this.codec.stop();
        } catch (Exception e) {
            Logging.e(TAG, "Media encoder stop failed", e);
        }
        try {
            this.codec.release();
        } catch (Exception e2) {
            Logging.e(TAG, "Media encoder release failed", e2);
            this.shutdownException = e2;
        }
        this.configBuffer = null;
        Logging.d(TAG, "Release on output thread done");
    }

    private VideoCodecStatus updateBitrate() {
        this.outputThreadChecker.checkIsOnValidThread();
        this.adjustedBitrate = this.bitrateAdjuster.getAdjustedBitrateBps();
        try {
            Bundle params = new Bundle();
            params.putInt("video-bitrate", this.adjustedBitrate);
            this.codec.setParameters(params);
            return VideoCodecStatus.OK;
        } catch (IllegalStateException e) {
            Logging.e(TAG, "updateBitrate failed", e);
            return VideoCodecStatus.ERROR;
        }
    }

    private boolean canUseSurface() {
        return (this.sharedContext == null || this.surfaceColorFormat == null) ? false : true;
    }

    private void updateInputFormat(MediaFormat format) {
        this.stride = this.width;
        this.sliceHeight = this.height;
        if (format != null) {
            if (format.containsKey("stride")) {
                this.stride = format.getInteger("stride");
                this.stride = Math.max(this.stride, this.width);
            }
            if (format.containsKey("slice-height")) {
                this.sliceHeight = format.getInteger("slice-height");
                this.sliceHeight = Math.max(this.sliceHeight, this.height);
            }
        }
        this.isSemiPlanar = isSemiPlanar(this.yuvColorFormat.intValue());
        if (this.isSemiPlanar) {
            int chromaHeight = (this.height + 1) / 2;
            this.frameSizeBytes = (this.sliceHeight * this.stride) + (this.stride * chromaHeight);
        } else {
            int chromaStride = (this.stride + 1) / 2;
            int chromaSliceHeight = (this.sliceHeight + 1) / 2;
            this.frameSizeBytes = (this.sliceHeight * this.stride) + (chromaSliceHeight * chromaStride * 2);
        }
        Logging.d(TAG, "updateInputFormat format: " + format + " stride: " + this.stride + " sliceHeight: " + this.sliceHeight + " isSemiPlanar: " + this.isSemiPlanar + " frameSizeBytes: " + this.frameSizeBytes);
    }

    protected boolean isEncodingStatisticsSupported() {
        MediaCodecInfo codecInfo;
        MediaCodecInfo.CodecCapabilities codecCaps;
        if (this.codecType == VideoCodecMimeType.VP8 || this.codecType == VideoCodecMimeType.VP9 || (codecInfo = this.codec.getCodecInfo()) == null || (codecCaps = codecInfo.getCapabilitiesForType(this.codecType.mimeType())) == null) {
            return false;
        }
        return codecCaps.isFeatureSupported("encoding-statistics");
    }

    protected void fillInputBuffer(ByteBuffer buffer, VideoFrame.Buffer frame) {
        VideoFrame.I420Buffer i420 = frame.toI420();
        if (this.isSemiPlanar) {
            YuvHelper.I420ToNV12(i420.getDataY(), i420.getStrideY(), i420.getDataU(), i420.getStrideU(), i420.getDataV(), i420.getStrideV(), buffer, i420.getWidth(), i420.getHeight(), this.stride, this.sliceHeight);
        } else {
            YuvHelper.I420Copy(i420.getDataY(), i420.getStrideY(), i420.getDataU(), i420.getStrideU(), i420.getDataV(), i420.getStrideV(), buffer, i420.getWidth(), i420.getHeight(), this.stride, this.sliceHeight);
        }
        i420.release();
    }

    protected boolean isSemiPlanar(int colorFormat) {
        switch (colorFormat) {
            case 19:
                return false;
            case 21:
            case 2141391872:
            case 2141391876:
                return true;
            default:
                throw new IllegalArgumentException("Unsupported colorFormat: " + colorFormat);
        }
    }
}
