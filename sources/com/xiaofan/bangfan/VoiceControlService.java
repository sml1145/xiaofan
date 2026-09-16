package com.xiaofan.bangfan;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioRecord;
import android.media.audiofx.AcousticEchoCanceler;
import android.media.audiofx.AutomaticGainControl;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.xiaofan.bangfan.XiaoFanBrain;
import com.xiaofan.bangfan.ai.NativeAsr;
import com.xiaofan.bangfan.ai.OfflineModels;
import com.xiaofan.bangfan.ai.VoiceCommandRouter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
/* compiled from: VoiceControlService.kt */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 ,2\u00020\u0001:\u0001,B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u000eH\u0002J\u0010\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u0016\u001a\u00020\bH\u0002J\u0014\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u000eH\u0016J\"\u0010\u001c\u001a\u00020\u001d2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u001dH\u0016J\b\u0010 \u001a\u00020\u000eH\u0002J\u0010\u0010!\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020\bH\u0002J\b\u0010#\u001a\u00020\u000eH\u0002J7\u0010$\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020&2%\b\u0002\u0010'\u001a\u001f\u0012\u0013\u0012\u00110\b¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(+\u0012\u0004\u0012\u00020\u000e\u0018\u00010(H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/xiaofan/bangfan/VoiceControlService;", "Landroid/app/Service;", "()V", "asrHandle", "", "io", "Ljava/util/concurrent/ExecutorService;", "live", "", "main", "Landroid/os/Handler;", "micThread", "Ljava/lang/Thread;", "beginLive", "", "dispatch", "cmd", "Lcom/xiaofan/bangfan/ai/VoiceCommandRouter$Command;", "ensureHandleLoadedBlocking", "feedback", "text", "", "hasMic", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onDestroy", "onStartCommand", "", "flags", "startId", "runContinuous", "startAsForeground", "listening", "stopEverything", "submitTranscription", "pcm", "", "onResult", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "recognized", "Companion", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class VoiceControlService extends Service {
    public static final String ACTION_LIVE_START = "com.xiaofan.bangfan.action.LIVE_START";
    public static final String ACTION_LIVE_STOP = "com.xiaofan.bangfan.action.LIVE_STOP";
    public static final String ACTION_STOP = "com.xiaofan.bangfan.action.VOICE_STOP";
    private static final String CHANNEL_ID = "xiaofan_voice";
    public static final Companion Companion = new Companion(null);
    private static final double ENERGY_OFF = 200.0d;
    private static final double ENERGY_ON = 300.0d;
    private static final int FRAME = 320;
    private static final int MAX_UTT_FRAMES = 600;
    private static final int MIN_LOUD_FRAMES = 3;
    private static final int MIN_SPEECH_FRAMES = 6;
    private static final double NOISE_FLOOR_MAX = 220.0d;
    private static final int NOTIF_ID = 2006;
    private static final int PREROLL_FRAMES = 15;
    private static final int SAMPLE_RATE = 16000;
    private static final int SIL_TAIL_FRAMES = 25;
    private static final long TTS_TAIL_GUARD_MS = 350;
    private static volatile boolean alive;
    private static volatile boolean liveNow;
    private static volatile Companion.UiListener uiListener;
    private volatile long asrHandle;
    private final ExecutorService io;
    private volatile boolean live;
    private final Handler main;
    private Thread micThread;

    /* compiled from: VoiceControlService.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[VoiceCommandRouter.Command.values().length];
            try {
                iArr[VoiceCommandRouter.Command.NEXT_PAGE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[VoiceCommandRouter.Command.PREV_PAGE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[VoiceCommandRouter.Command.AUTO_START.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[VoiceCommandRouter.Command.PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[VoiceCommandRouter.Command.FASTER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[VoiceCommandRouter.Command.SLOWER.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[VoiceCommandRouter.Command.BALL_SHOW.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[VoiceCommandRouter.Command.BALL_HIDE.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[VoiceCommandRouter.Command.TELL_TIME.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr[VoiceCommandRouter.Command.WEATHER.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                iArr[VoiceCommandRouter.Command.CONFIRM.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                iArr[VoiceCommandRouter.Command.CANCEL.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                iArr[VoiceCommandRouter.Command.NONE.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public VoiceControlService() {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: com.xiaofan.bangfan.VoiceControlService$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                Thread io$lambda$0;
                io$lambda$0 = VoiceControlService.io$lambda$0(runnable);
                return io$lambda$0;
            }
        });
        Intrinsics.checkNotNullExpressionValue(newSingleThreadExecutor, "newSingleThreadExecutor(...)");
        this.io = newSingleThreadExecutor;
        this.main = new Handler(Looper.getMainLooper());
    }

    /* compiled from: VoiceControlService.kt */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001(B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010 \u001a\u00020\u0018J\u0006\u0010!\u001a\u00020\u0018J\u000e\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%J\u000e\u0010&\u001a\u00020#2\u0006\u0010$\u001a\u00020%J\u000e\u0010'\u001a\u00020#2\u0006\u0010$\u001a\u00020%R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001f¨\u0006)"}, d2 = {"Lcom/xiaofan/bangfan/VoiceControlService$Companion;", "", "()V", "ACTION_LIVE_START", "", "ACTION_LIVE_STOP", "ACTION_STOP", "CHANNEL_ID", "ENERGY_OFF", "", "ENERGY_ON", "FRAME", "", "MAX_UTT_FRAMES", "MIN_LOUD_FRAMES", "MIN_SPEECH_FRAMES", "NOISE_FLOOR_MAX", "NOTIF_ID", "PREROLL_FRAMES", "SAMPLE_RATE", "SIL_TAIL_FRAMES", "TTS_TAIL_GUARD_MS", "", "alive", "", "liveNow", "uiListener", "Lcom/xiaofan/bangfan/VoiceControlService$Companion$UiListener;", "getUiListener", "()Lcom/xiaofan/bangfan/VoiceControlService$Companion$UiListener;", "setUiListener", "(Lcom/xiaofan/bangfan/VoiceControlService$Companion$UiListener;)V", "isLiveListening", "isRunning", "startLive", "", "ctx", "Landroid/content/Context;", "stop", "stopLive", "UiListener", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean isRunning() {
            return VoiceControlService.alive;
        }

        public final boolean isLiveListening() {
            return VoiceControlService.liveNow;
        }

        /* compiled from: VoiceControlService.kt */
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lcom/xiaofan/bangfan/VoiceControlService$Companion$UiListener;", "", "onHeard", "", "raw", "", "cmd", "Lcom/xiaofan/bangfan/ai/VoiceCommandRouter$Command;", "onListeningChanged", "listening", "", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        /* loaded from: classes4.dex */
        public interface UiListener {
            void onHeard(String str, VoiceCommandRouter.Command command);

            void onListeningChanged(boolean z);

            /* compiled from: VoiceControlService.kt */
            @Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
            /* loaded from: classes4.dex */
            public static final class DefaultImpls {
                public static void onHeard(UiListener $this, String raw, VoiceCommandRouter.Command cmd) {
                    Intrinsics.checkNotNullParameter(raw, "raw");
                    Intrinsics.checkNotNullParameter(cmd, "cmd");
                }

                public static void onListeningChanged(UiListener $this, boolean listening) {
                }
            }
        }

        public final UiListener getUiListener() {
            return VoiceControlService.uiListener;
        }

        public final void setUiListener(UiListener uiListener) {
            VoiceControlService.uiListener = uiListener;
        }

        public final void startLive(Context ctx) {
            Intrinsics.checkNotNullParameter(ctx, "ctx");
            Intent i = new Intent(ctx, VoiceControlService.class).setAction(VoiceControlService.ACTION_LIVE_START);
            Intrinsics.checkNotNullExpressionValue(i, "setAction(...)");
            if (Build.VERSION.SDK_INT >= 26) {
                ctx.startForegroundService(i);
            } else {
                ctx.startService(i);
            }
        }

        public final void stopLive(Context ctx) {
            Intrinsics.checkNotNullParameter(ctx, "ctx");
            try {
                ctx.startService(new Intent(ctx, VoiceControlService.class).setAction(VoiceControlService.ACTION_LIVE_STOP));
            } catch (Throwable th) {
            }
        }

        public final void stop(Context ctx) {
            Intrinsics.checkNotNullParameter(ctx, "ctx");
            try {
                ctx.startService(new Intent(ctx, VoiceControlService.class).setAction(VoiceControlService.ACTION_STOP));
            } catch (Throwable th) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Thread io$lambda$0(Runnable r) {
        return new Thread(r, "xf-asr-worker");
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent != null ? intent.getAction() : null;
        if (action != null) {
            switch (action.hashCode()) {
                case 460211479:
                    if (action.equals(ACTION_LIVE_STOP)) {
                        stopEverything();
                        return 1;
                    }
                    return 1;
                case 1381640653:
                    if (action.equals(ACTION_LIVE_START)) {
                        beginLive();
                        return 1;
                    }
                    return 1;
                case 2061655469:
                    if (action.equals(ACTION_STOP)) {
                        stopEverything();
                        return 1;
                    }
                    return 1;
                default:
                    return 1;
            }
        }
        return 1;
    }

    private final boolean hasMic() {
        return checkSelfPermission("android.permission.RECORD_AUDIO") == 0;
    }

    private final void beginLive() {
        if (this.live) {
            return;
        }
        boolean micOk = hasMic();
        boolean asrOk = OfflineModels.INSTANCE.isReady(this, OfflineModels.Kind.ASR);
        Log.i("VoiceCtrl", "beginLive mic=" + micOk + " asrReady=" + asrOk);
        if (!micOk) {
            XiaoFanVoice xiaoFanVoice = XiaoFanVoice.INSTANCE;
            Context applicationContext = getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            xiaoFanVoice.tip(applicationContext, "还没有麦克风权限，到第三页开启离线声控时授权一下");
            stopSelf();
        } else if (!asrOk) {
            XiaoFanVoice xiaoFanVoice2 = XiaoFanVoice.INSTANCE;
            Context applicationContext2 = getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext2, "getApplicationContext(...)");
            xiaoFanVoice2.tip(applicationContext2, "声控识别模型还没下好，先到第三页下载离线模型");
            stopSelf();
        } else {
            alive = true;
            this.live = true;
            liveNow = true;
            startAsForeground(true);
            Companion.UiListener uiListener2 = uiListener;
            if (uiListener2 != null) {
                uiListener2.onListeningChanged(true);
            }
            FloatBallService companion = FloatBallService.Companion.getInstance();
            if (companion != null) {
                companion.showTip("小翻正在聆听，说话吧");
            }
            this.micThread = new Thread(new Runnable() { // from class: com.xiaofan.bangfan.VoiceControlService$$ExternalSyntheticLambda8
                @Override // java.lang.Runnable
                public final void run() {
                    VoiceControlService.beginLive$lambda$2(VoiceControlService.this);
                }
            }, "xf-live-mic");
            Thread thread = this.micThread;
            if (thread != null) {
                thread.start();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void beginLive$lambda$2(final VoiceControlService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Process.setThreadPriority(-16);
        this$0.ensureHandleLoadedBlocking();
        Log.i("VoiceCtrl", "asr handle=" + this$0.asrHandle);
        if (this$0.asrHandle == 0) {
            this$0.main.post(new Runnable() { // from class: com.xiaofan.bangfan.VoiceControlService$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    VoiceControlService.beginLive$lambda$2$lambda$1(VoiceControlService.this);
                }
            });
            return;
        }
        try {
            long t0 = SystemClock.uptimeMillis();
            NativeAsr.INSTANCE.nativeTranscribe(this$0.asrHandle, new short[4000], "zh");
            Log.i("VoiceCtrl", "asr warmup cost=" + (SystemClock.uptimeMillis() - t0) + "ms");
        } catch (Throwable th) {
            Log.w("VoiceCtrl", "warmup failed", th);
        }
        this$0.runContinuous();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void beginLive$lambda$2$lambda$1(VoiceControlService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        XiaoFanVoice xiaoFanVoice = XiaoFanVoice.INSTANCE;
        Context applicationContext = this$0.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        xiaoFanVoice.tip(applicationContext, "识别模型没准备好，稍后再试");
        this$0.stopEverything();
    }

    /* JADX WARN: Type inference failed for: r1v18, types: [T, java.util.ArrayList] */
    private final void runContinuous() {
        AcousticEchoCanceler aec;
        AutomaticGainControl agc;
        AutomaticGainControl agc2;
        Ref.LongRef lastMissTipAt;
        int n;
        double rms;
        AudioRecord recorder;
        short[] frame;
        Ref.IntRef speechFrames;
        Ref.BooleanRef speaking;
        Ref.ObjectRef utter;
        Ref.IntRef loudFrames;
        ArrayDeque preRoll;
        Ref.DoubleRef noiseFloor;
        AutomaticGainControl agc3;
        boolean z;
        int i;
        AcousticEchoCanceler aec2;
        Ref.IntRef intRef;
        AudioRecord recorder2 = null;
        AcousticEchoCanceler aec3 = null;
        AutomaticGainControl agc4 = null;
        try {
            int minBuf = AudioRecord.getMinBufferSize(SAMPLE_RATE, 16, 2);
            int bufBytes = Math.max(minBuf, 64000);
            AudioRecord recorder3 = new AudioRecord(6, SAMPLE_RATE, 16, 2, bufBytes);
            try {
                Log.i("VoiceCtrl", "AudioRecord state=" + recorder3.getState() + " minBuf=" + minBuf + " bufBytes=" + bufBytes);
                if (recorder3.getState() != 1) {
                    try {
                        this.main.post(new Runnable() { // from class: com.xiaofan.bangfan.VoiceControlService$$ExternalSyntheticLambda5
                            @Override // java.lang.Runnable
                            public final void run() {
                                VoiceControlService.runContinuous$lambda$3(VoiceControlService.this);
                            }
                        });
                        try {
                            recorder3.stop();
                        } catch (Throwable th) {
                        }
                        try {
                            recorder3.release();
                        } catch (Throwable th2) {
                        }
                        return;
                    } catch (Throwable th3) {
                        th = th3;
                        recorder2 = recorder3;
                    }
                } else {
                    int sid = recorder3.getAudioSessionId();
                    if (AcousticEchoCanceler.isAvailable()) {
                        AcousticEchoCanceler it = AcousticEchoCanceler.create(sid);
                        if (it != null) {
                            it.setEnabled(true);
                        } else {
                            it = null;
                        }
                        aec3 = it;
                        Log.i("VoiceCtrl", "AEC enabled=" + (aec3 != null ? Boolean.valueOf(aec3.getEnabled()) : null));
                        aec = aec3;
                    } else {
                        aec = null;
                    }
                    try {
                        if (AutomaticGainControl.isAvailable()) {
                            AutomaticGainControl it2 = AutomaticGainControl.create(sid);
                            if (it2 != null) {
                                it2.setEnabled(true);
                            } else {
                                it2 = null;
                            }
                            agc4 = it2;
                            Log.i("VoiceCtrl", "AGC enabled=" + (agc4 != null ? Boolean.valueOf(agc4.getEnabled()) : null));
                            agc = agc4;
                        } else {
                            agc = null;
                        }
                    } catch (Throwable th4) {
                        try {
                            Log.w("VoiceCtrl", "AGC unavailable", th4);
                            agc = agc4;
                        } catch (Throwable th5) {
                            th = th5;
                            aec3 = aec;
                            recorder2 = recorder3;
                        }
                    }
                    try {
                        recorder3.startRecording();
                        short[] frame2 = new short[FRAME];
                        ArrayDeque preRoll2 = new ArrayDeque();
                        Ref.ObjectRef utter2 = new Ref.ObjectRef();
                        utter2.element = new ArrayList(96000);
                        Ref.BooleanRef speaking2 = new Ref.BooleanRef();
                        Ref.IntRef speechFrames2 = new Ref.IntRef();
                        Ref.IntRef loudFrames2 = new Ref.IntRef();
                        Ref.IntRef silentFrames = new Ref.IntRef();
                        long ttsGuardUntil = 0;
                        AudioRecord recorder4 = recorder3;
                        Ref.DoubleRef noiseFloor2 = new Ref.DoubleRef();
                        try {
                            noiseFloor2.element = 55.0d;
                            int frameCount = 0;
                            int nearZeroFrames = 0;
                            boolean noAudioTipped = false;
                            Ref.IntRef silentFrames2 = silentFrames;
                            Ref.LongRef lastMissTipAt2 = new Ref.LongRef();
                            while (true) {
                                agc2 = agc;
                                try {
                                    if (!this.live) {
                                        break;
                                    }
                                    int bufBytes2 = bufBytes;
                                    AudioRecord recorder5 = recorder4;
                                    AcousticEchoCanceler aec4 = aec;
                                    try {
                                        int n2 = recorder5.read(frame2, 0, frame2.length);
                                        if (n2 > 0) {
                                            int frameCount2 = frameCount + 1;
                                            double sum = 0.0d;
                                            int i2 = 0;
                                            while (i2 < n2) {
                                                try {
                                                    double v = frame2[i2];
                                                    sum += v * v;
                                                    i2++;
                                                    nearZeroFrames = nearZeroFrames;
                                                } catch (Throwable th6) {
                                                    th = th6;
                                                    recorder2 = recorder5;
                                                    aec3 = aec4;
                                                    agc4 = agc2;
                                                }
                                            }
                                            int nearZeroFrames2 = nearZeroFrames;
                                            double rms2 = Math.sqrt(sum / n2);
                                            if (frameCount2 % 25 == 0) {
                                                n = n2;
                                                rms = rms2;
                                                try {
                                                    recorder = recorder5;
                                                    try {
                                                        lastMissTipAt = lastMissTipAt2;
                                                        Log.i("VoiceCtrl", "rms=" + ((int) rms2) + " floor=" + ((int) noiseFloor2.element) + " on=" + ((int) runContinuous$dynOn(noiseFloor2)) + " speaking=" + speaking2.element);
                                                    } catch (Throwable th7) {
                                                        th = th7;
                                                        aec3 = aec4;
                                                        agc4 = agc2;
                                                        recorder2 = recorder;
                                                    }
                                                } catch (Throwable th8) {
                                                    th = th8;
                                                    aec3 = aec4;
                                                    agc4 = agc2;
                                                    recorder2 = recorder5;
                                                }
                                            } else {
                                                lastMissTipAt = lastMissTipAt2;
                                                n = n2;
                                                rms = rms2;
                                                recorder = recorder5;
                                            }
                                            try {
                                                long nowUp = SystemClock.uptimeMillis();
                                                if (XiaoFanVoice.INSTANCE.isSpeakingNow()) {
                                                    ttsGuardUntil = nowUp + TTS_TAIL_GUARD_MS;
                                                }
                                                if (nowUp < ttsGuardUntil) {
                                                    if (speaking2.element) {
                                                        runContinuous$resetUtter(utter2, speaking2, speechFrames2, loudFrames2, silentFrames2);
                                                    }
                                                    preRoll2.clear();
                                                    aec = aec4;
                                                    agc = agc2;
                                                    bufBytes = bufBytes2;
                                                    frameCount = frameCount2;
                                                    nearZeroFrames = nearZeroFrames2;
                                                    recorder4 = recorder;
                                                    lastMissTipAt2 = lastMissTipAt;
                                                } else {
                                                    int nearZeroFrames3 = rms < 6.0d ? nearZeroFrames2 + 1 : 0;
                                                    if (!noAudioTipped && nearZeroFrames3 > 150) {
                                                        this.main.post(new Runnable() { // from class: com.xiaofan.bangfan.VoiceControlService$$ExternalSyntheticLambda6
                                                            @Override // java.lang.Runnable
                                                            public final void run() {
                                                                VoiceControlService.runContinuous$lambda$6(VoiceControlService.this);
                                                            }
                                                        });
                                                        noAudioTipped = true;
                                                    }
                                                    short[] copy = Arrays.copyOf(frame2, n);
                                                    Intrinsics.checkNotNullExpressionValue(copy, "copyOf(...)");
                                                    if (speaking2.element) {
                                                        frame = frame2;
                                                        Ref.IntRef loudFrames3 = loudFrames2;
                                                        ((ArrayList) utter2.element).addAll(ArraysKt.toList(copy));
                                                        speechFrames2.element++;
                                                        if (rms < runContinuous$dynOff(noiseFloor2)) {
                                                            try {
                                                                silentFrames2.element++;
                                                                if (silentFrames2.element >= 25) {
                                                                    loudFrames = loudFrames3;
                                                                    speechFrames = speechFrames2;
                                                                    speaking = speaking2;
                                                                    utter = utter2;
                                                                    preRoll = preRoll2;
                                                                    noiseFloor = noiseFloor2;
                                                                    agc3 = agc2;
                                                                    lastMissTipAt2 = lastMissTipAt;
                                                                    try {
                                                                        runContinuous$endUtterance(speechFrames2, loudFrames, utter2, this, lastMissTipAt2, speaking, silentFrames2);
                                                                    } catch (Throwable th9) {
                                                                        th = th9;
                                                                        agc4 = agc3;
                                                                        aec3 = aec4;
                                                                        recorder2 = recorder;
                                                                    }
                                                                } else {
                                                                    speechFrames = speechFrames2;
                                                                    speaking = speaking2;
                                                                    utter = utter2;
                                                                    loudFrames = loudFrames3;
                                                                    preRoll = preRoll2;
                                                                    lastMissTipAt2 = lastMissTipAt;
                                                                    noiseFloor = noiseFloor2;
                                                                    agc3 = agc2;
                                                                }
                                                                z = true;
                                                            } catch (Throwable th10) {
                                                                th = th10;
                                                                agc4 = agc2;
                                                                aec3 = aec4;
                                                                recorder2 = recorder;
                                                            }
                                                        } else {
                                                            speechFrames = speechFrames2;
                                                            speaking = speaking2;
                                                            utter = utter2;
                                                            loudFrames = loudFrames3;
                                                            preRoll = preRoll2;
                                                            lastMissTipAt2 = lastMissTipAt;
                                                            noiseFloor = noiseFloor2;
                                                            agc3 = agc2;
                                                            try {
                                                                silentFrames2.element = 0;
                                                                z = true;
                                                                loudFrames.element++;
                                                            } catch (Throwable th11) {
                                                                th = th11;
                                                                aec3 = aec4;
                                                                agc4 = agc3;
                                                                recorder2 = recorder;
                                                            }
                                                        }
                                                        if (speechFrames.element >= MAX_UTT_FRAMES) {
                                                            aec2 = aec4;
                                                            speechFrames2 = speechFrames;
                                                            recorder4 = recorder;
                                                            i = nearZeroFrames3;
                                                            intRef = silentFrames2;
                                                            try {
                                                                runContinuous$endUtterance(speechFrames, loudFrames, utter, this, lastMissTipAt2, speaking, silentFrames2);
                                                            } catch (Throwable th12) {
                                                                th = th12;
                                                                aec3 = aec2;
                                                                agc4 = agc3;
                                                                recorder2 = recorder4;
                                                            }
                                                        } else {
                                                            speechFrames2 = speechFrames;
                                                            i = nearZeroFrames3;
                                                            aec2 = aec4;
                                                            recorder4 = recorder;
                                                            intRef = silentFrames2;
                                                        }
                                                    } else if (rms >= runContinuous$dynOn(noiseFloor2)) {
                                                        speaking2.element = true;
                                                        speechFrames2.element = 1;
                                                        loudFrames2.element = 1;
                                                        silentFrames2.element = 0;
                                                        Iterator it3 = preRoll2.iterator();
                                                        while (it3.hasNext()) {
                                                            short[] pr = (short[]) it3.next();
                                                            ((ArrayList) utter2.element).addAll(ArraysKt.toList(pr));
                                                            frame2 = frame2;
                                                        }
                                                        frame = frame2;
                                                        ((ArrayList) utter2.element).addAll(ArraysKt.toList(copy));
                                                        loudFrames = loudFrames2;
                                                        speaking = speaking2;
                                                        utter = utter2;
                                                        preRoll = preRoll2;
                                                        i = nearZeroFrames3;
                                                        aec2 = aec4;
                                                        recorder4 = recorder;
                                                        lastMissTipAt2 = lastMissTipAt;
                                                        intRef = silentFrames2;
                                                        noiseFloor = noiseFloor2;
                                                        agc3 = agc2;
                                                    } else {
                                                        frame = frame2;
                                                        Ref.IntRef loudFrames4 = loudFrames2;
                                                        noiseFloor2.element = RangesKt.coerceIn((noiseFloor2.element * 0.92d) + (0.08d * rms), 12.0d, (double) NOISE_FLOOR_MAX);
                                                        preRoll2.addLast(copy);
                                                        while (preRoll2.size() > 15) {
                                                            preRoll2.removeFirst();
                                                        }
                                                        speaking = speaking2;
                                                        utter = utter2;
                                                        loudFrames = loudFrames4;
                                                        preRoll = preRoll2;
                                                        i = nearZeroFrames3;
                                                        aec2 = aec4;
                                                        recorder4 = recorder;
                                                        lastMissTipAt2 = lastMissTipAt;
                                                        intRef = silentFrames2;
                                                        noiseFloor = noiseFloor2;
                                                        agc3 = agc2;
                                                    }
                                                    aec = aec2;
                                                    agc = agc3;
                                                    noiseFloor2 = noiseFloor;
                                                    bufBytes = bufBytes2;
                                                    frameCount = frameCount2;
                                                    nearZeroFrames = i;
                                                    silentFrames2 = intRef;
                                                    speaking2 = speaking;
                                                    utter2 = utter;
                                                    preRoll2 = preRoll;
                                                    loudFrames2 = loudFrames;
                                                    frame2 = frame;
                                                }
                                            } catch (Throwable th13) {
                                                th = th13;
                                                aec3 = aec4;
                                                agc4 = agc2;
                                                recorder2 = recorder;
                                            }
                                        } else {
                                            recorder4 = recorder5;
                                            aec = aec4;
                                            agc = agc2;
                                            noiseFloor2 = noiseFloor2;
                                            bufBytes = bufBytes2;
                                            speaking2 = speaking2;
                                            utter2 = utter2;
                                            loudFrames2 = loudFrames2;
                                            frame2 = frame2;
                                        }
                                    } catch (Throwable th14) {
                                        th = th14;
                                        aec3 = aec4;
                                        agc4 = agc2;
                                        recorder2 = recorder5;
                                    }
                                } catch (Throwable th15) {
                                    th = th15;
                                    aec3 = aec;
                                    agc4 = agc2;
                                    recorder2 = recorder4;
                                }
                            }
                            AcousticEchoCanceler aec5 = aec;
                            if (aec5 != null) {
                                try {
                                    aec5.setEnabled(false);
                                } catch (Throwable th16) {
                                }
                            }
                            if (aec5 != null) {
                                try {
                                    aec5.release();
                                } catch (Throwable th17) {
                                }
                            }
                            if (agc2 != null) {
                                try {
                                    agc2.setEnabled(false);
                                } catch (Throwable th18) {
                                }
                            }
                            if (agc2 != null) {
                                try {
                                    agc2.release();
                                } catch (Throwable th19) {
                                }
                            }
                            try {
                                recorder4.stop();
                            } catch (Throwable th20) {
                            }
                            try {
                                recorder4.release();
                            } catch (Throwable th21) {
                            }
                            return;
                        } catch (Throwable th22) {
                            th = th22;
                            aec3 = aec;
                            agc4 = agc;
                            recorder2 = recorder4;
                        }
                    } catch (Throwable th23) {
                        th = th23;
                        aec3 = aec;
                        agc4 = agc;
                        recorder2 = recorder3;
                    }
                }
            } catch (Throwable th24) {
                th = th24;
                recorder2 = recorder3;
            }
        } catch (Throwable th25) {
            th = th25;
        }
        try {
            Log.e("VoiceCtrl", "continuous loop error", th);
            this.main.post(new Runnable() { // from class: com.xiaofan.bangfan.VoiceControlService$$ExternalSyntheticLambda7
                @Override // java.lang.Runnable
                public final void run() {
                    VoiceControlService.runContinuous$lambda$7(VoiceControlService.this);
                }
            });
            if (aec3 != null) {
                try {
                    aec3.setEnabled(false);
                } catch (Throwable th26) {
                }
            }
            if (aec3 != null) {
                try {
                    aec3.release();
                } catch (Throwable th27) {
                }
            }
            if (agc4 != null) {
                try {
                    agc4.setEnabled(false);
                } catch (Throwable th28) {
                }
            }
            if (agc4 != null) {
                try {
                    agc4.release();
                } catch (Throwable th29) {
                }
            }
            if (recorder2 != null) {
                try {
                    recorder2.stop();
                } catch (Throwable th30) {
                }
            }
            if (recorder2 != null) {
                try {
                    recorder2.release();
                } catch (Throwable th31) {
                }
            }
        } catch (Throwable th32) {
            if (aec3 != null) {
                try {
                    aec3.setEnabled(false);
                } catch (Throwable th33) {
                }
            }
            if (aec3 != null) {
                try {
                    aec3.release();
                } catch (Throwable th34) {
                }
            }
            if (agc4 != null) {
                try {
                    agc4.setEnabled(false);
                } catch (Throwable th35) {
                }
            }
            if (agc4 != null) {
                try {
                    agc4.release();
                } catch (Throwable th36) {
                }
            }
            if (recorder2 != null) {
                try {
                    recorder2.stop();
                } catch (Throwable th37) {
                }
            }
            if (recorder2 != null) {
                try {
                    recorder2.release();
                } catch (Throwable th38) {
                }
            }
            throw th32;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void runContinuous$lambda$3(VoiceControlService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        XiaoFanVoice xiaoFanVoice = XiaoFanVoice.INSTANCE;
        Context applicationContext = this$0.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        xiaoFanVoice.tip(applicationContext, "麦克风启动失败，检查一下权限");
        this$0.stopEverything();
    }

    private static final double runContinuous$dynOn(Ref.DoubleRef noiseFloor) {
        return RangesKt.coerceIn((noiseFloor.element * 1.9d) + 45.0d, 75.0d, 750.0d);
    }

    private static final double runContinuous$dynOff(Ref.DoubleRef noiseFloor) {
        return RangesKt.coerceIn((noiseFloor.element * 1.3d) + 28.0d, 55.0d, 620.0d);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [T, java.util.ArrayList] */
    private static final void runContinuous$resetUtter(Ref.ObjectRef<ArrayList<Short>> objectRef, Ref.BooleanRef speaking, Ref.IntRef speechFrames, Ref.IntRef loudFrames, Ref.IntRef silentFrames) {
        objectRef.element = new ArrayList(96000);
        speaking.element = false;
        speechFrames.element = 0;
        loudFrames.element = 0;
        silentFrames.element = 0;
    }

    private static final void runContinuous$endUtterance(Ref.IntRef speechFrames, Ref.IntRef loudFrames, Ref.ObjectRef<ArrayList<Short>> objectRef, VoiceControlService this$0, final Ref.LongRef lastMissTipAt, Ref.BooleanRef speaking, Ref.IntRef silentFrames) {
        if (speechFrames.element >= 6 && loudFrames.element >= 3 && objectRef.element.size() >= 1280) {
            short[] pcm = new short[objectRef.element.size()];
            int size = objectRef.element.size();
            for (int i = 0; i < size; i++) {
                Short sh = objectRef.element.get(i);
                Intrinsics.checkNotNullExpressionValue(sh, "get(...)");
                pcm[i] = sh.shortValue();
            }
            FloatBallService companion = FloatBallService.Companion.getInstance();
            if (companion != null) {
                companion.showTip("正在识别…");
            }
            this$0.submitTranscription(pcm, new Function1<Boolean, Unit>() { // from class: com.xiaofan.bangfan.VoiceControlService$runContinuous$endUtterance$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean recognized) {
                    long now = SystemClock.uptimeMillis();
                    if (!recognized && now - Ref.LongRef.this.element > 4000) {
                        Ref.LongRef.this.element = now;
                        FloatBallService companion2 = FloatBallService.Companion.getInstance();
                        if (companion2 != null) {
                            companion2.showTip("没太听清，靠近一点再说一次");
                        }
                    }
                }
            });
        }
        runContinuous$resetUtter(objectRef, speaking, speechFrames, loudFrames, silentFrames);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void runContinuous$lambda$6(VoiceControlService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        XiaoFanVoice xiaoFanVoice = XiaoFanVoice.INSTANCE;
        Context applicationContext = this$0.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        xiaoFanVoice.tip(applicationContext, "没收到声音，检查麦克风有没有被占用");
        FloatBallService companion = FloatBallService.Companion.getInstance();
        if (companion != null) {
            companion.showTip("没收到麦克风声音");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void runContinuous$lambda$7(VoiceControlService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        XiaoFanVoice xiaoFanVoice = XiaoFanVoice.INSTANCE;
        Context applicationContext = this$0.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        xiaoFanVoice.tip(applicationContext, "聆听出错了，再长按悬浮球重开一次");
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void submitTranscription$default(VoiceControlService voiceControlService, short[] sArr, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = null;
        }
        voiceControlService.submitTranscription(sArr, function1);
    }

    private final void submitTranscription(final short[] pcm, final Function1<? super Boolean, Unit> function1) {
        final long h = this.asrHandle;
        if (h != 0) {
            this.io.execute(new Runnable() { // from class: com.xiaofan.bangfan.VoiceControlService$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    VoiceControlService.submitTranscription$lambda$9(h, pcm, this, function1);
                }
            });
        } else if (function1 != null) {
            function1.invoke(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void submitTranscription$lambda$9(long $h, short[] pcm, final VoiceControlService this$0, final Function1 $onResult) {
        final String text;
        Intrinsics.checkNotNullParameter(pcm, "$pcm");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        long t0 = SystemClock.uptimeMillis();
        try {
            text = NativeAsr.INSTANCE.nativeTranscribe($h, pcm, "zh");
        } catch (Throwable th) {
            text = null;
        }
        Log.i("VoiceCtrl", "decode cost=" + (SystemClock.uptimeMillis() - t0) + "ms samples=" + pcm.length);
        this$0.main.post(new Runnable() { // from class: com.xiaofan.bangfan.VoiceControlService$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                VoiceControlService.submitTranscription$lambda$9$lambda$8(text, $onResult, this$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void submitTranscription$lambda$9$lambda$8(String $text, Function1 $onResult, VoiceControlService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String t = $text != null ? StringsKt.trim((CharSequence) $text).toString() : null;
        Log.i("VoiceCtrl", "asr text='" + t + "'");
        String str = t;
        if (str == null || StringsKt.isBlank(str)) {
            if ($onResult != null) {
                $onResult.invoke(false);
            }
        } else if (XiaoFanVoice.INSTANCE.isEchoOfSpeech(t)) {
            Log.i("VoiceCtrl", "drop self-echo: " + t);
            if ($onResult != null) {
                $onResult.invoke(false);
            }
        } else {
            VoiceCommandRouter.Command cmd = VoiceCommandRouter.INSTANCE.route(t);
            Companion.UiListener uiListener2 = uiListener;
            if (uiListener2 != null) {
                uiListener2.onHeard(t, cmd);
            }
            if (cmd != VoiceCommandRouter.Command.NONE) {
                this$0.dispatch(cmd);
                if ($onResult != null) {
                    $onResult.invoke(true);
                    return;
                }
                return;
            }
            FloatBallService companion = FloatBallService.Companion.getInstance();
            if (companion != null) {
                companion.showTip("听到“" + t + "”，还不会这个口令");
            }
            if ($onResult != null) {
                $onResult.invoke(true);
            }
        }
    }

    private final void ensureHandleLoadedBlocking() {
        if (this.asrHandle != 0) {
            return;
        }
        try {
            NativeAsr.INSTANCE.ensureLoaded();
            int threads = RangesKt.coerceIn(Runtime.getRuntime().availableProcessors(), 2, 8);
            String mp = OfflineModels.INSTANCE.file(this, OfflineModels.Kind.ASR).getAbsolutePath();
            NativeAsr nativeAsr = NativeAsr.INSTANCE;
            Intrinsics.checkNotNull(mp);
            this.asrHandle = nativeAsr.nativeLoad(mp, threads);
            Log.i("VoiceCtrl", "nativeLoad path=" + mp + " threads=" + threads + " -> " + this.asrHandle);
        } catch (Throwable th) {
            Log.e("VoiceCtrl", "load asr failed", th);
        }
    }

    private final void startAsForeground(boolean listening) {
        Object systemService = getSystemService("notification");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        NotificationManager nm = (NotificationManager) systemService;
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel ch = new NotificationChannel(CHANNEL_ID, "小翻离线声控", 2);
            ch.setDescription("本地识别翻页口令");
            nm.createNotificationChannel(ch);
        }
        Notification.Builder builder = Build.VERSION.SDK_INT >= 26 ? new Notification.Builder(this, CHANNEL_ID) : new Notification.Builder(this);
        Notification notif = builder.setContentTitle(listening ? "小翻正在持续聆听…" : "小翻离线声控就绪").setContentText(listening ? "长按悬浮球可关闭聆听；可说下一页/上一页/暂停/继续" : "长按悬浮球开始说话").setSmallIcon(17301668).setOngoing(true).build();
        Intrinsics.checkNotNullExpressionValue(notif, "build(...)");
        try {
            if (Build.VERSION.SDK_INT >= 29) {
                startForeground(NOTIF_ID, notif, 128);
            } else {
                startForeground(NOTIF_ID, notif);
            }
        } catch (Throwable th) {
            try {
                startForeground(NOTIF_ID, notif);
            } catch (Throwable th2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void feedback(String text) {
        FloatBallService companion = FloatBallService.Companion.getInstance();
        if (companion != null) {
            companion.showTip(text);
        }
    }

    private final void dispatch(VoiceCommandRouter.Command cmd) {
        Context ctx = getApplicationContext();
        switch (WhenMappings.$EnumSwitchMapping$0[cmd.ordinal()]) {
            case 1:
                TurnManager turnManager = TurnManager.INSTANCE;
                Intrinsics.checkNotNull(ctx);
                feedback(turnManager.requestTurn(ctx, "离线声控-下一页") ? "好，下一页" : "翻页没成功，检查无障碍");
                return;
            case 2:
                TurnManager turnManager2 = TurnManager.INSTANCE;
                Intrinsics.checkNotNull(ctx);
                feedback(turnManager2.requestPrev(ctx, "离线声控-上一页") ? "好，回到上一页" : "回退没成功，检查无障碍");
                return;
            case 3:
                TurnManager turnManager3 = TurnManager.INSTANCE;
                Intrinsics.checkNotNull(ctx);
                turnManager3.setAutoTurn(ctx, true);
                FloatBallService companion = FloatBallService.Companion.getInstance();
                if (companion != null) {
                    companion.syncEngines();
                }
                feedback("好的，继续自动翻页");
                return;
            case 4:
                TurnManager turnManager4 = TurnManager.INSTANCE;
                Intrinsics.checkNotNull(ctx);
                turnManager4.setAutoTurn(ctx, false);
                FloatBallService companion2 = FloatBallService.Companion.getInstance();
                if (companion2 != null) {
                    companion2.syncEngines();
                }
                feedback("已暂停自动翻页");
                return;
            case 5:
                AppPrefs appPrefs = AppPrefs.INSTANCE;
                Intrinsics.checkNotNull(ctx);
                float f = appPrefs.useCustomInterval(ctx) ? AppPrefs.INSTANCE.customTurnFactor(ctx) : AppPrefs.INSTANCE.turnFactor(ctx);
                float nf = RangesKt.coerceIn(0.85f * f, 0.5f, 3.0f);
                if (AppPrefs.INSTANCE.useCustomInterval(ctx)) {
                    AppPrefs.INSTANCE.setCustomTurnFactor(ctx, nf);
                } else {
                    AppPrefs.INSTANCE.setTurnFactor(ctx, nf);
                }
                TurnManager.INSTANCE.notifyTimedTurnChanged(AppPrefs.INSTANCE.autoTurn(ctx), AppPrefs.INSTANCE.turnIntervalMs(ctx));
                feedback("好的，翻快一点");
                return;
            case 6:
                AppPrefs appPrefs2 = AppPrefs.INSTANCE;
                Intrinsics.checkNotNull(ctx);
                float f2 = appPrefs2.useCustomInterval(ctx) ? AppPrefs.INSTANCE.customTurnFactor(ctx) : AppPrefs.INSTANCE.turnFactor(ctx);
                float nf2 = RangesKt.coerceIn(1.18f * f2, 0.5f, 3.0f);
                if (AppPrefs.INSTANCE.useCustomInterval(ctx)) {
                    AppPrefs.INSTANCE.setCustomTurnFactor(ctx, nf2);
                } else {
                    AppPrefs.INSTANCE.setTurnFactor(ctx, nf2);
                }
                TurnManager.INSTANCE.notifyTimedTurnChanged(AppPrefs.INSTANCE.autoTurn(ctx), AppPrefs.INSTANCE.turnIntervalMs(ctx));
                feedback("好的，翻慢一点");
                return;
            case 7:
                startService(new Intent(ctx, FloatBallService.class).setAction(FloatBallService.ACTION_START));
                feedback("悬浮球打开啦");
                return;
            case 8:
                startService(new Intent(ctx, FloatBallService.class).setAction(FloatBallService.ACTION_STOP));
                feedback("悬浮球收起啦");
                return;
            case 9:
                feedback(XiaoFanBrain.INSTANCE.speakTimeNow());
                return;
            case 10:
                XiaoFanBrain.Intent it = XiaoFanBrain.INSTANCE.parse("天气");
                XiaoFanBrain xiaoFanBrain = XiaoFanBrain.INSTANCE;
                Intrinsics.checkNotNull(ctx);
                xiaoFanBrain.handle(ctx, it, new XiaoFanBrain.AnswerCallback() { // from class: com.xiaofan.bangfan.VoiceControlService$dispatch$1
                    @Override // com.xiaofan.bangfan.XiaoFanBrain.AnswerCallback
                    public void onAction(String action) {
                        Intrinsics.checkNotNullParameter(action, "action");
                    }

                    @Override // com.xiaofan.bangfan.XiaoFanBrain.AnswerCallback
                    public void onSpeak(String text) {
                        Intrinsics.checkNotNullParameter(text, "text");
                        VoiceControlService.this.feedback(text);
                    }
                });
                return;
            case 11:
                feedback("好的");
                return;
            case 12:
                TurnManager turnManager5 = TurnManager.INSTANCE;
                Intrinsics.checkNotNull(ctx);
                turnManager5.setAutoTurn(ctx, false);
                FloatBallService companion3 = FloatBallService.Companion.getInstance();
                if (companion3 != null) {
                    companion3.syncEngines();
                }
                feedback("好的，已取消");
                return;
            default:
                return;
        }
    }

    private final void stopEverything() {
        this.live = false;
        liveNow = false;
        alive = false;
        Companion.UiListener uiListener2 = uiListener;
        if (uiListener2 != null) {
            uiListener2.onListeningChanged(false);
        }
        FloatBallService companion = FloatBallService.Companion.getInstance();
        if (companion != null) {
            companion.setListeningGlow(false);
        }
        try {
            Thread thread = this.micThread;
            if (thread != null) {
                thread.interrupt();
            }
        } catch (Throwable th) {
        }
        final long h = this.asrHandle;
        this.asrHandle = 0L;
        if (h != 0) {
            this.io.execute(new Runnable() { // from class: com.xiaofan.bangfan.VoiceControlService$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    VoiceControlService.stopEverything$lambda$10(h);
                }
            });
        }
        try {
            stopForeground(1);
        } catch (Throwable th2) {
        }
        stopSelf();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void stopEverything$lambda$10(long $h) {
        try {
            NativeAsr.INSTANCE.nativeUnload($h);
        } catch (Throwable th) {
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        this.live = false;
        liveNow = false;
        alive = false;
        try {
            Thread thread = this.micThread;
            if (thread != null) {
                thread.interrupt();
            }
        } catch (Throwable th) {
        }
        super.onDestroy();
    }
}
