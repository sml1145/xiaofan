package org.webrtc;
/* loaded from: classes5.dex */
public class LibvpxVp8Decoder extends WrappedNativeVideoDecoder {
    static native long nativeCreateDecoder(long j);

    @Override // org.webrtc.WrappedNativeVideoDecoder, org.webrtc.VideoDecoder
    public long createNative(long webrtcEnvRef) {
        return nativeCreateDecoder(webrtcEnvRef);
    }
}
