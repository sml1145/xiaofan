package org.webrtc.audio;
/* loaded from: classes5.dex */
public interface AudioDeviceModule {
    long getNativeAudioDeviceModulePointer();

    void release();

    void setMicrophoneMute(boolean z);

    void setSpeakerMute(boolean z);

    default boolean setNoiseSuppressorEnabled(boolean enabled) {
        return false;
    }

    default boolean setPreferredMicrophoneFieldDimension(float dimension) {
        return false;
    }
}
