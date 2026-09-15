package com.xiaofan.bangfan;

import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.ranges.RangesKt;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: UpdateManager.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "done", "", "total", "invoke"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class UpdateManager$startDownload$2$1 extends Lambda implements Function2<Long, Long, Unit> {
    final /* synthetic */ ProgressBar $bar;
    final /* synthetic */ TextView $pct;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UpdateManager$startDownload$2$1(ProgressBar progressBar, TextView textView) {
        super(2);
        this.$bar = progressBar;
        this.$pct = textView;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Long l, Long l2) {
        invoke(l.longValue(), l2.longValue());
        return Unit.INSTANCE;
    }

    public final void invoke(final long done, final long total) {
        Handler handler;
        final int p = total > 0 ? (int) ((100 * done) / total) : -1;
        handler = UpdateManager.main;
        final ProgressBar progressBar = this.$bar;
        final TextView textView = this.$pct;
        handler.post(new Runnable() { // from class: com.xiaofan.bangfan.UpdateManager$startDownload$2$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                UpdateManager$startDownload$2$1.invoke$lambda$0(progressBar, p, textView, done, total);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$0(ProgressBar bar, int $p, TextView pct, long $done, long $total) {
        StringBuilder append;
        String str;
        Intrinsics.checkNotNullParameter(bar, "$bar");
        Intrinsics.checkNotNullParameter(pct, "$pct");
        bar.setProgress(RangesKt.coerceAtLeast($p, 0));
        long j = 1024;
        long j2 = ($done / j) / j;
        if ($p >= 0) {
            append = new StringBuilder().append("已下载 ").append($p).append("%（").append(j2).append("MB / ").append(($total / j) / j);
            str = "MB）";
        } else {
            append = new StringBuilder().append("已下载 ").append(j2);
            str = "MB";
        }
        pct.setText(append.append(str).toString());
    }
}
