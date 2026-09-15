package com.xiaofan.bangfan;

import android.content.Context;
import android.media.AudioRecord;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.xiaofan.bangfan.ai.NativeAsr;
import com.xiaofan.bangfan.ai.OfflineModels;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
/* compiled from: DictationController.kt */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0017\n\u0002\b\u0003\u0018\u0000 32\u00020\u0001:\u000234B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010)\u001a\u00020\u0018H\u0002J\u0012\u0010*\u001a\u00020\u00182\b\u0010+\u001a\u0004\u0018\u00010\u0014H\u0002J\b\u0010,\u001a\u00020\fH\u0002J\u0006\u0010-\u001a\u00020\u0018J\u0006\u0010.\u001a\u00020\fJ\u0006\u0010/\u001a\u00020\u0018J\u0010\u00100\u001a\u00020\u00182\u0006\u00101\u001a\u000202H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R9\u0010\u0012\u001a!\u0012\u0015\u0012\u0013\u0018\u00010\u0014¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR7\u0010\u001d\u001a\u001f\u0012\u0013\u0012\u00110\u001e¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u001a\"\u0004\b!\u0010\u001cR\u001e\u0010\"\u001a\u0012\u0012\u0004\u0012\u00020$0#j\b\u0012\u0004\u0012\u00020$`%X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"Lcom/xiaofan/bangfan/DictationController;", "", "appCtx", "Landroid/content/Context;", "(Landroid/content/Context;)V", "bufLock", "handle", "", "io", "Ljava/util/concurrent/ExecutorService;", "kotlin.jvm.PlatformType", "isRecording", "", "()Z", "main", "Landroid/os/Handler;", "micThread", "Ljava/lang/Thread;", "onResult", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "text", "", "getOnResult", "()Lkotlin/jvm/functions/Function1;", "setOnResult", "(Lkotlin/jvm/functions/Function1;)V", "onState", "Lcom/xiaofan/bangfan/DictationController$State;", "state", "getOnState", "setOnState", "pcm", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "recorder", "Landroid/media/AudioRecord;", "recording", "ensureHandle", "finishFail", NotificationCompat.CATEGORY_MESSAGE, "hasMic", "release", "start", "stop", "transcribe", "data", "", "Companion", "State", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class DictationController {
    public static final Companion Companion = new Companion(null);
    private static final int MIN_SAMPLES = 3840;
    private static final int SAMPLE_RATE = 16000;
    private final Context appCtx;
    private final Object bufLock;
    private volatile long handle;
    private final ExecutorService io;
    private final Handler main;
    private Thread micThread;
    private Function1<? super String, Unit> onResult;
    private Function1<? super State, Unit> onState;
    private final ArrayList<Short> pcm;
    private AudioRecord recorder;
    private volatile boolean recording;

    /* compiled from: DictationController.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/xiaofan/bangfan/DictationController$State;", "", "(Ljava/lang/String;I)V", "IDLE", "LISTENING", "RECOGNIZING", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public enum State {
        IDLE,
        LISTENING,
        RECOGNIZING;
        
        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<State> getEntries() {
            return $ENTRIES;
        }
    }

    public DictationController(Context appCtx) {
        Intrinsics.checkNotNullParameter(appCtx, "appCtx");
        this.appCtx = appCtx;
        this.main = new Handler(Looper.getMainLooper());
        this.io = Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: com.xiaofan.bangfan.DictationController$$ExternalSyntheticLambda4
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                Thread io$lambda$0;
                io$lambda$0 = DictationController.io$lambda$0(runnable);
                return io$lambda$0;
            }
        });
        this.bufLock = new Object();
        this.pcm = new ArrayList<>(480000);
    }

    /* compiled from: DictationController.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/xiaofan/bangfan/DictationController$Companion;", "", "()V", "MIN_SAMPLES", "", "SAMPLE_RATE", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Thread io$lambda$0(Runnable r) {
        return new Thread(r, "xf-dictate");
    }

    public final Function1<State, Unit> getOnState() {
        return this.onState;
    }

    public final void setOnState(Function1<? super State, Unit> function1) {
        this.onState = function1;
    }

    public final Function1<String, Unit> getOnResult() {
        return this.onResult;
    }

    public final void setOnResult(Function1<? super String, Unit> function1) {
        this.onResult = function1;
    }

    private final boolean hasMic() {
        return this.appCtx.checkSelfPermission("android.permission.RECORD_AUDIO") == 0;
    }

    public final boolean isRecording() {
        return this.recording;
    }

    public final boolean start() {
        if (this.recording) {
            return true;
        }
        if (hasMic() && OfflineModels.INSTANCE.isReady(this.appCtx, OfflineModels.Kind.ASR)) {
            this.recording = true;
            Function1<? super State, Unit> function1 = this.onState;
            if (function1 != null) {
                function1.invoke(State.LISTENING);
            }
            this.micThread = new Thread(new Runnable() { // from class: com.xiaofan.bangfan.DictationController$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    DictationController.start$lambda$7(DictationController.this);
                }
            }, "xf-dictate-mic");
            Thread thread = this.micThread;
            if (thread != null) {
                thread.start();
            }
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void start$lambda$7(final DictationController this$0) {
        AudioRecord rec;
        short[] sArr;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Process.setThreadPriority(-16);
        this$0.ensureHandle();
        if (this$0.handle == 0) {
            this$0.main.post(new Runnable() { // from class: com.xiaofan.bangfan.DictationController$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    DictationController.start$lambda$7$lambda$1(DictationController.this);
                }
            });
            return;
        }
        AudioRecord rec2 = null;
        try {
            synchronized (this$0.bufLock) {
                this$0.pcm.clear();
                Unit unit = Unit.INSTANCE;
            }
            int minBuf = AudioRecord.getMinBufferSize(SAMPLE_RATE, 16, 2);
            int bufBytes = Math.max(minBuf, 64000);
            rec = new AudioRecord(6, SAMPLE_RATE, 16, 2, bufBytes);
        } catch (Throwable th) {
            try {
                this$0.main.post(new Runnable() { // from class: com.xiaofan.bangfan.DictationController$$ExternalSyntheticLambda8
                    @Override // java.lang.Runnable
                    public final void run() {
                        DictationController.start$lambda$7$lambda$6(DictationController.this);
                    }
                });
            } finally {
                if (0 != 0) {
                    try {
                        rec2.stop();
                    } catch (Throwable th2) {
                    }
                }
                if (0 != 0) {
                    try {
                        rec2.release();
                    } catch (Throwable th3) {
                    }
                }
                this$0.recorder = null;
            }
        }
        if (rec.getState() != 1) {
            this$0.main.post(new Runnable() { // from class: com.xiaofan.bangfan.DictationController$$ExternalSyntheticLambda7
                @Override // java.lang.Runnable
                public final void run() {
                    DictationController.start$lambda$7$lambda$3(DictationController.this);
                }
            });
            try {
                rec.stop();
            } catch (Throwable th4) {
            }
            try {
                rec.release();
            } catch (Throwable th5) {
            }
            this$0.recorder = null;
            return;
        }
        this$0.recorder = rec;
        rec.startRecording();
        short[] frame = new short[320];
        while (this$0.recording) {
            int n = rec.read(frame, 0, frame.length);
            if (n > 0) {
                synchronized (this$0.bufLock) {
                    if (this$0.pcm.size() < 480000) {
                        for (int i = 0; i < n; i++) {
                            this$0.pcm.add(Short.valueOf(frame[i]));
                        }
                    }
                    Unit unit2 = Unit.INSTANCE;
                }
            }
        }
        synchronized (this$0.bufLock) {
            sArr = new short[this$0.pcm.size()];
            int length = sArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                Short sh = this$0.pcm.get(i2);
                Intrinsics.checkNotNullExpressionValue(sh, "get(...)");
                sArr[i2] = sh.shortValue();
            }
            this$0.pcm.clear();
            Unit unit3 = Unit.INSTANCE;
        }
        this$0.transcribe(sArr);
        try {
            rec.stop();
        } catch (Throwable th6) {
        }
        try {
            rec.release();
        } catch (Throwable th7) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void start$lambda$7$lambda$1(DictationController this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finishFail("识别模型没准备好，稍后再试");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void start$lambda$7$lambda$3(DictationController this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finishFail("麦克风启动失败，检查权限");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void start$lambda$7$lambda$6(DictationController this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finishFail(null);
    }

    public final void stop() {
        if (this.recording) {
            this.recording = false;
        }
    }

    private final void ensureHandle() {
        if (this.handle != 0) {
            return;
        }
        try {
            NativeAsr.INSTANCE.ensureLoaded();
            int threads = RangesKt.coerceIn(Runtime.getRuntime().availableProcessors(), 2, 8);
            NativeAsr nativeAsr = NativeAsr.INSTANCE;
            String absolutePath = OfflineModels.INSTANCE.file(this.appCtx, OfflineModels.Kind.ASR).getAbsolutePath();
            Intrinsics.checkNotNullExpressionValue(absolutePath, "getAbsolutePath(...)");
            this.handle = nativeAsr.nativeLoad(absolutePath, threads);
        } catch (Throwable th) {
            this.handle = 0L;
        }
    }

    private final void transcribe(final short[] data) {
        if (data.length < MIN_SAMPLES) {
            this.main.post(new Runnable() { // from class: com.xiaofan.bangfan.DictationController$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    DictationController.transcribe$lambda$8(DictationController.this);
                }
            });
            return;
        }
        this.main.post(new Runnable() { // from class: com.xiaofan.bangfan.DictationController$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                DictationController.transcribe$lambda$9(DictationController.this);
            }
        });
        this.io.execute(new Runnable() { // from class: com.xiaofan.bangfan.DictationController$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                DictationController.transcribe$lambda$11(DictationController.this, data);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void transcribe$lambda$8(DictationController this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finishFail(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void transcribe$lambda$9(DictationController this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Function1<? super State, Unit> function1 = this$0.onState;
        if (function1 != null) {
            function1.invoke(State.RECOGNIZING);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void transcribe$lambda$11(final DictationController this$0, short[] data) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(data, "$data");
        long h = this$0.handle;
        String str = null;
        if (h != 0) {
            try {
                str = NativeAsr.INSTANCE.nativeTranscribe(h, data, "zh");
            } catch (Throwable th) {
            }
        }
        final String text = str;
        this$0.main.post(new Runnable() { // from class: com.xiaofan.bangfan.DictationController$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                DictationController.transcribe$lambda$11$lambda$10(DictationController.this, text);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void transcribe$lambda$11$lambda$10(DictationController this$0, String $text) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Function1<? super State, Unit> function1 = this$0.onState;
        if (function1 != null) {
            function1.invoke(State.IDLE);
        }
        String t = $text != null ? StringsKt.trim((CharSequence) $text).toString() : null;
        Function1<? super String, Unit> function12 = this$0.onResult;
        if (function12 != null) {
            String str = t;
            function12.invoke(str == null || str.length() == 0 ? null : t);
        }
    }

    private final void finishFail(String msg) {
        this.recording = false;
        Function1<? super State, Unit> function1 = this.onState;
        if (function1 != null) {
            function1.invoke(State.IDLE);
        }
        Function1<? super String, Unit> function12 = this.onResult;
        if (function12 != null) {
            function12.invoke(null);
        }
        if (msg != null) {
            Toast.makeText(this.appCtx, msg, 0).show();
        }
    }

    public final void release() {
        this.recording = false;
        try {
            Thread thread = this.micThread;
            if (thread != null) {
                thread.interrupt();
            }
        } catch (Throwable th) {
        }
        final long h = this.handle;
        this.handle = 0L;
        if (h != 0) {
            this.io.execute(new Runnable() { // from class: com.xiaofan.bangfan.DictationController$$ExternalSyntheticLambda9
                @Override // java.lang.Runnable
                public final void run() {
                    DictationController.release$lambda$12(h);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void release$lambda$12(long $h) {
        try {
            NativeAsr.INSTANCE.nativeUnload($h);
        } catch (Throwable th) {
        }
    }
}
