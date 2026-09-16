package com.xiaofan.bangfan;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
/* compiled from: VolumeKeyGate.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\rB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n¨\u0006\u000e"}, d2 = {"Lcom/xiaofan/bangfan/VolumeKeyGate;", "", "()V", "decide", "Lcom/xiaofan/bangfan/VolumeKeyGate$Decision;", "btConnected", "", "volumeKeyCtrlEnabled", "autoTurnRunning", "volumeChangeDirection", "", "current", "baseline", "Decision", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class VolumeKeyGate {
    public static final VolumeKeyGate INSTANCE = new VolumeKeyGate();

    /* compiled from: VolumeKeyGate.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/xiaofan/bangfan/VolumeKeyGate$Decision;", "", "(Ljava/lang/String;I)V", "PASS_TO_SYSTEM", "CONSUME_LOCAL_TURN", "CONSUME_BT_REMOTE", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public enum Decision {
        PASS_TO_SYSTEM,
        CONSUME_LOCAL_TURN,
        CONSUME_BT_REMOTE;
        
        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<Decision> getEntries() {
            return $ENTRIES;
        }
    }

    private VolumeKeyGate() {
    }

    public final Decision decide(boolean btConnected, boolean volumeKeyCtrlEnabled, boolean autoTurnRunning) {
        return btConnected ? Decision.CONSUME_BT_REMOTE : (volumeKeyCtrlEnabled && autoTurnRunning) ? Decision.CONSUME_LOCAL_TURN : Decision.PASS_TO_SYSTEM;
    }

    public final int volumeChangeDirection(int current, int baseline) {
        if (baseline < 0) {
            return 0;
        }
        if (current > baseline) {
            return 1;
        }
        return current < baseline ? -1 : 0;
    }
}
