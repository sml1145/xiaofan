package org.webrtc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes5.dex */
public class SimulcastVideoEncoderFactory implements VideoEncoderFactory {
    VideoEncoderFactory fallback;
    VideoEncoderFactory primary;

    static native VideoCodecInfo nativeAV1Codec();

    static native List<VideoCodecInfo> nativeVP9Codecs();

    public SimulcastVideoEncoderFactory(VideoEncoderFactory primary, VideoEncoderFactory fallback) {
        this.primary = primary;
        this.fallback = fallback;
    }

    @Override // org.webrtc.VideoEncoderFactory
    public VideoEncoder createEncoder(VideoCodecInfo info) {
        return new SimulcastVideoEncoder(this.primary, this.fallback, info);
    }

    @Override // org.webrtc.VideoEncoderFactory
    public VideoCodecInfo[] getSupportedCodecs() {
        List<VideoCodecInfo> codecs = new ArrayList<>();
        codecs.addAll(Arrays.asList(this.primary.getSupportedCodecs()));
        if (this.fallback != null) {
            codecs.addAll(Arrays.asList(this.fallback.getSupportedCodecs()));
        }
        codecs.addAll(nativeVP9Codecs());
        codecs.add(nativeAV1Codec());
        return (VideoCodecInfo[]) codecs.toArray(new VideoCodecInfo[codecs.size()]);
    }
}
