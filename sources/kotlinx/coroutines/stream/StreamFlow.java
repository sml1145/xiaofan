package kotlinx.coroutines.stream;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.stream.Stream;
import kotlin.Metadata;
import kotlin.jvm.Volatile;
import kotlinx.coroutines.flow.Flow;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Stream.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J\u001f\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\fR\t\u0010\u0006\u001a\u00020\u0007X\u0082\u0004R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, d2 = {"Lkotlinx/coroutines/stream/StreamFlow;", "T", "Lkotlinx/coroutines/flow/Flow;", "stream", "Ljava/util/stream/Stream;", "(Ljava/util/stream/Stream;)V", "consumed", "Lkotlinx/atomicfu/AtomicBoolean;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class StreamFlow<T> implements Flow<T> {
    private static final AtomicIntegerFieldUpdater consumed$FU = AtomicIntegerFieldUpdater.newUpdater(StreamFlow.class, "consumed");
    @Volatile
    private volatile int consumed = 0;
    private final Stream<T> stream;

    public StreamFlow(Stream<T> stream) {
        this.stream = stream;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x005b A[Catch: all -> 0x0078, TRY_LEAVE, TryCatch #0 {all -> 0x0078, blocks: (B:13:0x003a, B:18:0x0055, B:20:0x005b, B:17:0x004c), top: B:31:0x0023 }] */
    @Override // kotlinx.coroutines.flow.Flow
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object collect(kotlinx.coroutines.flow.FlowCollector<? super T> r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof kotlinx.coroutines.stream.StreamFlow$collect$1
            if (r0 == 0) goto L14
            r0 = r10
            kotlinx.coroutines.stream.StreamFlow$collect$1 r0 = (kotlinx.coroutines.stream.StreamFlow$collect$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L19
        L14:
            kotlinx.coroutines.stream.StreamFlow$collect$1 r0 = new kotlinx.coroutines.stream.StreamFlow$collect$1
            r0.<init>(r8, r10)
        L19:
            r10 = r0
            java.lang.Object r0 = r10.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r10.label
            r3 = 1
            switch(r2) {
                case 0: goto L3e;
                case 1: goto L2e;
                default: goto L26;
            }
        L26:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L2e:
            java.lang.Object r9 = r10.L$2
            java.util.Iterator r9 = (java.util.Iterator) r9
            java.lang.Object r2 = r10.L$1
            kotlinx.coroutines.flow.FlowCollector r2 = (kotlinx.coroutines.flow.FlowCollector) r2
            java.lang.Object r4 = r10.L$0
            kotlinx.coroutines.stream.StreamFlow r4 = (kotlinx.coroutines.stream.StreamFlow) r4
            kotlin.ResultKt.throwOnFailure(r0)     // Catch: java.lang.Throwable -> L78
            goto L6e
        L3e:
            kotlin.ResultKt.throwOnFailure(r0)
            r4 = r8
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r2 = kotlinx.coroutines.stream.StreamFlow.consumed$FU
            r5 = 0
            boolean r2 = r2.compareAndSet(r4, r5, r3)
            if (r2 == 0) goto L7f
        L4c:
            java.util.stream.Stream<T> r2 = r4.stream     // Catch: java.lang.Throwable -> L78
            java.util.Iterator r2 = r2.iterator()     // Catch: java.lang.Throwable -> L78
            r7 = r2
            r2 = r9
            r9 = r7
        L55:
            boolean r5 = r9.hasNext()     // Catch: java.lang.Throwable -> L78
            if (r5 == 0) goto L6f
            java.lang.Object r5 = r9.next()     // Catch: java.lang.Throwable -> L78
            r10.L$0 = r4     // Catch: java.lang.Throwable -> L78
            r10.L$1 = r2     // Catch: java.lang.Throwable -> L78
            r10.L$2 = r9     // Catch: java.lang.Throwable -> L78
            r10.label = r3     // Catch: java.lang.Throwable -> L78
            java.lang.Object r6 = r2.emit(r5, r10)     // Catch: java.lang.Throwable -> L78
            if (r6 != r1) goto L6e
            return r1
        L6e:
            goto L55
        L6f:
            java.util.stream.Stream<T> r9 = r4.stream
            r9.close()
            r9 = r4
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        L78:
            r9 = move-exception
            java.util.stream.Stream<T> r1 = r4.stream
            r1.close()
            throw r9
        L7f:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r1 = "Stream.consumeAsFlow can be collected only once"
            java.lang.String r1 = r1.toString()
            r9.<init>(r1)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.stream.StreamFlow.collect(kotlinx.coroutines.flow.FlowCollector, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
