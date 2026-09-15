package com.xiaofan.bangfan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.xiaofan.bangfan.UiKit;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
/* compiled from: VideoActivity.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0014J\b\u0010\t\u001a\u00020\u0006H\u0014J\b\u0010\n\u001a\u00020\u0006H\u0002J\b\u0010\u000b\u001a\u00020\u0006H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/xiaofan/bangfan/VideoActivity;", "Lcom/xiaofan/bangfan/BaseActivity;", "()V", "statusLine", "Landroid/widget/TextView;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "refresh", "showIntervalDialog", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class VideoActivity extends BaseActivity {
    private TextView statusLine;

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrollView scaffold = UiKit.INSTANCE.scaffold(this, "视频助刷", "打开抖音/快手后，小翻自动帮你上滑看下一条，解放双手。");
        LinearLayout content = UiKit.INSTANCE.contentOf(scaffold);
        this.statusLine = UiKit.INSTANCE.bodyText(this, "");
        content.addView(this.statusLine, UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 4.0f), 0, UiKit.INSTANCE.dp(this, 10.0f)));
        LinearLayout card = UiKit.INSTANCE.card(this);
        card.addView(UiKit.INSTANCE.cardTitle(this, "自动刷视频"));
        card.addView(UiKit.INSTANCE.switchRow(this, "开启自动刷视频", "仅在短视频 APP 前台时生效，离开自动静默", new UiKit.Getter() { // from class: com.xiaofan.bangfan.VideoActivity$$ExternalSyntheticLambda0
            @Override // com.xiaofan.bangfan.UiKit.Getter
            public final Object get() {
                boolean onCreate$lambda$0;
                onCreate$lambda$0 = VideoActivity.onCreate$lambda$0(VideoActivity.this);
                return Boolean.valueOf(onCreate$lambda$0);
            }
        }, new UiKit.Setter() { // from class: com.xiaofan.bangfan.VideoActivity$$ExternalSyntheticLambda1
            @Override // com.xiaofan.bangfan.UiKit.Setter
            public final void set(boolean z) {
                VideoActivity.onCreate$lambda$1(VideoActivity.this, z);
            }
        }), UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 6.0f), 0, 0));
        card.addView(UiKit.INSTANCE.divider(this));
        card.addView(UiKit.INSTANCE.valueRow(this, "每条视频停留", new UiKit.Getter() { // from class: com.xiaofan.bangfan.VideoActivity$$ExternalSyntheticLambda2
            @Override // com.xiaofan.bangfan.UiKit.Getter
            public final Object get() {
                String onCreate$lambda$2;
                onCreate$lambda$2 = VideoActivity.onCreate$lambda$2(VideoActivity.this);
                return onCreate$lambda$2;
            }
        }, new View.OnClickListener() { // from class: com.xiaofan.bangfan.VideoActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VideoActivity.onCreate$lambda$3(VideoActivity.this, view);
            }
        }));
        content.addView(card, UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 10.0f), 0, 0));
        content.addView(UiKit.INSTANCE.bodyText(this, "说明：小翻通过无障碍模拟上滑手势，与你自己手指上滑效果一致。部分 APP 的直播/广告页可能需要手动操作。"), UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 4.0f), 0, 0));
        setContentView(scaffold);
        refresh();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onCreate$lambda$0(VideoActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        return AppPrefs.INSTANCE.autoSwipeOn(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$1(VideoActivity this$0, boolean v) {
        FloatBallService companion;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppPrefs.INSTANCE.setAutoSwipeOn(this$0, v);
        if (!v) {
            AppPrefs.INSTANCE.setSwipePaused(this$0, false);
        }
        if (FloatBallService.Companion.isRunning() && (companion = FloatBallService.Companion.getInstance()) != null) {
            companion.refreshFromPrefs();
        }
        this$0.refresh();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String onCreate$lambda$2(VideoActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(Locale.CHINA, "%.0f 秒", Arrays.copyOf(new Object[]{Double.valueOf(AppPrefs.INSTANCE.swipeIntervalMs(this$0) / 1000.0d)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$3(VideoActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showIntervalDialog();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaofan.bangfan.BaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        refresh();
    }

    private final void refresh() {
        TextView status = this.statusLine;
        if (status == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        if (!AppPrefs.INSTANCE.autoSwipeOn(this)) {
            sb.append("自动刷视频：已关闭");
        } else if (AppPrefs.INSTANCE.swipePaused(this)) {
            sb.append("自动刷视频：已暂停");
        } else {
            sb.append("自动刷视频：已开启");
        }
        status.setText(sb.toString());
    }

    private final void showIntervalDialog() {
        final long[] intervals = {8000, 12000, 18000, 25000, 40000, 60000};
        String[] labels = {"8 秒", "12 秒", "18 秒（推荐）", "25 秒", "40 秒", "60 秒"};
        new AlertDialog.Builder(this).setTitle("每条视频停留时长").setItems(labels, new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.VideoActivity$$ExternalSyntheticLambda4
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                VideoActivity.showIntervalDialog$lambda$4(VideoActivity.this, intervals, dialogInterface, i);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showIntervalDialog$lambda$4(VideoActivity this$0, long[] intervals, DialogInterface dialogInterface, int which) {
        FloatBallService companion;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(intervals, "$intervals");
        AppPrefs.INSTANCE.setSwipeIntervalMs(this$0, intervals[which]);
        if (FloatBallService.Companion.isRunning() && (companion = FloatBallService.Companion.getInstance()) != null) {
            companion.refreshFromPrefs();
        }
        this$0.refresh();
    }
}
