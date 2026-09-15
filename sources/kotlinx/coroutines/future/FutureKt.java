package kotlinx.coroutines.future;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.ExceptionsKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
/* compiled from: Future.kt */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00040\u0001*\u00020\u0005\u001a\u001c\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0007\u001a!\u0010\b\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0007H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\t\u001a[\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2'\u0010\u0010\u001a#\b\u0001\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0011¢\u0006\u0002\b\u0014ø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001a\u0018\u0010\u0016\u001a\u00020\u0004*\u00020\u00052\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u0001H\u0002\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, d2 = {"asCompletableFuture", "Ljava/util/concurrent/CompletableFuture;", "T", "Lkotlinx/coroutines/Deferred;", "", "Lkotlinx/coroutines/Job;", "asDeferred", "Ljava/util/concurrent/CompletionStage;", "await", "(Ljava/util/concurrent/CompletionStage;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "future", "Lkotlinx/coroutines/CoroutineScope;", "context", "Lkotlin/coroutines/CoroutineContext;", "start", "Lkotlinx/coroutines/CoroutineStart;", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;)Ljava/util/concurrent/CompletableFuture;", "setupCancellation", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class FutureKt {
    public static /* synthetic */ CompletableFuture future$default(CoroutineScope coroutineScope, CoroutineContext coroutineContext, CoroutineStart coroutineStart, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        if ((i & 2) != 0) {
            coroutineStart = CoroutineStart.DEFAULT;
        }
        return future(coroutineScope, coroutineContext, coroutineStart, function2);
    }

    public static final <T> CompletableFuture<T> future(CoroutineScope $this$future, CoroutineContext context, CoroutineStart start, Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> function2) {
        if (!(!start.isLazy())) {
            throw new IllegalArgumentException((start + " start is not supported").toString());
        }
        CoroutineContext newContext = CoroutineContextKt.newCoroutineContext($this$future, context);
        CompletableFuture future = new CompletableFuture();
        CompletableFutureCoroutine coroutine = new CompletableFutureCoroutine(newContext, future);
        future.handle((BiFunction) coroutine);
        coroutine.start(start, coroutine, function2);
        return future;
    }

    public static final <T> CompletableFuture<T> asCompletableFuture(final Deferred<? extends T> deferred) {
        final CompletableFuture future = new CompletableFuture();
        setupCancellation(deferred, future);
        deferred.invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: kotlinx.coroutines.future.FutureKt$asCompletableFuture$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable it) {
                try {
                    future.complete(deferred.getCompleted());
                } catch (Throwable t) {
                    future.completeExceptionally(t);
                }
            }
        });
        return future;
    }

    public static final CompletableFuture<Unit> asCompletableFuture(Job $this$asCompletableFuture) {
        final CompletableFuture future = new CompletableFuture();
        setupCancellation($this$asCompletableFuture, future);
        $this$asCompletableFuture.invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: kotlinx.coroutines.future.FutureKt$asCompletableFuture$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable cause) {
                if (cause != null) {
                    future.completeExceptionally(cause);
                } else {
                    future.complete(Unit.INSTANCE);
                }
            }
        });
        return future;
    }

    private static final void setupCancellation(final Job $this$setupCancellation, CompletableFuture<?> completableFuture) {
        completableFuture.handle(new BiFunction() { // from class: kotlinx.coroutines.future.FutureKt$$ExternalSyntheticLambda1
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                Unit unit;
                unit = FutureKt.setupCancellation$lambda$2(Job.this, obj, (Throwable) obj2);
                return unit;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit setupCancellation$lambda$2(Job $this_setupCancellation, Object obj, Throwable exception) {
        if (exception != null) {
            r4 = exception instanceof CancellationException ? (CancellationException) exception : null;
            if (r4 == null) {
                r4 = ExceptionsKt.CancellationException("CompletableFuture was completed exceptionally", exception);
            }
        }
        $this_setupCancellation.cancel(r4);
        return Unit.INSTANCE;
    }

    public static final <T> Deferred<T> asDeferred(CompletionStage<T> completionStage) {
        Throwable original;
        CompletableFuture future = completionStage.toCompletableFuture();
        if (future.isDone()) {
            try {
                return CompletableDeferredKt.CompletableDeferred(future.get());
            } catch (Throwable e) {
                ExecutionException executionException = e instanceof ExecutionException ? (ExecutionException) e : null;
                if (executionException == null || (original = executionException.getCause()) == null) {
                    original = e;
                }
                CompletableDeferred it = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
                it.completeExceptionally(original);
                return it;
            }
        }
        final CompletableDeferred result = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        final Function2<T, Throwable, Object> function2 = new Function2<T, Throwable, Object>() { // from class: kotlinx.coroutines.future.FutureKt$asDeferred$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(Object p1, Throwable th) {
                return invoke2((FutureKt$asDeferred$2<T>) p1, th);
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final Object invoke2(T t, Throwable exception) {
                Throwable th;
                boolean completeExceptionally;
                try {
                    if (exception == null) {
                        completeExceptionally = result.complete(t);
                    } else {
                        CompletableDeferred<T> completableDeferred = result;
                        CompletionException completionException = exception instanceof CompletionException ? (CompletionException) exception : null;
                        if (completionException == null || (th = completionException.getCause()) == null) {
                            th = exception;
                        }
                        completeExceptionally = completableDeferred.completeExceptionally(th);
                    }
                    return Boolean.valueOf(completeExceptionally);
                } catch (Throwable e2) {
                    CoroutineExceptionHandlerKt.handleCoroutineException(EmptyCoroutineContext.INSTANCE, e2);
                    return Unit.INSTANCE;
                }
            }
        };
        completionStage.handle(new BiFunction() { // from class: kotlinx.coroutines.future.FutureKt$$ExternalSyntheticLambda0
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                Object invoke;
                invoke = Function2.this.invoke(obj, (Throwable) obj2);
                return invoke;
            }
        });
        JobKt.cancelFutureOnCompletion(result, future);
        return result;
    }

    public static final <T> Object await(CompletionStage<T> completionStage, Continuation<? super T> continuation) {
        final CompletableFuture future = completionStage.toCompletableFuture();
        if (future.isDone()) {
            try {
                return future.get();
            } catch (ExecutionException e) {
                Throwable cause = e.getCause();
                if (cause == null) {
                    throw e;
                }
                throw cause;
            }
        }
        CancellableContinuationImpl cancellable$iv = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellable$iv.initCancellability();
        CancellableContinuationImpl cont = cancellable$iv;
        final ContinuationHandler consumer = new ContinuationHandler(cont);
        completionStage.handle(consumer);
        cont.invokeOnCancellation(new Function1<Throwable, Unit>() { // from class: kotlinx.coroutines.future.FutureKt$await$2$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable it) {
                future.cancel(false);
                consumer.cont = null;
            }
        });
        Object result = cancellable$iv.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
