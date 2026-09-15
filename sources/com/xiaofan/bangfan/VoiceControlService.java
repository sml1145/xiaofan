package com.xiaofan.bangfan;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioRecord;
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
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 )2\u00020\u0001:\u0001)B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u000eH\u0002J\b\u0010\u0013\u001a\u00020\bH\u0002J\u0014\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u000eH\u0016J\"\u0010\u0019\u001a\u00020\u001a2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001aH\u0016J\b\u0010\u001d\u001a\u00020\u000eH\u0002J\u0010\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\bH\u0002J\b\u0010 \u001a\u00020\u000eH\u0002J7\u0010!\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020#2%\b\u0002\u0010$\u001a\u001f\u0012\u0013\u0012\u00110\b¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b((\u0012\u0004\u0012\u00020\u000e\u0018\u00010%H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/xiaofan/bangfan/VoiceControlService;", "Landroid/app/Service;", "()V", "asrHandle", "", "io", "Ljava/util/concurrent/ExecutorService;", "live", "", "main", "Landroid/os/Handler;", "micThread", "Ljava/lang/Thread;", "beginLive", "", "dispatch", "cmd", "Lcom/xiaofan/bangfan/ai/VoiceCommandRouter$Command;", "ensureHandleLoadedBlocking", "hasMic", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onDestroy", "onStartCommand", "", "flags", "startId", "runContinuous", "startAsForeground", "listening", "stopEverything", "submitTranscription", "pcm", "", "onResult", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "recognized", "Companion", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
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
    private static final int MIN_SPEECH_FRAMES = 10;
    private static final int NOTIF_ID = 2006;
    private static final int PREROLL_FRAMES = 10;
    private static final int SAMPLE_RATE = 16000;
    private static final int SIL_TAIL_FRAMES = 25;
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
                iArr[VoiceCommandRouter.Command.VIDEO_START.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr[VoiceCommandRouter.Command.VIDEO_PAUSE.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                iArr[VoiceCommandRouter.Command.TELL_TIME.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                iArr[VoiceCommandRouter.Command.WEATHER.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                iArr[VoiceCommandRouter.Command.CONFIRM.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                iArr[VoiceCommandRouter.Command.CANCEL.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                iArr[VoiceCommandRouter.Command.NONE.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
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
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001$B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u001c\u001a\u00020\u0014J\u0006\u0010\u001d\u001a\u00020\u0014J\u000e\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!J\u000e\u0010\"\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!J\u000e\u0010#\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006%"}, d2 = {"Lcom/xiaofan/bangfan/VoiceControlService$Companion;", "", "()V", "ACTION_LIVE_START", "", "ACTION_LIVE_STOP", "ACTION_STOP", "CHANNEL_ID", "ENERGY_OFF", "", "ENERGY_ON", "FRAME", "", "MAX_UTT_FRAMES", "MIN_SPEECH_FRAMES", "NOTIF_ID", "PREROLL_FRAMES", "SAMPLE_RATE", "SIL_TAIL_FRAMES", "alive", "", "liveNow", "uiListener", "Lcom/xiaofan/bangfan/VoiceControlService$Companion$UiListener;", "getUiListener", "()Lcom/xiaofan/bangfan/VoiceControlService$Companion$UiListener;", "setUiListener", "(Lcom/xiaofan/bangfan/VoiceControlService$Companion$UiListener;)V", "isLiveListening", "isRunning", "startLive", "", "ctx", "Landroid/content/Context;", "stop", "stopLive", "UiListener", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
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

    /* JADX WARN: Type inference failed for: r1v10, types: [T, java.util.ArrayList] */
    private final void runContinuous() {
        Ref.LongRef lastMissTipAt;
        Ref.IntRef silentFrames;
        Ref.ObjectRef utter;
        ArrayDeque preRoll;
        AudioRecord recorder;
        short[] frame;
        ArrayDeque preRoll2;
        Ref.DoubleRef noiseFloor;
        Ref.IntRef silentFrames2;
        Ref.IntRef speechFrames;
        boolean z;
        Ref.BooleanRef speaking;
        Ref.ObjectRef objectRef;
        ArrayDeque arrayDeque;
        boolean z2;
        Ref.IntRef intRef;
        final VoiceControlService voiceControlService = this;
        AudioRecord recorder2 = null;
        try {
            int minBuf = AudioRecord.getMinBufferSize(SAMPLE_RATE, 16, 2);
            int bufBytes = Math.max(minBuf, 64000);
            AudioRecord recorder3 = new AudioRecord(6, SAMPLE_RATE, 16, 2, bufBytes);
            try {
                Log.i("VoiceCtrl", "AudioRecord state=" + recorder3.getState() + " minBuf=" + minBuf + " bufBytes=" + bufBytes);
                try {
                    if (recorder3.getState() != 1) {
                        voiceControlService.main.post(new Runnable() { // from class: com.xiaofan.bangfan.VoiceControlService$$ExternalSyntheticLambda5
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
                    }
                    recorder3.startRecording();
                    short[] frame2 = new short[FRAME];
                    ArrayDeque preRoll3 = new ArrayDeque();
                    Ref.ObjectRef utter2 = new Ref.ObjectRef();
                    utter2.element = new ArrayList(96000);
                    Ref.BooleanRef speaking2 = new Ref.BooleanRef();
                    Ref.IntRef speechFrames2 = new Ref.IntRef();
                    Ref.IntRef silentFrames3 = new Ref.IntRef();
                    Ref.DoubleRef noiseFloor2 = new Ref.DoubleRef();
                    noiseFloor2.element = 55.0d;
                    int frameCount = 0;
                    int nearZeroFrames = 0;
                    boolean noAudioTipped = false;
                    Ref.LongRef lastMissTipAt2 = new Ref.LongRef();
                    while (voiceControlService.live) {
                        int minBuf2 = minBuf;
                        int n = recorder3.read(frame2, 0, frame2.length);
                        if (n > 0) {
                            int frameCount2 = frameCount + 1;
                            double sum = 0.0d;
                            int i = 0;
                            while (i < n) {
                                double v = frame2[i];
                                sum += v * v;
                                i++;
                                bufBytes = bufBytes;
                            }
                            int bufBytes2 = bufBytes;
                            double rms = Math.sqrt(sum / n);
                            if (frameCount2 % 25 == 0) {
                                utter = utter2;
                                preRoll = preRoll3;
                                lastMissTipAt = lastMissTipAt2;
                                recorder = recorder3;
                                try {
                                    silentFrames = silentFrames3;
                                    Log.i("VoiceCtrl", "rms=" + ((int) rms) + " floor=" + ((int) noiseFloor2.element) + " on=" + ((int) runContinuous$dynOn(noiseFloor2)) + " speaking=" + speaking2.element);
                                } catch (Throwable th3) {
                                    th = th3;
                                    recorder2 = recorder;
                                    try {
                                        Log.e("VoiceCtrl", "continuous loop error", th);
                                        try {
                                            this.main.post(new Runnable() { // from class: com.xiaofan.bangfan.VoiceControlService$$ExternalSyntheticLambda7
                                                @Override // java.lang.Runnable
                                                public final void run() {
                                                    VoiceControlService.runContinuous$lambda$5(VoiceControlService.this);
                                                }
                                            });
                                            if (recorder2 != null) {
                                                try {
                                                    recorder2.stop();
                                                } catch (Throwable th4) {
                                                }
                                            }
                                            if (recorder2 != null) {
                                                try {
                                                    recorder2.release();
                                                } catch (Throwable th5) {
                                                }
                                            }
                                            return;
                                        } catch (Throwable th6) {
                                            th = th6;
                                            Throwable th7 = th;
                                            if (recorder2 != null) {
                                                try {
                                                    recorder2.stop();
                                                } catch (Throwable th8) {
                                                }
                                            }
                                            if (recorder2 != null) {
                                                try {
                                                    recorder2.release();
                                                } catch (Throwable th9) {
                                                }
                                            }
                                            throw th7;
                                        }
                                    } catch (Throwable th10) {
                                        th = th10;
                                    }
                                }
                            } else {
                                lastMissTipAt = lastMissTipAt2;
                                silentFrames = silentFrames3;
                                utter = utter2;
                                preRoll = preRoll3;
                                recorder = recorder3;
                            }
                            int nearZeroFrames2 = rms < 6.0d ? nearZeroFrames + 1 : 0;
                            if (!noAudioTipped && nearZeroFrames2 > 150) {
                                voiceControlService.main.post(new Runnable() { // from class: com.xiaofan.bangfan.VoiceControlService$$ExternalSyntheticLambda6
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        VoiceControlService.runContinuous$lambda$4(VoiceControlService.this);
                                    }
                                });
                                noAudioTipped = true;
                            }
                            short[] copy = Arrays.copyOf(frame2, n);
                            Intrinsics.checkNotNullExpressionValue(copy, "copyOf(...)");
                            if (speaking2.element) {
                                Ref.ObjectRef utter3 = utter;
                                Ref.IntRef silentFrames4 = silentFrames;
                                frame = frame2;
                                ArrayDeque preRoll4 = preRoll;
                                ((ArrayList) utter3.element).addAll(ArraysKt.toList(copy));
                                if (rms < runContinuous$dynOff(noiseFloor2)) {
                                    silentFrames4.element++;
                                    if (silentFrames4.element >= 25) {
                                        noiseFloor = noiseFloor2;
                                        silentFrames2 = silentFrames4;
                                        preRoll2 = preRoll4;
                                        speechFrames = speechFrames2;
                                        lastMissTipAt2 = lastMissTipAt;
                                        runContinuous$endUtterance(speechFrames2, utter3, this, lastMissTipAt2, speaking2, silentFrames2);
                                    } else {
                                        preRoll2 = preRoll4;
                                        noiseFloor = noiseFloor2;
                                        silentFrames2 = silentFrames4;
                                        speechFrames = speechFrames2;
                                        lastMissTipAt2 = lastMissTipAt;
                                    }
                                    z = true;
                                } else {
                                    preRoll2 = preRoll4;
                                    noiseFloor = noiseFloor2;
                                    silentFrames2 = silentFrames4;
                                    speechFrames = speechFrames2;
                                    lastMissTipAt2 = lastMissTipAt;
                                    silentFrames2.element = 0;
                                    z = true;
                                    speechFrames.element++;
                                }
                                if (speechFrames.element + silentFrames2.element >= MAX_UTT_FRAMES) {
                                    speaking = speaking2;
                                    objectRef = utter3;
                                    arrayDeque = preRoll2;
                                    intRef = silentFrames2;
                                    z2 = z;
                                    runContinuous$endUtterance(speechFrames, utter3, this, lastMissTipAt2, speaking, silentFrames2);
                                } else {
                                    speaking = speaking2;
                                    objectRef = utter3;
                                    arrayDeque = preRoll2;
                                    z2 = z;
                                    intRef = silentFrames2;
                                }
                            } else if (rms >= runContinuous$dynOn(noiseFloor2)) {
                                speaking2.element = true;
                                speechFrames2.element = 1;
                                Ref.IntRef silentFrames5 = silentFrames;
                                silentFrames5.element = 0;
                                Iterator it = preRoll.iterator();
                                while (it.hasNext()) {
                                    short[] pr = (short[]) it.next();
                                    Ref.ObjectRef utter4 = utter;
                                    ((ArrayList) utter4.element).addAll(ArraysKt.toList(pr));
                                    frame2 = frame2;
                                    utter = utter4;
                                }
                                Ref.ObjectRef utter5 = utter;
                                frame = frame2;
                                ((ArrayList) utter5.element).addAll(ArraysKt.toList(copy));
                                noiseFloor = noiseFloor2;
                                speechFrames = speechFrames2;
                                speaking = speaking2;
                                objectRef = utter5;
                                arrayDeque = preRoll;
                                z2 = true;
                                intRef = silentFrames5;
                                lastMissTipAt2 = lastMissTipAt;
                            } else {
                                Ref.ObjectRef utter6 = utter;
                                Ref.IntRef silentFrames6 = silentFrames;
                                frame = frame2;
                                noiseFloor2.element = (noiseFloor2.element * 0.92d) + (0.08d * rms);
                                if (noiseFloor2.element < 12.0d) {
                                    noiseFloor2.element = 12.0d;
                                }
                                ArrayDeque preRoll5 = preRoll;
                                preRoll5.addLast(copy);
                                while (preRoll5.size() > 10) {
                                    preRoll5.removeFirst();
                                }
                                arrayDeque = preRoll5;
                                noiseFloor = noiseFloor2;
                                intRef = silentFrames6;
                                speechFrames = speechFrames2;
                                speaking = speaking2;
                                objectRef = utter6;
                                lastMissTipAt2 = lastMissTipAt;
                                z2 = true;
                            }
                            speechFrames2 = speechFrames;
                            speaking2 = speaking;
                            noiseFloor2 = noiseFloor;
                            nearZeroFrames = nearZeroFrames2;
                            utter2 = objectRef;
                            minBuf = minBuf2;
                            frameCount = frameCount2;
                            preRoll3 = arrayDeque;
                            bufBytes = bufBytes2;
                            silentFrames3 = intRef;
                            frame2 = frame;
                            recorder3 = recorder;
                            voiceControlService = this;
                        } else {
                            noiseFloor2 = noiseFloor2;
                            minBuf = minBuf2;
                            frame2 = frame2;
                            voiceControlService = this;
                        }
                    }
                    AudioRecord recorder4 = recorder3;
                    try {
                        recorder4.stop();
                    } catch (Throwable th11) {
                    }
                    try {
                        recorder4.release();
                    } catch (Throwable th12) {
                    }
                } catch (Throwable th13) {
                    th = th13;
                    recorder2 = recorder3;
                }
            } catch (Throwable th14) {
                th = th14;
                recorder2 = recorder3;
            }
        } catch (Throwable th15) {
            th = th15;
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
        return RangesKt.coerceIn((noiseFloor.element * 2.4d) + 55.0d, 110.0d, 1200.0d);
    }

    private static final double runContinuous$dynOff(Ref.DoubleRef noiseFloor) {
        return RangesKt.coerceIn((noiseFloor.element * 1.7d) + 30.0d, 80.0d, 1000.0d);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [T, java.util.ArrayList] */
    private static final void runContinuous$resetUtter(Ref.ObjectRef<ArrayList<Short>> objectRef, Ref.BooleanRef speaking, Ref.IntRef speechFrames, Ref.IntRef silentFrames) {
        objectRef.element = new ArrayList(96000);
        speaking.element = false;
        speechFrames.element = 0;
        silentFrames.element = 0;
    }

    private static final void runContinuous$endUtterance(Ref.IntRef speechFrames, Ref.ObjectRef<ArrayList<Short>> objectRef, VoiceControlService this$0, final Ref.LongRef lastMissTipAt, Ref.BooleanRef speaking, Ref.IntRef silentFrames) {
        if (speechFrames.element >= 10 && objectRef.element.size() >= 1280) {
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
        runContinuous$resetUtter(objectRef, speaking, speechFrames, silentFrames);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void runContinuous$lambda$4(VoiceControlService this$0) {
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
    public static final void runContinuous$lambda$5(VoiceControlService this$0) {
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
                    VoiceControlService.submitTranscription$lambda$7(h, pcm, this, function1);
                }
            });
        } else if (function1 != null) {
            function1.invoke(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void submitTranscription$lambda$7(long $h, short[] pcm, final VoiceControlService this$0, final Function1 $onResult) {
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
                VoiceControlService.submitTranscription$lambda$7$lambda$6(text, $onResult, this$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void submitTranscription$lambda$7$lambda$6(String $text, Function1 $onResult, VoiceControlService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String t = $text != null ? StringsKt.trim((CharSequence) $text).toString() : null;
        Log.i("VoiceCtrl", "asr text='" + t + "'");
        String str = t;
        if (str == null || StringsKt.isBlank(str)) {
            if ($onResult != null) {
                $onResult.invoke(false);
                return;
            }
            return;
        }
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

    private final void dispatch(VoiceCommandRouter.Command cmd) {
        final Context ctx = getApplicationContext();
        switch (WhenMappings.$EnumSwitchMapping$0[cmd.ordinal()]) {
            case 1:
                TurnManager turnManager = TurnManager.INSTANCE;
                Intrinsics.checkNotNull(ctx);
                if (turnManager.requestTurn(ctx, "离线声控-下一页")) {
                    XiaoFanVoice.INSTANCE.tip(ctx, "好，下一页");
                    return;
                } else {
                    XiaoFanVoice.INSTANCE.tip(ctx, "翻页没成功，检查无障碍服务哦");
                    return;
                }
            case 2:
                TurnManager turnManager2 = TurnManager.INSTANCE;
                Intrinsics.checkNotNull(ctx);
                if (turnManager2.requestPrev(ctx, "离线声控-上一页")) {
                    XiaoFanVoice.INSTANCE.tip(ctx, "好，回到上一页");
                    return;
                } else {
                    XiaoFanVoice.INSTANCE.tip(ctx, "回退没成功，检查无障碍服务哦");
                    return;
                }
            case 3:
                TurnManager turnManager3 = TurnManager.INSTANCE;
                Intrinsics.checkNotNull(ctx);
                turnManager3.setAutoTurn(ctx, true);
                FloatBallService companion = FloatBallService.Companion.getInstance();
                if (companion != null) {
                    companion.syncEngines();
                }
                XiaoFanVoice.INSTANCE.tip(ctx, "好的，继续自动翻页");
                return;
            case 4:
                TurnManager turnManager4 = TurnManager.INSTANCE;
                Intrinsics.checkNotNull(ctx);
                turnManager4.setAutoTurn(ctx, false);
                FloatBallService companion2 = FloatBallService.Companion.getInstance();
                if (companion2 != null) {
                    companion2.syncEngines();
                }
                XiaoFanVoice.INSTANCE.tip(ctx, "已暂停自动翻页");
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
                XiaoFanVoice.INSTANCE.tip(ctx, "好的，翻快一点");
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
                XiaoFanVoice.INSTANCE.tip(ctx, "好的，翻慢一点");
                return;
            case 7:
                startService(new Intent(ctx, FloatBallService.class).setAction(FloatBallService.ACTION_START));
                XiaoFanVoice xiaoFanVoice = XiaoFanVoice.INSTANCE;
                Intrinsics.checkNotNull(ctx);
                xiaoFanVoice.tip(ctx, "悬浮球打开啦");
                return;
            case 8:
                startService(new Intent(ctx, FloatBallService.class).setAction(FloatBallService.ACTION_STOP));
                XiaoFanVoice xiaoFanVoice2 = XiaoFanVoice.INSTANCE;
                Intrinsics.checkNotNull(ctx);
                xiaoFanVoice2.tip(ctx, "悬浮球收起啦");
                return;
            case 9:
                AppPrefs appPrefs3 = AppPrefs.INSTANCE;
                Intrinsics.checkNotNull(ctx);
                appPrefs3.setAutoSwipeOn(ctx, true);
                FloatBallService companion3 = FloatBallService.Companion.getInstance();
                if (companion3 != null) {
                    companion3.syncEngines();
                }
                XiaoFanVoice.INSTANCE.tip(ctx, "开始帮你刷视频");
                return;
            case 10:
                AppPrefs appPrefs4 = AppPrefs.INSTANCE;
                Intrinsics.checkNotNull(ctx);
                appPrefs4.setAutoSwipeOn(ctx, false);
                FloatBallService companion4 = FloatBallService.Companion.getInstance();
                if (companion4 != null) {
                    companion4.syncEngines();
                }
                XiaoFanVoice.INSTANCE.tip(ctx, "已暂停刷视频");
                return;
            case 11:
                XiaoFanVoice xiaoFanVoice3 = XiaoFanVoice.INSTANCE;
                Intrinsics.checkNotNull(ctx);
                xiaoFanVoice3.tip(ctx, XiaoFanBrain.INSTANCE.speakTimeNow());
                return;
            case 12:
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
                        XiaoFanVoice xiaoFanVoice4 = XiaoFanVoice.INSTANCE;
                        Context ctx2 = ctx;
                        Intrinsics.checkNotNullExpressionValue(ctx2, "$ctx");
                        xiaoFanVoice4.tip(ctx2, text);
                    }
                });
                return;
            case 13:
                XiaoFanVoice xiaoFanVoice4 = XiaoFanVoice.INSTANCE;
                Intrinsics.checkNotNull(ctx);
                xiaoFanVoice4.tip(ctx, "好的");
                return;
            case 14:
                TurnManager turnManager5 = TurnManager.INSTANCE;
                Intrinsics.checkNotNull(ctx);
                turnManager5.setAutoTurn(ctx, false);
                FloatBallService companion5 = FloatBallService.Companion.getInstance();
                if (companion5 != null) {
                    companion5.syncEngines();
                }
                XiaoFanVoice.INSTANCE.tip(ctx, "好的，已取消");
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
                    VoiceControlService.stopEverything$lambda$8(h);
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
    public static final void stopEverything$lambda$8(long $h) {
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
