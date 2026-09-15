package com.xiaofan.bangfan;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
/* compiled from: GoAi.kt */
@Metadata(d1 = {"\u0000)\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\u008a\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J,\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001¢\u0006\u0002\u0010\u0011J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"com/xiaofan/bangfan/GoAi$bestMove$Cand", "", "r", "", "c", "score", "", "(IID)V", "getC", "()I", "getR", "getScore", "()D", "component1", "component2", "component3", "copy", "(IID)Lcom/xiaofan/bangfan/GoAi$bestMove$Cand;", "equals", "", "other", "hashCode", "toString", "", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class GoAi$bestMove$Cand {
    private final int c;
    private final int r;
    private final double score;

    public static /* synthetic */ GoAi$bestMove$Cand copy$default(GoAi$bestMove$Cand goAi$bestMove$Cand, int i, int i2, double d, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = goAi$bestMove$Cand.r;
        }
        if ((i3 & 2) != 0) {
            i2 = goAi$bestMove$Cand.c;
        }
        if ((i3 & 4) != 0) {
            d = goAi$bestMove$Cand.score;
        }
        return goAi$bestMove$Cand.copy(i, i2, d);
    }

    public final int component1() {
        return this.r;
    }

    public final int component2() {
        return this.c;
    }

    public final double component3() {
        return this.score;
    }

    public final GoAi$bestMove$Cand copy(int i, int i2, double d) {
        return new GoAi$bestMove$Cand(i, i2, d);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GoAi$bestMove$Cand) {
            GoAi$bestMove$Cand goAi$bestMove$Cand = (GoAi$bestMove$Cand) obj;
            return this.r == goAi$bestMove$Cand.r && this.c == goAi$bestMove$Cand.c && Double.compare(this.score, goAi$bestMove$Cand.score) == 0;
        }
        return false;
    }

    public int hashCode() {
        return (((Integer.hashCode(this.r) * 31) + Integer.hashCode(this.c)) * 31) + Double.hashCode(this.score);
    }

    public String toString() {
        int i = this.r;
        int i2 = this.c;
        return "Cand(r=" + i + ", c=" + i2 + ", score=" + this.score + ")";
    }

    public GoAi$bestMove$Cand(int r, int c, double score) {
        this.r = r;
        this.c = c;
        this.score = score;
    }

    public final int getC() {
        return this.c;
    }

    public final int getR() {
        return this.r;
    }

    public final double getScore() {
        return this.score;
    }
}
