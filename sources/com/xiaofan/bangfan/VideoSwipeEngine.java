package com.xiaofan.bangfan;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;
/* compiled from: VideoSwipeEngine.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\b\u0018\u0000 \u001c2\u00020\u0001:\u0002\u001b\u001cB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\u0006\u0010\u0011\u001a\u00020\fJ\b\u0010\u0012\u001a\u00020\u0010H\u0002J\u0010\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0010\u0010\u0016\u001a\u00020\u00102\b\u0010\u0017\u001a\u0004\u0018\u00010\u0006J\u0006\u0010\u0018\u001a\u00020\u0010J\u0006\u0010\u0019\u001a\u00020\u0010J\b\u0010\u001a\u001a\u00020\u0010H\u0002R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \b*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/xiaofan/bangfan/VideoSwipeEngine;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "callback", "Lcom/xiaofan/bangfan/VideoSwipeEngine$Callback;", "ctx", "kotlin.jvm.PlatformType", "main", "Landroid/os/Handler;", "running", "", "tickTask", "Ljava/lang/Runnable;", "cancelTick", "", "isRunning", "notifyState", "scheduleNext", "delayMs", "", "setCallback", "cb", "start", "stop", "tickOnce", "Callback", "Companion", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class VideoSwipeEngine {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "VideoSwipe";
    private Callback callback;
    private final Context ctx;
    private final Handler main;
    private volatile boolean running;
    private Runnable tickTask;

    /* compiled from: VideoSwipeEngine.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\u0018\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH&¨\u0006\u000b"}, d2 = {"Lcom/xiaofan/bangfan/VideoSwipeEngine$Callback;", "", "onStateChanged", "", "running", "", "paused", "onSwiped", "dir", "", "reason", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public interface Callback {
        void onStateChanged(boolean z, boolean z2);

        void onSwiped(String str, String str2);
    }

    public VideoSwipeEngine(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.ctx = context.getApplicationContext();
        this.main = new Handler(Looper.getMainLooper());
    }

    public final void setCallback(Callback cb) {
        this.callback = cb;
    }

    public final boolean isRunning() {
        return this.running;
    }

    public final void start() {
        if (this.running) {
            return;
        }
        this.running = true;
        notifyState();
        AppPrefs appPrefs = AppPrefs.INSTANCE;
        Context ctx = this.ctx;
        Intrinsics.checkNotNullExpressionValue(ctx, "ctx");
        scheduleNext(appPrefs.swipeIntervalMs(ctx));
        Log.i(TAG, "video swipe engine started");
    }

    public final void stop() {
        this.running = false;
        cancelTick();
        notifyState();
        Log.i(TAG, "video swipe engine stopped");
    }

    private final void scheduleNext(long delayMs) {
        cancelTick();
        Runnable task = new Runnable() { // from class: com.xiaofan.bangfan.VideoSwipeEngine$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                VideoSwipeEngine.scheduleNext$lambda$0(VideoSwipeEngine.this);
            }
        };
        this.tickTask = task;
        this.main.postDelayed(task, delayMs);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void scheduleNext$lambda$0(VideoSwipeEngine this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.tickOnce();
    }

    private final void cancelTick() {
        Runnable it = this.tickTask;
        if (it != null) {
            this.main.removeCallbacks(it);
        }
        this.tickTask = null;
    }

    private final void tickOnce() {
        Callback callback;
        if (this.running) {
            try {
                AppPrefs appPrefs = AppPrefs.INSTANCE;
                Context ctx = this.ctx;
                Intrinsics.checkNotNullExpressionValue(ctx, "ctx");
                boolean paused = appPrefs.swipePaused(ctx);
                PageTurnAccessibilityService service = PageTurnAccessibilityService.Companion.getInstance();
                boolean inVideo = service != null && service.isVideoAppInForeground();
                if (!paused && inVideo) {
                    AppPrefs appPrefs2 = AppPrefs.INSTANCE;
                    Context ctx2 = this.ctx;
                    Intrinsics.checkNotNullExpressionValue(ctx2, "ctx");
                    if (appPrefs2.autoSwipeOn(ctx2)) {
                        Intrinsics.checkNotNull(service);
                        if (service.performPageTurn("自动刷视频") && (callback = this.callback) != null) {
                            callback.onSwiped("next", DebugKt.DEBUG_PROPERTY_VALUE_AUTO);
                        }
                    }
                }
            } catch (Throwable th) {
                Log.w(TAG, "tick failed", th);
            }
            AppPrefs appPrefs3 = AppPrefs.INSTANCE;
            Context ctx3 = this.ctx;
            Intrinsics.checkNotNullExpressionValue(ctx3, "ctx");
            scheduleNext(appPrefs3.swipeIntervalMs(ctx3));
        }
    }

    private final void notifyState() {
        Callback callback = this.callback;
        if (callback != null) {
            boolean z = this.running;
            AppPrefs appPrefs = AppPrefs.INSTANCE;
            Context ctx = this.ctx;
            Intrinsics.checkNotNullExpressionValue(ctx, "ctx");
            callback.onStateChanged(z, appPrefs.swipePaused(ctx));
        }
    }

    /* compiled from: VideoSwipeEngine.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/xiaofan/bangfan/VideoSwipeEngine$Companion;", "", "()V", "TAG", "", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
