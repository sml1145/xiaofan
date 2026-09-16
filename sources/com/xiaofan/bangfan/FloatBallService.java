package com.xiaofan.bangfan;

import android.animation.ValueAnimator;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.xiaofan.bangfan.PresenceDetector;
import com.xiaofan.bangfan.TimedTurnEngine;
import com.xiaofan.bangfan.TurnManager;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.DebugKt;
/* compiled from: FloatBallService.kt */
@Metadata(d1 = {"\u0000¯\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b.*\u0003\u000f&.\u0018\u0000 \u008c\u00012\u00020\u00012\u00020\u0002:\u0002\u008c\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020\u0005H\u0002J\b\u0010;\u001a\u000209H\u0002J\b\u0010<\u001a\u00020=H\u0002J\b\u0010>\u001a\u000209H\u0002J\u0010\u0010?\u001a\u00020$2\u0006\u0010@\u001a\u00020$H\u0002J\u0010\u0010A\u001a\u00020$2\u0006\u0010B\u001a\u00020$H\u0002J\b\u0010C\u001a\u00020\rH\u0002J\b\u0010D\u001a\u00020$H\u0002J\b\u0010E\u001a\u000209H\u0002J\u000e\u0010F\u001a\u00020$2\u0006\u0010G\u001a\u00020\rJ\b\u0010H\u001a\u000209H\u0002J\b\u0010I\u001a\u000209H\u0002J\b\u0010J\u001a\u000209H\u0002J\u0006\u0010K\u001a\u000209J\b\u0010L\u001a\u000209H\u0002J\b\u0010M\u001a\u00020\u0005H\u0002J\u0006\u0010N\u001a\u00020\u0012J\u0010\u0010O\u001a\u0002092\u0006\u0010P\u001a\u00020$H\u0016J\u0010\u0010Q\u001a\u0002092\u0006\u0010R\u001a\u00020SH\u0016J\u000e\u0010T\u001a\u0002092\u0006\u0010U\u001a\u00020\u0005J\u0010\u0010V\u001a\u0002092\u0006\u0010W\u001a\u00020\u0005H\u0016J\b\u0010X\u001a\u000209H\u0002J\u0014\u0010Y\u001a\u0004\u0018\u00010Z2\b\u0010[\u001a\u0004\u0018\u00010\\H\u0016J\u0010\u0010]\u001a\u0002092\u0006\u0010^\u001a\u00020_H\u0016J\b\u0010`\u001a\u000209H\u0016J\b\u0010a\u001a\u000209H\u0016J\u0006\u0010b\u001a\u000209J\u0010\u0010c\u001a\u0002092\u0006\u0010:\u001a\u00020\u0005H\u0016J\u0018\u0010d\u001a\u0002092\u0006\u0010e\u001a\u00020S2\u0006\u0010f\u001a\u00020SH\u0016J\u0010\u0010g\u001a\u0002092\u0006\u0010h\u001a\u00020\u0005H\u0016J\u0006\u0010i\u001a\u000209J\"\u0010j\u001a\u00020$2\b\u0010[\u001a\u0004\u0018\u00010\\2\u0006\u0010k\u001a\u00020$2\u0006\u0010l\u001a\u00020$H\u0016J\u0018\u0010m\u001a\u0002092\u0006\u0010n\u001a\u00020\u00052\u0006\u0010o\u001a\u00020\u0012H\u0016J\u0010\u0010p\u001a\u0002092\u0006\u0010R\u001a\u00020SH\u0016J\u0010\u0010q\u001a\u0002092\u0006\u0010R\u001a\u00020SH\u0016J\b\u0010r\u001a\u000209H\u0002J\u0006\u0010s\u001a\u000209J\u0006\u0010t\u001a\u000209J\b\u0010u\u001a\u000209H\u0002J\b\u0010v\u001a\u000209H\u0002J\b\u0010w\u001a\u000209H\u0002J\b\u0010x\u001a\u000209H\u0002J\b\u0010y\u001a\u000209H\u0002J\u000e\u0010z\u001a\u0002092\u0006\u0010W\u001a\u00020\u0005J\u0010\u0010{\u001a\u0002092\b\u0010|\u001a\u0004\u0018\u00010SJ\b\u0010}\u001a\u000209H\u0002J\b\u0010~\u001a\u000209H\u0002J\b\u0010\u007f\u001a\u000209H\u0002J\u0007\u0010\u0080\u0001\u001a\u000209J\t\u0010\u0081\u0001\u001a\u000209H\u0002J\t\u0010\u0082\u0001\u001a\u000209H\u0002J\u0007\u0010\u0083\u0001\u001a\u000209J\t\u0010\u0084\u0001\u001a\u000209H\u0002J\u0007\u0010\u0085\u0001\u001a\u000209J\u0011\u0010\u0086\u0001\u001a\u0002092\u0006\u0010|\u001a\u00020SH\u0002J\t\u0010\u0087\u0001\u001a\u000209H\u0002J\t\u0010\u0088\u0001\u001a\u000209H\u0002J\t\u0010\u0089\u0001\u001a\u000209H\u0002J\u0012\u0010\u008a\u0001\u001a\u0002092\u0007\u0010\u008b\u0001\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001aR\u000e\u0010\u001b\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u00020&X\u0082\u0004¢\u0006\u0004\n\u0002\u0010'R\u0010\u0010(\u001a\u0004\u0018\u00010)X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010-\u001a\u00020.X\u0082\u0004¢\u0006\u0004\n\u0002\u0010/R\u0010\u00100\u001a\u0004\u0018\u000101X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00102\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00103\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u000205X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u000207X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u008d\u0001"}, d2 = {"Lcom/xiaofan/bangfan/FloatBallService;", "Landroid/app/Service;", "Lcom/xiaofan/bangfan/TurnManager$Listener;", "()V", "attached", "", "ballAvatar", "Landroid/widget/ImageView;", "ballLabel", "Landroid/widget/TextView;", "ballRoot", "Landroid/view/View;", "ballSizePx", "", "ballTouchListener", "com/xiaofan/bangfan/FloatBallService$ballTouchListener$1", "Lcom/xiaofan/bangfan/FloatBallService$ballTouchListener$1;", "companionLastTick", "", "companionTask", "Ljava/lang/Runnable;", "dragging", "hidden", "hideTask", "isFaded", "lastAnnouncedPresence", "Ljava/lang/Boolean;", "lastPresenceAnnounceAt", "listenGlow", "listenPulse", "Landroid/animation/ValueAnimator;", "lp", "Landroid/view/WindowManager$LayoutParams;", "main", "Landroid/os/Handler;", "peekPx", "", "presenceCallback", "com/xiaofan/bangfan/FloatBallService$presenceCallback$1", "Lcom/xiaofan/bangfan/FloatBallService$presenceCallback$1;", "presenceDetector", "Lcom/xiaofan/bangfan/PresenceDetector;", "progressRing", "screenH", "screenW", "timedCallback", "com/xiaofan/bangfan/FloatBallService$timedCallback$1", "Lcom/xiaofan/bangfan/FloatBallService$timedCallback$1;", "timedEngine", "Lcom/xiaofan/bangfan/TimedTurnEngine;", "tipHideTask", "toastView", "vibrator", "Landroid/os/Vibrator;", "wm", "Landroid/view/WindowManager;", "announcePresence", "", "present", "applyLp", "buildNotification", "Landroid/app/Notification;", "cancelAutoHide", "clampX", "x", "clampY", "y", "computeBallSize", "computeFgsType", "createChannel", "dp", "value", "ensureBall", "fadeBall", "flashBall", "flushCompanionNow", "hideToEdge", "isNearEdge", "liveCompanionSeconds", "onAdCountdown", "seconds", "onAdDetected", "reason", "", "onAdPageChanged", "ad", "onAutoTurnChanged", DebugKt.DEBUG_PROPERTY_VALUE_ON, "onBallShortTap", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onCreate", "onDestroy", "onPermissionGranted", "onPresenceChanged", "onProgress", "book", "chapter", "onServiceReady", "ready", "onSpeedResultChanged", "onStartCommand", "flags", "startId", "onTimedTurnChanged", "running", "interval", "onTurnFailed", "onTurned", "openMainActivity", "refreshForegroundServiceType", "refreshFromPrefs", "releaseAllEngines", "removeBall", "restoreFromEdge", "saveBallPosition", "scheduleAutoHide", "setListeningGlow", "showTip", "text", "snapToEdge", "startCompanionTimer", "startForegroundWithType", "startPresenceDetection", "startReaderEngines", "stopCompanionTimer", "stopPresenceDetection", "stopReaderEngines", "syncEngines", "toast", "toggleAutoTurn", "unfadeBall", "updateLabel", "vibrate", "short", "Companion", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class FloatBallService extends Service implements TurnManager.Listener {
    public static final String ACTION_START = "com.xiaofan.bangfan.action.START_BALL";
    public static final String ACTION_STOP = "com.xiaofan.bangfan.action.STOP_BALL";
    private static final long AUTO_HIDE_DELAY_MS = 3000;
    private static final String CHANNEL_ID = "xiaofan_float_ball";
    private static final long COMPANION_TICK_MS = 10000;
    public static final Companion Companion = new Companion(null);
    private static final int NOTIF_ID = 1001;
    private static final float PEEK_RATIO = 0.22f;
    private static final long PRESENCE_ANNOUNCE_GAP_MS = 10000;
    private static final String TAG = "FloatBall";
    private static volatile FloatBallService instance;
    private volatile boolean attached;
    private ImageView ballAvatar;
    private TextView ballLabel;
    private View ballRoot;
    private float ballSizePx;
    private long companionLastTick;
    private Runnable companionTask;
    private volatile boolean dragging;
    private volatile boolean hidden;
    private Runnable hideTask;
    private volatile boolean isFaded;
    private Boolean lastAnnouncedPresence;
    private long lastPresenceAnnounceAt;
    private View listenGlow;
    private ValueAnimator listenPulse;
    private WindowManager.LayoutParams lp;
    private int peekPx;
    private PresenceDetector presenceDetector;
    private View progressRing;
    private int screenH;
    private int screenW;
    private TimedTurnEngine timedEngine;
    private Runnable tipHideTask;
    private TextView toastView;
    private Vibrator vibrator;
    private WindowManager wm;
    private final Handler main = new Handler(Looper.getMainLooper());
    private final FloatBallService$ballTouchListener$1 ballTouchListener = new FloatBallService$ballTouchListener$1(this);
    private final FloatBallService$timedCallback$1 timedCallback = new TimedTurnEngine.Callback() { // from class: com.xiaofan.bangfan.FloatBallService$timedCallback$1
        @Override // com.xiaofan.bangfan.TimedTurnEngine.Callback
        public void onTick(long elapsed) {
            View ring;
            ring = FloatBallService.this.progressRing;
            if (ring == null) {
                return;
            }
            long interval = AppPrefs.INSTANCE.turnIntervalMs(FloatBallService.this);
            float ratio = interval <= 0 ? 0.0f : 1.0f - (((float) elapsed) / ((float) interval));
            ring.setVisibility(0);
            ring.setAlpha((Math.max(0.0f, Math.min(1.0f, ratio)) * 0.7f) + 0.3f);
        }

        @Override // com.xiaofan.bangfan.TimedTurnEngine.Callback
        public void onPaused(String reason) {
            View view;
            TimedTurnEngine timedTurnEngine;
            Intrinsics.checkNotNullParameter(reason, "reason");
            view = FloatBallService.this.progressRing;
            if (view != null) {
                view.setVisibility(8);
            }
            FloatBallService.this.updateLabel();
            FloatBallService.this.showTip(reason);
            timedTurnEngine = FloatBallService.this.timedEngine;
            if (!Intrinsics.areEqual(timedTurnEngine != null ? timedTurnEngine.getPauseReason() : null, AppPrefs.KEY_PRESENCE)) {
                XiaoFanVoice.INSTANCE.tip(FloatBallService.this, reason);
            }
        }

        @Override // com.xiaofan.bangfan.TimedTurnEngine.Callback
        public void onResumed(long interval) {
            View view;
            FloatBallService.this.updateLabel();
            view = FloatBallService.this.progressRing;
            if (view == null) {
                return;
            }
            view.setVisibility(0);
        }
    };
    private final FloatBallService$presenceCallback$1 presenceCallback = new PresenceDetector.Callback() { // from class: com.xiaofan.bangfan.FloatBallService$presenceCallback$1
        @Override // com.xiaofan.bangfan.PresenceDetector.Callback
        public void onPresenceChanged(boolean present) {
            TimedTurnEngine timedTurnEngine;
            timedTurnEngine = FloatBallService.this.timedEngine;
            if (timedTurnEngine != null) {
                timedTurnEngine.onPresenceChanged(present);
            }
            FloatBallService.this.updateLabel();
            FloatBallService.this.announcePresence(present);
        }

        /* JADX WARN: Code restructure failed: missing block: B:5:0x0009, code lost:
            r1 = r3.this$0.timedEngine;
         */
        @Override // com.xiaofan.bangfan.PresenceDetector.Callback
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onSample(boolean r4, boolean r5) {
            /*
                r3 = this;
                com.xiaofan.bangfan.FloatBallService r0 = com.xiaofan.bangfan.FloatBallService.this
                android.view.View r0 = com.xiaofan.bangfan.FloatBallService.access$getProgressRing$p(r0)
                if (r0 != 0) goto L9
                return
            L9:
                com.xiaofan.bangfan.FloatBallService r1 = com.xiaofan.bangfan.FloatBallService.this
                com.xiaofan.bangfan.TimedTurnEngine r1 = com.xiaofan.bangfan.FloatBallService.access$getTimedEngine$p(r1)
                if (r1 != 0) goto L12
                return
            L12:
                boolean r2 = r1.isRunning()
                if (r2 == 0) goto L19
                return
            L19:
                if (r4 == 0) goto L1d
                r2 = 0
                goto L1f
            L1d:
                r2 = 8
            L1f:
                r0.setVisibility(r2)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaofan.bangfan.FloatBallService$presenceCallback$1.onSample(boolean, boolean):void");
        }

        @Override // com.xiaofan.bangfan.PresenceDetector.Callback
        public void onError(String msg) {
            Intrinsics.checkNotNullParameter(msg, "msg");
            FloatBallService.this.showTip(msg);
            XiaoFanVoice.INSTANCE.warn(FloatBallService.this, msg);
        }
    };

    /* compiled from: FloatBallService.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011J\u0006\u0010\u0013\u001a\u00020\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/xiaofan/bangfan/FloatBallService$Companion;", "", "()V", "ACTION_START", "", "ACTION_STOP", "AUTO_HIDE_DELAY_MS", "", "CHANNEL_ID", "COMPANION_TICK_MS", "NOTIF_ID", "", "PEEK_RATIO", "", "PRESENCE_ANNOUNCE_GAP_MS", "TAG", "instance", "Lcom/xiaofan/bangfan/FloatBallService;", "getInstance", "isRunning", "", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FloatBallService getInstance() {
            return FloatBallService.instance;
        }

        public final boolean isRunning() {
            return FloatBallService.instance != null;
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // com.xiaofan.bangfan.TurnManager.Listener
    public void onProgress(String book, String chapter) {
        Intrinsics.checkNotNullParameter(book, "book");
        Intrinsics.checkNotNullParameter(chapter, "chapter");
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        instance = this;
        Object systemService = getSystemService("window");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        this.wm = (WindowManager) systemService;
        Object systemService2 = getSystemService("vibrator");
        Intrinsics.checkNotNull(systemService2, "null cannot be cast to non-null type android.os.Vibrator");
        this.vibrator = (Vibrator) systemService2;
        DisplayMetrics dm = getResources().getDisplayMetrics();
        this.screenW = dm.widthPixels;
        this.screenH = dm.heightPixels;
        this.ballSizePx = computeBallSize();
        this.peekPx = Math.max(6, (int) (this.ballSizePx * PEEK_RATIO));
        createChannel();
        startForegroundWithType();
        XiaoFanVoice.INSTANCE.init(this);
        startCompanionTimer();
    }

    private final void startCompanionTimer() {
        stopCompanionTimer();
        this.companionLastTick = System.currentTimeMillis();
        Runnable runnable = new Runnable() { // from class: com.xiaofan.bangfan.FloatBallService$startCompanionTimer$task$1
            @Override // java.lang.Runnable
            public void run() {
                long j;
                Handler handler;
                long now = System.currentTimeMillis();
                j = FloatBallService.this.companionLastTick;
                long seconds = (now - j) / 1000;
                FloatBallService.this.companionLastTick = now;
                if (seconds > 0) {
                    AppPrefs.INSTANCE.addCompanionSeconds(FloatBallService.this, seconds);
                }
                handler = FloatBallService.this.main;
                handler.postDelayed(this, 10000L);
            }
        };
        this.companionTask = runnable;
        this.main.postDelayed(runnable, 10000L);
    }

    private final void stopCompanionTimer() {
        if (this.companionLastTick > 0) {
            long seconds = (System.currentTimeMillis() - this.companionLastTick) / 1000;
            if (seconds > 0) {
                AppPrefs.INSTANCE.addCompanionSeconds(this, seconds);
            }
            this.companionLastTick = 0L;
        }
        Runnable it = this.companionTask;
        if (it != null) {
            this.main.removeCallbacks(it);
        }
        this.companionTask = null;
    }

    public final long liveCompanionSeconds() {
        if (this.companionLastTick > 0) {
            return (System.currentTimeMillis() - this.companionLastTick) / 1000;
        }
        return 0L;
    }

    public final void flushCompanionNow() {
        if (this.companionLastTick > 0) {
            long now = System.currentTimeMillis();
            long seconds = (now - this.companionLastTick) / 1000;
            if (seconds > 0) {
                AppPrefs.INSTANCE.addCompanionSeconds(this, seconds);
            }
            this.companionLastTick = now;
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        String str;
        if (intent == null || (str = intent.getAction()) == null) {
            str = ACTION_START;
        }
        if (Intrinsics.areEqual(ACTION_STOP, str)) {
            stopSelf();
            return 2;
        }
        ensureBall();
        TurnManager.INSTANCE.setListener(this);
        syncEngines();
        return 1;
    }

    @Override // android.app.Service
    public void onDestroy() {
        TurnManager.INSTANCE.clearListener(this);
        stopCompanionTimer();
        releaseAllEngines();
        removeBall();
        instance = null;
        super.onDestroy();
    }

    private final void startForegroundWithType() {
        Notification notif = buildNotification();
        if (Build.VERSION.SDK_INT >= 34) {
            int type = computeFgsType();
            try {
                startForeground(1001, notif, type);
                Log.i(TAG, "startForeground type=" + type);
                return;
            } catch (Throwable th) {
                Log.w(TAG, "startForeground typed failed, fallback", th);
                try {
                    startForeground(1001, notif, BasicMeasure.EXACTLY);
                    return;
                } catch (Throwable th2) {
                }
            }
        }
        try {
            startForeground(1001, notif);
        } catch (Throwable th3) {
            Log.e(TAG, "startForeground failed", th3);
        }
    }

    private final int computeFgsType() {
        boolean hasCamera = checkSelfPermission("android.permission.CAMERA") == 0;
        boolean hasMic = checkSelfPermission("android.permission.RECORD_AUDIO") == 0;
        int type = hasCamera ? 64 : 0;
        if (hasMic) {
            type |= 128;
        }
        return type == 0 ? BasicMeasure.EXACTLY : type;
    }

    public final void refreshForegroundServiceType() {
        try {
            stopForeground(false);
        } catch (Throwable th) {
        }
        startForegroundWithType();
        Log.i(TAG, "foreground service type refreshed");
    }

    private final float computeBallSize() {
        if (AppPrefs.INSTANCE.ballSizeDp(this) > 0) {
            return dp(AppPrefs.INSTANCE.ballSizeDp(this));
        }
        float size = Math.min(this.screenW, this.screenH) * 0.115f;
        int min = dp(46.0f);
        int max = dp(68.0f);
        if (size < min) {
            size = min;
        }
        return size > ((float) max) ? max : size;
    }

    public final int dp(float value) {
        return Math.round(TypedValue.applyDimension(1, value, getResources().getDisplayMetrics()));
    }

    private final void ensureBall() {
        if (this.attached) {
            return;
        }
        if (!Settings.canDrawOverlays(this)) {
            toast("未授予悬浮窗权限，无法显示悬浮球");
            stopSelf();
            return;
        }
        FrameLayout root = new FrameLayout(this);
        this.ballAvatar = new ImageView(this);
        int resId = getResources().getIdentifier(AppPrefs.INSTANCE.avatarResName(this), "drawable", getPackageName());
        if (resId != 0) {
            ImageView imageView = this.ballAvatar;
            Intrinsics.checkNotNull(imageView);
            imageView.setImageResource(resId);
        }
        ImageView imageView2 = this.ballAvatar;
        Intrinsics.checkNotNull(imageView2);
        imageView2.setScaleType(ImageView.ScaleType.FIT_CENTER);
        FrameLayout.LayoutParams avatarLp = new FrameLayout.LayoutParams((int) this.ballSizePx, (int) this.ballSizePx);
        avatarLp.gravity = 17;
        root.addView(this.ballAvatar, avatarLp);
        this.ballLabel = new TextView(this);
        TextView textView = this.ballLabel;
        Intrinsics.checkNotNull(textView);
        textView.setTextColor(-1);
        TextView textView2 = this.ballLabel;
        Intrinsics.checkNotNull(textView2);
        textView2.setTextSize(2, 11.0f);
        TextView textView3 = this.ballLabel;
        Intrinsics.checkNotNull(textView3);
        textView3.setGravity(17);
        TextView textView4 = this.ballLabel;
        Intrinsics.checkNotNull(textView4);
        textView4.getPaint().setFakeBoldText(true);
        TextView textView5 = this.ballLabel;
        Intrinsics.checkNotNull(textView5);
        textView5.setShadowLayer(3.0f, 0.0f, dp(1.0f), -1442840576);
        FrameLayout.LayoutParams labelLp = new FrameLayout.LayoutParams((int) this.ballSizePx, (int) this.ballSizePx);
        labelLp.gravity = 17;
        root.addView(this.ballLabel, labelLp);
        root.setAlpha(AppPrefs.INSTANCE.ballAlpha(this) / 255.0f);
        this.progressRing = new View(this);
        GradientDrawable ringBg = new GradientDrawable();
        ringBg.setShape(1);
        ringBg.setColor(0);
        ringBg.setStroke(dp(3.0f), -13722021);
        View view = this.progressRing;
        Intrinsics.checkNotNull(view);
        view.setBackground(ringBg);
        View view2 = this.progressRing;
        Intrinsics.checkNotNull(view2);
        view2.setVisibility(8);
        FrameLayout.LayoutParams ringLp = new FrameLayout.LayoutParams((int) (this.ballSizePx + dp(8.0f)), (int) (this.ballSizePx + dp(8.0f)));
        ringLp.gravity = 17;
        root.addView(this.progressRing, ringLp);
        this.listenGlow = new View(this);
        GradientDrawable glowBg = new GradientDrawable();
        glowBg.setShape(1);
        glowBg.setColor(0);
        glowBg.setStroke(dp(4.0f), Color.parseColor("#26C6DA"));
        View view3 = this.listenGlow;
        Intrinsics.checkNotNull(view3);
        view3.setBackground(glowBg);
        View view4 = this.listenGlow;
        Intrinsics.checkNotNull(view4);
        view4.setVisibility(8);
        FrameLayout.LayoutParams glowLp = new FrameLayout.LayoutParams((int) this.ballSizePx, (int) this.ballSizePx);
        glowLp.gravity = 17;
        root.addView(this.listenGlow, glowLp);
        this.ballRoot = root;
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(-2, -2, Build.VERSION.SDK_INT >= 26 ? 2038 : 2002, 520, -3);
        this.lp = params;
        params.gravity = 8388659;
        int savedX = AppPrefs.INSTANCE.ballX(this);
        int savedY = AppPrefs.INSTANCE.ballY(this);
        if (savedX >= 0 && savedY > 0) {
            params.x = clampX(savedX);
            params.y = clampY(savedY);
        } else {
            params.x = dp(8.0f);
            params.y = dp(46.0f);
        }
        root.setOnTouchListener(this.ballTouchListener);
        try {
            WindowManager windowManager = this.wm;
            if (windowManager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("wm");
                windowManager = null;
            }
            windowManager.addView(root, params);
            this.attached = true;
            updateLabel();
            scheduleAutoHide();
        } catch (Throwable th) {
            toast("悬浮球添加失败：" + th.getMessage());
            stopSelf();
        }
    }

    private final void removeBall() {
        cancelAutoHide();
        if (this.attached && this.ballRoot != null) {
            try {
                WindowManager windowManager = this.wm;
                if (windowManager == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("wm");
                    windowManager = null;
                }
                windowManager.removeView(this.ballRoot);
            } catch (Throwable th) {
            }
        }
        this.attached = false;
        this.ballRoot = null;
        TextView it = this.toastView;
        if (it != null) {
            try {
                WindowManager windowManager2 = this.wm;
                if (windowManager2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("wm");
                    windowManager2 = null;
                }
                windowManager2.removeView(it);
            } catch (Throwable th2) {
            }
        }
        this.toastView = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void saveBallPosition() {
        if (this.attached) {
            WindowManager.LayoutParams layoutParams = this.lp;
            Intrinsics.checkNotNull(layoutParams);
            String side = layoutParams.x + (((int) this.ballSizePx) / 2) < this.screenW / 2 ? "left" : "right";
            WindowManager.LayoutParams layoutParams2 = this.lp;
            Intrinsics.checkNotNull(layoutParams2);
            int i = layoutParams2.x;
            WindowManager.LayoutParams layoutParams3 = this.lp;
            Intrinsics.checkNotNull(layoutParams3);
            AppPrefs.INSTANCE.saveBallPosition(this, i, layoutParams3.y, side);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onBallShortTap() {
        if (this.isFaded) {
            unfadeBall();
            scheduleAutoHide();
            return;
        }
        toggleAutoTurn();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int clampX(int x) {
        int max = this.screenW - ((int) this.ballSizePx);
        int v = x;
        if (v < 0) {
            v = 0;
        }
        return v > max ? max : v;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int clampY(int y) {
        int max = (this.screenH - ((int) this.ballSizePx)) - dp(24.0f);
        int min = dp(12.0f);
        int v = y;
        if (v < min) {
            v = min;
        }
        return v > max ? max : v;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void applyLp() {
        if (!this.attached || this.ballRoot == null) {
            return;
        }
        try {
            WindowManager windowManager = this.wm;
            if (windowManager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("wm");
                windowManager = null;
            }
            windowManager.updateViewLayout(this.ballRoot, this.lp);
        } catch (Throwable th) {
        }
    }

    private final void snapToEdge() {
        WindowManager.LayoutParams layoutParams = this.lp;
        Intrinsics.checkNotNull(layoutParams);
        int centerX = layoutParams.x + (((int) this.ballSizePx) / 2);
        WindowManager.LayoutParams layoutParams2 = this.lp;
        Intrinsics.checkNotNull(layoutParams2);
        layoutParams2.x = centerX < this.screenW / 2 ? dp(2.0f) : (this.screenW - ((int) this.ballSizePx)) - dp(2.0f);
        WindowManager.LayoutParams layoutParams3 = this.lp;
        Intrinsics.checkNotNull(layoutParams3);
        WindowManager.LayoutParams layoutParams4 = this.lp;
        Intrinsics.checkNotNull(layoutParams4);
        layoutParams3.y = clampY(layoutParams4.y);
        applyLp();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isNearEdge() {
        WindowManager.LayoutParams layoutParams = this.lp;
        Intrinsics.checkNotNull(layoutParams);
        int x = layoutParams.x;
        int threshold = dp(24.0f);
        return x <= threshold || x >= (this.screenW - ((int) this.ballSizePx)) - threshold;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void scheduleAutoHide() {
        cancelAutoHide();
        Runnable task = new Runnable() { // from class: com.xiaofan.bangfan.FloatBallService$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                FloatBallService.scheduleAutoHide$lambda$2(FloatBallService.this);
            }
        };
        this.hideTask = task;
        this.main.postDelayed(task, AUTO_HIDE_DELAY_MS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void scheduleAutoHide$lambda$2(FloatBallService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.fadeBall();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void cancelAutoHide() {
        Runnable it = this.hideTask;
        if (it != null) {
            this.main.removeCallbacks(it);
        }
        this.hideTask = null;
    }

    private final void fadeBall() {
        if (!this.attached || this.dragging || this.hidden) {
            return;
        }
        this.isFaded = true;
        View view = this.ballRoot;
        if (view == null) {
            return;
        }
        view.setAlpha(0.3f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void unfadeBall() {
        if (this.isFaded) {
            this.isFaded = false;
            View view = this.ballRoot;
            if (view == null) {
                return;
            }
            view.setAlpha(AppPrefs.INSTANCE.ballAlpha(this) / 255.0f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideToEdge() {
        if (this.hidden || !this.attached || this.dragging) {
            return;
        }
        cancelAutoHide();
        WindowManager.LayoutParams layoutParams = this.lp;
        Intrinsics.checkNotNull(layoutParams);
        int centerX = layoutParams.x + (((int) this.ballSizePx) / 2);
        WindowManager.LayoutParams layoutParams2 = this.lp;
        Intrinsics.checkNotNull(layoutParams2);
        layoutParams2.x = centerX < this.screenW / 2 ? (-((int) this.ballSizePx)) + this.peekPx : this.screenW - this.peekPx;
        this.hidden = true;
        this.isFaded = false;
        applyLp();
        View view = this.ballRoot;
        if (view != null) {
            view.setAlpha(Math.min(0.5f, AppPrefs.INSTANCE.ballAlpha(this) / 255.0f));
        }
        saveBallPosition();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void restoreFromEdge() {
        if (this.hidden && this.attached) {
            WindowManager.LayoutParams layoutParams = this.lp;
            Intrinsics.checkNotNull(layoutParams);
            int centerX = layoutParams.x + (((int) this.ballSizePx) / 2);
            WindowManager.LayoutParams layoutParams2 = this.lp;
            Intrinsics.checkNotNull(layoutParams2);
            layoutParams2.x = centerX < this.screenW / 2 ? dp(2.0f) : (this.screenW - ((int) this.ballSizePx)) - dp(2.0f);
            this.hidden = false;
            this.isFaded = false;
            applyLp();
            View view = this.ballRoot;
            if (view == null) {
                return;
            }
            view.setAlpha(AppPrefs.INSTANCE.ballAlpha(this) / 255.0f);
        }
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        this.screenW = dm.widthPixels;
        this.screenH = dm.heightPixels;
        float size = computeBallSize();
        if (Math.abs(size - this.ballSizePx) > 1.0f) {
            this.ballSizePx = size;
            this.peekPx = Math.max(6, (int) (PEEK_RATIO * size));
            removeBall();
            ensureBall();
        } else if (this.attached) {
            WindowManager.LayoutParams layoutParams = this.lp;
            Intrinsics.checkNotNull(layoutParams);
            WindowManager.LayoutParams layoutParams2 = this.lp;
            Intrinsics.checkNotNull(layoutParams2);
            layoutParams.x = clampX(layoutParams2.x);
            WindowManager.LayoutParams layoutParams3 = this.lp;
            Intrinsics.checkNotNull(layoutParams3);
            WindowManager.LayoutParams layoutParams4 = this.lp;
            Intrinsics.checkNotNull(layoutParams4);
            layoutParams3.y = clampY(layoutParams4.y);
            applyLp();
        }
    }

    public final void syncEngines() {
        if (AppPrefs.INSTANCE.autoTurn(this)) {
            startReaderEngines();
        } else {
            stopReaderEngines();
        }
        updateLabel();
    }

    private final void startReaderEngines() {
        if (this.timedEngine == null) {
            this.timedEngine = new TimedTurnEngine(this);
            TimedTurnEngine timedTurnEngine = this.timedEngine;
            Intrinsics.checkNotNull(timedTurnEngine);
            timedTurnEngine.setCallback(this.timedCallback);
        }
        TimedTurnEngine timedTurnEngine2 = this.timedEngine;
        Intrinsics.checkNotNull(timedTurnEngine2);
        timedTurnEngine2.reloadInterval();
        TimedTurnEngine timedTurnEngine3 = this.timedEngine;
        Intrinsics.checkNotNull(timedTurnEngine3);
        if (!timedTurnEngine3.isRunning()) {
            TimedTurnEngine timedTurnEngine4 = this.timedEngine;
            Intrinsics.checkNotNull(timedTurnEngine4);
            timedTurnEngine4.start();
        }
        if (AppPrefs.INSTANCE.presenceRequired(this)) {
            if (checkSelfPermission("android.permission.CAMERA") == 0) {
                if (this.presenceDetector == null) {
                    this.presenceDetector = new PresenceDetector(this);
                    PresenceDetector presenceDetector = this.presenceDetector;
                    Intrinsics.checkNotNull(presenceDetector);
                    presenceDetector.setCallback(this.presenceCallback);
                }
                PresenceDetector presenceDetector2 = this.presenceDetector;
                Intrinsics.checkNotNull(presenceDetector2);
                if (!presenceDetector2.isRunning()) {
                    PresenceDetector presenceDetector3 = this.presenceDetector;
                    Intrinsics.checkNotNull(presenceDetector3);
                    presenceDetector3.start();
                    return;
                }
                return;
            }
            showTip("未授予摄像头权限：暂停检测已关闭，会一直按时翻页");
            return;
        }
        PresenceDetector presenceDetector4 = this.presenceDetector;
        if (presenceDetector4 != null) {
            presenceDetector4.stop();
        }
        TimedTurnEngine timedTurnEngine5 = this.timedEngine;
        if (timedTurnEngine5 != null) {
            timedTurnEngine5.resumeFromPresenceGate();
        }
    }

    private final void stopReaderEngines() {
        TimedTurnEngine timedTurnEngine = this.timedEngine;
        if (timedTurnEngine != null) {
            timedTurnEngine.stop();
        }
        PresenceDetector presenceDetector = this.presenceDetector;
        if (presenceDetector != null) {
            presenceDetector.stop();
        }
        View view = this.progressRing;
        if (view == null) {
            return;
        }
        view.setVisibility(8);
    }

    public final void startPresenceDetection() {
        if (AppPrefs.INSTANCE.autoTurn(this)) {
            if (checkSelfPermission("android.permission.CAMERA") != 0) {
                showTip("未授予摄像头权限，无法开启离开屏幕暂停");
                return;
            }
            if (this.timedEngine == null) {
                this.timedEngine = new TimedTurnEngine(this);
                TimedTurnEngine timedTurnEngine = this.timedEngine;
                Intrinsics.checkNotNull(timedTurnEngine);
                timedTurnEngine.setCallback(this.timedCallback);
            }
            if (this.presenceDetector == null) {
                this.presenceDetector = new PresenceDetector(this);
                PresenceDetector presenceDetector = this.presenceDetector;
                Intrinsics.checkNotNull(presenceDetector);
                presenceDetector.setCallback(this.presenceCallback);
            }
            PresenceDetector presenceDetector2 = this.presenceDetector;
            Intrinsics.checkNotNull(presenceDetector2);
            presenceDetector2.resetToPresent();
            PresenceDetector presenceDetector3 = this.presenceDetector;
            Intrinsics.checkNotNull(presenceDetector3);
            if (!presenceDetector3.isRunning()) {
                PresenceDetector presenceDetector4 = this.presenceDetector;
                Intrinsics.checkNotNull(presenceDetector4);
                presenceDetector4.start();
            }
        }
    }

    public final void stopPresenceDetection() {
        PresenceDetector presenceDetector = this.presenceDetector;
        if (presenceDetector != null) {
            presenceDetector.stop();
        }
        this.presenceDetector = null;
        AppPrefs.INSTANCE.setPresence(this, true);
        TimedTurnEngine timedTurnEngine = this.timedEngine;
        if (timedTurnEngine != null) {
            timedTurnEngine.resumeFromPresenceGate();
        }
        updateLabel();
    }

    private final void releaseAllEngines() {
        stopReaderEngines();
        PresenceDetector presenceDetector = this.presenceDetector;
        if (presenceDetector != null) {
            presenceDetector.release();
        }
        this.presenceDetector = null;
        this.timedEngine = null;
    }

    public final void onSpeedResultChanged() {
        TimedTurnEngine timedTurnEngine = this.timedEngine;
        if (timedTurnEngine != null) {
            timedTurnEngine.reloadInterval();
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%.1f", Arrays.copyOf(new Object[]{Double.valueOf(AppPrefs.INSTANCE.turnIntervalMs(this) / 1000.0d)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        showTip("翻页间隔已更新为 " + format + " 秒");
    }

    public final void onPermissionGranted() {
        refreshForegroundServiceType();
        syncEngines();
    }

    public final void onAdPageChanged(boolean ad) {
        TimedTurnEngine timedTurnEngine = this.timedEngine;
        if (timedTurnEngine != null) {
            timedTurnEngine.onAdPageChanged(ad);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void announcePresence(boolean present) {
        if (AppPrefs.INSTANCE.presenceRequired(this) && AppPrefs.INSTANCE.autoTurn(this)) {
            Boolean last = this.lastAnnouncedPresence;
            boolean directionChanged = last == null || !Intrinsics.areEqual(last, Boolean.valueOf(present));
            long now = System.currentTimeMillis();
            if (directionChanged || now - this.lastPresenceAnnounceAt >= 10000) {
                this.lastAnnouncedPresence = Boolean.valueOf(present);
                this.lastPresenceAnnounceAt = now;
                XiaoFanVoice.INSTANCE.tip(this, present ? "你回来啦，继续帮你翻页" : "你离开了，我先暂停翻页");
            }
        }
    }

    private final void toggleAutoTurn() {
        boolean on = !AppPrefs.INSTANCE.autoTurn(this);
        TurnManager.INSTANCE.setAutoTurn(this, on);
        vibrate(true);
        syncEngines();
        if (on) {
            if (!AppPrefs.INSTANCE.speedDone(this)) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format("%.1f", Arrays.copyOf(new Object[]{Double.valueOf(AppPrefs.INSTANCE.turnIntervalMs(this) / 1000.0d)}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                showTip("还没测过阅读速度，先用兜底间隔 " + format + " 秒翻页，建议先测速");
                XiaoFanVoice.INSTANCE.tip(this, "还没测速哦，先用默认速度翻，建议测一下更准");
            } else {
                StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                String msg = String.format("自动翻页已开启，每 %.1f 秒翻一页", Arrays.copyOf(new Object[]{Double.valueOf(AppPrefs.INSTANCE.turnIntervalMs(this) / 1000.0d)}, 1));
                Intrinsics.checkNotNullExpressionValue(msg, "format(...)");
                showTip(msg);
                XiaoFanVoice.INSTANCE.tip(this, msg);
            }
        } else {
            showTip("自动翻页已关闭");
            XiaoFanVoice.INSTANCE.tip(this, "自动翻页关掉了");
        }
        scheduleAutoHide();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(335544320);
        try {
            startActivity(intent);
        } catch (Throwable th) {
            toast("打开主界面失败");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateLabel() {
        String text;
        TextView label = this.ballLabel;
        if (label == null) {
            return;
        }
        if (AppPrefs.INSTANCE.autoTurn(this)) {
            TimedTurnEngine timedTurnEngine = this.timedEngine;
            boolean z = false;
            if (timedTurnEngine != null && timedTurnEngine.isPaused()) {
                z = true;
            }
            text = z ? "已暂停" : "翻页中";
        } else {
            text = "";
        }
        label.setText(text);
        label.setTextSize(2, text.length() > 2 ? 9.5f : 11.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void vibrate(boolean z) {
        if (AppPrefs.INSTANCE.silent(this) || !AppPrefs.INSTANCE.vibrateOn(this)) {
            return;
        }
        try {
            Vibrator vibrator = this.vibrator;
            Vibrator vibrator2 = null;
            if (vibrator == null) {
                Intrinsics.throwUninitializedPropertyAccessException("vibrator");
                vibrator = null;
            }
            if (vibrator.hasVibrator()) {
                long duration = z ? 22L : 45L;
                if (Build.VERSION.SDK_INT < 26) {
                    Vibrator vibrator3 = this.vibrator;
                    if (vibrator3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("vibrator");
                    } else {
                        vibrator2 = vibrator3;
                    }
                    vibrator2.vibrate(duration);
                    return;
                }
                Vibrator vibrator4 = this.vibrator;
                if (vibrator4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("vibrator");
                } else {
                    vibrator2 = vibrator4;
                }
                vibrator2.vibrate(VibrationEffect.createOneShot(duration, -1));
            }
        } catch (Throwable th) {
        }
    }

    private final void flashBall() {
        final ImageView view;
        ImageView imageView = this.ballAvatar;
        if (imageView != null) {
            view = imageView;
        } else {
            view = this.ballRoot;
            if (view == null) {
                return;
            }
        }
        view.animate().cancel();
        view.animate().scaleX(1.18f).scaleY(1.18f).setDuration(90L).withEndAction(new Runnable() { // from class: com.xiaofan.bangfan.FloatBallService$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                FloatBallService.flashBall$lambda$4(view);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void flashBall$lambda$4(View view) {
        Intrinsics.checkNotNullParameter(view, "$view");
        view.animate().scaleX(1.0f).scaleY(1.0f).setDuration(120L).start();
    }

    public final void setListeningGlow(final boolean on) {
        this.main.post(new Runnable() { // from class: com.xiaofan.bangfan.FloatBallService$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                FloatBallService.setListeningGlow$lambda$6(FloatBallService.this, on);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setListeningGlow$lambda$6(FloatBallService this$0, boolean $on) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        final View v = this$0.listenGlow;
        if (v == null) {
            return;
        }
        if ($on) {
            this$0.cancelAutoHide();
            this$0.isFaded = false;
            this$0.hidden = false;
            View view = this$0.ballRoot;
            if (view != null) {
                view.setAlpha(1.0f);
            }
            v.setVisibility(0);
            ValueAnimator valueAnimator = this$0.listenPulse;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            ValueAnimator anim = ValueAnimator.ofFloat(0.35f, 1.0f);
            anim.setDuration(700L);
            anim.setRepeatMode(2);
            anim.setRepeatCount(-1);
            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.xiaofan.bangfan.FloatBallService$$ExternalSyntheticLambda6
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                    FloatBallService.setListeningGlow$lambda$6$lambda$5(v, valueAnimator2);
                }
            });
            anim.start();
            this$0.listenPulse = anim;
            return;
        }
        ValueAnimator valueAnimator2 = this$0.listenPulse;
        if (valueAnimator2 != null) {
            valueAnimator2.cancel();
        }
        this$0.listenPulse = null;
        v.setVisibility(8);
        v.setAlpha(1.0f);
        this$0.scheduleAutoHide();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setListeningGlow$lambda$6$lambda$5(View v, ValueAnimator va) {
        Intrinsics.checkNotNullParameter(v, "$v");
        Intrinsics.checkNotNullParameter(va, "va");
        Object animatedValue = va.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Float");
        v.setAlpha(((Float) animatedValue).floatValue());
    }

    public final void showTip(final String text) {
        String str = text;
        if (str == null || str.length() == 0) {
            return;
        }
        this.main.post(new Runnable() { // from class: com.xiaofan.bangfan.FloatBallService$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                FloatBallService.showTip$lambda$11(FloatBallService.this, text);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showTip$lambda$11(final FloatBallService this$0, String $text) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            if (this$0.attached && Settings.canDrawOverlays(this$0)) {
                TextView it = this$0.toastView;
                WindowManager windowManager = null;
                if (it != null) {
                    try {
                        WindowManager windowManager2 = this$0.wm;
                        if (windowManager2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("wm");
                            windowManager2 = null;
                        }
                        windowManager2.removeView(it);
                    } catch (Throwable th) {
                    }
                }
                this$0.toastView = null;
                TextView tv = new TextView(this$0);
                tv.setText($text);
                tv.setTextColor(-1);
                tv.setTextSize(2, 12.5f);
                tv.setPadding(this$0.dp(12.0f), this$0.dp(7.0f), this$0.dp(12.0f), this$0.dp(7.0f));
                GradientDrawable bg = new GradientDrawable();
                bg.setColor(-433049803);
                bg.setCornerRadius(this$0.dp(14.0f));
                tv.setBackground(bg);
                WindowManager.LayoutParams params = new WindowManager.LayoutParams(-2, -2, Build.VERSION.SDK_INT >= 26 ? 2038 : 2002, 520, -3);
                params.gravity = 8388659;
                int dp = this$0.dp(8.0f);
                WindowManager.LayoutParams layoutParams = this$0.lp;
                Intrinsics.checkNotNull(layoutParams);
                params.x = Math.max(dp, Math.min(layoutParams.x, Math.max(0, this$0.screenW - this$0.dp(40.0f))));
                int dp2 = this$0.dp(24.0f);
                WindowManager.LayoutParams layoutParams2 = this$0.lp;
                Intrinsics.checkNotNull(layoutParams2);
                params.y = Math.max(dp2, layoutParams2.y - this$0.dp(50.0f));
                WindowManager windowManager3 = this$0.wm;
                if (windowManager3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("wm");
                } else {
                    windowManager = windowManager3;
                }
                windowManager.addView(tv, params);
                this$0.toastView = tv;
                Runnable it2 = this$0.tipHideTask;
                if (it2 != null) {
                    this$0.main.removeCallbacks(it2);
                }
                this$0.tipHideTask = new Runnable() { // from class: com.xiaofan.bangfan.FloatBallService$$ExternalSyntheticLambda4
                    @Override // java.lang.Runnable
                    public final void run() {
                        FloatBallService.showTip$lambda$11$lambda$10(FloatBallService.this);
                    }
                };
                Handler handler = this$0.main;
                Runnable runnable = this$0.tipHideTask;
                Intrinsics.checkNotNull(runnable);
                handler.postDelayed(runnable, 2200L);
            }
        } catch (Throwable th2) {
            this$0.toast($text);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showTip$lambda$11$lambda$10(FloatBallService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TextView it = this$0.toastView;
        if (it != null) {
            try {
                WindowManager windowManager = this$0.wm;
                if (windowManager == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("wm");
                    windowManager = null;
                }
                windowManager.removeView(it);
            } catch (Throwable th) {
            }
        }
        this$0.toastView = null;
    }

    private final void toast(final String text) {
        this.main.post(new Runnable() { // from class: com.xiaofan.bangfan.FloatBallService$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                FloatBallService.toast$lambda$12(FloatBallService.this, text);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void toast$lambda$12(FloatBallService this$0, String text) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(text, "$text");
        try {
            Toast.makeText(this$0, text, 0).show();
        } catch (Throwable th) {
        }
    }

    @Override // com.xiaofan.bangfan.TurnManager.Listener
    public void onAutoTurnChanged(boolean on) {
        updateLabel();
    }

    @Override // com.xiaofan.bangfan.TurnManager.Listener
    public void onTurned(String reason) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        AppPrefs.INSTANCE.bumpTotalTurns(this);
        XiaoFanVoice.INSTANCE.turn(this, "翻页啦");
    }

    @Override // com.xiaofan.bangfan.TurnManager.Listener
    public void onTurnFailed(String reason) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        showTip("翻页失败：" + reason);
    }

    @Override // com.xiaofan.bangfan.TurnManager.Listener
    public void onAdDetected(String reason) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        showTip("检测到广告页，正在处理…");
    }

    @Override // com.xiaofan.bangfan.TurnManager.Listener
    public void onAdCountdown(int seconds) {
        showTip("广告倒计时 " + seconds + " 秒，结束后自动翻页");
    }

    @Override // com.xiaofan.bangfan.TurnManager.Listener
    public void onPresenceChanged(boolean present) {
        updateLabel();
    }

    @Override // com.xiaofan.bangfan.TurnManager.Listener
    public void onTimedTurnChanged(boolean running, long interval) {
        updateLabel();
    }

    @Override // com.xiaofan.bangfan.TurnManager.Listener
    public void onServiceReady(boolean ready) {
        if (!ready) {
            showTip("无障碍服务已断开，自动翻页暂停");
            XiaoFanVoice.INSTANCE.warn(this, "无障碍服务断开了，翻页暂停");
        }
    }

    private final void createChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            Object systemService = getSystemService("notification");
            NotificationManager nm = systemService instanceof NotificationManager ? (NotificationManager) systemService : null;
            if (nm == null) {
                return;
            }
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, getString(R.string.notif_channel), 2);
            channel.setDescription("保持小翻帮翻在后台运行");
            channel.setShowBadge(false);
            try {
                nm.createNotificationChannel(channel);
            } catch (Throwable th) {
            }
        }
    }

    private final Notification buildNotification() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(268435456);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
        Notification.Builder builder = Build.VERSION.SDK_INT >= 26 ? new Notification.Builder(this, CHANNEL_ID) : new Notification.Builder(this);
        builder.setContentTitle(getString(R.string.notif_title)).setContentText("小翻助手运行中 · 点击打开设置").setSmallIcon(17301581).setContentIntent(pi).setOngoing(true);
        Notification build = builder.build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        return build;
    }

    public final void refreshFromPrefs() {
        ensureBall();
        float size = computeBallSize();
        if (Math.abs(size - this.ballSizePx) > 1.0f) {
            this.ballSizePx = size;
            this.peekPx = Math.max(6, (int) (PEEK_RATIO * size));
            removeBall();
            ensureBall();
        }
        XiaoFanVoice.INSTANCE.applyVoiceParams(this);
        updateLabel();
        syncEngines();
        scheduleAutoHide();
    }
}
