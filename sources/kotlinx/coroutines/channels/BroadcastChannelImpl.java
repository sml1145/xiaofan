package kotlinx.coroutines.channels;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.selects.SelectInstance;
/* compiled from: BroadcastChannel.kt */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\n\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u000245B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0017\u0010\u001f\u001a\u00020\n2\b\u0010 \u001a\u0004\u0018\u00010!H\u0010¢\u0006\u0002\b\"J\u0012\u0010#\u001a\u00020\n2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\u000e\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00000%H\u0016J\u001e\u0010&\u001a\u00020'2\n\u0010(\u001a\u0006\u0012\u0002\b\u00030\u00132\b\u0010)\u001a\u0004\u0018\u00010\rH\u0014J\u0016\u0010*\u001a\u00020'2\f\u0010+\u001a\b\u0012\u0004\u0012\u00028\u00000%H\u0002J\u0019\u0010,\u001a\u00020'2\u0006\u0010)\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010-J\b\u0010.\u001a\u00020/H\u0016J&\u00100\u001a\b\u0012\u0004\u0012\u00020'012\u0006\u0010)\u001a\u00028\u0000H\u0016ø\u0001\u0001ø\u0001\u0002ø\u0001\u0000¢\u0006\u0004\b2\u00103R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000e\u001a\u00060\u000fj\u0002`\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R6\u0010\u0011\u001a*\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0013\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0012j\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0013\u0012\u0006\u0012\u0004\u0018\u00010\r`\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u0017\u001a\u00028\u00008F¢\u0006\f\u0012\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0019\u0010\u001c\u001a\u0004\u0018\u00018\u00008F¢\u0006\f\u0012\u0004\b\u001d\u0010\u0019\u001a\u0004\b\u001e\u0010\u001b\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u00066"}, d2 = {"Lkotlinx/coroutines/channels/BroadcastChannelImpl;", "E", "Lkotlinx/coroutines/channels/BufferedChannel;", "Lkotlinx/coroutines/channels/BroadcastChannel;", "capacity", "", "(I)V", "getCapacity", "()I", "isClosedForSend", "", "()Z", "lastConflatedElement", "", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "Lkotlinx/coroutines/internal/ReentrantLock;", "onSendInternalResult", "Ljava/util/HashMap;", "Lkotlinx/coroutines/selects/SelectInstance;", "Lkotlin/collections/HashMap;", "subscribers", "", "value", "getValue$annotations", "()V", "getValue", "()Ljava/lang/Object;", "valueOrNull", "getValueOrNull$annotations", "getValueOrNull", "cancelImpl", "cause", "", "cancelImpl$kotlinx_coroutines_core", "close", "openSubscription", "Lkotlinx/coroutines/channels/ReceiveChannel;", "registerSelectForSend", "", "select", "element", "removeSubscriber", "s", "send", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toString", "", "trySend", "Lkotlinx/coroutines/channels/ChannelResult;", "trySend-JP2dKIU", "(Ljava/lang/Object;)Ljava/lang/Object;", "SubscriberBuffered", "SubscriberConflated", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class BroadcastChannelImpl<E> extends BufferedChannel<E> implements BroadcastChannel<E> {
    private final int capacity;
    private Object lastConflatedElement;
    private final ReentrantLock lock;
    private final HashMap<SelectInstance<?>, Object> onSendInternalResult;
    private List<? extends BufferedChannel<E>> subscribers;

    public static /* synthetic */ void getValue$annotations() {
    }

    public static /* synthetic */ void getValueOrNull$annotations() {
    }

    public final int getCapacity() {
        return this.capacity;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BroadcastChannelImpl(int capacity) {
        super(0, null);
        boolean z = false;
        this.capacity = capacity;
        if (!((this.capacity >= 1 || this.capacity == -1) ? true : z)) {
            throw new IllegalArgumentException(("BroadcastChannel capacity must be positive or Channel.CONFLATED, but " + this.capacity + " was specified").toString());
        }
        this.lock = new ReentrantLock();
        this.subscribers = CollectionsKt.emptyList();
        this.lastConflatedElement = BroadcastChannelKt.access$getNO_ELEMENT$p();
        this.onSendInternalResult = new HashMap<>();
    }

    @Override // kotlinx.coroutines.channels.BroadcastChannel
    public ReceiveChannel<E> openSubscription() {
        ReentrantLock $this$withLock$iv = this.lock;
        ReentrantLock reentrantLock = $this$withLock$iv;
        reentrantLock.lock();
        try {
            BufferedChannel s = this.capacity == -1 ? new SubscriberConflated() : new SubscriberBuffered();
            if (isClosedForSend() && this.lastConflatedElement == BroadcastChannelKt.access$getNO_ELEMENT$p()) {
                s.close(getCloseCause());
                return s;
            }
            if (this.lastConflatedElement != BroadcastChannelKt.access$getNO_ELEMENT$p()) {
                s.mo1776trySendJP2dKIU(getValue());
            }
            this.subscribers = CollectionsKt.plus((Collection<? extends BufferedChannel>) this.subscribers, s);
            reentrantLock.unlock();
            return s;
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removeSubscriber(ReceiveChannel<? extends E> receiveChannel) {
        ReentrantLock $this$withLock$iv = this.lock;
        ReentrantLock reentrantLock = $this$withLock$iv;
        reentrantLock.lock();
        try {
            Iterable<Object> $this$filter$iv = this.subscribers;
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$filter$iv) {
                BufferedChannel it = (BufferedChannel) element$iv$iv;
                if (it != receiveChannel) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
            this.subscribers = (List) destination$iv$iv;
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00a6  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x008a -> B:27:0x008e). Please submit an issue!!! */
    @Override // kotlinx.coroutines.channels.BufferedChannel, kotlinx.coroutines.channels.SendChannel
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object send(E r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            r9 = this;
            boolean r0 = r11 instanceof kotlinx.coroutines.channels.BroadcastChannelImpl$send$1
            if (r0 == 0) goto L14
            r0 = r11
            kotlinx.coroutines.channels.BroadcastChannelImpl$send$1 r0 = (kotlinx.coroutines.channels.BroadcastChannelImpl$send$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            kotlinx.coroutines.channels.BroadcastChannelImpl$send$1 r0 = new kotlinx.coroutines.channels.BroadcastChannelImpl$send$1
            r0.<init>(r9, r11)
        L19:
            r11 = r0
            java.lang.Object r0 = r11.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r11.label
            switch(r2) {
                case 0: goto L40;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L2d:
            r10 = 0
            r2 = 0
            java.lang.Object r3 = r11.L$2
            java.util.Iterator r3 = (java.util.Iterator) r3
            java.lang.Object r4 = r11.L$1
            java.lang.Object r5 = r11.L$0
            kotlinx.coroutines.channels.BroadcastChannelImpl r5 = (kotlinx.coroutines.channels.BroadcastChannelImpl) r5
            kotlin.ResultKt.throwOnFailure(r0)
            r6 = r2
            r2 = r1
            r1 = r0
            goto L8e
        L40:
            kotlin.ResultKt.throwOnFailure(r0)
            r2 = r9
            java.util.concurrent.locks.ReentrantLock r3 = r2.lock
            r4 = 0
            r5 = r3
            java.util.concurrent.locks.Lock r5 = (java.util.concurrent.locks.Lock) r5
            r5.lock()
            r3 = 0
            boolean r6 = r2.isClosedForSend()     // Catch: java.lang.Throwable -> Laf
            if (r6 != 0) goto Laa
            int r6 = r2.capacity     // Catch: java.lang.Throwable -> Laf
            r7 = -1
            if (r6 != r7) goto L5b
            r2.lastConflatedElement = r10     // Catch: java.lang.Throwable -> Laf
        L5b:
            java.util.List<? extends kotlinx.coroutines.channels.BufferedChannel<E>> r6 = r2.subscribers     // Catch: java.lang.Throwable -> Laf
            r5.unlock()
            r3 = r6
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            r4 = 0
            java.util.Iterator r5 = r3.iterator()
            r3 = r5
            r5 = r2
            r8 = r4
            r4 = r10
            r10 = r8
        L6d:
            boolean r2 = r3.hasNext()
            if (r2 == 0) goto La6
            java.lang.Object r2 = r3.next()
            kotlinx.coroutines.channels.BufferedChannel r2 = (kotlinx.coroutines.channels.BufferedChannel) r2
            r6 = 0
            r11.L$0 = r5
            r11.L$1 = r4
            r11.L$2 = r3
            r7 = 1
            r11.label = r7
            java.lang.Object r2 = r2.sendBroadcast$kotlinx_coroutines_core(r4, r11)
            if (r2 != r1) goto L8a
            return r1
        L8a:
            r8 = r1
            r1 = r0
            r0 = r2
            r2 = r8
        L8e:
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 != 0) goto La2
            boolean r7 = r5.isClosedForSend()
            if (r7 != 0) goto L9d
            goto La2
        L9d:
            java.lang.Throwable r2 = r5.getSendException()
            throw r2
        La2:
            r0 = r1
            r1 = r2
            goto L6d
        La6:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        Laa:
            java.lang.Throwable r1 = r2.getSendException()     // Catch: java.lang.Throwable -> Laf
            throw r1     // Catch: java.lang.Throwable -> Laf
        Laf:
            r10 = move-exception
            r5.unlock()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BroadcastChannelImpl.send(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.channels.BufferedChannel, kotlinx.coroutines.channels.SendChannel
    /* renamed from: trySend-JP2dKIU  reason: not valid java name */
    public Object mo1776trySendJP2dKIU(E e) {
        ReentrantLock $this$withLock$iv = this.lock;
        ReentrantLock reentrantLock = $this$withLock$iv;
        reentrantLock.lock();
        try {
            if (isClosedForSend()) {
                return super.mo1776trySendJP2dKIU(e);
            }
            Iterable $this$any$iv = this.subscribers;
            boolean z = false;
            if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
                Iterator<T> it = $this$any$iv.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Object element$iv = it.next();
                    BufferedChannel it2 = (BufferedChannel) element$iv;
                    if (it2.shouldSendSuspend$kotlinx_coroutines_core()) {
                        z = true;
                        break;
                    }
                }
            }
            boolean shouldSuspend = z;
            if (shouldSuspend) {
                return ChannelResult.Companion.m1800failurePtdJZtk();
            }
            if (this.capacity == -1) {
                this.lastConflatedElement = e;
            }
            Iterable $this$forEach$iv = this.subscribers;
            for (Object element$iv2 : $this$forEach$iv) {
                BufferedChannel it3 = (BufferedChannel) element$iv2;
                it3.mo1776trySendJP2dKIU(e);
            }
            return ChannelResult.Companion.m1801successJP2dKIU(Unit.INSTANCE);
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.channels.BufferedChannel
    public void registerSelectForSend(SelectInstance<?> selectInstance, Object element) {
        ReentrantLock $this$withLock$iv = this.lock;
        ReentrantLock reentrantLock = $this$withLock$iv;
        reentrantLock.lock();
        try {
            Object result = this.onSendInternalResult.remove(selectInstance);
            if (result != null) {
                selectInstance.selectInRegistrationPhase(result);
                return;
            }
            Unit unit = Unit.INSTANCE;
            reentrantLock.unlock();
            BuildersKt.launch$default(CoroutineScopeKt.CoroutineScope(selectInstance.getContext()), null, CoroutineStart.UNDISPATCHED, new BroadcastChannelImpl$registerSelectForSend$2(this, element, selectInstance, null), 1, null);
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.BufferedChannel, kotlinx.coroutines.channels.SendChannel
    public boolean close(Throwable cause) {
        ReentrantLock $this$withLock$iv = this.lock;
        ReentrantLock reentrantLock = $this$withLock$iv;
        reentrantLock.lock();
        try {
            Iterable $this$forEach$iv = this.subscribers;
            for (Object element$iv : $this$forEach$iv) {
                BufferedChannel it = (BufferedChannel) element$iv;
                it.close(cause);
            }
            Iterable $this$forEach$iv2 = this.subscribers;
            Iterable<Object> $this$filter$iv = $this$forEach$iv2;
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$filter$iv) {
                BufferedChannel it2 = (BufferedChannel) element$iv$iv;
                if (it2.hasElements$kotlinx_coroutines_core()) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
            this.subscribers = (List) destination$iv$iv;
            return super.close(cause);
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.BufferedChannel
    public boolean cancelImpl$kotlinx_coroutines_core(Throwable cause) {
        ReentrantLock $this$withLock$iv = this.lock;
        ReentrantLock reentrantLock = $this$withLock$iv;
        reentrantLock.lock();
        try {
            Iterable $this$forEach$iv = this.subscribers;
            for (Object element$iv : $this$forEach$iv) {
                BufferedChannel it = (BufferedChannel) element$iv;
                it.cancelImpl$kotlinx_coroutines_core(cause);
            }
            this.lastConflatedElement = BroadcastChannelKt.access$getNO_ELEMENT$p();
            return super.cancelImpl$kotlinx_coroutines_core(cause);
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.BufferedChannel, kotlinx.coroutines.channels.SendChannel
    public boolean isClosedForSend() {
        ReentrantLock $this$withLock$iv = this.lock;
        ReentrantLock reentrantLock = $this$withLock$iv;
        reentrantLock.lock();
        try {
            return super.isClosedForSend();
        } finally {
            reentrantLock.unlock();
        }
    }

    /* compiled from: BroadcastChannel.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0003\n\u0000\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lkotlinx/coroutines/channels/BroadcastChannelImpl$SubscriberBuffered;", "Lkotlinx/coroutines/channels/BufferedChannel;", "(Lkotlinx/coroutines/channels/BroadcastChannelImpl;)V", "cancelImpl", "", "cause", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    private final class SubscriberBuffered extends BufferedChannel<E> {
        public SubscriberBuffered() {
            super(BroadcastChannelImpl.this.getCapacity(), null, 2, null);
        }

        @Override // kotlinx.coroutines.channels.BufferedChannel
        /* renamed from: cancelImpl */
        public boolean cancelImpl$kotlinx_coroutines_core(Throwable cause) {
            ReentrantLock $this$withLock$iv = ((BroadcastChannelImpl) BroadcastChannelImpl.this).lock;
            BroadcastChannelImpl<E> broadcastChannelImpl = BroadcastChannelImpl.this;
            ReentrantLock reentrantLock = $this$withLock$iv;
            reentrantLock.lock();
            try {
                broadcastChannelImpl.removeSubscriber(this);
                return super.cancelImpl$kotlinx_coroutines_core(cause);
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    /* compiled from: BroadcastChannel.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0003\n\u0000\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lkotlinx/coroutines/channels/BroadcastChannelImpl$SubscriberConflated;", "Lkotlinx/coroutines/channels/ConflatedBufferedChannel;", "(Lkotlinx/coroutines/channels/BroadcastChannelImpl;)V", "cancelImpl", "", "cause", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    private final class SubscriberConflated extends ConflatedBufferedChannel<E> {
        public SubscriberConflated() {
            super(1, BufferOverflow.DROP_OLDEST, null, 4, null);
        }

        @Override // kotlinx.coroutines.channels.BufferedChannel
        /* renamed from: cancelImpl */
        public boolean cancelImpl$kotlinx_coroutines_core(Throwable cause) {
            BroadcastChannelImpl.this.removeSubscriber(this);
            return super.cancelImpl$kotlinx_coroutines_core(cause);
        }
    }

    public final E getValue() {
        ReentrantLock $this$withLock$iv = this.lock;
        ReentrantLock reentrantLock = $this$withLock$iv;
        reentrantLock.lock();
        try {
            if (isClosedForSend()) {
                Throwable closeCause = getCloseCause();
                if (closeCause == null) {
                    throw new IllegalStateException("This broadcast channel is closed");
                }
                throw closeCause;
            } else if (this.lastConflatedElement == BroadcastChannelKt.access$getNO_ELEMENT$p()) {
                throw new IllegalStateException("No value".toString());
            } else {
                return (E) this.lastConflatedElement;
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    public final E getValueOrNull() {
        ReentrantLock $this$withLock$iv = this.lock;
        ReentrantLock reentrantLock = $this$withLock$iv;
        reentrantLock.lock();
        try {
            E e = null;
            if (!isClosedForReceive() && this.lastConflatedElement != BroadcastChannelKt.access$getNO_ELEMENT$p()) {
                e = (E) this.lastConflatedElement;
            }
            return e;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.BufferedChannel
    public String toString() {
        return (this.lastConflatedElement != BroadcastChannelKt.access$getNO_ELEMENT$p() ? "CONFLATED_ELEMENT=" + this.lastConflatedElement + "; " : "") + "BROADCAST=<" + super.toString() + ">; SUBSCRIBERS=" + CollectionsKt.joinToString$default(this.subscribers, ";", "<", ">", 0, null, null, 56, null);
    }
}
