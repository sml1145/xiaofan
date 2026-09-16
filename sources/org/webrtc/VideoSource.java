package org.webrtc;

import org.webrtc.VideoProcessor;
/* loaded from: classes5.dex */
public class VideoSource extends MediaSource {
    private final CapturerObserver capturerObserver;
    private boolean isCapturerRunning;
    private final NativeAndroidVideoTrackSource nativeAndroidVideoTrackSource;
    private VideoProcessor videoProcessor;
    private final Object videoProcessorLock;

    /* loaded from: classes5.dex */
    public static class AspectRatio {
        public static final AspectRatio UNDEFINED = new AspectRatio(0, 0);
        public final int height;
        public final int width;

        public AspectRatio(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }

    public VideoSource(long nativeSource) {
        super(nativeSource);
        this.videoProcessorLock = new Object();
        this.capturerObserver = new CapturerObserver() { // from class: org.webrtc.VideoSource.1
            @Override // org.webrtc.CapturerObserver
            public void onCapturerStarted(boolean success) {
                VideoSource.this.nativeAndroidVideoTrackSource.setState(success);
                synchronized (VideoSource.this.videoProcessorLock) {
                    VideoSource.this.isCapturerRunning = success;
                    if (VideoSource.this.videoProcessor != null) {
                        VideoSource.this.videoProcessor.onCapturerStarted(success);
                    }
                }
            }

            @Override // org.webrtc.CapturerObserver
            public void onCapturerStopped() {
                VideoSource.this.nativeAndroidVideoTrackSource.setState(false);
                synchronized (VideoSource.this.videoProcessorLock) {
                    VideoSource.this.isCapturerRunning = false;
                    if (VideoSource.this.videoProcessor != null) {
                        VideoSource.this.videoProcessor.onCapturerStopped();
                    }
                }
            }

            @Override // org.webrtc.CapturerObserver
            public void onFrameCaptured(VideoFrame frame) {
                VideoProcessor.FrameAdaptationParameters parameters = VideoSource.this.nativeAndroidVideoTrackSource.adaptFrame(frame);
                synchronized (VideoSource.this.videoProcessorLock) {
                    if (VideoSource.this.videoProcessor != null) {
                        VideoSource.this.videoProcessor.onFrameCaptured(frame, parameters);
                        return;
                    }
                    VideoFrame adaptedFrame = VideoProcessor.applyFrameAdaptationParameters(frame, parameters);
                    if (adaptedFrame != null) {
                        VideoSource.this.nativeAndroidVideoTrackSource.onFrameCaptured(adaptedFrame);
                        adaptedFrame.release();
                    }
                }
            }
        };
        this.nativeAndroidVideoTrackSource = new NativeAndroidVideoTrackSource(nativeSource);
    }

    public void adaptOutputFormat(int width, int height, int fps) {
        int maxSide = Math.max(width, height);
        int minSide = Math.min(width, height);
        adaptOutputFormat(maxSide, minSide, minSide, maxSide, fps);
    }

    public void adaptOutputFormat(int landscapeWidth, int landscapeHeight, int portraitWidth, int portraitHeight, int fps) {
        adaptOutputFormat(new AspectRatio(landscapeWidth, landscapeHeight), Integer.valueOf(landscapeWidth * landscapeHeight), new AspectRatio(portraitWidth, portraitHeight), Integer.valueOf(portraitWidth * portraitHeight), Integer.valueOf(fps));
    }

    public void adaptOutputFormat(AspectRatio targetLandscapeAspectRatio, Integer maxLandscapePixelCount, AspectRatio targetPortraitAspectRatio, Integer maxPortraitPixelCount, Integer maxFps) {
        this.nativeAndroidVideoTrackSource.adaptOutputFormat(targetLandscapeAspectRatio, maxLandscapePixelCount, targetPortraitAspectRatio, maxPortraitPixelCount, maxFps);
    }

    public void setIsScreencast(boolean isScreencast) {
        this.nativeAndroidVideoTrackSource.setIsScreencast(isScreencast);
    }

    public void setVideoProcessor(VideoProcessor newVideoProcessor) {
        synchronized (this.videoProcessorLock) {
            if (this.videoProcessor != null) {
                this.videoProcessor.setSink(null);
                if (this.isCapturerRunning) {
                    this.videoProcessor.onCapturerStopped();
                }
            }
            this.videoProcessor = newVideoProcessor;
            if (newVideoProcessor != null) {
                newVideoProcessor.setSink(new VideoSink() { // from class: org.webrtc.VideoSource$$ExternalSyntheticLambda1
                    @Override // org.webrtc.VideoSink
                    public final void onFrame(VideoFrame videoFrame) {
                        VideoSource.this.m2180lambda$setVideoProcessor$1$orgwebrtcVideoSource(videoFrame);
                    }
                });
                if (this.isCapturerRunning) {
                    newVideoProcessor.onCapturerStarted(true);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$setVideoProcessor$0$org-webrtc-VideoSource  reason: not valid java name */
    public /* synthetic */ void m2179lambda$setVideoProcessor$0$orgwebrtcVideoSource(VideoFrame frame) {
        this.nativeAndroidVideoTrackSource.onFrameCaptured(frame);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$setVideoProcessor$1$org-webrtc-VideoSource  reason: not valid java name */
    public /* synthetic */ void m2180lambda$setVideoProcessor$1$orgwebrtcVideoSource(final VideoFrame frame) {
        runWithReference(new Runnable() { // from class: org.webrtc.VideoSource$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                VideoSource.this.m2179lambda$setVideoProcessor$0$orgwebrtcVideoSource(frame);
            }
        });
    }

    public CapturerObserver getCapturerObserver() {
        return this.capturerObserver;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getNativeVideoTrackSource() {
        return getNativeMediaSource();
    }

    @Override // org.webrtc.MediaSource
    public void dispose() {
        setVideoProcessor(null);
        super.dispose();
    }
}
