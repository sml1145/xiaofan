package com.xiaofan.bangfan;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: GomokuGame.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 +2\u00020\u0001:\u0002+,B\u0005¢\u0006\u0002\u0010\u0002J&\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00192\u0006\u0010 \u001a\u00020\n2\u0006\u0010!\u001a\u00020\n2\u0006\u0010\"\u001a\u00020\nJ\u0011\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u0010\bJ\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00140\u0019J\u0006\u0010%\u001a\u00020\nJ\u001e\u0010&\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\n2\u0006\u0010!\u001a\u00020\n2\u0006\u0010\"\u001a\u00020\nJ\u0006\u0010'\u001a\u00020(J\u000e\u0010)\u001a\u00020(2\u0006\u0010\"\u001a\u00020\nJ\b\u0010*\u001a\u0004\u0018\u00010\u0014R,\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004@BX\u0086\u000e¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u001e\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\n@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001e\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u00140\u0013j\b\u0012\u0004\u0012\u00020\u0014`\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0016\u001a\u0004\u0018\u00010\u00142\b\u0010\u0003\u001a\u0004\u0018\u00010\u0014@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R.\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00192\u000e\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0019@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u001e\u0010\u001d\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\n@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\r¨\u0006-"}, d2 = {"Lcom/xiaofan/bangfan/GomokuGame;", "", "()V", "<set-?>", "", "", "board", "getBoard", "()[[I", "[[I", "", "currentPlayer", "getCurrentPlayer", "()I", "", "gameOver", "getGameOver", "()Z", "history", "Ljava/util/ArrayList;", "Lcom/xiaofan/bangfan/GomokuGame$Move;", "Lkotlin/collections/ArrayList;", "lastMove", "getLastMove", "()Lcom/xiaofan/bangfan/GomokuGame$Move;", "", "winLine", "getWinLine", "()Ljava/util/List;", "winner", "getWinner", "checkWin", "x", "y", "player", "copyBoard", "historyCopy", "moveCount", "placeStone", "reset", "", "resign", "undo", "Companion", "Move", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class GomokuGame {
    public static final int BLACK = 1;
    public static final Companion Companion = new Companion(null);
    private static final int[][] DIRS = {new int[]{1, 0}, new int[]{0, 1}, new int[]{1, 1}, new int[]{1, -1}};
    public static final int EMPTY = 0;
    public static final int SIZE = 15;
    public static final int WHITE = 2;
    private int[][] board;
    private int currentPlayer;
    private boolean gameOver;
    private final ArrayList<Move> history;
    private Move lastMove;
    private List<int[]> winLine;
    private int winner;

    /* compiled from: GomokuGame.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0015\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0019\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/xiaofan/bangfan/GomokuGame$Companion;", "", "()V", "BLACK", "", "DIRS", "", "", "getDIRS", "()[[I", "[[I", "EMPTY", "SIZE", "WHITE", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int[][] getDIRS() {
            return GomokuGame.DIRS;
        }
    }

    public GomokuGame() {
        int[][] iArr = new int[15];
        for (int i = 0; i < 15; i++) {
            iArr[i] = new int[15];
        }
        this.board = iArr;
        this.history = new ArrayList<>();
        this.currentPlayer = 1;
        this.winner = -1;
    }

    /* compiled from: GomokuGame.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/xiaofan/bangfan/GomokuGame$Move;", "", "x", "", "y", "player", "(III)V", "getPlayer", "()I", "getX", "getY", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class Move {
        private final int player;
        private final int x;
        private final int y;

        public static /* synthetic */ Move copy$default(Move move, int i, int i2, int i3, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                i = move.x;
            }
            if ((i4 & 2) != 0) {
                i2 = move.y;
            }
            if ((i4 & 4) != 0) {
                i3 = move.player;
            }
            return move.copy(i, i2, i3);
        }

        public final int component1() {
            return this.x;
        }

        public final int component2() {
            return this.y;
        }

        public final int component3() {
            return this.player;
        }

        public final Move copy(int i, int i2, int i3) {
            return new Move(i, i2, i3);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Move) {
                Move move = (Move) obj;
                return this.x == move.x && this.y == move.y && this.player == move.player;
            }
            return false;
        }

        public int hashCode() {
            return (((Integer.hashCode(this.x) * 31) + Integer.hashCode(this.y)) * 31) + Integer.hashCode(this.player);
        }

        public String toString() {
            int i = this.x;
            int i2 = this.y;
            return "Move(x=" + i + ", y=" + i2 + ", player=" + this.player + ")";
        }

        public Move(int x, int y, int player) {
            this.x = x;
            this.y = y;
            this.player = player;
        }

        public final int getPlayer() {
            return this.player;
        }

        public final int getX() {
            return this.x;
        }

        public final int getY() {
            return this.y;
        }
    }

    public final int[][] getBoard() {
        return this.board;
    }

    public final int getCurrentPlayer() {
        return this.currentPlayer;
    }

    public final boolean getGameOver() {
        return this.gameOver;
    }

    public final int getWinner() {
        return this.winner;
    }

    public final List<int[]> getWinLine() {
        return this.winLine;
    }

    public final Move getLastMove() {
        return this.lastMove;
    }

    public final void reset() {
        int[][] iArr = new int[15];
        for (int i = 0; i < 15; i++) {
            iArr[i] = new int[15];
        }
        this.board = iArr;
        this.history.clear();
        this.currentPlayer = 1;
        this.gameOver = false;
        this.winner = -1;
        this.winLine = null;
        this.lastMove = null;
    }

    public final int moveCount() {
        return this.history.size();
    }

    public final List<Move> historyCopy() {
        return new ArrayList(this.history);
    }

    public final boolean placeStone(int x, int y, int player) {
        if (!this.gameOver && x >= 0 && x < 15 && y >= 0 && y < 15 && this.board[y][x] == 0 && player == this.currentPlayer) {
            this.board[y][x] = player;
            Move mv = new Move(x, y, player);
            this.lastMove = mv;
            this.history.add(mv);
            List line = checkWin(x, y, player);
            if (line != null) {
                this.gameOver = true;
                this.winner = player;
                this.winLine = line;
                return true;
            } else if (this.history.size() >= 225) {
                this.gameOver = true;
                this.winner = 0;
                return true;
            } else {
                this.currentPlayer = player == 1 ? 2 : 1;
                return true;
            }
        }
        return false;
    }

    public final List<int[]> checkWin(int x, int y, int player) {
        int[][] iArr;
        for (int[] iArr2 : DIRS) {
            int dx = iArr2[0];
            int dy = iArr2[1];
            ArrayList line = new ArrayList();
            line.add(new int[]{x, y});
            for (int i = 1; i < 5; i++) {
                int nx = x + (dx * i);
                int ny = y + (dy * i);
                if (nx < 0 || nx >= 15 || ny < 0 || ny >= 15 || this.board[ny][nx] != player) {
                    break;
                }
                line.add(new int[]{nx, ny});
            }
            for (int i2 = 1; i2 < 5; i2++) {
                int nx2 = x - (dx * i2);
                int ny2 = y - (dy * i2);
                if (nx2 < 0 || nx2 >= 15 || ny2 < 0 || ny2 >= 15 || this.board[ny2][nx2] != player) {
                    break;
                }
                line.add(0, new int[]{nx2, ny2});
            }
            if (line.size() >= 5) {
                return CollectionsKt.take(line, 5);
            }
        }
        return null;
    }

    public final Move undo() {
        if (this.history.isEmpty() || this.gameOver) {
            return null;
        }
        Move remove = this.history.remove(this.history.size() - 1);
        Intrinsics.checkNotNullExpressionValue(remove, "removeAt(...)");
        Move last = remove;
        this.board[last.getY()][last.getX()] = 0;
        this.currentPlayer = last.getPlayer();
        this.lastMove = this.history.isEmpty() ^ true ? this.history.get(this.history.size() - 1) : null;
        return last;
    }

    public final void resign(int player) {
        if (this.gameOver) {
            return;
        }
        this.gameOver = true;
        this.winner = player == 1 ? 2 : 1;
    }

    public final int[][] copyBoard() {
        int[][] iArr = new int[15];
        for (int i = 0; i < 15; i++) {
            int[] iArr2 = this.board[i];
            int[] copyOf = Arrays.copyOf(iArr2, iArr2.length);
            Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(...)");
            iArr[i] = copyOf;
        }
        return iArr;
    }
}
