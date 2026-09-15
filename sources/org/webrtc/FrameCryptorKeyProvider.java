package org.webrtc;
/* loaded from: classes5.dex */
public class FrameCryptorKeyProvider {
    private long nativeKeyProvider;

    private static native byte[] nativeExportKey(long j, String str, int i);

    private static native byte[] nativeExportSharedKey(long j, int i);

    private static native byte[] nativeRatchetKey(long j, String str, int i);

    private static native byte[] nativeRatchetSharedKey(long j, int i);

    private static native boolean nativeSetKey(long j, String str, int i, byte[] bArr);

    private static native boolean nativeSetSharedKey(long j, int i, byte[] bArr);

    private static native void nativeSetSifTrailer(long j, byte[] bArr);

    public FrameCryptorKeyProvider(long nativeKeyProvider) {
        this.nativeKeyProvider = nativeKeyProvider;
    }

    public long getNativeKeyProvider() {
        return this.nativeKeyProvider;
    }

    public boolean setSharedKey(int index, byte[] key) {
        checkKeyProviderExists();
        return nativeSetSharedKey(this.nativeKeyProvider, index, key);
    }

    public byte[] ratchetSharedKey(int index) {
        checkKeyProviderExists();
        return nativeRatchetSharedKey(this.nativeKeyProvider, index);
    }

    public byte[] exportSharedKey(int index) {
        checkKeyProviderExists();
        return nativeExportSharedKey(this.nativeKeyProvider, index);
    }

    public boolean setKey(String participantId, int index, byte[] key) {
        checkKeyProviderExists();
        return nativeSetKey(this.nativeKeyProvider, participantId, index, key);
    }

    public byte[] ratchetKey(String participantId, int index) {
        checkKeyProviderExists();
        return nativeRatchetKey(this.nativeKeyProvider, participantId, index);
    }

    public byte[] exportKey(String participantId, int index) {
        checkKeyProviderExists();
        return nativeExportKey(this.nativeKeyProvider, participantId, index);
    }

    public void setSifTrailer(byte[] sifTrailer) {
        checkKeyProviderExists();
        nativeSetSifTrailer(this.nativeKeyProvider, sifTrailer);
    }

    public void dispose() {
        checkKeyProviderExists();
        JniCommon.nativeReleaseRef(this.nativeKeyProvider);
        this.nativeKeyProvider = 0L;
    }

    private void checkKeyProviderExists() {
        if (this.nativeKeyProvider == 0) {
            throw new IllegalStateException("FrameCryptorKeyProvider has been disposed.");
        }
    }
}
