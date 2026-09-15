package org.webrtc;
/* loaded from: classes5.dex */
public class SimulcastVideoEncoder extends WrappedNativeVideoEncoder {
    VideoEncoderFactory fallback;
    VideoCodecInfo info;
    VideoEncoderFactory primary;

    static native long nativeCreateEncoder(long j, VideoEncoderFactory videoEncoderFactory, VideoEncoderFactory videoEncoderFactory2, VideoCodecInfo videoCodecInfo);

    public SimulcastVideoEncoder(VideoEncoderFactory primary, VideoEncoderFactory fallback, VideoCodecInfo info) {
        this.primary = primary;
        this.fallback = fallback;
        this.info = info;
    }

    @Override // org.webrtc.WrappedNativeVideoEncoder, org.webrtc.VideoEncoder
    public long createNative(long webrtcEnvRef) {
        return nativeCreateEncoder(webrtcEnvRef, this.primary, this.fallback, this.info);
    }

    @Override // org.webrtc.WrappedNativeVideoEncoder, org.webrtc.VideoEncoder
    public boolean isHardwareEncoder() {
        return false;
    }
}
