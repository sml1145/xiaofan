package kotlinx.coroutines;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.Volatile;
import kotlin.jvm.internal.Intrinsics;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Await.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0002\r\u000eB\u001b\u0012\u0014\u0010\u0003\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0004¢\u0006\u0002\u0010\u0006J\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\fR\u001e\u0010\u0003\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\t\u0010\b\u001a\u00020\tX\u0082\u0004\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, d2 = {"Lkotlinx/coroutines/AwaitAll;", "T", "", "deferreds", "", "Lkotlinx/coroutines/Deferred;", "([Lkotlinx/coroutines/Deferred;)V", "[Lkotlinx/coroutines/Deferred;", "notCompletedCount", "Lkotlinx/atomicfu/AtomicInt;", "await", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "AwaitAllNode", "DisposeHandlersOnCancel", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class AwaitAll<T> {
    private static final AtomicIntegerFieldUpdater notCompletedCount$FU = AtomicIntegerFieldUpdater.newUpdater(AwaitAll.class, "notCompletedCount");
    private final Deferred<T>[] deferreds;
    @Volatile
    private volatile int notCompletedCount;

    /* JADX WARN: Multi-variable type inference failed */
    public AwaitAll(Deferred<? extends T>[] deferredArr) {
        this.deferreds = deferredArr;
        this.notCompletedCount = this.deferreds.length;
    }

    public final Object await(Continuation<? super List<? extends T>> continuation) {
        CancellableContinuationImpl cancellable$iv = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellable$iv.initCancellability();
        CancellableContinuationImpl cont = cancellable$iv;
        int length = this.deferreds.length;
        AwaitAllNode[] nodes = new AwaitAllNode[length];
        for (int i = 0; i < length; i++) {
            Deferred deferred = this.deferreds[i];
            deferred.start();
            AwaitAllNode $this$await_u24lambda_u242_u24lambda_u240 = new AwaitAllNode(cont);
            AwaitAllNode $this$asHandler$iv = $this$await_u24lambda_u242_u24lambda_u240;
            $this$await_u24lambda_u242_u24lambda_u240.setHandle(deferred.invokeOnCompletion($this$asHandler$iv));
            Unit unit = Unit.INSTANCE;
            nodes[i] = $this$await_u24lambda_u242_u24lambda_u240;
        }
        DisposeHandlersOnCancel disposer = new DisposeHandlersOnCancel(nodes);
        for (AwaitAllNode awaitAllNode : nodes) {
            awaitAllNode.setDisposer(disposer);
        }
        if (cont.isCompleted()) {
            disposer.disposeAll();
        } else {
            DisposeHandlersOnCancel $this$asHandler$iv2 = disposer;
            cont.invokeOnCancellation($this$asHandler$iv2);
        }
        Object result = cancellable$iv.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: Await.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u001d\u0012\u0016\u0010\u0002\u001a\u0012\u0012\u000e\u0012\f0\u0004R\b\u0012\u0004\u0012\u00028\u00000\u00050\u0003¢\u0006\u0002\u0010\u0006J\u0006\u0010\b\u001a\u00020\tJ\u0013\u0010\n\u001a\u00020\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0096\u0002J\b\u0010\r\u001a\u00020\u000eH\u0016R \u0010\u0002\u001a\u0012\u0012\u000e\u0012\f0\u0004R\b\u0012\u0004\u0012\u00028\u00000\u00050\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\u000f"}, d2 = {"Lkotlinx/coroutines/AwaitAll$DisposeHandlersOnCancel;", "Lkotlinx/coroutines/CancelHandler;", "nodes", "", "Lkotlinx/coroutines/AwaitAll$AwaitAllNode;", "Lkotlinx/coroutines/AwaitAll;", "(Lkotlinx/coroutines/AwaitAll;[Lkotlinx/coroutines/AwaitAll$AwaitAllNode;)V", "[Lkotlinx/coroutines/AwaitAll$AwaitAllNode;", "disposeAll", "", "invoke", "cause", "", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    public final class DisposeHandlersOnCancel extends CancelHandler {
        private final AwaitAll<T>.AwaitAllNode[] nodes;

        public DisposeHandlersOnCancel(AwaitAll<T>.AwaitAllNode[] awaitAllNodeArr) {
            this.nodes = awaitAllNodeArr;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }

        public final void disposeAll() {
            for (AwaitAll<T>.AwaitAllNode awaitAllNode : this.nodes) {
                awaitAllNode.getHandle().dispose();
            }
        }

        @Override // kotlinx.coroutines.CancelHandlerBase
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public void invoke2(Throwable cause) {
            disposeAll();
        }

        public String toString() {
            return "DisposeHandlersOnCancel[" + this.nodes + ']';
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: Await.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0019\u0012\u0012\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0096\u0002R\u001b\u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0018\u00010\bR\b\u0012\u0004\u0012\u00028\u00000\t0\u0007X\u0082\u0004R\u001a\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R<\u0010\u000b\u001a\u000e\u0018\u00010\bR\b\u0012\u0004\u0012\u00028\u00000\t2\u0012\u0010\n\u001a\u000e\u0018\u00010\bR\b\u0012\u0004\u0012\u00028\u00000\t8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\u001a"}, d2 = {"Lkotlinx/coroutines/AwaitAll$AwaitAllNode;", "Lkotlinx/coroutines/JobNode;", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "", "(Lkotlinx/coroutines/AwaitAll;Lkotlinx/coroutines/CancellableContinuation;)V", "_disposer", "Lkotlinx/atomicfu/AtomicRef;", "Lkotlinx/coroutines/AwaitAll$DisposeHandlersOnCancel;", "Lkotlinx/coroutines/AwaitAll;", "value", "disposer", "getDisposer", "()Lkotlinx/coroutines/AwaitAll$DisposeHandlersOnCancel;", "setDisposer", "(Lkotlinx/coroutines/AwaitAll$DisposeHandlersOnCancel;)V", "handle", "Lkotlinx/coroutines/DisposableHandle;", "getHandle", "()Lkotlinx/coroutines/DisposableHandle;", "setHandle", "(Lkotlinx/coroutines/DisposableHandle;)V", "invoke", "", "cause", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    public final class AwaitAllNode extends JobNode {
        private static final AtomicReferenceFieldUpdater _disposer$FU = AtomicReferenceFieldUpdater.newUpdater(AwaitAllNode.class, Object.class, "_disposer");
        @Volatile
        private volatile Object _disposer;
        private final CancellableContinuation<List<? extends T>> continuation;
        public DisposableHandle handle;

        /* JADX WARN: Multi-variable type inference failed */
        public AwaitAllNode(CancellableContinuation<? super List<? extends T>> cancellableContinuation) {
            this.continuation = cancellableContinuation;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }

        public final DisposableHandle getHandle() {
            DisposableHandle disposableHandle = this.handle;
            if (disposableHandle != null) {
                return disposableHandle;
            }
            Intrinsics.throwUninitializedPropertyAccessException("handle");
            return null;
        }

        public final void setHandle(DisposableHandle disposableHandle) {
            this.handle = disposableHandle;
        }

        public final AwaitAll<T>.DisposeHandlersOnCancel getDisposer() {
            return (DisposeHandlersOnCancel) _disposer$FU.get(this);
        }

        public final void setDisposer(AwaitAll<T>.DisposeHandlersOnCancel disposeHandlersOnCancel) {
            _disposer$FU.set(this, disposeHandlersOnCancel);
        }

        @Override // kotlinx.coroutines.CompletionHandlerBase
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public void invoke2(Throwable cause) {
            if (cause != null) {
                Object token = this.continuation.tryResumeWithException(cause);
                if (token != null) {
                    this.continuation.completeResume(token);
                    AwaitAll<T>.DisposeHandlersOnCancel disposer = getDisposer();
                    if (disposer != null) {
                        disposer.disposeAll();
                    }
                }
            } else if (AwaitAll.notCompletedCount$FU.decrementAndGet(AwaitAll.this) == 0) {
                CancellableContinuation<List<? extends T>> cancellableContinuation = this.continuation;
                Deferred[] deferredArr = ((AwaitAll) AwaitAll.this).deferreds;
                Collection destination$iv$iv = new ArrayList(deferredArr.length);
                for (Deferred deferred : deferredArr) {
                    destination$iv$iv.add(deferred.getCompleted());
                }
                Result.Companion companion = Result.Companion;
                cancellableContinuation.resumeWith(Result.m270constructorimpl((List) destination$iv$iv));
            }
        }
    }
}
