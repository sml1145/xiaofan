package com.xiaofan.bangfan;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import java.util.Arrays;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
/* compiled from: SpeedTestActivity.kt */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 .2\u00020\u0001:\u0001.B\u0005¢\u0006\u0002\u0010\u0002J\u0019\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000fH\u0002¢\u0006\u0002\u0010\u0019J\b\u0010\u001a\u001a\u00020\u001bH\u0002J\u0010\u0010\u001c\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020 H\u0002J\b\u0010!\u001a\u00020 H\u0002J\b\u0010\"\u001a\u00020 H\u0017J\u0012\u0010#\u001a\u00020 2\b\u0010$\u001a\u0004\u0018\u00010%H\u0014J\b\u0010&\u001a\u00020 H\u0014J\b\u0010'\u001a\u00020 H\u0002J\u0010\u0010(\u001a\u00020 2\u0006\u0010)\u001a\u00020\nH\u0002J\b\u0010*\u001a\u00020 H\u0002J\b\u0010+\u001a\u00020 H\u0002J\u0010\u0010,\u001a\u00020 2\u0006\u0010-\u001a\u00020\u0010H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000fX\u0082.¢\u0006\u0004\n\u0002\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lcom/xiaofan/bangfan/SpeedTestActivity;", "Lcom/xiaofan/bangfan/BaseActivity;", "()V", "finished", "", "nextBtn", "Landroid/widget/TextView;", "pageCostMs", "", "pageIndex", "", "pageStartedAt", "", "pageText", "pages", "", "", "[[Ljava/lang/String;", "progressText", "resultText", "timerTask", "Ljava/lang/Runnable;", "timerText", "titleText", "buildPages", "()[[Ljava/lang/String;", "buildUi", "Landroid/view/View;", "dp", "value", "", "finishOk", "", "finishTest", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onNextClick", "showPage", "index", "startTimer", "stopTimer", "toast", NotificationCompat.CATEGORY_MESSAGE, "Companion", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class SpeedTestActivity extends BaseActivity {
    public static final Companion Companion = new Companion(null);
    private static final int PAGE_COUNT = 10;
    private boolean finished;
    private TextView nextBtn;
    private final long[] pageCostMs = new long[10];
    private int pageIndex;
    private long pageStartedAt;
    private TextView pageText;
    private String[][] pages;
    private TextView progressText;
    private TextView resultText;
    private Runnable timerTask;
    private TextView timerText;
    private TextView titleText;

    /* compiled from: SpeedTestActivity.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/xiaofan/bangfan/SpeedTestActivity$Companion;", "", "()V", "PAGE_COUNT", "", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.pages = buildPages();
        setContentView(buildUi());
        showPage(0);
    }

    private final View buildUi() {
        FrameLayout root = new FrameLayout(this);
        root.setBackgroundColor(getColor(R.color.bg));
        LinearLayout col = new LinearLayout(this);
        col.setOrientation(1);
        col.setPadding(dp(20.0f), dp(24.0f), dp(20.0f), dp(20.0f));
        this.titleText = new TextView(this);
        TextView textView = this.titleText;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("titleText");
            textView = null;
        }
        textView.setText(getString(R.string.speed_title));
        TextView textView3 = this.titleText;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("titleText");
            textView3 = null;
        }
        textView3.setTextColor(getColor(R.color.ink));
        TextView textView4 = this.titleText;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("titleText");
            textView4 = null;
        }
        textView4.setTextSize(2, 24.0f);
        TextView textView5 = this.titleText;
        if (textView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("titleText");
            textView5 = null;
        }
        textView5.getPaint().setFakeBoldText(true);
        TextView textView6 = this.titleText;
        if (textView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("titleText");
            textView6 = null;
        }
        col.addView(textView6);
        this.progressText = new TextView(this);
        TextView textView7 = this.progressText;
        if (textView7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressText");
            textView7 = null;
        }
        textView7.setTextColor(getColor(R.color.muted));
        TextView textView8 = this.progressText;
        if (textView8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressText");
            textView8 = null;
        }
        textView8.setTextSize(2, 13.0f);
        TextView textView9 = this.progressText;
        if (textView9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressText");
            textView9 = null;
        }
        textView9.setPadding(0, dp(6.0f), 0, dp(10.0f));
        TextView textView10 = this.progressText;
        if (textView10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressText");
            textView10 = null;
        }
        col.addView(textView10);
        this.timerText = new TextView(this);
        TextView textView11 = this.timerText;
        if (textView11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("timerText");
            textView11 = null;
        }
        textView11.setTextColor(getColor(R.color.brand));
        TextView textView12 = this.timerText;
        if (textView12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("timerText");
            textView12 = null;
        }
        textView12.setTextSize(2, 15.0f);
        TextView textView13 = this.timerText;
        if (textView13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("timerText");
            textView13 = null;
        }
        textView13.getPaint().setFakeBoldText(true);
        TextView textView14 = this.timerText;
        if (textView14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("timerText");
            textView14 = null;
        }
        textView14.setPadding(0, 0, 0, dp(10.0f));
        TextView textView15 = this.timerText;
        if (textView15 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("timerText");
            textView15 = null;
        }
        col.addView(textView15);
        ScrollView scroll = new ScrollView(this);
        this.pageText = new TextView(this);
        TextView textView16 = this.pageText;
        if (textView16 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pageText");
            textView16 = null;
        }
        textView16.setTextColor(getColor(R.color.ink));
        TextView textView17 = this.pageText;
        if (textView17 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pageText");
            textView17 = null;
        }
        textView17.setTextSize(2, 17.5f);
        TextView textView18 = this.pageText;
        if (textView18 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pageText");
            textView18 = null;
        }
        textView18.setLineSpacing(0.0f, 1.7f);
        TextView textView19 = this.pageText;
        if (textView19 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pageText");
            textView19 = null;
        }
        textView19.setPadding(dp(4.0f), dp(6.0f), dp(4.0f), dp(6.0f));
        TextView textView20 = this.pageText;
        if (textView20 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pageText");
            textView20 = null;
        }
        scroll.addView(textView20, new FrameLayout.LayoutParams(-1, -2));
        col.addView(scroll, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        this.resultText = new TextView(this);
        TextView textView21 = this.resultText;
        if (textView21 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resultText");
            textView21 = null;
        }
        textView21.setTextColor(getColor(R.color.ok));
        TextView textView22 = this.resultText;
        if (textView22 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resultText");
            textView22 = null;
        }
        textView22.setTextSize(2, 14.0f);
        TextView textView23 = this.resultText;
        if (textView23 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resultText");
            textView23 = null;
        }
        textView23.setLineSpacing(0.0f, 1.4f);
        TextView textView24 = this.resultText;
        if (textView24 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resultText");
            textView24 = null;
        }
        textView24.setVisibility(8);
        TextView textView25 = this.resultText;
        if (textView25 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resultText");
            textView25 = null;
        }
        col.addView(textView25, new LinearLayout.LayoutParams(-1, -2));
        this.nextBtn = new TextView(this);
        TextView textView26 = this.nextBtn;
        if (textView26 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nextBtn");
            textView26 = null;
        }
        textView26.setText(getString(R.string.speed_next));
        TextView textView27 = this.nextBtn;
        if (textView27 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nextBtn");
            textView27 = null;
        }
        textView27.setTextColor(-1);
        TextView textView28 = this.nextBtn;
        if (textView28 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nextBtn");
            textView28 = null;
        }
        textView28.setTextSize(2, 16.0f);
        TextView textView29 = this.nextBtn;
        if (textView29 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nextBtn");
            textView29 = null;
        }
        textView29.getPaint().setFakeBoldText(true);
        TextView textView30 = this.nextBtn;
        if (textView30 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nextBtn");
            textView30 = null;
        }
        textView30.setGravity(17);
        TextView textView31 = this.nextBtn;
        if (textView31 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nextBtn");
            textView31 = null;
        }
        textView31.setPadding(dp(16.0f), dp(14.0f), dp(16.0f), dp(14.0f));
        GradientDrawable bg = new GradientDrawable();
        bg.setColor(getColor(R.color.brand));
        bg.setCornerRadius(dp(26.0f));
        TextView textView32 = this.nextBtn;
        if (textView32 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nextBtn");
            textView32 = null;
        }
        textView32.setBackground(bg);
        TextView textView33 = this.nextBtn;
        if (textView33 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nextBtn");
            textView33 = null;
        }
        textView33.setOnClickListener(new View.OnClickListener() { // from class: com.xiaofan.bangfan.SpeedTestActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SpeedTestActivity.buildUi$lambda$0(SpeedTestActivity.this, view);
            }
        });
        LinearLayout.LayoutParams btnLp = new LinearLayout.LayoutParams(-1, -2);
        btnLp.topMargin = dp(14.0f);
        TextView textView34 = this.nextBtn;
        if (textView34 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nextBtn");
        } else {
            textView2 = textView34;
        }
        col.addView(textView2, btnLp);
        root.addView(col, new FrameLayout.LayoutParams(-1, -1));
        return root;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void buildUi$lambda$0(SpeedTestActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onNextClick();
    }

    private final void onNextClick() {
        if (this.finished) {
            finishOk();
            return;
        }
        long elapsed = System.currentTimeMillis() - this.pageStartedAt;
        if (elapsed < 800) {
            toast("太快啦，请按平时阅读的速度读完这一页再点");
            return;
        }
        this.pageCostMs[this.pageIndex] = elapsed;
        this.pageIndex++;
        if (this.pageIndex < 10) {
            showPage(this.pageIndex);
        } else {
            finishTest();
        }
    }

    private final void showPage(int index) {
        String[][] strArr = this.pages;
        TextView textView = null;
        if (strArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pages");
            strArr = null;
        }
        String[][] strArr2 = this.pages;
        if (strArr2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pages");
            strArr2 = null;
        }
        String[] page = strArr[index % strArr2.length];
        StringBuilder sb = new StringBuilder();
        for (String line : page) {
            sb.append(line);
            sb.append('\n');
        }
        TextView textView2 = this.pageText;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pageText");
            textView2 = null;
        }
        textView2.setText(sb.toString());
        TextView textView3 = this.pageText;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pageText");
            textView3 = null;
        }
        textView3.scrollTo(0, 0);
        TextView textView4 = this.progressText;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressText");
            textView4 = null;
        }
        textView4.setText("第 " + (index + 1) + " / 10 页");
        TextView textView5 = this.timerText;
        if (textView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("timerText");
        } else {
            textView = textView5;
        }
        textView.setText("本页已用 0.0 秒");
        this.pageStartedAt = System.currentTimeMillis();
        startTimer();
    }

    private final void startTimer() {
        stopTimer();
        Runnable runnable = new Runnable() { // from class: com.xiaofan.bangfan.SpeedTestActivity$startTimer$task$1
            @Override // java.lang.Runnable
            public void run() {
                boolean z;
                TextView textView;
                long j;
                TextView textView2;
                z = SpeedTestActivity.this.finished;
                if (z) {
                    return;
                }
                textView = SpeedTestActivity.this.timerText;
                TextView textView3 = null;
                if (textView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("timerText");
                    textView = null;
                }
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                long currentTimeMillis = System.currentTimeMillis();
                j = SpeedTestActivity.this.pageStartedAt;
                String format = String.format("本页已用 %.1f 秒", Arrays.copyOf(new Object[]{Double.valueOf((currentTimeMillis - j) / 1000.0d)}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                textView.setText(format);
                textView2 = SpeedTestActivity.this.timerText;
                if (textView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("timerText");
                } else {
                    textView3 = textView2;
                }
                textView3.postDelayed(this, 100L);
            }
        };
        this.timerTask = runnable;
        TextView textView = this.timerText;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("timerText");
            textView = null;
        }
        textView.post(runnable);
    }

    private final void stopTimer() {
        Runnable it = this.timerTask;
        if (it != null) {
            TextView textView = this.timerText;
            if (textView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("timerText");
                textView = null;
            }
            textView.removeCallbacks(it);
        }
        this.timerTask = null;
    }

    private final void finishTest() {
        long[] jArr;
        FloatBallService companion;
        stopTimer();
        this.finished = true;
        long min = Long.MAX_VALUE;
        long max = 0;
        long sum = 0;
        int count = 0;
        for (long cost : this.pageCostMs) {
            if (cost > 0) {
                count++;
                sum += cost;
                if (cost < min) {
                    min = cost;
                }
                if (cost > max) {
                    max = cost;
                }
            }
        }
        if (count == 0) {
            toast("没有采集到有效数据，请重新测速");
            this.finished = false;
            this.pageIndex = 0;
            showPage(0);
            return;
        }
        long avg = sum / count;
        AppPrefs.INSTANCE.saveSpeedResult(this, avg);
        float factor = AppPrefs.INSTANCE.turnFactor(this);
        TextView textView = this.resultText;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resultText");
            textView = null;
        }
        textView.setVisibility(0);
        double avgSec = avg / 1000.0d;
        double turnSec = (((float) avg) * factor) / 1000.0d;
        TextView textView2 = this.resultText;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resultText");
            textView2 = null;
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("测速完成\n有效样本 %d 页\n平均每页 %.1f 秒\n最快 %.1f 秒 · 最慢 %.1f 秒\n\n自动翻页间隔 = %.1f 秒（平均 × %.2f）", Arrays.copyOf(new Object[]{Integer.valueOf(count), Double.valueOf(avgSec), Double.valueOf(min / 1000.0d), Double.valueOf(max / 1000.0d), Double.valueOf(turnSec), Float.valueOf(factor)}, 6));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        textView2.setText(format);
        TextView textView3 = this.titleText;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("titleText");
            textView3 = null;
        }
        textView3.setText("测速结果");
        TextView textView4 = this.progressText;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressText");
            textView4 = null;
        }
        textView4.setText("已保存，之后可在设置里调整倍率或重新测速");
        TextView textView5 = this.timerText;
        if (textView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("timerText");
            textView5 = null;
        }
        textView5.setText("");
        TextView textView6 = this.nextBtn;
        if (textView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nextBtn");
            textView6 = null;
        }
        textView6.setText("完成，开始使用");
        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
        String format2 = String.format("测速完成啦，平均每页%.1f秒，我会每%.1f秒帮你翻一页", Arrays.copyOf(new Object[]{Double.valueOf(avgSec), Double.valueOf(turnSec)}, 2));
        Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
        XiaoFanVoice.INSTANCE.tip(this, format2);
        if (!FloatBallService.Companion.isRunning() || (companion = FloatBallService.Companion.getInstance()) == null) {
            return;
        }
        companion.onSpeedResultChanged();
    }

    private final void finishOk() {
        setResult(-1);
        finish();
    }

    private final String[][] buildPages() {
        return new String[][]{new String[]{"第一章 · 安静的早晨", "", "清晨六点，城市还没有完全醒来。", "路灯依旧亮着，橙黄色的光落在湿漉漉的马路上，", "把每一块砖缝都照得清清楚楚。", "老周推开早餐铺的卷帘门，铁皮哗啦一声响，", "惊飞了屋檐下两只麻雀。", "他习惯性地抬头看了看天，云层很厚，", "是要下雨的样子。", "蒸笼摆上灶台，白汽腾起来，", "整个铺子一下子就暖了。"}, new String[]{"第二章 · 第一笼包子", "", "面是昨晚和好的，醒了一夜，", "按下去会慢慢回弹，像有呼吸。", "老周的手很稳，擀皮、放馅、收褶，", "十八个褶子一个不多一个不少。", "他做这件事做了三十七年，", "手比脑子记得更清楚。", "第一个包子放进蒸笼的时候，", "街对面公交站台已经站了人。", "是个穿校服的女孩，背着很沉的书包，", "一边等车一边背单词。"}, new String[]{"第三章 · 常客", "", "六点半，第一个客人进门。", "是修自行车的老陈，手上还带着机油味。", "\"两个肉的，一碗稀饭。\"", "不用问，老周已经动手了。", "三十年的交情都省在这句话里。", "老陈坐下，从口袋里摸出一副老花镜，", "戴上，摊开一张报纸。", "报纸是昨天的，他每天都看昨天的，", "因为今天的要留给孙子。", "铺子里只有蒸笼的响声。"}, new String[]{"第四章 · 雨", "", "七点整，雨落下来了。", "先是几滴，打在遮阳棚上啪嗒啪嗒，", "然后就连成了线。", "路上的行人突然都跑起来，", "有人把公文包顶在头上，", "有人躲在屋檐下打电话。", "老周把蒸笼往里挪了挪，", "怕雨水溅进去。", "那个背书包的女孩跑进来，", "头发湿了大半，还在喘气。"}, new String[]{"第五章 · 一把伞", "", "\"来两个包子吧，要快的，", "我八点要考试。\"", "老周麻利地装袋，", "又从柜台下面摸出一把伞。", "伞是去年客人落下的，藏青色，", "伞骨有一根是弯的。", "\"拿着，考完还我就行。\"", "女孩愣了一下，说了声谢谢，", "撑开伞冲进了雨里。", "那把弯骨伞在雨里歪歪斜斜地走远了。"}, new String[]{"第六章 · 上午", "", "雨下到九点才停。", "铺子里的客人来来去去，", "有赶着上班的，有遛完鸟回来的，", "还有推着婴儿车的年轻母亲。", "老周的额头一直是汗，", "他用搭在肩上的毛巾擦一把，", "接着干。", "老伴坐在门口择菜，", "偶尔抬头看他一眼，", "什么也不说。"}, new String[]{"第七章 · 收音机", "", "十点，收音机里开始放评弹。", "是老周年轻时爱听的段子，", "吴侬软语，一句三拐。", "他跟着哼了两句，", "手上的活没停。", "老伴笑他：\"跑调了。\"", "\"跑调也比你强。\"", "两个人吵了一辈子，", "也这么搭了一辈子。", "蒸笼的白汽一直在升。"}, new String[]{"第八章 · 收摊", "", "十一点半，最后一笼卖完。", "老周关了火，把灶台擦干净，", "每一个动作都有条不紊。", "他数了数今天的钱，", "比昨天多了三十七块。", "他笑了笑，没跟老伴说。", "卷帘门拉下一半，", "光从底下漏进来，", "在地上切出一条亮线。", "铺子安静下来了。"}, new String[]{"第九章 · 那把伞", "", "下午三点，女孩来了。", "她换了一身干衣服，", "手里拎着那把藏青色的伞。", "\"叔叔，还您。\"", "伞是干的，叠得整整齐齐。", "\"考得怎么样？\"", "女孩笑了：\"还行。\"", "老周给她装了两个包子，", "死活不肯收钱。", "女孩站在门口鞠了一躬才走。"}, new String[]{"第十章 · 灯", "", "天黑得早，五点就亮灯了。", "老周把明天要用的面和好，", "盖上湿布，让它慢慢醒。", "他坐在门口的塑料凳上，", "看街上的人流慢慢稀下去。", "老伴在里面喊他吃饭。", "他应了一声，没动。", "再坐一会儿吧，", "明天六点又要起来了。", "路灯亮了，还是那个橙黄色。"}};
    }

    private final int dp(float value) {
        return Math.round(TypedValue.applyDimension(1, value, getResources().getDisplayMetrics()));
    }

    private final void toast(String msg) {
        Toast.makeText(this, msg, 0).show();
    }

    @Override // android.app.Activity
    @Deprecated(message = "Deprecated in Java")
    public void onBackPressed() {
        if (!this.finished && this.pageIndex > 0) {
            toast("测速未完成，已放弃本次数据（原设置保持不变）");
        }
        super.onBackPressed();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        stopTimer();
        super.onDestroy();
    }
}
