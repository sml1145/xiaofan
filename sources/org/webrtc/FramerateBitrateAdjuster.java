package org.webrtc;
/* loaded from: classes5.dex */
class FramerateBitrateAdjuster extends BaseBitrateAdjuster {
    private static final int DEFAULT_FRAMERATE_FPS = 30;

    @Override // org.webrtc.BaseBitrateAdjuster, org.webrtc.BitrateAdjuster
    public void setTargets(int targetBitrateBps, double targetFramerateFps) {
        this.targetFramerateFps = 30.0d;
        this.targetBitrateBps = (int) ((targetBitrateBps * 30) / targetFramerateFps);
    }
}
