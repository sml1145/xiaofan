package kotlin.random;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;
import kotlin.ranges.ULongRange;
/* compiled from: URandom.kt */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001f\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u001f\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\bH\u0000¢\u0006\u0004\b\t\u0010\n\u001a\u0019\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007¢\u0006\u0002\u0010\u0010\u001a\u001b\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u0011\u001a\u00020\fH\u0007¢\u0006\u0004\b\u0012\u0010\u0013\u001a/\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u0011\u001a\u00020\f2\b\b\u0002\u0010\u0014\u001a\u00020\u000f2\b\b\u0002\u0010\u0015\u001a\u00020\u000fH\u0007¢\u0006\u0004\b\u0016\u0010\u0017\u001a\u0011\u0010\u0018\u001a\u00020\u0003*\u00020\rH\u0007¢\u0006\u0002\u0010\u0019\u001a\u001b\u0010\u0018\u001a\u00020\u0003*\u00020\r2\u0006\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u001a\u0010\u001b\u001a#\u0010\u0018\u001a\u00020\u0003*\u00020\r2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u0019\u0010\u0018\u001a\u00020\u0003*\u00020\r2\u0006\u0010\u001e\u001a\u00020\u001fH\u0007¢\u0006\u0002\u0010 \u001a\u0011\u0010!\u001a\u00020\b*\u00020\rH\u0007¢\u0006\u0002\u0010\"\u001a\u001b\u0010!\u001a\u00020\b*\u00020\r2\u0006\u0010\u0004\u001a\u00020\bH\u0007¢\u0006\u0004\b#\u0010$\u001a#\u0010!\u001a\u00020\b*\u00020\r2\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\bH\u0007¢\u0006\u0004\b%\u0010&\u001a\u0019\u0010!\u001a\u00020\b*\u00020\r2\u0006\u0010\u001e\u001a\u00020'H\u0007¢\u0006\u0002\u0010(¨\u0006)"}, d2 = {"checkUIntRangeBounds", "", "from", "Lkotlin/UInt;", "until", "checkUIntRangeBounds-J1ME1BU", "(II)V", "checkULongRangeBounds", "Lkotlin/ULong;", "checkULongRangeBounds-eb3DHEI", "(JJ)V", "nextUBytes", "Lkotlin/UByteArray;", "Lkotlin/random/Random;", "size", "", "(Lkotlin/random/Random;I)[B", "array", "nextUBytes-EVgfTAA", "(Lkotlin/random/Random;[B)[B", "fromIndex", "toIndex", "nextUBytes-Wvrt4B4", "(Lkotlin/random/Random;[BII)[B", "nextUInt", "(Lkotlin/random/Random;)I", "nextUInt-qCasIEU", "(Lkotlin/random/Random;I)I", "nextUInt-a8DCA5k", "(Lkotlin/random/Random;II)I", "range", "Lkotlin/ranges/UIntRange;", "(Lkotlin/random/Random;Lkotlin/ranges/UIntRange;)I", "nextULong", "(Lkotlin/random/Random;)J", "nextULong-V1Xi4fY", "(Lkotlin/random/Random;J)J", "nextULong-jmpaW-c", "(Lkotlin/random/Random;JJ)J", "Lkotlin/ranges/ULongRange;", "(Lkotlin/random/Random;Lkotlin/ranges/ULongRange;)J", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class URandomKt {
    public static final int nextUInt(Random $this$nextUInt) {
        Intrinsics.checkNotNullParameter($this$nextUInt, "<this>");
        return UInt.m368constructorimpl($this$nextUInt.nextInt());
    }

    /* renamed from: nextUInt-qCasIEU  reason: not valid java name */
    public static final int m1494nextUIntqCasIEU(Random nextUInt, int until) {
        Intrinsics.checkNotNullParameter(nextUInt, "$this$nextUInt");
        return m1493nextUInta8DCA5k(nextUInt, 0, until);
    }

    /* renamed from: nextUInt-a8DCA5k  reason: not valid java name */
    public static final int m1493nextUInta8DCA5k(Random nextUInt, int from, int until) {
        Intrinsics.checkNotNullParameter(nextUInt, "$this$nextUInt");
        m1488checkUIntRangeBoundsJ1ME1BU(from, until);
        int signedFrom = from ^ Integer.MIN_VALUE;
        int signedUntil = until ^ Integer.MIN_VALUE;
        int signedResult = Integer.MIN_VALUE ^ nextUInt.nextInt(signedFrom, signedUntil);
        return UInt.m368constructorimpl(signedResult);
    }

    public static final int nextUInt(Random $this$nextUInt, UIntRange range) {
        int compare;
        int compare2;
        Intrinsics.checkNotNullParameter($this$nextUInt, "<this>");
        Intrinsics.checkNotNullParameter(range, "range");
        if (range.isEmpty()) {
            throw new IllegalArgumentException("Cannot get random in empty range: " + range);
        }
        compare = Integer.compare(range.m1498getLastpVg5ArA() ^ Integer.MIN_VALUE, (-1) ^ Integer.MIN_VALUE);
        if (compare < 0) {
            return m1493nextUInta8DCA5k($this$nextUInt, range.m1497getFirstpVg5ArA(), UInt.m368constructorimpl(range.m1498getLastpVg5ArA() + 1));
        }
        compare2 = Integer.compare(range.m1497getFirstpVg5ArA() ^ Integer.MIN_VALUE, 0 ^ Integer.MIN_VALUE);
        return compare2 > 0 ? UInt.m368constructorimpl(m1493nextUInta8DCA5k($this$nextUInt, UInt.m368constructorimpl(range.m1497getFirstpVg5ArA() - 1), range.m1498getLastpVg5ArA()) + 1) : nextUInt($this$nextUInt);
    }

    public static final long nextULong(Random $this$nextULong) {
        Intrinsics.checkNotNullParameter($this$nextULong, "<this>");
        return ULong.m447constructorimpl($this$nextULong.nextLong());
    }

    /* renamed from: nextULong-V1Xi4fY  reason: not valid java name */
    public static final long m1495nextULongV1Xi4fY(Random nextULong, long until) {
        Intrinsics.checkNotNullParameter(nextULong, "$this$nextULong");
        return m1496nextULongjmpaWc(nextULong, 0L, until);
    }

    /* renamed from: nextULong-jmpaW-c  reason: not valid java name */
    public static final long m1496nextULongjmpaWc(Random nextULong, long from, long until) {
        Intrinsics.checkNotNullParameter(nextULong, "$this$nextULong");
        m1489checkULongRangeBoundseb3DHEI(from, until);
        long signedFrom = from ^ Long.MIN_VALUE;
        long signedUntil = until ^ Long.MIN_VALUE;
        long signedResult = Long.MIN_VALUE ^ nextULong.nextLong(signedFrom, signedUntil);
        return ULong.m447constructorimpl(signedResult);
    }

    public static final long nextULong(Random $this$nextULong, ULongRange range) {
        int compare;
        int compare2;
        Intrinsics.checkNotNullParameter($this$nextULong, "<this>");
        Intrinsics.checkNotNullParameter(range, "range");
        if (range.isEmpty()) {
            throw new IllegalArgumentException("Cannot get random in empty range: " + range);
        }
        compare = Long.compare(range.m1507getLastsVKNKU() ^ Long.MIN_VALUE, (-1) ^ Long.MIN_VALUE);
        if (compare < 0) {
            return m1496nextULongjmpaWc($this$nextULong, range.m1506getFirstsVKNKU(), ULong.m447constructorimpl(range.m1507getLastsVKNKU() + ULong.m447constructorimpl(1 & 4294967295L)));
        }
        compare2 = Long.compare(range.m1506getFirstsVKNKU() ^ Long.MIN_VALUE, 0 ^ Long.MIN_VALUE);
        if (compare2 > 0) {
            long j = 1 & 4294967295L;
            return ULong.m447constructorimpl(m1496nextULongjmpaWc($this$nextULong, ULong.m447constructorimpl(range.m1506getFirstsVKNKU() - ULong.m447constructorimpl(j)), range.m1507getLastsVKNKU()) + ULong.m447constructorimpl(j));
        }
        return nextULong($this$nextULong);
    }

    /* renamed from: nextUBytes-EVgfTAA  reason: not valid java name */
    public static final byte[] m1490nextUBytesEVgfTAA(Random nextUBytes, byte[] array) {
        Intrinsics.checkNotNullParameter(nextUBytes, "$this$nextUBytes");
        Intrinsics.checkNotNullParameter(array, "array");
        nextUBytes.nextBytes(array);
        return array;
    }

    public static final byte[] nextUBytes(Random $this$nextUBytes, int size) {
        Intrinsics.checkNotNullParameter($this$nextUBytes, "<this>");
        return UByteArray.m344constructorimpl($this$nextUBytes.nextBytes(size));
    }

    /* renamed from: nextUBytes-Wvrt4B4$default  reason: not valid java name */
    public static /* synthetic */ byte[] m1492nextUBytesWvrt4B4$default(Random random, byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UByteArray.m350getSizeimpl(bArr);
        }
        return m1491nextUBytesWvrt4B4(random, bArr, i, i2);
    }

    /* renamed from: nextUBytes-Wvrt4B4  reason: not valid java name */
    public static final byte[] m1491nextUBytesWvrt4B4(Random nextUBytes, byte[] array, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(nextUBytes, "$this$nextUBytes");
        Intrinsics.checkNotNullParameter(array, "array");
        nextUBytes.nextBytes(array, fromIndex, toIndex);
        return array;
    }

    /* renamed from: checkUIntRangeBounds-J1ME1BU  reason: not valid java name */
    public static final void m1488checkUIntRangeBoundsJ1ME1BU(int from, int until) {
        int compare;
        compare = Integer.compare(until ^ Integer.MIN_VALUE, from ^ Integer.MIN_VALUE);
        if (!(compare > 0)) {
            throw new IllegalArgumentException(RandomKt.boundsErrorMessage(UInt.m362boximpl(from), UInt.m362boximpl(until)).toString());
        }
    }

    /* renamed from: checkULongRangeBounds-eb3DHEI  reason: not valid java name */
    public static final void m1489checkULongRangeBoundseb3DHEI(long from, long until) {
        int compare;
        compare = Long.compare(until ^ Long.MIN_VALUE, from ^ Long.MIN_VALUE);
        if (!(compare > 0)) {
            throw new IllegalArgumentException(RandomKt.boundsErrorMessage(ULong.m441boximpl(from), ULong.m441boximpl(until)).toString());
        }
    }
}
