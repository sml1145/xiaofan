package kotlinx.coroutines.internal;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.Volatile;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.RangesKt;
/* compiled from: OnDemandAllocatingPool.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B!\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0002\u0010\u0007J\u0006\u0010\f\u001a\u00020\rJ\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fJ\r\u0010\u0010\u001a\u00020\u0011H\u0000¢\u0006\u0002\b\u0012J\b\u0010\u0013\u001a\u00020\u0011H\u0016J\t\u0010\u0014\u001a\u00020\u0004H\u0082\bJ\r\u0010\u0015\u001a\u00020\r*\u00020\u0004H\u0082\bR\t\u0010\b\u001a\u00020\tX\u0082\u0004R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00028\u00000\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u000bX\u0082\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lkotlinx/coroutines/internal/OnDemandAllocatingPool;", "T", "", "maxCapacity", "", "create", "Lkotlin/Function1;", "(ILkotlin/jvm/functions/Function1;)V", "controlState", "Lkotlinx/atomicfu/AtomicInt;", "elements", "Lkotlinx/atomicfu/AtomicArray;", "allocate", "", "close", "", "stateRepresentation", "", "stateRepresentation$kotlinx_coroutines_core", "toString", "tryForbidNewElements", "isClosed", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class OnDemandAllocatingPool<T> {
    private static final AtomicIntegerFieldUpdater controlState$FU = AtomicIntegerFieldUpdater.newUpdater(OnDemandAllocatingPool.class, "controlState");
    @Volatile
    private volatile int controlState;
    private final Function1<Integer, T> create;
    private final AtomicReferenceArray elements;
    private final int maxCapacity;

    private final void loop$atomicfu(AtomicIntegerFieldUpdater atomicIntegerFieldUpdater, Function1<? super Integer, Unit> function1, Object obj) {
        while (true) {
            function1.invoke(Integer.valueOf(atomicIntegerFieldUpdater.get(obj)));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public OnDemandAllocatingPool(int maxCapacity, Function1<? super Integer, ? extends T> function1) {
        this.maxCapacity = maxCapacity;
        this.create = function1;
        this.elements = new AtomicReferenceArray(this.maxCapacity);
    }

    private final int tryForbidNewElements() {
        int it;
        AtomicIntegerFieldUpdater atomicfu$handler$iv = controlState$FU;
        do {
            it = atomicfu$handler$iv.get(this);
            if ((it & Integer.MIN_VALUE) != 0) {
                return 0;
            }
        } while (!controlState$FU.compareAndSet(this, it, it | Integer.MIN_VALUE));
        return it;
    }

    private final boolean isClosed(int $this$isClosed) {
        return (Integer.MIN_VALUE & $this$isClosed) != 0;
    }

    public final boolean allocate() {
        int ctl;
        AtomicIntegerFieldUpdater atomicfu$handler$iv = controlState$FU;
        do {
            ctl = atomicfu$handler$iv.get(this);
            int $this$isClosed$iv = (Integer.MIN_VALUE & ctl) != 0 ? 1 : 0;
            if ($this$isClosed$iv != 0) {
                return false;
            }
            if (ctl >= this.maxCapacity) {
                return true;
            }
        } while (!controlState$FU.compareAndSet(this, ctl, ctl + 1));
        this.elements.set(ctl, this.create.invoke(Integer.valueOf(ctl)));
        return true;
    }

    public final List<T> close() {
        int it$iv;
        Object element;
        AtomicIntegerFieldUpdater atomicfu$handler$iv$iv = controlState$FU;
        while (true) {
            it$iv = atomicfu$handler$iv$iv.get(this);
            if (!((it$iv & Integer.MIN_VALUE) != 0)) {
                if (controlState$FU.compareAndSet(this, it$iv, it$iv | Integer.MIN_VALUE)) {
                    break;
                }
            } else {
                it$iv = 0;
                break;
            }
        }
        int elementsExisting = it$iv;
        Iterable $this$map$iv = RangesKt.until(0, elementsExisting);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        Iterator<T> it = $this$map$iv.iterator();
        while (it.hasNext()) {
            int item$iv$iv = ((IntIterator) it).nextInt();
            do {
                element = this.elements.getAndSet(item$iv$iv, null);
            } while (element == null);
            destination$iv$iv.add(element);
        }
        return (List) destination$iv$iv;
    }

    public final String stateRepresentation$kotlinx_coroutines_core() {
        int ctl = controlState$FU.get(this);
        Iterable $this$map$iv = RangesKt.until(0, Integer.MAX_VALUE & ctl);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        Iterator<T> it = $this$map$iv.iterator();
        while (it.hasNext()) {
            int item$iv$iv = ((IntIterator) it).nextInt();
            destination$iv$iv.add(this.elements.get(item$iv$iv));
        }
        String elementsStr = ((List) destination$iv$iv).toString();
        String closedStr = (Integer.MIN_VALUE & ctl) != 0 ? "[closed]" : "";
        return elementsStr + closedStr;
    }

    public String toString() {
        return "OnDemandAllocatingPool(" + stateRepresentation$kotlinx_coroutines_core() + ')';
    }
}
