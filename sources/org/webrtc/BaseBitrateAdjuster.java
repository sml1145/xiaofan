package org.webrtc;
/* loaded from: classes5.dex */
class BaseBitrateAdjuster implements BitrateAdjuster {
    protected int targetBitrateBps;
    protected double targetFramerateFps;

    @Override // org.webrtc.BitrateAdjuster
    public void setTargets(int targetBitrateBps, double targetFramerateFps) {
        this.targetBitrateBps = targetBitrateBps;
        this.targetFramerateFps = targetFramerateFps;
    }

    @Override // org.webrtc.BitrateAdjuster
    public void reportEncodedFrame(int size) {
    }

    @Override // org.webrtc.BitrateAdjuster
    public int getAdjustedBitrateBps() {
        return this.targetBitrateBps;
    }

    @Override // org.webrtc.BitrateAdjuster
    public double getAdjustedFramerateFps() {
        return this.targetFramerateFps;
    }
}
