package kotlinx.coroutines.scheduling;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.Volatile;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.channels.ChannelSegment$$ExternalSyntheticBackportWithForwarding0;
/* compiled from: WorkQueue.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0012\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0013\u001a\u00020\u00072\b\b\u0002\u0010\u0014\u001a\u00020\u0015J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0013\u001a\u00020\u0007H\u0002J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aJ\b\u0010\u001b\u001a\u0004\u0018\u00010\u0007J\b\u0010\u001c\u001a\u0004\u0018\u00010\u0007J\n\u0010\u001d\u001a\u0004\u0018\u00010\u0007H\u0002J\b\u0010\u001e\u001a\u0004\u0018\u00010\u0007J\u0010\u0010\u001f\u001a\u00020\u00152\u0006\u0010 \u001a\u00020\u001aH\u0002J\u0012\u0010!\u001a\u0004\u0018\u00010\u00072\u0006\u0010\"\u001a\u00020\u0015H\u0002J\u0016\u0010#\u001a\u0004\u0018\u00010\u00072\n\u0010$\u001a\u00060\tj\u0002`%H\u0002J\u001a\u0010&\u001a\u0004\u0018\u00010\u00072\u0006\u0010'\u001a\u00020\t2\u0006\u0010\"\u001a\u00020\u0015H\u0002J\"\u0010(\u001a\u00020)2\n\u0010$\u001a\u00060\tj\u0002`%2\u000e\u0010*\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070+J$\u0010,\u001a\u00020)2\n\u0010$\u001a\u00060\tj\u0002`%2\u000e\u0010*\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070+H\u0002J\u000e\u0010-\u001a\u00020\u0018*\u0004\u0018\u00010\u0007H\u0002R\t\u0010\u0003\u001a\u00020\u0004X\u0082\u0004R\u0016\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\t\u0010\f\u001a\u00020\u0004X\u0082\u0004R\u0011\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000eX\u0082\u0004R\t\u0010\u000f\u001a\u00020\u0004X\u0082\u0004R\u0014\u0010\u0010\u001a\u00020\t8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000b¨\u0006."}, d2 = {"Lkotlinx/coroutines/scheduling/WorkQueue;", "", "()V", "blockingTasksInBuffer", "Lkotlinx/atomicfu/AtomicInt;", "buffer", "Ljava/util/concurrent/atomic/AtomicReferenceArray;", "Lkotlinx/coroutines/scheduling/Task;", "bufferSize", "", "getBufferSize", "()I", "consumerIndex", "lastScheduledTask", "Lkotlinx/atomicfu/AtomicRef;", "producerIndex", "size", "getSize$kotlinx_coroutines_core", "add", "task", "fair", "", "addLast", "offloadAllWorkTo", "", "globalQueue", "Lkotlinx/coroutines/scheduling/GlobalQueue;", "poll", "pollBlocking", "pollBuffer", "pollCpu", "pollTo", "queue", "pollWithExclusiveMode", "onlyBlocking", "stealWithExclusiveMode", "stealingMode", "Lkotlinx/coroutines/scheduling/StealingMode;", "tryExtractFromTheMiddle", "index", "trySteal", "", "stolenTaskRef", "Lkotlin/jvm/internal/Ref$ObjectRef;", "tryStealLastScheduled", "decrementIfBlocking", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class WorkQueue {
    @Volatile
    private volatile int blockingTasksInBuffer;
    private final AtomicReferenceArray<Task> buffer = new AtomicReferenceArray<>(128);
    @Volatile
    private volatile int consumerIndex;
    @Volatile
    private volatile Object lastScheduledTask;
    @Volatile
    private volatile int producerIndex;
    private static final AtomicReferenceFieldUpdater lastScheduledTask$FU = AtomicReferenceFieldUpdater.newUpdater(WorkQueue.class, Object.class, "lastScheduledTask");
    private static final AtomicIntegerFieldUpdater producerIndex$FU = AtomicIntegerFieldUpdater.newUpdater(WorkQueue.class, "producerIndex");
    private static final AtomicIntegerFieldUpdater consumerIndex$FU = AtomicIntegerFieldUpdater.newUpdater(WorkQueue.class, "consumerIndex");
    private static final AtomicIntegerFieldUpdater blockingTasksInBuffer$FU = AtomicIntegerFieldUpdater.newUpdater(WorkQueue.class, "blockingTasksInBuffer");

    private final int getBufferSize() {
        return producerIndex$FU.get(this) - consumerIndex$FU.get(this);
    }

    public final int getSize$kotlinx_coroutines_core() {
        return lastScheduledTask$FU.get(this) != null ? getBufferSize() + 1 : getBufferSize();
    }

    public final Task poll() {
        Task task = (Task) lastScheduledTask$FU.getAndSet(this, null);
        return task == null ? pollBuffer() : task;
    }

    public static /* synthetic */ Task add$default(WorkQueue workQueue, Task task, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return workQueue.add(task, z);
    }

    public final Task add(Task task, boolean fair) {
        if (fair) {
            return addLast(task);
        }
        Task previous = (Task) lastScheduledTask$FU.getAndSet(this, task);
        if (previous == null) {
            return null;
        }
        return addLast(previous);
    }

    private final Task addLast(Task task) {
        if (getBufferSize() == 127) {
            return task;
        }
        if (task.taskContext.getTaskMode() == 1) {
            blockingTasksInBuffer$FU.incrementAndGet(this);
        }
        int nextIndex = producerIndex$FU.get(this) & 127;
        while (this.buffer.get(nextIndex) != null) {
            Thread.yield();
        }
        this.buffer.lazySet(nextIndex, task);
        producerIndex$FU.incrementAndGet(this);
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final long trySteal(int stealingMode, Ref.ObjectRef<Task> objectRef) {
        T pollBuffer = stealingMode == 3 ? pollBuffer() : stealWithExclusiveMode(stealingMode);
        if (pollBuffer != 0) {
            objectRef.element = pollBuffer;
            return -1L;
        }
        return tryStealLastScheduled(stealingMode, objectRef);
    }

    private final Task stealWithExclusiveMode(int stealingMode) {
        int start = consumerIndex$FU.get(this);
        int end = producerIndex$FU.get(this);
        boolean onlyBlocking = stealingMode == 1;
        while (start != end) {
            if (onlyBlocking && blockingTasksInBuffer$FU.get(this) == 0) {
                return null;
            }
            int start2 = start + 1;
            Task tryExtractFromTheMiddle = tryExtractFromTheMiddle(start, onlyBlocking);
            if (tryExtractFromTheMiddle != null) {
                return tryExtractFromTheMiddle;
            }
            start = start2;
        }
        return null;
    }

    public final Task pollBlocking() {
        return pollWithExclusiveMode(true);
    }

    public final Task pollCpu() {
        return pollWithExclusiveMode(false);
    }

    private final Task pollWithExclusiveMode(boolean onlyBlocking) {
        Task lastScheduled;
        do {
            lastScheduled = (Task) lastScheduledTask$FU.get(this);
            if (lastScheduled != null) {
                if ((lastScheduled.taskContext.getTaskMode() == 1) == onlyBlocking) {
                }
            }
            int start = consumerIndex$FU.get(this);
            int end = producerIndex$FU.get(this);
            while (start != end) {
                if (onlyBlocking && blockingTasksInBuffer$FU.get(this) == 0) {
                    return null;
                }
                end--;
                Task task = tryExtractFromTheMiddle(end, onlyBlocking);
                if (task != null) {
                    return task;
                }
            }
            return null;
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(lastScheduledTask$FU, this, lastScheduled, null));
        return lastScheduled;
    }

    private final Task tryExtractFromTheMiddle(int index, boolean onlyBlocking) {
        int arrayIndex = index & 127;
        Task value = this.buffer.get(arrayIndex);
        if (value != null) {
            if ((value.taskContext.getTaskMode() == 1) == onlyBlocking && ChannelSegment$$ExternalSyntheticBackportWithForwarding0.m(this.buffer, arrayIndex, value, null)) {
                if (onlyBlocking) {
                    blockingTasksInBuffer$FU.decrementAndGet(this);
                }
                return value;
            }
        }
        return null;
    }

    public final void offloadAllWorkTo(GlobalQueue globalQueue) {
        Task it = (Task) lastScheduledTask$FU.getAndSet(this, null);
        if (it != null) {
            globalQueue.addLast(it);
        }
        do {
        } while (pollTo(globalQueue));
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [T, kotlinx.coroutines.scheduling.Task, java.lang.Object] */
    private final long tryStealLastScheduled(int stealingMode, Ref.ObjectRef<Task> objectRef) {
        ?? r0;
        do {
            r0 = (Task) lastScheduledTask$FU.get(this);
            if (r0 == 0) {
                return -2L;
            }
            if (((r0.taskContext.getTaskMode() == 1 ? 1 : 2) & stealingMode) == 0) {
                return -2L;
            }
            long time = TasksKt.schedulerTimeSource.nanoTime();
            long staleness = time - r0.submissionTime;
            if (staleness < TasksKt.WORK_STEALING_TIME_RESOLUTION_NS) {
                return TasksKt.WORK_STEALING_TIME_RESOLUTION_NS - staleness;
            }
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(lastScheduledTask$FU, this, r0, null));
        objectRef.element = r0;
        return -1L;
    }

    private final boolean pollTo(GlobalQueue queue) {
        Task task = pollBuffer();
        if (task == null) {
            return false;
        }
        queue.addLast(task);
        return true;
    }

    private final Task pollBuffer() {
        Task value;
        while (true) {
            int tailLocal = consumerIndex$FU.get(this);
            if (tailLocal - producerIndex$FU.get(this) == 0) {
                return null;
            }
            int index = tailLocal & 127;
            if (consumerIndex$FU.compareAndSet(this, tailLocal, tailLocal + 1) && (value = this.buffer.getAndSet(index, null)) != null) {
                decrementIfBlocking(value);
                return value;
            }
        }
    }

    private final void decrementIfBlocking(Task $this$decrementIfBlocking) {
        if ($this$decrementIfBlocking == null) {
            return;
        }
        Task $this$isBlocking$iv = $this$decrementIfBlocking.taskContext.getTaskMode() == 1 ? 1 : null;
        if ($this$isBlocking$iv != null) {
            int value = blockingTasksInBuffer$FU.decrementAndGet(this);
            if (DebugKt.getASSERTIONS_ENABLED()) {
                if (!(value >= 0)) {
                    throw new AssertionError();
                }
            }
        }
    }
}
