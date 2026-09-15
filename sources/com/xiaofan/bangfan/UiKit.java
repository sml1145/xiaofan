package com.xiaofan.bangfan;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.PowerManager;
import android.provider.Settings;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.DebugKt;
/* compiled from: UiKit.kt */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002<=B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ&\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u0006J\u0016\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0015J\u000e\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0005\u001a\u00020\u0006J\u0016\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u001eJ\u0016\u0010\u001f\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010 \u001a\u00020\bJ\u000e\u0010!\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\"\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010J.\u0010#\u001a\u00020$2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010%\u001a\u00020\u00152\u0006\u0010&\u001a\u00020\u00152\u0006\u0010'\u001a\u00020\u00152\u0006\u0010(\u001a\u00020\u0015J\u0006\u0010)\u001a\u00020$J\u000e\u0010*\u001a\u00020+2\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010,\u001a\u00020+2\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010-\u001a\u00020+2\u0006\u0010\u000f\u001a\u00020\u0010J \u0010.\u001a\u00020+2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010/\u001a\u00020\u00042\u0006\u00100\u001a\u00020\u000bH\u0002J\u000e\u00101\u001a\u00020+2\u0006\u0010\u000f\u001a\u00020\u0010J \u00102\u001a\u00020\u00192\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u00103\u001a\u00020\b2\b\u00104\u001a\u0004\u0018\u00010\bJ6\u00105\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u00103\u001a\u00020\b2\b\u00106\u001a\u0004\u0018\u00010\b2\f\u00107\u001a\b\u0012\u0004\u0012\u00020\u000b082\u0006\u00109\u001a\u00020:J.\u0010;\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u00103\u001a\u00020\b2\f\u00107\u001a\b\u0012\u0004\u0012\u00020\b082\b\u0010\f\u001a\u0004\u0018\u00010\r¨\u0006>"}, d2 = {"Lcom/xiaofan/bangfan/UiKit;", "", "()V", "bodyText", "Landroid/widget/TextView;", "activity", "Landroid/app/Activity;", "text", "", "button", "primary", "", "listener", "Landroid/view/View$OnClickListener;", "canOverlay", "context", "Landroid/content/Context;", "card", "Landroid/widget/LinearLayout;", "cardTitle", "color", "", "resId", "contentOf", "scrollView", "Landroid/widget/ScrollView;", "divider", "Landroid/view/View;", "dp", "value", "", "hasPerm", "perm", "isA11yEnabled", "isIgnoringBatteryOptimizations", "margin", "Landroid/widget/LinearLayout$LayoutParams;", "left", "top", "right", "bottom", "matchWrap", "openA11ySettings", "", "openAppDetails", "openOverlaySettings", "paintSwitch", "tv", DebugKt.DEBUG_PROPERTY_VALUE_ON, "requestIgnoreBatteryOptimizations", "scaffold", "title", "subtitle", "switchRow", "desc", "getter", "Lcom/xiaofan/bangfan/UiKit$Getter;", "setter", "Lcom/xiaofan/bangfan/UiKit$Setter;", "valueRow", "Getter", "Setter", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class UiKit {
    public static final UiKit INSTANCE = new UiKit();

    /* compiled from: UiKit.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\bæ\u0080\u0001\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\r\u0010\u0003\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/xiaofan/bangfan/UiKit$Getter;", "T", "", "get", "()Ljava/lang/Object;", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public interface Getter<T> {
        T get();
    }

    /* compiled from: UiKit.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/xiaofan/bangfan/UiKit$Setter;", "", "set", "", "value", "", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public interface Setter {
        void set(boolean z);
    }

    private UiKit() {
    }

    public final int color(Activity activity, int resId) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        return activity.getColor(resId);
    }

    public final int dp(Context context, float value) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (int) TypedValue.applyDimension(1, value, context.getResources().getDisplayMetrics());
    }

    public final ScrollView scaffold(final Activity activity, String title, String subtitle) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(title, "title");
        ScrollView scrollView = new ScrollView(activity);
        scrollView.setBackgroundColor(color(activity, R.color.bg));
        boolean z = true;
        scrollView.setFillViewport(true);
        LinearLayout container = new LinearLayout(activity);
        container.setOrientation(1);
        container.setPadding(dp(activity, 18.0f), dp(activity, 20.0f), dp(activity, 18.0f), dp(activity, 28.0f));
        LinearLayout header = new LinearLayout(activity);
        header.setOrientation(0);
        header.setGravity(16);
        TextView backBtn = new TextView(activity);
        backBtn.setText("‹ 返回");
        backBtn.setTextColor(color(activity, R.color.brand));
        backBtn.setTextSize(2, 15.0f);
        backBtn.setPadding(0, dp(activity, 6.0f), dp(activity, 14.0f), dp(activity, 6.0f));
        backBtn.setOnClickListener(new View.OnClickListener() { // from class: com.xiaofan.bangfan.UiKit$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UiKit.scaffold$lambda$0(activity, view);
            }
        });
        header.addView(backBtn);
        TextView titleTv = new TextView(activity);
        titleTv.setText(title);
        titleTv.setTextColor(color(activity, R.color.ink));
        titleTv.setTextSize(2, 23.0f);
        titleTv.getPaint().setFakeBoldText(true);
        header.addView(titleTv, new LinearLayout.LayoutParams(0, -2, 1.0f));
        container.addView(header, new LinearLayout.LayoutParams(-1, -2));
        String str = subtitle;
        if (str != null && str.length() != 0) {
            z = false;
        }
        if (!z) {
            TextView subTv = new TextView(activity);
            subTv.setText(subtitle);
            subTv.setTextColor(color(activity, R.color.muted));
            subTv.setTextSize(2, 12.5f);
            subTv.setLineSpacing(0.0f, 1.35f);
            subTv.setPadding(0, dp(activity, 6.0f), 0, dp(activity, 4.0f));
            container.addView(subTv, margin(activity, 0, dp(activity, 6.0f), 0, dp(activity, 4.0f)));
        }
        scrollView.addView(container, new FrameLayout.LayoutParams(-1, -2));
        scrollView.setTag(R.id.xf_content_root, container);
        return scrollView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void scaffold$lambda$0(Activity activity, View it) {
        Intrinsics.checkNotNullParameter(activity, "$activity");
        ClickFx.INSTANCE.play(activity);
        activity.finish();
    }

    public final LinearLayout contentOf(ScrollView scrollView) {
        Intrinsics.checkNotNullParameter(scrollView, "scrollView");
        Object tag = scrollView.getTag(R.id.xf_content_root);
        Intrinsics.checkNotNull(tag, "null cannot be cast to non-null type android.widget.LinearLayout");
        return (LinearLayout) tag;
    }

    public final LinearLayout card(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        LinearLayout card = new LinearLayout(activity);
        card.setOrientation(1);
        GradientDrawable bg = new GradientDrawable();
        bg.setColor(color(activity, R.color.card));
        bg.setCornerRadius(dp(activity, 16.0f));
        bg.setStroke(1, color(activity, R.color.line));
        card.setBackground(bg);
        card.setPadding(dp(activity, 16.0f), dp(activity, 14.0f), dp(activity, 16.0f), dp(activity, 16.0f));
        return card;
    }

    public final TextView cardTitle(Activity activity, String text) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(text, "text");
        TextView tv = new TextView(activity);
        tv.setText(text);
        tv.setTextColor(color(activity, R.color.ink));
        tv.setTextSize(2, 16.0f);
        tv.getPaint().setFakeBoldText(true);
        return tv;
    }

    public final TextView bodyText(Activity activity, String text) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(text, "text");
        TextView tv = new TextView(activity);
        tv.setText(text);
        tv.setTextColor(color(activity, R.color.muted));
        tv.setTextSize(2, 12.5f);
        tv.setLineSpacing(0.0f, 1.4f);
        return tv;
    }

    public final LinearLayout switchRow(final Activity activity, String title, String desc, final Getter<Boolean> getter, final Setter setter) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(getter, "getter");
        Intrinsics.checkNotNullParameter(setter, "setter");
        LinearLayout row = new LinearLayout(activity);
        row.setOrientation(0);
        row.setGravity(16);
        row.setPadding(0, dp(activity, 7.0f), 0, dp(activity, 7.0f));
        LinearLayout textCol = new LinearLayout(activity);
        boolean z = true;
        textCol.setOrientation(1);
        TextView titleTv = new TextView(activity);
        titleTv.setText(title);
        titleTv.setTextColor(color(activity, R.color.ink));
        titleTv.setTextSize(2, 14.5f);
        textCol.addView(titleTv);
        String str = desc;
        if (str != null && str.length() != 0) {
            z = false;
        }
        if (!z) {
            TextView descTv = new TextView(activity);
            descTv.setText(desc);
            descTv.setTextColor(color(activity, R.color.muted));
            descTv.setTextSize(2, 11.0f);
            descTv.setLineSpacing(0.0f, 1.25f);
            textCol.addView(descTv);
        }
        row.addView(textCol, new LinearLayout.LayoutParams(0, -2, 1.0f));
        final TextView stateTv = new TextView(activity);
        stateTv.setTextSize(2, 12.5f);
        stateTv.setPadding(dp(activity, 12.0f), dp(activity, 6.0f), dp(activity, 4.0f), dp(activity, 6.0f));
        paintSwitch(activity, stateTv, getter.get().booleanValue());
        row.addView(stateTv);
        row.setOnClickListener(new View.OnClickListener() { // from class: com.xiaofan.bangfan.UiKit$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UiKit.switchRow$lambda$1(activity, getter, setter, stateTv, view);
            }
        });
        return row;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void switchRow$lambda$1(Activity activity, Getter getter, Setter setter, TextView stateTv, View it) {
        Intrinsics.checkNotNullParameter(activity, "$activity");
        Intrinsics.checkNotNullParameter(getter, "$getter");
        Intrinsics.checkNotNullParameter(setter, "$setter");
        Intrinsics.checkNotNullParameter(stateTv, "$stateTv");
        ClickFx.INSTANCE.play(activity);
        boolean newVal = !((Boolean) getter.get()).booleanValue();
        setter.set(newVal);
        INSTANCE.paintSwitch(activity, stateTv, newVal);
    }

    private final void paintSwitch(Activity activity, TextView tv, boolean on) {
        tv.setText(on ? "已开" : "已关");
        tv.setTextColor(color(activity, on ? R.color.ok : R.color.muted));
    }

    public final LinearLayout valueRow(final Activity activity, String title, final Getter<String> getter, final View.OnClickListener listener) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(getter, "getter");
        LinearLayout row = new LinearLayout(activity);
        row.setOrientation(0);
        row.setGravity(16);
        row.setPadding(0, dp(activity, 10.0f), 0, dp(activity, 10.0f));
        TextView titleTv = new TextView(activity);
        titleTv.setText(title);
        titleTv.setTextColor(color(activity, R.color.ink));
        titleTv.setTextSize(2, 14.5f);
        row.addView(titleTv, new LinearLayout.LayoutParams(0, -2, 1.0f));
        final TextView valueTv = new TextView(activity);
        valueTv.setText(getter.get());
        valueTv.setTextColor(color(activity, R.color.muted));
        valueTv.setTextSize(2, 12.0f);
        valueTv.setGravity(GravityCompat.END);
        valueTv.setMaxWidth(dp(activity, 190.0f));
        row.addView(valueTv);
        row.setOnClickListener(new View.OnClickListener() { // from class: com.xiaofan.bangfan.UiKit$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UiKit.valueRow$lambda$3(activity, listener, valueTv, getter, view);
            }
        });
        return row;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void valueRow$lambda$3(Activity activity, View.OnClickListener $listener, final TextView valueTv, final Getter getter, View it) {
        Intrinsics.checkNotNullParameter(activity, "$activity");
        Intrinsics.checkNotNullParameter(valueTv, "$valueTv");
        Intrinsics.checkNotNullParameter(getter, "$getter");
        ClickFx.INSTANCE.play(activity);
        if ($listener != null) {
            $listener.onClick(it);
        }
        valueTv.postDelayed(new Runnable() { // from class: com.xiaofan.bangfan.UiKit$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                UiKit.valueRow$lambda$3$lambda$2(valueTv, getter);
            }
        }, 450L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void valueRow$lambda$3$lambda$2(TextView valueTv, Getter getter) {
        Intrinsics.checkNotNullParameter(valueTv, "$valueTv");
        Intrinsics.checkNotNullParameter(getter, "$getter");
        valueTv.setText((CharSequence) getter.get());
    }

    public final View divider(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        View view = new View(activity);
        view.setBackgroundColor(color(activity, R.color.line));
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, 1);
        lp.topMargin = dp(activity, 4.0f);
        lp.bottomMargin = dp(activity, 4.0f);
        view.setLayoutParams(lp);
        return view;
    }

    public final TextView button(final Activity activity, String text, boolean primary, final View.OnClickListener listener) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(listener, "listener");
        TextView btn = new TextView(activity);
        btn.setText(text);
        btn.setGravity(17);
        if (primary) {
            btn.setTextColor(-1);
            btn.setTextSize(2, 15.5f);
            btn.getPaint().setFakeBoldText(true);
            btn.setPadding(dp(activity, 16.0f), dp(activity, 13.0f), dp(activity, 16.0f), dp(activity, 13.0f));
            GradientDrawable bg = new GradientDrawable();
            bg.setColor(color(activity, R.color.brand));
            bg.setCornerRadius(dp(activity, 26.0f));
            btn.setBackground(bg);
        } else {
            btn.setTextColor(color(activity, R.color.brand));
            btn.setTextSize(2, 14.5f);
            btn.setPadding(dp(activity, 14.0f), dp(activity, 12.0f), dp(activity, 14.0f), dp(activity, 12.0f));
            GradientDrawable bg2 = new GradientDrawable();
            bg2.setColor(color(activity, R.color.card));
            bg2.setCornerRadius(dp(activity, 26.0f));
            bg2.setStroke(1, color(activity, R.color.brand));
            btn.setBackground(bg2);
        }
        btn.setOnClickListener(new View.OnClickListener() { // from class: com.xiaofan.bangfan.UiKit$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UiKit.button$lambda$4(activity, listener, view);
            }
        });
        return btn;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void button$lambda$4(Activity activity, View.OnClickListener listener, View it) {
        Intrinsics.checkNotNullParameter(activity, "$activity");
        Intrinsics.checkNotNullParameter(listener, "$listener");
        ClickFx.INSTANCE.play(activity);
        listener.onClick(it);
    }

    public final LinearLayout.LayoutParams margin(Activity activity, int left, int top, int right, int bottom) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, -2);
        lp.setMargins(left, top, right, bottom);
        return lp;
    }

    public final LinearLayout.LayoutParams matchWrap() {
        return new LinearLayout.LayoutParams(-1, -2);
    }

    public final boolean hasPerm(Context context, String perm) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(perm, "perm");
        try {
            return context.checkSelfPermission(perm) == 0;
        } catch (Throwable th) {
            return false;
        }
    }

    public final boolean canOverlay(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return Settings.canDrawOverlays(context);
    }

    public final void openOverlaySettings(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            Intent intent = new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + context.getPackageName()));
            intent.addFlags(268435456);
            context.startActivity(intent);
        } catch (Throwable th) {
        }
    }

    public final boolean isA11yEnabled(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            ComponentName component = new ComponentName(context.getPackageName(), PageTurnAccessibilityService.class.getName());
            String enabled = Settings.Secure.getString(context.getContentResolver(), "enabled_accessibility_services");
            if (enabled == null) {
                return false;
            }
            Iterable $this$any$iv = StringsKt.split$default((CharSequence) enabled, new String[]{":"}, false, 0, 6, (Object) null);
            if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
                return false;
            }
            for (Object element$iv : $this$any$iv) {
                String it = (String) element$iv;
                if (Intrinsics.areEqual(component, ComponentName.unflattenFromString(it))) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            return false;
        }
    }

    public final void openA11ySettings(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            context.startActivity(new Intent("android.settings.ACCESSIBILITY_SETTINGS").addFlags(268435456));
        } catch (Throwable th) {
        }
    }

    public final void openAppDetails(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:" + context.getPackageName()));
            intent.addFlags(268435456);
            context.startActivity(intent);
        } catch (Throwable th) {
        }
    }

    public final boolean isIgnoringBatteryOptimizations(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            Object systemService = context.getSystemService("power");
            PowerManager pm = systemService instanceof PowerManager ? (PowerManager) systemService : null;
            if (pm != null) {
                return pm.isIgnoringBatteryOptimizations(context.getPackageName());
            }
            return false;
        } catch (Throwable th) {
            return false;
        }
    }

    public final void requestIgnoreBatteryOptimizations(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            Intent intent = new Intent("android.settings.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS", Uri.parse("package:" + context.getPackageName()));
            intent.addFlags(268435456);
            context.startActivity(intent);
        } catch (Throwable th) {
            try {
                openAppDetails(context);
            } catch (Throwable th2) {
                context.startActivity(new Intent("android.settings.IGNORE_BATTERY_OPTIMIZATION_SETTINGS").addFlags(268435456));
            }
        }
    }
}
