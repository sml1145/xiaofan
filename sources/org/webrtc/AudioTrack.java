package org.webrtc;

import java.util.IdentityHashMap;
/* loaded from: classes5.dex */
public class AudioTrack extends MediaStreamTrack {
    private final IdentityHashMap<AudioTrackSink, Long> sinks;

    private static native void nativeAddSink(long j, long j2);

    private static native void nativeFreeSink(long j);

    private static native void nativeRemoveSink(long j, long j2);

    private static native void nativeSetVolume(long j, double d);

    private static native long nativeWrapSink(AudioTrackSink audioTrackSink);

    public AudioTrack(long nativeTrack) {
        super(nativeTrack);
        this.sinks = new IdentityHashMap<>();
    }

    public void setVolume(double volume) {
        nativeSetVolume(getNativeAudioTrack(), volume);
    }

    public void addSink(AudioTrackSink sink) {
        if (sink == null) {
            throw new IllegalArgumentException("The AudioTrackSink is not allowed to be null");
        }
        if (!this.sinks.containsKey(sink)) {
            long nativeSink = nativeWrapSink(sink);
            this.sinks.put(sink, Long.valueOf(nativeSink));
            nativeAddSink(getNativeMediaStreamTrack(), nativeSink);
        }
    }

    public void removeSink(AudioTrackSink sink) {
        Long nativeSink = this.sinks.remove(sink);
        if (nativeSink != null) {
            nativeRemoveSink(getNativeMediaStreamTrack(), nativeSink.longValue());
            nativeFreeSink(nativeSink.longValue());
        }
    }

    @Override // org.webrtc.MediaStreamTrack
    public void dispose() {
        for (Long l : this.sinks.values()) {
            long nativeSink = l.longValue();
            nativeRemoveSink(getNativeMediaStreamTrack(), nativeSink);
            nativeFreeSink(nativeSink);
        }
        this.sinks.clear();
        super.dispose();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getNativeAudioTrack() {
        return getNativeMediaStreamTrack();
    }
}
