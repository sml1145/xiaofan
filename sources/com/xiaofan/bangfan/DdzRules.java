package com.xiaofan.bangfan;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
/* compiled from: DdzRules.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001:\u00010B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0017J\u0016\u0010\u0019\u001a\u0004\u0018\u00010\u00172\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00040\u001bJ\u001e\u0010\u001c\u001a\u0004\u0018\u00010\u00172\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00040\u001b2\u0006\u0010\u001d\u001a\u00020\u0017J \u0010\u001e\u001a\u0004\u0018\u00010\u00172\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00040\u001b2\b\b\u0002\u0010\u001f\u001a\u00020\u0004J>\u0010 \u001a*\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\"0!j\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\"`#2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00040\u001bH\u0002J\u0016\u0010$\u001a\u00020\u00152\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00040\u001bH\u0002J\u000e\u0010&\u001a\u00020\u00152\u0006\u0010'\u001a\u00020\u0004J\u000e\u0010(\u001a\u00020)2\u0006\u0010'\u001a\u00020\u0004J\u000e\u0010*\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u0004J\u0016\u0010+\u001a\u0004\u0018\u00010\u00172\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00040\u001bJ\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00040\"J\u001a\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00040\u001b2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00040\u001bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Lcom/xiaofan/bangfan/DdzRules;", "", "()V", "BIG_JOKER", "", "SMALL_JOKER", "T_BOMB", "T_DBL_STRAIGHT", "T_FOUR2", "T_FOUR22", "T_PAIR", "T_PLANE", "T_PLANE1", "T_PLANE2", "T_ROCKET", "T_SINGLE", "T_STRAIGHT", "T_TRIPLE", "T_TRIPLE1", "T_TRIPLE2", "canBeat", "", "a", "Lcom/xiaofan/bangfan/DdzRules$Combo;", "b", "chooseLead", "hand", "", "findBeat", "target", "findBomb", "aboveRank", "group", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "isConsecutive", "ranksSorted", "isRed", "c", "label", "", "rank", "recognize", "input", "shuffledDeck", "sorted", "cards", "Combo", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class DdzRules {
    public static final int BIG_JOKER = 53;
    public static final DdzRules INSTANCE = new DdzRules();
    public static final int SMALL_JOKER = 52;
    public static final int T_BOMB = 11;
    public static final int T_DBL_STRAIGHT = 7;
    public static final int T_FOUR2 = 13;
    public static final int T_FOUR22 = 14;
    public static final int T_PAIR = 2;
    public static final int T_PLANE = 8;
    public static final int T_PLANE1 = 9;
    public static final int T_PLANE2 = 10;
    public static final int T_ROCKET = 12;
    public static final int T_SINGLE = 1;
    public static final int T_STRAIGHT = 6;
    public static final int T_TRIPLE = 3;
    public static final int T_TRIPLE1 = 4;
    public static final int T_TRIPLE2 = 5;

    private DdzRules() {
    }

    public final int rank(int c) {
        return c >= 52 ? c - 36 : (c / 4) + 3;
    }

    public final String label(int c) {
        int rank = rank(c);
        boolean z = false;
        if (3 <= rank && rank < 11) {
            z = true;
        }
        return z ? String.valueOf(rank(c)) : rank == 11 ? "J" : rank == 12 ? "Q" : rank == 13 ? "K" : rank == 14 ? "A" : rank == 15 ? "2" : rank == 16 ? "小王" : rank == 17 ? "大王" : "?";
    }

    public final boolean isRed(int c) {
        return rank(c) >= 15;
    }

    public final List<Integer> sorted(List<Integer> cards) {
        Intrinsics.checkNotNullParameter(cards, "cards");
        List<Integer> $this$sortedByDescending$iv = cards;
        return CollectionsKt.sortedWith($this$sortedByDescending$iv, new Comparator() { // from class: com.xiaofan.bangfan.DdzRules$sorted$$inlined$sortedByDescending$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                int it = ((Number) t2).intValue();
                int it2 = ((Number) t).intValue();
                return ComparisonsKt.compareValues(Integer.valueOf((DdzRules.INSTANCE.rank(it) * 4) - (it % 4)), Integer.valueOf((DdzRules.INSTANCE.rank(it2) * 4) - (it2 % 4)));
            }
        });
    }

    /* compiled from: DdzRules.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/xiaofan/bangfan/DdzRules$Combo;", "", "type", "", "mainRank", "len", "cards", "", "(III[I)V", "getCards", "()[I", "getLen", "()I", "getMainRank", "getType", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class Combo {
        private final int[] cards;
        private final int len;
        private final int mainRank;
        private final int type;

        public Combo(int type, int mainRank, int len, int[] cards) {
            Intrinsics.checkNotNullParameter(cards, "cards");
            this.type = type;
            this.mainRank = mainRank;
            this.len = len;
            this.cards = cards;
        }

        public final int[] getCards() {
            return this.cards;
        }

        public final int getLen() {
            return this.len;
        }

        public final int getMainRank() {
            return this.mainRank;
        }

        public final int getType() {
            return this.type;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:218:0x0437  */
    /* JADX WARN: Removed duplicated region for block: B:346:0x043a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:347:0x0494 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:349:0x048a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:361:0x05bf A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:362:0x05b5 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.xiaofan.bangfan.DdzRules.Combo recognize(java.util.List<java.lang.Integer> r23) {
        /*
            Method dump skipped, instructions count: 1504
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaofan.bangfan.DdzRules.recognize(java.util.List):com.xiaofan.bangfan.DdzRules$Combo");
    }

    private final boolean isConsecutive(List<Integer> list) {
        int size = list.size();
        for (int i = 1; i < size; i++) {
            if (list.get(i).intValue() != list.get(i - 1).intValue() + 1) {
                return false;
            }
        }
        return true;
    }

    public final boolean canBeat(Combo a, Combo b) {
        Intrinsics.checkNotNullParameter(a, "a");
        if (b == null || a.getType() == 12) {
            return true;
        }
        if (b.getType() == 12) {
            return false;
        }
        if (a.getType() != 11 || b.getType() == 11) {
            return a.getType() == b.getType() && a.getLen() == b.getLen() && a.getMainRank() > b.getMainRank();
        }
        return true;
    }

    private final HashMap<Integer, List<Integer>> group(List<Integer> list) {
        ArrayList arrayList;
        Map g = new LinkedHashMap();
        for (Integer num : sorted(list)) {
            int c = num.intValue();
            Map $this$getOrPut$iv = g;
            Integer valueOf = Integer.valueOf(rank(c));
            Object value$iv = $this$getOrPut$iv.get(valueOf);
            if (value$iv == null) {
                arrayList = new ArrayList();
                $this$getOrPut$iv.put(valueOf, arrayList);
            } else {
                arrayList = value$iv;
            }
            ((List) arrayList).add(Integer.valueOf(c));
        }
        return (HashMap) g;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:114:0x01e7 A[LOOP:5: B:98:0x01b1->B:114:0x01e7, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:158:0x01ea A[EDGE_INSN: B:158:0x01ea->B:116:0x01ea ?: BREAK  , SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r13v7, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v14 */
    /* JADX WARN: Type inference failed for: r9v15 */
    /* JADX WARN: Type inference failed for: r9v16, types: [java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.xiaofan.bangfan.DdzRules.Combo chooseLead(java.util.List<java.lang.Integer> r19) {
        /*
            Method dump skipped, instructions count: 659
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaofan.bangfan.DdzRules.chooseLead(java.util.List):com.xiaofan.bangfan.DdzRules$Combo");
    }

    /* JADX WARN: Removed duplicated region for block: B:216:0x018d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:249:0x03b5 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x018a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.xiaofan.bangfan.DdzRules.Combo findBeat(java.util.List<java.lang.Integer> r19, com.xiaofan.bangfan.DdzRules.Combo r20) {
        /*
            Method dump skipped, instructions count: 1410
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaofan.bangfan.DdzRules.findBeat(java.util.List, com.xiaofan.bangfan.DdzRules$Combo):com.xiaofan.bangfan.DdzRules$Combo");
    }

    static /* synthetic */ List findBeat$cardsOf$default(HashMap hashMap, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 8) != 0) {
            i3 = -1;
        }
        return findBeat$cardsOf(hashMap, i, i2, i3);
    }

    private static final List<Integer> findBeat$cardsOf(HashMap<Integer, List<Integer>> hashMap, int rank, int n, int exclude) {
        List l;
        if (rank != exclude && (l = hashMap.get(Integer.valueOf(rank))) != null && l.size() >= n) {
            return l.subList(0, n);
        }
        return CollectionsKt.emptyList();
    }

    public static /* synthetic */ Combo findBomb$default(DdzRules ddzRules, List list, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = -1;
        }
        return ddzRules.findBomb(list, i);
    }

    public final Combo findBomb(List<Integer> hand, int aboveRank) {
        Intrinsics.checkNotNullParameter(hand, "hand");
        HashMap g = group(hand);
        Set<Integer> keySet = g.keySet();
        Intrinsics.checkNotNullExpressionValue(keySet, "<get-keys>(...)");
        for (Number number : CollectionsKt.sorted(keySet)) {
            int r = number.intValue();
            if (r > aboveRank) {
                List<Integer> list = g.get(Integer.valueOf(r));
                Intrinsics.checkNotNull(list);
                if (list.size() >= 4) {
                    List<Integer> list2 = g.get(Integer.valueOf(r));
                    Intrinsics.checkNotNull(list2);
                    return new Combo(11, r, 1, CollectionsKt.toIntArray(list2.subList(0, 4)));
                }
            }
        }
        if (hand.contains(52) && hand.contains(53)) {
            return new Combo(12, 17, 1, new int[]{52, 53});
        }
        return null;
    }

    public final List<Integer> shuffledDeck() {
        List it = CollectionsKt.toMutableList(new IntRange(0, 53));
        CollectionsKt.shuffle(it, Random.Default);
        return it;
    }
}
