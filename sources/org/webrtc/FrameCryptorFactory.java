package org.webrtc;
/* loaded from: classes5.dex */
public class FrameCryptorFactory {
    private static native FrameCryptor nativeCreateFrameCryptorForRtpReceiver(long j, long j2, String str, int i, long j3);

    private static native FrameCryptor nativeCreateFrameCryptorForRtpSender(long j, long j2, String str, int i, long j3);

    private static native FrameCryptorKeyProvider nativeCreateFrameCryptorKeyProvider(boolean z, byte[] bArr, int i, byte[] bArr2, int i2, int i3, boolean z2);

    public static FrameCryptorKeyProvider createFrameCryptorKeyProvider(boolean sharedKey, byte[] ratchetSalt, int ratchetWindowSize, byte[] uncryptedMagicBytes, int failureTolerance, int keyRingSize, boolean discardFrameWhenCryptorNotReady) {
        return nativeCreateFrameCryptorKeyProvider(sharedKey, ratchetSalt, ratchetWindowSize, uncryptedMagicBytes, failureTolerance, keyRingSize, discardFrameWhenCryptorNotReady);
    }

    public static FrameCryptor createFrameCryptorForRtpSender(PeerConnectionFactory factory, RtpSender rtpSender, String participantId, FrameCryptorAlgorithm algorithm, FrameCryptorKeyProvider keyProvider) {
        return nativeCreateFrameCryptorForRtpSender(factory.getNativeOwnedFactoryAndThreads(), rtpSender.getNativeRtpSender(), participantId, algorithm.ordinal(), keyProvider.getNativeKeyProvider());
    }

    public static FrameCryptor createFrameCryptorForRtpReceiver(PeerConnectionFactory factory, RtpReceiver rtpReceiver, String participantId, FrameCryptorAlgorithm algorithm, FrameCryptorKeyProvider keyProvider) {
        return nativeCreateFrameCryptorForRtpReceiver(factory.getNativeOwnedFactoryAndThreads(), rtpReceiver.getNativeRtpReceiver(), participantId, algorithm.ordinal(), keyProvider.getNativeKeyProvider());
    }
}
