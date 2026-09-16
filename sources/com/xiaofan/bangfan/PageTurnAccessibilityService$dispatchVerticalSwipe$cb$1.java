package com.xiaofan.bangfan;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.os.Handler;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: PageTurnAccessibilityService.kt */
@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\u0007"}, d2 = {"com/xiaofan/bangfan/PageTurnAccessibilityService$dispatchVerticalSwipe$cb$1", "Landroid/accessibilityservice/AccessibilityService$GestureResultCallback;", "onCancelled", "", "g", "Landroid/accessibilityservice/GestureDescription;", "onCompleted", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class PageTurnAccessibilityService$dispatchVerticalSwipe$cb$1 extends AccessibilityService.GestureResultCallback {
    final /* synthetic */ String $reason;
    final /* synthetic */ boolean $retry;
    final /* synthetic */ boolean $up;
    final /* synthetic */ PageTurnAccessibilityService this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PageTurnAccessibilityService$dispatchVerticalSwipe$cb$1(boolean $up, String $reason, boolean $retry, PageTurnAccessibilityService $receiver) {
        this.$up = $up;
        this.$reason = $reason;
        this.$retry = $retry;
        this.this$0 = $receiver;
    }

    @Override // android.accessibilityservice.AccessibilityService.GestureResultCallback
    public void onCompleted(GestureDescription g) {
        boolean z = this.$up;
        Log.i("PageTurnA11y", "vertical swipe up=" + z + " reason=" + this.$reason + " completed");
    }

    @Override // android.accessibilityservice.AccessibilityService.GestureResultCallback
    public void onCancelled(GestureDescription g) {
        Handler handler;
        boolean z = this.$up;
        Log.w("PageTurnA11y", "vertical swipe up=" + z + " reason=" + this.$reason + " cancelled");
        if (this.$retry) {
            handler = this.this$0.main;
            final PageTurnAccessibilityService pageTurnAccessibilityService = this.this$0;
            final boolean z2 = this.$up;
            final String str = this.$reason;
            handler.postDelayed(new Runnable() { // from class: com.xiaofan.bangfan.PageTurnAccessibilityService$dispatchVerticalSwipe$cb$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    PageTurnAccessibilityService$dispatchVerticalSwipe$cb$1.onCancelled$lambda$0(PageTurnAccessibilityService.this, z2, str);
                }
            }, 90L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCancelled$lambda$0(PageTurnAccessibilityService this$0, boolean $up, String reason) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(reason, "$reason");
        this$0.dispatchVerticalSwipe($up, reason, false);
    }
}
