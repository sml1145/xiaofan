package com.xiaofan.bangfan;

import android.os.Handler;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MainActivity.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "piece", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class MainActivity$answerByLocalBrain$1$ans$1 extends Lambda implements Function1<String, Unit> {
    final /* synthetic */ StringBuilder $sb;
    final /* synthetic */ MainActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainActivity$answerByLocalBrain$1$ans$1(StringBuilder sb, MainActivity mainActivity) {
        super(1);
        this.$sb = sb;
        this.this$0 = mainActivity;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(String str) {
        invoke2(str);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(String piece) {
        Handler handler;
        Intrinsics.checkNotNullParameter(piece, "piece");
        this.$sb.append(piece);
        final String cur = this.$sb.toString();
        Intrinsics.checkNotNullExpressionValue(cur, "toString(...)");
        handler = this.this$0.main;
        final MainActivity mainActivity = this.this$0;
        handler.post(new Runnable() { // from class: com.xiaofan.bangfan.MainActivity$answerByLocalBrain$1$ans$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                MainActivity$answerByLocalBrain$1$ans$1.invoke$lambda$0(MainActivity.this, cur);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$0(MainActivity this$0, String cur) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(cur, "$cur");
        this$0.replaceLastXiaoFan(StringsKt.isBlank(cur) ? "我想一想…" : cur);
    }
}
