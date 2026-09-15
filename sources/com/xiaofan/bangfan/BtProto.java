package com.xiaofan.bangfan;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
/* compiled from: BtProto.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0004J\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/xiaofan/bangfan/BtProto;", "", "()V", "CMD_NEXT", "", "CMD_PING", "CMD_PONG", "CMD_PREV", "isTurnCommand", "", "b", "parse", "", "bytes", "", "len", "", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class BtProto {
    public static final byte CMD_NEXT = 1;
    public static final byte CMD_PING = 16;
    public static final byte CMD_PONG = 17;
    public static final byte CMD_PREV = 2;
    public static final BtProto INSTANCE = new BtProto();

    private BtProto() {
    }

    public final List<Byte> parse(byte[] bytes, int len) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        ArrayList out = new ArrayList(2);
        int n = RangesKt.coerceIn(len, 0, bytes.length);
        for (int i = 0; i < n; i++) {
            byte b = bytes[i];
            if (b == 1 || b == 2 || b == 16 || b == 17) {
                out.add(Byte.valueOf(b));
            }
        }
        return out;
    }

    public final boolean isTurnCommand(byte b) {
        return b == 1 || b == 2;
    }
}
