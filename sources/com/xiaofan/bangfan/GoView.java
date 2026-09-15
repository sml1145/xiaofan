package com.xiaofan.bangfan;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: GoView.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001:\u0001:B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010&\u001a\u00020'J\u0010\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020*H\u0014J(\u0010+\u001a\u00020'2\u0006\u0010,\u001a\u00020\u001a2\u0006\u0010-\u001a\u00020\u001a2\u0006\u0010.\u001a\u00020\u001a2\u0006\u0010/\u001a\u00020\u001aH\u0014J\u0010\u00100\u001a\u0002012\u0006\u00102\u001a\u000203H\u0016J\u001e\u00104\u001a\u00020'2\u0006\u00105\u001a\u00020\u001a2\u0006\u00106\u001a\u00020\u001a2\u0006\u00107\u001a\u00020\u001aJ\u0010\u00108\u001a\u00020\b2\u0006\u00106\u001a\u00020\u001aH\u0002J\u0010\u00109\u001a\u00020\b2\u0006\u00105\u001a\u00020\u001aH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\u001a@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u001e\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\u001a@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001dR\u000e\u0010 \u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010!\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\u001a@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001dR\u000e\u0010#\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006;"}, d2 = {"Lcom/xiaofan/bangfan/GoView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "black", "Landroid/graphics/Paint;", "cell", "", "game", "Lcom/xiaofan/bangfan/GoGame;", "getGame", "()Lcom/xiaofan/bangfan/GoGame;", "setGame", "(Lcom/xiaofan/bangfan/GoGame;)V", "line", "listener", "Lcom/xiaofan/bangfan/GoView$Listener;", "getListener", "()Lcom/xiaofan/bangfan/GoView$Listener;", "setListener", "(Lcom/xiaofan/bangfan/GoView$Listener;)V", "mark", "ox", "oy", "<set-?>", "", "pendingC", "getPendingC", "()I", "pendingColor", "getPendingColor", "pendingFill", "pendingR", "getPendingR", "pendingRing", "white", "whiteEdge", "clearPending", "", "onDraw", "canvas", "Landroid/graphics/Canvas;", "onSizeChanged", "w", "h", "oldw", "oldh", "onTouchEvent", "", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "setPending", "r", "c", "color", "xOf", "yOf", "Listener", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class GoView extends View {
    private final Paint black;
    private float cell;
    private GoGame game;
    private final Paint line;
    private Listener listener;
    private final Paint mark;
    private float ox;
    private float oy;
    private int pendingC;
    private int pendingColor;
    private final Paint pendingFill;
    private int pendingR;
    private final Paint pendingRing;
    private final Paint white;
    private final Paint whiteEdge;

    /* compiled from: GoView.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/xiaofan/bangfan/GoView$Listener;", "", "onCellTap", "", "r", "", "c", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public interface Listener {
        void onCellTap(int i, int i2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GoView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.pendingR = -1;
        this.pendingC = -1;
        Paint $this$pendingRing_u24lambda_u240 = new Paint(1);
        $this$pendingRing_u24lambda_u240.setColor(Color.parseColor("#2E7D32"));
        $this$pendingRing_u24lambda_u240.setStyle(Paint.Style.STROKE);
        $this$pendingRing_u24lambda_u240.setStrokeWidth(5.0f);
        this.pendingRing = $this$pendingRing_u24lambda_u240;
        Paint $this$pendingFill_u24lambda_u241 = new Paint(1);
        $this$pendingFill_u24lambda_u241.setStyle(Paint.Style.FILL);
        this.pendingFill = $this$pendingFill_u24lambda_u241;
        Paint $this$line_u24lambda_u242 = new Paint(1);
        $this$line_u24lambda_u242.setColor(Color.parseColor("#6B4A2B"));
        $this$line_u24lambda_u242.setStrokeWidth(2.0f);
        this.line = $this$line_u24lambda_u242;
        Paint $this$black_u24lambda_u243 = new Paint(1);
        $this$black_u24lambda_u243.setColor(Color.parseColor("#222222"));
        $this$black_u24lambda_u243.setStyle(Paint.Style.FILL);
        this.black = $this$black_u24lambda_u243;
        Paint $this$white_u24lambda_u244 = new Paint(1);
        $this$white_u24lambda_u244.setColor(Color.parseColor("#F5F5F5"));
        $this$white_u24lambda_u244.setStyle(Paint.Style.FILL);
        this.white = $this$white_u24lambda_u244;
        Paint $this$whiteEdge_u24lambda_u245 = new Paint(1);
        $this$whiteEdge_u24lambda_u245.setColor(Color.parseColor("#999999"));
        $this$whiteEdge_u24lambda_u245.setStyle(Paint.Style.STROKE);
        $this$whiteEdge_u24lambda_u245.setStrokeWidth(1.5f);
        this.whiteEdge = $this$whiteEdge_u24lambda_u245;
        Paint $this$mark_u24lambda_u246 = new Paint(1);
        $this$mark_u24lambda_u246.setColor(Color.parseColor("#E53935"));
        $this$mark_u24lambda_u246.setStyle(Paint.Style.FILL);
        this.mark = $this$mark_u24lambda_u246;
    }

    public final Listener getListener() {
        return this.listener;
    }

    public final void setListener(Listener listener) {
        this.listener = listener;
    }

    public final GoGame getGame() {
        return this.game;
    }

    public final void setGame(GoGame goGame) {
        this.game = goGame;
    }

    public final int getPendingR() {
        return this.pendingR;
    }

    public final int getPendingC() {
        return this.pendingC;
    }

    public final int getPendingColor() {
        return this.pendingColor;
    }

    public final void setPending(int r, int c, int color) {
        this.pendingR = r;
        this.pendingC = c;
        this.pendingColor = color;
        invalidate();
    }

    public final void clearPending() {
        this.pendingR = -1;
        this.pendingC = -1;
        this.pendingColor = 0;
        invalidate();
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        GoGame goGame = this.game;
        int n = goGame != null ? goGame.getSize() : 9;
        this.cell = Math.min(w, h) / (n + 1);
        this.ox = (w - (this.cell * (n - 1))) / 2.0f;
        this.oy = (h - (this.cell * (n - 1))) / 2.0f;
    }

    private final float xOf(int c) {
        return this.ox + (c * this.cell);
    }

    private final float yOf(int r) {
        return this.oy + (r * this.cell);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int i;
        int i2;
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        GoGame g = this.game;
        if (g == null) {
            return;
        }
        int n = g.getSize();
        canvas.drawColor(Color.parseColor("#EACB94"));
        for (int i3 = 0; i3 < n; i3++) {
            canvas.drawLine(xOf(0), yOf(i3), xOf(n - 1), yOf(i3), this.line);
            canvas.drawLine(xOf(i3), yOf(0), xOf(i3), yOf(n - 1), this.line);
        }
        List<int[]> stars = n == 9 ? CollectionsKt.listOf((Object[]) new int[][]{new int[]{2, 2}, new int[]{2, 6}, new int[]{6, 2}, new int[]{6, 6}, new int[]{4, 4}}) : CollectionsKt.emptyList();
        for (int[] s : stars) {
            canvas.drawCircle(xOf(s[1]), yOf(s[0]), this.cell * 0.09f, this.black);
        }
        float radius = this.cell * 0.46f;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                int v = g.getBoard()[r][c];
                if (v != 0) {
                    if (v == 1) {
                        canvas.drawCircle(xOf(c), yOf(r), radius, this.black);
                    } else {
                        canvas.drawCircle(xOf(c), yOf(r), radius, this.white);
                        canvas.drawCircle(xOf(c), yOf(r), radius, this.whiteEdge);
                    }
                }
            }
        }
        int[] lm = g.getLastMove();
        if (lm != null) {
            int v2 = g.getBoard()[lm[0]][lm[1]];
            this.mark.setColor(Color.parseColor(v2 == 1 ? "#FFFFFF" : "#E53935"));
            canvas.drawCircle(xOf(lm[1]), yOf(lm[0]), 0.28f * radius, this.mark);
        }
        int v3 = this.pendingR;
        if (v3 >= 0 && this.pendingC >= 0 && !g.getGameOver()) {
            float px = xOf(this.pendingC);
            float py = yOf(this.pendingR);
            Paint paint = this.pendingFill;
            if (this.pendingColor == 1) {
                i = 110;
                i2 = 20;
            } else {
                i = 150;
                i2 = 255;
            }
            paint.setColor(Color.argb(i, i2, i2, i2));
            canvas.drawCircle(px, py, radius, this.pendingFill);
            canvas.drawCircle(px, py, 1.05f * radius, this.pendingRing);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        GoGame g;
        Listener listener;
        Intrinsics.checkNotNullParameter(event, "event");
        if (event.getActionMasked() != 1 || (g = this.game) == null) {
            return true;
        }
        int c = Math.round((event.getX() - this.ox) / this.cell);
        int r = Math.round((event.getY() - this.oy) / this.cell);
        boolean z = false;
        if (r >= 0 && r < g.getSize()) {
            if (c >= 0 && c < g.getSize()) {
                z = true;
            }
            if (z && (listener = this.listener) != null) {
                listener.onCellTap(r, c);
            }
        }
        return true;
    }
}
