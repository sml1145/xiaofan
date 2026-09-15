package org.webrtc;
/* loaded from: classes5.dex */
public class VideoDecoderFallback extends WrappedNativeVideoDecoder {
    private final VideoDecoder fallback;
    private final VideoDecoder primary;

    private static native long nativeCreate(long j, VideoDecoder videoDecoder, VideoDecoder videoDecoder2);

    public VideoDecoderFallback(VideoDecoder fallback, VideoDecoder primary) {
        this.fallback = fallback;
        this.primary = primary;
    }

    @Override // org.webrtc.WrappedNativeVideoDecoder, org.webrtc.VideoDecoder
    public long createNative(long webrtcEnvRef) {
        return nativeCreate(webrtcEnvRef, this.fallback, this.primary);
    }
}
