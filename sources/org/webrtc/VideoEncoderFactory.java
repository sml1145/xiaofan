package org.webrtc;
/* loaded from: classes5.dex */
public interface VideoEncoderFactory {
    VideoEncoder createEncoder(VideoCodecInfo videoCodecInfo);

    VideoCodecInfo[] getSupportedCodecs();

    /* loaded from: classes5.dex */
    public interface VideoEncoderSelector {
        VideoCodecInfo onAvailableBitrate(int i);

        void onCurrentEncoder(VideoCodecInfo videoCodecInfo);

        VideoCodecInfo onEncoderBroken();

        default VideoCodecInfo onResolutionChange(int widht, int height) {
            return null;
        }
    }

    default VideoCodecInfo[] getImplementations() {
        return getSupportedCodecs();
    }

    default VideoEncoderSelector getEncoderSelector() {
        return null;
    }
}
