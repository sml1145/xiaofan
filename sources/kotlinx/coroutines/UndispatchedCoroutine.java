package kotlinx.coroutines;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.internal.ThreadContextKt;
/* compiled from: CoroutineContext.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0002\u0010\u0007J\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\rH\u0014J\u0006\u0010\u0011\u001a\u00020\tJ\u0018\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\u0013\u001a\u0004\u0018\u00010\rR\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\n\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\r0\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lkotlinx/coroutines/UndispatchedCoroutine;", "T", "Lkotlinx/coroutines/internal/ScopeCoroutine;", "context", "Lkotlin/coroutines/CoroutineContext;", "uCont", "Lkotlin/coroutines/Continuation;", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/Continuation;)V", "threadLocalIsSet", "", "threadStateToRecover", "Ljava/lang/ThreadLocal;", "Lkotlin/Pair;", "", "afterResume", "", "state", "clearThreadContext", "saveThreadContext", "oldValue", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class UndispatchedCoroutine<T> extends ScopeCoroutine<T> {
    private volatile boolean threadLocalIsSet;
    private final ThreadLocal<Pair<CoroutineContext, Object>> threadStateToRecover;

    public UndispatchedCoroutine(CoroutineContext context, Continuation<? super T> continuation) {
        super(context.get(UndispatchedMarker.INSTANCE) == null ? context.plus(UndispatchedMarker.INSTANCE) : context, continuation);
        this.threadStateToRecover = new ThreadLocal<>();
        if (continuation.getContext().get(ContinuationInterceptor.Key) instanceof CoroutineDispatcher) {
            return;
        }
        Object values = ThreadContextKt.updateThreadContext(context, null);
        ThreadContextKt.restoreThreadContext(context, values);
        saveThreadContext(context, values);
    }

    public final void saveThreadContext(CoroutineContext context, Object oldValue) {
        this.threadLocalIsSet = true;
        this.threadStateToRecover.set(TuplesKt.to(context, oldValue));
    }

    public final boolean clearThreadContext() {
        boolean z = this.threadLocalIsSet && this.threadStateToRecover.get() == null;
        this.threadStateToRecover.remove();
        return !z;
    }

    @Override // kotlinx.coroutines.internal.ScopeCoroutine, kotlinx.coroutines.AbstractCoroutine
    protected void afterResume(Object state) {
        UndispatchedCoroutine undispatchedCompletion$iv;
        if (this.threadLocalIsSet) {
            Pair<CoroutineContext, Object> pair = this.threadStateToRecover.get();
            if (pair != null) {
                CoroutineContext ctx = pair.component1();
                Object value = pair.component2();
                ThreadContextKt.restoreThreadContext(ctx, value);
            }
            this.threadStateToRecover.remove();
        }
        Object result = CompletionStateKt.recoverResult(state, this.uCont);
        Continuation continuation$iv = this.uCont;
        CoroutineContext context$iv = continuation$iv.getContext();
        Object oldValue$iv = ThreadContextKt.updateThreadContext(context$iv, null);
        if (oldValue$iv != ThreadContextKt.NO_THREAD_ELEMENTS) {
            undispatchedCompletion$iv = CoroutineContextKt.updateUndispatchedCompletion(continuation$iv, context$iv, oldValue$iv);
        } else {
            undispatchedCompletion$iv = null;
        }
        try {
            this.uCont.resumeWith(result);
            Unit unit = Unit.INSTANCE;
        } finally {
            if (undispatchedCompletion$iv == null || undispatchedCompletion$iv.clearThreadContext()) {
                ThreadContextKt.restoreThreadContext(context$iv, oldValue$iv);
            }
        }
    }
}
