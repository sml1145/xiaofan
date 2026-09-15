package com.xiaofan.bangfan;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.view.GravityCompat;
import com.xiaofan.bangfan.WeatherManager;
import com.xiaofan.bangfan.XiaoFanAlarm;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
/* compiled from: FunctionPanelActivity.kt */
@Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u00002\u00020\u0001:\u0001SB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u0007H\u0002J\u0010\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0010\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020\u001eH\u0002J\u0010\u0010#\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J(\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u001e2\u0006\u0010'\u001a\u00020\u001e2\u0006\u0010(\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020\u001eH\u0002J\u0010\u0010*\u001a\u00020\u001c2\u0006\u0010+\u001a\u00020\u001cH\u0002J\u0012\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010/H\u0014J\b\u00100\u001a\u00020-H\u0014J\b\u00101\u001a\u00020-H\u0014J-\u00102\u001a\u00020-2\u0006\u00103\u001a\u00020\u001e2\u000e\u00104\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001c052\u0006\u00106\u001a\u000207H\u0016¢\u0006\u0002\u00108J\b\u00109\u001a\u00020-H\u0014J\u0014\u0010:\u001a\u0004\u0018\u0001072\b\u0010;\u001a\u0004\u0018\u00010\u001cH\u0002J\b\u0010<\u001a\u00020-H\u0002J\u0010\u0010=\u001a\u00020-2\u0006\u0010>\u001a\u00020?H\u0002J0\u0010@\u001a\u00020-2\f\u0010A\u001a\b\u0012\u0004\u0012\u00020C0B2\b\u0010D\u001a\u0004\u0018\u00010E2\u0006\u0010F\u001a\u00020\u001c2\u0006\u0010G\u001a\u00020?H\u0002J\u0010\u0010H\u001a\u00020-2\u0006\u0010I\u001a\u00020\u001cH\u0002J\b\u0010J\u001a\u00020-H\u0002J\b\u0010K\u001a\u00020-H\u0002J\b\u0010L\u001a\u00020-H\u0002J\b\u0010M\u001a\u00020-H\u0002J\b\u0010N\u001a\u00020-H\u0002J\b\u0010O\u001a\u00020-H\u0002J\u0010\u0010P\u001a\u00020-2\u0006\u0010I\u001a\u00020\u001cH\u0002J\u0010\u0010Q\u001a\u00020\u001c2\u0006\u0010R\u001a\u00020\u001eH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006T"}, d2 = {"Lcom/xiaofan/bangfan/FunctionPanelActivity;", "Lcom/xiaofan/bangfan/BaseActivity;", "()V", "alarmAddBtn", "Landroid/widget/TextView;", "alarmCount", "alarmList", "Landroid/widget/LinearLayout;", "clock", "Lcom/xiaofan/bangfan/FunctionPanelActivity$ClockView;", "clockTask", "Ljava/lang/Runnable;", "dateText", "digitalTime", "exactAlarmWarn", "main", "Landroid/os/Handler;", "mascot", "Lcom/xiaofan/bangfan/MascotView;", "mascotHint", "weatherCityBtn", "weatherList", "weatherTitle", "buildUi", "Landroid/view/View;", "card", "cardTitle", "text", "", "dp", "", "value", "", "getColorCompat", "id", "labelOf", "marginParams", "Landroid/widget/LinearLayout$LayoutParams;", "left", "top", "right", "bottom", "normalizeDigits", "s", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPause", "onRequestPermissionsResult", "requestCode", "permissions", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onResume", "parseTime", "input", "refreshAlarms", "refreshWeather", "force", "", "renderWeather", "days", "", "Lcom/xiaofan/bangfan/WeatherManager$Day;", "current", "Lcom/xiaofan/bangfan/WeatherManager$Current;", "place", "cached", "renderWeatherError", NotificationCompat.CATEGORY_MESSAGE, "requestLocationIfNeeded", "showAddAlarmDialog", "showCityDialog", "startClock", "stopClock", "tickClock", "toast", "weatherEmoji", "code", "ClockView", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class FunctionPanelActivity extends BaseActivity {
    private TextView alarmAddBtn;
    private TextView alarmCount;
    private LinearLayout alarmList;
    private ClockView clock;
    private Runnable clockTask;
    private TextView dateText;
    private TextView digitalTime;
    private TextView exactAlarmWarn;
    private final Handler main = new Handler(Looper.getMainLooper());
    private MascotView mascot;
    private TextView mascotHint;
    private TextView weatherCityBtn;
    private LinearLayout weatherList;
    private TextView weatherTitle;

    private final String weatherEmoji(int code) {
        if (code != 0) {
            boolean z = true;
            if (code != 1) {
                if (code == 2) {
                    return "⛅";
                }
                if (code == 3) {
                    return "☁";
                }
                if (code == 45 || code == 48) {
                    return "🌫";
                }
                if (51 <= code && code < 68) {
                    return "🌧";
                }
                if (71 > code || code >= 87) {
                    z = false;
                }
                return z ? "🌨" : code >= 95 ? "⛈" : "🌤";
            }
        }
        return "☀";
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(buildUi());
        requestLocationIfNeeded();
        refreshAlarms();
        refreshWeather(false);
        startClock();
        XiaoFanAlarm.INSTANCE.rescheduleAll(this);
        MascotView mascotView = this.mascot;
        if (mascotView != null) {
            mascotView.setSpeaking(true);
        }
        MascotView mascotView2 = this.mascot;
        if (mascotView2 != null) {
            mascotView2.setSpeaking(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaofan.bangfan.BaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        MascotView mascotView = this.mascot;
        if (mascotView != null) {
            mascotView.applyVisualState(this);
        }
        startClock();
        refreshAlarms();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaofan.bangfan.BaseActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        stopClock();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        stopClock();
        super.onDestroy();
    }

    private final View buildUi() {
        ScrollView scroll = new ScrollView(this);
        scroll.setBackgroundColor(getColorCompat(R.color.bg));
        scroll.setFillViewport(true);
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(1);
        root.setPadding(dp(18.0f), dp(20.0f), dp(18.0f), dp(28.0f));
        LinearLayout header = new LinearLayout(this);
        header.setOrientation(0);
        header.setGravity(16);
        TextView title = new TextView(this);
        title.setText("小翻功能表");
        title.setTextColor(getColorCompat(R.color.ink));
        title.setTextSize(2, 24.0f);
        title.getPaint().setFakeBoldText(true);
        header.addView(title, new LinearLayout.LayoutParams(0, -2, 1.0f));
        TextView close = new TextView(this);
        close.setText("收起");
        close.setTextColor(getColorCompat(R.color.muted));
        close.setTextSize(2, 13.0f);
        close.setPadding(dp(10.0f), dp(6.0f), 0, dp(6.0f));
        close.setOnClickListener(new View.OnClickListener() { // from class: com.xiaofan.bangfan.FunctionPanelActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FunctionPanelActivity.buildUi$lambda$0(FunctionPanelActivity.this, view);
            }
        });
        header.addView(close);
        root.addView(header);
        LinearLayout mascotCard = card();
        this.mascot = new MascotView(this, null, 2, null);
        LinearLayout.LayoutParams mascotLp = new LinearLayout.LayoutParams(dp(150.0f), dp(150.0f));
        mascotLp.gravity = 17;
        mascotCard.addView(this.mascot, mascotLp);
        this.mascotHint = new TextView(this);
        TextView textView = this.mascotHint;
        Intrinsics.checkNotNull(textView);
        textView.setText("点我一下试试");
        TextView textView2 = this.mascotHint;
        Intrinsics.checkNotNull(textView2);
        textView2.setTextColor(getColorCompat(R.color.muted));
        TextView textView3 = this.mascotHint;
        Intrinsics.checkNotNull(textView3);
        textView3.setTextSize(2, 12.0f);
        TextView textView4 = this.mascotHint;
        Intrinsics.checkNotNull(textView4);
        textView4.setGravity(17);
        TextView textView5 = this.mascotHint;
        Intrinsics.checkNotNull(textView5);
        textView5.setPadding(0, dp(6.0f), 0, 0);
        mascotCard.addView(this.mascotHint);
        MascotView mascotView = this.mascot;
        Intrinsics.checkNotNull(mascotView);
        mascotView.setMascotListener(new FunctionPanelActivity$buildUi$2(this));
        root.addView(mascotCard, marginParams(0, dp(10.0f), 0, 0));
        LinearLayout clockCard = card();
        clockCard.addView(cardTitle("北京时间"));
        LinearLayout clockRow = new LinearLayout(this);
        clockRow.setOrientation(0);
        clockRow.setGravity(16);
        this.clock = new ClockView(this);
        clockRow.addView(this.clock, new LinearLayout.LayoutParams(dp(116.0f), dp(116.0f)));
        LinearLayout timeCol = new LinearLayout(this);
        timeCol.setOrientation(1);
        timeCol.setPadding(dp(16.0f), 0, 0, 0);
        this.digitalTime = new TextView(this);
        TextView textView6 = this.digitalTime;
        Intrinsics.checkNotNull(textView6);
        textView6.setTextColor(getColorCompat(R.color.ink));
        TextView textView7 = this.digitalTime;
        Intrinsics.checkNotNull(textView7);
        textView7.setTextSize(2, 27.0f);
        TextView textView8 = this.digitalTime;
        Intrinsics.checkNotNull(textView8);
        textView8.getPaint().setFakeBoldText(true);
        timeCol.addView(this.digitalTime);
        this.dateText = new TextView(this);
        TextView textView9 = this.dateText;
        Intrinsics.checkNotNull(textView9);
        textView9.setTextColor(getColorCompat(R.color.muted));
        TextView textView10 = this.dateText;
        Intrinsics.checkNotNull(textView10);
        textView10.setTextSize(2, 13.0f);
        TextView textView11 = this.dateText;
        Intrinsics.checkNotNull(textView11);
        textView11.setPadding(0, dp(4.0f), 0, 0);
        timeCol.addView(this.dateText);
        TextView tz = new TextView(this);
        tz.setText("Asia/Shanghai · UTC+8");
        tz.setTextColor(getColorCompat(R.color.brand));
        tz.setTextSize(2, 11.0f);
        tz.setPadding(0, dp(6.0f), 0, 0);
        timeCol.addView(tz);
        clockRow.addView(timeCol, new LinearLayout.LayoutParams(0, -2, 1.0f));
        clockCard.addView(clockRow, marginParams(0, dp(10.0f), 0, 0));
        root.addView(clockCard, marginParams(0, dp(12.0f), 0, 0));
        LinearLayout weatherCard = card();
        LinearLayout weatherHeader = new LinearLayout(this);
        weatherHeader.setOrientation(0);
        weatherHeader.setGravity(16);
        this.weatherTitle = cardTitle("一周天气");
        weatherHeader.addView(this.weatherTitle, new LinearLayout.LayoutParams(0, -2, 1.0f));
        this.weatherCityBtn = new TextView(this);
        TextView textView12 = this.weatherCityBtn;
        Intrinsics.checkNotNull(textView12);
        textView12.setText("设置城市");
        TextView textView13 = this.weatherCityBtn;
        Intrinsics.checkNotNull(textView13);
        textView13.setTextColor(getColorCompat(R.color.brand));
        TextView textView14 = this.weatherCityBtn;
        Intrinsics.checkNotNull(textView14);
        textView14.setTextSize(2, 13.0f);
        TextView textView15 = this.weatherCityBtn;
        Intrinsics.checkNotNull(textView15);
        textView15.setPadding(dp(10.0f), dp(4.0f), 0, dp(4.0f));
        TextView textView16 = this.weatherCityBtn;
        Intrinsics.checkNotNull(textView16);
        textView16.setOnClickListener(new View.OnClickListener() { // from class: com.xiaofan.bangfan.FunctionPanelActivity$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FunctionPanelActivity.buildUi$lambda$1(FunctionPanelActivity.this, view);
            }
        });
        weatherHeader.addView(this.weatherCityBtn);
        TextView refresh = new TextView(this);
        refresh.setText("刷新");
        refresh.setTextColor(getColorCompat(R.color.muted));
        refresh.setTextSize(2, 13.0f);
        refresh.setPadding(dp(14.0f), dp(4.0f), 0, dp(4.0f));
        refresh.setOnClickListener(new View.OnClickListener() { // from class: com.xiaofan.bangfan.FunctionPanelActivity$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FunctionPanelActivity.buildUi$lambda$2(FunctionPanelActivity.this, view);
            }
        });
        weatherHeader.addView(refresh);
        weatherCard.addView(weatherHeader);
        this.weatherList = new LinearLayout(this);
        LinearLayout linearLayout = this.weatherList;
        Intrinsics.checkNotNull(linearLayout);
        linearLayout.setOrientation(1);
        weatherCard.addView(this.weatherList, marginParams(0, dp(8.0f), 0, 0));
        root.addView(weatherCard, marginParams(0, dp(12.0f), 0, 0));
        LinearLayout alarmCard = card();
        LinearLayout alarmHeader = new LinearLayout(this);
        alarmHeader.setOrientation(0);
        alarmHeader.setGravity(16);
        LinearLayout alarmTitleCol = new LinearLayout(this);
        alarmTitleCol.setOrientation(1);
        alarmTitleCol.addView(cardTitle("闹钟"));
        this.alarmCount = new TextView(this);
        TextView textView17 = this.alarmCount;
        Intrinsics.checkNotNull(textView17);
        textView17.setTextColor(getColorCompat(R.color.muted));
        TextView textView18 = this.alarmCount;
        Intrinsics.checkNotNull(textView18);
        textView18.setTextSize(2, 11.5f);
        alarmTitleCol.addView(this.alarmCount);
        alarmHeader.addView(alarmTitleCol, new LinearLayout.LayoutParams(0, -2, 1.0f));
        this.alarmAddBtn = new TextView(this);
        TextView textView19 = this.alarmAddBtn;
        Intrinsics.checkNotNull(textView19);
        textView19.setText("+ 新建");
        TextView textView20 = this.alarmAddBtn;
        Intrinsics.checkNotNull(textView20);
        textView20.setTextColor(-1);
        TextView textView21 = this.alarmAddBtn;
        Intrinsics.checkNotNull(textView21);
        textView21.setTextSize(2, 13.0f);
        TextView textView22 = this.alarmAddBtn;
        Intrinsics.checkNotNull(textView22);
        textView22.setPadding(dp(14.0f), dp(7.0f), dp(14.0f), dp(7.0f));
        GradientDrawable btnBg = new GradientDrawable();
        btnBg.setColor(getColorCompat(R.color.brand));
        btnBg.setCornerRadius(dp(20.0f));
        TextView textView23 = this.alarmAddBtn;
        Intrinsics.checkNotNull(textView23);
        textView23.setBackground(btnBg);
        TextView textView24 = this.alarmAddBtn;
        Intrinsics.checkNotNull(textView24);
        textView24.setOnClickListener(new View.OnClickListener() { // from class: com.xiaofan.bangfan.FunctionPanelActivity$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FunctionPanelActivity.buildUi$lambda$3(FunctionPanelActivity.this, view);
            }
        });
        alarmHeader.addView(this.alarmAddBtn);
        alarmCard.addView(alarmHeader);
        this.exactAlarmWarn = new TextView(this);
        TextView textView25 = this.exactAlarmWarn;
        Intrinsics.checkNotNull(textView25);
        textView25.setText("系统限制了精确闹钟，可能会晚一点响。点这里去开启");
        TextView textView26 = this.exactAlarmWarn;
        Intrinsics.checkNotNull(textView26);
        textView26.setTextColor(getColorCompat(R.color.bad));
        TextView textView27 = this.exactAlarmWarn;
        Intrinsics.checkNotNull(textView27);
        textView27.setTextSize(2, 12.0f);
        TextView textView28 = this.exactAlarmWarn;
        Intrinsics.checkNotNull(textView28);
        textView28.setPadding(0, dp(8.0f), 0, 0);
        TextView textView29 = this.exactAlarmWarn;
        Intrinsics.checkNotNull(textView29);
        textView29.setVisibility(8);
        TextView textView30 = this.exactAlarmWarn;
        Intrinsics.checkNotNull(textView30);
        textView30.setOnClickListener(new View.OnClickListener() { // from class: com.xiaofan.bangfan.FunctionPanelActivity$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FunctionPanelActivity.buildUi$lambda$4(FunctionPanelActivity.this, view);
            }
        });
        alarmCard.addView(this.exactAlarmWarn);
        this.alarmList = new LinearLayout(this);
        LinearLayout linearLayout2 = this.alarmList;
        Intrinsics.checkNotNull(linearLayout2);
        linearLayout2.setOrientation(1);
        alarmCard.addView(this.alarmList, marginParams(0, dp(8.0f), 0, 0));
        root.addView(alarmCard, marginParams(0, dp(12.0f), 0, 0));
        scroll.addView(root, new FrameLayout.LayoutParams(-1, -2));
        return scroll;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void buildUi$lambda$0(FunctionPanelActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void buildUi$lambda$1(FunctionPanelActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showCityDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void buildUi$lambda$2(FunctionPanelActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.refreshWeather(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void buildUi$lambda$3(FunctionPanelActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showAddAlarmDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void buildUi$lambda$4(FunctionPanelActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        XiaoFanAlarm.INSTANCE.openExactAlarmSettings(this$0);
    }

    private final void startClock() {
        stopClock();
        Runnable runnable = new Runnable() { // from class: com.xiaofan.bangfan.FunctionPanelActivity$startClock$task$1
            @Override // java.lang.Runnable
            public void run() {
                Handler handler;
                FunctionPanelActivity.this.tickClock();
                handler = FunctionPanelActivity.this.main;
                handler.postDelayed(this, 100L);
            }
        };
        this.clockTask = runnable;
        this.main.post(runnable);
    }

    private final void stopClock() {
        Runnable it = this.clockTask;
        if (it != null) {
            this.main.removeCallbacks(it);
        }
        this.clockTask = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void tickClock() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Shanghai"));
        int h = cal.get(11);
        int m = cal.get(12);
        int s = cal.get(13);
        int ms = cal.get(14);
        TextView textView = this.digitalTime;
        if (textView != null) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format(Locale.CHINA, "%02d:%02d:%02d", Arrays.copyOf(new Object[]{Integer.valueOf(h), Integer.valueOf(m), Integer.valueOf(s)}, 3));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            textView.setText(format);
        }
        TextView textView2 = this.dateText;
        if (textView2 != null) {
            textView2.setText(new SimpleDateFormat("yyyy年M月d日 EEEE", Locale.CHINA).format(cal.getTime()));
        }
        ClockView clockView = this.clock;
        if (clockView != null) {
            clockView.setTime(h, m, s, ms);
        }
    }

    /* compiled from: FunctionPanelActivity.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J8\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u0006H\u0002J\u0010\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0014J&\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/xiaofan/bangfan/FunctionPanelActivity$ClockView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "face", "Landroid/graphics/Paint;", "h", "", "hourHand", "hub", "m", "minHand", "ms", "rim", "s", "secHand", "tick", "drawHand", "", "canvas", "Landroid/graphics/Canvas;", "cx", "", "cy", "angle", "len", "paint", "onDraw", "setTime", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class ClockView extends View {
        private final Paint face;
        private int h;
        private final Paint hourHand;
        private final Paint hub;
        private int m;
        private final Paint minHand;
        private int ms;
        private final Paint rim;
        private int s;
        private final Paint secHand;
        private final Paint tick;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ClockView(Context context) {
            super(context);
            Intrinsics.checkNotNullParameter(context, "context");
            this.face = new Paint(1);
            this.rim = new Paint(1);
            this.tick = new Paint(1);
            this.hourHand = new Paint(1);
            this.minHand = new Paint(1);
            this.secHand = new Paint(1);
            this.hub = new Paint(1);
            float density = context.getResources().getDisplayMetrics().density;
            this.face.setColor(-1);
            this.face.setStyle(Paint.Style.FILL);
            this.rim.setColor(-13253400);
            this.rim.setStyle(Paint.Style.STROKE);
            this.rim.setStrokeWidth(3.0f * density);
            this.tick.setColor(-7694426);
            this.tick.setStrokeWidth(1.6f * density);
            this.hourHand.setColor(-12958124);
            this.hourHand.setStyle(Paint.Style.STROKE);
            this.hourHand.setStrokeWidth(5.0f * density);
            this.hourHand.setStrokeCap(Paint.Cap.ROUND);
            this.minHand.setColor(-12958124);
            this.minHand.setStyle(Paint.Style.STROKE);
            this.minHand.setStrokeWidth(3.4f * density);
            this.minHand.setStrokeCap(Paint.Cap.ROUND);
            this.secHand.setColor(-1021016);
            this.secHand.setStyle(Paint.Style.STROKE);
            this.secHand.setStrokeWidth(1.8f * density);
            this.secHand.setStrokeCap(Paint.Cap.ROUND);
            this.hub.setColor(-13253400);
            this.hub.setStyle(Paint.Style.FILL);
        }

        public final void setTime(int h, int m, int s, int ms) {
            this.h = h;
            this.m = m;
            this.s = s;
            this.ms = ms;
            invalidate();
        }

        @Override // android.view.View
        protected void onDraw(Canvas canvas) {
            Intrinsics.checkNotNullParameter(canvas, "canvas");
            float w = getWidth();
            float hgt = getHeight();
            float cx = w / 2.0f;
            float cy = hgt / 2.0f;
            float r = (Math.min(w, hgt) / 2.0f) - this.rim.getStrokeWidth();
            if (r <= 0.0f) {
                return;
            }
            canvas.drawCircle(cx, cy, r, this.face);
            canvas.drawCircle(cx, cy, r, this.rim);
            for (int i = 0; i < 60; i++) {
                double angle = Math.toRadians((i * 6) - 90);
                boolean major = i % 5 == 0;
                float len = (major ? 0.13f : 0.06f) * r;
                this.tick.setStrokeWidth(this.tick.getStrokeWidth() * (major ? 1.6f : 0.7f));
                float x1 = cx + ((float) (Math.cos(angle) * (r - len)));
                float y1 = cy + ((float) (Math.sin(angle) * (r - len)));
                float x2 = cx + ((float) (Math.cos(angle) * (r - (r * 0.02f))));
                float y2 = cy + ((float) (Math.sin(angle) * (r - (0.02f * r))));
                canvas.drawLine(x1, y1, x2, y2, this.tick);
            }
            drawHand(canvas, cx, cy, ((this.h % 12) + (this.m / 60.0f)) * 30.0f, r * 0.5f, this.hourHand);
            drawHand(canvas, cx, cy, (this.m + (this.s / 60.0f)) * 6.0f, r * 0.72f, this.minHand);
            drawHand(canvas, cx, cy, (this.s + (this.ms / 1000.0f)) * 6.0f, r * 0.8f, this.secHand);
            canvas.drawCircle(cx, cy, 0.055f * r, this.hub);
        }

        private final void drawHand(Canvas canvas, float cx, float cy, float angle, float len, Paint paint) {
            double rad = Math.toRadians(angle - 90);
            double d = 0.18f;
            double x1 = cx - ((Math.cos(rad) * len) * d);
            double y1 = cy - ((Math.sin(rad) * len) * d);
            double x2 = cx + (Math.cos(rad) * len);
            double y2 = cy + (Math.sin(rad) * len);
            canvas.drawLine((float) x1, (float) y1, (float) x2, (float) y2, paint);
        }
    }

    private final void requestLocationIfNeeded() {
        String city = WeatherManager.INSTANCE.manualCity(this);
        if (checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0 || checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0) {
            return;
        }
        if (city.length() > 0) {
            return;
        }
        requestPermissions(new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, 401);
    }

    private final void refreshWeather(boolean force) {
        LinearLayout list = this.weatherList;
        if (list == null) {
            return;
        }
        list.removeAllViews();
        TextView loading = new TextView(this);
        loading.setText("正在获取天气…");
        loading.setTextColor(getColorCompat(R.color.muted));
        loading.setTextSize(2, 12.5f);
        list.addView(loading);
        WeatherManager.INSTANCE.fetchWeek(this, new FunctionPanelActivity$refreshWeather$1(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void renderWeather(List<WeatherManager.Day> list, WeatherManager.Current current, String place, boolean cached) {
        String str;
        int i;
        LinearLayout list2 = this.weatherList;
        if (list2 == null) {
            return;
        }
        TextView textView = this.weatherTitle;
        if (textView != null) {
            textView.setText("一周天气 · " + place + (cached ? "（缓存）" : ""));
        }
        list2.removeAllViews();
        String str2 = "%";
        int i2 = 0;
        if (current != null) {
            LinearLayout row = new LinearLayout(this);
            row.setOrientation(0);
            row.setGravity(16);
            GradientDrawable bg = new GradientDrawable();
            bg.setColor(-1378309);
            bg.setCornerRadius(dp(12.0f));
            row.setBackground(bg);
            row.setPadding(dp(14.0f), dp(12.0f), dp(14.0f), dp(12.0f));
            TextView emoji = new TextView(this);
            emoji.setText(weatherEmoji(current.getCode()));
            emoji.setTextSize(2, 30.0f);
            row.addView(emoji, new LinearLayout.LayoutParams(dp(44.0f), -2));
            LinearLayout col = new LinearLayout(this);
            col.setOrientation(1);
            TextView temp = new TextView(this);
            temp.setText(Math.round(current.getTemp()) + "℃  " + current.getText());
            temp.setTextColor(getColorCompat(R.color.ink));
            temp.setTextSize(2, 19.0f);
            temp.getPaint().setFakeBoldText(true);
            col.addView(temp);
            StringBuilder detail = new StringBuilder("体感 " + Math.round(current.getFeelsLike()) + "℃");
            if (current.getHumidity() > 0) {
                detail.append(" · 湿度 " + current.getHumidity() + "%");
            }
            if (current.getWind() > 0.0f) {
                detail.append(" · 风 " + Math.round(current.getWind()) + "km/h");
            }
            if (current.getPrecipProb() >= 30) {
                detail.append(" · 降水 " + current.getPrecipProb() + "%");
            }
            TextView detailTv = new TextView(this);
            detailTv.setText(detail.toString());
            detailTv.setTextColor(getColorCompat(R.color.muted));
            detailTv.setTextSize(2, 12.0f);
            col.addView(detailTv);
            row.addView(col, new LinearLayout.LayoutParams(0, -2, 1.0f));
            list2.addView(row, marginParams(0, 0, 0, dp(8.0f)));
        }
        int i3 = 0;
        int size = list.size();
        while (i3 < size) {
            WeatherManager.Day day = list.get(i3);
            LinearLayout row2 = new LinearLayout(this);
            row2.setOrientation(i2);
            row2.setGravity(16);
            row2.setPadding(i2, dp(5.0f), i2, dp(5.0f));
            TextView dayLabel = new TextView(this);
            switch (i3) {
                case 0:
                    str = "今天";
                    break;
                case 1:
                    str = "明天";
                    break;
                default:
                    str = day.getWeekCn();
                    break;
            }
            dayLabel.setText(str);
            dayLabel.setTextColor(getColorCompat(R.color.ink));
            dayLabel.setTextSize(2, 13.5f);
            row2.addView(dayLabel, new LinearLayout.LayoutParams(dp(48.0f), -2));
            TextView dayEmoji = new TextView(this);
            dayEmoji.setText(weatherEmoji(day.getCode()));
            dayEmoji.setTextSize(2, 16.0f);
            row2.addView(dayEmoji, new LinearLayout.LayoutParams(dp(28.0f), -2));
            LinearLayout col2 = new LinearLayout(this);
            col2.setOrientation(1);
            TextView text = new TextView(this);
            text.setText(day.getText());
            text.setTextColor(getColorCompat(R.color.muted));
            text.setTextSize(2, 12.5f);
            col2.addView(text);
            if (day.getPrecipProb() < 20) {
                i = size;
            } else {
                TextView prob = new TextView(this);
                i = size;
                prob.setText("降水 " + day.getPrecipProb() + str2);
                prob.setTextColor(getColorCompat(R.color.brand_dark));
                prob.setTextSize(2, 10.5f);
                col2.addView(prob);
            }
            row2.addView(col2, new LinearLayout.LayoutParams(0, -2, 1.0f));
            TextView temps = new TextView(this);
            temps.setText(Math.round(day.getTempMin()) + "° / " + Math.round(day.getTempMax()) + "°");
            temps.setTextColor(getColorCompat(R.color.brand));
            temps.setTextSize(2, 13.5f);
            temps.getPaint().setFakeBoldText(true);
            temps.setGravity(GravityCompat.END);
            row2.addView(temps, new LinearLayout.LayoutParams(dp(80.0f), -2));
            list2.addView(row2);
            i3++;
            str2 = str2;
            size = i;
            i2 = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void renderWeatherError(String msg) {
        LinearLayout list = this.weatherList;
        if (list == null) {
            return;
        }
        list.removeAllViews();
        TextView err = new TextView(this);
        err.setText(msg);
        err.setTextColor(getColorCompat(R.color.bad));
        err.setTextSize(2, 12.5f);
        err.setLineSpacing(0.0f, 1.4f);
        list.addView(err);
        TextView retry = new TextView(this);
        retry.setText("填写城市后重试");
        retry.setTextColor(getColorCompat(R.color.brand));
        retry.setTextSize(2, 13.0f);
        retry.setPadding(0, dp(10.0f), 0, 0);
        retry.setOnClickListener(new View.OnClickListener() { // from class: com.xiaofan.bangfan.FunctionPanelActivity$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FunctionPanelActivity.renderWeatherError$lambda$6(FunctionPanelActivity.this, view);
            }
        });
        list.addView(retry);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void renderWeatherError$lambda$6(FunctionPanelActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showCityDialog();
    }

    private final void showCityDialog() {
        final EditText input = new EditText(this);
        input.setHint("例如：杭州 / 北京 / Shanghai");
        input.setInputType(1);
        input.setText(WeatherManager.INSTANCE.manualCity(this));
        input.setSelection(input.getText().length());
        input.setPadding(dp(16.0f), dp(12.0f), dp(16.0f), dp(12.0f));
        LinearLayout container = new LinearLayout(this);
        container.setOrientation(1);
        container.setPadding(dp(20.0f), dp(8.0f), dp(20.0f), 0);
        TextView hint = new TextView(this);
        hint.setText("不想给定位权限的话，填个大致城市也能查天气。");
        hint.setTextColor(getColorCompat(R.color.muted));
        hint.setTextSize(2, 12.0f);
        container.addView(hint);
        container.addView(input);
        new AlertDialog.Builder(this).setTitle("设置城市").setView(container).setPositiveButton("获取天气", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.FunctionPanelActivity$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                FunctionPanelActivity.showCityDialog$lambda$7(FunctionPanelActivity.this, input, dialogInterface, i);
            }
        }).setNeutralButton("用定位", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.FunctionPanelActivity$$ExternalSyntheticLambda3
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                FunctionPanelActivity.showCityDialog$lambda$8(FunctionPanelActivity.this, dialogInterface, i);
            }
        }).setNegativeButton("取消", (DialogInterface.OnClickListener) null).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showCityDialog$lambda$7(FunctionPanelActivity this$0, EditText input, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(input, "$input");
        WeatherManager.INSTANCE.setManualCity(this$0, StringsKt.trim((CharSequence) input.getText().toString()).toString());
        this$0.refreshWeather(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showCityDialog$lambda$8(FunctionPanelActivity this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.requestPermissions(new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, 401);
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != 401) {
            return;
        }
        int length = grantResults.length;
        int[] iArr = null;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            int element$iv = grantResults[i];
            int it = element$iv == 0 ? 1 : 0;
            if (it != 0) {
                iArr = 1;
                break;
            }
            i++;
        }
        int[] $this$any$iv = iArr;
        if ($this$any$iv == null) {
            showCityDialog();
            return;
        }
        toast("已获取定位，正在查询当地天气");
        refreshWeather(true);
    }

    private final void refreshAlarms() {
        LinearLayout list = this.alarmList;
        if (list == null) {
            return;
        }
        list.removeAllViews();
        List items = XiaoFanAlarm.INSTANCE.load(this);
        XiaoFanAlarm.Item next = XiaoFanAlarm.INSTANCE.nextUpcoming(this);
        TextView textView = this.alarmCount;
        if (textView != null) {
            textView.setText(next == null ? "暂无生效的闹钟" : "下一个：" + next.timeText() + "（" + XiaoFanAlarm.INSTANCE.remainingText(next.getNextAt()) + "）");
        }
        TextView textView2 = this.exactAlarmWarn;
        int i = 0;
        if (textView2 != null) {
            textView2.setVisibility(XiaoFanAlarm.INSTANCE.canScheduleExact(this) ? 8 : 0);
        }
        int i2 = 2;
        if (items.isEmpty()) {
            TextView empty = new TextView(this);
            empty.setText("还没有闹钟\n点「+ 新建」添加闹钟");
            empty.setTextColor(getColorCompat(R.color.muted));
            empty.setTextSize(2, 12.5f);
            empty.setLineSpacing(0.0f, 1.4f);
            list.addView(empty);
            return;
        }
        for (final XiaoFanAlarm.Item item : items) {
            LinearLayout row = new LinearLayout(this);
            row.setOrientation(i);
            row.setGravity(16);
            row.setPadding(dp(10.0f), dp(9.0f), dp(6.0f), dp(9.0f));
            GradientDrawable bg = new GradientDrawable();
            bg.setColor(getColorCompat(R.color.bg));
            bg.setCornerRadius(dp(10.0f));
            row.setBackground(bg);
            LinearLayout col = new LinearLayout(this);
            col.setOrientation(1);
            TextView time = new TextView(this);
            time.setText(item.timeText());
            time.setTextSize(i2, 19.0f);
            time.getPaint().setFakeBoldText(true);
            time.setTextColor(getColorCompat(item.getEnabled() ? R.color.ink : R.color.muted));
            col.addView(time);
            StringBuilder detail = new StringBuilder();
            if ((item.getLabel().length() <= 0 ? i : 1) != 0) {
                detail.append(item.getLabel() + " · ");
            }
            detail.append(item.repeatText());
            if (!item.getEnabled()) {
                detail.append(" · 已停用");
            }
            TextView detailTv = new TextView(this);
            detailTv.setText(detail.toString());
            detailTv.setTextColor(getColorCompat(R.color.muted));
            detailTv.setTextSize(i2, 11.5f);
            col.addView(detailTv);
            List items2 = items;
            row.addView(col, new LinearLayout.LayoutParams(i, -2, 1.0f));
            TextView toggle = new TextView(this);
            toggle.setText(item.getEnabled() ? "关闭" : "开启");
            toggle.setTextColor(getColorCompat(item.getEnabled() ? R.color.muted : R.color.ok));
            toggle.setTextSize(2, 13.0f);
            XiaoFanAlarm.Item next2 = next;
            toggle.setPadding(dp(12.0f), dp(6.0f), dp(6.0f), dp(6.0f));
            toggle.setOnClickListener(new View.OnClickListener() { // from class: com.xiaofan.bangfan.FunctionPanelActivity$$ExternalSyntheticLambda10
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FunctionPanelActivity.refreshAlarms$lambda$10(FunctionPanelActivity.this, item, view);
                }
            });
            row.addView(toggle);
            TextView del = new TextView(this);
            del.setText("删除");
            del.setTextColor(getColorCompat(R.color.bad));
            del.setTextSize(2, 13.0f);
            del.setPadding(dp(12.0f), dp(6.0f), dp(6.0f), dp(6.0f));
            del.setOnClickListener(new View.OnClickListener() { // from class: com.xiaofan.bangfan.FunctionPanelActivity$$ExternalSyntheticLambda11
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FunctionPanelActivity.refreshAlarms$lambda$11(FunctionPanelActivity.this, item, view);
                }
            });
            row.addView(del);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, -2);
            lp.bottomMargin = dp(8.0f);
            list.addView(row, lp);
            items = items2;
            next = next2;
            i = 0;
            i2 = 2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void refreshAlarms$lambda$10(FunctionPanelActivity this$0, XiaoFanAlarm.Item item, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(item, "$item");
        XiaoFanAlarm.INSTANCE.setEnabled(this$0, item.getId(), !item.getEnabled());
        this$0.refreshAlarms();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void refreshAlarms$lambda$11(FunctionPanelActivity this$0, XiaoFanAlarm.Item item, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(item, "$item");
        XiaoFanAlarm.INSTANCE.remove(this$0, item.getId());
        this$0.refreshAlarms();
        this$0.toast("已删除 " + item.timeText() + " 的闹钟");
    }

    private final void showAddAlarmDialog() {
        LinearLayout container = new LinearLayout(this);
        container.setOrientation(1);
        container.setPadding(dp(20.0f), dp(8.0f), dp(20.0f), 0);
        final EditText timeInput = new EditText(this);
        timeInput.setHint("时间，例如 07:30 或 07:30:15");
        timeInput.setInputType(1);
        Calendar cal = Calendar.getInstance();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(Locale.CHINA, "%02d:%02d", Arrays.copyOf(new Object[]{Integer.valueOf(cal.get(11)), Integer.valueOf(cal.get(12))}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        timeInput.setText(format);
        timeInput.setSelection(timeInput.getText().length());
        container.addView(labelOf("响铃时间（24 小时制，可带秒）"));
        container.addView(timeInput);
        final EditText labelInput = new EditText(this);
        labelInput.setHint("备注，例如 起床 / 吃药 / 开会");
        labelInput.setInputType(1);
        container.addView(labelOf("备注"));
        LinearLayout.LayoutParams labelLp = new LinearLayout.LayoutParams(-1, -2);
        labelLp.bottomMargin = dp(8.0f);
        container.addView(labelInput, labelLp);
        final TextView repeatToggle = new TextView(this);
        final boolean[] repeatState = {false};
        repeatToggle.setText("重复：仅一次");
        repeatToggle.setTextColor(getColorCompat(R.color.brand));
        repeatToggle.setTextSize(2, 13.5f);
        repeatToggle.setPadding(0, dp(6.0f), 0, dp(6.0f));
        repeatToggle.setOnClickListener(new View.OnClickListener() { // from class: com.xiaofan.bangfan.FunctionPanelActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FunctionPanelActivity.showAddAlarmDialog$lambda$12(repeatState, repeatToggle, view);
            }
        });
        container.addView(repeatToggle);
        ScrollView scroll = new ScrollView(this);
        scroll.addView(container);
        new AlertDialog.Builder(this).setTitle("新建闹钟").setView(scroll).setPositiveButton("确定", new DialogInterface.OnClickListener() { // from class: com.xiaofan.bangfan.FunctionPanelActivity$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                FunctionPanelActivity.showAddAlarmDialog$lambda$13(FunctionPanelActivity.this, timeInput, labelInput, repeatState, dialogInterface, i);
            }
        }).setNegativeButton("取消", (DialogInterface.OnClickListener) null).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showAddAlarmDialog$lambda$12(boolean[] repeatState, TextView repeatToggle, View it) {
        Intrinsics.checkNotNullParameter(repeatState, "$repeatState");
        Intrinsics.checkNotNullParameter(repeatToggle, "$repeatToggle");
        repeatState[0] = !repeatState[0];
        repeatToggle.setText(repeatState[0] ? "重复：每天" : "重复：仅一次");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showAddAlarmDialog$lambda$13(FunctionPanelActivity this$0, EditText timeInput, EditText labelInput, boolean[] repeatState, DialogInterface dialogInterface, int i) {
        TextView textView;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(timeInput, "$timeInput");
        Intrinsics.checkNotNullParameter(labelInput, "$labelInput");
        Intrinsics.checkNotNullParameter(repeatState, "$repeatState");
        int[] parsed = this$0.parseTime(timeInput.getText().toString());
        if (parsed == null) {
            this$0.toast("时间格式不对，请用 07:30 或 07:30:15");
            return;
        }
        XiaoFanAlarm.Item item = XiaoFanAlarm.INSTANCE.add(this$0, StringsKt.trim((CharSequence) labelInput.getText().toString()).toString(), parsed[0], parsed[1], parsed[2], repeatState[0]);
        this$0.refreshAlarms();
        this$0.toast("闹钟已设好：" + item.timeText());
        XiaoFanVoice.INSTANCE.tip(this$0, "闹钟定好啦，" + item.timeText());
        if (XiaoFanAlarm.INSTANCE.canScheduleExact(this$0) || (textView = this$0.exactAlarmWarn) == null) {
            return;
        }
        textView.setVisibility(0);
    }

    private final TextView labelOf(String text) {
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setTextColor(getColorCompat(R.color.muted));
        tv.setTextSize(2, 12.0f);
        tv.setPadding(0, dp(8.0f), 0, dp(2.0f));
        return tv;
    }

    private final int[] parseTime(String input) {
        if (input == null) {
            return null;
        }
        String s = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.trim((CharSequence) normalizeDigits(input)).toString(), (char) 65306, ':', false, 4, (Object) null), (char) 65293, ':', false, 4, (Object) null), (char) 12290, ':', false, 4, (Object) null), (char) 65292, ':', false, 4, (Object) null), (char) Typography.middleDot, ':', false, 4, (Object) null), '.', ':', false, 4, (Object) null), '-', ':', false, 4, (Object) null), '\t', ':', false, 4, (Object) null), ' ', ':', false, 4, (Object) null);
        StringBuilder sb = new StringBuilder();
        boolean lastColon = true;
        int length = s.length();
        boolean z = false;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == ':') {
                if (!lastColon) {
                    sb.append(c);
                }
                lastColon = true;
            } else {
                sb.append(c);
                lastColon = false;
            }
        }
        while (true) {
            if (!(sb.length() > 0) || StringsKt.last(sb) != ':') {
                break;
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        List parts = StringsKt.split$default((CharSequence) sb2, new String[]{":"}, false, 0, 6, (Object) null);
        int size = parts.size();
        if (2 <= size && size < 4) {
            try {
                int h = Integer.parseInt(StringsKt.trim((CharSequence) ((String) parts.get(0))).toString());
                int m = Integer.parseInt(StringsKt.trim((CharSequence) ((String) parts.get(1))).toString());
                int sec = parts.size() == 3 ? Integer.parseInt(StringsKt.trim((CharSequence) ((String) parts.get(2))).toString()) : 0;
                if (h >= 0 && h < 24) {
                    if (m >= 0 && m < 60) {
                        if (sec >= 0 && sec < 60) {
                            z = true;
                        }
                        if (z) {
                            return new int[]{h, m, sec};
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
        return null;
    }

    private final String normalizeDigits(String s) {
        StringBuilder sb = new StringBuilder(s.length());
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (65296 <= c && c < 65306) {
                sb.append((char) ((c - 65296) + 48));
            } else {
                sb.append(c);
            }
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }

    private final LinearLayout card() {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(1);
        GradientDrawable bg = new GradientDrawable();
        bg.setColor(getColorCompat(R.color.card));
        bg.setCornerRadius(dp(16.0f));
        bg.setStroke(1, getColorCompat(R.color.line));
        card.setBackground(bg);
        card.setPadding(dp(16.0f), dp(14.0f), dp(16.0f), dp(16.0f));
        return card;
    }

    private final TextView cardTitle(String text) {
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setTextColor(getColorCompat(R.color.ink));
        tv.setTextSize(2, 16.0f);
        tv.getPaint().setFakeBoldText(true);
        return tv;
    }

    private final LinearLayout.LayoutParams marginParams(int left, int top, int right, int bottom) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, -2);
        lp.setMargins(left, top, right, bottom);
        return lp;
    }

    private final int getColorCompat(int id) {
        return getColor(id);
    }

    private final int dp(float value) {
        return Math.round(TypedValue.applyDimension(1, value, getResources().getDisplayMetrics()));
    }

    private final void toast(String msg) {
        Toast.makeText(this, msg, 0).show();
    }
}
