package com.xiaofan.bangfan;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
/* compiled from: BaseActivity.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0014J\b\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010\t\u001a\u00020\u0004H\u0002J\b\u0010\n\u001a\u00020\bH\u0004J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u0004H\u0014J\b\u0010\u000f\u001a\u00020\u0004H\u0014J\b\u0010\u0010\u001a\u00020\u0004H\u0014¨\u0006\u0012"}, d2 = {"Lcom/xiaofan/bangfan/BaseActivity;", "Landroid/app/Activity;", "()V", "attachBaseContext", "", "newBase", "Landroid/content/Context;", "currentResNight", "", "flushAppSession", "isNightUi", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onNightUiRefresh", "onPause", "onResume", "Companion", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public class BaseActivity extends Activity {
    public static final Companion Companion = new Companion(null);
    private static long appSessionStart;
    private static int resumedCount;

    protected void onNightUiRefresh() {
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context newBase) {
        Intrinsics.checkNotNullParameter(newBase, "newBase");
        super.attachBaseContext(Companion.wrapNightContext(newBase));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean isNightUi() {
        try {
            switch (AppPrefs.INSTANCE.nightModeState(this)) {
                case 0:
                    return false;
                case 1:
                    return true;
                default:
                    return Companion.systemNightOn();
            }
        } catch (Throwable th) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        resumedCount++;
        if (resumedCount == 1 && appSessionStart == 0) {
            appSessionStart = System.currentTimeMillis();
        }
        if (AppPrefs.INSTANCE.nightModeState(this) == 2 && isNightUi() != currentResNight()) {
            recreate();
        } else {
            onNightUiRefresh();
        }
    }

    private final boolean currentResNight() {
        return (getResources().getConfiguration().uiMode & 48) == 32;
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        if (AppPrefs.INSTANCE.nightModeState(this) == 2) {
            boolean sysNight = (newConfig.uiMode & 48) == 32;
            if (sysNight != currentResNight()) {
                recreate();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onPause() {
        resumedCount = RangesKt.coerceAtLeast(resumedCount - 1, 0);
        if (resumedCount == 0 && appSessionStart > 0) {
            flushAppSession();
        }
        super.onPause();
    }

    private final void flushAppSession() {
        long start = appSessionStart;
        appSessionStart = 0L;
        if (start > 0 && !FloatBallService.Companion.isRunning()) {
            long seconds = (System.currentTimeMillis() - start) / 1000;
            if (seconds > 0) {
                AppPrefs.INSTANCE.addCompanionSeconds(this, seconds);
            }
        }
    }

    /* compiled from: BaseActivity.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\u0004J\u0006\u0010\b\u001a\u00020\tJ\u0006\u0010\n\u001a\u00020\tJ\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/xiaofan/bangfan/BaseActivity$Companion;", "", "()V", "appSessionStart", "", "resumedCount", "", "appSessionSeconds", "isAppInForeground", "", "systemNightOn", "wrapNightContext", "Landroid/content/Context;", "context", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean systemNightOn() {
            try {
                return (Resources.getSystem().getConfiguration().uiMode & 48) == 32;
            } catch (Throwable th) {
                return false;
            }
        }

        public final Context wrapNightContext(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            try {
                int i = 32;
                switch (AppPrefs.INSTANCE.nightModeState(context)) {
                    case 0:
                        i = 16;
                        break;
                    case 1:
                        break;
                    default:
                        if (!systemNightOn()) {
                            i = 16;
                            break;
                        } else {
                            break;
                        }
                }
                int targetNight = i;
                Configuration config = new Configuration(context.getResources().getConfiguration());
                int currentNight = config.uiMode & 48;
                if (currentNight == targetNight) {
                    return context;
                }
                config.uiMode = (config.uiMode & (-49)) | targetNight;
                Context createConfigurationContext = context.createConfigurationContext(config);
                Intrinsics.checkNotNull(createConfigurationContext);
                return createConfigurationContext;
            } catch (Throwable th) {
                return context;
            }
        }

        public final boolean isAppInForeground() {
            return BaseActivity.resumedCount > 0;
        }

        public final long appSessionSeconds() {
            if (BaseActivity.appSessionStart <= 0 || FloatBallService.Companion.isRunning()) {
                return 0L;
            }
            return (System.currentTimeMillis() - BaseActivity.appSessionStart) / 1000;
        }
    }
}
