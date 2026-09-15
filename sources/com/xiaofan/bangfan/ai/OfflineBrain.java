package com.xiaofan.bangfan.ai;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.xiaofan.bangfan.ai.OfflineBrain;
import com.xiaofan.bangfan.ai.OfflineModels;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: OfflineBrain.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001:\u0003 !\"B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\rJ\u000e\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0007J \u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0010\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0007H\u0002J\u000e\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\rJ\u000e\u0010\u001f\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00040\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000f0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/xiaofan/bangfan/ai/OfflineBrain;", "", "()V", "bootstrapped", "Ljava/util/concurrent/atomic/AtomicBoolean;", "busy", "Ljava/util/concurrent/ConcurrentHashMap;", "Lcom/xiaofan/bangfan/ai/OfflineModels$Kind;", "io", "Ljava/util/concurrent/ExecutorService;", "kotlin.jvm.PlatformType", "listeners", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lcom/xiaofan/bangfan/ai/OfflineBrain$Listener;", "states", "Lcom/xiaofan/bangfan/ai/OfflineBrain$State;", "addListener", "", "l", "bootstrap", "ctx", "Landroid/content/Context;", "download", "kind", "emit", "phase", "Lcom/xiaofan/bangfan/ai/OfflineBrain$Phase;", NotificationCompat.CATEGORY_PROGRESS, "", "flag", "removeListener", "state", "Listener", "Phase", "State", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class OfflineBrain {
    public static final OfflineBrain INSTANCE = new OfflineBrain();
    private static final ExecutorService io = Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: com.xiaofan.bangfan.ai.OfflineBrain$$ExternalSyntheticLambda1
        @Override // java.util.concurrent.ThreadFactory
        public final Thread newThread(Runnable runnable) {
            Thread io$lambda$0;
            io$lambda$0 = OfflineBrain.io$lambda$0(runnable);
            return io$lambda$0;
        }
    });
    private static final ConcurrentHashMap<OfflineModels.Kind, AtomicBoolean> busy = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<OfflineModels.Kind, State> states = new ConcurrentHashMap<>();
    private static final CopyOnWriteArrayList<Listener> listeners = new CopyOnWriteArrayList<>();
    private static final AtomicBoolean bootstrapped = new AtomicBoolean(false);

    /* compiled from: OfflineBrain.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/xiaofan/bangfan/ai/OfflineBrain$Listener;", "", "onState", "", "kind", "Lcom/xiaofan/bangfan/ai/OfflineModels$Kind;", "state", "Lcom/xiaofan/bangfan/ai/OfflineBrain$State;", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes3.dex */
    public interface Listener {
        void onState(OfflineModels.Kind kind, State state);
    }

    /* compiled from: OfflineBrain.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/xiaofan/bangfan/ai/OfflineBrain$Phase;", "", "(Ljava/lang/String;I)V", "NOT_STARTED", "DOWNLOADING", "READY", "FAILED", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes3.dex */
    public enum Phase {
        NOT_STARTED,
        DOWNLOADING,
        READY,
        FAILED;
        
        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<Phase> getEntries() {
            return $ENTRIES;
        }
    }

    private OfflineBrain() {
    }

    /* compiled from: OfflineBrain.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/xiaofan/bangfan/ai/OfflineBrain$State;", "", "phase", "Lcom/xiaofan/bangfan/ai/OfflineBrain$Phase;", NotificationCompat.CATEGORY_PROGRESS, "", "(Lcom/xiaofan/bangfan/ai/OfflineBrain$Phase;I)V", "getPhase", "()Lcom/xiaofan/bangfan/ai/OfflineBrain$Phase;", "getProgress", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes3.dex */
    public static final class State {
        private final Phase phase;
        private final int progress;

        public static /* synthetic */ State copy$default(State state, Phase phase, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                phase = state.phase;
            }
            if ((i2 & 2) != 0) {
                i = state.progress;
            }
            return state.copy(phase, i);
        }

        public final Phase component1() {
            return this.phase;
        }

        public final int component2() {
            return this.progress;
        }

        public final State copy(Phase phase, int i) {
            Intrinsics.checkNotNullParameter(phase, "phase");
            return new State(phase, i);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof State) {
                State state = (State) obj;
                return this.phase == state.phase && this.progress == state.progress;
            }
            return false;
        }

        public int hashCode() {
            return (this.phase.hashCode() * 31) + Integer.hashCode(this.progress);
        }

        public String toString() {
            Phase phase = this.phase;
            return "State(phase=" + phase + ", progress=" + this.progress + ")";
        }

        public State(Phase phase, int progress) {
            Intrinsics.checkNotNullParameter(phase, "phase");
            this.phase = phase;
            this.progress = progress;
        }

        public final Phase getPhase() {
            return this.phase;
        }

        public final int getProgress() {
            return this.progress;
        }
    }

    static {
        for (OfflineModels.Kind kind : OfflineModels.Kind.values()) {
            states.put(kind, new State(Phase.NOT_STARTED, 0));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Thread io$lambda$0(Runnable r) {
        return new Thread(r, "xf-model-io");
    }

    public final void addListener(Listener l) {
        Intrinsics.checkNotNullParameter(l, "l");
        listeners.addIfAbsent(l);
    }

    public final void removeListener(Listener l) {
        Intrinsics.checkNotNullParameter(l, "l");
        listeners.remove(l);
    }

    public final State state(OfflineModels.Kind kind) {
        Intrinsics.checkNotNullParameter(kind, "kind");
        State state = states.get(kind);
        return state == null ? new State(Phase.NOT_STARTED, 0) : state;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void emit(OfflineModels.Kind kind, Phase phase, int progress) {
        State s = new State(phase, progress);
        states.put(kind, s);
        Iterable $this$forEach$iv = listeners;
        for (Object element$iv : $this$forEach$iv) {
            Listener it = (Listener) element$iv;
            try {
                it.onState(kind, s);
            } catch (Throwable th) {
            }
        }
    }

    private final AtomicBoolean flag(OfflineModels.Kind kind) {
        AtomicBoolean putIfAbsent;
        ConcurrentMap $this$getOrPut$iv = busy;
        AtomicBoolean atomicBoolean = $this$getOrPut$iv.get(kind);
        if (atomicBoolean == null && (putIfAbsent = $this$getOrPut$iv.putIfAbsent(kind, (atomicBoolean = new AtomicBoolean(false)))) != null) {
            atomicBoolean = putIfAbsent;
        }
        Intrinsics.checkNotNullExpressionValue(atomicBoolean, "getOrPut(...)");
        return atomicBoolean;
    }

    public final void download(Context ctx, final OfflineModels.Kind kind) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        Intrinsics.checkNotNullParameter(kind, "kind");
        final Context app = ctx.getApplicationContext();
        OfflineModels offlineModels = OfflineModels.INSTANCE;
        Intrinsics.checkNotNull(app);
        if (offlineModels.isReady(app, kind)) {
            emit(kind, Phase.READY, 100);
        } else if (flag(kind).compareAndSet(false, true)) {
            io.execute(new Runnable() { // from class: com.xiaofan.bangfan.ai.OfflineBrain$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    OfflineBrain.download$lambda$4(OfflineModels.Kind.this, app);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void download$lambda$4(final OfflineModels.Kind kind, Context $app) {
        Intrinsics.checkNotNullParameter(kind, "$kind");
        OfflineBrain offlineBrain = INSTANCE;
        Phase phase = Phase.DOWNLOADING;
        OfflineModels offlineModels = OfflineModels.INSTANCE;
        Intrinsics.checkNotNull($app);
        offlineBrain.emit(kind, phase, offlineModels.progress($app, kind));
        AtomicBoolean cancel = new AtomicBoolean(false);
        boolean ok = OfflineModels.INSTANCE.download($app, kind, new Function1<Integer, Unit>() { // from class: com.xiaofan.bangfan.ai.OfflineBrain$download$1$ok$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int p) {
                OfflineBrain.INSTANCE.emit(OfflineModels.Kind.this, OfflineBrain.Phase.DOWNLOADING, p);
            }
        }, cancel);
        if (ok) {
            INSTANCE.emit(kind, Phase.READY, 100);
            if (kind == OfflineModels.Kind.LLM) {
                LocalBrain.INSTANCE.ensureLoaded($app, new Function1<String, Unit>() { // from class: com.xiaofan.bangfan.ai.OfflineBrain$download$1$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(String str) {
                        invoke2(str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke  reason: avoid collision after fix types in other method */
                    public final void invoke2(String it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                    }
                });
            }
        } else {
            INSTANCE.emit(kind, Phase.FAILED, OfflineModels.INSTANCE.progress($app, kind));
        }
        INSTANCE.flag(kind).set(false);
    }

    public final void bootstrap(Context ctx) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        Context app = ctx.getApplicationContext();
        OfflineModels offlineModels = OfflineModels.INSTANCE;
        Intrinsics.checkNotNull(app);
        if (offlineModels.isReady(app, OfflineModels.Kind.LLM)) {
            emit(OfflineModels.Kind.LLM, Phase.READY, 100);
            LocalBrain.INSTANCE.ensureLoaded(app, new Function1<String, Unit>() { // from class: com.xiaofan.bangfan.ai.OfflineBrain$bootstrap$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(String str) {
                    invoke2(str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(String it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }
            });
        }
        if (bootstrapped.compareAndSet(false, true)) {
            if (!OfflineModels.INSTANCE.isReady(app, OfflineModels.Kind.LLM)) {
                download(app, OfflineModels.Kind.LLM);
            }
            if (OfflineModels.INSTANCE.isReady(app, OfflineModels.Kind.ASR)) {
                emit(OfflineModels.Kind.ASR, Phase.READY, 100);
            }
        }
    }
}
