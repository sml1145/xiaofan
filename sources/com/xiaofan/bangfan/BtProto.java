package com.xiaofan.bangfan;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
/* compiled from: BtProto.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0006\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0004J\u001c\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/xiaofan/bangfan/BtProto;", "", "()V", "CMD_NEXT", "", "CMD_PING", "CMD_PONG", "CMD_PREV", "CMD_SWIPE_DOWN", "CMD_SWIPE_UP", "known", "", "isTurnCommand", "", "b", "parse", "", "bytes", "", "len", "", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class BtProto {
    public static final byte CMD_NEXT = 1;
    public static final byte CMD_PREV = 2;
    public static final byte CMD_SWIPE_DOWN = 4;
    public static final byte CMD_SWIPE_UP = 3;
    public static final BtProto INSTANCE = new BtProto();
    public static final byte CMD_PING = 16;
    public static final byte CMD_PONG = 17;
    private static final Set<Byte> known = SetsKt.setOf((Object[]) new Byte[]{(byte) 1, (byte) 2, (byte) 3, (byte) 4, Byte.valueOf((byte) CMD_PING), Byte.valueOf((byte) CMD_PONG)});

    private BtProto() {
    }

    public final List<Byte> parse(byte[] bytes, int len) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        ArrayList out = new ArrayList(2);
        int n = RangesKt.coerceIn(len, 0, bytes.length);
        for (int i = 0; i < n; i++) {
            byte b = bytes[i];
            if (known.contains(Byte.valueOf(b))) {
                out.add(Byte.valueOf(b));
            }
        }
        return out;
    }

    public final boolean isTurnCommand(byte b) {
        return b == 1 || b == 2 || b == 3 || b == 4;
    }
}
