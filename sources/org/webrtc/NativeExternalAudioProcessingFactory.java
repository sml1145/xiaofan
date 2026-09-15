package org.webrtc;
/* loaded from: classes5.dex */
public class NativeExternalAudioProcessingFactory implements AudioProcessingFactory {
    private final String libname;

    private static native long nativeCreateAudioProcessingModule(String str);

    private static native void nativeDestroyAudioProcessingModule();

    public NativeExternalAudioProcessingFactory(String libname) {
        if (libname == null) {
            throw new NullPointerException("libname must not be null.");
        }
        if (libname.isEmpty()) {
            throw new IllegalArgumentException("libname must not be empty.");
        }
        this.libname = libname;
    }

    @Override // org.webrtc.AudioProcessingFactory
    public long createNative() {
        return nativeCreateAudioProcessingModule(this.libname);
    }

    public void destroyNative() {
        nativeDestroyAudioProcessingModule();
    }
}
