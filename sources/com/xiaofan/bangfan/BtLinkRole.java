package com.xiaofan.bangfan;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
/* compiled from: BtLinkRole.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0004¨\u0006\t"}, d2 = {"Lcom/xiaofan/bangfan/BtLinkRole;", "", "()V", "keep", "", "myId", "", "peerId", "outbound", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class BtLinkRole {
    public static final BtLinkRole INSTANCE = new BtLinkRole();

    private BtLinkRole() {
    }

    public final boolean keep(long myId, long peerId, boolean outbound) {
        int i = (myId > peerId ? 1 : (myId == peerId ? 0 : -1));
        if (outbound) {
            if (i >= 0) {
                return true;
            }
        } else if (i < 0) {
            return true;
        }
        return false;
    }
}
