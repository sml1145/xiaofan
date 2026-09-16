package com.xiaofan.bangfan;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.xiaofan.bangfan.AdDetector;
import com.xiaofan.bangfan.ScreenSnapshot;
import com.xiaofan.bangfan.VolumeKeyGate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
/* compiled from: PageTurnAccessibilityService.kt */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0011\n\u0002\b\u0006\u0018\u0000 Y2\u00020\u0001:\u0001YB\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u001c\u001a\u00020\u001dJ\u0016\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 J\b\u0010\"\u001a\u0004\u0018\u00010\fJ\u0010\u0010#\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u0006H\u0002J \u0010%\u001a\u00020\u001d2\u0006\u0010&\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\f2\u0006\u0010(\u001a\u00020\u0004H\u0002J \u0010)\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\f2\u0006\u0010*\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0004H\u0002J\u0006\u0010,\u001a\u00020\u0006J\u0006\u0010-\u001a\u00020\u0006J\u0006\u0010.\u001a\u00020\u0006J\n\u0010/\u001a\u0004\u0018\u00010\fH\u0002J\u0010\u00100\u001a\u00020\u001d2\u0006\u00101\u001a\u000202H\u0002J\u0006\u00103\u001a\u00020\u0004J\u0006\u00104\u001a\u00020\u0004J\u0012\u00105\u001a\u00020\u00042\b\u00106\u001a\u0004\u0018\u00010\fH\u0002J\u0012\u00107\u001a\u00020\u00042\b\u00106\u001a\u0004\u0018\u00010\fH\u0002J\u0006\u00108\u001a\u00020\u0004J\u0012\u00109\u001a\u00020\u001d2\b\u0010:\u001a\u0004\u0018\u00010;H\u0016J\b\u0010<\u001a\u00020\u001dH\u0016J\b\u0010=\u001a\u00020\u001dH\u0016J\u0012\u0010>\u001a\u00020\u00042\b\u0010:\u001a\u0004\u0018\u00010?H\u0014J\b\u0010@\u001a\u00020\u001dH\u0014J\u0012\u0010A\u001a\u00020\u00042\b\u0010B\u001a\u0004\u0018\u00010CH\u0016J\u000e\u0010D\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\fJ\u000e\u0010E\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\fJ\u000e\u0010F\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\fJ\u000e\u0010G\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\fJ\u0016\u0010H\u001a\u00020\u00042\u0006\u0010&\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\fJ\n\u0010I\u001a\u0004\u0018\u00010\fH\u0002J\u0010\u0010J\u001a\u00020\u001d2\u0006\u0010K\u001a\u00020\u0004H\u0002J\b\u0010L\u001a\u00020\u001dH\u0002J\b\u0010M\u001a\u00020\u001dH\u0002J\b\u0010N\u001a\u00020\u001dH\u0002J\u0006\u0010O\u001a\u00020\u001dJ\b\u0010P\u001a\u00020\u001dH\u0002J\u0010\u0010Q\u001a\u00020\u00042\u0006\u0010*\u001a\u00020\u0004H\u0002J\u001f\u0010R\u001a\u00020\u00042\u0012\u0010S\u001a\n\u0012\u0006\b\u0001\u0012\u00020\f0T\"\u00020\f¢\u0006\u0002\u0010UJ\u0018\u0010V\u001a\u00020\u001d2\u0006\u0010W\u001a\u00020\u00062\u0006\u0010X\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006Z"}, d2 = {"Lcom/xiaofan/bangfan/PageTurnAccessibilityService;", "Landroid/accessibilityservice/AccessibilityService;", "()V", "adPageActive", "", "adRemainingSeconds", "", "adTickTask", "Ljava/lang/Runnable;", "adWatchDeadline", "", "basePkg", "", "busy", "foregroundPkg", "lastTurnAt", "lastVerticalAt", "lastVolDownAt", "lastVolUpAt", "main", "Landroid/os/Handler;", "maxVerticalQueued", "normalCharsBase", "normalSamples", "pollTask", "screenH", "screenW", "verticalQueued", "analyzeNow", "", "clickAt", "x", "", "y", "currentForegroundPackage", "debounceVolumeKey", "keyCode", "dispatchVerticalSwipe", "up", "reason", "retry", "doTurn", "prev", "timed", "getAdRemainingSeconds", "getScreenHeight", "getScreenWidth", "getTopActivityPackage", "handleAdPage", "result", "Lcom/xiaofan/bangfan/AdDetector$Result;", "isAdPageActive", "isCurrentPageBookEnd", "isNoisePackage", "pkg", "isNonReaderPackage", "isReaderAppInForeground", "onAccessibilityEvent", NotificationCompat.CATEGORY_EVENT, "Landroid/view/accessibility/AccessibilityEvent;", "onDestroy", "onInterrupt", "onKeyEvent", "Landroid/view/KeyEvent;", "onServiceConnected", "onUnbind", "intent", "Landroid/content/Intent;", "performFastForward", "performPageTurn", "performPrevPage", "performTimedPageTurn", "performVerticalSwipe", "resolveForegroundPackage", "setAdPageActive", "active", "startAdTick", "startPolling", "stopAdTickOnly", "stopAdWatching", "stopPolling", "swipePage", "toggleReaderOption", "texts", "", "([Ljava/lang/String;)Z", "updateBaseline", "chars", "speedUp", "Companion", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class PageTurnAccessibilityService extends AccessibilityService {
    public static final Companion Companion = new Companion(null);
    private static final long POLL_INTERVAL_MS = 900;
    private static final String TAG = "PageTurnA11y";
    private static final long VOLUME_KEY_DEBOUNCE_MS = 300;
    private static volatile PageTurnAccessibilityService instance;
    private volatile boolean adPageActive;
    private Runnable adTickTask;
    private volatile long adWatchDeadline;
    private volatile boolean busy;
    private volatile long lastTurnAt;
    private volatile long lastVerticalAt;
    private long lastVolDownAt;
    private long lastVolUpAt;
    private int normalCharsBase;
    private int normalSamples;
    private Runnable pollTask;
    private int screenH;
    private int screenW;
    private int verticalQueued;
    private final Handler main = new Handler(Looper.getMainLooper());
    private volatile int adRemainingSeconds = -1;
    private volatile String foregroundPkg = "";
    private String basePkg = "";
    private final int maxVerticalQueued = 8;

    /* compiled from: PageTurnAccessibilityService.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[VolumeKeyGate.Decision.values().length];
            try {
                iArr[VolumeKeyGate.Decision.CONSUME_BT_REMOTE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[VolumeKeyGate.Decision.PASS_TO_SYSTEM.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[VolumeKeyGate.Decision.CONSUME_LOCAL_TURN.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* compiled from: PageTurnAccessibilityService.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u0004\u0018\u00010\tJ\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/xiaofan/bangfan/PageTurnAccessibilityService$Companion;", "", "()V", "POLL_INTERVAL_MS", "", "TAG", "", "VOLUME_KEY_DEBOUNCE_MS", "instance", "Lcom/xiaofan/bangfan/PageTurnAccessibilityService;", "getInstance", "isRunning", "", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final PageTurnAccessibilityService getInstance() {
            return PageTurnAccessibilityService.instance;
        }

        public final boolean isRunning() {
            return PageTurnAccessibilityService.instance != null;
        }
    }

    @Override // android.accessibilityservice.AccessibilityService
    public void onInterrupt() {
    }

    @Override // android.accessibilityservice.AccessibilityService
    protected void onServiceConnected() {
        super.onServiceConnected();
        instance = this;
        DisplayMetrics dm = getResources().getDisplayMetrics();
        this.screenW = dm.widthPixels;
        this.screenH = dm.heightPixels;
        startPolling();
        TurnManager.INSTANCE.notifyServiceReady(true);
        Log.i(TAG, "accessibility service connected");
    }

    @Override // android.accessibilityservice.AccessibilityService
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event != null && event.getEventType() == 32) {
            CharSequence it = event.getPackageName();
            if (it != null) {
                this.foregroundPkg = it.toString();
            }
            analyzeNow();
        }
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        stopPolling();
        stopAdWatching();
        instance = null;
        TurnManager.INSTANCE.notifyServiceReady(false);
        return super.onUnbind(intent);
    }

    @Override // android.app.Service
    public void onDestroy() {
        stopPolling();
        stopAdWatching();
        instance = null;
        TurnManager.INSTANCE.notifyServiceReady(false);
        super.onDestroy();
    }

    @Override // android.accessibilityservice.AccessibilityService
    protected boolean onKeyEvent(KeyEvent event) {
        boolean isUp;
        boolean z;
        boolean z2;
        if (event == null) {
            return false;
        }
        int keyCode = event.getKeyCode();
        if (keyCode != 24 && keyCode != 25) {
            return false;
        }
        if (keyCode != 24) {
            isUp = false;
        } else {
            isUp = true;
        }
        boolean btActive = BluetoothRemoteService.Companion.volumeRemoteActive();
        VolumeKeyGate volumeKeyGate = VolumeKeyGate.INSTANCE;
        try {
            z = AppPrefs.INSTANCE.volumeKeyCtrl(this);
        } catch (Throwable th) {
            z = false;
        }
        try {
            z2 = TurnManager.INSTANCE.isAutoTurnEnabled(this);
        } catch (Throwable th2) {
            z2 = false;
        }
        VolumeKeyGate.Decision decision = volumeKeyGate.decide(btActive, z, z2);
        if (event.getAction() != 0) {
            if (decision == VolumeKeyGate.Decision.PASS_TO_SYSTEM) {
                return false;
            }
            return true;
        }
        switch (WhenMappings.$EnumSwitchMapping$0[decision.ordinal()]) {
            case 1:
                if (BluetoothRemoteService.Companion.onVolumeKey(isUp)) {
                    Log.i(TAG, "volume key -> bluetooth remote " + (isUp ? "prev" : "next"));
                    return true;
                }
                return true;
            case 2:
                return false;
            case 3:
                if (!debounceVolumeKey(keyCode)) {
                    return true;
                }
                boolean ok = isUp ? performPrevPage("音量键上一页") : performPageTurn("音量键翻页");
                Log.i(TAG, "reader volume key -> turn " + (ok ? "ok" : "failed"));
                return true;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private final boolean debounceVolumeKey(int keyCode) {
        long now = SystemClock.uptimeMillis();
        if (keyCode == 24) {
            if (now - this.lastVolUpAt < VOLUME_KEY_DEBOUNCE_MS) {
                return false;
            }
            this.lastVolUpAt = now;
            return true;
        } else if (now - this.lastVolDownAt < VOLUME_KEY_DEBOUNCE_MS) {
            return false;
        } else {
            this.lastVolDownAt = now;
            return true;
        }
    }

    private final void startPolling() {
        stopPolling();
        Runnable runnable = new Runnable() { // from class: com.xiaofan.bangfan.PageTurnAccessibilityService$startPolling$task$1
            @Override // java.lang.Runnable
            public void run() {
                Handler handler;
                try {
                    PageTurnAccessibilityService.this.analyzeNow();
                } catch (Throwable th) {
                }
                handler = PageTurnAccessibilityService.this.main;
                handler.postDelayed(this, 900L);
            }
        };
        this.pollTask = runnable;
        this.main.postDelayed(runnable, 1200L);
    }

    private final void stopPolling() {
        Runnable it = this.pollTask;
        if (it != null) {
            this.main.removeCallbacks(it);
        }
        this.pollTask = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x00c6 A[Catch: all -> 0x00ec, TryCatch #4 {all -> 0x00ec, blocks: (B:40:0x0081, B:42:0x009a, B:48:0x00a6, B:50:0x00ad, B:52:0x00b5, B:54:0x00ba, B:60:0x00c6, B:65:0x00d4), top: B:99:0x0081 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00f8 A[Catch: all -> 0x0125, TRY_LEAVE, TryCatch #2 {all -> 0x0125, blocks: (B:27:0x0056, B:34:0x006b, B:36:0x0075, B:68:0x00ed, B:70:0x00f8), top: B:95:0x0056 }] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x00fd A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void analyzeNow() {
        /*
            Method dump skipped, instructions count: 305
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaofan.bangfan.PageTurnAccessibilityService.analyzeNow():void");
    }

    private final void setAdPageActive(boolean active) {
        if (this.adPageActive == active) {
            return;
        }
        this.adPageActive = active;
        FloatBallService companion = FloatBallService.Companion.getInstance();
        if (companion != null) {
            companion.onAdPageChanged(active);
        }
    }

    public final boolean isAdPageActive() {
        return this.adPageActive;
    }

    private final void handleAdPage(AdDetector.Result result) {
        long timeout = AppPrefs.INSTANCE.adTimeoutMs(this);
        if (result.getSkipButtonBounds() != null && clickAt(result.getSkipButtonBounds().centerX(), result.getSkipButtonBounds().centerY())) {
            stopAdWatching();
            this.lastTurnAt = System.currentTimeMillis();
            BookStore.INSTANCE.bumpTurnOnLatest(this);
            TurnManager.INSTANCE.notifyTurned(this, "已跳过广告");
        } else if (result.getCountdownSeconds() > 0) {
            this.adRemainingSeconds = result.getCountdownSeconds();
            this.adWatchDeadline = System.currentTimeMillis() + (result.getCountdownSeconds() * 1000);
            TurnManager.INSTANCE.notifyAdCountdown(this, result.getCountdownSeconds());
            startAdTick();
        } else if (this.adWatchDeadline == 0) {
            this.adWatchDeadline = System.currentTimeMillis() + timeout;
            startAdTick();
        }
    }

    private final void startAdTick() {
        stopAdTickOnly();
        Runnable runnable = new Runnable() { // from class: com.xiaofan.bangfan.PageTurnAccessibilityService$startAdTick$task$1
            @Override // java.lang.Runnable
            public void run() {
                long j;
                Handler handler;
                Handler handler2;
                int i;
                int i2;
                String str;
                long now = System.currentTimeMillis();
                AccessibilityNodeInfo root = null;
                int countdown = -1;
                try {
                    root = PageTurnAccessibilityService.this.getRootInActiveWindow();
                    AdDetector adDetector = AdDetector.INSTANCE;
                    ScreenSnapshot.Companion companion = ScreenSnapshot.Companion;
                    i = PageTurnAccessibilityService.this.screenW;
                    i2 = PageTurnAccessibilityService.this.screenH;
                    str = PageTurnAccessibilityService.this.foregroundPkg;
                    AdDetector.CountdownHit hit = adDetector.findCountdown(companion.capture(root, i, i2, str));
                    countdown = hit != null ? hit.getSeconds() : -1;
                } catch (Throwable th) {
                }
                if (root != null) {
                    AccessibilityNodeInfo it = root;
                    try {
                        it.recycle();
                    } catch (Throwable th2) {
                    }
                }
                if (countdown > 0) {
                    PageTurnAccessibilityService.this.adRemainingSeconds = countdown;
                    PageTurnAccessibilityService.this.adWatchDeadline = (countdown * 1000) + now;
                    TurnManager.INSTANCE.notifyAdCountdown(PageTurnAccessibilityService.this, countdown);
                    handler2 = PageTurnAccessibilityService.this.main;
                    handler2.postDelayed(this, 500L);
                    return;
                }
                j = PageTurnAccessibilityService.this.adWatchDeadline;
                if (now < j) {
                    handler = PageTurnAccessibilityService.this.main;
                    handler.postDelayed(this, 400L);
                    return;
                }
                PageTurnAccessibilityService.this.stopAdWatching();
                PageTurnAccessibilityService.this.performPageTurn("广告倒计时结束");
            }
        };
        this.adTickTask = runnable;
        this.main.postDelayed(runnable, 400L);
    }

    private final void stopAdTickOnly() {
        Runnable it = this.adTickTask;
        if (it != null) {
            this.main.removeCallbacks(it);
        }
        this.adTickTask = null;
    }

    public final void stopAdWatching() {
        stopAdTickOnly();
        this.adWatchDeadline = 0L;
        this.adRemainingSeconds = -1;
    }

    public final int getAdRemainingSeconds() {
        return this.adRemainingSeconds;
    }

    public final boolean performPageTurn(String reason) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        return doTurn(reason, false, false);
    }

    public final boolean performPrevPage(String reason) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        return doTurn(reason, true, false);
    }

    public final boolean performTimedPageTurn(String reason) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        return doTurn(reason, false, true);
    }

    public final boolean isCurrentPageBookEnd() {
        String pkg;
        ScreenSnapshot snapshot;
        if (this.screenW <= 0 || this.screenH <= 0) {
            DisplayMetrics dm = getResources().getDisplayMetrics();
            this.screenW = dm.widthPixels;
            this.screenH = dm.heightPixels;
        }
        try {
            AccessibilityNodeInfo root = getRootInActiveWindow();
            if (root == null || (snapshot = ScreenSnapshot.Companion.capture(root, this.screenW, this.screenH, (pkg = resolveForegroundPackage()))) == null) {
                return false;
            }
            String currentPkg = pkg == null ? "" : pkg;
            if (!Intrinsics.areEqual(this.basePkg, currentPkg)) {
                this.basePkg = currentPkg;
                this.normalCharsBase = 0;
                this.normalSamples = 0;
            }
            boolean shouldSpeedUp = AdDetector.INSTANCE.shouldSpeedUp(snapshot, this.normalCharsBase, this.normalSamples);
            int bodyChars = snapshot.bodyCharCount();
            updateBaseline(bodyChars, shouldSpeedUp);
            Log.i(TAG, "book-end check: body=" + bodyChars + " base=" + this.normalCharsBase + " samples=" + this.normalSamples + " speedUp=" + shouldSpeedUp);
            return shouldSpeedUp;
        } catch (Throwable th) {
            Log.w(TAG, "book-end detect failed", th);
            return false;
        }
    }

    private final void updateBaseline(int chars, boolean speedUp) {
        if (speedUp || chars <= 0) {
            return;
        }
        if (this.normalCharsBase <= 0) {
            this.normalCharsBase = chars;
            this.normalSamples = 1;
        } else if (this.normalSamples < 5) {
            this.normalCharsBase = ((this.normalCharsBase * this.normalSamples) + chars) / (this.normalSamples + 1);
            this.normalSamples++;
        } else {
            this.normalCharsBase = (int) ((this.normalCharsBase * 0.8f) + (chars * 0.2f));
        }
    }

    private final boolean doTurn(String reason, boolean prev, boolean timed) {
        if (this.busy) {
            return false;
        }
        if ((!timed || isReaderAppInForeground()) && System.currentTimeMillis() - this.lastTurnAt >= Math.min(1200L, AppPrefs.INSTANCE.turnIntervalMs(this) / 3)) {
            if (this.screenW <= 0 || this.screenH <= 0) {
                DisplayMetrics dm = getResources().getDisplayMetrics();
                this.screenW = dm.widthPixels;
                this.screenH = dm.heightPixels;
            }
            this.busy = true;
            String mode = AppPrefs.INSTANCE.turnMode(this);
            String pkg = resolveForegroundPackage();
            boolean ok = Intrinsics.areEqual(mode, "click") ? clickAt((int) (this.screenW * 0.8f), (int) (this.screenH * 0.55f)) : swipePage(prev);
            if (ok) {
                this.lastTurnAt = System.currentTimeMillis();
                BookStore.INSTANCE.bumpTurnOnLatest(this);
                String str = pkg;
                if (!(str == null || str.length() == 0) && !Intrinsics.areEqual(getPackageName(), pkg)) {
                    AppKnowledge.INSTANCE.remember(this, pkg, 1);
                }
                TurnManager.INSTANCE.notifyTurned(this, reason);
            } else {
                TurnManager.INSTANCE.notifyTurnFailed(this, reason);
            }
            this.busy = false;
            return ok;
        }
        return false;
    }

    private final boolean swipePage(boolean prev) {
        float y = this.screenH * 0.55f;
        float f = this.screenW;
        Pair pair = prev ? TuplesKt.to(Float.valueOf(f * 0.18f), Float.valueOf(this.screenW * 0.82f)) : TuplesKt.to(Float.valueOf(f * 0.82f), Float.valueOf(this.screenW * 0.18f));
        float fromX = ((Number) pair.component1()).floatValue();
        float toX = ((Number) pair.component2()).floatValue();
        Path path = new Path();
        path.moveTo(fromX, y);
        path.lineTo(toX, y);
        return dispatchGesture(new GestureDescription.Builder().addStroke(new GestureDescription.StrokeDescription(path, 0L, 260L)).build(), null, this.main);
    }

    public final boolean performVerticalSwipe(final boolean up, final String reason) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        if (this.screenW <= 0 || this.screenH <= 0) {
            DisplayMetrics dm = getResources().getDisplayMetrics();
            this.screenW = dm.widthPixels;
            this.screenH = dm.heightPixels;
        }
        if (this.verticalQueued >= this.maxVerticalQueued) {
            Log.w(TAG, "vertical swipe queue full, drop " + reason);
            return false;
        }
        this.verticalQueued++;
        long wait = RangesKt.coerceAtLeast((this.lastVerticalAt + 210) - SystemClock.uptimeMillis(), 0L);
        this.main.postDelayed(new Runnable() { // from class: com.xiaofan.bangfan.PageTurnAccessibilityService$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                PageTurnAccessibilityService.performVerticalSwipe$lambda$5(PageTurnAccessibilityService.this, up, reason);
            }
        }, wait);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void performVerticalSwipe$lambda$5(PageTurnAccessibilityService this$0, boolean $up, String reason) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(reason, "$reason");
        this$0.verticalQueued--;
        this$0.lastVerticalAt = SystemClock.uptimeMillis();
        this$0.dispatchVerticalSwipe($up, reason, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void dispatchVerticalSwipe(final boolean up, final String reason, boolean retry) {
        boolean dispatched;
        float x = this.screenW * 0.5f;
        float f = this.screenH;
        Pair pair = up ? TuplesKt.to(Float.valueOf(f * 0.8f), Float.valueOf(this.screenH * 0.26f)) : TuplesKt.to(Float.valueOf(f * 0.26f), Float.valueOf(this.screenH * 0.8f));
        float fromY = ((Number) pair.component1()).floatValue();
        float toY = ((Number) pair.component2()).floatValue();
        Path path = new Path();
        path.moveTo(x, fromY);
        path.lineTo(x, toY);
        GestureDescription gesture = new GestureDescription.Builder().addStroke(new GestureDescription.StrokeDescription(path, 0L, 210L)).build();
        PageTurnAccessibilityService$dispatchVerticalSwipe$cb$1 cb = new PageTurnAccessibilityService$dispatchVerticalSwipe$cb$1(up, reason, retry, this);
        try {
            dispatched = dispatchGesture(gesture, cb, this.main);
        } catch (Throwable th) {
            Log.w(TAG, "vertical swipe dispatch threw", th);
            dispatched = false;
        }
        if (dispatched || !retry) {
            return;
        }
        this.main.postDelayed(new Runnable() { // from class: com.xiaofan.bangfan.PageTurnAccessibilityService$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                PageTurnAccessibilityService.dispatchVerticalSwipe$lambda$6(PageTurnAccessibilityService.this, up, reason);
            }
        }, 90L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void dispatchVerticalSwipe$lambda$6(PageTurnAccessibilityService this$0, boolean $up, String reason) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(reason, "$reason");
        this$0.dispatchVerticalSwipe($up, reason, false);
    }

    public final boolean performFastForward(String reason) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        if (this.screenW <= 0 || this.screenH <= 0) {
            DisplayMetrics dm = getResources().getDisplayMetrics();
            this.screenW = dm.widthPixels;
            this.screenH = dm.heightPixels;
        }
        final float x = this.screenW * 0.74f;
        final float y = this.screenH * 0.5f;
        boolean first = clickAt(x, y);
        this.main.postDelayed(new Runnable() { // from class: com.xiaofan.bangfan.PageTurnAccessibilityService$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                PageTurnAccessibilityService.performFastForward$lambda$7(PageTurnAccessibilityService.this, x, y);
            }
        }, 110L);
        Log.i(TAG, "fast-forward double tap reason=" + reason + " scheduled=" + first);
        return first;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void performFastForward$lambda$7(PageTurnAccessibilityService this$0, float $x, float $y) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.clickAt($x, $y);
    }

    public final boolean clickAt(float x, float y) {
        Path path = new Path();
        path.moveTo(x, y);
        path.lineTo(x + 1.0f, 1.0f + y);
        return dispatchGesture(new GestureDescription.Builder().addStroke(new GestureDescription.StrokeDescription(path, 0L, 60L)).build(), null, this.main);
    }

    public final boolean toggleReaderOption(String... texts) {
        AccessibilityNodeInfo rootInActiveWindow;
        Intrinsics.checkNotNullParameter(texts, "texts");
        AccessibilityNodeInfo root = null;
        boolean z = false;
        try {
            rootInActiveWindow = getRootInActiveWindow();
        } catch (Throwable th) {
            if (root != null) {
                AccessibilityNodeInfo it = root;
                try {
                    it.recycle();
                } catch (Throwable th2) {
                }
            }
        }
        if (rootInActiveWindow == null) {
            return false;
        }
        root = rootInActiveWindow;
        Rect bounds = ScreenSnapshot.Companion.capture(root, this.screenW, this.screenH, this.foregroundPkg).findNode((String[]) Arrays.copyOf(texts, texts.length));
        if (bounds == null) {
            try {
                root.recycle();
            } catch (Throwable th3) {
            }
            return false;
        } else if (bounds.width() <= 0) {
            try {
                root.recycle();
            } catch (Throwable th4) {
            }
            return false;
        } else {
            z = clickAt(bounds.centerX(), bounds.centerY());
            try {
                root.recycle();
            } catch (Throwable th5) {
            }
            return z;
        }
    }

    public final int getScreenWidth() {
        return this.screenW;
    }

    public final int getScreenHeight() {
        return this.screenH;
    }

    public final boolean isReaderAppInForeground() {
        String pkg = resolveForegroundPackage();
        String str = pkg;
        if ((str == null || str.length() == 0) || Intrinsics.areEqual(getPackageName(), pkg) || AppPrefs.INSTANCE.isExcluded(this, pkg)) {
            return false;
        }
        int classify = AppKnowledge.INSTANCE.classify(this, pkg);
        return classify == 1 || !isNonReaderPackage(pkg);
    }

    public final String currentForegroundPackage() {
        return resolveForegroundPackage();
    }

    private final String resolveForegroundPackage() {
        AccessibilityNodeInfo root;
        try {
            AccessibilityNodeInfo root2 = getRootInActiveWindow();
            if (root2 != null) {
                CharSequence pkg = root2.getPackageName();
                try {
                    root2.recycle();
                } catch (Throwable th) {
                }
                if (pkg != null) {
                    if ((pkg.length() > 0) && !isNoisePackage(pkg.toString())) {
                        return pkg.toString();
                    }
                }
            }
        } catch (Throwable th2) {
        }
        try {
            List windows = getWindows();
            if (windows != null) {
                String focused = null;
                String active = null;
                for (AccessibilityWindowInfo win : windows) {
                    if (win != null) {
                        try {
                            if (win.getType() == 1 && (root = win.getRoot()) != null) {
                                CharSequence packageName = root.getPackageName();
                                String pkg2 = (packageName == null || (pkg2 = packageName.toString()) == null) ? "" : "";
                                try {
                                    root.recycle();
                                } catch (Throwable th3) {
                                }
                                if ((pkg2.length() > 0) && !isNoisePackage(pkg2)) {
                                    if (win.isFocused() && focused == null) {
                                        focused = pkg2;
                                    }
                                    if (win.isActive() && active == null) {
                                        active = pkg2;
                                    }
                                }
                            }
                        } catch (Throwable th4) {
                        }
                    }
                }
                if (focused != null) {
                    return focused;
                }
                if (active != null) {
                    return active;
                }
            }
        } catch (Throwable th5) {
        }
        String top = getTopActivityPackage();
        String str = top;
        if ((str == null || str.length() == 0) || isNoisePackage(top)) {
            if (!(this.foregroundPkg.length() > 0) || isNoisePackage(this.foregroundPkg)) {
                return null;
            }
            return this.foregroundPkg;
        }
        return top;
    }

    private final boolean isNoisePackage(String pkg) {
        String str = pkg;
        if ((str == null || str.length() == 0) || StringsKt.startsWith$default(pkg, "android:", false, 2, (Object) null) || Intrinsics.areEqual(pkg, "android")) {
            return true;
        }
        Locale ENGLISH = Locale.ENGLISH;
        Intrinsics.checkNotNullExpressionValue(ENGLISH, "ENGLISH");
        String lower = pkg.toLowerCase(ENGLISH);
        Intrinsics.checkNotNullExpressionValue(lower, "toLowerCase(...)");
        String[] noise = {"com.google.android.gms", "com.google.android.googlequicksearchbox", "com.google.android.providers", "inputmethod", "ime", "com.android.systemui", "com.android.internal", "wallpaper", "screensaver", "android.ext.shared"};
        for (String n : noise) {
            if (StringsKt.contains$default((CharSequence) lower, (CharSequence) n, false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0036 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0037 A[Catch: all -> 0x0069, TryCatch #0 {all -> 0x0069, blocks: (B:3:0x0002, B:5:0x000c, B:9:0x0014, B:11:0x002a, B:18:0x0037, B:19:0x003c, B:21:0x0042, B:24:0x0054, B:28:0x0064), top: B:32:0x0002 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.lang.String getTopActivityPackage() {
        /*
            r12 = this;
            r0 = 0
            java.lang.String r1 = "usagestats"
            java.lang.Object r1 = r12.getSystemService(r1)     // Catch: java.lang.Throwable -> L69
            boolean r2 = r1 instanceof android.app.usage.UsageStatsManager     // Catch: java.lang.Throwable -> L69
            if (r2 == 0) goto L10
            android.app.usage.UsageStatsManager r1 = (android.app.usage.UsageStatsManager) r1     // Catch: java.lang.Throwable -> L69
            r2 = r1
            goto L11
        L10:
            r2 = r0
        L11:
            if (r2 != 0) goto L14
            return r0
        L14:
            long r3 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L69
            r8 = r3
            r3 = 4
            r1 = 60000(0xea60, float:8.4078E-41)
            long r4 = (long) r1     // Catch: java.lang.Throwable -> L69
            long r4 = r8 - r4
            r6 = r8
            java.util.List r1 = r2.queryUsageStats(r3, r4, r6)     // Catch: java.lang.Throwable -> L69
            r3 = r1
            java.util.Collection r3 = (java.util.Collection) r3     // Catch: java.lang.Throwable -> L69
            if (r3 == 0) goto L33
            boolean r3 = r3.isEmpty()     // Catch: java.lang.Throwable -> L69
            if (r3 == 0) goto L31
            goto L33
        L31:
            r3 = 0
            goto L34
        L33:
            r3 = 1
        L34:
            if (r3 == 0) goto L37
            return r0
        L37:
            r3 = 0
            java.util.Iterator r4 = r1.iterator()     // Catch: java.lang.Throwable -> L69
        L3c:
            boolean r5 = r4.hasNext()     // Catch: java.lang.Throwable -> L69
            if (r5 == 0) goto L62
            java.lang.Object r5 = r4.next()     // Catch: java.lang.Throwable -> L69
            android.app.usage.UsageStats r5 = (android.app.usage.UsageStats) r5     // Catch: java.lang.Throwable -> L69
            long r6 = r5.getLastTimeUsed()     // Catch: java.lang.Throwable -> L69
            r10 = 0
            int r6 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r6 <= 0) goto L3c
            if (r3 == 0) goto L60
            long r6 = r5.getLastTimeUsed()     // Catch: java.lang.Throwable -> L69
            long r10 = r3.getLastTimeUsed()     // Catch: java.lang.Throwable -> L69
            int r6 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r6 <= 0) goto L3c
        L60:
            r3 = r5
            goto L3c
        L62:
            if (r3 == 0) goto L6a
            java.lang.String r0 = r3.getPackageName()     // Catch: java.lang.Throwable -> L69
            goto L6a
        L69:
            r1 = move-exception
        L6a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaofan.bangfan.PageTurnAccessibilityService.getTopActivityPackage():java.lang.String");
    }

    private final boolean isNonReaderPackage(String pkg) {
        String str = pkg;
        if ((str == null || str.length() == 0) || Intrinsics.areEqual(pkg, getPackageName())) {
            return true;
        }
        String[] nonReaders = {"launcher", "home", "desktop", "systemui", "settings", "packageinstaller", "permissioncontroller", "google.android.inputmethod", "com.google.android.apps.nexuslauncher", "com.android.launcher", "trebuchet", "poco.launcher", "miui.home", "huawei.home", "oppo.launcher", "vivo.launcher", "oneplus.launcher"};
        Locale ENGLISH = Locale.ENGLISH;
        Intrinsics.checkNotNullExpressionValue(ENGLISH, "ENGLISH");
        String lower = pkg.toLowerCase(ENGLISH);
        Intrinsics.checkNotNullExpressionValue(lower, "toLowerCase(...)");
        for (String n : nonReaders) {
            if (StringsKt.contains$default((CharSequence) lower, (CharSequence) n, false, 2, (Object) null)) {
                return true;
            }
        }
        return Intrinsics.areEqual(lower, "android") || StringsKt.startsWith$default(lower, "com.android.", false, 2, (Object) null);
    }
}
