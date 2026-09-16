package kotlinx.coroutines;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.Volatile;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.time.DurationKt;
import kotlinx.coroutines.Delay;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.ThreadSafeHeap;
import kotlinx.coroutines.internal.ThreadSafeHeapNode;
/* compiled from: EventLoop.common.kt */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b \u0018\u00002\u00020\u00012\u00020\u0002:\u00043456B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\u0010\u0010\u0018\u001a\n\u0018\u00010\u0019j\u0004\u0018\u0001`\u001aH\u0002J\u001a\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001d2\n\u0010\u001e\u001a\u00060\u0019j\u0002`\u001aJ\u0014\u0010\u001f\u001a\u00020\u00172\n\u0010 \u001a\u00060\u0019j\u0002`\u001aH\u0016J\u0014\u0010!\u001a\u00020\f2\n\u0010 \u001a\u00060\u0019j\u0002`\u001aH\u0002J\b\u0010\"\u001a\u00020\u0013H\u0016J\b\u0010#\u001a\u00020\u0017H\u0002J\b\u0010$\u001a\u00020\u0017H\u0004J\u0016\u0010%\u001a\u00020\u00172\u0006\u0010&\u001a\u00020\u00132\u0006\u0010'\u001a\u00020(J\u0018\u0010)\u001a\u00020*2\u0006\u0010&\u001a\u00020\u00132\u0006\u0010'\u001a\u00020(H\u0002J\u001c\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u00132\n\u0010\u001e\u001a\u00060\u0019j\u0002`\u001aH\u0004J\u001e\u0010.\u001a\u00020\u00172\u0006\u0010-\u001a\u00020\u00132\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u001700H\u0016J\u0010\u00101\u001a\u00020\f2\u0006\u0010 \u001a\u00020(H\u0002J\b\u00102\u001a\u00020\u0017H\u0016R\u0011\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005X\u0082\u0004R\t\u0010\u0007\u001a\u00020\bX\u0082\u0004R\u0011\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0005X\u0082\u0004R$\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f8B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\f8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000eR\u0014\u0010\u0012\u001a\u00020\u00138TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u00067"}, d2 = {"Lkotlinx/coroutines/EventLoopImplBase;", "Lkotlinx/coroutines/EventLoopImplPlatform;", "Lkotlinx/coroutines/Delay;", "()V", "_delayed", "Lkotlinx/atomicfu/AtomicRef;", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTaskQueue;", "_isCompleted", "Lkotlinx/atomicfu/AtomicBoolean;", "_queue", "", "value", "", "isCompleted", "()Z", "setCompleted", "(Z)V", "isEmpty", "nextTime", "", "getNextTime", "()J", "closeQueue", "", "dequeue", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "dispatch", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "enqueue", "task", "enqueueImpl", "processNextEvent", "rescheduleAllDelayed", "resetAll", "schedule", "now", "delayedTask", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "scheduleImpl", "", "scheduleInvokeOnTimeout", "Lkotlinx/coroutines/DisposableHandle;", "timeMillis", "scheduleResumeAfterDelay", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "shouldUnpark", "shutdown", "DelayedResumeTask", "DelayedRunnableTask", "DelayedTask", "DelayedTaskQueue", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public abstract class EventLoopImplBase extends EventLoopImplPlatform implements Delay {
    @Volatile
    private volatile Object _delayed;
    @Volatile
    private volatile int _isCompleted = 0;
    @Volatile
    private volatile Object _queue;
    private static final AtomicReferenceFieldUpdater _queue$FU = AtomicReferenceFieldUpdater.newUpdater(EventLoopImplBase.class, Object.class, "_queue");
    private static final AtomicReferenceFieldUpdater _delayed$FU = AtomicReferenceFieldUpdater.newUpdater(EventLoopImplBase.class, Object.class, "_delayed");
    private static final AtomicIntegerFieldUpdater _isCompleted$FU = AtomicIntegerFieldUpdater.newUpdater(EventLoopImplBase.class, "_isCompleted");

    private final void loop$atomicfu(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, Function1<Object, Unit> function1, Object obj) {
        while (true) {
            function1.invoke(atomicReferenceFieldUpdater.get(obj));
        }
    }

    @Override // kotlinx.coroutines.Delay
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated without replacement as an internal method never intended for public use")
    public Object delay(long time, Continuation<? super Unit> continuation) {
        return Delay.DefaultImpls.delay(this, time, continuation);
    }

    public DisposableHandle invokeOnTimeout(long timeMillis, Runnable block, CoroutineContext context) {
        return Delay.DefaultImpls.invokeOnTimeout(this, timeMillis, block, context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isCompleted() {
        return _isCompleted$FU.get(this) != 0;
    }

    private final void setCompleted(boolean value) {
        _isCompleted$FU.set(this, value ? 1 : 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.EventLoop
    public boolean isEmpty() {
        Symbol symbol;
        if (isUnconfinedQueueEmpty()) {
            DelayedTaskQueue delayed = (DelayedTaskQueue) _delayed$FU.get(this);
            if (delayed == null || delayed.isEmpty()) {
                Object queue = _queue$FU.get(this);
                if (queue == null) {
                    return true;
                }
                if (queue instanceof LockFreeTaskQueueCore) {
                    return ((LockFreeTaskQueueCore) queue).isEmpty();
                }
                symbol = EventLoop_commonKt.CLOSED_EMPTY;
                return queue == symbol;
            }
            return false;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.EventLoop
    public long getNextTime() {
        DelayedTask nextDelayedTask;
        Symbol symbol;
        if (super.getNextTime() == 0) {
            return 0L;
        }
        Object queue = _queue$FU.get(this);
        if (queue != null) {
            if (!(queue instanceof LockFreeTaskQueueCore)) {
                symbol = EventLoop_commonKt.CLOSED_EMPTY;
                return queue == symbol ? Long.MAX_VALUE : 0L;
            } else if (!((LockFreeTaskQueueCore) queue).isEmpty()) {
                return 0L;
            }
        }
        DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) _delayed$FU.get(this);
        if (delayedTaskQueue == null || (nextDelayedTask = delayedTaskQueue.peek()) == null) {
            return Long.MAX_VALUE;
        }
        long j = nextDelayedTask.nanoTime;
        AbstractTimeSource timeSource = AbstractTimeSourceKt.getTimeSource();
        return RangesKt.coerceAtLeast(j - (timeSource != null ? timeSource.nanoTime() : System.nanoTime()), 0L);
    }

    @Override // kotlinx.coroutines.EventLoop
    public void shutdown() {
        ThreadLocalEventLoop.INSTANCE.resetEventLoop$kotlinx_coroutines_core();
        setCompleted(true);
        closeQueue();
        do {
        } while (processNextEvent() <= 0);
        rescheduleAllDelayed();
    }

    @Override // kotlinx.coroutines.Delay
    /* renamed from: scheduleResumeAfterDelay */
    public void mo1833scheduleResumeAfterDelay(long timeMillis, CancellableContinuation<? super Unit> cancellableContinuation) {
        long timeNanos = EventLoop_commonKt.delayToNanos(timeMillis);
        if (timeNanos < DurationKt.MAX_MILLIS) {
            AbstractTimeSource timeSource = AbstractTimeSourceKt.getTimeSource();
            long now = timeSource != null ? timeSource.nanoTime() : System.nanoTime();
            DelayedResumeTask task = new DelayedResumeTask(now + timeNanos, cancellableContinuation);
            schedule(now, task);
            CancellableContinuationKt.disposeOnCancellation(cancellableContinuation, task);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final DisposableHandle scheduleInvokeOnTimeout(long timeMillis, Runnable block) {
        long timeNanos = EventLoop_commonKt.delayToNanos(timeMillis);
        if (timeNanos < DurationKt.MAX_MILLIS) {
            AbstractTimeSource timeSource = AbstractTimeSourceKt.getTimeSource();
            long now = timeSource != null ? timeSource.nanoTime() : System.nanoTime();
            DelayedRunnableTask task = new DelayedRunnableTask(now + timeNanos, block);
            schedule(now, task);
            return task;
        }
        return NonDisposableHandle.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0075  */
    @Override // kotlinx.coroutines.EventLoop
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long processNextEvent() {
        /*
            r16 = this;
            r1 = r16
            boolean r0 = r16.processUnconfinedEvent()
            r2 = 0
            if (r0 == 0) goto Lb
            return r2
        Lb:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = kotlinx.coroutines.EventLoopImplBase._delayed$FU
            java.lang.Object r0 = r0.get(r1)
            r4 = r0
            kotlinx.coroutines.EventLoopImplBase$DelayedTaskQueue r4 = (kotlinx.coroutines.EventLoopImplBase.DelayedTaskQueue) r4
            if (r4 == 0) goto L68
            boolean r0 = r4.isEmpty()
            if (r0 != 0) goto L68
            kotlinx.coroutines.AbstractTimeSource r0 = kotlinx.coroutines.AbstractTimeSourceKt.getTimeSource()
            if (r0 == 0) goto L27
            long r5 = r0.nanoTime()
            goto L2b
        L27:
            long r5 = java.lang.System.nanoTime()
        L2b:
            r7 = r4
            kotlinx.coroutines.internal.ThreadSafeHeap r7 = (kotlinx.coroutines.internal.ThreadSafeHeap) r7
            r8 = 0
            r9 = 0
            r10 = 0
            monitor-enter(r7)
            r0 = 0
            kotlinx.coroutines.internal.ThreadSafeHeapNode r11 = r7.firstImpl()     // Catch: java.lang.Throwable -> L65
            if (r11 != 0) goto L3e
            monitor-exit(r7)
            r12 = 0
            goto L5f
        L3e:
            r13 = r11
            kotlinx.coroutines.EventLoopImplBase$DelayedTask r13 = (kotlinx.coroutines.EventLoopImplBase.DelayedTask) r13     // Catch: java.lang.Throwable -> L65
            r14 = 0
            boolean r15 = r13.timeToExecute(r5)     // Catch: java.lang.Throwable -> L65
            r12 = 0
            if (r15 == 0) goto L51
            r15 = r13
            java.lang.Runnable r15 = (java.lang.Runnable) r15     // Catch: java.lang.Throwable -> L65
            boolean r15 = r1.enqueueImpl(r15)     // Catch: java.lang.Throwable -> L65
            goto L52
        L51:
            r15 = r12
        L52:
            if (r15 == 0) goto L5a
            kotlinx.coroutines.internal.ThreadSafeHeapNode r12 = r7.removeAtImpl(r12)     // Catch: java.lang.Throwable -> L65
            goto L5b
        L5a:
            r12 = 0
        L5b:
            monitor-exit(r7)
        L5f:
            kotlinx.coroutines.EventLoopImplBase$DelayedTask r12 = (kotlinx.coroutines.EventLoopImplBase.DelayedTask) r12
            if (r12 != 0) goto L64
            goto L68
        L64:
            goto L2b
        L65:
            r0 = move-exception
            monitor-exit(r7)
            throw r0
        L68:
            java.lang.Runnable r0 = r16.dequeue()
            if (r0 == 0) goto L75
            r5 = 0
            r6 = 0
            r0.run()
            return r2
        L75:
            long r2 = r16.getNextTime()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.EventLoopImplBase.processNextEvent():long");
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    /* renamed from: dispatch */
    public final void mo1832dispatch(CoroutineContext context, Runnable block) {
        enqueue(block);
    }

    public void enqueue(Runnable task) {
        if (enqueueImpl(task)) {
            unpark();
        } else {
            DefaultExecutor.INSTANCE.enqueue(task);
        }
    }

    private final boolean enqueueImpl(Runnable task) {
        Symbol symbol;
        AtomicReferenceFieldUpdater atomicfu$handler$iv = _queue$FU;
        while (true) {
            Object queue = atomicfu$handler$iv.get(this);
            if (isCompleted()) {
                return false;
            }
            if (queue == null) {
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_queue$FU, this, null, task)) {
                    return true;
                }
            } else if (!(queue instanceof LockFreeTaskQueueCore)) {
                symbol = EventLoop_commonKt.CLOSED_EMPTY;
                if (queue == symbol) {
                    return false;
                }
                LockFreeTaskQueueCore newQueue = new LockFreeTaskQueueCore(8, true);
                Intrinsics.checkNotNull(queue, "null cannot be cast to non-null type java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }");
                newQueue.addLast((Runnable) queue);
                newQueue.addLast(task);
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_queue$FU, this, queue, newQueue)) {
                    return true;
                }
            } else {
                Intrinsics.checkNotNull(queue, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeTaskQueueCore<java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }>{ kotlinx.coroutines.EventLoop_commonKt.Queue<java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }> }");
                switch (((LockFreeTaskQueueCore) queue).addLast(task)) {
                    case 0:
                        return true;
                    case 1:
                        AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_queue$FU, this, queue, ((LockFreeTaskQueueCore) queue).next());
                        continue;
                    case 2:
                        return false;
                }
            }
        }
    }

    private final Runnable dequeue() {
        Symbol symbol;
        AtomicReferenceFieldUpdater atomicfu$handler$iv = _queue$FU;
        while (true) {
            Object queue = atomicfu$handler$iv.get(this);
            if (queue == null) {
                return null;
            }
            if (!(queue instanceof LockFreeTaskQueueCore)) {
                symbol = EventLoop_commonKt.CLOSED_EMPTY;
                if (queue == symbol) {
                    return null;
                }
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_queue$FU, this, queue, null)) {
                    Intrinsics.checkNotNull(queue, "null cannot be cast to non-null type java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }");
                    return (Runnable) queue;
                }
            } else {
                Intrinsics.checkNotNull(queue, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeTaskQueueCore<java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }>{ kotlinx.coroutines.EventLoop_commonKt.Queue<java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }> }");
                Object result = ((LockFreeTaskQueueCore) queue).removeFirstOrNull();
                if (result != LockFreeTaskQueueCore.REMOVE_FROZEN) {
                    return (Runnable) result;
                }
                AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_queue$FU, this, queue, ((LockFreeTaskQueueCore) queue).next());
            }
        }
    }

    private final void closeQueue() {
        Symbol symbol;
        Symbol symbol2;
        if (DebugKt.getASSERTIONS_ENABLED() && !isCompleted()) {
            throw new AssertionError();
        }
        AtomicReferenceFieldUpdater atomicfu$handler$iv = _queue$FU;
        while (true) {
            Object queue = atomicfu$handler$iv.get(this);
            if (queue == null) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _queue$FU;
                symbol = EventLoop_commonKt.CLOSED_EMPTY;
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, null, symbol)) {
                    return;
                }
            } else if (!(queue instanceof LockFreeTaskQueueCore)) {
                symbol2 = EventLoop_commonKt.CLOSED_EMPTY;
                if (queue == symbol2) {
                    return;
                }
                LockFreeTaskQueueCore newQueue = new LockFreeTaskQueueCore(8, true);
                Intrinsics.checkNotNull(queue, "null cannot be cast to non-null type java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }");
                newQueue.addLast((Runnable) queue);
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_queue$FU, this, queue, newQueue)) {
                    return;
                }
            } else {
                ((LockFreeTaskQueueCore) queue).close();
                return;
            }
        }
    }

    public final void schedule(long now, DelayedTask delayedTask) {
        switch (scheduleImpl(now, delayedTask)) {
            case 0:
                if (shouldUnpark(delayedTask)) {
                    unpark();
                    return;
                }
                return;
            case 1:
                reschedule(now, delayedTask);
                return;
            case 2:
                return;
            default:
                throw new IllegalStateException("unexpected result".toString());
        }
    }

    private final boolean shouldUnpark(DelayedTask task) {
        DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) _delayed$FU.get(this);
        return (delayedTaskQueue != null ? delayedTaskQueue.peek() : null) == task;
    }

    private final int scheduleImpl(long now, DelayedTask delayedTask) {
        if (isCompleted()) {
            return 1;
        }
        DelayedTaskQueue delayedQueue = (DelayedTaskQueue) _delayed$FU.get(this);
        if (delayedQueue == null) {
            EventLoopImplBase $this$scheduleImpl_u24lambda_u248 = this;
            AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_delayed$FU, $this$scheduleImpl_u24lambda_u248, null, new DelayedTaskQueue(now));
            Object obj = _delayed$FU.get($this$scheduleImpl_u24lambda_u248);
            Intrinsics.checkNotNull(obj);
            delayedQueue = (DelayedTaskQueue) obj;
        }
        return delayedTask.scheduleTask(now, delayedQueue, this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void resetAll() {
        _queue$FU.set(this, null);
        _delayed$FU.set(this, null);
    }

    private final void rescheduleAllDelayed() {
        DelayedTask delayedTask;
        AbstractTimeSource timeSource = AbstractTimeSourceKt.getTimeSource();
        long now = timeSource != null ? timeSource.nanoTime() : System.nanoTime();
        while (true) {
            DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) _delayed$FU.get(this);
            if (delayedTaskQueue != null && (delayedTask = delayedTaskQueue.removeFirstOrNull()) != null) {
                reschedule(now, delayedTask);
            } else {
                return;
            }
        }
    }

    /* compiled from: EventLoop.common.kt */
    @Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\b \u0018\u00002\u00060\u0001j\u0002`\u00022\b\u0012\u0004\u0012\u00020\u00000\u00032\u00020\u00042\u00020\u00052\u00060\u0006j\u0002`\u0007B\r\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0011\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u0000H\u0096\u0002J\u0006\u0010\u001b\u001a\u00020\u001cJ\u001e\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"J\u000e\u0010#\u001a\u00020$2\u0006\u0010\u001e\u001a\u00020\tJ\b\u0010%\u001a\u00020&H\u0016R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R0\u0010\u000e\u001a\b\u0012\u0002\b\u0003\u0018\u00010\r2\f\u0010\f\u001a\b\u0012\u0002\b\u0003\u0018\u00010\r8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0012\u0010\b\u001a\u00020\t8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "", "Lkotlinx/coroutines/DisposableHandle;", "Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "", "Lkotlinx/coroutines/internal/SynchronizedObject;", "nanoTime", "", "(J)V", "_heap", "value", "Lkotlinx/coroutines/internal/ThreadSafeHeap;", "heap", "getHeap", "()Lkotlinx/coroutines/internal/ThreadSafeHeap;", "setHeap", "(Lkotlinx/coroutines/internal/ThreadSafeHeap;)V", "index", "", "getIndex", "()I", "setIndex", "(I)V", "compareTo", "other", "dispose", "", "scheduleTask", "now", "delayed", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTaskQueue;", "eventLoop", "Lkotlinx/coroutines/EventLoopImplBase;", "timeToExecute", "", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    public static abstract class DelayedTask implements Runnable, Comparable<DelayedTask>, DisposableHandle, ThreadSafeHeapNode {
        private volatile Object _heap;
        private int index = -1;
        public long nanoTime;

        public DelayedTask(long nanoTime) {
            this.nanoTime = nanoTime;
        }

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public ThreadSafeHeap<?> getHeap() {
            Object obj = this._heap;
            if (obj instanceof ThreadSafeHeap) {
                return (ThreadSafeHeap) obj;
            }
            return null;
        }

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public void setHeap(ThreadSafeHeap<?> threadSafeHeap) {
            Symbol symbol;
            Object obj = this._heap;
            symbol = EventLoop_commonKt.DISPOSED_TASK;
            if (!(obj != symbol)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            this._heap = threadSafeHeap;
        }

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public int getIndex() {
            return this.index;
        }

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public void setIndex(int i) {
            this.index = i;
        }

        @Override // java.lang.Comparable
        public int compareTo(DelayedTask other) {
            long dTime = this.nanoTime - other.nanoTime;
            if (dTime > 0) {
                return 1;
            }
            return dTime < 0 ? -1 : 0;
        }

        public final boolean timeToExecute(long now) {
            return now - this.nanoTime >= 0;
        }

        public final int scheduleTask(long now, DelayedTaskQueue delayed, EventLoopImplBase eventLoop) {
            Symbol symbol;
            synchronized (this) {
                try {
                    Object obj = this._heap;
                    symbol = EventLoop_commonKt.DISPOSED_TASK;
                    if (obj == symbol) {
                        return 2;
                    }
                    DelayedTaskQueue this_$iv = delayed;
                    synchronized (this_$iv) {
                        try {
                            try {
                                DelayedTask firstTask = this_$iv.firstImpl();
                                if (eventLoop.isCompleted()) {
                                    try {
                                        return 1;
                                    } catch (Throwable th) {
                                        th = th;
                                        throw th;
                                    }
                                }
                                if (firstTask == null) {
                                    try {
                                        delayed.timeNow = now;
                                    } catch (Throwable th2) {
                                        th = th2;
                                        throw th;
                                    }
                                } else {
                                    long firstTime = firstTask.nanoTime;
                                    long minTime = firstTime - now >= 0 ? now : firstTime;
                                    if (minTime - delayed.timeNow > 0) {
                                        try {
                                            delayed.timeNow = minTime;
                                        } catch (Throwable th3) {
                                            th = th3;
                                            throw th;
                                        }
                                    }
                                }
                                if (this.nanoTime - delayed.timeNow < 0) {
                                    this.nanoTime = delayed.timeNow;
                                }
                                this_$iv.addImpl(this);
                                return 0;
                            } catch (Throwable th4) {
                                th = th4;
                            }
                        } catch (Throwable th5) {
                            th = th5;
                        }
                    }
                } catch (Throwable th6) {
                    th = th6;
                }
            }
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public final void dispose() {
            Symbol symbol;
            Symbol symbol2;
            synchronized (this) {
                Object heap = this._heap;
                symbol = EventLoop_commonKt.DISPOSED_TASK;
                if (heap == symbol) {
                    return;
                }
                DelayedTaskQueue delayedTaskQueue = heap instanceof DelayedTaskQueue ? (DelayedTaskQueue) heap : null;
                if (delayedTaskQueue != null) {
                    delayedTaskQueue.remove(this);
                }
                symbol2 = EventLoop_commonKt.DISPOSED_TASK;
                this._heap = symbol2;
                Unit unit = Unit.INSTANCE;
            }
        }

        public String toString() {
            return "Delayed[nanos=" + this.nanoTime + ']';
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: EventLoop.common.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\u0006H\u0016J\b\u0010\t\u001a\u00020\nH\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lkotlinx/coroutines/EventLoopImplBase$DelayedResumeTask;", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "nanoTime", "", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "", "(Lkotlinx/coroutines/EventLoopImplBase;JLkotlinx/coroutines/CancellableContinuation;)V", "run", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    public final class DelayedResumeTask extends DelayedTask {
        private final CancellableContinuation<Unit> cont;

        /* JADX WARN: Multi-variable type inference failed */
        public DelayedResumeTask(long nanoTime, CancellableContinuation<? super Unit> cancellableContinuation) {
            super(nanoTime);
            this.cont = cancellableContinuation;
        }

        @Override // java.lang.Runnable
        public void run() {
            CancellableContinuation $this$run_u24lambda_u240 = this.cont;
            $this$run_u24lambda_u240.resumeUndispatched(EventLoopImplBase.this, Unit.INSTANCE);
        }

        @Override // kotlinx.coroutines.EventLoopImplBase.DelayedTask
        public String toString() {
            return super.toString() + this.cont;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: EventLoop.common.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016R\u0012\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lkotlinx/coroutines/EventLoopImplBase$DelayedRunnableTask;", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "nanoTime", "", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "(JLjava/lang/Runnable;)V", "run", "", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    public static final class DelayedRunnableTask extends DelayedTask {
        private final Runnable block;

        public DelayedRunnableTask(long nanoTime, Runnable block) {
            super(nanoTime);
            this.block = block;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.block.run();
        }

        @Override // kotlinx.coroutines.EventLoopImplBase.DelayedTask
        public String toString() {
            return super.toString() + this.block;
        }
    }

    /* compiled from: EventLoop.common.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005R\u0012\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lkotlinx/coroutines/EventLoopImplBase$DelayedTaskQueue;", "Lkotlinx/coroutines/internal/ThreadSafeHeap;", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "timeNow", "", "(J)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    public static final class DelayedTaskQueue extends ThreadSafeHeap<DelayedTask> {
        public long timeNow;

        public DelayedTaskQueue(long timeNow) {
            this.timeNow = timeNow;
        }
    }
}
