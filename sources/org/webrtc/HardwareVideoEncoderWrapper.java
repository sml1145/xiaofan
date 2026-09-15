package org.webrtc;

import org.webrtc.VideoEncoder;
import org.webrtc.VideoFrame;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public class HardwareVideoEncoderWrapper implements VideoEncoder {
    private static final String TAG = "HardwareVideoEncoderWrapper";
    private final int alignment;
    private CropSizeCalculator calculator = new CropSizeCalculator(1, 0, 0);
    private final VideoEncoder internalEncoder;

    public HardwareVideoEncoderWrapper(VideoEncoder internalEncoder, int alignment) {
        this.internalEncoder = internalEncoder;
        this.alignment = alignment;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class CropSizeCalculator {
        private static final String TAG = "CropSizeCalculator";
        private final int alignment;
        private final int cropX;
        private final int cropY;
        private final int originalHeight;
        private final int originalWidth;

        public CropSizeCalculator(int alignment, int originalWidth, int originalHeight) {
            this.alignment = alignment;
            this.originalWidth = originalWidth;
            this.originalHeight = originalHeight;
            this.cropX = originalWidth % alignment;
            this.cropY = originalHeight % alignment;
            if (originalWidth != 0 && originalHeight != 0) {
                Logging.v(TAG, "init(): alignment=" + alignment + " size=" + originalWidth + "x" + originalHeight + " => " + getCroppedWidth() + "x" + getCroppedHeight());
            }
        }

        public int getCroppedWidth() {
            return this.originalWidth - this.cropX;
        }

        public int getCroppedHeight() {
            return this.originalHeight - this.cropY;
        }

        public boolean isCropRequired() {
            return (this.cropX == 0 && this.cropY == 0) ? false : true;
        }

        public boolean hasFrameSizeChanged(int nextWidth, int nextHeight) {
            if (this.originalWidth == nextWidth && this.originalHeight == nextHeight) {
                return false;
            }
            Logging.v(TAG, "frame size has changed: " + this.originalWidth + "x" + this.originalHeight + " => " + nextWidth + "x" + nextHeight);
            return true;
        }
    }

    private VideoCodecStatus retryWithoutCropping(int width, int height, Runnable retryFunc) {
        Logging.v(TAG, "retrying without resolution adjustment");
        this.calculator = new CropSizeCalculator(1, width, height);
        retryFunc.run();
        return VideoCodecStatus.OK;
    }

    @Override // org.webrtc.VideoEncoder
    public VideoCodecStatus initEncode(final VideoEncoder.Settings originalSettings, final VideoEncoder.Callback callback) {
        this.calculator = new CropSizeCalculator(this.alignment, originalSettings.width, originalSettings.height);
        if (!this.calculator.isCropRequired()) {
            return this.internalEncoder.initEncode(originalSettings, callback);
        }
        VideoEncoder.Settings croppedSettings = new VideoEncoder.Settings(originalSettings.numberOfCores, this.calculator.getCroppedWidth(), this.calculator.getCroppedHeight(), originalSettings.startBitrate, originalSettings.maxFramerate, originalSettings.numberOfSimulcastStreams, originalSettings.automaticResizeOn, originalSettings.capabilities);
        try {
            VideoCodecStatus result = this.internalEncoder.initEncode(croppedSettings, callback);
            if (result == VideoCodecStatus.FALLBACK_SOFTWARE) {
                Logging.e(TAG, "internalEncoder.initEncode() returned FALLBACK_SOFTWARE: croppedSettings " + croppedSettings);
                return retryWithoutCropping(originalSettings.width, originalSettings.height, new Runnable() { // from class: org.webrtc.HardwareVideoEncoderWrapper$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        HardwareVideoEncoderWrapper.this.m2105lambda$initEncode$0$orgwebrtcHardwareVideoEncoderWrapper(originalSettings, callback);
                    }
                });
            }
            return result;
        } catch (Exception e) {
            Logging.e(TAG, "internalEncoder.initEncode() failed", e);
            return retryWithoutCropping(originalSettings.width, originalSettings.height, new Runnable() { // from class: org.webrtc.HardwareVideoEncoderWrapper$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    HardwareVideoEncoderWrapper.this.m2106lambda$initEncode$1$orgwebrtcHardwareVideoEncoderWrapper(originalSettings, callback);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$initEncode$0$org-webrtc-HardwareVideoEncoderWrapper  reason: not valid java name */
    public /* synthetic */ void m2105lambda$initEncode$0$orgwebrtcHardwareVideoEncoderWrapper(VideoEncoder.Settings originalSettings, VideoEncoder.Callback callback) {
        this.internalEncoder.initEncode(originalSettings, callback);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$initEncode$1$org-webrtc-HardwareVideoEncoderWrapper  reason: not valid java name */
    public /* synthetic */ void m2106lambda$initEncode$1$orgwebrtcHardwareVideoEncoderWrapper(VideoEncoder.Settings originalSettings, VideoEncoder.Callback callback) {
        this.internalEncoder.initEncode(originalSettings, callback);
    }

    @Override // org.webrtc.VideoEncoder
    public VideoCodecStatus release() {
        return this.internalEncoder.release();
    }

    @Override // org.webrtc.VideoEncoder
    public VideoCodecStatus encode(final VideoFrame frame, final VideoEncoder.EncodeInfo encodeInfo) {
        if (this.calculator.hasFrameSizeChanged(frame.getBuffer().getWidth(), frame.getBuffer().getHeight())) {
            this.calculator = new CropSizeCalculator(this.alignment, frame.getBuffer().getWidth(), frame.getBuffer().getHeight());
        }
        if (this.calculator.isCropRequired()) {
            int croppedWidth = this.calculator.getCroppedWidth();
            int croppedHeight = this.calculator.getCroppedHeight();
            VideoFrame.Buffer croppedBuffer = frame.getBuffer().cropAndScale(this.calculator.cropX / 2, this.calculator.cropY / 2, croppedWidth, croppedHeight, croppedWidth, croppedHeight);
            VideoFrame croppedFrame = new VideoFrame(croppedBuffer, frame.getRotation(), frame.getTimestampNs());
            try {
                VideoCodecStatus result = this.internalEncoder.encode(croppedFrame, encodeInfo);
                if (result == VideoCodecStatus.FALLBACK_SOFTWARE) {
                    Logging.e(TAG, "internalEncoder.encode() returned FALLBACK_SOFTWARE");
                    return retryWithoutCropping(frame.getBuffer().getWidth(), frame.getBuffer().getHeight(), new Runnable() { // from class: org.webrtc.HardwareVideoEncoderWrapper$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            HardwareVideoEncoderWrapper.this.m2103lambda$encode$2$orgwebrtcHardwareVideoEncoderWrapper(frame, encodeInfo);
                        }
                    });
                }
                return result;
            } catch (Exception e) {
                Logging.e(TAG, "internalEncoder.encode() failed", e);
                return retryWithoutCropping(frame.getBuffer().getWidth(), frame.getBuffer().getHeight(), new Runnable() { // from class: org.webrtc.HardwareVideoEncoderWrapper$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        HardwareVideoEncoderWrapper.this.m2104lambda$encode$3$orgwebrtcHardwareVideoEncoderWrapper(frame, encodeInfo);
                    }
                });
            } finally {
                croppedBuffer.release();
            }
        }
        return this.internalEncoder.encode(frame, encodeInfo);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$encode$2$org-webrtc-HardwareVideoEncoderWrapper  reason: not valid java name */
    public /* synthetic */ void m2103lambda$encode$2$orgwebrtcHardwareVideoEncoderWrapper(VideoFrame frame, VideoEncoder.EncodeInfo encodeInfo) {
        this.internalEncoder.encode(frame, encodeInfo);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$encode$3$org-webrtc-HardwareVideoEncoderWrapper  reason: not valid java name */
    public /* synthetic */ void m2104lambda$encode$3$orgwebrtcHardwareVideoEncoderWrapper(VideoFrame frame, VideoEncoder.EncodeInfo encodeInfo) {
        this.internalEncoder.encode(frame, encodeInfo);
    }

    @Override // org.webrtc.VideoEncoder
    public VideoCodecStatus setRateAllocation(VideoEncoder.BitrateAllocation allocation, int frameRate) {
        return this.internalEncoder.setRateAllocation(allocation, frameRate);
    }

    @Override // org.webrtc.VideoEncoder
    public VideoEncoder.ScalingSettings getScalingSettings() {
        return this.internalEncoder.getScalingSettings();
    }

    @Override // org.webrtc.VideoEncoder
    public String getImplementationName() {
        return this.internalEncoder.getImplementationName();
    }

    @Override // org.webrtc.VideoEncoder
    public long createNative(long webrtcEnvRef) {
        return this.internalEncoder.createNative(webrtcEnvRef);
    }

    @Override // org.webrtc.VideoEncoder
    public boolean isHardwareEncoder() {
        return this.internalEncoder.isHardwareEncoder();
    }

    @Override // org.webrtc.VideoEncoder
    public VideoCodecStatus setRates(VideoEncoder.RateControlParameters rcParameters) {
        return this.internalEncoder.setRates(rcParameters);
    }

    @Override // org.webrtc.VideoEncoder
    public VideoEncoder.ResolutionBitrateLimits[] getResolutionBitrateLimits() {
        return this.internalEncoder.getResolutionBitrateLimits();
    }

    @Override // org.webrtc.VideoEncoder
    public VideoEncoder.EncoderInfo getEncoderInfo() {
        return this.internalEncoder.getEncoderInfo();
    }
}
