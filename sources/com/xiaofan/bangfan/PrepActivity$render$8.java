package com.xiaofan.bangfan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* compiled from: PrepActivity.kt */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
final class PrepActivity$render$8 extends Lambda implements Function0<Unit> {
    final /* synthetic */ PrepActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PrepActivity$render$8(PrepActivity prepActivity) {
        super(0);
        this.this$0 = prepActivity;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2() {
        if (!UiKit.INSTANCE.isIgnoringBatteryOptimizations(this.this$0)) {
            final PrepActivity prepActivity = this.this$0;
            new AlertDialog.Builder(this.this$0).setTitle("允许小翻后台常驻").setMessage("小翻需要在后台持续运行才能自动翻页和响闹钟。\n\n系统即将弹出「忽略电池优化」确认框，请选择「允许」。\n部分手机还需在系统设置里把小翻的后台策略改为「无限制/允许自启动」。").setPositiveButton("去允许", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.PrepActivity$render$8$$ExternalSyntheticLambda0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    PrepActivity$render$8.invoke$lambda$0(PrepActivity.this, dialogInterface, i);
                }
            }).setNegativeButton("稍后", (DialogInterface.OnClickListener) null).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$0(PrepActivity this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        UiKit.INSTANCE.requestIgnoreBatteryOptimizations(this$0);
    }
}
