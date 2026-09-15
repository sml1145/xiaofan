package com.xiaofan.bangfan;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: GoGame.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0015\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\r\u0018\u0000 <2\u00020\u0001:\u0001<B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001e\u0010#\u001a\u00020\u000f2\u0006\u0010$\u001a\u00020\u00032\u0006\u0010%\u001a\u00020\u00032\u0006\u0010&\u001a\u00020\u0003J\u0006\u0010'\u001a\u00020(J4\u0010)\u001a\u001e\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00070\u0014j\b\u0012\u0004\u0012\u00020\u0007`\u0016\u0012\u0004\u0012\u00020\u00030*2\u0006\u0010+\u001a\u00020\u00032\u0006\u0010,\u001a\u00020\u0003H\u0002J\u0016\u0010-\u001a\u00020\u000f2\u0006\u0010$\u001a\u00020\u00032\u0006\u0010%\u001a\u00020\u0003J\u0006\u0010.\u001a\u00020\u0003J\u001e\u0010/\u001a\b\u0012\u0004\u0012\u00020\u0007002\u0006\u0010$\u001a\u00020\u00032\u0006\u0010%\u001a\u00020\u0003H\u0002J\u000e\u00101\u001a\u00020\u00032\u0006\u0010&\u001a\u00020\u0003J\u0006\u00102\u001a\u00020(J\u0016\u00103\u001a\u00020\u000f2\u0006\u0010$\u001a\u00020\u00032\u0006\u0010%\u001a\u00020\u0003J\u0006\u00104\u001a\u00020\u0015J\b\u00105\u001a\u00020(H\u0002J\u0006\u00106\u001a\u00020(J\u0006\u00107\u001a\u00020(J\u0006\u00108\u001a\u00020\u0007J\u0006\u00109\u001a\u00020(J\b\u0010:\u001a\u00020(H\u0002J\u0006\u0010;\u001a\u00020(R\u0019\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0011\u0010\u000b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001e\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R!\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00020\u00150\u0014j\b\u0012\u0004\u0012\u00020\u0015`\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\"\u0010\u0019\u001a\u0004\u0018\u00010\u00072\b\u0010\u000e\u001a\u0004\u0018\u00010\u0007@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\rR%\u0010\u001b\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0014j\n\u0012\u0006\u0012\u0004\u0018\u00010\u0007`\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0018R\u001e\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0003@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001fR\u001e\u0010!\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0003@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001f¨\u0006="}, d2 = {"Lcom/xiaofan/bangfan/GoGame;", "", "size", "", "(I)V", "board", "", "", "getBoard", "()[[I", "[[I", "captures", "getCaptures", "()[I", "<set-?>", "", "gameOver", "getGameOver", "()Z", "history", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "getHistory", "()Ljava/util/ArrayList;", "lastMove", "getLastMove", "moveLog", "getMoveLog", "passes", "getPasses", "()I", "getSize", XiaoFanBrain.INTENT_TURN, "getTurn", "canPlace", "r", "c", "color", "endGame", "", "groupInfo", "Lkotlin/Pair;", "sr", "sc", "inside", "moveCount", "neighbors", "", "opponent", "pass", "place", "positionKey", "rebuildFromLog", "reset", "resumeGame", "score", "undoLast", "undoOne", "undoTwo", "Companion", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class GoGame {
    public static final int BLACK = 1;
    public static final Companion Companion = new Companion(null);
    public static final int WHITE = 2;
    private final int[][] board;
    private final int[] captures;
    private boolean gameOver;
    private final ArrayList<String> history;
    private int[] lastMove;
    private final ArrayList<int[]> moveLog;
    private int passes;
    private final int size;
    private int turn;

    public GoGame() {
        this(0, 1, null);
    }

    public GoGame(int size) {
        this.size = size;
        int i = this.size;
        int[][] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = new int[this.size];
        }
        this.board = iArr;
        this.turn = 1;
        this.captures = new int[]{0, 0, 0};
        this.history = new ArrayList<>();
        this.moveLog = new ArrayList<>();
        reset();
    }

    public /* synthetic */ GoGame(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 9 : i);
    }

    public final int getSize() {
        return this.size;
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

    public final int getPasses() {
        return this.passes;
    }

    public final int[] getLastMove() {
        return this.lastMove;
    }

    public final int[] getCaptures() {
        return this.captures;
    }

    public final ArrayList<String> getHistory() {
        return this.history;
    }

    public final ArrayList<int[]> getMoveLog() {
        return this.moveLog;
    }

    public final void reset() {
        int i = this.size;
        for (int r = 0; r < i; r++) {
            int i2 = this.size;
            for (int c = 0; c < i2; c++) {
                this.board[r][c] = 0;
            }
        }
        this.turn = 1;
        this.gameOver = false;
        this.passes = 0;
        this.lastMove = null;
        this.captures[1] = 0;
        this.captures[2] = 0;
        this.history.clear();
        this.moveLog.clear();
        this.history.add(positionKey());
    }

    public final boolean inside(int r, int c) {
        if (r >= 0 && r < this.size) {
            if (c >= 0 && c < this.size) {
                return true;
            }
        }
        return false;
    }

    public final int opponent(int color) {
        return color == 1 ? 2 : 1;
    }

    private final List<int[]> neighbors(int r, int c) {
        ArrayList l = new ArrayList(4);
        if (r > 0) {
            l.add(new int[]{r - 1, c});
        }
        if (r < this.size - 1) {
            l.add(new int[]{r + 1, c});
        }
        if (c > 0) {
            l.add(new int[]{r, c - 1});
        }
        if (c < this.size - 1) {
            l.add(new int[]{r, c + 1});
        }
        return l;
    }

    private final Pair<ArrayList<int[]>, Integer> groupInfo(int sr, int sc) {
        int color = this.board[sr][sc];
        ArrayList stones = new ArrayList();
        HashSet liberties = new HashSet();
        HashSet seen = new HashSet();
        ArrayDeque stack = new ArrayDeque();
        stack.addLast(new int[]{sr, sc});
        while (!stack.isEmpty()) {
            int[] cur = (int[]) stack.removeLast();
            char c = 0;
            long key = (cur[0] * this.size) + cur[1];
            if (seen.add(Long.valueOf(key))) {
                stones.add(cur);
                for (int[] n : neighbors(cur[0], cur[1])) {
                    int v = this.board[n[c]][n[1]];
                    if (v == 0) {
                        liberties.add(Long.valueOf((n[c] * this.size) + n[1]));
                        key = key;
                        c = 0;
                    } else {
                        long key2 = key;
                        if (v == color) {
                            c = 0;
                            if (!seen.contains(Long.valueOf((n[0] * this.size) + n[1]))) {
                                stack.addLast(n);
                                key = key2;
                            }
                        } else {
                            c = 0;
                        }
                        key = key2;
                    }
                }
            }
        }
        return TuplesKt.to(stones, Integer.valueOf(liberties.size()));
    }

    public final String positionKey() {
        StringBuilder sb = new StringBuilder(this.size * this.size);
        int i = this.size;
        for (int r = 0; r < i; r++) {
            int i2 = this.size;
            for (int c = 0; c < i2; c++) {
                sb.append(this.board[r][c]);
            }
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }

    public final boolean canPlace(int r, int c, int color) {
        if (!this.gameOver && inside(r, c) && this.board[r][c] == 0) {
            int i = this.size;
            int[][] snap = new int[i];
            for (int i2 = 0; i2 < i; i2++) {
                int[] iArr = this.board[i2];
                int[] copyOf = Arrays.copyOf(iArr, iArr.length);
                Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(...)");
                snap[i2] = copyOf;
            }
            this.board[r][c] = color;
            int opp = opponent(color);
            HashSet checked = new HashSet();
            for (int[] n : neighbors(r, c)) {
                if (this.board[n[0]][n[1]] == opp && checked.add(Long.valueOf((n[0] * this.size) + n[1]))) {
                    Pair<ArrayList<int[]>, Integer> groupInfo = groupInfo(n[0], n[1]);
                    ArrayList stones = groupInfo.component1();
                    int libs = groupInfo.component2().intValue();
                    if (libs == 0) {
                        Iterator<int[]> it = stones.iterator();
                        while (it.hasNext()) {
                            int[] s = it.next();
                            this.board[s[0]][s[1]] = 0;
                        }
                    }
                }
            }
            int ownLibs = groupInfo(r, c).component2().intValue();
            boolean legal = ownLibs > 0;
            String key = legal ? positionKey() : "";
            int i3 = this.size;
            for (int i4 = 0; i4 < i3; i4++) {
                int i5 = this.size;
                for (int j = 0; j < i5; j++) {
                    this.board[i4][j] = snap[i4][j];
                }
            }
            if (legal) {
                return !this.history.contains(key);
            }
            return false;
        }
        return false;
    }

    public final boolean place(int r, int c) {
        int color = this.turn;
        if (canPlace(r, c, color)) {
            this.board[r][c] = color;
            int opp = opponent(color);
            HashSet checked = new HashSet();
            int capCount = 0;
            for (int[] n : neighbors(r, c)) {
                if (this.board[n[0]][n[1]] == opp) {
                    long k = (n[0] * this.size) + n[1];
                    if (checked.add(Long.valueOf(k))) {
                        Pair<ArrayList<int[]>, Integer> groupInfo = groupInfo(n[0], n[1]);
                        ArrayList stones = groupInfo.component1();
                        int libs = groupInfo.component2().intValue();
                        if (libs == 0) {
                            Iterator<int[]> it = stones.iterator();
                            while (it.hasNext()) {
                                int[] s = it.next();
                                this.board[s[0]][s[1]] = 0;
                                capCount++;
                            }
                        }
                    }
                }
            }
            int[] iArr = this.captures;
            iArr[opp] = iArr[opp] + capCount;
            this.lastMove = new int[]{r, c};
            this.passes = 0;
            this.moveLog.add(new int[]{r, c});
            this.history.add(positionKey());
            this.turn = opp;
            return true;
        }
        return false;
    }

    public final void pass() {
        if (this.gameOver) {
            return;
        }
        this.passes++;
        this.lastMove = null;
        this.moveLog.add(null);
        this.turn = opponent(this.turn);
        if (this.passes >= 2) {
            this.gameOver = true;
        }
    }

    public final void endGame() {
        this.gameOver = true;
    }

    public final void resumeGame() {
        this.gameOver = false;
        this.passes = 0;
    }

    public final void undoLast() {
        if (this.moveLog.isEmpty()) {
            return;
        }
        undoOne();
    }

    private final void undoOne() {
        if (this.moveLog.isEmpty()) {
            return;
        }
        this.moveLog.remove(this.moveLog.size() - 1);
        rebuildFromLog();
    }

    public final void undoTwo() {
        if (this.moveLog.size() >= 2) {
            undoOne();
        } else if (this.moveLog.size() != 1) {
            return;
        }
        undoOne();
    }

    private final void rebuildFromLog() {
        int i;
        int r = 0;
        int i2 = this.size;
        while (true) {
            i = 0;
            if (r >= i2) {
                break;
            }
            int i3 = this.size;
            for (int c = 0; c < i3; c++) {
                this.board[r][c] = 0;
            }
            r++;
        }
        this.captures[1] = 0;
        this.captures[2] = 0;
        this.history.clear();
        int current = 1;
        this.history.add(positionKey());
        Iterator<int[]> it = this.moveLog.iterator();
        while (it.hasNext()) {
            int[] m = it.next();
            if (m == null) {
                current = opponent(current);
            } else {
                this.board[m[0]][m[1]] = current;
                int opp = opponent(current);
                HashSet checked = new HashSet();
                for (int[] n : neighbors(m[0], m[1])) {
                    if (this.board[n[0]][n[1]] == opp && checked.add(Long.valueOf((n[0] * this.size) + n[1]))) {
                        Pair<ArrayList<int[]>, Integer> groupInfo = groupInfo(n[0], n[1]);
                        ArrayList stones = groupInfo.component1();
                        int libs = groupInfo.component2().intValue();
                        if (libs == 0) {
                            Iterator<int[]> it2 = stones.iterator();
                            while (it2.hasNext()) {
                                int[] s = it2.next();
                                this.board[s[0]][s[1]] = 0;
                                int[] iArr = this.captures;
                                iArr[opp] = iArr[opp] + 1;
                            }
                        }
                    }
                }
                this.history.add(positionKey());
                current = opponent(current);
            }
        }
        this.turn = current;
        this.lastMove = (int[]) CollectionsKt.lastOrNull((List<? extends Object>) this.moveLog);
        this.gameOver = false;
        Iterable $this$count$iv = CollectionsKt.takeLast(this.moveLog, 2);
        if (!($this$count$iv instanceof Collection) || !((Collection) $this$count$iv).isEmpty()) {
            int count$iv = 0;
            for (Object element$iv : $this$count$iv) {
                int[] it3 = (int[]) element$iv;
                int[] it4 = it3 == null ? 1 : null;
                if (it4 != null && (count$iv = count$iv + 1) < 0) {
                    CollectionsKt.throwCountOverflow();
                }
            }
            i = count$iv;
        }
        this.passes = i;
    }

    public final int[] score() {
        boolean z;
        int i;
        int i2;
        boolean z2;
        int black = 0;
        int white = 0;
        int r = 0;
        int i3 = this.size;
        while (true) {
            z = true;
            if (r >= i3) {
                break;
            }
            int i4 = this.size;
            for (int c = 0; c < i4; c++) {
                if (this.board[r][c] == 1) {
                    black++;
                } else if (this.board[r][c] == 2) {
                    white++;
                }
            }
            r++;
        }
        HashSet seen = new HashSet();
        int r2 = 0;
        int i5 = this.size;
        while (r2 < i5) {
            int c2 = 0;
            int i6 = this.size;
            while (c2 < i6) {
                long key = (r2 * this.size) + c2;
                if (this.board[r2][c2] != 0) {
                    i = i5;
                    i2 = i6;
                    z2 = z;
                } else if (seen.add(Long.valueOf(key))) {
                    ArrayList region = new ArrayList();
                    HashSet border = new HashSet();
                    ArrayDeque stack = new ArrayDeque();
                    stack.addLast(new int[]{r2, c2});
                    while (stack.isEmpty() ^ z) {
                        int[] cur = (int[]) stack.removeLast();
                        int i7 = i5;
                        int i8 = i6;
                        long key2 = key;
                        long k2 = (cur[0] * this.size) + cur[1];
                        if (this.board[cur[0]][cur[1]] != 0) {
                            border.add(Integer.valueOf(this.board[cur[0]][cur[1]]));
                            i5 = i7;
                            i6 = i8;
                            key = key2;
                            z = true;
                        } else if (!seen.add(Long.valueOf(k2))) {
                            i5 = i7;
                            i6 = i8;
                            key = key2;
                            z = true;
                        } else {
                            region.add(cur);
                            for (int[] n : neighbors(cur[0], cur[1])) {
                                stack.addLast(n);
                            }
                            i5 = i7;
                            i6 = i8;
                            key = key2;
                            z = true;
                        }
                    }
                    i = i5;
                    i2 = i6;
                    z2 = true;
                    if (border.size() == 1) {
                        int owner = ((Number) CollectionsKt.first(border)).intValue();
                        int size = region.size();
                        if (owner == 1) {
                            black += size;
                        } else {
                            white += size;
                        }
                    }
                } else {
                    i = i5;
                    i2 = i6;
                    z2 = z;
                }
                c2++;
                z = z2;
                i5 = i;
                i6 = i2;
            }
            r2++;
            i5 = i5;
        }
        return new int[]{black, white};
    }

    public final int moveCount() {
        return this.moveLog.size();
    }

    /* compiled from: GoGame.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/xiaofan/bangfan/GoGame$Companion;", "", "()V", "BLACK", "", "WHITE", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
