package org.webrtc;

import android.os.Handler;
import android.os.HandlerThread;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.concurrent.CountDownLatch;
import org.webrtc.EglBase;
import org.webrtc.VideoFrame;
/* loaded from: classes5.dex */
public class VideoFileRenderer implements VideoSink {
    private static final String TAG = "VideoFileRenderer";
    private EglBase eglBase;
    private final HandlerThread fileThread;
    private final Handler fileThreadHandler;
    private int frameCount;
    private final int outputFileHeight;
    private final String outputFileName;
    private final int outputFileWidth;
    private final ByteBuffer outputFrameBuffer;
    private final int outputFrameSize;
    private final HandlerThread renderThread;
    private final Handler renderThreadHandler;
    private final FileOutputStream videoOutFile;
    private YuvConverter yuvConverter;

    public VideoFileRenderer(String outputFile, int outputFileWidth, int outputFileHeight, final EglBase.Context sharedContext) throws IOException {
        if (outputFileWidth % 2 == 1 || outputFileHeight % 2 == 1) {
            throw new IllegalArgumentException("Does not support uneven width or height");
        }
        this.outputFileName = outputFile;
        this.outputFileWidth = outputFileWidth;
        this.outputFileHeight = outputFileHeight;
        this.outputFrameSize = ((outputFileWidth * outputFileHeight) * 3) / 2;
        this.outputFrameBuffer = ByteBuffer.allocateDirect(this.outputFrameSize);
        this.videoOutFile = new FileOutputStream(outputFile);
        this.videoOutFile.write(("YUV4MPEG2 C420 W" + outputFileWidth + " H" + outputFileHeight + " Ip F30:1 A1:1\n").getBytes(Charset.forName("US-ASCII")));
        this.renderThread = new HandlerThread("VideoFileRendererRenderThread");
        this.renderThread.start();
        this.renderThreadHandler = new Handler(this.renderThread.getLooper());
        this.fileThread = new HandlerThread("VideoFileRendererFileThread");
        this.fileThread.start();
        this.fileThreadHandler = new Handler(this.fileThread.getLooper());
        ThreadUtils.invokeAtFrontUninterruptibly(this.renderThreadHandler, new Runnable() { // from class: org.webrtc.VideoFileRenderer.1
            @Override // java.lang.Runnable
            public void run() {
                VideoFileRenderer.this.eglBase = EglBase.create(sharedContext, EglBase.CONFIG_PIXEL_BUFFER);
                VideoFileRenderer.this.eglBase.createDummyPbufferSurface();
                VideoFileRenderer.this.eglBase.makeCurrent();
                VideoFileRenderer.this.yuvConverter = new YuvConverter();
            }
        });
    }

    @Override // org.webrtc.VideoSink
    public void onFrame(final VideoFrame frame) {
        frame.retain();
        this.renderThreadHandler.post(new Runnable() { // from class: org.webrtc.VideoFileRenderer$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                VideoFileRenderer.this.m2176lambda$onFrame$0$orgwebrtcVideoFileRenderer(frame);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: renderFrameOnRenderThread */
    public void m2176lambda$onFrame$0$orgwebrtcVideoFileRenderer(final VideoFrame frame) {
        int cropWidth;
        int cropHeight;
        VideoFrame.Buffer buffer = frame.getBuffer();
        int targetWidth = frame.getRotation() % 180 == 0 ? this.outputFileWidth : this.outputFileHeight;
        int targetHeight = frame.getRotation() % 180 == 0 ? this.outputFileHeight : this.outputFileWidth;
        float frameAspectRatio = buffer.getWidth() / buffer.getHeight();
        float fileAspectRatio = targetWidth / targetHeight;
        int cropWidth2 = buffer.getWidth();
        int cropHeight2 = buffer.getHeight();
        if (fileAspectRatio > frameAspectRatio) {
            cropWidth = cropWidth2;
            cropHeight = (int) (cropHeight2 * (frameAspectRatio / fileAspectRatio));
        } else {
            cropWidth = (int) (cropWidth2 * (fileAspectRatio / frameAspectRatio));
            cropHeight = cropHeight2;
        }
        int cropX = (buffer.getWidth() - cropWidth) / 2;
        int cropY = (buffer.getHeight() - cropHeight) / 2;
        VideoFrame.Buffer scaledBuffer = buffer.cropAndScale(cropX, cropY, cropWidth, cropHeight, targetWidth, targetHeight);
        frame.release();
        final VideoFrame.I420Buffer i420 = scaledBuffer.toI420();
        scaledBuffer.release();
        this.fileThreadHandler.post(new Runnable() { // from class: org.webrtc.VideoFileRenderer$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                VideoFileRenderer.this.m2179lambda$renderFrameOnRenderThread$1$orgwebrtcVideoFileRenderer(i420, frame);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$renderFrameOnRenderThread$1$org-webrtc-VideoFileRenderer  reason: not valid java name */
    public /* synthetic */ void m2179lambda$renderFrameOnRenderThread$1$orgwebrtcVideoFileRenderer(VideoFrame.I420Buffer i420, VideoFrame frame) {
        YuvHelper.I420Rotate(i420.getDataY(), i420.getStrideY(), i420.getDataU(), i420.getStrideU(), i420.getDataV(), i420.getStrideV(), this.outputFrameBuffer, i420.getWidth(), i420.getHeight(), frame.getRotation());
        i420.release();
        try {
            this.videoOutFile.write("FRAME\n".getBytes(Charset.forName("US-ASCII")));
            this.videoOutFile.write(this.outputFrameBuffer.array(), this.outputFrameBuffer.arrayOffset(), this.outputFrameSize);
            this.frameCount++;
        } catch (IOException e) {
            throw new RuntimeException("Error writing video to disk", e);
        }
    }

    public void release() {
        final CountDownLatch cleanupBarrier = new CountDownLatch(1);
        this.renderThreadHandler.post(new Runnable() { // from class: org.webrtc.VideoFileRenderer$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                VideoFileRenderer.this.m2177lambda$release$2$orgwebrtcVideoFileRenderer(cleanupBarrier);
            }
        });
        ThreadUtils.awaitUninterruptibly(cleanupBarrier);
        this.fileThreadHandler.post(new Runnable() { // from class: org.webrtc.VideoFileRenderer$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                VideoFileRenderer.this.m2178lambda$release$3$orgwebrtcVideoFileRenderer();
            }
        });
        try {
            this.fileThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Logging.e(TAG, "Interrupted while waiting for the write to disk to complete.", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$release$2$org-webrtc-VideoFileRenderer  reason: not valid java name */
    public /* synthetic */ void m2177lambda$release$2$orgwebrtcVideoFileRenderer(CountDownLatch cleanupBarrier) {
        this.yuvConverter.release();
        this.eglBase.release();
        this.renderThread.quit();
        cleanupBarrier.countDown();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$release$3$org-webrtc-VideoFileRenderer  reason: not valid java name */
    public /* synthetic */ void m2178lambda$release$3$orgwebrtcVideoFileRenderer() {
        try {
            this.videoOutFile.close();
            Logging.d(TAG, "Video written to disk as " + this.outputFileName + ". The number of frames is " + this.frameCount + " and the dimensions of the frames are " + this.outputFileWidth + "x" + this.outputFileHeight + ".");
            this.fileThread.quit();
        } catch (IOException e) {
            throw new RuntimeException("Error closing output file", e);
        }
    }
}
