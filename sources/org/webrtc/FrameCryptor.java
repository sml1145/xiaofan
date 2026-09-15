package org.webrtc;
/* loaded from: classes5.dex */
public class FrameCryptor {
    private long nativeFrameCryptor;
    private long observerPtr = 0;

    /* loaded from: classes5.dex */
    public interface Observer {
        void onFrameCryptionStateChanged(String str, FrameCryptionState frameCryptionState);
    }

    private static native int nativeGetKeyIndex(long j);

    private static native boolean nativeIsEnabled(long j);

    private static native void nativeSetEnabled(long j, boolean z);

    private static native void nativeSetKeyIndex(long j, int i);

    private static native long nativeSetObserver(long j, Observer observer);

    private static native void nativeUnSetObserver(long j);

    /* loaded from: classes5.dex */
    public enum FrameCryptionState {
        NEW,
        OK,
        ENCRYPTIONFAILED,
        DECRYPTIONFAILED,
        MISSINGKEY,
        KEYRATCHETED,
        INTERNALERROR;

        static FrameCryptionState fromNativeIndex(int nativeIndex) {
            return values()[nativeIndex];
        }
    }

    public long getNativeFrameCryptor() {
        return this.nativeFrameCryptor;
    }

    public FrameCryptor(long nativeFrameCryptor) {
        this.nativeFrameCryptor = nativeFrameCryptor;
    }

    public void setEnabled(boolean enabled) {
        checkFrameCryptorExists();
        nativeSetEnabled(this.nativeFrameCryptor, enabled);
    }

    public boolean isEnabled() {
        checkFrameCryptorExists();
        return nativeIsEnabled(this.nativeFrameCryptor);
    }

    public int getKeyIndex() {
        checkFrameCryptorExists();
        return nativeGetKeyIndex(this.nativeFrameCryptor);
    }

    public void setKeyIndex(int index) {
        checkFrameCryptorExists();
        nativeSetKeyIndex(this.nativeFrameCryptor, index);
    }

    public void dispose() {
        checkFrameCryptorExists();
        nativeUnSetObserver(this.nativeFrameCryptor);
        JniCommon.nativeReleaseRef(this.nativeFrameCryptor);
        this.nativeFrameCryptor = 0L;
        if (this.observerPtr != 0) {
            JniCommon.nativeReleaseRef(this.observerPtr);
            this.observerPtr = 0L;
        }
    }

    public void setObserver(Observer observer) {
        checkFrameCryptorExists();
        nativeSetObserver(this.nativeFrameCryptor, observer);
        if (this.observerPtr != 0) {
            JniCommon.nativeReleaseRef(this.observerPtr);
            this.observerPtr = 0L;
        }
        long newPtr = this.observerPtr;
    }

    private void checkFrameCryptorExists() {
        if (this.nativeFrameCryptor == 0) {
            throw new IllegalStateException("FrameCryptor has been disposed.");
        }
    }
}
