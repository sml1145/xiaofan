package kotlinx.coroutines.flow;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ChannelResult;
/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: Delay.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "T", "value", "Lkotlinx/coroutines/channels/ChannelResult;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__DelayKt$timeoutInternal$1$1$1", f = "Delay.kt", i = {0}, l = {404}, m = "invokeSuspend", n = {"$this$onSuccess_u2dWpGqRn0$iv"}, s = {"L$0"})
/* loaded from: classes.dex */
final class FlowKt__DelayKt$timeoutInternal$1$1$1<T> extends SuspendLambda implements Function2<ChannelResult<? extends T>, Continuation<? super Boolean>, Object> {
    final /* synthetic */ FlowCollector<T> $downStream;
    /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public FlowKt__DelayKt$timeoutInternal$1$1$1(FlowCollector<? super T> flowCollector, Continuation<? super FlowKt__DelayKt$timeoutInternal$1$1$1> continuation) {
        super(2, continuation);
        this.$downStream = flowCollector;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        FlowKt__DelayKt$timeoutInternal$1$1$1 flowKt__DelayKt$timeoutInternal$1$1$1 = new FlowKt__DelayKt$timeoutInternal$1$1$1(this.$downStream, continuation);
        flowKt__DelayKt$timeoutInternal$1$1$1.L$0 = obj;
        return flowKt__DelayKt$timeoutInternal$1$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Continuation<? super Boolean> continuation) {
        return m1817invokeWpGqRn0(((ChannelResult) obj).m1800unboximpl(), continuation);
    }

    /* renamed from: invoke-WpGqRn0  reason: not valid java name */
    public final Object m1817invokeWpGqRn0(Object obj, Continuation<? super Boolean> continuation) {
        return ((FlowKt__DelayKt$timeoutInternal$1$1$1) create(ChannelResult.m1788boximpl(obj), continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object $this$onSuccess_u2dWpGqRn0$iv;
        FlowKt__DelayKt$timeoutInternal$1$1$1<T> flowKt__DelayKt$timeoutInternal$1$1$1;
        Object $this$onSuccess_u2dWpGqRn0$iv2;
        boolean z;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                $this$onSuccess_u2dWpGqRn0$iv = ((ChannelResult) this.L$0).m1800unboximpl();
                FlowCollector<T> flowCollector = this.$downStream;
                if (!($this$onSuccess_u2dWpGqRn0$iv instanceof ChannelResult.Failed)) {
                    this.L$0 = $this$onSuccess_u2dWpGqRn0$iv;
                    this.label = 1;
                    if (flowCollector.emit($this$onSuccess_u2dWpGqRn0$iv, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    flowKt__DelayKt$timeoutInternal$1$1$1 = this;
                    $this$onSuccess_u2dWpGqRn0$iv2 = $this$onSuccess_u2dWpGqRn0$iv;
                    z = false;
                    $this$onSuccess_u2dWpGqRn0$iv = $this$onSuccess_u2dWpGqRn0$iv2;
                    break;
                }
                break;
            case 1:
                flowKt__DelayKt$timeoutInternal$1$1$1 = this;
                z = false;
                $this$onSuccess_u2dWpGqRn0$iv2 = flowKt__DelayKt$timeoutInternal$1$1$1.L$0;
                ResultKt.throwOnFailure($result);
                $this$onSuccess_u2dWpGqRn0$iv = $this$onSuccess_u2dWpGqRn0$iv2;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Object $this$onClosed_u2dWpGqRn0$iv = $this$onSuccess_u2dWpGqRn0$iv;
        if ($this$onClosed_u2dWpGqRn0$iv instanceof ChannelResult.Closed) {
            ChannelResult.m1792exceptionOrNullimpl($this$onClosed_u2dWpGqRn0$iv);
            return Boxing.boxBoolean(false);
        }
        return Boxing.boxBoolean(true);
    }
}
