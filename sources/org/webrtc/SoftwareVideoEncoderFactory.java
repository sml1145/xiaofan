package org.webrtc;

import java.util.List;
/* loaded from: classes5.dex */
public class SoftwareVideoEncoderFactory implements VideoEncoderFactory {
    private static final String TAG = "SoftwareVideoEncoderFactory";
    private final long nativeFactory = nativeCreateFactory();

    /* JADX INFO: Access modifiers changed from: private */
    public static native long nativeCreate(long j, long j2, VideoCodecInfo videoCodecInfo);

    private static native long nativeCreateFactory();

    private static native List<VideoCodecInfo> nativeGetSupportedCodecs(long j);

    private static native boolean nativeIsSupported(long j, VideoCodecInfo videoCodecInfo);

    @Override // org.webrtc.VideoEncoderFactory
    public VideoEncoder createEncoder(final VideoCodecInfo info) {
        if (!nativeIsSupported(this.nativeFactory, info)) {
            Logging.w(TAG, "Trying to create encoder for unsupported format. " + info);
            return null;
        }
        return new WrappedNativeVideoEncoder() { // from class: org.webrtc.SoftwareVideoEncoderFactory.1
            @Override // org.webrtc.WrappedNativeVideoEncoder, org.webrtc.VideoEncoder
            public long createNative(long webrtcEnvRef) {
                return SoftwareVideoEncoderFactory.nativeCreate(SoftwareVideoEncoderFactory.this.nativeFactory, webrtcEnvRef, info);
            }

            @Override // org.webrtc.WrappedNativeVideoEncoder, org.webrtc.VideoEncoder
            public boolean isHardwareEncoder() {
                return false;
            }
        };
    }

    @Override // org.webrtc.VideoEncoderFactory
    public VideoCodecInfo[] getSupportedCodecs() {
        return (VideoCodecInfo[]) nativeGetSupportedCodecs(this.nativeFactory).toArray(new VideoCodecInfo[0]);
    }
}
