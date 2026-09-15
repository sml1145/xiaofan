package org.webrtc.audio;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Build;
import android.os.Process;
import java.nio.ByteBuffer;
import org.webrtc.Logging;
import org.webrtc.ThreadUtils;
import org.webrtc.audio.JavaAudioDeviceModule;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public class WebRtcAudioTrack {
    private static final int AUDIO_TRACK_START = 0;
    private static final int AUDIO_TRACK_STOP = 1;
    private static final long AUDIO_TRACK_THREAD_JOIN_TIMEOUT_MS = 2000;
    private static final int BITS_PER_SAMPLE = 16;
    private static final int BUFFERS_PER_SECOND = 100;
    private static final int CALLBACK_BUFFER_SIZE_MS = 10;
    private static final int DEFAULT_USAGE = 2;
    private static final String TAG = "WebRtcAudioTrackExternal";
    private final AudioAttributes audioAttributes;
    private final AudioManager audioManager;
    private AudioTrackThread audioThread;
    private AudioTrack audioTrack;
    private ByteBuffer byteBuffer;
    private final Context context;
    private byte[] emptyBytes;
    private final JavaAudioDeviceModule.AudioTrackErrorCallback errorCallback;
    private int initialBufferSizeInFrames;
    private long nativeAudioTrack;
    private volatile boolean speakerMute;
    private final JavaAudioDeviceModule.AudioTrackStateCallback stateCallback;
    private final ThreadUtils.ThreadChecker threadChecker;
    private boolean useLowLatency;
    private final VolumeLogger volumeLogger;

    private static native void nativeCacheDirectBufferAddress(long j, ByteBuffer byteBuffer);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeGetPlayoutData(long j, int i);

    /* loaded from: classes5.dex */
    private class AudioTrackThread extends Thread {
        private LowLatencyAudioBufferManager bufferManager;
        private volatile boolean keepAlive;

        public AudioTrackThread(String name) {
            super(name);
            this.keepAlive = true;
            this.bufferManager = new LowLatencyAudioBufferManager();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Process.setThreadPriority(-19);
            Logging.d(WebRtcAudioTrack.TAG, "AudioTrackThread" + WebRtcAudioUtils.getThreadInfo());
            WebRtcAudioTrack.assertTrue(WebRtcAudioTrack.this.audioTrack.getPlayState() == 3);
            WebRtcAudioTrack.this.doAudioTrackStateCallback(0);
            int sizeInBytes = WebRtcAudioTrack.this.byteBuffer.capacity();
            while (this.keepAlive) {
                WebRtcAudioTrack.nativeGetPlayoutData(WebRtcAudioTrack.this.nativeAudioTrack, sizeInBytes);
                WebRtcAudioTrack.assertTrue(sizeInBytes <= WebRtcAudioTrack.this.byteBuffer.remaining());
                if (WebRtcAudioTrack.this.speakerMute) {
                    WebRtcAudioTrack.this.byteBuffer.clear();
                    WebRtcAudioTrack.this.byteBuffer.put(WebRtcAudioTrack.this.emptyBytes);
                    WebRtcAudioTrack.this.byteBuffer.position(0);
                }
                int bytesWritten = WebRtcAudioTrack.this.audioTrack.write(WebRtcAudioTrack.this.byteBuffer, sizeInBytes, 0);
                if (bytesWritten != sizeInBytes) {
                    Logging.e(WebRtcAudioTrack.TAG, "AudioTrack.write played invalid number of bytes: " + bytesWritten);
                    if (bytesWritten < 0) {
                        this.keepAlive = false;
                        WebRtcAudioTrack.this.reportWebRtcAudioTrackError("AudioTrack.write failed: " + bytesWritten);
                    }
                }
                if (WebRtcAudioTrack.this.useLowLatency) {
                    this.bufferManager.maybeAdjustBufferSize(WebRtcAudioTrack.this.audioTrack);
                }
                WebRtcAudioTrack.this.byteBuffer.rewind();
            }
        }

        public void stopThread() {
            Logging.d(WebRtcAudioTrack.TAG, "stopThread");
            this.keepAlive = false;
        }
    }

    WebRtcAudioTrack(Context context, AudioManager audioManager) {
        this(context, audioManager, null, null, null, false, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WebRtcAudioTrack(Context context, AudioManager audioManager, AudioAttributes audioAttributes, JavaAudioDeviceModule.AudioTrackErrorCallback errorCallback, JavaAudioDeviceModule.AudioTrackStateCallback stateCallback, boolean useLowLatency, boolean enableVolumeLogger) {
        this.threadChecker = new ThreadUtils.ThreadChecker();
        this.threadChecker.detachThread();
        this.context = context;
        this.audioManager = audioManager;
        this.audioAttributes = audioAttributes;
        this.errorCallback = errorCallback;
        this.stateCallback = stateCallback;
        this.volumeLogger = enableVolumeLogger ? new VolumeLogger(audioManager) : null;
        this.useLowLatency = useLowLatency;
        Logging.d(TAG, "ctor" + WebRtcAudioUtils.getThreadInfo());
    }

    public void setNativeAudioTrack(long nativeAudioTrack) {
        this.nativeAudioTrack = nativeAudioTrack;
    }

    private int initPlayout(int sampleRate, int channels, double bufferSizeFactor) {
        this.threadChecker.checkIsOnValidThread();
        Logging.d(TAG, "initPlayout(sampleRate=" + sampleRate + ", channels=" + channels + ", bufferSizeFactor=" + bufferSizeFactor + ")");
        int bytesPerFrame = channels * 2;
        this.byteBuffer = ByteBuffer.allocateDirect((sampleRate / 100) * bytesPerFrame);
        Logging.d(TAG, "byteBuffer.capacity: " + this.byteBuffer.capacity());
        this.emptyBytes = new byte[this.byteBuffer.capacity()];
        nativeCacheDirectBufferAddress(this.nativeAudioTrack, this.byteBuffer);
        int channelConfig = channelCountToConfiguration(channels);
        int minBufferSizeInBytes = (int) (AudioTrack.getMinBufferSize(sampleRate, channelConfig, 2) * bufferSizeFactor);
        Logging.d(TAG, "minBufferSizeInBytes: " + minBufferSizeInBytes);
        if (minBufferSizeInBytes < this.byteBuffer.capacity()) {
            reportWebRtcAudioTrackInitError("AudioTrack.getMinBufferSize returns an invalid value.");
            return -1;
        }
        if (bufferSizeFactor > 1.0d) {
            this.useLowLatency = false;
        }
        if (this.audioTrack != null) {
            reportWebRtcAudioTrackInitError("Conflict with existing AudioTrack.");
            return -1;
        }
        try {
            if (this.useLowLatency && Build.VERSION.SDK_INT >= 26) {
                this.audioTrack = createAudioTrackOnOreoOrHigher(sampleRate, channelConfig, minBufferSizeInBytes, this.audioAttributes);
            } else {
                this.audioTrack = createAudioTrackBeforeOreo(sampleRate, channelConfig, minBufferSizeInBytes, this.audioAttributes);
            }
            if (this.audioTrack == null || this.audioTrack.getState() != 1) {
                reportWebRtcAudioTrackInitError("Initialization of audio track failed.");
                releaseAudioResources();
                return -1;
            }
            this.initialBufferSizeInFrames = this.audioTrack.getBufferSizeInFrames();
            logMainParameters();
            logMainParametersExtended();
            return minBufferSizeInBytes;
        } catch (IllegalArgumentException e) {
            reportWebRtcAudioTrackInitError(e.getMessage());
            releaseAudioResources();
            return -1;
        }
    }

    private boolean startPlayout() {
        this.threadChecker.checkIsOnValidThread();
        if (this.volumeLogger != null) {
            this.volumeLogger.start();
        }
        Logging.d(TAG, "startPlayout");
        assertTrue(this.audioTrack != null);
        assertTrue(this.audioThread == null);
        try {
            this.audioTrack.play();
            if (this.audioTrack.getPlayState() != 3) {
                reportWebRtcAudioTrackStartError(JavaAudioDeviceModule.AudioTrackStartErrorCode.AUDIO_TRACK_START_STATE_MISMATCH, "AudioTrack.play failed - incorrect state :" + this.audioTrack.getPlayState());
                releaseAudioResources();
                return false;
            }
            this.audioThread = new AudioTrackThread("AudioTrackJavaThread");
            this.audioThread.start();
            return true;
        } catch (IllegalStateException e) {
            reportWebRtcAudioTrackStartError(JavaAudioDeviceModule.AudioTrackStartErrorCode.AUDIO_TRACK_START_EXCEPTION, "AudioTrack.play failed: " + e.getMessage());
            releaseAudioResources();
            return false;
        }
    }

    private boolean stopPlayout() {
        this.threadChecker.checkIsOnValidThread();
        if (this.volumeLogger != null) {
            this.volumeLogger.stop();
        }
        Logging.d(TAG, "stopPlayout");
        assertTrue(this.audioThread != null);
        logUnderrunCount();
        this.audioThread.stopThread();
        Logging.d(TAG, "Stopping the AudioTrackThread...");
        this.audioThread.interrupt();
        if (!ThreadUtils.joinUninterruptibly(this.audioThread, AUDIO_TRACK_THREAD_JOIN_TIMEOUT_MS)) {
            Logging.e(TAG, "Join of AudioTrackThread timed out.");
            WebRtcAudioUtils.logAudioState(TAG, this.context, this.audioManager);
        }
        Logging.d(TAG, "AudioTrackThread has now been stopped.");
        this.audioThread = null;
        if (this.audioTrack != null) {
            Logging.d(TAG, "Calling AudioTrack.stop...");
            try {
                this.audioTrack.stop();
                Logging.d(TAG, "AudioTrack.stop is done.");
                doAudioTrackStateCallback(1);
            } catch (IllegalStateException e) {
                Logging.e(TAG, "AudioTrack.stop failed: " + e.getMessage());
            }
        }
        releaseAudioResources();
        return true;
    }

    private int getStreamMaxVolume() {
        this.threadChecker.checkIsOnValidThread();
        Logging.d(TAG, "getStreamMaxVolume");
        return this.audioManager.getStreamMaxVolume(0);
    }

    private boolean setStreamVolume(int volume) {
        this.threadChecker.checkIsOnValidThread();
        Logging.d(TAG, "setStreamVolume(" + volume + ")");
        if (this.audioManager.isVolumeFixed()) {
            Logging.e(TAG, "The device implements a fixed volume policy.");
            return false;
        }
        this.audioManager.setStreamVolume(0, volume, 0);
        return true;
    }

    private int getStreamVolume() {
        this.threadChecker.checkIsOnValidThread();
        Logging.d(TAG, "getStreamVolume");
        return this.audioManager.getStreamVolume(0);
    }

    private int GetPlayoutUnderrunCount() {
        if (this.audioTrack != null) {
            return this.audioTrack.getUnderrunCount();
        }
        return -1;
    }

    private void logMainParameters() {
        Logging.d(TAG, "AudioTrack: session ID: " + this.audioTrack.getAudioSessionId() + ", channels: " + this.audioTrack.getChannelCount() + ", sample rate: " + this.audioTrack.getSampleRate() + ", max gain: " + AudioTrack.getMaxVolume());
    }

    private static void logNativeOutputSampleRate(int requestedSampleRateInHz) {
        int nativeOutputSampleRate = AudioTrack.getNativeOutputSampleRate(0);
        Logging.d(TAG, "nativeOutputSampleRate: " + nativeOutputSampleRate);
        if (requestedSampleRateInHz != nativeOutputSampleRate) {
            Logging.w(TAG, "Unable to use fast mode since requested sample rate is not native");
        }
    }

    private static AudioAttributes getAudioAttributes(AudioAttributes overrideAttributes) {
        AudioAttributes.Builder attributesBuilder = new AudioAttributes.Builder().setUsage(2).setContentType(1);
        if (overrideAttributes != null) {
            if (overrideAttributes.getUsage() != 0) {
                attributesBuilder.setUsage(overrideAttributes.getUsage());
            }
            if (overrideAttributes.getContentType() != 0) {
                attributesBuilder.setContentType(overrideAttributes.getContentType());
            }
            attributesBuilder.setFlags(overrideAttributes.getFlags());
            if (Build.VERSION.SDK_INT >= 29) {
                attributesBuilder = applyAttributesOnQOrHigher(attributesBuilder, overrideAttributes);
            }
        }
        return attributesBuilder.build();
    }

    private static AudioTrack createAudioTrackBeforeOreo(int sampleRateInHz, int channelConfig, int bufferSizeInBytes, AudioAttributes overrideAttributes) {
        Logging.d(TAG, "createAudioTrackBeforeOreo");
        logNativeOutputSampleRate(sampleRateInHz);
        return new AudioTrack(getAudioAttributes(overrideAttributes), new AudioFormat.Builder().setEncoding(2).setSampleRate(sampleRateInHz).setChannelMask(channelConfig).build(), bufferSizeInBytes, 1, 0);
    }

    private static AudioTrack createAudioTrackOnOreoOrHigher(int sampleRateInHz, int channelConfig, int bufferSizeInBytes, AudioAttributes overrideAttributes) {
        Logging.d(TAG, "createAudioTrackOnOreoOrHigher");
        logNativeOutputSampleRate(sampleRateInHz);
        return new AudioTrack.Builder().setAudioAttributes(getAudioAttributes(overrideAttributes)).setAudioFormat(new AudioFormat.Builder().setEncoding(2).setSampleRate(sampleRateInHz).setChannelMask(channelConfig).build()).setBufferSizeInBytes(bufferSizeInBytes).setPerformanceMode(1).setTransferMode(1).setSessionId(0).build();
    }

    private static AudioAttributes.Builder applyAttributesOnQOrHigher(AudioAttributes.Builder builder, AudioAttributes overrideAttributes) {
        return builder.setAllowedCapturePolicy(overrideAttributes.getAllowedCapturePolicy());
    }

    private void logBufferSizeInFrames() {
        Logging.d(TAG, "AudioTrack: buffer size in frames: " + this.audioTrack.getBufferSizeInFrames());
    }

    private int getBufferSizeInFrames() {
        return this.audioTrack.getBufferSizeInFrames();
    }

    private int getInitialBufferSizeInFrames() {
        return this.initialBufferSizeInFrames;
    }

    private void logBufferCapacityInFrames() {
        Logging.d(TAG, "AudioTrack: buffer capacity in frames: " + this.audioTrack.getBufferCapacityInFrames());
    }

    private void logMainParametersExtended() {
        logBufferSizeInFrames();
        logBufferCapacityInFrames();
    }

    private void logUnderrunCount() {
        Logging.d(TAG, "underrun count: " + this.audioTrack.getUnderrunCount());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void assertTrue(boolean condition) {
        if (!condition) {
            throw new AssertionError("Expected condition to be true");
        }
    }

    private int channelCountToConfiguration(int channels) {
        return channels == 1 ? 4 : 12;
    }

    public void setSpeakerMute(boolean mute) {
        Logging.w(TAG, "setSpeakerMute(" + mute + ")");
        this.speakerMute = mute;
    }

    private void releaseAudioResources() {
        Logging.d(TAG, "releaseAudioResources");
        if (this.audioTrack != null) {
            this.audioTrack.release();
            this.audioTrack = null;
        }
    }

    private void reportWebRtcAudioTrackInitError(String errorMessage) {
        Logging.e(TAG, "Init playout error: " + errorMessage);
        WebRtcAudioUtils.logAudioState(TAG, this.context, this.audioManager);
        if (this.errorCallback != null) {
            this.errorCallback.onWebRtcAudioTrackInitError(errorMessage);
        }
    }

    private void reportWebRtcAudioTrackStartError(JavaAudioDeviceModule.AudioTrackStartErrorCode errorCode, String errorMessage) {
        Logging.e(TAG, "Start playout error: " + errorCode + ". " + errorMessage);
        WebRtcAudioUtils.logAudioState(TAG, this.context, this.audioManager);
        if (this.errorCallback != null) {
            this.errorCallback.onWebRtcAudioTrackStartError(errorCode, errorMessage);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportWebRtcAudioTrackError(String errorMessage) {
        Logging.e(TAG, "Run-time playback error: " + errorMessage);
        WebRtcAudioUtils.logAudioState(TAG, this.context, this.audioManager);
        if (this.errorCallback != null) {
            this.errorCallback.onWebRtcAudioTrackError(errorMessage);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doAudioTrackStateCallback(int audioState) {
        Logging.d(TAG, "doAudioTrackStateCallback: " + audioState);
        if (this.stateCallback != null) {
            if (audioState == 0) {
                this.stateCallback.onWebRtcAudioTrackStart();
            } else if (audioState == 1) {
                this.stateCallback.onWebRtcAudioTrackStop();
            } else {
                Logging.e(TAG, "Invalid audio state");
            }
        }
    }
}
