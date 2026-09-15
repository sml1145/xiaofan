package org.webrtc;
/* loaded from: classes5.dex */
public interface ManagedAudioProcessingFactory extends AudioProcessingFactory {
    void destroyNative();

    boolean isEnabled();

    void setEnabled(boolean z);
}
