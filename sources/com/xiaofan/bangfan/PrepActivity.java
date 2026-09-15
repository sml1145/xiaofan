package com.xiaofan.bangfan;

import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: PrepActivity.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J.\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\rH\u0002J\u0012\u0010\u000e\u001a\u00020\u00062\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J-\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00132\u000e\u0010\u0014\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016¢\u0006\u0002\u0010\u0018J\b\u0010\u0019\u001a\u00020\u0006H\u0014J\b\u0010\u001a\u001a\u00020\u0006H\u0002J!\u0010\u001b\u001a\u00020\u00062\u0012\u0010\u0014\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0015\"\u00020\bH\u0002¢\u0006\u0002\u0010\u001cR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/xiaofan/bangfan/PrepActivity;", "Lcom/xiaofan/bangfan/BaseActivity;", "()V", "list", "Landroid/widget/LinearLayout;", "addRow", "", "title", "", "desc", "enabled", "", "onClick", "Lkotlin/Function0;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onRequestPermissionsResult", "requestCode", "", "permissions", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onResume", "render", "req", "([Ljava/lang/String;)V", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class PrepActivity extends BaseActivity {
    private LinearLayout list;

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrollView scaffold = UiKit.INSTANCE.scaffold(this, "前置准备", "把下面的权限逐个开一下，小翻才能帮你翻页、读屏、定位天气。");
        LinearLayout content = UiKit.INSTANCE.contentOf(scaffold);
        this.list = new LinearLayout(this);
        LinearLayout linearLayout = this.list;
        LinearLayout linearLayout2 = null;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("list");
            linearLayout = null;
        }
        linearLayout.setOrientation(1);
        LinearLayout card = UiKit.INSTANCE.card(this);
        card.addView(UiKit.INSTANCE.cardTitle(this, "权限清单"));
        LinearLayout linearLayout3 = this.list;
        if (linearLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("list");
        } else {
            linearLayout2 = linearLayout3;
        }
        card.addView(linearLayout2, UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 8.0f), 0, 0));
        content.addView(card, UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 10.0f), 0, 0));
        setContentView(scaffold);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaofan.bangfan.BaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        render();
    }

    private final void render() {
        LinearLayout linearLayout = this.list;
        LinearLayout linearLayout2 = null;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("list");
            linearLayout = null;
        }
        linearLayout.removeAllViews();
        addRow("悬浮窗", "显示悬浮球与提示条", UiKit.INSTANCE.canOverlay(this), new Function0<Unit>() { // from class: com.xiaofan.bangfan.PrepActivity$render$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2() {
                if (!UiKit.INSTANCE.canOverlay(PrepActivity.this)) {
                    UiKit.INSTANCE.openOverlaySettings(PrepActivity.this);
                }
            }
        });
        addRow("无障碍服务", "翻页、读屏、拦截音量键", UiKit.INSTANCE.isA11yEnabled(this), new Function0<Unit>() { // from class: com.xiaofan.bangfan.PrepActivity$render$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2() {
                if (!UiKit.INSTANCE.isA11yEnabled(PrepActivity.this)) {
                    UiKit.INSTANCE.openA11ySettings(PrepActivity.this);
                }
            }
        });
        addRow("摄像头", "检测你是否在看屏幕", UiKit.INSTANCE.hasPerm(this, "android.permission.CAMERA"), new Function0<Unit>() { // from class: com.xiaofan.bangfan.PrepActivity$render$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2() {
                PrepActivity.this.req("android.permission.CAMERA");
            }
        });
        addRow("麦克风", "语音播报与自定义音色", UiKit.INSTANCE.hasPerm(this, "android.permission.RECORD_AUDIO"), new Function0<Unit>() { // from class: com.xiaofan.bangfan.PrepActivity$render$4
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2() {
                PrepActivity.this.req("android.permission.RECORD_AUDIO");
            }
        });
        addRow("位置信息", "天气自动定位（可改用手动城市）", UiKit.INSTANCE.hasPerm(this, "android.permission.ACCESS_FINE_LOCATION") || UiKit.INSTANCE.hasPerm(this, "android.permission.ACCESS_COARSE_LOCATION"), new Function0<Unit>() { // from class: com.xiaofan.bangfan.PrepActivity$render$5
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2() {
                PrepActivity.this.req("android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION");
            }
        });
        boolean notifOk = true;
        if (Build.VERSION.SDK_INT >= 33 && !UiKit.INSTANCE.hasPerm(this, "android.permission.POST_NOTIFICATIONS")) {
            notifOk = false;
        }
        addRow("通知", "闹钟响铃与前台服务提示", notifOk, new Function0<Unit>() { // from class: com.xiaofan.bangfan.PrepActivity$render$6
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2() {
                if (Build.VERSION.SDK_INT >= 33) {
                    PrepActivity.this.req("android.permission.POST_NOTIFICATIONS");
                }
            }
        });
        if (!XiaoFanAlarm.INSTANCE.canScheduleExact(this)) {
            addRow("精确闹钟", "让闹钟准点到秒（当前被系统限制）", false, new Function0<Unit>() { // from class: com.xiaofan.bangfan.PrepActivity$render$7
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    XiaoFanAlarm.INSTANCE.openExactAlarmSettings(PrepActivity.this);
                }
            });
        }
        addRow("后台运行白名单", "加入电池优化白名单，防止小翻在后台被系统清理", UiKit.INSTANCE.isIgnoringBatteryOptimizations(this), new PrepActivity$render$8(this));
        LinearLayout linearLayout3 = this.list;
        if (linearLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("list");
        } else {
            linearLayout2 = linearLayout3;
        }
        linearLayout2.addView(UiKit.INSTANCE.bodyText(this, "提示：无障碍服务开启时，系统会提示「小翻帮翻可访问你的屏幕内容」——这是安卓对所有无障碍服务的标准提示。小翻只在本机识别页面用于翻页与广告跳过，不上传任何内容。"), UiKit.INSTANCE.margin(this, 0, UiKit.INSTANCE.dp(this, 12.0f), 0, 0));
    }

    private final void addRow(String title, String desc, boolean enabled, final Function0<Unit> function0) {
        LinearLayout row = new LinearLayout(this);
        row.setOrientation(0);
        row.setGravity(16);
        row.setPadding(0, UiKit.INSTANCE.dp(this, 9.0f), 0, UiKit.INSTANCE.dp(this, 9.0f));
        LinearLayout textCol = new LinearLayout(this);
        textCol.setOrientation(1);
        TextView titleTv = new TextView(this);
        titleTv.setText(title);
        titleTv.setTextColor(UiKit.INSTANCE.color(this, R.color.ink));
        titleTv.setTextSize(15.0f);
        textCol.addView(titleTv);
        TextView descTv = new TextView(this);
        descTv.setText(desc);
        descTv.setTextColor(UiKit.INSTANCE.color(this, R.color.muted));
        descTv.setTextSize(11.5f);
        textCol.addView(descTv);
        row.addView(textCol, new LinearLayout.LayoutParams(0, -2, 1.0f));
        TextView stateTv = new TextView(this);
        stateTv.setText(enabled ? "已开启" : "去开启");
        stateTv.setTextColor(enabled ? UiKit.INSTANCE.color(this, R.color.ok) : -1);
        stateTv.setTextSize(12.5f);
        stateTv.setPadding(UiKit.INSTANCE.dp(this, 14.0f), UiKit.INSTANCE.dp(this, 7.0f), UiKit.INSTANCE.dp(this, 14.0f), UiKit.INSTANCE.dp(this, 7.0f));
        if (!enabled) {
            GradientDrawable bg = new GradientDrawable();
            bg.setColor(UiKit.INSTANCE.color(this, R.color.brand));
            bg.setCornerRadius(UiKit.INSTANCE.dp(this, 20.0f));
            stateTv.setBackground(bg);
        }
        row.addView(stateTv);
        row.setOnClickListener(new View.OnClickListener() { // from class: com.xiaofan.bangfan.PrepActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PrepActivity.addRow$lambda$0(Function0.this, view);
            }
        });
        LinearLayout linearLayout = this.list;
        LinearLayout linearLayout2 = null;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("list");
            linearLayout = null;
        }
        linearLayout.addView(row);
        LinearLayout linearLayout3 = this.list;
        if (linearLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("list");
        } else {
            linearLayout2 = linearLayout3;
        }
        linearLayout2.addView(UiKit.INSTANCE.divider(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void addRow$lambda$0(Function0 onClick, View it) {
        Intrinsics.checkNotNullParameter(onClick, "$onClick");
        onClick.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void req(String... permissions) {
        Collection destination$iv$iv = new ArrayList();
        for (String str : permissions) {
            if (!UiKit.INSTANCE.hasPerm(this, str)) {
                destination$iv$iv.add(str);
            }
        }
        Collection needed = (List) destination$iv$iv;
        if (!needed.isEmpty()) {
            Collection $this$toTypedArray$iv = needed;
            requestPermissions((String[]) $this$toTypedArray$iv.toArray(new String[0]), 501);
        }
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        FloatBallService companion;
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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
        if ($this$any$iv != null && FloatBallService.Companion.isRunning() && (companion = FloatBallService.Companion.getInstance()) != null) {
            companion.onPermissionGranted();
        }
        render();
    }
}
