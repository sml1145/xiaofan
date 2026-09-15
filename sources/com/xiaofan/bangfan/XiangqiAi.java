package com.xiaofan.bangfan;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.xiaofan.bangfan.XiangqiGame;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.DurationKt;
/* compiled from: XiangqiAi.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0004J\u0010\u0010\u000f\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0018\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0004H\u0002J@\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0007H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/xiaofan/bangfan/XiangqiAi;", "", "()V", "MAX_DEPTH", "", "MAX_NODES", "TIME_BUDGET_MS", "", "VALUE", "", "bestMove", "Lcom/xiaofan/bangfan/XiangqiGame$Move;", "src", "Lcom/xiaofan/bangfan/XiangqiGame;", "color", "clone", "evaluate", "g", "negamax", "depth", "aIn", "bIn", "nodes", "Ljava/util/concurrent/atomic/AtomicInteger;", "deadline", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class XiangqiAi {
    private static final int MAX_DEPTH = 3;
    private static final int MAX_NODES = 120000;
    private static final long TIME_BUDGET_MS = 2600;
    public static final XiangqiAi INSTANCE = new XiangqiAi();
    private static final int[] VALUE = {0, 900, 400, 210, 210, 60000, 450, 100};

    private XiangqiAi() {
    }

    public final XiangqiGame.Move bestMove(XiangqiGame src, int color) {
        int i = color;
        Intrinsics.checkNotNullParameter(src, "src");
        final XiangqiGame g = clone(src);
        long deadline = System.currentTimeMillis() + TIME_BUDGET_MS;
        AtomicInteger nodes = new AtomicInteger(0);
        Iterable $this$sortedByDescending$iv = g.legalMoves(i);
        List sortedWith = CollectionsKt.sortedWith($this$sortedByDescending$iv, new Comparator() { // from class: com.xiaofan.bangfan.XiangqiAi$bestMove$$inlined$sortedByDescending$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                XiangqiGame.Move it = (XiangqiGame.Move) t2;
                XiangqiGame.Move it2 = (XiangqiGame.Move) t;
                return ComparisonsKt.compareValues(Integer.valueOf(Math.abs(XiangqiGame.this.getBoard()[it.getTr()][it.getTc()])), Integer.valueOf(Math.abs(XiangqiGame.this.getBoard()[it2.getTr()][it2.getTc()])));
            }
        });
        if (sortedWith.isEmpty()) {
            return null;
        }
        List<XiangqiGame.Move> shuffled = CollectionsKt.shuffled(sortedWith);
        XiangqiGame.Move best = null;
        int bestScore = Integer.MIN_VALUE;
        int alpha = -1000000;
        for (XiangqiGame.Move m : shuffled) {
            int cap = g.apply(m);
            int bestScore2 = bestScore;
            int alpha2 = alpha;
            int score = -negamax(g, -i, 2, -DurationKt.NANOS_IN_MILLIS, -alpha, nodes, deadline);
            g.undo(m, cap);
            if (score > bestScore2) {
                bestScore = score;
                best = m;
            } else {
                bestScore = bestScore2;
            }
            alpha = Math.max(alpha2, score);
            if (System.currentTimeMillis() > deadline) {
                break;
            }
            i = color;
        }
        return best == null ? (XiangqiGame.Move) CollectionsKt.firstOrNull((List<? extends Object>) shuffled) : best;
    }

    private final int negamax(final XiangqiGame g, int color, int depth, int aIn, int bIn, AtomicInteger nodes, long deadline) {
        if (nodes.incrementAndGet() > MAX_NODES || System.currentTimeMillis() > deadline) {
            return evaluate(g, color);
        }
        List moves = g.legalMoves(color);
        if (moves.isEmpty()) {
            return (-90000) - depth;
        }
        if (depth == 0) {
            return evaluate(g, color);
        }
        List $this$sortedByDescending$iv = moves;
        List<XiangqiGame.Move> ordered = CollectionsKt.sortedWith($this$sortedByDescending$iv, new Comparator() { // from class: com.xiaofan.bangfan.XiangqiAi$negamax$$inlined$sortedByDescending$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                XiangqiGame.Move it = (XiangqiGame.Move) t2;
                XiangqiGame.Move it2 = (XiangqiGame.Move) t;
                return ComparisonsKt.compareValues(Integer.valueOf(Math.abs(XiangqiGame.this.getBoard()[it.getTr()][it.getTc()])), Integer.valueOf(Math.abs(XiangqiGame.this.getBoard()[it2.getTr()][it2.getTc()])));
            }
        });
        int alpha = aIn;
        int best = Integer.MIN_VALUE;
        for (XiangqiGame.Move m : ordered) {
            int cap = g.apply(m);
            List moves2 = moves;
            List ordered2 = ordered;
            int s = -negamax(g, -color, depth - 1, -bIn, -alpha, nodes, deadline);
            g.undo(m, cap);
            if (s > best) {
                best = s;
            }
            alpha = Math.max(alpha, best);
            if (alpha >= bIn) {
                break;
            }
            moves = moves2;
            ordered = ordered2;
        }
        return best;
    }

    private final int evaluate(XiangqiGame g, int color) {
        int red = 0;
        int black = 0;
        int r = 0;
        int rows = g.getRows();
        while (r < rows) {
            int cols = g.getCols();
            for (int c = 0; c < cols; c++) {
                int p = g.getBoard()[r][c];
                if (p != 0) {
                    int v = VALUE[Math.abs(p)];
                    if (Math.abs(p) == 7) {
                        boolean z = false;
                        boolean crossed = p <= 0 ? r >= 5 : r <= 4;
                        if (crossed) {
                            v += 35;
                        }
                        if (3 <= c && c < 6) {
                            z = true;
                        }
                        if (z) {
                            v += 6;
                        }
                    }
                    if (p > 0) {
                        red += v;
                    } else {
                        black += v;
                    }
                }
            }
            r++;
        }
        int r2 = red - black;
        return color == 1 ? r2 : -r2;
    }

    private final XiangqiGame clone(XiangqiGame src) {
        XiangqiGame g = new XiangqiGame();
        int rows = src.getRows();
        for (int r = 0; r < rows; r++) {
            int cols = src.getCols();
            for (int c = 0; c < cols; c++) {
                g.getBoard()[r][c] = src.getBoard()[r][c];
            }
        }
        return g;
    }
}
