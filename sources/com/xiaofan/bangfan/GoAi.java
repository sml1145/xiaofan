package com.xiaofan.bangfan;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArrayDeque;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
/* compiled from: GoAi.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0002J \u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\bH\u0002J&\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u00112\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\bH\u0002¨\u0006\u0014"}, d2 = {"Lcom/xiaofan/bangfan/GoAi;", "", "()V", "bestMove", "", "src", "Lcom/xiaofan/bangfan/GoGame;", "color", "", "copyInto", "", "dst", "groupLibs", "g", "sr", "sc", "trialNeighbors", "", "r", "c", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class GoAi {
    public static final GoAi INSTANCE = new GoAi();

    private GoAi() {
    }

    public final int[] bestMove(GoGame src, int color) {
        Object maxElem$iv;
        int opp;
        ArrayList list;
        boolean z;
        double lineScore;
        GoAi goAi = this;
        GoGame src2 = src;
        Intrinsics.checkNotNullParameter(src2, "src");
        char c = 1;
        int opp2 = color == 1 ? 2 : 1;
        ArrayList list2 = new ArrayList();
        int r = 0;
        int size = src.getSize();
        while (r < size) {
            int c2 = 0;
            int size2 = src.getSize();
            while (c2 < size2) {
                if (src.getBoard()[r][c2] != 0) {
                    opp = opp2;
                    list = list2;
                } else if (src2.canPlace(r, c2, color)) {
                    GoGame trial = new GoGame(src.getSize());
                    goAi.copyInto(src2, trial);
                    int before = trial.getCaptures()[opp2];
                    if (trial.place(r, c2)) {
                        int captured = trial.getCaptures()[opp2] - before;
                        int minOwnLib = 99;
                        Iterator<int[]> it = goAi.trialNeighbors(trial, r, c2).iterator();
                        while (true) {
                            z = false;
                            if (!it.hasNext()) {
                                break;
                            }
                            int[] n = it.next();
                            if (trial.getBoard()[n[0]][n[c]] == color) {
                                minOwnLib = Math.min(minOwnLib, goAi.groupLibs(trial, n[0], n[c]));
                            }
                        }
                        int minOwnLib2 = Math.min(minOwnLib, goAi.groupLibs(trial, r, c2));
                        int adjEnemy = 0;
                        int adjFriend = 0;
                        for (int[] n2 : goAi.trialNeighbors(trial, r, c2)) {
                            if (trial.getBoard()[n2[0]][n2[c]] == opp2) {
                                adjEnemy++;
                                c = 1;
                            } else if (trial.getBoard()[n2[0]][n2[1]] == color) {
                                adjFriend++;
                                c = 1;
                            } else {
                                c = 1;
                            }
                        }
                        int edgeDist = ComparisonsKt.minOf(r, c2, (src.getSize() - 1) - r, (src.getSize() - 1) - c2);
                        if (edgeDist == 0) {
                            lineScore = -2.0d;
                        } else if (edgeDist == 1) {
                            lineScore = 0.5d;
                        } else {
                            if (2 <= edgeDist && edgeDist < 4) {
                                z = true;
                            }
                            lineScore = z ? 2.0d : 1.0d;
                        }
                        opp = opp2;
                        double s = 0.0d + (captured * 40.0d) + (adjEnemy * 3.0d) + (adjFriend * 1.2d) + lineScore;
                        if (minOwnLib2 == 1) {
                            s -= 14.0d;
                        } else if (minOwnLib2 == 2) {
                            s -= 3.0d;
                        } else if (minOwnLib2 >= 4) {
                            s += 2.0d;
                        }
                        list = list2;
                        list.add(new GoAi$bestMove$Cand(r, c2, s + (Random.Default.nextDouble() * 0.6d)));
                    } else {
                        opp = opp2;
                        list = list2;
                    }
                } else {
                    opp = opp2;
                    list = list2;
                }
                c2++;
                goAi = this;
                src2 = src;
                list2 = list;
                opp2 = opp;
                c = 1;
            }
            r++;
            goAi = this;
            src2 = src;
            c = 1;
        }
        ArrayList list3 = list2;
        if (list3.isEmpty()) {
            return null;
        }
        ArrayList $this$maxByOrNull$iv = list3;
        Iterator iterator$iv = $this$maxByOrNull$iv.iterator();
        if (iterator$iv.hasNext()) {
            maxElem$iv = iterator$iv.next();
            if (iterator$iv.hasNext()) {
                GoAi$bestMove$Cand it2 = (GoAi$bestMove$Cand) maxElem$iv;
                double maxValue$iv = it2.getScore();
                do {
                    Object e$iv = iterator$iv.next();
                    GoAi$bestMove$Cand it3 = (GoAi$bestMove$Cand) e$iv;
                    double v$iv = it3.getScore();
                    if (Double.compare(maxValue$iv, v$iv) < 0) {
                        maxElem$iv = e$iv;
                        maxValue$iv = v$iv;
                    }
                } while (iterator$iv.hasNext());
            }
        } else {
            maxElem$iv = null;
        }
        GoAi$bestMove$Cand best = (GoAi$bestMove$Cand) maxElem$iv;
        if (best != null && best.getScore() >= -6.0d) {
            return new int[]{best.getR(), best.getC()};
        }
        return null;
    }

    private final void copyInto(GoGame src, GoGame dst) {
        int size = src.getSize();
        for (int r = 0; r < size; r++) {
            int size2 = src.getSize();
            for (int c = 0; c < size2; c++) {
                dst.getBoard()[r][c] = src.getBoard()[r][c];
            }
        }
        dst.getHistory().clear();
        dst.getHistory().addAll(src.getHistory());
    }

    private final List<int[]> trialNeighbors(GoGame g, int r, int c) {
        ArrayList l = new ArrayList(4);
        if (r > 0) {
            l.add(new int[]{r - 1, c});
        }
        if (r < g.getSize() - 1) {
            l.add(new int[]{r + 1, c});
        }
        if (c > 0) {
            l.add(new int[]{r, c - 1});
        }
        if (c < g.getSize() - 1) {
            l.add(new int[]{r, c + 1});
        }
        return l;
    }

    private final int groupLibs(GoGame g, int sr, int sc) {
        int color = g.getBoard()[sr][sc];
        HashSet seen = new HashSet();
        HashSet libs = new HashSet();
        ArrayDeque stack = new ArrayDeque();
        stack.addLast(new int[]{sr, sc});
        while (!stack.isEmpty()) {
            int[] cur = (int[]) stack.removeLast();
            if (seen.add(Long.valueOf((cur[0] * g.getSize()) + cur[1]))) {
                for (int[] n : trialNeighbors(g, cur[0], cur[1])) {
                    int v = g.getBoard()[n[0]][n[1]];
                    if (v == 0) {
                        libs.add(Long.valueOf((n[0] * g.getSize()) + n[1]));
                    } else if (v == color) {
                        stack.addLast(n);
                    }
                }
            }
        }
        return libs.size();
    }
}
