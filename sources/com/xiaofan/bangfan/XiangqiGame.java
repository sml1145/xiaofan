package com.xiaofan.bangfan;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: XiangqiGame.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010 \n\u0002\b\u0013\u0018\u0000 =2\u00020\u0001:\u0002=>B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u001d\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\u001fJ\b\u0010 \u001a\u00020!H\u0002J\u000e\u0010\"\u001a\u00020\n2\u0006\u0010#\u001a\u00020\nJ\u0010\u0010$\u001a\u0004\u0018\u00010\u00052\u0006\u0010%\u001a\u00020\nJ\u000e\u0010&\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020\nJ\u0016\u0010'\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020\n2\u0006\u0010)\u001a\u00020\nJ\u0006\u0010*\u001a\u00020\u000eJ\u0014\u0010+\u001a\b\u0012\u0004\u0012\u00020\u001f0,2\u0006\u0010%\u001a\u00020\nJ&\u0010-\u001a\u00020\u000e2\u0006\u0010.\u001a\u00020\n2\u0006\u0010/\u001a\u00020\n2\u0006\u00100\u001a\u00020\n2\u0006\u00101\u001a\u00020\nJ\u0006\u00102\u001a\u00020\nJ8\u00103\u001a\u00020!2\u0006\u0010(\u001a\u00020\n2\u0006\u0010)\u001a\u00020\n2\u0006\u00104\u001a\u00020\n2\u0016\u00105\u001a\u0012\u0012\u0004\u0012\u00020\u001f0\u0013j\b\u0012\u0004\u0012\u00020\u001f`\u0014H\u0002J\u0014\u00106\u001a\b\u0012\u0004\u0012\u00020\u001f0,2\u0006\u0010%\u001a\u00020\nJ\u0006\u00107\u001a\u00020!J\u001e\u00108\u001a\u00020\u000e2\u0006\u00100\u001a\u00020\n2\u0006\u00101\u001a\u00020\n2\u0006\u00109\u001a\u00020\nJ\u0016\u0010:\u001a\u00020!2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010;\u001a\u00020\nJ\u0006\u0010<\u001a\u00020!R\u0019\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\t\u001a\u00020\nX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001e\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R!\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0013j\b\u0012\u0004\u0012\u00020\u0005`\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\nX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\fR\u001e\u0010\u0019\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\n@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\fR\u001e\u0010\u001b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\n@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\f¨\u0006?"}, d2 = {"Lcom/xiaofan/bangfan/XiangqiGame;", "", "()V", "board", "", "", "getBoard", "()[[I", "[[I", "cols", "", "getCols", "()I", "<set-?>", "", "gameOver", "getGameOver", "()Z", "history", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getHistory", "()Ljava/util/ArrayList;", "rows", "getRows", XiaoFanBrain.INTENT_TURN, "getTurn", "winner", "getWinner", "apply", "m", "Lcom/xiaofan/bangfan/XiangqiGame$Move;", "checkGameEnd", "", "colorOf", "piece", "findKing", "color", "inCheck", "inside", "r", "c", "kingsFacing", "legalMoves", "", "move", "fr", "fc", "tr", "tc", "moveCount", "pieceMoves", "p", "list", "pseudoMoves", "reset", "squareAttacked", "byColor", "undo", "cap", "undoLast", "Companion", "Move", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class XiangqiGame {
    public static final int A = 4;
    public static final int B = 3;
    public static final int BLACK = -1;
    public static final int C = 6;
    public static final Companion Companion = new Companion(null);
    public static final int K = 5;
    public static final int N = 2;
    public static final int P = 7;
    public static final int R = 1;
    public static final int RED = 1;
    private final int[][] board;
    private boolean gameOver;
    private final ArrayList<int[]> history;
    private int turn;
    private int winner;
    private final int rows = 10;
    private final int cols = 9;

    public XiangqiGame() {
        int i = this.rows;
        int[][] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = new int[this.cols];
        }
        this.board = iArr;
        this.turn = 1;
        this.history = new ArrayList<>();
        reset();
    }

    public final int getRows() {
        return this.rows;
    }

    public final int getCols() {
        return this.cols;
    }

    public final int[][] getBoard() {
        return this.board;
    }

    public final int getTurn() {
        return this.turn;
    }

    public final boolean getGameOver() {
        return this.gameOver;
    }

    public final int getWinner() {
        return this.winner;
    }

    public final ArrayList<int[]> getHistory() {
        return this.history;
    }

    /* compiled from: XiangqiGame.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J1\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0018"}, d2 = {"Lcom/xiaofan/bangfan/XiangqiGame$Move;", "", "fr", "", "fc", "tr", "tc", "(IIII)V", "getFc", "()I", "getFr", "getTc", "getTr", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class Move {
        private final int fc;
        private final int fr;
        private final int tc;
        private final int tr;

        public static /* synthetic */ Move copy$default(Move move, int i, int i2, int i3, int i4, int i5, Object obj) {
            if ((i5 & 1) != 0) {
                i = move.fr;
            }
            if ((i5 & 2) != 0) {
                i2 = move.fc;
            }
            if ((i5 & 4) != 0) {
                i3 = move.tr;
            }
            if ((i5 & 8) != 0) {
                i4 = move.tc;
            }
            return move.copy(i, i2, i3, i4);
        }

        public final int component1() {
            return this.fr;
        }

        public final int component2() {
            return this.fc;
        }

        public final int component3() {
            return this.tr;
        }

        public final int component4() {
            return this.tc;
        }

        public final Move copy(int i, int i2, int i3, int i4) {
            return new Move(i, i2, i3, i4);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Move) {
                Move move = (Move) obj;
                return this.fr == move.fr && this.fc == move.fc && this.tr == move.tr && this.tc == move.tc;
            }
            return false;
        }

        public int hashCode() {
            return (((((Integer.hashCode(this.fr) * 31) + Integer.hashCode(this.fc)) * 31) + Integer.hashCode(this.tr)) * 31) + Integer.hashCode(this.tc);
        }

        public String toString() {
            int i = this.fr;
            int i2 = this.fc;
            int i3 = this.tr;
            return "Move(fr=" + i + ", fc=" + i2 + ", tr=" + i3 + ", tc=" + this.tc + ")";
        }

        public Move(int fr, int fc, int tr, int tc) {
            this.fr = fr;
            this.fc = fc;
            this.tr = tr;
            this.tc = tc;
        }

        public final int getFc() {
            return this.fc;
        }

        public final int getFr() {
            return this.fr;
        }

        public final int getTc() {
            return this.tc;
        }

        public final int getTr() {
            return this.tr;
        }
    }

    public final void reset() {
        int i = this.rows;
        for (int r = 0; r < i; r++) {
            int i2 = this.cols;
            for (int c = 0; c < i2; c++) {
                this.board[r][c] = 0;
            }
        }
        int[] back = {1, 2, 3, 4, 5, 4, 3, 2, 1};
        for (int c2 = 0; c2 < 9; c2++) {
            this.board[0][c2] = -back[c2];
        }
        this.board[2][1] = -6;
        this.board[2][7] = -6;
        this.board[3][0] = -7;
        this.board[3][2] = -7;
        this.board[3][4] = -7;
        this.board[3][6] = -7;
        this.board[3][8] = -7;
        for (int c3 = 0; c3 < 9; c3++) {
            this.board[9][c3] = back[c3];
        }
        this.board[7][1] = 6;
        this.board[7][7] = 6;
        this.board[6][0] = 7;
        this.board[6][2] = 7;
        this.board[6][4] = 7;
        this.board[6][6] = 7;
        this.board[6][8] = 7;
        this.turn = 1;
        this.gameOver = false;
        this.winner = 0;
        this.history.clear();
    }

    public final int colorOf(int piece) {
        if (piece > 0) {
            return 1;
        }
        return piece < 0 ? -1 : 0;
    }

    public final boolean inside(int r, int c) {
        if (r >= 0 && r < this.rows) {
            if (c >= 0 && c < this.cols) {
                return true;
            }
        }
        return false;
    }

    public final List<Move> pseudoMoves(int color) {
        ArrayList list = new ArrayList();
        int i = this.rows;
        for (int r = 0; r < i; r++) {
            int i2 = this.cols;
            for (int c = 0; c < i2; c++) {
                int p = this.board[r][c];
                if (colorOf(p) == color) {
                    pieceMoves(r, c, p, list);
                }
            }
        }
        return list;
    }

    /* JADX WARN: Removed duplicated region for block: B:164:0x01ad A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void pieceMoves(int r21, int r22, int r23, java.util.ArrayList<com.xiaofan.bangfan.XiangqiGame.Move> r24) {
        /*
            Method dump skipped, instructions count: 792
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaofan.bangfan.XiangqiGame.pieceMoves(int, int, int, java.util.ArrayList):void");
    }

    private static final void pieceMoves$tryAdd(XiangqiGame this$0, int color, ArrayList<Move> arrayList, int $r, int $c, int tr, int tc) {
        if (this$0.inside(tr, tc)) {
            int tp = this$0.board[tr][tc];
            if (tp == 0 || this$0.colorOf(tp) != color) {
                arrayList.add(new Move($r, $c, tr, tc));
            }
        }
    }

    public final int[] findKing(int color) {
        int target = color * 5;
        int i = this.rows;
        for (int r = 0; r < i; r++) {
            int i2 = this.cols;
            for (int c = 0; c < i2; c++) {
                if (this.board[r][c] == target) {
                    return new int[]{r, c};
                }
            }
        }
        return null;
    }

    public final boolean kingsFacing() {
        int[] bk;
        int[] rk = findKing(1);
        if (rk == null || (bk = findKing(-1)) == null || rk[1] != bk[1]) {
            return false;
        }
        int lo = Math.min(rk[0], bk[0]) + 1;
        int hi = Math.max(rk[0], bk[0]) - 1;
        int r = lo;
        if (r <= hi) {
            while (this.board[r][rk[1]] == 0) {
                if (r != hi) {
                    r++;
                }
            }
            return false;
        }
        return true;
    }

    public final boolean inCheck(int color) {
        int[] k = findKing(color);
        if (k == null) {
            return true;
        }
        return squareAttacked(k[0], k[1], -color);
    }

    public final boolean squareAttacked(int tr, int tc, int byColor) {
        XiangqiGame xiangqiGame = this;
        char c = 0;
        char c2 = 1;
        int[][] dirs = {new int[]{-1, 0}, new int[]{1, 0}, new int[]{0, -1}, new int[]{0, 1}};
        for (int[] d : dirs) {
            int nr = d[0] + tr;
            int nc = tc + d[1];
            boolean screen = false;
            int nc2 = nc;
            while (true) {
                if (xiangqiGame.inside(nr, nc2)) {
                    int p = xiangqiGame.board[nr][nc2];
                    if (screen) {
                        if (p != 0) {
                            if (xiangqiGame.colorOf(p) == byColor && Math.abs(p) == 6) {
                                return true;
                            }
                        }
                    } else if (p == 0) {
                        continue;
                    } else if (xiangqiGame.colorOf(p) == byColor && Math.abs(p) == 1) {
                        return true;
                    } else {
                        screen = true;
                    }
                    nr += d[0];
                    nc2 += d[1];
                }
            }
        }
        int[][] knight = {new int[]{-2, -1, -1, 0}, new int[]{-2, 1, -1, 0}, new int[]{2, -1, 1, 0}, new int[]{2, 1, 1, 0}, new int[]{-1, -2, 0, -1}, new int[]{1, -2, 0, -1}, new int[]{-1, 2, 0, 1}, new int[]{1, 2, 0, 1}};
        for (int[] m : knight) {
            int fr = tr + m[0];
            int fc = tc + m[1];
            if (xiangqiGame.inside(fr, fc)) {
                int p2 = xiangqiGame.board[fr][fc];
                if (xiangqiGame.colorOf(p2) == byColor && Math.abs(p2) == 2 && xiangqiGame.board[tr + m[2]][tc + m[3]] == 0) {
                    return true;
                }
            }
        }
        int pawnForward = byColor == 1 ? 1 : -1;
        int[][] pawnSpots = byColor == 1 ? new int[][]{new int[]{tr + pawnForward, tc}, new int[]{tr, tc - 1}, new int[]{tr, tc + 1}} : new int[][]{new int[]{tr + pawnForward, tc}, new int[]{tr, tc - 1}, new int[]{tr, tc + 1}};
        for (int[] s : pawnSpots) {
            if (xiangqiGame.inside(s[0], s[1])) {
                int p3 = xiangqiGame.board[s[0]][s[1]];
                if (xiangqiGame.colorOf(p3) == byColor && Math.abs(p3) == 7) {
                    return true;
                }
            }
        }
        int[][] near = {new int[]{-1, 0}, new int[]{1, 0}, new int[]{0, -1}, new int[]{0, 1}, new int[]{-1, -1}, new int[]{-1, 1}, new int[]{1, -1}, new int[]{1, 1}};
        int length = near.length;
        int i = 0;
        while (i < length) {
            int[] s2 = near[i];
            int fr2 = s2[c] + tr;
            int fc2 = s2[c2] + tc;
            if (xiangqiGame.inside(fr2, fc2)) {
                int p4 = xiangqiGame.board[fr2][fc2];
                if (xiangqiGame.colorOf(p4) != byColor) {
                    c2 = 1;
                } else if (Math.abs(p4) == 5 || Math.abs(p4) == 4) {
                    return true;
                } else {
                    c2 = 1;
                }
            }
            i++;
            xiangqiGame = this;
            c = 0;
        }
        return false;
    }

    public final List<Move> legalMoves(int color) {
        ArrayList result = new ArrayList();
        for (Move m : pseudoMoves(color)) {
            int cap = apply(m);
            if (!inCheck(color) && !kingsFacing()) {
                result.add(m);
            }
            undo(m, cap);
        }
        return result;
    }

    public final int apply(Move m) {
        Intrinsics.checkNotNullParameter(m, "m");
        int cap = this.board[m.getTr()][m.getTc()];
        this.board[m.getTr()][m.getTc()] = this.board[m.getFr()][m.getFc()];
        this.board[m.getFr()][m.getFc()] = 0;
        return cap;
    }

    public final void undo(Move m, int cap) {
        Intrinsics.checkNotNullParameter(m, "m");
        this.board[m.getFr()][m.getFc()] = this.board[m.getTr()][m.getTc()];
        this.board[m.getTr()][m.getTc()] = cap;
    }

    public final boolean move(int fr, int fc, int tr, int tc) {
        Object element$iv;
        Move it;
        if (this.gameOver) {
            return false;
        }
        int p = this.board[fr][fc];
        if (colorOf(p) != this.turn) {
            return false;
        }
        Iterable $this$firstOrNull$iv = legalMoves(this.turn);
        Iterator<T> it2 = $this$firstOrNull$iv.iterator();
        while (true) {
            if (it2.hasNext()) {
                element$iv = it2.next();
                Move it3 = (Move) element$iv;
                if (it3.getFr() == fr && it3.getFc() == fc && it3.getTr() == tr && it3.getTc() == tc) {
                    it = 1;
                    continue;
                } else {
                    it = null;
                    continue;
                }
                if (it != null) {
                    break;
                }
            } else {
                element$iv = null;
                break;
            }
        }
        Move legal = (Move) element$iv;
        if (legal == null) {
            return false;
        }
        int cap = apply(legal);
        this.history.add(new int[]{fr, fc, tr, tc, cap});
        this.turn = -this.turn;
        checkGameEnd();
        return true;
    }

    private final void checkGameEnd() {
        List moves = legalMoves(this.turn);
        if (moves.isEmpty()) {
            this.gameOver = true;
            this.winner = -this.turn;
        }
    }

    public final void undoLast() {
        if (this.history.isEmpty()) {
            return;
        }
        int[] remove = this.history.remove(this.history.size() - 1);
        Intrinsics.checkNotNullExpressionValue(remove, "removeAt(...)");
        int[] last = remove;
        Move m = new Move(last[0], last[1], last[2], last[3]);
        undo(m, last[4]);
        this.turn = -this.turn;
        this.gameOver = false;
        this.winner = 0;
    }

    public final int moveCount() {
        return this.history.size();
    }

    /* compiled from: XiangqiGame.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/xiaofan/bangfan/XiangqiGame$Companion;", "", "()V", "A", "", "B", "BLACK", "C", "K", "N", "P", "R", "RED", "pieceText", "", "p", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String pieceText(int p) {
            if (p == 0) {
                return "";
            }
            char[] redMap = {' ', 36710, 39532, 30456, 20181, 24069, 28846, 20853};
            char[] blackMap = {' ', 36554, 39340, 35937, 22763, 23559, 30770, 21330};
            int t = Math.abs(p);
            return String.valueOf(p > 0 ? redMap[t] : blackMap[t]);
        }
    }
}
