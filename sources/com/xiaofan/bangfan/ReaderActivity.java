package com.xiaofan.bangfan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.xiaofan.bangfan.UiKit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.DebugKt;
/* compiled from: ReaderActivity.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0014J\b\u0010\t\u001a\u00020\u0006H\u0014J\b\u0010\n\u001a\u00020\u0006H\u0002J\b\u0010\u000b\u001a\u00020\u0006H\u0002J\b\u0010\f\u001a\u00020\u0006H\u0002J\b\u0010\r\u001a\u00020\u0006H\u0002J\b\u0010\u000e\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/xiaofan/bangfan/ReaderActivity;", "Lcom/xiaofan/bangfan/BaseActivity;", "()V", "statusLine", "Landroid/widget/TextView;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "refresh", "showCustomFactorDialog", "showCustomIntervalDialog", "showFactorDialog", "showTurnModeDialog", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class ReaderActivity extends BaseActivity {
    private TextView statusLine;

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrollView scaffold = UiKit.INSTANCE.scaffold(this, "阅读翻页", "小翻按你的阅读速度自动翻页；离开屏幕会自动暂停，回来继续。");
        LinearLayout content = UiKit.INSTANCE.contentOf(scaffold);
        this.statusLine = UiKit.INSTANCE.bodyText(this, "");
        TextView textView = this.statusLine;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("statusLine");
            textView = null;
        }
        content.addView(textView, UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 4.0f), 0, UiKit.INSTANCE.dp(this, 10.0f)));
        LinearLayout turnCard = UiKit.INSTANCE.card(this);
        turnCard.addView(UiKit.INSTANCE.cardTitle(this, "自动翻页"));
        turnCard.addView(UiKit.INSTANCE.switchRow(this, "开启自动翻页", "按测速结果定时翻页", new UiKit.Getter() { // from class: com.xiaofan.bangfan.ReaderActivity$$ExternalSyntheticLambda15
            @Override // com.xiaofan.bangfan.UiKit.Getter
            public final Object get() {
                boolean onCreate$lambda$0;
                onCreate$lambda$0 = ReaderActivity.onCreate$lambda$0(ReaderActivity.this);
                return Boolean.valueOf(onCreate$lambda$0);
            }
        }, new UiKit.Setter() { // from class: com.xiaofan.bangfan.ReaderActivity$$ExternalSyntheticLambda3
            @Override // com.xiaofan.bangfan.UiKit.Setter
            public final void set(boolean z) {
                ReaderActivity.onCreate$lambda$1(ReaderActivity.this, z);
            }
        }), UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 6.0f), 0, 0));
        content.addView(turnCard, UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 10.0f), 0, 0));
        LinearLayout rhythmCard = UiKit.INSTANCE.card(this);
        rhythmCard.addView(UiKit.INSTANCE.cardTitle(this, "阅读节奏"));
        rhythmCard.addView(UiKit.INSTANCE.button(this, AppPrefs.INSTANCE.speedDone(this) ? "重新测速（10 页）" : "开始阅读测速（10 页）", true, new View.OnClickListener() { // from class: com.xiaofan.bangfan.ReaderActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ReaderActivity.onCreate$lambda$2(ReaderActivity.this, view);
            }
        }), UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 6.0f), 0, UiKit.INSTANCE.dp(this, 10.0f)));
        boolean useCustom = AppPrefs.INSTANCE.useCustomInterval(this);
        rhythmCard.addView(UiKit.INSTANCE.switchRow(this, "使用自定义翻页间隔", useCustom ? "当前：自定义间隔，与系统测速二选一" : "当前：按系统测速结果翻页，可切换为自定义", new UiKit.Getter() { // from class: com.xiaofan.bangfan.ReaderActivity$$ExternalSyntheticLambda5
            @Override // com.xiaofan.bangfan.UiKit.Getter
            public final Object get() {
                boolean onCreate$lambda$3;
                onCreate$lambda$3 = ReaderActivity.onCreate$lambda$3(ReaderActivity.this);
                return Boolean.valueOf(onCreate$lambda$3);
            }
        }, new UiKit.Setter() { // from class: com.xiaofan.bangfan.ReaderActivity$$ExternalSyntheticLambda6
            @Override // com.xiaofan.bangfan.UiKit.Setter
            public final void set(boolean z) {
                ReaderActivity.onCreate$lambda$4(ReaderActivity.this, z);
            }
        }), UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 4.0f), 0, 0));
        rhythmCard.addView(UiKit.INSTANCE.divider(this));
        if (useCustom) {
            rhythmCard.addView(UiKit.INSTANCE.valueRow(this, "自定义翻页间隔", new UiKit.Getter() { // from class: com.xiaofan.bangfan.ReaderActivity$$ExternalSyntheticLambda7
                @Override // com.xiaofan.bangfan.UiKit.Getter
                public final Object get() {
                    String onCreate$lambda$5;
                    onCreate$lambda$5 = ReaderActivity.onCreate$lambda$5(ReaderActivity.this);
                    return onCreate$lambda$5;
                }
            }, new View.OnClickListener() { // from class: com.xiaofan.bangfan.ReaderActivity$$ExternalSyntheticLambda8
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ReaderActivity.onCreate$lambda$6(ReaderActivity.this, view);
                }
            }));
            rhythmCard.addView(UiKit.INSTANCE.divider(this));
            rhythmCard.addView(UiKit.INSTANCE.valueRow(this, "自定义间隔倍率", new UiKit.Getter() { // from class: com.xiaofan.bangfan.ReaderActivity$$ExternalSyntheticLambda9
                @Override // com.xiaofan.bangfan.UiKit.Getter
                public final Object get() {
                    String onCreate$lambda$7;
                    onCreate$lambda$7 = ReaderActivity.onCreate$lambda$7(ReaderActivity.this);
                    return onCreate$lambda$7;
                }
            }, new View.OnClickListener() { // from class: com.xiaofan.bangfan.ReaderActivity$$ExternalSyntheticLambda10
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ReaderActivity.onCreate$lambda$8(ReaderActivity.this, view);
                }
            }));
        } else {
            rhythmCard.addView(UiKit.INSTANCE.valueRow(this, "翻页间隔倍率", new UiKit.Getter() { // from class: com.xiaofan.bangfan.ReaderActivity$$ExternalSyntheticLambda12
                @Override // com.xiaofan.bangfan.UiKit.Getter
                public final Object get() {
                    String onCreate$lambda$9;
                    onCreate$lambda$9 = ReaderActivity.onCreate$lambda$9(ReaderActivity.this);
                    return onCreate$lambda$9;
                }
            }, new View.OnClickListener() { // from class: com.xiaofan.bangfan.ReaderActivity$$ExternalSyntheticLambda16
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ReaderActivity.onCreate$lambda$10(ReaderActivity.this, view);
                }
            }));
        }
        rhythmCard.addView(UiKit.INSTANCE.divider(this));
        rhythmCard.addView(UiKit.INSTANCE.valueRow(this, "翻页方式", new UiKit.Getter() { // from class: com.xiaofan.bangfan.ReaderActivity$$ExternalSyntheticLambda17
            @Override // com.xiaofan.bangfan.UiKit.Getter
            public final Object get() {
                String onCreate$lambda$11;
                onCreate$lambda$11 = ReaderActivity.onCreate$lambda$11(ReaderActivity.this);
                return onCreate$lambda$11;
            }
        }, new View.OnClickListener() { // from class: com.xiaofan.bangfan.ReaderActivity$$ExternalSyntheticLambda18
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ReaderActivity.onCreate$lambda$12(ReaderActivity.this, view);
            }
        }));
        content.addView(rhythmCard, UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 12.0f), 0, 0));
        LinearLayout smartCard = UiKit.INSTANCE.card(this);
        smartCard.addView(UiKit.INSTANCE.cardTitle(this, "智能识别"));
        smartCard.addView(UiKit.INSTANCE.switchRow(this, "离开屏幕就暂停", "前置摄像头检测人脸：人不在时暂停，回来自动继续。\n注意：夜晚或光线不足时人脸检测可能不准，建议关闭此功能以保证自动翻页正常。", new UiKit.Getter() { // from class: com.xiaofan.bangfan.ReaderActivity$$ExternalSyntheticLambda19
            @Override // com.xiaofan.bangfan.UiKit.Getter
            public final Object get() {
                boolean onCreate$lambda$13;
                onCreate$lambda$13 = ReaderActivity.onCreate$lambda$13(ReaderActivity.this);
                return Boolean.valueOf(onCreate$lambda$13);
            }
        }, new UiKit.Setter() { // from class: com.xiaofan.bangfan.ReaderActivity$$ExternalSyntheticLambda20
            @Override // com.xiaofan.bangfan.UiKit.Setter
            public final void set(boolean z) {
                ReaderActivity.onCreate$lambda$14(ReaderActivity.this, z);
            }
        }), UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 4.0f), 0, 0));
        smartCard.addView(UiKit.INSTANCE.divider(this));
        smartCard.addView(UiKit.INSTANCE.switchRow(this, "广告页自动处理", "识别广告并等倒计时结束后翻页", new UiKit.Getter() { // from class: com.xiaofan.bangfan.ReaderActivity$$ExternalSyntheticLambda21
            @Override // com.xiaofan.bangfan.UiKit.Getter
            public final Object get() {
                boolean onCreate$lambda$15;
                onCreate$lambda$15 = ReaderActivity.onCreate$lambda$15(ReaderActivity.this);
                return Boolean.valueOf(onCreate$lambda$15);
            }
        }, new UiKit.Setter() { // from class: com.xiaofan.bangfan.ReaderActivity$$ExternalSyntheticLambda22
            @Override // com.xiaofan.bangfan.UiKit.Setter
            public final void set(boolean z) {
                ReaderActivity.onCreate$lambda$16(ReaderActivity.this, z);
            }
        }));
        smartCard.addView(UiKit.INSTANCE.divider(this));
        smartCard.addView(UiKit.INSTANCE.switchRow(this, "书末页加速翻页", "识别到章节末页（文字很少）时翻页提速一倍，翻完即恢复正常；插图页不会误判", new UiKit.Getter() { // from class: com.xiaofan.bangfan.ReaderActivity$$ExternalSyntheticLambda1
            @Override // com.xiaofan.bangfan.UiKit.Getter
            public final Object get() {
                boolean onCreate$lambda$17;
                onCreate$lambda$17 = ReaderActivity.onCreate$lambda$17(ReaderActivity.this);
                return Boolean.valueOf(onCreate$lambda$17);
            }
        }, new UiKit.Setter() { // from class: com.xiaofan.bangfan.ReaderActivity$$ExternalSyntheticLambda2
            @Override // com.xiaofan.bangfan.UiKit.Setter
            public final void set(boolean z) {
                ReaderActivity.onCreate$lambda$18(ReaderActivity.this, z);
            }
        }));
        content.addView(smartCard, UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 12.0f), 0, 0));
        content.addView(UiKit.INSTANCE.bodyText(this, "离线声控在第二页“声控与大脑”模块里。小翻已内置番茄小说、七猫、起点读书、QQ 阅读、微信读书、掌阅等主流阅读 APP 的识别，也会记住你用过的其它阅读 APP，无需手动配置即可自动翻页。"), UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 12.0f), 0, 0));
        setContentView(scaffold);
        refresh();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onCreate$lambda$0(ReaderActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        return AppPrefs.INSTANCE.autoTurn(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$1(ReaderActivity this$0, boolean v) {
        FloatBallService companion;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TurnManager.INSTANCE.setAutoTurn(this$0, v);
        if (FloatBallService.Companion.isRunning() && (companion = FloatBallService.Companion.getInstance()) != null) {
            companion.refreshFromPrefs();
        }
        this$0.refresh();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$2(ReaderActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0, SpeedTestActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onCreate$lambda$3(ReaderActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        return AppPrefs.INSTANCE.useCustomInterval(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$4(ReaderActivity this$0, boolean v) {
        FloatBallService companion;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppPrefs.INSTANCE.setUseCustomInterval(this$0, v);
        if (FloatBallService.Companion.isRunning() && (companion = FloatBallService.Companion.getInstance()) != null) {
            companion.refreshFromPrefs();
        }
        this$0.recreate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String onCreate$lambda$5(ReaderActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(Locale.CHINA, "%.1f 秒/页", Arrays.copyOf(new Object[]{Double.valueOf(AppPrefs.INSTANCE.customIntervalMs(this$0) / 1000.0d)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$6(ReaderActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showCustomIntervalDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String onCreate$lambda$7(ReaderActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(Locale.CHINA, "%.2f 倍 · 实际约 %.1f 秒/页", Arrays.copyOf(new Object[]{Float.valueOf(AppPrefs.INSTANCE.customTurnFactor(this$0)), Double.valueOf(AppPrefs.INSTANCE.turnIntervalMs(this$0) / 1000.0d)}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$8(ReaderActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showCustomFactorDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String onCreate$lambda$9(ReaderActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(Locale.CHINA, "%.2f 倍 · 约 %.1f 秒/页", Arrays.copyOf(new Object[]{Float.valueOf(AppPrefs.INSTANCE.turnFactor(this$0)), Double.valueOf(AppPrefs.INSTANCE.turnIntervalMs(this$0) / 1000.0d)}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$10(ReaderActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showFactorDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String onCreate$lambda$11(ReaderActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String turnMode = AppPrefs.INSTANCE.turnMode(this$0);
        return Intrinsics.areEqual(turnMode, "click") ? "点击右半屏" : Intrinsics.areEqual(turnMode, "swipe") ? "左右滑动" : "自动（滑动）";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$12(ReaderActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showTurnModeDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onCreate$lambda$13(ReaderActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        return AppPrefs.INSTANCE.presenceRequired(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$14(ReaderActivity this$0, boolean v) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppPrefs.INSTANCE.setPresenceRequired(this$0, v);
        if (FloatBallService.Companion.isRunning()) {
            FloatBallService svc = FloatBallService.Companion.getInstance();
            if (v) {
                if (svc != null) {
                    svc.startPresenceDetection();
                }
                Toast.makeText(this$0, "已开启离开屏幕暂停", 0).show();
                return;
            }
            if (svc != null) {
                svc.stopPresenceDetection();
            }
            Toast.makeText(this$0, "已关闭离开屏幕暂停，将持续自动翻页", 0).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onCreate$lambda$15(ReaderActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        return AppPrefs.INSTANCE.adDetect(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$16(ReaderActivity this$0, boolean v) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppPrefs.INSTANCE.setAdDetect(this$0, v);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onCreate$lambda$17(ReaderActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        return AppPrefs.INSTANCE.speedUpOnBookEnd(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$18(ReaderActivity this$0, boolean v) {
        FloatBallService companion;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppPrefs.INSTANCE.setSpeedUpOnBookEnd(this$0, v);
        if (!FloatBallService.Companion.isRunning() || (companion = FloatBallService.Companion.getInstance()) == null) {
            return;
        }
        companion.refreshFromPrefs();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaofan.bangfan.BaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        refresh();
    }

    private final void refresh() {
        StringBuilder sb = new StringBuilder();
        sb.append(AppPrefs.INSTANCE.autoTurn(this) ? "自动翻页：已开启" : "自动翻页：已关闭");
        if (AppPrefs.INSTANCE.speedDone(this)) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format(Locale.CHINA, " · 已测速，平均 %.1f 秒/页", Arrays.copyOf(new Object[]{Double.valueOf(AppPrefs.INSTANCE.avgReadMs(this) / 1000.0d)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            sb.append(format);
        } else {
            sb.append(" · 尚未测速（建议先测一次）");
        }
        TextView textView = this.statusLine;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("statusLine");
            textView = null;
        }
        textView.setText(sb.toString());
    }

    private final void showFactorDialog() {
        final float[] factors = {1.0f, 1.05f, 1.1f, 1.2f, 1.35f, 1.5f};
        new AlertDialog.Builder(this).setTitle("翻页间隔倍率").setItems(new String[]{"1.00 倍（严格按测速速度）", "1.05 倍", "1.10 倍（推荐）", "1.20 倍（更从容）", "1.35 倍", "1.50 倍（很慢的读物）"}, new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.ReaderActivity$$ExternalSyntheticLambda14
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                ReaderActivity.showFactorDialog$lambda$19(ReaderActivity.this, factors, dialogInterface, i);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showFactorDialog$lambda$19(ReaderActivity this$0, float[] factors, DialogInterface dialogInterface, int which) {
        FloatBallService companion;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(factors, "$factors");
        AppPrefs.INSTANCE.setTurnFactor(this$0, factors[which]);
        if (FloatBallService.Companion.isRunning() && (companion = FloatBallService.Companion.getInstance()) != null) {
            companion.refreshFromPrefs();
        }
        this$0.refresh();
    }

    private final void showCustomIntervalDialog() {
        final long[] intervals = {2000, 3000, 4000, 5000, AppPrefs.FALLBACK_READ_MS, 8000, 10000, 15000};
        Collection destination$iv$iv = new ArrayList(intervals.length);
        for (long item$iv$iv : intervals) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format(Locale.CHINA, "%.0f 秒/页", Arrays.copyOf(new Object[]{Double.valueOf(item$iv$iv / 1000.0d)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            destination$iv$iv.add(format);
        }
        Collection thisCollection$iv = (List) destination$iv$iv;
        String[] labels = (String[]) thisCollection$iv.toArray(new String[0]);
        new AlertDialog.Builder(this).setTitle("自定义翻页间隔").setItems(labels, new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.ReaderActivity$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                ReaderActivity.showCustomIntervalDialog$lambda$21(ReaderActivity.this, intervals, dialogInterface, i);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showCustomIntervalDialog$lambda$21(ReaderActivity this$0, long[] intervals, DialogInterface dialogInterface, int which) {
        FloatBallService companion;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(intervals, "$intervals");
        AppPrefs.INSTANCE.setCustomIntervalMs(this$0, intervals[which]);
        if (FloatBallService.Companion.isRunning() && (companion = FloatBallService.Companion.getInstance()) != null) {
            companion.refreshFromPrefs();
        }
        this$0.recreate();
    }

    private final void showCustomFactorDialog() {
        final float[] factors = {0.5f, 0.75f, 1.0f, 1.25f, 1.5f, 2.0f};
        Collection destination$iv$iv = new ArrayList(factors.length);
        for (float item$iv$iv : factors) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format(Locale.CHINA, "%.2f 倍", Arrays.copyOf(new Object[]{Float.valueOf(item$iv$iv)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            destination$iv$iv.add(format);
        }
        Collection thisCollection$iv = (List) destination$iv$iv;
        String[] labels = (String[]) thisCollection$iv.toArray(new String[0]);
        new AlertDialog.Builder(this).setTitle("自定义间隔倍率").setItems(labels, new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.ReaderActivity$$ExternalSyntheticLambda11
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                ReaderActivity.showCustomFactorDialog$lambda$23(ReaderActivity.this, factors, dialogInterface, i);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showCustomFactorDialog$lambda$23(ReaderActivity this$0, float[] factors, DialogInterface dialogInterface, int which) {
        FloatBallService companion;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(factors, "$factors");
        AppPrefs.INSTANCE.setCustomTurnFactor(this$0, factors[which]);
        if (FloatBallService.Companion.isRunning() && (companion = FloatBallService.Companion.getInstance()) != null) {
            companion.refreshFromPrefs();
        }
        this$0.recreate();
    }

    private final void showTurnModeDialog() {
        final String[] modes = {DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "swipe", "click"};
        new AlertDialog.Builder(this).setTitle("翻页方式").setItems(new String[]{"自动滑动（推荐，兼容多数阅读 APP）", "左右滑动", "点击右半屏（适合支持点击翻页的 APP）"}, new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.ReaderActivity$$ExternalSyntheticLambda13
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                ReaderActivity.showTurnModeDialog$lambda$24(ReaderActivity.this, modes, dialogInterface, i);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showTurnModeDialog$lambda$24(ReaderActivity this$0, String[] modes, DialogInterface dialogInterface, int which) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(modes, "$modes");
        AppPrefs.INSTANCE.setTurnMode(this$0, modes[which]);
    }
}
