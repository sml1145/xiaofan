package com.xiaofan.bangfan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: DataActivity.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0006\u001a\u00020\u0007H\u0002J\b\u0010\b\u001a\u00020\u0007H\u0002J\u0018\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0012\u0010\u000e\u001a\u00020\u00072\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\b\u0010\u0011\u001a\u00020\u0007H\u0014J\b\u0010\u0012\u001a\u00020\u0007H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/xiaofan/bangfan/DataActivity;", "Lcom/xiaofan/bangfan/BaseActivity;", "()V", "bkInfoRef", "Landroid/widget/TextView;", "summary", "doExport", "", "doImportDialog", "doRestore", "file", "Ljava/io/File;", "merge", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "refresh", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class DataActivity extends BaseActivity {
    private TextView bkInfoRef;
    private TextView summary;

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrollView scaffold = UiKit.INSTANCE.scaffold(this, "数据与存档", "所有设置都自动记在本机，换机或重装后可一键恢复。");
        LinearLayout content = UiKit.INSTANCE.contentOf(scaffold);
        LinearLayout card = UiKit.INSTANCE.card(this);
        card.addView(UiKit.INSTANCE.cardTitle(this, "使用统计"));
        this.summary = UiKit.INSTANCE.bodyText(this, "");
        card.addView(this.summary, UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 6.0f), 0, 0));
        card.addView(UiKit.INSTANCE.button(this, "重置统计", false, new View.OnClickListener() { // from class: com.xiaofan.bangfan.DataActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DataActivity.onCreate$lambda$1(DataActivity.this, view);
            }
        }), UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 10.0f), 0, 0));
        content.addView(card, UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 10.0f), 0, 0));
        LinearLayout card2 = UiKit.INSTANCE.card(this);
        card2.addView(UiKit.INSTANCE.cardTitle(this, "设置存档"));
        this.bkInfoRef = UiKit.INSTANCE.bodyText(this, "");
        card2.addView(this.bkInfoRef, UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 6.0f), 0, UiKit.INSTANCE.dp(this, 10.0f)));
        LinearLayout btnRow = new LinearLayout(this);
        btnRow.setOrientation(0);
        btnRow.addView(UiKit.INSTANCE.button(this, "导出存档", true, new View.OnClickListener() { // from class: com.xiaofan.bangfan.DataActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DataActivity.onCreate$lambda$2(DataActivity.this, view);
            }
        }), new LinearLayout.LayoutParams(0, -2, 1.0f));
        TextView importBtn = UiKit.INSTANCE.button(this, "恢复存档", false, new View.OnClickListener() { // from class: com.xiaofan.bangfan.DataActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DataActivity.onCreate$lambda$3(DataActivity.this, view);
            }
        });
        LinearLayout.LayoutParams importLp = new LinearLayout.LayoutParams(0, -2, 1.0f);
        importLp.leftMargin = UiKit.INSTANCE.dp(this, 10.0f);
        btnRow.addView(importBtn, importLp);
        card2.addView(btnRow);
        content.addView(card2, UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 12.0f), 0, 0));
        setContentView(scaffold);
        refresh();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$1(final DataActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        new AlertDialog.Builder(this$0).setTitle("重置统计？").setMessage("只清空翻页与广告计数，不影响书架与设置。").setPositiveButton("重置", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.DataActivity$$ExternalSyntheticLambda3
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DataActivity.onCreate$lambda$1$lambda$0(DataActivity.this, dialogInterface, i);
            }
        }).setNegativeButton("取消", (DialogInterface.OnClickListener) null).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$1$lambda$0(DataActivity this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppPrefs.INSTANCE.resetStats(this$0);
        this$0.refresh();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$2(DataActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.doExport();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$3(DataActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.doImportDialog();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaofan.bangfan.BaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        refresh();
    }

    private final void refresh() {
        String str;
        long turns = AppPrefs.INSTANCE.totalTurns(this);
        long ads = AppPrefs.INSTANCE.totalAdSkipped(this);
        long readMin = AppPrefs.INSTANCE.totalReadMs(this) / 60000;
        TextView textView = this.summary;
        if (textView != null) {
            textView.setText("已翻页 " + turns + " 次 · 跳过广告 " + ads + " 次 · 累计阅读约 " + readMin + " 分钟");
        }
        File file = SettingsBackup.INSTANCE.defaultFile(this);
        TextView textView2 = this.bkInfoRef;
        if (textView2 != null) {
            if (file != null && file.exists()) {
                str = SettingsBackup.INSTANCE.describe(this, file) + "\n\n位置：\n" + file.getAbsolutePath();
            }
            textView2.setText(str);
        }
    }

    private final void doExport() {
        try {
            File file = SettingsBackup.INSTANCE.export(this, SettingsBackup.INSTANCE.defaultFile(this));
            Toast.makeText(this, "已导出：" + file.getName(), 0).show();
            refresh();
        } catch (Exception e) {
            Toast.makeText(this, "导出失败：" + e.getMessage(), 0).show();
        }
    }

    private final void doImportDialog() {
        final File file = SettingsBackup.INSTANCE.defaultFile(this);
        if (file == null || !file.exists()) {
            Toast.makeText(this, "没有找到存档文件", 0).show();
        } else {
            new AlertDialog.Builder(this).setTitle("恢复方式").setMessage("覆盖恢复：以存档为准，替换当前全部设置。\n合并恢复：只补充当前缺失项，保留你现在的设置。").setPositiveButton("覆盖恢复", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.DataActivity$$ExternalSyntheticLambda4
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    DataActivity.doImportDialog$lambda$4(DataActivity.this, file, dialogInterface, i);
                }
            }).setNegativeButton("合并恢复", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.DataActivity$$ExternalSyntheticLambda5
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    DataActivity.doImportDialog$lambda$5(DataActivity.this, file, dialogInterface, i);
                }
            }).setNeutralButton("取消", (DialogInterface.OnClickListener) null).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void doImportDialog$lambda$4(DataActivity this$0, File $file, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.doRestore($file, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void doImportDialog$lambda$5(DataActivity this$0, File $file, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.doRestore($file, true);
    }

    private final void doRestore(File file, boolean merge) {
        FloatBallService companion;
        try {
            int count = SettingsBackup.INSTANCE.restore(this, file, merge);
            Toast.makeText(this, "已恢复 " + count + " 项", 0).show();
            XiaoFanVoice.INSTANCE.applyVoiceParams(this);
            if (FloatBallService.Companion.isRunning() && (companion = FloatBallService.Companion.getInstance()) != null) {
                companion.refreshFromPrefs();
            }
            refresh();
        } catch (Exception e) {
            Toast.makeText(this, "恢复失败：" + e.getMessage(), 0).show();
        }
    }
}
