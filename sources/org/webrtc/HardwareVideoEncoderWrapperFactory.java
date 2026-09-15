package org.webrtc;
/* loaded from: classes5.dex */
class HardwareVideoEncoderWrapperFactory implements VideoEncoderFactory {
    private static final String TAG = "HardwareVideoEncoderWrapperFactory";
    private final HardwareVideoEncoderFactory factory;
    private final int resolutionPixelAlignment;

    public HardwareVideoEncoderWrapperFactory(HardwareVideoEncoderFactory factory, int resolutionPixelAlignment) {
        this.factory = factory;
        this.resolutionPixelAlignment = resolutionPixelAlignment;
        if (resolutionPixelAlignment == 0) {
            throw new IllegalArgumentException("resolutionPixelAlignment should not be 0");
        }
    }

    @Override // org.webrtc.VideoEncoderFactory
    public VideoEncoder createEncoder(VideoCodecInfo videoCodecInfo) {
        try {
            VideoEncoder encoder = this.factory.createEncoder(videoCodecInfo);
            if (encoder == null) {
                return null;
            }
            return new HardwareVideoEncoderWrapper(encoder, this.resolutionPixelAlignment);
        } catch (Exception e) {
            Logging.e(TAG, "createEncoder failed", e);
            return null;
        }
    }

    @Override // org.webrtc.VideoEncoderFactory
    public VideoCodecInfo[] getSupportedCodecs() {
        return this.factory.getSupportedCodecs();
    }
}
