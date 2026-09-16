package com.xiaofan.bangfan;

import android.os.Handler;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: UpdateManager.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", NotificationCompat.CATEGORY_MESSAGE, "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class UpdateManager$startDownload$2$1 extends Lambda implements Function1<String, Unit> {
    final /* synthetic */ TextView $pct;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UpdateManager$startDownload$2$1(TextView textView) {
        super(1);
        this.$pct = textView;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(String str) {
        invoke2(str);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$0(TextView pct, String msg) {
        Intrinsics.checkNotNullParameter(pct, "$pct");
        Intrinsics.checkNotNullParameter(msg, "$msg");
        pct.setText(msg);
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(final String msg) {
        Handler handler;
        Intrinsics.checkNotNullParameter(msg, "msg");
        handler = UpdateManager.main;
        final TextView textView = this.$pct;
        handler.post(new Runnable() { // from class: com.xiaofan.bangfan.UpdateManager$startDownload$2$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                UpdateManager$startDownload$2$1.invoke$lambda$0(textView, msg);
            }
        });
    }
}
