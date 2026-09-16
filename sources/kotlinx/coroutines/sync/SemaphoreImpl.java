package kotlinx.coroutines.sync;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.Volatile;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KFunction;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.Waiter;
import kotlinx.coroutines.channels.ChannelSegment$$ExternalSyntheticBackportWithForwarding0;
import kotlinx.coroutines.internal.ConcurrentLinkedListKt;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.SegmentOrClosed;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.selects.SelectInstance;
/* compiled from: Semaphore.kt */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0010\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0011\u0010\u0016\u001a\u00020\u0014H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0017Jb\u0010\u0016\u001a\u00020\u0014\"\u0004\b\u0000\u0010\u00182\u0006\u0010\u0019\u001a\u0002H\u00182!\u0010\u001a\u001a\u001d\u0012\u0013\u0012\u0011H\u0018¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u001d0\u00122!\u0010\u001e\u001a\u001d\u0012\u0013\u0012\u0011H\u0018¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u00140\u0012H\u0083\b¢\u0006\u0002\u0010\u001fJ\u0016\u0010\u0016\u001a\u00020\u00142\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00140 H\u0005J\u0011\u0010!\u001a\u00020\u0014H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0017J\u0010\u0010\"\u001a\u00020\u001d2\u0006\u0010\u0019\u001a\u00020#H\u0002J\b\u0010$\u001a\u00020\u0014H\u0002J\b\u0010%\u001a\u00020\u0003H\u0002J\u001e\u0010&\u001a\u00020\u00142\n\u0010'\u001a\u0006\u0012\u0002\b\u00030(2\b\u0010)\u001a\u0004\u0018\u00010*H\u0004J\b\u0010+\u001a\u00020\u0014H\u0016J\b\u0010,\u001a\u00020\u001dH\u0016J\b\u0010-\u001a\u00020\u001dH\u0002J\f\u0010.\u001a\u00020\u001d*\u00020*H\u0002R\t\u0010\u0006\u001a\u00020\u0007X\u0082\u0004R\u0014\u0010\b\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\t\u0010\u000b\u001a\u00020\fX\u0082\u0004R\t\u0010\r\u001a\u00020\fX\u0082\u0004R\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004R\u001a\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006/"}, d2 = {"Lkotlinx/coroutines/sync/SemaphoreImpl;", "Lkotlinx/coroutines/sync/Semaphore;", "permits", "", "acquiredPermits", "(II)V", "_availablePermits", "Lkotlinx/atomicfu/AtomicInt;", "availablePermits", "getAvailablePermits", "()I", "deqIdx", "Lkotlinx/atomicfu/AtomicLong;", "enqIdx", "head", "Lkotlinx/atomicfu/AtomicRef;", "Lkotlinx/coroutines/sync/SemaphoreSegment;", "onCancellationRelease", "Lkotlin/Function1;", "", "", "tail", "acquire", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "W", "waiter", "suspend", "Lkotlin/ParameterName;", "name", "", "onAcquired", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "Lkotlinx/coroutines/CancellableContinuation;", "acquireSlowPath", "addAcquireToQueue", "Lkotlinx/coroutines/Waiter;", "coerceAvailablePermitsAtMaximum", "decPermits", "onAcquireRegFunction", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "ignoredParam", "", "release", "tryAcquire", "tryResumeNextFromQueue", "tryResumeAcquire", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public class SemaphoreImpl implements Semaphore {
    @Volatile
    private volatile int _availablePermits;
    @Volatile
    private volatile long deqIdx;
    @Volatile
    private volatile long enqIdx;
    @Volatile
    private volatile Object head;
    private final Function1<Throwable, Unit> onCancellationRelease;
    private final int permits;
    @Volatile
    private volatile Object tail;
    private static final AtomicReferenceFieldUpdater head$FU = AtomicReferenceFieldUpdater.newUpdater(SemaphoreImpl.class, Object.class, "head");
    private static final AtomicLongFieldUpdater deqIdx$FU = AtomicLongFieldUpdater.newUpdater(SemaphoreImpl.class, "deqIdx");
    private static final AtomicReferenceFieldUpdater tail$FU = AtomicReferenceFieldUpdater.newUpdater(SemaphoreImpl.class, Object.class, "tail");
    private static final AtomicLongFieldUpdater enqIdx$FU = AtomicLongFieldUpdater.newUpdater(SemaphoreImpl.class, "enqIdx");
    private static final AtomicIntegerFieldUpdater _availablePermits$FU = AtomicIntegerFieldUpdater.newUpdater(SemaphoreImpl.class, "_availablePermits");

    @Override // kotlinx.coroutines.sync.Semaphore
    public Object acquire(Continuation<? super Unit> continuation) {
        return acquire$suspendImpl(this, continuation);
    }

    public SemaphoreImpl(int permits, int acquiredPermits) {
        this.permits = permits;
        boolean z = true;
        if (!(this.permits > 0)) {
            throw new IllegalArgumentException(("Semaphore should have at least 1 permit, but had " + this.permits).toString());
        }
        if (!((acquiredPermits < 0 || acquiredPermits > this.permits) ? false : z)) {
            throw new IllegalArgumentException(("The number of acquired permits should be in 0.." + this.permits).toString());
        }
        SemaphoreSegment s = new SemaphoreSegment(0L, null, 2);
        this.head = s;
        this.tail = s;
        this._availablePermits = this.permits - acquiredPermits;
        this.onCancellationRelease = new Function1<Throwable, Unit>() { // from class: kotlinx.coroutines.sync.SemaphoreImpl$onCancellationRelease$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                SemaphoreImpl.this.release();
            }
        };
    }

    @Override // kotlinx.coroutines.sync.Semaphore
    public int getAvailablePermits() {
        return Math.max(_availablePermits$FU.get(this), 0);
    }

    @Override // kotlinx.coroutines.sync.Semaphore
    public boolean tryAcquire() {
        while (true) {
            int p = _availablePermits$FU.get(this);
            if (p > this.permits) {
                coerceAvailablePermitsAtMaximum();
            } else if (p <= 0) {
                return false;
            } else {
                if (_availablePermits$FU.compareAndSet(this, p, p - 1)) {
                    return true;
                }
            }
        }
    }

    static /* synthetic */ Object acquire$suspendImpl(SemaphoreImpl $this, Continuation<? super Unit> continuation) {
        Object acquireSlowPath;
        int p = $this.decPermits();
        return (p <= 0 && (acquireSlowPath = $this.acquireSlowPath(continuation)) == IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? acquireSlowPath : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object acquireSlowPath(Continuation<? super Unit> continuation) {
        CancellableContinuationImpl cancellable$iv = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(continuation));
        try {
            if (!addAcquireToQueue(cancellable$iv)) {
                acquire((CancellableContinuation<? super Unit>) cancellable$iv);
            }
            Object result = cancellable$iv.getResult();
            if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return result == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? result : Unit.INSTANCE;
        } catch (Throwable e$iv) {
            cancellable$iv.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
            throw e$iv;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Incorrect condition in loop: B:4:0x0007 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void acquire(kotlinx.coroutines.CancellableContinuation<? super kotlin.Unit> r8) {
        /*
            r7 = this;
            r0 = r7
            r1 = 0
        L2:
            int r2 = r0.decPermits()
            if (r2 <= 0) goto L14
            r3 = r8
            r4 = 0
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            kotlin.jvm.functions.Function1<java.lang.Throwable, kotlin.Unit> r6 = r7.onCancellationRelease
            r3.resume(r5, r6)
            goto L24
        L14:
            r3 = r8
            r4 = 0
            java.lang.String r5 = "null cannot be cast to non-null type kotlinx.coroutines.Waiter"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3, r5)
            r5 = r3
            kotlinx.coroutines.Waiter r5 = (kotlinx.coroutines.Waiter) r5
            boolean r3 = r7.addAcquireToQueue(r5)
            if (r3 == 0) goto L2
        L24:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.sync.SemaphoreImpl.acquire(kotlinx.coroutines.CancellableContinuation):void");
    }

    /* JADX WARN: Incorrect condition in loop: B:4:0x0006 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final <W> void acquire(W r4, kotlin.jvm.functions.Function1<? super W, java.lang.Boolean> r5, kotlin.jvm.functions.Function1<? super W, kotlin.Unit> r6) {
        /*
            r3 = this;
            r0 = 0
        L1:
            int r1 = r3.decPermits()
            if (r1 <= 0) goto Lc
            r6.invoke(r4)
            return
        Lc:
            java.lang.Object r2 = r5.invoke(r4)
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            if (r2 == 0) goto L1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.sync.SemaphoreImpl.acquire(java.lang.Object, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1):void");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Incorrect condition in loop: B:4:0x0007 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onAcquireRegFunction(kotlinx.coroutines.selects.SelectInstance<?> r7, java.lang.Object r8) {
        /*
            r6 = this;
            r0 = r6
            r1 = 0
        L2:
            int r2 = r0.decPermits()
            if (r2 <= 0) goto L12
            r3 = r7
            r4 = 0
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            r3.selectInRegistrationPhase(r5)
            goto L22
        L12:
            r3 = r7
            r4 = 0
            java.lang.String r5 = "null cannot be cast to non-null type kotlinx.coroutines.Waiter"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3, r5)
            r5 = r3
            kotlinx.coroutines.Waiter r5 = (kotlinx.coroutines.Waiter) r5
            boolean r3 = r6.addAcquireToQueue(r5)
            if (r3 == 0) goto L2
        L22:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.sync.SemaphoreImpl.onAcquireRegFunction(kotlinx.coroutines.selects.SelectInstance, java.lang.Object):void");
    }

    private final int decPermits() {
        int p;
        do {
            p = _availablePermits$FU.getAndDecrement(this);
        } while (p > this.permits);
        return p;
    }

    @Override // kotlinx.coroutines.sync.Semaphore
    public void release() {
        do {
            int p = _availablePermits$FU.getAndIncrement(this);
            if (p >= this.permits) {
                coerceAvailablePermitsAtMaximum();
                throw new IllegalStateException(("The number of released permits cannot be greater than " + this.permits).toString());
            } else if (p >= 0) {
                return;
            }
        } while (!tryResumeNextFromQueue());
    }

    private final void coerceAvailablePermitsAtMaximum() {
        int cur;
        do {
            cur = _availablePermits$FU.get(this);
            if (cur <= this.permits) {
                return;
            }
        } while (!_availablePermits$FU.compareAndSet(this, cur, this.permits));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean addAcquireToQueue(Waiter waiter) {
        int i;
        Object s$iv;
        int i2;
        Object expected$iv;
        Object value$iv;
        Symbol symbol;
        long id$iv;
        boolean z;
        SemaphoreSegment curTail = (SemaphoreSegment) tail$FU.get(this);
        long enqIdx = enqIdx$FU.getAndIncrement(this);
        KFunction createNewSegment = SemaphoreImpl$addAcquireToQueue$createNewSegment$1.INSTANCE;
        AtomicReferenceFieldUpdater atomicfu$handler$iv = tail$FU;
        i = SemaphoreKt.SEGMENT_SIZE;
        long id$iv2 = enqIdx / i;
        while (true) {
            s$iv = ConcurrentLinkedListKt.findSegmentInternal(curTail, id$iv2, (Function2) createNewSegment);
            if (SegmentOrClosed.m1840isClosedimpl(s$iv)) {
                break;
            }
            Segment to$iv$iv = SegmentOrClosed.m1838getSegmentimpl(s$iv);
            int $i$f$moveForward$atomicfu = 0;
            while (true) {
                Segment cur$iv$iv = (Segment) atomicfu$handler$iv.get(this);
                int $i$f$moveForward$atomicfu2 = $i$f$moveForward$atomicfu;
                long j = cur$iv$iv.id;
                id$iv = id$iv2;
                long id$iv3 = to$iv$iv.id;
                if (j >= id$iv3) {
                    z = true;
                    break;
                } else if (!to$iv$iv.tryIncPointers$kotlinx_coroutines_core()) {
                    z = false;
                    break;
                } else if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicfu$handler$iv, this, cur$iv$iv, to$iv$iv)) {
                    if (cur$iv$iv.decPointers$kotlinx_coroutines_core()) {
                        cur$iv$iv.remove();
                    }
                    z = true;
                } else {
                    if (to$iv$iv.decPointers$kotlinx_coroutines_core()) {
                        to$iv$iv.remove();
                    }
                    $i$f$moveForward$atomicfu = $i$f$moveForward$atomicfu2;
                    id$iv2 = id$iv;
                }
            }
            if (z) {
                break;
            }
            id$iv2 = id$iv;
        }
        SemaphoreSegment segment = (SemaphoreSegment) SegmentOrClosed.m1838getSegmentimpl(s$iv);
        i2 = SemaphoreKt.SEGMENT_SIZE;
        int i3 = (int) (enqIdx % i2);
        if (!ChannelSegment$$ExternalSyntheticBackportWithForwarding0.m(segment.getAcquirers(), i3, null, waiter)) {
            expected$iv = SemaphoreKt.PERMIT;
            value$iv = SemaphoreKt.TAKEN;
            if (!ChannelSegment$$ExternalSyntheticBackportWithForwarding0.m(segment.getAcquirers(), i3, expected$iv, value$iv)) {
                if (!DebugKt.getASSERTIONS_ENABLED()) {
                    return false;
                }
                Object obj = segment.getAcquirers().get(i3);
                symbol = SemaphoreKt.BROKEN;
                if (obj == symbol) {
                    return false;
                }
                throw new AssertionError();
            } else if (waiter instanceof CancellableContinuation) {
                Intrinsics.checkNotNull(waiter, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<kotlin.Unit>");
                CancellableContinuation cancellableContinuation = (CancellableContinuation) waiter;
                ((CancellableContinuation) waiter).resume(Unit.INSTANCE, this.onCancellationRelease);
                return true;
            } else if (waiter instanceof SelectInstance) {
                ((SelectInstance) waiter).selectInRegistrationPhase(Unit.INSTANCE);
                return true;
            } else {
                throw new IllegalStateException(("unexpected: " + waiter).toString());
            }
        }
        waiter.invokeOnCancellation(segment, i3);
        return true;
    }

    private final boolean tryResumeNextFromQueue() {
        int i;
        Object s$iv;
        int i2;
        long deqIdx;
        int i3;
        Symbol symbol;
        Symbol symbol2;
        int i4;
        Object expected$iv;
        Object value$iv;
        Symbol symbol3;
        SemaphoreSegment curHead;
        boolean z;
        SemaphoreSegment curHead2 = (SemaphoreSegment) head$FU.get(this);
        long deqIdx2 = deqIdx$FU.getAndIncrement(this);
        i = SemaphoreKt.SEGMENT_SIZE;
        long id = deqIdx2 / i;
        KFunction createNewSegment = SemaphoreImpl$tryResumeNextFromQueue$createNewSegment$1.INSTANCE;
        AtomicReferenceFieldUpdater atomicfu$handler$iv = head$FU;
        while (true) {
            s$iv = ConcurrentLinkedListKt.findSegmentInternal(curHead2, id, (Function2) createNewSegment);
            if (SegmentOrClosed.m1840isClosedimpl(s$iv)) {
                deqIdx = deqIdx2;
                break;
            }
            Segment to$iv$iv = SegmentOrClosed.m1838getSegmentimpl(s$iv);
            int $i$f$moveForward$atomicfu = 0;
            while (true) {
                Segment cur$iv$iv = (Segment) atomicfu$handler$iv.get(this);
                int $i$f$moveForward$atomicfu2 = $i$f$moveForward$atomicfu;
                curHead = curHead2;
                deqIdx = deqIdx2;
                if (cur$iv$iv.id >= to$iv$iv.id) {
                    z = true;
                    break;
                } else if (!to$iv$iv.tryIncPointers$kotlinx_coroutines_core()) {
                    z = false;
                    break;
                } else if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicfu$handler$iv, this, cur$iv$iv, to$iv$iv)) {
                    if (cur$iv$iv.decPointers$kotlinx_coroutines_core()) {
                        cur$iv$iv.remove();
                    }
                    z = true;
                } else {
                    if (to$iv$iv.decPointers$kotlinx_coroutines_core()) {
                        to$iv$iv.remove();
                    }
                    $i$f$moveForward$atomicfu = $i$f$moveForward$atomicfu2;
                    curHead2 = curHead;
                    deqIdx2 = deqIdx;
                }
            }
            if (z) {
                break;
            }
            curHead2 = curHead;
            deqIdx2 = deqIdx;
        }
        SemaphoreSegment segment = (SemaphoreSegment) SegmentOrClosed.m1838getSegmentimpl(s$iv);
        segment.cleanPrev();
        if (segment.id > id) {
            return false;
        }
        i3 = SemaphoreKt.SEGMENT_SIZE;
        int i5 = (int) (deqIdx % i3);
        symbol = SemaphoreKt.PERMIT;
        Object value$iv2 = segment.getAcquirers().getAndSet(i5, symbol);
        if (value$iv2 == null) {
            i4 = SemaphoreKt.MAX_SPIN_CYCLES;
            for (i2 = 0; i2 < i4; i2++) {
                Object obj = segment.getAcquirers().get(i5);
                symbol3 = SemaphoreKt.TAKEN;
                if (obj == symbol3) {
                    return true;
                }
            }
            expected$iv = SemaphoreKt.PERMIT;
            value$iv = SemaphoreKt.BROKEN;
            return !ChannelSegment$$ExternalSyntheticBackportWithForwarding0.m(segment.getAcquirers(), i5, expected$iv, value$iv);
        }
        symbol2 = SemaphoreKt.CANCELLED;
        if (value$iv2 == symbol2) {
            return false;
        }
        return tryResumeAcquire(value$iv2);
    }

    private final boolean tryResumeAcquire(Object $this$tryResumeAcquire) {
        if ($this$tryResumeAcquire instanceof CancellableContinuation) {
            Intrinsics.checkNotNull($this$tryResumeAcquire, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<kotlin.Unit>");
            CancellableContinuation cancellableContinuation = (CancellableContinuation) $this$tryResumeAcquire;
            Object token = ((CancellableContinuation) $this$tryResumeAcquire).tryResume(Unit.INSTANCE, null, this.onCancellationRelease);
            if (token != null) {
                ((CancellableContinuation) $this$tryResumeAcquire).completeResume(token);
                return true;
            }
            return false;
        } else if ($this$tryResumeAcquire instanceof SelectInstance) {
            return ((SelectInstance) $this$tryResumeAcquire).trySelect(this, Unit.INSTANCE);
        } else {
            throw new IllegalStateException(("unexpected: " + $this$tryResumeAcquire).toString());
        }
    }
}
