package org.webrtc.audio;

import java.nio.ByteBuffer;
/* loaded from: classes5.dex */
public interface AudioRecordDataCallback {
    void onAudioDataRecorded(int i, int i2, int i3, ByteBuffer byteBuffer);
}
