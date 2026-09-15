package com.xiaofan.bangfan;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewParent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.xiaofan.bangfan.MascotView;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: SplashActivity.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\u0010\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u000eH\u0002J\b\u0010\u0015\u001a\u00020\u0012H\u0017J\u0012\u0010\u0016\u001a\u00020\u00122\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014J\b\u0010\u0019\u001a\u00020\u0012H\u0014R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082.¢\u0006\u0004\n\u0002\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/xiaofan/bangfan/SplashActivity;", "Lcom/xiaofan/bangfan/BaseActivity;", "()V", "chars", "", "Landroid/widget/TextView;", "[Landroid/widget/TextView;", "column", "Landroid/widget/LinearLayout;", "main", "Landroid/os/Handler;", "mascot", "Lcom/xiaofan/bangfan/MascotView;", "dp", "", "value", "", "finishToMain", "", "flash", "index", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "Companion", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class SplashActivity extends BaseActivity {
    public static final Companion Companion = new Companion(null);
    private static final long EXIT_DELAY_MS = 260;
    private static final long FLASH_HOLD_MS = 420;
    private static final long PER_CHAR_MS = 105;
    private TextView[] chars;
    private LinearLayout column;
    private final Handler main = new Handler(Looper.getMainLooper());
    private MascotView mascot;

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout root = new FrameLayout(this);
        root.setBackgroundColor(getColor(R.color.splash_bg));
        LinearLayout content = new LinearLayout(this);
        content.setOrientation(1);
        int i = 17;
        content.setGravity(17);
        int i2 = 2;
        this.mascot = new MascotView(this, null, 2, null);
        MascotView mascotView = this.mascot;
        if (mascotView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mascot");
            mascotView = null;
        }
        mascotView.setSceneEnabled(false);
        MascotView mascotView2 = this.mascot;
        if (mascotView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mascot");
            mascotView2 = null;
        }
        mascotView2.setMascotListener(new MascotView.MascotListener() { // from class: com.xiaofan.bangfan.SplashActivity$onCreate$1
            @Override // com.xiaofan.bangfan.MascotView.MascotListener
            public void onMascotLongPressed() {
            }

            @Override // com.xiaofan.bangfan.MascotView.MascotListener
            public void onMascotTapped() {
                MascotView mascotView3;
                mascotView3 = SplashActivity.this.mascot;
                if (mascotView3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mascot");
                    mascotView3 = null;
                }
                mascotView3.flashChest();
                XiaoFanVoice.INSTANCE.tip(SplashActivity.this, "我在呢");
            }
        });
        int min = (int) (Math.min(getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels) * 0.4f);
        LinearLayout.LayoutParams mascotLp = new LinearLayout.LayoutParams(min, min);
        mascotLp.bottomMargin = dp(8.0f);
        MascotView mascotView3 = this.mascot;
        if (mascotView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mascot");
            mascotView3 = null;
        }
        content.addView(mascotView3, mascotLp);
        MascotView mascotView4 = this.mascot;
        if (mascotView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mascot");
            mascotView4 = null;
        }
        mascotView4.applyVisualState(this);
        this.column = new LinearLayout(this);
        LinearLayout linearLayout = this.column;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("column");
            linearLayout = null;
        }
        linearLayout.setOrientation(1);
        LinearLayout linearLayout2 = this.column;
        if (linearLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("column");
            linearLayout2 = null;
        }
        linearLayout2.setGravity(17);
        String line = getString(R.string.splash_line);
        Intrinsics.checkNotNullExpressionValue(line, "getString(...)");
        int length = line.length();
        TextView[] textViewArr = new TextView[length];
        for (int i3 = 0; i3 < length; i3++) {
            textViewArr[i3] = new TextView(this);
        }
        this.chars = textViewArr;
        float textSize = line.length() > 20 ? 20.0f : 24.0f;
        int i4 = 0;
        int length2 = line.length();
        while (i4 < length2) {
            TextView tv = new TextView(this);
            tv.setText(String.valueOf(line.charAt(i4)));
            tv.setTextColor(getColor(R.color.muted));
            tv.setTextSize(i2, textSize);
            tv.setGravity(i);
            tv.setAlpha(0.12f);
            tv.setLineSpacing(0.0f, 1.06f);
            LinearLayout linearLayout3 = this.column;
            if (linearLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("column");
                linearLayout3 = null;
            }
            int min2 = min;
            linearLayout3.addView(tv, new LinearLayout.LayoutParams(-2, -2));
            TextView[] textViewArr2 = this.chars;
            if (textViewArr2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("chars");
                textViewArr2 = null;
            }
            textViewArr2[i4] = tv;
            i4++;
            min = min2;
            i = 17;
            i2 = 2;
        }
        LinearLayout linearLayout4 = this.column;
        if (linearLayout4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("column");
            linearLayout4 = null;
        }
        content.addView(linearLayout4, new LinearLayout.LayoutParams(-2, -2));
        FrameLayout.LayoutParams rootLp = new FrameLayout.LayoutParams(-2, -2);
        rootLp.gravity = 17;
        root.addView(content, rootLp);
        setContentView(root);
        MascotView mascotView5 = this.mascot;
        if (mascotView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mascot");
            mascotView5 = null;
        }
        mascotView5.setSpeaking(true);
        int i5 = 0;
        TextView[] textViewArr3 = this.chars;
        if (textViewArr3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chars");
            textViewArr3 = null;
        }
        int length3 = textViewArr3.length;
        while (i5 < length3) {
            TextView[] textViewArr4 = this.chars;
            if (textViewArr4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("chars");
                textViewArr4 = null;
            }
            final TextView tv2 = textViewArr4[i5];
            this.main.postDelayed(new Runnable() { // from class: com.xiaofan.bangfan.SplashActivity$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    SplashActivity.onCreate$lambda$1(tv2, this);
                }
            }, (i5 * PER_CHAR_MS) + 240);
            i5++;
            root = root;
            content = content;
        }
        TextView[] textViewArr5 = this.chars;
        if (textViewArr5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chars");
            textViewArr5 = null;
        }
        long totalDuration = (textViewArr5.length * PER_CHAR_MS) + 240;
        this.main.postDelayed(new Runnable() { // from class: com.xiaofan.bangfan.SplashActivity$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                SplashActivity.onCreate$lambda$2(SplashActivity.this);
            }
        }, totalDuration);
        this.main.postDelayed(new Runnable() { // from class: com.xiaofan.bangfan.SplashActivity$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                SplashActivity.onCreate$lambda$3(SplashActivity.this);
            }
        }, 120 + totalDuration);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$1(final TextView tv, SplashActivity this$0) {
        Intrinsics.checkNotNullParameter(tv, "$tv");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        tv.setTextColor(this$0.getColor(R.color.brand));
        tv.animate().alpha(1.0f).setDuration(180L).start();
        tv.animate().scaleX(1.18f).scaleY(1.18f).setDuration(90L).withEndAction(new Runnable() { // from class: com.xiaofan.bangfan.SplashActivity$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                SplashActivity.onCreate$lambda$1$lambda$0(tv);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$1$lambda$0(TextView tv) {
        Intrinsics.checkNotNullParameter(tv, "$tv");
        tv.animate().scaleX(1.0f).scaleY(1.0f).setDuration(120L).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$2(SplashActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MascotView mascotView = this$0.mascot;
        if (mascotView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mascot");
            mascotView = null;
        }
        mascotView.setSpeaking(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$3(SplashActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.flash(0);
    }

    private final void flash(final int index) {
        LinearLayout linearLayout = this.column;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("column");
            linearLayout = null;
        }
        linearLayout.animate().alpha(index % 2 == 0 ? 0.35f : 1.0f).setDuration(130L).setInterpolator(new AccelerateDecelerateInterpolator()).withEndAction(new Runnable() { // from class: com.xiaofan.bangfan.SplashActivity$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                SplashActivity.flash$lambda$6(index, this);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void flash$lambda$6(int $index, final SplashActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        final int next = $index + 1;
        if (next < 4) {
            this$0.main.postDelayed(new Runnable() { // from class: com.xiaofan.bangfan.SplashActivity$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    SplashActivity.flash$lambda$6$lambda$4(SplashActivity.this, next);
                }
            }, 90L);
        } else {
            this$0.main.postDelayed(new Runnable() { // from class: com.xiaofan.bangfan.SplashActivity$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    SplashActivity.flash$lambda$6$lambda$5(SplashActivity.this);
                }
            }, FLASH_HOLD_MS);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void flash$lambda$6$lambda$4(SplashActivity this$0, int $next) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.flash($next);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void flash$lambda$6$lambda$5(SplashActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finishToMain();
    }

    private final void finishToMain() {
        MascotView mascotView = this.mascot;
        LinearLayout linearLayout = null;
        if (mascotView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mascot");
            mascotView = null;
        }
        mascotView.setSpeaking(false);
        LinearLayout linearLayout2 = this.column;
        if (linearLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("column");
            linearLayout2 = null;
        }
        ViewParent parent = linearLayout2.getParent();
        LinearLayout parent2 = parent instanceof View ? (View) parent : null;
        if (parent2 == null) {
            LinearLayout linearLayout3 = this.column;
            if (linearLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("column");
            } else {
                linearLayout = linearLayout3;
            }
            parent2 = linearLayout;
        }
        parent2.animate().alpha(0.0f).setDuration(EXIT_DELAY_MS).withEndAction(new Runnable() { // from class: com.xiaofan.bangfan.SplashActivity$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                SplashActivity.finishToMain$lambda$7(SplashActivity.this);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void finishToMain$lambda$7(SplashActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0, MainActivity.class));
        this$0.overridePendingTransition(17432576, 17432577);
        this$0.finish();
    }

    private final int dp(float value) {
        return (int) TypedValue.applyDimension(1, value, getResources().getDisplayMetrics());
    }

    @Override // android.app.Activity
    @Deprecated(message = "Deprecated in Java")
    public void onBackPressed() {
        finishToMain();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        this.main.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    /* compiled from: SplashActivity.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/xiaofan/bangfan/SplashActivity$Companion;", "", "()V", "EXIT_DELAY_MS", "", "FLASH_HOLD_MS", "PER_CHAR_MS", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
