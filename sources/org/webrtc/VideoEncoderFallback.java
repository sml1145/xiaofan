package org.webrtc;
/* loaded from: classes5.dex */
public class VideoEncoderFallback extends WrappedNativeVideoEncoder {
    private final VideoEncoder fallback;
    private final VideoEncoder primary;

    private static native long nativeCreate(long j, VideoEncoder videoEncoder, VideoEncoder videoEncoder2);

    public VideoEncoderFallback(VideoEncoder fallback, VideoEncoder primary) {
        this.fallback = fallback;
        this.primary = primary;
    }

    @Override // org.webrtc.WrappedNativeVideoEncoder, org.webrtc.VideoEncoder
    public long createNative(long webrtcEnvRef) {
        return nativeCreate(webrtcEnvRef, this.fallback, this.primary);
    }

    @Override // org.webrtc.WrappedNativeVideoEncoder, org.webrtc.VideoEncoder
    public boolean isHardwareEncoder() {
        return this.primary.isHardwareEncoder();
    }
}
