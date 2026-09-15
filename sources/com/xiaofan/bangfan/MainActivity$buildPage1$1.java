package com.xiaofan.bangfan;

import android.content.Intent;
import android.os.Handler;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.xiaofan.bangfan.MascotView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: MainActivity.kt */
@Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016¨\u0006\u0005"}, d2 = {"com/xiaofan/bangfan/MainActivity$buildPage1$1", "Lcom/xiaofan/bangfan/MascotView$MascotListener;", "onMascotLongPressed", "", "onMascotTapped", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class MainActivity$buildPage1$1 implements MascotView.MascotListener {
    final /* synthetic */ MainActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MainActivity$buildPage1$1(MainActivity $receiver) {
        this.this$0 = $receiver;
    }

    @Override // com.xiaofan.bangfan.MascotView.MascotListener
    public void onMascotTapped() {
        MascotView mascotView;
        MascotView mascotView2;
        String msg;
        Handler handler;
        ClickFx.INSTANCE.play(this.this$0);
        mascotView = this.this$0.mascotView;
        if (mascotView != null) {
            mascotView.flashChest();
        }
        mascotView2 = this.this$0.mascotView;
        if (mascotView2 != null) {
            mascotView2.setSpeaking(true);
        }
        msg = this.this$0.pickGreeting();
        XiaoFanVoice.INSTANCE.greet(this.this$0, msg);
        this.this$0.showSpeechBubble(msg);
        handler = this.this$0.main;
        final MainActivity mainActivity = this.this$0;
        handler.postDelayed(new Runnable() { // from class: com.xiaofan.bangfan.MainActivity$buildPage1$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                MainActivity$buildPage1$1.onMascotTapped$lambda$0(MainActivity.this);
            }
        }, 1500L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onMascotTapped$lambda$0(MainActivity this$0) {
        MascotView mascotView;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        mascotView = this$0.mascotView;
        if (mascotView != null) {
            mascotView.setSpeaking(false);
        }
    }

    @Override // com.xiaofan.bangfan.MascotView.MascotListener
    public void onMascotLongPressed() {
        this.this$0.startActivity(new Intent(this.this$0, FunctionPanelActivity.class));
    }
}
