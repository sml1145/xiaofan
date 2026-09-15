package com.xiaofan.bangfan;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.xiaofan.bangfan.TimedTurnEngine;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: TimedTurnEngine.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u001b\u0018\u0000 32\u00020\u0001:\u000223B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0018\u001a\u00020\u0019J\u0006\u0010\u001a\u001a\u00020\u0006J\u0006\u0010\u001b\u001a\u00020\u0012J\u0006\u0010\u001c\u001a\u00020\bJ\u0006\u0010\u001d\u001a\u00020\bJ\u000e\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020\bJ\u000e\u0010 \u001a\u00020\u00192\u0006\u0010!\u001a\u00020\bJ\u0018\u0010\"\u001a\u00020\u00192\u0006\u0010#\u001a\u00020\u00122\u0006\u0010$\u001a\u00020\u0012H\u0002J\u0006\u0010%\u001a\u00020\u0019J\u0006\u0010&\u001a\u00020\u0006J\u0006\u0010'\u001a\u00020\u0019J\u0006\u0010(\u001a\u00020\u0019J\u0010\u0010)\u001a\u00020\u00192\u0006\u0010#\u001a\u00020\u0012H\u0002J\b\u0010*\u001a\u00020\u0019H\u0002J\u0010\u0010+\u001a\u00020\u00192\b\u0010,\u001a\u0004\u0018\u00010\nJ\u0006\u0010-\u001a\u00020\u0019J\b\u0010.\u001a\u00020\u0019H\u0002J\u0006\u0010/\u001a\u00020\u0019J\b\u00100\u001a\u00020\u0019H\u0002J\b\u00101\u001a\u00020\u0019H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n \f*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00064"}, d2 = {"Lcom/xiaofan/bangfan/TimedTurnEngine;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "accumulatedBeforePause", "", "adPageActive", "", "callback", "Lcom/xiaofan/bangfan/TimedTurnEngine$Callback;", "ctx", "kotlin.jvm.PlatformType", "intervalMs", "main", "Landroid/os/Handler;", "pageStartedAt", "pauseReason", "", "paused", "probeTask", "Ljava/lang/Runnable;", "running", "tickTask", "fireTurn", "", "getIntervalMs", "getPauseReason", "isPaused", "isRunning", "onAdPageChanged", "isAd", "onPresenceChanged", "present", "pauseInternal", "reason", "userMessage", "reloadInterval", "remainingMs", "resetPacing", "resumeFromPresenceGate", "resumeInternal", "scheduleTick", "setCallback", "cb", "start", "startForegroundProbe", "stop", "stopForegroundProbe", "stopTick", "Callback", "Companion", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class TimedTurnEngine {
    public static final Companion Companion = new Companion(null);
    private static final long PROBE_INTERVAL_MS = 4000;
    private static final String TAG = "TimedTurn";
    private static final long TICK_GRANULARITY_MS = 200;
    private long accumulatedBeforePause;
    private boolean adPageActive;
    private Callback callback;
    private final Context ctx;
    private long intervalMs;
    private final Handler main;
    private long pageStartedAt;
    private String pauseReason;
    private volatile boolean paused;
    private Runnable probeTask;
    private volatile boolean running;
    private Runnable tickTask;

    /* compiled from: TimedTurnEngine.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\bH&¨\u0006\u000b"}, d2 = {"Lcom/xiaofan/bangfan/TimedTurnEngine$Callback;", "", "onPaused", "", "reason", "", "onResumed", "intervalMs", "", "onTick", "remainingMs", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public interface Callback {
        void onPaused(String str);

        void onResumed(long j);

        void onTick(long j);
    }

    public TimedTurnEngine(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.ctx = context.getApplicationContext();
        this.main = new Handler(Looper.getMainLooper());
        this.pauseReason = "";
    }

    public final void setCallback(Callback cb) {
        this.callback = cb;
    }

    public final boolean isRunning() {
        return this.running;
    }

    public final boolean isPaused() {
        return this.paused;
    }

    public final String getPauseReason() {
        return this.pauseReason;
    }

    public final long getIntervalMs() {
        return this.intervalMs;
    }

    public final long remainingMs() {
        if (!this.running || this.paused) {
            return -1L;
        }
        return Math.max(0L, this.intervalMs - (this.accumulatedBeforePause + (System.currentTimeMillis() - this.pageStartedAt)));
    }

    public final void start() {
        if (this.running) {
            return;
        }
        AppPrefs appPrefs = AppPrefs.INSTANCE;
        Context ctx = this.ctx;
        Intrinsics.checkNotNullExpressionValue(ctx, "ctx");
        this.intervalMs = appPrefs.turnIntervalMs(ctx);
        AppPrefs appPrefs2 = AppPrefs.INSTANCE;
        Context ctx2 = this.ctx;
        Intrinsics.checkNotNullExpressionValue(ctx2, "ctx");
        boolean speedDone = appPrefs2.speedDone(ctx2);
        if (!speedDone) {
            Log.w(TAG, "尚未测速，使用兜底间隔 " + this.intervalMs + "ms");
        }
        this.running = true;
        this.paused = false;
        this.accumulatedBeforePause = 0L;
        this.pageStartedAt = System.currentTimeMillis();
        TurnManager.INSTANCE.notifyTimedTurnChanged(true, this.intervalMs);
        Callback callback = this.callback;
        if (callback != null) {
            callback.onResumed(this.intervalMs);
        }
        scheduleTick();
        Log.i(TAG, "started, interval=" + this.intervalMs + " ms measured=" + speedDone);
    }

    public final void stop() {
        this.running = false;
        this.paused = false;
        stopTick();
        TurnManager.INSTANCE.notifyTimedTurnChanged(false, this.intervalMs);
        Log.i(TAG, "stopped");
    }

    public final void reloadInterval() {
        AppPrefs appPrefs = AppPrefs.INSTANCE;
        Context ctx = this.ctx;
        Intrinsics.checkNotNullExpressionValue(ctx, "ctx");
        this.intervalMs = appPrefs.turnIntervalMs(ctx);
        if (this.running) {
            TurnManager.INSTANCE.notifyTimedTurnChanged(true, this.intervalMs);
        }
    }

    public final void onPresenceChanged(boolean present) {
        AppPrefs appPrefs = AppPrefs.INSTANCE;
        Context ctx = this.ctx;
        Intrinsics.checkNotNullExpressionValue(ctx, "ctx");
        if (appPrefs.presenceRequired(ctx) && this.running) {
            if (present) {
                if (this.paused && Intrinsics.areEqual(this.pauseReason, AppPrefs.KEY_PRESENCE)) {
                    this.accumulatedBeforePause = 0L;
                    this.pageStartedAt = System.currentTimeMillis();
                    resumeInternal("用户回到屏幕前");
                }
            } else if (this.paused) {
            } else {
                this.accumulatedBeforePause += System.currentTimeMillis() - this.pageStartedAt;
                pauseInternal(AppPrefs.KEY_PRESENCE, "检测到你离开了屏幕，已暂停自动翻页");
            }
        }
    }

    public final void onAdPageChanged(boolean isAd) {
        if (this.running) {
            this.adPageActive = isAd;
            if (isAd) {
                if (this.paused) {
                    return;
                }
                this.accumulatedBeforePause += System.currentTimeMillis() - this.pageStartedAt;
                pauseInternal("ad", "广告页由倒计时逻辑接管");
            } else if (this.paused && Intrinsics.areEqual(this.pauseReason, "ad")) {
                resumeInternal("广告已结束");
            }
        }
    }

    private final void pauseInternal(String reason, String userMessage) {
        this.paused = true;
        this.pauseReason = reason;
        stopTick();
        TurnManager.INSTANCE.notifyTimedTurnChanged(false, this.intervalMs);
        Callback callback = this.callback;
        if (callback != null) {
            callback.onPaused(userMessage);
        }
        Log.i(TAG, "paused: " + reason);
        if (Intrinsics.areEqual(reason, "foreground")) {
            startForegroundProbe();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resumeInternal(String reason) {
        this.paused = false;
        this.pauseReason = "";
        this.pageStartedAt = System.currentTimeMillis();
        TurnManager.INSTANCE.notifyTimedTurnChanged(true, this.intervalMs);
        Callback callback = this.callback;
        if (callback != null) {
            callback.onResumed(this.intervalMs);
        }
        scheduleTick();
        Log.i(TAG, "resumed: " + reason);
        stopForegroundProbe();
    }

    private final void startForegroundProbe() {
        stopForegroundProbe();
        Runnable runnable = new Runnable() { // from class: com.xiaofan.bangfan.TimedTurnEngine$startForegroundProbe$task$1
            @Override // java.lang.Runnable
            public void run() {
                boolean z;
                boolean z2;
                String str;
                Handler handler;
                z = TimedTurnEngine.this.running;
                if (z) {
                    z2 = TimedTurnEngine.this.paused;
                    if (z2) {
                        str = TimedTurnEngine.this.pauseReason;
                        if (Intrinsics.areEqual(str, "foreground")) {
                            PageTurnAccessibilityService service = PageTurnAccessibilityService.Companion.getInstance();
                            if (service == null || !service.isReaderAppInForeground()) {
                                handler = TimedTurnEngine.this.main;
                                handler.postDelayed(this, 4000L);
                                return;
                            }
                            Log.i("TimedTurn", "probe: back in reader app, resume");
                            TimedTurnEngine.this.resumeInternal("back in reader app");
                            return;
                        }
                    }
                }
                TimedTurnEngine.this.stopForegroundProbe();
            }
        };
        this.probeTask = runnable;
        this.main.postDelayed(runnable, PROBE_INTERVAL_MS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopForegroundProbe() {
        Runnable it = this.probeTask;
        if (it != null) {
            this.main.removeCallbacks(it);
        }
        this.probeTask = null;
    }

    private final void scheduleTick() {
        stopTick();
        Runnable runnable = new Runnable() { // from class: com.xiaofan.bangfan.TimedTurnEngine$scheduleTick$task$1
            @Override // java.lang.Runnable
            public void run() {
                boolean z;
                boolean z2;
                TimedTurnEngine.Callback callback;
                Handler handler;
                z = TimedTurnEngine.this.running;
                if (z) {
                    z2 = TimedTurnEngine.this.paused;
                    if (z2) {
                        return;
                    }
                    long remaining = TimedTurnEngine.this.remainingMs();
                    callback = TimedTurnEngine.this.callback;
                    if (callback != null) {
                        callback.onTick(remaining);
                    }
                    if (remaining > 0) {
                        handler = TimedTurnEngine.this.main;
                        handler.postDelayed(this, 200L);
                        return;
                    }
                    TimedTurnEngine.this.fireTurn();
                }
            }
        };
        this.tickTask = runnable;
        this.main.postDelayed(runnable, TICK_GRANULARITY_MS);
    }

    private final void stopTick() {
        Runnable it = this.tickTask;
        if (it != null) {
            this.main.removeCallbacks(it);
        }
        this.tickTask = null;
    }

    public final void fireTurn() {
        PageTurnAccessibilityService service = PageTurnAccessibilityService.Companion.getInstance();
        if (service == null) {
            Log.i(TAG, "turn failed: a11y missing");
            pauseInternal("a11y", "无障碍服务未开启，无法翻页");
            return;
        }
        boolean turned = service.performTimedPageTurn("定时翻页（" + (this.intervalMs / 1000.0d) + " 秒/页）");
        if (turned) {
            Log.i(TAG, "turn ok: page turned");
            this.accumulatedBeforePause = 0L;
            this.pageStartedAt = System.currentTimeMillis();
            AppPrefs appPrefs = AppPrefs.INSTANCE;
            Context ctx = this.ctx;
            Intrinsics.checkNotNullExpressionValue(ctx, "ctx");
            long baseInterval = appPrefs.turnIntervalMs(ctx);
            AppPrefs appPrefs2 = AppPrefs.INSTANCE;
            Context ctx2 = this.ctx;
            Intrinsics.checkNotNullExpressionValue(ctx2, "ctx");
            boolean speedUp = appPrefs2.speedUpOnBookEnd(ctx2) && service.isCurrentPageBookEnd();
            this.intervalMs = speedUp ? Math.max(800L, baseInterval / 2) : baseInterval;
            if (speedUp) {
                Log.i(TAG, "book-end page: speed up interval " + baseInterval + " -> " + this.intervalMs);
            }
            TurnManager.INSTANCE.notifyTimedTurnChanged(true, this.intervalMs);
            scheduleTick();
        } else if (!service.isReaderAppInForeground()) {
            Log.i(TAG, "gate: not in reader app, pause");
            pauseInternal("foreground", "当前不在阅读 APP，已暂停定时翻页");
        }
    }

    public final void resetPacing() {
        if (this.running) {
            this.accumulatedBeforePause = 0L;
            this.pageStartedAt = System.currentTimeMillis();
        }
    }

    public final void resumeFromPresenceGate() {
        if (this.paused && Intrinsics.areEqual(this.pauseReason, AppPrefs.KEY_PRESENCE)) {
            resumeInternal("presence gate disabled by user");
        }
    }

    /* compiled from: TimedTurnEngine.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/xiaofan/bangfan/TimedTurnEngine$Companion;", "", "()V", "PROBE_INTERVAL_MS", "", "TAG", "", "TICK_GRANULARITY_MS", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
