package org.webrtc;

import java.nio.ByteBuffer;
/* loaded from: classes5.dex */
public class ExternalAudioProcessingFactory implements AudioProcessingFactory {
    private long apmPtr = nativeGetDefaultApm();
    private long capturePostProcessingPtr = 0;
    private long renderPreProcessingPtr = 0;

    /* loaded from: classes5.dex */
    public interface AudioProcessing {
        void initialize(int i, int i2);

        void process(int i, int i2, ByteBuffer byteBuffer);

        void reset(int i);
    }

    private static native void nativeDestroy();

    private static native long nativeGetDefaultApm();

    private static native void nativeSetBypassFlagForCapturePost(boolean z);

    private static native void nativeSetBypassFlagForRenderPre(boolean z);

    private static native long nativeSetCapturePostProcessing(AudioProcessing audioProcessing);

    private static native long nativeSetRenderPreProcessing(AudioProcessing audioProcessing);

    @Override // org.webrtc.AudioProcessingFactory
    public long createNative() {
        if (this.apmPtr == 0) {
            this.apmPtr = nativeGetDefaultApm();
        }
        return this.apmPtr;
    }

    public void setCapturePostProcessing(AudioProcessing processing) {
        checkExternalAudioProcessorExists();
        long newPtr = nativeSetCapturePostProcessing(processing);
        if (this.capturePostProcessingPtr != 0) {
            JniCommon.nativeReleaseRef(this.capturePostProcessingPtr);
            this.capturePostProcessingPtr = 0L;
        }
        this.capturePostProcessingPtr = newPtr;
    }

    public void setRenderPreProcessing(AudioProcessing processing) {
        checkExternalAudioProcessorExists();
        long newPtr = nativeSetRenderPreProcessing(processing);
        if (this.renderPreProcessingPtr != 0) {
            JniCommon.nativeReleaseRef(this.renderPreProcessingPtr);
            this.renderPreProcessingPtr = 0L;
        }
        this.renderPreProcessingPtr = newPtr;
    }

    public void setBypassFlagForCapturePost(boolean bypass) {
        checkExternalAudioProcessorExists();
        nativeSetBypassFlagForCapturePost(bypass);
    }

    public void setBypassFlagForRenderPre(boolean bypass) {
        checkExternalAudioProcessorExists();
        nativeSetBypassFlagForRenderPre(bypass);
    }

    public void destroy() {
        checkExternalAudioProcessorExists();
        if (this.renderPreProcessingPtr != 0) {
            JniCommon.nativeReleaseRef(this.renderPreProcessingPtr);
            this.renderPreProcessingPtr = 0L;
        }
        if (this.capturePostProcessingPtr != 0) {
            JniCommon.nativeReleaseRef(this.capturePostProcessingPtr);
            this.capturePostProcessingPtr = 0L;
        }
        nativeDestroy();
        this.apmPtr = 0L;
    }

    private void checkExternalAudioProcessorExists() {
        if (this.apmPtr == 0) {
            throw new IllegalStateException("ExternalAudioProcessor has been disposed.");
        }
    }
}
