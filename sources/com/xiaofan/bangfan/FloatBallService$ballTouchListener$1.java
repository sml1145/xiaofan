package com.xiaofan.bangfan;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: FloatBallService.kt */
@Metadata(d1 = {"\u0000;\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"com/xiaofan/bangfan/FloatBallService$ballTouchListener$1", "Landroid/view/View$OnTouchListener;", "downAt", "", "downRawX", "", "downRawY", "downX", "", "downY", "gestureConsumed", "", "longPressTask", "Ljava/lang/Runnable;", "moved", "wasFadedAtDown", "onTouch", "v", "Landroid/view/View;", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class FloatBallService$ballTouchListener$1 implements View.OnTouchListener {
    private long downAt;
    private float downRawX;
    private float downRawY;
    private int downX;
    private int downY;
    private boolean gestureConsumed;
    private Runnable longPressTask;
    private boolean moved;
    final /* synthetic */ FloatBallService this$0;
    private boolean wasFadedAtDown;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FloatBallService$ballTouchListener$1(FloatBallService $receiver) {
        this.this$0 = $receiver;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View v, MotionEvent event) {
        WindowManager.LayoutParams layoutParams;
        WindowManager.LayoutParams layoutParams2;
        boolean z;
        Handler handler;
        boolean isNearEdge;
        WindowManager.LayoutParams layoutParams3;
        WindowManager.LayoutParams layoutParams4;
        int clampY;
        Handler handler2;
        Handler handler3;
        WindowManager.LayoutParams layoutParams5;
        int clampX;
        WindowManager.LayoutParams layoutParams6;
        int clampY2;
        Intrinsics.checkNotNullParameter(v, "v");
        Intrinsics.checkNotNullParameter(event, "event");
        switch (event.getActionMasked()) {
            case 0:
                this.downRawX = event.getRawX();
                this.downRawY = event.getRawY();
                layoutParams = this.this$0.lp;
                Intrinsics.checkNotNull(layoutParams);
                this.downX = layoutParams.x;
                layoutParams2 = this.this$0.lp;
                Intrinsics.checkNotNull(layoutParams2);
                this.downY = layoutParams2.y;
                this.downAt = System.currentTimeMillis();
                this.moved = false;
                this.gestureConsumed = false;
                z = this.this$0.isFaded;
                this.wasFadedAtDown = z;
                this.this$0.dragging = true;
                this.this$0.cancelAutoHide();
                this.this$0.restoreFromEdge();
                final FloatBallService floatBallService = this.this$0;
                this.longPressTask = new Runnable() { // from class: com.xiaofan.bangfan.FloatBallService$ballTouchListener$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        FloatBallService$ballTouchListener$1.onTouch$lambda$0(FloatBallService$ballTouchListener$1.this, floatBallService);
                    }
                };
                handler = this.this$0.main;
                Runnable runnable = this.longPressTask;
                Intrinsics.checkNotNull(runnable);
                handler.postDelayed(runnable, 620L);
                return true;
            case 1:
            case 3:
                this.this$0.dragging = false;
                Runnable it = this.longPressTask;
                if (it != null) {
                    handler2 = this.this$0.main;
                    handler2.removeCallbacks(it);
                }
                if (this.gestureConsumed) {
                    if (!VoiceControlService.Companion.isLiveListening()) {
                        this.this$0.scheduleAutoHide();
                    }
                    return true;
                }
                long elapsed = System.currentTimeMillis() - this.downAt;
                if (!this.moved && elapsed < 620) {
                    if (this.wasFadedAtDown) {
                        this.this$0.unfadeBall();
                        this.this$0.scheduleAutoHide();
                    } else {
                        this.this$0.onBallShortTap();
                    }
                } else if (this.moved) {
                    this.this$0.unfadeBall();
                    isNearEdge = this.this$0.isNearEdge();
                    if (isNearEdge) {
                        this.this$0.hideToEdge();
                    } else {
                        layoutParams3 = this.this$0.lp;
                        Intrinsics.checkNotNull(layoutParams3);
                        FloatBallService floatBallService2 = this.this$0;
                        layoutParams4 = this.this$0.lp;
                        Intrinsics.checkNotNull(layoutParams4);
                        clampY = floatBallService2.clampY(layoutParams4.y);
                        layoutParams3.y = clampY;
                        this.this$0.applyLp();
                    }
                    this.this$0.saveBallPosition();
                }
                if (!this.wasFadedAtDown || this.moved) {
                    this.this$0.scheduleAutoHide();
                }
                return true;
            case 2:
                if (this.gestureConsumed) {
                    return true;
                }
                float dx = event.getRawX() - this.downRawX;
                float dy = event.getRawY() - this.downRawY;
                if (Math.abs(dx) > this.this$0.dp(6.0f) || Math.abs(dy) > this.this$0.dp(6.0f)) {
                    this.moved = true;
                    Runnable it2 = this.longPressTask;
                    if (it2 != null) {
                        handler3 = this.this$0.main;
                        handler3.removeCallbacks(it2);
                    }
                    if (this.wasFadedAtDown) {
                        this.this$0.unfadeBall();
                    }
                }
                if (this.moved) {
                    layoutParams5 = this.this$0.lp;
                    Intrinsics.checkNotNull(layoutParams5);
                    clampX = this.this$0.clampX(this.downX + ((int) dx));
                    layoutParams5.x = clampX;
                    layoutParams6 = this.this$0.lp;
                    Intrinsics.checkNotNull(layoutParams6);
                    clampY2 = this.this$0.clampY(this.downY + ((int) dy));
                    layoutParams6.y = clampY2;
                    this.this$0.applyLp();
                }
                return true;
            default:
                return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTouch$lambda$0(FloatBallService$ballTouchListener$1 this$0, FloatBallService this$1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        if (!this$0.moved) {
            if (!AppPrefs.INSTANCE.voiceCmdOn(this$1)) {
                this$1.openMainActivity();
                return;
            }
            this$0.gestureConsumed = true;
            if (VoiceControlService.Companion.isLiveListening()) {
                VoiceControlService.Companion.stopLive(this$1);
                this$1.setListeningGlow(false);
                this$1.vibrate(true);
                this$1.showTip("已关闭聆听");
                return;
            }
            VoiceControlService.Companion.startLive(this$1);
            this$1.setListeningGlow(true);
            this$1.vibrate(true);
            this$1.showTip("小翻正在聆听，再长按关闭");
        }
    }
}
