package org.webrtc;
/* loaded from: classes5.dex */
public class LibvpxVp8Encoder extends WrappedNativeVideoEncoder {
    static native long nativeCreate(long j);

    @Override // org.webrtc.WrappedNativeVideoEncoder, org.webrtc.VideoEncoder
    public long createNative(long webrtcEnvRef) {
        return nativeCreate(webrtcEnvRef);
    }

    @Override // org.webrtc.WrappedNativeVideoEncoder, org.webrtc.VideoEncoder
    public boolean isHardwareEncoder() {
        return false;
    }
}
