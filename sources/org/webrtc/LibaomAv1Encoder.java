package org.webrtc;

import java.util.List;
/* loaded from: classes5.dex */
public class LibaomAv1Encoder extends WrappedNativeVideoEncoder {
    static native long nativeCreate(long j);

    static native List<String> nativeGetSupportedScalabilityModes();

    @Override // org.webrtc.WrappedNativeVideoEncoder, org.webrtc.VideoEncoder
    public long createNative(long webrtcEnvRef) {
        return nativeCreate(webrtcEnvRef);
    }

    @Override // org.webrtc.WrappedNativeVideoEncoder, org.webrtc.VideoEncoder
    public boolean isHardwareEncoder() {
        return false;
    }

    static List<String> scalabilityModes() {
        return nativeGetSupportedScalabilityModes();
    }
}
