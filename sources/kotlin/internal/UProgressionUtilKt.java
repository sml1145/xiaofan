package kotlin.internal;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.UByte$$ExternalSyntheticBackport3;
import kotlin.UInt;
import kotlin.ULong;
/* compiled from: UProgressionUtil.kt */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u001a'\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u0005\u0010\u0006\u001a'\u0010\u0000\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\b\u0010\t\u001a'\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0001¢\u0006\u0004\b\u000f\u0010\u0006\u001a'\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0010H\u0001¢\u0006\u0004\b\u0011\u0010\t¨\u0006\u0012"}, d2 = {"differenceModulo", "Lkotlin/UInt;", "a", "b", "c", "differenceModulo-WZ9TVnA", "(III)I", "Lkotlin/ULong;", "differenceModulo-sambcqE", "(JJJ)J", "getProgressionLastElement", "start", "end", "step", "", "getProgressionLastElement-Nkh28Cs", "", "getProgressionLastElement-7ftBX0g", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class UProgressionUtilKt {
    /* renamed from: differenceModulo-WZ9TVnA  reason: not valid java name */
    private static final int m1474differenceModuloWZ9TVnA(int a, int b, int c) {
        int compare;
        int ac = UByte$$ExternalSyntheticBackport0.m(a, c);
        int bc = UByte$$ExternalSyntheticBackport0.m(b, c);
        compare = Integer.compare(ac ^ Integer.MIN_VALUE, bc ^ Integer.MIN_VALUE);
        return UInt.m362constructorimpl(compare >= 0 ? ac - bc : UInt.m362constructorimpl(ac - bc) + c);
    }

    /* renamed from: differenceModulo-sambcqE  reason: not valid java name */
    private static final long m1475differenceModulosambcqE(long a, long b, long c) {
        int compare;
        long ac = UByte$$ExternalSyntheticBackport3.m(a, c);
        long bc = UByte$$ExternalSyntheticBackport3.m(b, c);
        compare = Long.compare(ac ^ Long.MIN_VALUE, bc ^ Long.MIN_VALUE);
        return ULong.m441constructorimpl(compare >= 0 ? ac - bc : ULong.m441constructorimpl(ac - bc) + c);
    }

    /* renamed from: getProgressionLastElement-Nkh28Cs  reason: not valid java name */
    public static final int m1477getProgressionLastElementNkh28Cs(int start, int end, int step) {
        int compare;
        int compare2;
        if (step > 0) {
            compare2 = Integer.compare(start ^ Integer.MIN_VALUE, end ^ Integer.MIN_VALUE);
            if (compare2 < 0) {
                return UInt.m362constructorimpl(end - m1474differenceModuloWZ9TVnA(end, start, UInt.m362constructorimpl(step)));
            }
        } else if (step >= 0) {
            throw new IllegalArgumentException("Step is zero.");
        } else {
            compare = Integer.compare(start ^ Integer.MIN_VALUE, end ^ Integer.MIN_VALUE);
            if (compare > 0) {
                return UInt.m362constructorimpl(m1474differenceModuloWZ9TVnA(start, end, UInt.m362constructorimpl(-step)) + end);
            }
        }
        return end;
    }

    /* renamed from: getProgressionLastElement-7ftBX0g  reason: not valid java name */
    public static final long m1476getProgressionLastElement7ftBX0g(long start, long end, long step) {
        int compare;
        int compare2;
        if (step > 0) {
            compare2 = Long.compare(start ^ Long.MIN_VALUE, end ^ Long.MIN_VALUE);
            if (compare2 < 0) {
                return ULong.m441constructorimpl(end - m1475differenceModulosambcqE(end, start, ULong.m441constructorimpl(step)));
            }
        } else if (step >= 0) {
            throw new IllegalArgumentException("Step is zero.");
        } else {
            compare = Long.compare(start ^ Long.MIN_VALUE, end ^ Long.MIN_VALUE);
            if (compare > 0) {
                return ULong.m441constructorimpl(m1475differenceModulosambcqE(start, end, ULong.m441constructorimpl(-step)) + end);
            }
        }
        return end;
    }
}
