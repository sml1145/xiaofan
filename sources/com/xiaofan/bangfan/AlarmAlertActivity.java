package com.xiaofan.bangfan;

import android.app.KeyguardManager;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: AlarmAlertActivity.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0002J\b\u0010\f\u001a\u00020\rH\u0002J\b\u0010\u000e\u001a\u00020\rH\u0016J\u0012\u0010\u000f\u001a\u00020\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014J\b\u0010\u0012\u001a\u00020\rH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/xiaofan/bangfan/AlarmAlertActivity;", "Lcom/xiaofan/bangfan/BaseActivity;", "()V", "alarmId", "", "label", "", "bigButton", "Landroid/widget/TextView;", "text", "primary", "", "finishAndRemove", "", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "showOverLockScreen", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class AlarmAlertActivity extends BaseActivity {
    private int alarmId;
    private String label = "";

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showOverLockScreen();
        Intent intent = getIntent();
        this.alarmId = intent != null ? intent.getIntExtra("alarm_id", 0) : 0;
        Intent intent2 = getIntent();
        String stringExtra = intent2 != null ? intent2.getStringExtra(AlarmRingService.EXTRA_LABEL) : null;
        if (stringExtra == null) {
            stringExtra = "";
        }
        this.label = stringExtra;
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(1);
        root.setGravity(17);
        root.setPadding(UiKit.INSTANCE.dp(this, 28.0f), UiKit.INSTANCE.dp(this, 28.0f), UiKit.INSTANCE.dp(this, 28.0f), UiKit.INSTANCE.dp(this, 28.0f));
        GradientDrawable bg = new GradientDrawable();
        bg.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
        bg.setColors(new int[]{-14717006, -15844772});
        root.setBackground(bg);
        TextView clock = new TextView(this);
        clock.setText(new SimpleDateFormat("HH:mm", Locale.CHINA).format(new Date()));
        clock.setTextColor(-1);
        clock.setTextSize(64.0f);
        clock.getPaint().setFakeBoldText(true);
        clock.setGravity(17);
        root.addView(clock, new LinearLayout.LayoutParams(-1, -2));
        TextView title = new TextView(this);
        title.setText("⏰ 小翻闹钟");
        title.setTextColor(-1510145);
        title.setTextSize(22.0f);
        title.getPaint().setFakeBoldText(true);
        title.setGravity(17);
        LinearLayout.LayoutParams tlp = new LinearLayout.LayoutParams(-1, -2);
        tlp.topMargin = UiKit.INSTANCE.dp(this, 18.0f);
        root.addView(title, tlp);
        TextView labelTv = new TextView(this);
        labelTv.setText(this.label.length() > 0 ? this.label : "时间到啦");
        labelTv.setTextColor(-1);
        labelTv.setTextSize(26.0f);
        labelTv.getPaint().setFakeBoldText(true);
        labelTv.setGravity(17);
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(-1, -2);
        llp.topMargin = UiKit.INSTANCE.dp(this, 14.0f);
        llp.bottomMargin = UiKit.INSTANCE.dp(this, 40.0f);
        root.addView(labelTv, llp);
        TextView stopBtn = bigButton("关闭闹钟", true);
        stopBtn.setOnClickListener(new View.OnClickListener() { // from class: com.xiaofan.bangfan.AlarmAlertActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AlarmAlertActivity.onCreate$lambda$0(AlarmAlertActivity.this, view);
            }
        });
        LinearLayout.LayoutParams slp = new LinearLayout.LayoutParams(-1, -2);
        root.addView(stopBtn, slp);
        TextView snoozeBtn = bigButton("贪睡 5 分钟", false);
        snoozeBtn.setOnClickListener(new View.OnClickListener() { // from class: com.xiaofan.bangfan.AlarmAlertActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AlarmAlertActivity.onCreate$lambda$1(AlarmAlertActivity.this, view);
            }
        });
        LinearLayout.LayoutParams zlp = new LinearLayout.LayoutParams(-1, -2);
        zlp.topMargin = UiKit.INSTANCE.dp(this, 16.0f);
        root.addView(snoozeBtn, zlp);
        setContentView(root);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$0(AlarmAlertActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        XiaoFanAlarm.INSTANCE.dismiss(this$0, this$0.alarmId);
        XiaoFanVoice.INSTANCE.tip(this$0, "闹钟已关闭");
        this$0.finishAndRemove();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$1(AlarmAlertActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            XiaoFanAlarm.INSTANCE.addAfterMinutes(this$0, this$0.label.length() > 0 ? this$0.label : "小翻闹钟", 5);
            XiaoFanAlarm.INSTANCE.dismiss(this$0, this$0.alarmId);
            Toast.makeText(this$0, "已贪睡，5 分钟后再次提醒", 0).show();
        } catch (Throwable th) {
            Toast.makeText(this$0, "贪睡设置失败：" + th.getMessage(), 0).show();
        }
        this$0.finishAndRemove();
    }

    private final TextView bigButton(String text, boolean primary) {
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setGravity(17);
        tv.setTextSize(19.0f);
        tv.getPaint().setFakeBoldText(true);
        tv.setPadding(0, UiKit.INSTANCE.dp(this, 17.0f), 0, UiKit.INSTANCE.dp(this, 17.0f));
        GradientDrawable d = new GradientDrawable();
        d.setCornerRadius(UiKit.INSTANCE.dp(this, 30.0f));
        if (primary) {
            d.setColor(-10929);
            tv.setTextColor(-14536644);
        } else {
            d.setColor(872415231);
            d.setStroke(2, -1);
            tv.setTextColor(-1);
        }
        tv.setBackground(d);
        return tv;
    }

    private final void showOverLockScreen() {
        try {
            if (Build.VERSION.SDK_INT >= 27) {
                setShowWhenLocked(true);
                setTurnScreenOn(true);
                Object systemService = getSystemService("keyguard");
                KeyguardManager km = systemService instanceof KeyguardManager ? (KeyguardManager) systemService : null;
                if (km != null) {
                    km.requestDismissKeyguard(this, null);
                }
            } else {
                getWindow().addFlags(6815744);
            }
            getWindow().addFlags(384);
            getWindow().getDecorView().setSystemUiVisibility(4102);
        } catch (Throwable th) {
        }
    }

    private final void finishAndRemove() {
        try {
            finishAndRemoveTask();
        } catch (Throwable th) {
            finish();
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
    }
}
