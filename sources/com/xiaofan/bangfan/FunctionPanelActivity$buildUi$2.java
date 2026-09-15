package com.xiaofan.bangfan;

import android.os.Handler;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.xiaofan.bangfan.MascotView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: FunctionPanelActivity.kt */
@Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016¨\u0006\u0005"}, d2 = {"com/xiaofan/bangfan/FunctionPanelActivity$buildUi$2", "Lcom/xiaofan/bangfan/MascotView$MascotListener;", "onMascotLongPressed", "", "onMascotTapped", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class FunctionPanelActivity$buildUi$2 implements MascotView.MascotListener {
    final /* synthetic */ FunctionPanelActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FunctionPanelActivity$buildUi$2(FunctionPanelActivity $receiver) {
        this.this$0 = $receiver;
    }

    @Override // com.xiaofan.bangfan.MascotView.MascotListener
    public void onMascotTapped() {
        MascotView mascotView;
        TextView textView;
        Handler handler;
        String msg = new String[]{"我在呀", "有什么可以帮你", "要不要听个时间？", "天气我也能查哦", "想定闹钟吗", "翻页交给我就好"}[(int) (Math.random() * 6)];
        mascotView = this.this$0.mascot;
        if (mascotView != null) {
            mascotView.setSpeaking(true);
        }
        textView = this.this$0.mascotHint;
        if (textView != null) {
            textView.setText(msg);
        }
        XiaoFanVoice.INSTANCE.tip(this.this$0, msg);
        handler = this.this$0.main;
        final FunctionPanelActivity functionPanelActivity = this.this$0;
        handler.postDelayed(new Runnable() { // from class: com.xiaofan.bangfan.FunctionPanelActivity$buildUi$2$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                FunctionPanelActivity$buildUi$2.onMascotTapped$lambda$0(FunctionPanelActivity.this);
            }
        }, 1600L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onMascotTapped$lambda$0(FunctionPanelActivity this$0) {
        MascotView mascotView;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        mascotView = this$0.mascot;
        if (mascotView != null) {
            mascotView.setSpeaking(false);
        }
    }

    @Override // com.xiaofan.bangfan.MascotView.MascotListener
    public void onMascotLongPressed() {
        MascotView mascotView;
        TextView textView;
        Handler handler;
        mascotView = this.this$0.mascot;
        if (mascotView != null) {
            mascotView.setSpeaking(true);
        }
        textView = this.this$0.mascotHint;
        if (textView != null) {
            textView.setText("长按我也会回应哦");
        }
        XiaoFanVoice.INSTANCE.tip(this.this$0, "你好呀");
        handler = this.this$0.main;
        final FunctionPanelActivity functionPanelActivity = this.this$0;
        handler.postDelayed(new Runnable() { // from class: com.xiaofan.bangfan.FunctionPanelActivity$buildUi$2$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                FunctionPanelActivity$buildUi$2.onMascotLongPressed$lambda$1(FunctionPanelActivity.this);
            }
        }, 1600L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onMascotLongPressed$lambda$1(FunctionPanelActivity this$0) {
        MascotView mascotView;
        TextView textView;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        mascotView = this$0.mascot;
        if (mascotView != null) {
            mascotView.setSpeaking(false);
        }
        textView = this$0.mascotHint;
        if (textView == null) {
            return;
        }
        textView.setText("点我一下试试");
    }
}
