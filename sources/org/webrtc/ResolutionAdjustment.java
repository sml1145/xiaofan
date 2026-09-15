package org.webrtc;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
/* compiled from: ResolutionAdjustment.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lorg/webrtc/ResolutionAdjustment;", "", "value", "", "(Ljava/lang/String;II)V", "getValue", "()I", "NONE", "MULTIPLE_OF_2", "MULTIPLE_OF_4", "MULTIPLE_OF_8", "MULTIPLE_OF_16", "stream-webrtc-android_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public enum ResolutionAdjustment {
    NONE(1),
    MULTIPLE_OF_2(2),
    MULTIPLE_OF_4(4),
    MULTIPLE_OF_8(8),
    MULTIPLE_OF_16(16);
    
    private final int value;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    public static EnumEntries<ResolutionAdjustment> getEntries() {
        return $ENTRIES;
    }

    ResolutionAdjustment(int value) {
        this.value = value;
    }

    public final int getValue() {
        return this.value;
    }
}
