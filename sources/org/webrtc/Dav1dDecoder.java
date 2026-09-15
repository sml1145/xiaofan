package org.webrtc;
/* loaded from: classes5.dex */
public class Dav1dDecoder extends WrappedNativeVideoDecoder {
    static native long nativeCreateDecoder();

    @Override // org.webrtc.WrappedNativeVideoDecoder, org.webrtc.VideoDecoder
    public long createNative(long webrtcEnvRef) {
        return nativeCreateDecoder();
    }
}
