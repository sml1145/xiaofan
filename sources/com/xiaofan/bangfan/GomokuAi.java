package com.xiaofan.bangfan;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.xiaofan.bangfan.GomokuAi;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: GomokuAi.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001:\u000245B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J!\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0015\u001a\u00020\b¢\u0006\u0002\u0010\u0016J#\u0010\u0017\u001a\u00020\u00182\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0015\u001a\u00020\bH\u0002¢\u0006\u0002\u0010\u0019JC\u0010\u001a\u001a\u00020\u00182\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\bH\u0002¢\u0006\u0002\u0010 J3\u0010!\u001a\u00020\u00182\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\bH\u0002¢\u0006\u0002\u0010\"J)\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00130$2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010%\u001a\u00020\bH\u0002¢\u0006\u0002\u0010&JC\u0010'\u001a\u00020(2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010)\u001a\u00020\b2\u0006\u0010*\u001a\u00020\u00182\u0006\u0010+\u001a\u00020\u00182\u0006\u0010,\u001a\u00020-2\u0006\u0010\u0015\u001a\u00020\bH\u0002¢\u0006\u0002\u0010.J3\u0010/\u001a\u00020-2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\bH\u0002¢\u0006\u0002\u00100J7\u00101\u001a\b\u0012\u0004\u0012\u00020\u00130$2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u00102\u001a\b\u0012\u0004\u0012\u00020\u00130$2\u0006\u0010\u001f\u001a\u00020\bH\u0002¢\u0006\u0002\u00103R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000¨\u00066"}, d2 = {"Lcom/xiaofan/bangfan/GomokuAi;", "", "()V", "DIRS", "", "", "[[I", "FIVE", "", "FOUR", "N", "ONE", "OPEN_FOUR", "OPEN_ONE", "OPEN_THREE", "OPEN_TWO", "THREE", "TWO", "bestMove", "Lcom/xiaofan/bangfan/GomokuAi$Candidate;", "b", "aiPlayer", "([[II)Lcom/xiaofan/bangfan/GomokuAi$Candidate;", "evaluateBoard", "", "([[II)J", "evaluateLine", "x", "y", "dx", "dy", "player", "([[IIIIII)J", "evaluatePoint", "([[IIII)J", "getCandidates", "", "range", "([[II)Ljava/util/List;", "minimax", "Lcom/xiaofan/bangfan/GomokuAi$MM;", "depth", "alphaIn", "betaIn", "maximizing", "", "([[IIJJZI)Lcom/xiaofan/bangfan/GomokuAi$MM;", "quickWin", "([[IIII)Z", "scoreCandidates", "cands", "([[ILjava/util/List;I)Ljava/util/List;", "Candidate", "MM", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class GomokuAi {
    private static final int FIVE = 100000;
    private static final int FOUR = 1000;
    private static final int N = 15;
    private static final int ONE = 1;
    private static final int OPEN_FOUR = 10000;
    private static final int OPEN_ONE = 10;
    private static final int OPEN_THREE = 1000;
    private static final int OPEN_TWO = 100;
    private static final int THREE = 100;
    private static final int TWO = 10;
    public static final GomokuAi INSTANCE = new GomokuAi();
    private static final int[][] DIRS = {new int[]{1, 0}, new int[]{0, 1}, new int[]{1, 1}, new int[]{1, -1}};

    private GomokuAi() {
    }

    /* compiled from: GomokuAi.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0006HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\r¨\u0006\u0019"}, d2 = {"Lcom/xiaofan/bangfan/GomokuAi$Candidate;", "", "x", "", "y", "score", "", "(IIJ)V", "getScore", "()J", "setScore", "(J)V", "getX", "()I", "getY", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class Candidate {
        private long score;
        private final int x;
        private final int y;

        public static /* synthetic */ Candidate copy$default(Candidate candidate, int i, int i2, long j, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = candidate.x;
            }
            if ((i3 & 2) != 0) {
                i2 = candidate.y;
            }
            if ((i3 & 4) != 0) {
                j = candidate.score;
            }
            return candidate.copy(i, i2, j);
        }

        public final int component1() {
            return this.x;
        }

        public final int component2() {
            return this.y;
        }

        public final long component3() {
            return this.score;
        }

        public final Candidate copy(int i, int i2, long j) {
            return new Candidate(i, i2, j);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Candidate) {
                Candidate candidate = (Candidate) obj;
                return this.x == candidate.x && this.y == candidate.y && this.score == candidate.score;
            }
            return false;
        }

        public int hashCode() {
            return (((Integer.hashCode(this.x) * 31) + Integer.hashCode(this.y)) * 31) + Long.hashCode(this.score);
        }

        public String toString() {
            int i = this.x;
            int i2 = this.y;
            return "Candidate(x=" + i + ", y=" + i2 + ", score=" + this.score + ")";
        }

        public Candidate(int x, int y, long score) {
            this.x = x;
            this.y = y;
            this.score = score;
        }

        public /* synthetic */ Candidate(int i, int i2, long j, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, i2, (i3 & 4) != 0 ? 0L : j);
        }

        public final long getScore() {
            return this.score;
        }

        public final int getX() {
            return this.x;
        }

        public final int getY() {
            return this.y;
        }

        public final void setScore(long j) {
            this.score = j;
        }
    }

    private final long evaluateLine(int[][] b, int x, int y, int dx, int dy, int player) {
        boolean z;
        int count = 1;
        int openEnds = 0;
        int nx = x + dx;
        int ny = y + dy;
        while (true) {
            z = false;
            if (!(nx >= 0 && nx < 15)) {
                break;
            }
            if (!(ny >= 0 && ny < 15) || b[ny][nx] != player) {
                break;
            }
            count++;
            nx += dx;
            ny += dy;
        }
        if (nx >= 0 && nx < 15) {
            if ((ny >= 0 && ny < 15) && b[ny][nx] == 0) {
                openEnds = 0 + 1;
            }
        }
        int nx2 = x - dx;
        int ny2 = y - dy;
        while (true) {
            if (!(nx2 >= 0 && nx2 < 15)) {
                break;
            }
            if (!(ny2 >= 0 && ny2 < 15) || b[ny2][nx2] != player) {
                break;
            }
            count++;
            nx2 -= dx;
            ny2 -= dy;
        }
        if (nx2 >= 0 && nx2 < 15) {
            if (ny2 >= 0 && ny2 < 15) {
                z = true;
            }
            if (z && b[ny2][nx2] == 0) {
                openEnds++;
            }
        }
        if (count >= 5) {
            return 100000L;
        }
        if (count == 4) {
            switch (openEnds) {
                case 1:
                    return 1000L;
                case 2:
                    return UpdateDownloadCore.STALL_WINDOW_MS;
                default:
                    return 0L;
            }
        } else if (count == 3) {
            switch (openEnds) {
                case 1:
                    return 100L;
                case 2:
                    return 1000L;
                default:
                    return 0L;
            }
        } else if (count == 2) {
            switch (openEnds) {
                case 1:
                    return 10L;
                case 2:
                    return 100L;
                default:
                    return 0L;
            }
        } else if (count == 1) {
            switch (openEnds) {
                case 1:
                    return 1L;
                case 2:
                    return 10L;
                default:
                    return 0L;
            }
        } else {
            return 0L;
        }
    }

    private final long evaluatePoint(int[][] b, int x, int y, int player) {
        int[][] iArr;
        long total = 0;
        for (int[] iArr2 : DIRS) {
            int dx = iArr2[0];
            int dy = iArr2[1];
            total += evaluateLine(b, x, y, dx, dy, player);
        }
        return total;
    }

    private final long evaluateBoard(int[][] b, int aiPlayer) {
        int human = aiPlayer == 1 ? 2 : 1;
        long aiScore = 0;
        long humanScore = 0;
        for (int y = 0; y < 15; y++) {
            for (int x = 0; x < 15; x++) {
                int i = b[y][x];
                if (i == aiPlayer) {
                    aiScore += evaluatePoint(b, x, y, aiPlayer);
                } else if (i == human) {
                    humanScore += evaluatePoint(b, x, y, human);
                }
            }
        }
        return aiScore - ((11 * humanScore) / 10);
    }

    private final List<Candidate> getCandidates(int[][] b, int range) {
        LinkedHashSet set = new LinkedHashSet();
        boolean hasStone = false;
        for (int y = 0; y < 15; y++) {
            for (int x = 0; x < 15; x++) {
                if (b[y][x] != 0) {
                    hasStone = true;
                    int dy = -range;
                    if (dy <= range) {
                        while (true) {
                            int dx = -range;
                            if (dx <= range) {
                                while (true) {
                                    int nx = x + dx;
                                    int ny = y + dy;
                                    boolean z = false;
                                    if (nx >= 0 && nx < 15) {
                                        if (ny >= 0 && ny < 15) {
                                            z = true;
                                        }
                                        if (z && b[ny][nx] == 0) {
                                            set.add(Integer.valueOf((ny * 15) + nx));
                                        }
                                    }
                                    if (dx == range) {
                                        break;
                                    }
                                    dx++;
                                }
                            }
                            if (dy != range) {
                                dy++;
                            }
                        }
                    }
                }
            }
        }
        if (hasStone) {
            LinkedHashSet $this$map$iv = set;
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                int it = ((Number) item$iv$iv).intValue();
                destination$iv$iv.add(new Candidate(it % 15, it / 15, 0L, 4, null));
            }
            return (List) destination$iv$iv;
        }
        return CollectionsKt.listOf(new Candidate(7, 7, 0L, 4, null));
    }

    private final List<Candidate> scoreCandidates(int[][] b, List<Candidate> list, int player) {
        int[][] iArr = b;
        int i = player;
        int opp = i == 1 ? 2 : 1;
        List<Candidate> $this$forEach$iv = list;
        for (Object element$iv : $this$forEach$iv) {
            Candidate pos = (Candidate) element$iv;
            iArr[pos.getY()][pos.getX()] = i;
            long attack = INSTANCE.evaluatePoint(iArr, pos.getX(), pos.getY(), i);
            iArr[pos.getY()][pos.getX()] = 0;
            iArr[pos.getY()][pos.getX()] = opp;
            long defend = INSTANCE.evaluatePoint(iArr, pos.getX(), pos.getY(), opp);
            iArr[pos.getY()][pos.getX()] = 0;
            int centerDist = Math.abs(pos.getX() - 7) + Math.abs(pos.getY() - 7);
            long posBonus = (14 - centerDist) * 2;
            pos.setScore(((105 * defend) / 100) + attack + posBonus);
            iArr = b;
            i = player;
            opp = opp;
            $this$forEach$iv = $this$forEach$iv;
        }
        List<Candidate> $this$sortedByDescending$iv = list;
        return CollectionsKt.sortedWith($this$sortedByDescending$iv, new Comparator() { // from class: com.xiaofan.bangfan.GomokuAi$scoreCandidates$$inlined$sortedByDescending$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                GomokuAi.Candidate it = (GomokuAi.Candidate) t2;
                GomokuAi.Candidate it2 = (GomokuAi.Candidate) t;
                return ComparisonsKt.compareValues(Long.valueOf(it.getScore()), Long.valueOf(it2.getScore()));
            }
        });
    }

    private final boolean quickWin(int[][] b, int x, int y, int player) {
        int[][] iArr;
        for (int[] iArr2 : DIRS) {
            int dx = iArr2[0];
            int dy = iArr2[1];
            int count = 1;
            int nx = x + dx;
            int ny = y + dy;
            while (true) {
                if (!(nx >= 0 && nx < 15)) {
                    break;
                }
                if (!(ny >= 0 && ny < 15) || b[ny][nx] != player) {
                    break;
                }
                count++;
                nx += dx;
                ny += dy;
            }
            int nx2 = x - dx;
            int ny2 = y - dy;
            while (true) {
                if (!(nx2 >= 0 && nx2 < 15)) {
                    break;
                }
                if (!(ny2 >= 0 && ny2 < 15) || b[ny2][nx2] != player) {
                    break;
                }
                count++;
                nx2 -= dx;
                ny2 -= dy;
            }
            if (count >= 5) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: GomokuAi.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/xiaofan/bangfan/GomokuAi$MM;", "", "score", "", "move", "Lcom/xiaofan/bangfan/GomokuAi$Candidate;", "(JLcom/xiaofan/bangfan/GomokuAi$Candidate;)V", "getMove", "()Lcom/xiaofan/bangfan/GomokuAi$Candidate;", "getScore", "()J", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class MM {
        private final Candidate move;
        private final long score;

        public static /* synthetic */ MM copy$default(MM mm, long j, Candidate candidate, int i, Object obj) {
            if ((i & 1) != 0) {
                j = mm.score;
            }
            if ((i & 2) != 0) {
                candidate = mm.move;
            }
            return mm.copy(j, candidate);
        }

        public final long component1() {
            return this.score;
        }

        public final Candidate component2() {
            return this.move;
        }

        public final MM copy(long j, Candidate candidate) {
            return new MM(j, candidate);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof MM) {
                MM mm = (MM) obj;
                return this.score == mm.score && Intrinsics.areEqual(this.move, mm.move);
            }
            return false;
        }

        public int hashCode() {
            return (Long.hashCode(this.score) * 31) + (this.move == null ? 0 : this.move.hashCode());
        }

        public String toString() {
            long j = this.score;
            return "MM(score=" + j + ", move=" + this.move + ")";
        }

        public MM(long score, Candidate move) {
            this.score = score;
            this.move = move;
        }

        public final Candidate getMove() {
            return this.move;
        }

        public final long getScore() {
            return this.score;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x00d2, code lost:
        return new com.xiaofan.bangfan.GomokuAi.MM(r0, r13);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final com.xiaofan.bangfan.GomokuAi.MM minimax(int[][] r25, int r26, long r27, long r29, boolean r31, int r32) {
        /*
            Method dump skipped, instructions count: 360
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaofan.bangfan.GomokuAi.minimax(int[][], int, long, long, boolean, int):com.xiaofan.bangfan.GomokuAi$MM");
    }

    public final Candidate bestMove(int[][] b, int aiPlayer) {
        Intrinsics.checkNotNullParameter(b, "b");
        int[][] copy = new int[15];
        for (int i = 0; i < 15; i++) {
            int[] iArr = b[i];
            int[] copyOf = Arrays.copyOf(iArr, iArr.length);
            Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(...)");
            copy[i] = copyOf;
        }
        int stones = 0;
        for (int y = 0; y < 15; y++) {
            for (int x = 0; x < 15; x++) {
                if (copy[y][x] != 0) {
                    stones++;
                }
            }
        }
        int depth = stones > 10 ? 3 : 2;
        if (stones > 25) {
            depth = 4;
        }
        MM r = minimax(copy, depth, Long.MIN_VALUE, Long.MAX_VALUE, true, aiPlayer);
        Candidate it = r.getMove();
        if (it != null) {
            return it;
        }
        List scored = scoreCandidates(copy, getCandidates(copy, 2), aiPlayer);
        return (Candidate) CollectionsKt.first((List<? extends Object>) scored);
    }
}
