package org.webrtc;

import java.util.List;
/* loaded from: classes5.dex */
public class LibvpxVp9Encoder extends WrappedNativeVideoEncoder {
    static native long nativeCreate(long j);

    static native List<String> nativeGetSupportedScalabilityModes();

    static native boolean nativeIsSupported();

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
