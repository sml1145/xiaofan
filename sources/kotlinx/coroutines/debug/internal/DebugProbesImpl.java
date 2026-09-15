package kotlinx.coroutines.debug.internal;

import _COROUTINE.ArtificialStackFrames;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.concurrent.ThreadsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineId;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.JobSupport;
import kotlinx.coroutines.debug.internal.DebugProbesImpl;
import kotlinx.coroutines.internal.ScopeCoroutine;
/* compiled from: DebugProbesImpl.kt */
@Metadata(d1 = {"\u0000Æ\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0017\n\u0002\u0010\u0003\n\u0002\b\n\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\bÁ\u0002\u0018\u00002\u00020\u0001:\u0001}B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J,\u00101\u001a\b\u0012\u0004\u0012\u0002H302\"\u0004\b\u0000\u001032\f\u00104\u001a\b\u0012\u0004\u0012\u0002H3022\b\u00105\u001a\u0004\u0018\u000106H\u0002J\u0010\u00107\u001a\u00020\u00142\u0006\u00108\u001a\u000209H\u0001J\f\u0010:\u001a\b\u0012\u0004\u0012\u00020<0;J\u0011\u0010=\u001a\b\u0012\u0004\u0012\u00020\u00010>¢\u0006\u0002\u0010?J9\u0010@\u001a\b\u0012\u0004\u0012\u0002HA0;\"\b\b\u0000\u0010A*\u00020\u00012\u001e\b\u0004\u0010B\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b\u0012\u0004\u0012\u00020D\u0012\u0004\u0012\u0002HA0CH\u0082\bJ\u0010\u0010E\u001a\u00020\u00142\u0006\u00108\u001a\u000209H\u0002J\f\u0010F\u001a\b\u0012\u0004\u0012\u00020G0;J\"\u0010H\u001a\b\u0012\u0004\u0012\u00020\u00040;2\u0006\u0010I\u001a\u00020<2\f\u0010J\u001a\b\u0012\u0004\u0012\u00020\u00040;J\u000e\u0010K\u001a\u00020)2\u0006\u0010I\u001a\u00020<J.\u0010L\u001a\b\u0012\u0004\u0012\u00020\u00040;2\u0006\u0010M\u001a\u00020)2\b\u0010N\u001a\u0004\u0018\u00010'2\f\u0010J\u001a\b\u0012\u0004\u0012\u00020\u00040;H\u0002J=\u0010O\u001a\u000e\u0012\u0004\u0012\u00020Q\u0012\u0004\u0012\u00020Q0P2\u0006\u0010R\u001a\u00020Q2\f\u0010S\u001a\b\u0012\u0004\u0012\u00020\u00040>2\f\u0010J\u001a\b\u0012\u0004\u0012\u00020\u00040;H\u0002¢\u0006\u0002\u0010TJ1\u0010U\u001a\u00020Q2\u0006\u0010V\u001a\u00020Q2\f\u0010S\u001a\b\u0012\u0004\u0012\u00020\u00040>2\f\u0010J\u001a\b\u0012\u0004\u0012\u00020\u00040;H\u0002¢\u0006\u0002\u0010WJ\u0016\u0010X\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013H\u0002J\u0015\u0010Y\u001a\u00020)2\u0006\u0010Z\u001a\u00020*H\u0000¢\u0006\u0002\b[J\r\u0010\\\u001a\u00020\u0014H\u0000¢\u0006\u0002\b]J\u001e\u0010^\u001a\u00020\u00142\u0006\u00108\u001a\u0002092\f\u0010_\u001a\b\u0012\u0004\u0012\u00020\u00040;H\u0002J\u0014\u0010`\u001a\u00020\u00142\n\u0010a\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0002J'\u0010b\u001a\b\u0012\u0004\u0012\u0002H302\"\u0004\b\u0000\u001032\f\u00104\u001a\b\u0012\u0004\u0012\u0002H302H\u0000¢\u0006\u0002\bcJ\u0019\u0010d\u001a\u00020\u00142\n\u00105\u001a\u0006\u0012\u0002\b\u000302H\u0000¢\u0006\u0002\beJ\u0019\u0010f\u001a\u00020\u00142\n\u00105\u001a\u0006\u0012\u0002\b\u000302H\u0000¢\u0006\u0002\bgJ%\u0010h\u001a\b\u0012\u0004\u0012\u00020\u00040;\"\b\b\u0000\u00103*\u00020i2\u0006\u0010j\u001a\u0002H3H\u0002¢\u0006\u0002\u0010kJ\b\u0010l\u001a\u00020\u0014H\u0002J\b\u0010m\u001a\u00020\u0014H\u0002J\r\u0010n\u001a\u00020\u0014H\u0000¢\u0006\u0002\boJ\u0018\u0010p\u001a\u00020\u00142\u0006\u00105\u001a\u00020\u00072\u0006\u0010M\u001a\u00020)H\u0002J\u001c\u0010q\u001a\u00020\u00142\n\u00105\u001a\u0006\u0012\u0002\b\u0003022\u0006\u0010M\u001a\u00020)H\u0002J(\u0010q\u001a\u00020\u00142\n\u0010a\u001a\u0006\u0012\u0002\b\u00030\u000b2\n\u00105\u001a\u0006\u0012\u0002\b\u0003022\u0006\u0010M\u001a\u00020)H\u0002J4\u0010r\u001a\u00020\u0014*\u00020*2\u0012\u0010s\u001a\u000e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\b0t2\n\u0010u\u001a\u00060vj\u0002`w2\u0006\u0010x\u001a\u00020)H\u0002J\u0010\u0010y\u001a\u00020\u000f*\u0006\u0012\u0002\b\u00030\u000bH\u0002J\u0016\u0010a\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000b*\u0006\u0012\u0002\b\u000302H\u0002J\u0013\u0010a\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000b*\u00020\u0007H\u0082\u0010J\u000f\u0010z\u001a\u0004\u0018\u00010\u0007*\u00020\u0007H\u0082\u0010J\u0012\u0010{\u001a\u000206*\b\u0012\u0004\u0012\u00020\u00040;H\u0002J\f\u0010|\u001a\u00020)*\u00020\u0001H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b0\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u001e\u0010\u000e\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b\u0012\u0004\u0012\u00020\u000f0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u00020\u000fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0017\"\u0004\b\u001c\u0010\u0019R\t\u0010\u001d\u001a\u00020\u001eX\u0082\u0004R\u0011\u0010\u001f\u001a\u00020\u000f8G¢\u0006\u0006\u001a\u0004\b \u0010\u0017R\u001a\u0010!\u001a\u00020\u000fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0017\"\u0004\b#\u0010\u0019R\t\u0010$\u001a\u00020%X\u0082\u0004R\u0010\u0010&\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010(\u001a\u00020)*\u00020*8BX\u0082\u0004¢\u0006\f\u0012\u0004\b+\u0010,\u001a\u0004\b-\u0010.R\u0018\u0010/\u001a\u00020\u000f*\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b/\u00100¨\u0006~"}, d2 = {"Lkotlinx/coroutines/debug/internal/DebugProbesImpl;", "", "()V", "ARTIFICIAL_FRAME", "Ljava/lang/StackTraceElement;", "callerInfoCache", "Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap;", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "Lkotlinx/coroutines/debug/internal/DebugCoroutineInfoImpl;", "capturedCoroutines", "", "Lkotlinx/coroutines/debug/internal/DebugProbesImpl$CoroutineOwner;", "getCapturedCoroutines", "()Ljava/util/Set;", "capturedCoroutinesMap", "", "dateFormat", "Ljava/text/SimpleDateFormat;", "dynamicAttach", "Lkotlin/Function1;", "", "enableCreationStackTraces", "getEnableCreationStackTraces$kotlinx_coroutines_core", "()Z", "setEnableCreationStackTraces$kotlinx_coroutines_core", "(Z)V", "ignoreCoroutinesWithEmptyContext", "getIgnoreCoroutinesWithEmptyContext", "setIgnoreCoroutinesWithEmptyContext", "installations", "Lkotlinx/atomicfu/AtomicInt;", "isInstalled", "isInstalled$kotlinx_coroutines_debug", "sanitizeStackTraces", "getSanitizeStackTraces$kotlinx_coroutines_core", "setSanitizeStackTraces$kotlinx_coroutines_core", "sequenceNumber", "Lkotlinx/atomicfu/AtomicLong;", "weakRefCleanerThread", "Ljava/lang/Thread;", "debugString", "", "Lkotlinx/coroutines/Job;", "getDebugString$annotations", "(Lkotlinx/coroutines/Job;)V", "getDebugString", "(Lkotlinx/coroutines/Job;)Ljava/lang/String;", "isInternalMethod", "(Ljava/lang/StackTraceElement;)Z", "createOwner", "Lkotlin/coroutines/Continuation;", "T", "completion", "frame", "Lkotlinx/coroutines/debug/internal/StackTraceFrame;", "dumpCoroutines", "out", "Ljava/io/PrintStream;", "dumpCoroutinesInfo", "", "Lkotlinx/coroutines/debug/internal/DebugCoroutineInfo;", "dumpCoroutinesInfoAsJsonAndReferences", "", "()[Ljava/lang/Object;", "dumpCoroutinesInfoImpl", "R", "create", "Lkotlin/Function2;", "Lkotlin/coroutines/CoroutineContext;", "dumpCoroutinesSynchronized", "dumpDebuggerInfo", "Lkotlinx/coroutines/debug/internal/DebuggerInfo;", "enhanceStackTraceWithThreadDump", "info", "coroutineTrace", "enhanceStackTraceWithThreadDumpAsJson", "enhanceStackTraceWithThreadDumpImpl", "state", "thread", "findContinuationStartIndex", "Lkotlin/Pair;", "", "indexOfResumeWith", "actualTrace", "(I[Ljava/lang/StackTraceElement;Ljava/util/List;)Lkotlin/Pair;", "findIndexOfFrame", "frameIndex", "(I[Ljava/lang/StackTraceElement;Ljava/util/List;)I", "getDynamicAttach", "hierarchyToString", "job", "hierarchyToString$kotlinx_coroutines_core", "install", "install$kotlinx_coroutines_core", "printStackTrace", "frames", "probeCoroutineCompleted", "owner", "probeCoroutineCreated", "probeCoroutineCreated$kotlinx_coroutines_core", "probeCoroutineResumed", "probeCoroutineResumed$kotlinx_coroutines_core", "probeCoroutineSuspended", "probeCoroutineSuspended$kotlinx_coroutines_core", "sanitizeStackTrace", "", "throwable", "(Ljava/lang/Throwable;)Ljava/util/List;", "startWeakRefCleanerThread", "stopWeakRefCleanerThread", "uninstall", "uninstall$kotlinx_coroutines_core", "updateRunningState", "updateState", "build", "map", "", "builder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "indent", "isFinished", "realCaller", "toStackTraceFrame", "toStringRepr", "CoroutineOwner", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class DebugProbesImpl {
    private static Thread weakRefCleanerThread;
    public static final DebugProbesImpl INSTANCE = new DebugProbesImpl();
    private static final StackTraceElement ARTIFICIAL_FRAME = new ArtificialStackFrames().coroutineCreation();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static final ConcurrentWeakMap<CoroutineOwner<?>, Boolean> capturedCoroutinesMap = new ConcurrentWeakMap<>(false, 1, null);
    private static boolean sanitizeStackTraces = true;
    private static boolean enableCreationStackTraces = true;
    private static boolean ignoreCoroutinesWithEmptyContext = true;
    private static final Function1<Boolean, Unit> dynamicAttach = INSTANCE.getDynamicAttach();
    private static final ConcurrentWeakMap<CoroutineStackFrame, DebugCoroutineInfoImpl> callerInfoCache = new ConcurrentWeakMap<>(true);
    private static final DebugProbesImpl$Installations$kotlinx$VolatileWrapper installations$kotlinx$VolatileWrapper = new DebugProbesImpl$Installations$kotlinx$VolatileWrapper(null);
    private static final DebugProbesImpl$SequenceNumber$kotlinx$VolatileWrapper sequenceNumber$kotlinx$VolatileWrapper = new DebugProbesImpl$SequenceNumber$kotlinx$VolatileWrapper(null);

    private static /* synthetic */ void getDebugString$annotations(Job job) {
    }

    private DebugProbesImpl() {
    }

    private final Set<CoroutineOwner<?>> getCapturedCoroutines() {
        return capturedCoroutinesMap.keySet();
    }

    public final boolean isInstalled$kotlinx_coroutines_debug() {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        atomicIntegerFieldUpdater = DebugProbesImpl$Installations$kotlinx$VolatileWrapper.installations$FU;
        return atomicIntegerFieldUpdater.get(installations$kotlinx$VolatileWrapper) > 0;
    }

    public final boolean getSanitizeStackTraces$kotlinx_coroutines_core() {
        return sanitizeStackTraces;
    }

    public final void setSanitizeStackTraces$kotlinx_coroutines_core(boolean z) {
        sanitizeStackTraces = z;
    }

    public final boolean getEnableCreationStackTraces$kotlinx_coroutines_core() {
        return enableCreationStackTraces;
    }

    public final void setEnableCreationStackTraces$kotlinx_coroutines_core(boolean z) {
        enableCreationStackTraces = z;
    }

    public final boolean getIgnoreCoroutinesWithEmptyContext() {
        return ignoreCoroutinesWithEmptyContext;
    }

    public final void setIgnoreCoroutinesWithEmptyContext(boolean z) {
        ignoreCoroutinesWithEmptyContext = z;
    }

    private final Function1<Boolean, Unit> getDynamicAttach() {
        Object m261constructorimpl;
        try {
            Result.Companion companion = Result.Companion;
            DebugProbesImpl debugProbesImpl = this;
            Class clz = Class.forName("kotlinx.coroutines.debug.internal.ByteBuddyDynamicAttach");
            Constructor ctor = clz.getConstructors()[0];
            Object newInstance = ctor.newInstance(new Object[0]);
            Intrinsics.checkNotNull(newInstance, "null cannot be cast to non-null type kotlin.Function1<kotlin.Boolean, kotlin.Unit>");
            m261constructorimpl = Result.m261constructorimpl((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(newInstance, 1));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            m261constructorimpl = Result.m261constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m267isFailureimpl(m261constructorimpl)) {
            m261constructorimpl = null;
        }
        return (Function1) m261constructorimpl;
    }

    public final void install$kotlinx_coroutines_core() {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        Function1<Boolean, Unit> function1;
        atomicIntegerFieldUpdater = DebugProbesImpl$Installations$kotlinx$VolatileWrapper.installations$FU;
        if (atomicIntegerFieldUpdater.incrementAndGet(installations$kotlinx$VolatileWrapper) > 1) {
            return;
        }
        startWeakRefCleanerThread();
        if (!AgentInstallationType.INSTANCE.isInstalledStatically$kotlinx_coroutines_core() && (function1 = dynamicAttach) != null) {
            function1.invoke(true);
        }
    }

    public final void uninstall$kotlinx_coroutines_core() {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        Function1<Boolean, Unit> function1;
        if (!isInstalled$kotlinx_coroutines_debug()) {
            throw new IllegalStateException("Agent was not installed".toString());
        }
        atomicIntegerFieldUpdater = DebugProbesImpl$Installations$kotlinx$VolatileWrapper.installations$FU;
        if (atomicIntegerFieldUpdater.decrementAndGet(installations$kotlinx$VolatileWrapper) != 0) {
            return;
        }
        stopWeakRefCleanerThread();
        capturedCoroutinesMap.clear();
        callerInfoCache.clear();
        if (!AgentInstallationType.INSTANCE.isInstalledStatically$kotlinx_coroutines_core() && (function1 = dynamicAttach) != null) {
            function1.invoke(false);
        }
    }

    private final void startWeakRefCleanerThread() {
        Thread thread;
        thread = ThreadsKt.thread((r12 & 1) != 0, (r12 & 2) != 0 ? false : true, (r12 & 4) != 0 ? null : null, (r12 & 8) != 0 ? null : "Coroutines Debugger Cleaner", (r12 & 16) != 0 ? -1 : 0, new Function0<Unit>() { // from class: kotlinx.coroutines.debug.internal.DebugProbesImpl$startWeakRefCleanerThread$1
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2() {
                ConcurrentWeakMap concurrentWeakMap;
                concurrentWeakMap = DebugProbesImpl.callerInfoCache;
                concurrentWeakMap.runWeakRefQueueCleaningLoopUntilInterrupted();
            }
        });
        weakRefCleanerThread = thread;
    }

    private final void stopWeakRefCleanerThread() {
        Thread thread = weakRefCleanerThread;
        if (thread == null) {
            return;
        }
        weakRefCleanerThread = null;
        thread.interrupt();
        thread.join();
    }

    public final String hierarchyToString$kotlinx_coroutines_core(Job job) {
        if (isInstalled$kotlinx_coroutines_debug()) {
            Iterable $this$filter$iv = getCapturedCoroutines();
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$filter$iv) {
                CoroutineOwner it = (CoroutineOwner) element$iv$iv;
                if (it.delegate.getContext().get(Job.Key) != null) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
            Iterable $this$associateBy$iv = (List) destination$iv$iv;
            int capacity$iv = RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associateBy$iv, 10)), 16);
            Map destination$iv$iv2 = new LinkedHashMap(capacity$iv);
            for (Object element$iv$iv2 : $this$associateBy$iv) {
                CoroutineOwner it2 = (CoroutineOwner) element$iv$iv2;
                CoroutineOwner it3 = (CoroutineOwner) element$iv$iv2;
                destination$iv$iv2.put(JobKt.getJob(it2.delegate.getContext()), it3.info);
            }
            StringBuilder $this$hierarchyToString_u24lambda_u246 = new StringBuilder();
            INSTANCE.build(job, destination$iv$iv2, $this$hierarchyToString_u24lambda_u246, "");
            String sb = $this$hierarchyToString_u24lambda_u246.toString();
            Intrinsics.checkNotNullExpressionValue(sb, "StringBuilder().apply(builderAction).toString()");
            return sb;
        }
        throw new IllegalStateException("Debug probes are not installed".toString());
    }

    private final void build(Job $this$build, Map<Job, DebugCoroutineInfoImpl> map, StringBuilder builder, String indent) {
        String newIndent;
        DebugCoroutineInfoImpl info = map.get($this$build);
        if (info == null) {
            if (!($this$build instanceof ScopeCoroutine)) {
                builder.append(indent + getDebugString($this$build) + '\n');
                newIndent = indent + '\t';
            } else {
                newIndent = indent;
            }
        } else {
            StackTraceElement element = (StackTraceElement) CollectionsKt.firstOrNull((List<? extends Object>) info.lastObservedStackTrace$kotlinx_coroutines_core());
            String state = info.getState$kotlinx_coroutines_core();
            builder.append(indent + getDebugString($this$build) + ", continuation is " + state + " at line " + element + '\n');
            newIndent = indent + '\t';
        }
        for (Job child : $this$build.getChildren()) {
            build(child, map, builder, newIndent);
        }
    }

    private final String getDebugString(Job $this$debugString) {
        return $this$debugString instanceof JobSupport ? ((JobSupport) $this$debugString).toDebugString() : $this$debugString.toString();
    }

    private final <R> List<R> dumpCoroutinesInfoImpl(final Function2<? super CoroutineOwner<?>, ? super CoroutineContext, ? extends R> function2) {
        if (isInstalled$kotlinx_coroutines_debug()) {
            Sequence $this$sortedBy$iv = CollectionsKt.asSequence(getCapturedCoroutines());
            return SequencesKt.toList(SequencesKt.mapNotNull(SequencesKt.sortedWith($this$sortedBy$iv, new DebugProbesImpl$dumpCoroutinesInfoImpl$$inlined$sortedBy$1()), new Function1<CoroutineOwner<?>, R>() { // from class: kotlinx.coroutines.debug.internal.DebugProbesImpl$dumpCoroutinesInfoImpl$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final R invoke(DebugProbesImpl.CoroutineOwner<?> coroutineOwner) {
                    boolean isFinished;
                    CoroutineContext context;
                    isFinished = DebugProbesImpl.INSTANCE.isFinished(coroutineOwner);
                    if (isFinished || (context = coroutineOwner.info.getContext()) == null) {
                        return null;
                    }
                    return function2.invoke(coroutineOwner, context);
                }
            }));
        }
        throw new IllegalStateException("Debug probes are not installed".toString());
    }

    public final Object[] dumpCoroutinesInfoAsJsonAndReferences() {
        String name;
        List coroutinesInfo = dumpCoroutinesInfo();
        int size = coroutinesInfo.size();
        ArrayList lastObservedThreads = new ArrayList(size);
        ArrayList lastObservedFrames = new ArrayList(size);
        ArrayList coroutinesInfoAsJson = new ArrayList(size);
        for (DebugCoroutineInfo info : coroutinesInfo) {
            CoroutineContext context = info.getContext();
            CoroutineName coroutineName = (CoroutineName) context.get(CoroutineName.Key);
            Long l = null;
            String name2 = (coroutineName == null || (name = coroutineName.getName()) == null) ? null : toStringRepr(name);
            CoroutineDispatcher coroutineDispatcher = (CoroutineDispatcher) context.get(CoroutineDispatcher.Key);
            String dispatcher = coroutineDispatcher != null ? toStringRepr(coroutineDispatcher) : null;
            StringBuilder append = new StringBuilder().append("\n                {\n                    \"name\": ").append(name2).append(",\n                    \"id\": ");
            CoroutineId coroutineId = (CoroutineId) context.get(CoroutineId.Key);
            if (coroutineId != null) {
                l = Long.valueOf(coroutineId.getId());
            }
            coroutinesInfoAsJson.add(StringsKt.trimIndent(append.append(l).append(",\n                    \"dispatcher\": ").append(dispatcher).append(",\n                    \"sequenceNumber\": ").append(info.getSequenceNumber()).append(",\n                    \"state\": \"").append(info.getState()).append("\"\n                } \n                ").toString()));
            lastObservedFrames.add(info.getLastObservedFrame());
            lastObservedThreads.add(info.getLastObservedThread());
        }
        ArrayList $this$toTypedArray$iv = lastObservedThreads;
        ArrayList $this$toTypedArray$iv2 = lastObservedFrames;
        List $this$toTypedArray$iv3 = coroutinesInfo;
        return new Object[]{'[' + CollectionsKt.joinToString$default(coroutinesInfoAsJson, null, null, null, 0, null, null, 63, null) + ']', $this$toTypedArray$iv.toArray(new Thread[0]), $this$toTypedArray$iv2.toArray(new CoroutineStackFrame[0]), $this$toTypedArray$iv3.toArray(new DebugCoroutineInfo[0])};
    }

    public final String enhanceStackTraceWithThreadDumpAsJson(DebugCoroutineInfo info) {
        List stackTraceElements = enhanceStackTraceWithThreadDump(info, info.lastObservedStackTrace());
        List stackTraceElementsInfoAsJson = new ArrayList();
        for (StackTraceElement element : stackTraceElements) {
            StringBuilder append = new StringBuilder().append("\n                {\n                    \"declaringClass\": \"").append(element.getClassName()).append("\",\n                    \"methodName\": \"").append(element.getMethodName()).append("\",\n                    \"fileName\": ");
            String fileName = element.getFileName();
            stackTraceElementsInfoAsJson.add(StringsKt.trimIndent(append.append(fileName != null ? toStringRepr(fileName) : null).append(",\n                    \"lineNumber\": ").append(element.getLineNumber()).append("\n                }\n                ").toString()));
        }
        return '[' + CollectionsKt.joinToString$default(stackTraceElementsInfoAsJson, null, null, null, 0, null, null, 63, null) + ']';
    }

    private final String toStringRepr(Object $this$toStringRepr) {
        String repr;
        repr = DebugProbesImplKt.repr($this$toStringRepr.toString());
        return repr;
    }

    public final List<DebugCoroutineInfo> dumpCoroutinesInfo() {
        if (isInstalled$kotlinx_coroutines_debug()) {
            Sequence $this$sortedBy$iv$iv = CollectionsKt.asSequence(getCapturedCoroutines());
            return SequencesKt.toList(SequencesKt.mapNotNull(SequencesKt.sortedWith($this$sortedBy$iv$iv, new DebugProbesImpl$dumpCoroutinesInfoImpl$$inlined$sortedBy$1()), new Function1<CoroutineOwner<?>, DebugCoroutineInfo>() { // from class: kotlinx.coroutines.debug.internal.DebugProbesImpl$dumpCoroutinesInfo$$inlined$dumpCoroutinesInfoImpl$1
                @Override // kotlin.jvm.functions.Function1
                public final DebugCoroutineInfo invoke(DebugProbesImpl.CoroutineOwner<?> coroutineOwner) {
                    boolean isFinished;
                    CoroutineContext context;
                    isFinished = DebugProbesImpl.INSTANCE.isFinished(coroutineOwner);
                    if (isFinished || (context = coroutineOwner.info.getContext()) == null) {
                        return null;
                    }
                    return new DebugCoroutineInfo(coroutineOwner.info, context);
                }
            }));
        }
        throw new IllegalStateException("Debug probes are not installed".toString());
    }

    public final List<DebuggerInfo> dumpDebuggerInfo() {
        if (isInstalled$kotlinx_coroutines_debug()) {
            Sequence $this$sortedBy$iv$iv = CollectionsKt.asSequence(getCapturedCoroutines());
            return SequencesKt.toList(SequencesKt.mapNotNull(SequencesKt.sortedWith($this$sortedBy$iv$iv, new DebugProbesImpl$dumpCoroutinesInfoImpl$$inlined$sortedBy$1()), new Function1<CoroutineOwner<?>, DebuggerInfo>() { // from class: kotlinx.coroutines.debug.internal.DebugProbesImpl$dumpDebuggerInfo$$inlined$dumpCoroutinesInfoImpl$1
                @Override // kotlin.jvm.functions.Function1
                public final DebuggerInfo invoke(DebugProbesImpl.CoroutineOwner<?> coroutineOwner) {
                    boolean isFinished;
                    CoroutineContext context;
                    isFinished = DebugProbesImpl.INSTANCE.isFinished(coroutineOwner);
                    if (isFinished || (context = coroutineOwner.info.getContext()) == null) {
                        return null;
                    }
                    return new DebuggerInfo(coroutineOwner.info, context);
                }
            }));
        }
        throw new IllegalStateException("Debug probes are not installed".toString());
    }

    public final void dumpCoroutines(PrintStream out) {
        synchronized (out) {
            INSTANCE.dumpCoroutinesSynchronized(out);
            Unit unit = Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isFinished(CoroutineOwner<?> coroutineOwner) {
        Job job;
        CoroutineContext context = coroutineOwner.info.getContext();
        if (context == null || (job = (Job) context.get(Job.Key)) == null || !job.isCompleted()) {
            return false;
        }
        capturedCoroutinesMap.remove(coroutineOwner);
        return true;
    }

    private final void dumpCoroutinesSynchronized(PrintStream out) {
        String state;
        if (!isInstalled$kotlinx_coroutines_debug()) {
            throw new IllegalStateException("Debug probes are not installed".toString());
        }
        out.print("Coroutines dump " + dateFormat.format(Long.valueOf(System.currentTimeMillis())));
        Sequence $this$sortedBy$iv = SequencesKt.filter(CollectionsKt.asSequence(getCapturedCoroutines()), new Function1<CoroutineOwner<?>, Boolean>() { // from class: kotlinx.coroutines.debug.internal.DebugProbesImpl$dumpCoroutinesSynchronized$2
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(DebugProbesImpl.CoroutineOwner<?> coroutineOwner) {
                boolean isFinished;
                isFinished = DebugProbesImpl.INSTANCE.isFinished(coroutineOwner);
                return Boolean.valueOf(!isFinished);
            }
        });
        for (Object element$iv : SequencesKt.sortedWith($this$sortedBy$iv, new Comparator() { // from class: kotlinx.coroutines.debug.internal.DebugProbesImpl$dumpCoroutinesSynchronized$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                DebugProbesImpl.CoroutineOwner it = (DebugProbesImpl.CoroutineOwner) t;
                DebugProbesImpl.CoroutineOwner it2 = (DebugProbesImpl.CoroutineOwner) t2;
                return ComparisonsKt.compareValues(Long.valueOf(it.info.sequenceNumber), Long.valueOf(it2.info.sequenceNumber));
            }
        })) {
            CoroutineOwner owner = (CoroutineOwner) element$iv;
            DebugCoroutineInfoImpl info = owner.info;
            List observedStackTrace = info.lastObservedStackTrace$kotlinx_coroutines_core();
            List enhancedStackTrace = INSTANCE.enhanceStackTraceWithThreadDumpImpl(info.getState$kotlinx_coroutines_core(), info.lastObservedThread, observedStackTrace);
            if (Intrinsics.areEqual(info.getState$kotlinx_coroutines_core(), DebugCoroutineInfoImplKt.RUNNING) && enhancedStackTrace == observedStackTrace) {
                state = info.getState$kotlinx_coroutines_core() + " (Last suspension stacktrace, not an actual stacktrace)";
            } else {
                state = info.getState$kotlinx_coroutines_core();
            }
            out.print("\n\nCoroutine " + owner.delegate + ", state: " + state);
            if (observedStackTrace.isEmpty()) {
                out.print("\n\tat " + ARTIFICIAL_FRAME);
                INSTANCE.printStackTrace(out, info.getCreationStackTrace());
            } else {
                INSTANCE.printStackTrace(out, enhancedStackTrace);
            }
        }
    }

    private final void printStackTrace(PrintStream out, List<StackTraceElement> list) {
        List<StackTraceElement> $this$forEach$iv = list;
        for (Object element$iv : $this$forEach$iv) {
            StackTraceElement frame = (StackTraceElement) element$iv;
            out.print("\n\tat " + frame);
        }
    }

    public final List<StackTraceElement> enhanceStackTraceWithThreadDump(DebugCoroutineInfo info, List<StackTraceElement> list) {
        return enhanceStackTraceWithThreadDumpImpl(info.getState(), info.getLastObservedThread(), list);
    }

    private final List<StackTraceElement> enhanceStackTraceWithThreadDumpImpl(String state, Thread thread, List<StackTraceElement> list) {
        Object m261constructorimpl;
        if (!Intrinsics.areEqual(state, DebugCoroutineInfoImplKt.RUNNING) || thread == null) {
            return list;
        }
        try {
            Result.Companion companion = Result.Companion;
            DebugProbesImpl debugProbesImpl = this;
            m261constructorimpl = Result.m261constructorimpl(thread.getStackTrace());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            m261constructorimpl = Result.m261constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m267isFailureimpl(m261constructorimpl)) {
            m261constructorimpl = null;
        }
        StackTraceElement[] actualTrace = (StackTraceElement[]) m261constructorimpl;
        if (actualTrace == null) {
            return list;
        }
        int index$iv = 0;
        int length = actualTrace.length;
        while (true) {
            if (index$iv < length) {
                StackTraceElement it = actualTrace[index$iv];
                if (Intrinsics.areEqual(it.getClassName(), "kotlin.coroutines.jvm.internal.BaseContinuationImpl") && Intrinsics.areEqual(it.getMethodName(), "resumeWith") && Intrinsics.areEqual(it.getFileName(), "ContinuationImpl.kt")) {
                    break;
                }
                index$iv++;
            } else {
                index$iv = -1;
                break;
            }
        }
        int indexOfResumeWith = index$iv;
        Pair<Integer, Integer> findContinuationStartIndex = findContinuationStartIndex(indexOfResumeWith, actualTrace, list);
        int continuationStartFrame = findContinuationStartIndex.component1().intValue();
        int delta = findContinuationStartIndex.component2().intValue();
        if (continuationStartFrame == -1) {
            return list;
        }
        int expectedSize = (((list.size() + indexOfResumeWith) - continuationStartFrame) - 1) - delta;
        ArrayList result = new ArrayList(expectedSize);
        int i = indexOfResumeWith - delta;
        for (int index = 0; index < i; index++) {
            result.add(actualTrace[index]);
        }
        int size = list.size();
        for (int index2 = continuationStartFrame + 1; index2 < size; index2++) {
            result.add(list.get(index2));
        }
        return result;
    }

    private final Pair<Integer, Integer> findContinuationStartIndex(int indexOfResumeWith, StackTraceElement[] actualTrace, List<StackTraceElement> list) {
        for (int i = 0; i < 3; i++) {
            int it = i;
            int result = INSTANCE.findIndexOfFrame((indexOfResumeWith - 1) - it, actualTrace, list);
            if (result != -1) {
                return TuplesKt.to(Integer.valueOf(result), Integer.valueOf(it));
            }
        }
        return TuplesKt.to(-1, 0);
    }

    private final int findIndexOfFrame(int frameIndex, StackTraceElement[] actualTrace, List<StackTraceElement> list) {
        StackTraceElement continuationFrame = (StackTraceElement) ArraysKt.getOrNull(actualTrace, frameIndex);
        if (continuationFrame == null) {
            return -1;
        }
        int index$iv = 0;
        for (Object item$iv : list) {
            StackTraceElement it = (StackTraceElement) item$iv;
            if (Intrinsics.areEqual(it.getFileName(), continuationFrame.getFileName()) && Intrinsics.areEqual(it.getClassName(), continuationFrame.getClassName()) && Intrinsics.areEqual(it.getMethodName(), continuationFrame.getMethodName())) {
                return index$iv;
            }
            index$iv++;
        }
        return -1;
    }

    public final void probeCoroutineResumed$kotlinx_coroutines_core(Continuation<?> continuation) {
        updateState(continuation, DebugCoroutineInfoImplKt.RUNNING);
    }

    public final void probeCoroutineSuspended$kotlinx_coroutines_core(Continuation<?> continuation) {
        updateState(continuation, DebugCoroutineInfoImplKt.SUSPENDED);
    }

    private final void updateState(Continuation<?> continuation, String state) {
        if (isInstalled$kotlinx_coroutines_debug()) {
            if (ignoreCoroutinesWithEmptyContext && continuation.getContext() == EmptyCoroutineContext.INSTANCE) {
                return;
            }
            if (Intrinsics.areEqual(state, DebugCoroutineInfoImplKt.RUNNING)) {
                CoroutineStackFrame stackFrame = continuation instanceof CoroutineStackFrame ? (CoroutineStackFrame) continuation : null;
                if (stackFrame == null) {
                    return;
                }
                updateRunningState(stackFrame, state);
                return;
            }
            CoroutineOwner owner = owner(continuation);
            if (owner == null) {
                return;
            }
            updateState(owner, continuation, state);
        }
    }

    private final void updateRunningState(CoroutineStackFrame frame, String state) {
        DebugCoroutineInfoImpl debugCoroutineInfoImpl;
        DebugCoroutineInfoImpl info;
        boolean shouldBeMatchedWithProbeSuspended;
        if (isInstalled$kotlinx_coroutines_debug()) {
            DebugCoroutineInfoImpl cached = callerInfoCache.remove(frame);
            if (cached != null) {
                info = cached;
                shouldBeMatchedWithProbeSuspended = false;
            } else {
                CoroutineOwner<?> owner = owner(frame);
                if (owner == null || (debugCoroutineInfoImpl = owner.info) == null) {
                    return;
                }
                info = debugCoroutineInfoImpl;
                shouldBeMatchedWithProbeSuspended = true;
                CoroutineStackFrame lastObservedFrame$kotlinx_coroutines_core = info.getLastObservedFrame$kotlinx_coroutines_core();
                CoroutineStackFrame realCaller = lastObservedFrame$kotlinx_coroutines_core != null ? realCaller(lastObservedFrame$kotlinx_coroutines_core) : null;
                if (realCaller != null) {
                    callerInfoCache.remove(realCaller);
                }
            }
            Intrinsics.checkNotNull(frame, "null cannot be cast to non-null type kotlin.coroutines.Continuation<*>");
            info.updateState$kotlinx_coroutines_core(state, (Continuation) frame, shouldBeMatchedWithProbeSuspended);
            CoroutineStackFrame caller = realCaller(frame);
            if (caller == null) {
                return;
            }
            callerInfoCache.put(caller, info);
        }
    }

    private final CoroutineStackFrame realCaller(CoroutineStackFrame $this$realCaller) {
        while (true) {
            CoroutineStackFrame caller = $this$realCaller.getCallerFrame();
            if (caller == null) {
                return null;
            }
            if (caller.getStackTraceElement() != null) {
                return caller;
            }
            $this$realCaller = caller;
        }
    }

    private final void updateState(CoroutineOwner<?> coroutineOwner, Continuation<?> continuation, String state) {
        if (isInstalled$kotlinx_coroutines_debug()) {
            coroutineOwner.info.updateState$kotlinx_coroutines_core(state, continuation, true);
        }
    }

    private final CoroutineOwner<?> owner(Continuation<?> continuation) {
        CoroutineStackFrame coroutineStackFrame = continuation instanceof CoroutineStackFrame ? (CoroutineStackFrame) continuation : null;
        if (coroutineStackFrame != null) {
            return owner(coroutineStackFrame);
        }
        return null;
    }

    private final CoroutineOwner<?> owner(CoroutineStackFrame $this$owner) {
        while (!($this$owner instanceof CoroutineOwner)) {
            CoroutineStackFrame callerFrame = $this$owner.getCallerFrame();
            if (callerFrame == null) {
                return null;
            }
            $this$owner = callerFrame;
        }
        return (CoroutineOwner) $this$owner;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <T> Continuation<T> probeCoroutineCreated$kotlinx_coroutines_core(Continuation<? super T> continuation) {
        StackTraceFrame frame;
        if (isInstalled$kotlinx_coroutines_debug()) {
            if (ignoreCoroutinesWithEmptyContext && continuation.getContext() == EmptyCoroutineContext.INSTANCE) {
                return continuation;
            }
            CoroutineOwner owner = owner(continuation);
            if (owner != null) {
                return continuation;
            }
            if (enableCreationStackTraces) {
                frame = toStackTraceFrame(sanitizeStackTrace(new Exception()));
            } else {
                frame = null;
            }
            return createOwner(continuation, frame);
        }
        return continuation;
    }

    private final StackTraceFrame toStackTraceFrame(List<StackTraceElement> list) {
        Object accumulator$iv = null;
        if (!list.isEmpty()) {
            ListIterator iterator$iv = list.listIterator(list.size());
            while (iterator$iv.hasPrevious()) {
                StackTraceElement frame = iterator$iv.previous();
                Object acc = accumulator$iv;
                accumulator$iv = new StackTraceFrame((CoroutineStackFrame) acc, frame);
            }
        }
        Object initial$iv = accumulator$iv;
        return new StackTraceFrame((CoroutineStackFrame) initial$iv, ARTIFICIAL_FRAME);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final <T> Continuation<T> createOwner(Continuation<? super T> continuation, StackTraceFrame frame) {
        AtomicLongFieldUpdater atomicLongFieldUpdater;
        if (isInstalled$kotlinx_coroutines_debug()) {
            CoroutineContext context = continuation.getContext();
            atomicLongFieldUpdater = DebugProbesImpl$SequenceNumber$kotlinx$VolatileWrapper.sequenceNumber$FU;
            DebugCoroutineInfoImpl info = new DebugCoroutineInfoImpl(context, frame, atomicLongFieldUpdater.incrementAndGet(sequenceNumber$kotlinx$VolatileWrapper));
            CoroutineOwner owner = new CoroutineOwner(continuation, info);
            capturedCoroutinesMap.put(owner, true);
            if (!isInstalled$kotlinx_coroutines_debug()) {
                capturedCoroutinesMap.clear();
            }
            return owner;
        }
        return continuation;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void probeCoroutineCompleted(CoroutineOwner<?> coroutineOwner) {
        CoroutineStackFrame caller;
        capturedCoroutinesMap.remove(coroutineOwner);
        CoroutineStackFrame lastObservedFrame$kotlinx_coroutines_core = coroutineOwner.info.getLastObservedFrame$kotlinx_coroutines_core();
        if (lastObservedFrame$kotlinx_coroutines_core == null || (caller = realCaller(lastObservedFrame$kotlinx_coroutines_core)) == null) {
            return;
        }
        callerInfoCache.remove(caller);
    }

    /* compiled from: DebugProbesImpl.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003B\u001d\b\u0000\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\n\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\u001e\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u0018H\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u0019J\b\u0010\u001a\u001a\u00020\u001bH\u0016R\u0016\u0010\b\u001a\u0004\u0018\u00010\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0012\u0010\u000b\u001a\u00020\fX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00028\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\u0004\u0018\u00010\u00108BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0010\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001c"}, d2 = {"Lkotlinx/coroutines/debug/internal/DebugProbesImpl$CoroutineOwner;", "T", "Lkotlin/coroutines/Continuation;", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "delegate", "info", "Lkotlinx/coroutines/debug/internal/DebugCoroutineInfoImpl;", "(Lkotlin/coroutines/Continuation;Lkotlinx/coroutines/debug/internal/DebugCoroutineInfoImpl;)V", "callerFrame", "getCallerFrame", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "frame", "Lkotlinx/coroutines/debug/internal/StackTraceFrame;", "getFrame", "()Lkotlinx/coroutines/debug/internal/StackTraceFrame;", "getStackTraceElement", "Ljava/lang/StackTraceElement;", "resumeWith", "", "result", "Lkotlin/Result;", "(Ljava/lang/Object;)V", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    public static final class CoroutineOwner<T> implements Continuation<T>, CoroutineStackFrame {
        public final Continuation<T> delegate;
        public final DebugCoroutineInfoImpl info;

        @Override // kotlin.coroutines.Continuation
        public CoroutineContext getContext() {
            return this.delegate.getContext();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public CoroutineOwner(Continuation<? super T> continuation, DebugCoroutineInfoImpl info) {
            this.delegate = continuation;
            this.info = info;
        }

        private final StackTraceFrame getFrame() {
            return this.info.getCreationStackBottom$kotlinx_coroutines_core();
        }

        @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
        public CoroutineStackFrame getCallerFrame() {
            StackTraceFrame frame = getFrame();
            if (frame != null) {
                return frame.getCallerFrame();
            }
            return null;
        }

        @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
        public StackTraceElement getStackTraceElement() {
            StackTraceFrame frame = getFrame();
            if (frame != null) {
                return frame.getStackTraceElement();
            }
            return null;
        }

        @Override // kotlin.coroutines.Continuation
        public void resumeWith(Object result) {
            DebugProbesImpl.INSTANCE.probeCoroutineCompleted(this);
            this.delegate.resumeWith(result);
        }

        public String toString() {
            return this.delegate.toString();
        }
    }

    private final <T extends Throwable> List<StackTraceElement> sanitizeStackTrace(T t) {
        StackTraceElement[] stackTrace = t.getStackTrace();
        int size = stackTrace.length;
        int i = -1;
        int length = stackTrace.length - 1;
        if (length >= 0) {
            while (true) {
                int index$iv = length;
                length--;
                StackTraceElement it = stackTrace[index$iv];
                if (!Intrinsics.areEqual(it.getClassName(), "kotlin.coroutines.jvm.internal.DebugProbesKt")) {
                    if (length < 0) {
                        break;
                    }
                } else {
                    i = index$iv;
                    break;
                }
            }
        }
        int traceStart = i + 1;
        if (!sanitizeStackTraces) {
            int i2 = size - traceStart;
            ArrayList arrayList = new ArrayList(i2);
            for (int i3 = 0; i3 < i2; i3++) {
                int it2 = i3;
                arrayList.add(stackTrace[it2 + traceStart]);
            }
            return arrayList;
        }
        ArrayList result = new ArrayList((size - traceStart) + 1);
        int i4 = traceStart;
        while (i4 < size) {
            if (isInternalMethod(stackTrace[i4])) {
                result.add(stackTrace[i4]);
                int j = i4 + 1;
                while (j < size && isInternalMethod(stackTrace[j])) {
                    j++;
                }
                int k = j - 1;
                while (k > i4 && stackTrace[k].getFileName() == null) {
                    k--;
                }
                if (k > i4 && k < j - 1) {
                    result.add(stackTrace[k]);
                }
                result.add(stackTrace[j - 1]);
                i4 = j;
            } else {
                result.add(stackTrace[i4]);
                i4++;
            }
        }
        return result;
    }

    private final boolean isInternalMethod(StackTraceElement $this$isInternalMethod) {
        return StringsKt.startsWith$default($this$isInternalMethod.getClassName(), "kotlinx.coroutines", false, 2, (Object) null);
    }
}
