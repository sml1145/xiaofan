package com.xiaofan.bangfan;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: XiangqiView.kt */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001:\u0001@B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010+\u001a\u00020,J\u0010\u0010-\u001a\u00020,2\u0006\u0010.\u001a\u00020/H\u0014J(\u00100\u001a\u00020,2\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u0002022\u0006\u00104\u001a\u0002022\u0006\u00105\u001a\u000202H\u0014J\u0010\u00106\u001a\u0002072\u0006\u00108\u001a\u000209H\u0016J\u0010\u0010:\u001a\u00020,2\b\u0010;\u001a\u0004\u0018\u00010\u0011J\u0010\u0010<\u001a\u00020\b2\u0006\u0010=\u001a\u000202H\u0002J\u0010\u0010>\u001a\u00020\b2\u0006\u0010?\u001a\u000202H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u000e\u0010\u001e\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010 \u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0013\"\u0004\b\"\u0010\u0015R\u000e\u0010#\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010(\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0013\"\u0004\b*\u0010\u0015¨\u0006A"}, d2 = {"Lcom/xiaofan/bangfan/XiangqiView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "blackPaint", "Landroid/graphics/Paint;", "cell", "", "fillPaint", "game", "Lcom/xiaofan/bangfan/XiangqiGame;", "getGame", "()Lcom/xiaofan/bangfan/XiangqiGame;", "setGame", "(Lcom/xiaofan/bangfan/XiangqiGame;)V", "lastMove", "", "getLastMove", "()[I", "setLastMove", "([I)V", "lastPaint", "line", "listener", "Lcom/xiaofan/bangfan/XiangqiView$Listener;", "getListener", "()Lcom/xiaofan/bangfan/XiangqiView$Listener;", "setListener", "(Lcom/xiaofan/bangfan/XiangqiView$Listener;)V", "originX", "originY", "pending", "getPending", "setPending", "pendingFill", "pendingPaint", "redPaint", "ringPaint", "selPaint", "selected", "getSelected", "setSelected", "clearPending", "", "onDraw", "canvas", "Landroid/graphics/Canvas;", "onSizeChanged", "w", "", "h", "oldw", "oldh", "onTouchEvent", "", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "showPending", "mv", "xOf", "c", "yOf", "r", "Listener", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class XiangqiView extends View {
    private final Paint blackPaint;
    private float cell;
    private final Paint fillPaint;
    private XiangqiGame game;
    private int[] lastMove;
    private final Paint lastPaint;
    private final Paint line;
    private Listener listener;
    private float originX;
    private float originY;
    private int[] pending;
    private final Paint pendingFill;
    private final Paint pendingPaint;
    private final Paint redPaint;
    private final Paint ringPaint;
    private final Paint selPaint;
    private int[] selected;

    /* compiled from: XiangqiView.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/xiaofan/bangfan/XiangqiView$Listener;", "", "onCellTap", "", "r", "", "c", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public interface Listener {
        void onCellTap(int i, int i2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public XiangqiView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Paint $this$pendingPaint_u24lambda_u240 = new Paint(1);
        $this$pendingPaint_u24lambda_u240.setColor(Color.parseColor("#2E7D32"));
        $this$pendingPaint_u24lambda_u240.setStyle(Paint.Style.STROKE);
        $this$pendingPaint_u24lambda_u240.setStrokeWidth(5.0f);
        this.pendingPaint = $this$pendingPaint_u24lambda_u240;
        Paint $this$pendingFill_u24lambda_u241 = new Paint(1);
        $this$pendingFill_u24lambda_u241.setColor(Color.argb(70, 46, 125, 50));
        $this$pendingFill_u24lambda_u241.setStyle(Paint.Style.FILL);
        this.pendingFill = $this$pendingFill_u24lambda_u241;
        Paint $this$line_u24lambda_u242 = new Paint(1);
        $this$line_u24lambda_u242.setColor(Color.parseColor("#6B4A2B"));
        $this$line_u24lambda_u242.setStrokeWidth(2.5f);
        $this$line_u24lambda_u242.setStyle(Paint.Style.STROKE);
        this.line = $this$line_u24lambda_u242;
        Paint $this$redPaint_u24lambda_u243 = new Paint(1);
        $this$redPaint_u24lambda_u243.setColor(Color.parseColor("#C62828"));
        $this$redPaint_u24lambda_u243.setTextAlign(Paint.Align.CENTER);
        this.redPaint = $this$redPaint_u24lambda_u243;
        Paint $this$blackPaint_u24lambda_u244 = new Paint(1);
        $this$blackPaint_u24lambda_u244.setColor(Color.parseColor("#222222"));
        $this$blackPaint_u24lambda_u244.setTextAlign(Paint.Align.CENTER);
        this.blackPaint = $this$blackPaint_u24lambda_u244;
        Paint $this$fillPaint_u24lambda_u245 = new Paint(1);
        $this$fillPaint_u24lambda_u245.setColor(Color.parseColor("#F6E7C8"));
        $this$fillPaint_u24lambda_u245.setStyle(Paint.Style.FILL);
        this.fillPaint = $this$fillPaint_u24lambda_u245;
        Paint $this$ringPaint_u24lambda_u246 = new Paint(1);
        $this$ringPaint_u24lambda_u246.setStyle(Paint.Style.STROKE);
        $this$ringPaint_u24lambda_u246.setStrokeWidth(3.0f);
        this.ringPaint = $this$ringPaint_u24lambda_u246;
        Paint $this$selPaint_u24lambda_u247 = new Paint(1);
        $this$selPaint_u24lambda_u247.setColor(Color.parseColor("#2E7D32"));
        $this$selPaint_u24lambda_u247.setStyle(Paint.Style.STROKE);
        $this$selPaint_u24lambda_u247.setStrokeWidth(4.0f);
        this.selPaint = $this$selPaint_u24lambda_u247;
        Paint $this$lastPaint_u24lambda_u248 = new Paint(1);
        $this$lastPaint_u24lambda_u248.setColor(Color.parseColor("#FFCA28"));
        $this$lastPaint_u24lambda_u248.setStyle(Paint.Style.FILL);
        $this$lastPaint_u24lambda_u248.setAlpha(120);
        this.lastPaint = $this$lastPaint_u24lambda_u248;
    }

    public final Listener getListener() {
        return this.listener;
    }

    public final void setListener(Listener listener) {
        this.listener = listener;
    }

    public final XiangqiGame getGame() {
        return this.game;
    }

    public final void setGame(XiangqiGame xiangqiGame) {
        this.game = xiangqiGame;
    }

    public final int[] getSelected() {
        return this.selected;
    }

    public final void setSelected(int[] iArr) {
        this.selected = iArr;
    }

    public final int[] getLastMove() {
        return this.lastMove;
    }

    public final void setLastMove(int[] iArr) {
        this.lastMove = iArr;
    }

    public final int[] getPending() {
        return this.pending;
    }

    public final void setPending(int[] iArr) {
        this.pending = iArr;
    }

    public final void showPending(int[] mv) {
        this.pending = mv;
        invalidate();
    }

    public final void clearPending() {
        this.pending = null;
        invalidate();
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        float cw = w / 9.0f;
        float ch = h / 10.0f;
        this.cell = Math.min(cw, ch);
        this.originX = (w - (this.cell * 8)) / 2.0f;
        this.originY = (h - (this.cell * 9)) / 2.0f;
    }

    private final float xOf(int c) {
        return this.originX + (c * this.cell);
    }

    private final float yOf(int r) {
        return this.originY + (r * this.cell);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        XiangqiGame g = this.game;
        if (g == null) {
            return;
        }
        int c = 0;
        while (true) {
            int i = 9;
            if (c < 9) {
                switch (c) {
                    case 0:
                    case 8:
                        canvas.drawLine(xOf(c), yOf(0), xOf(c), yOf(9), this.line);
                        break;
                    default:
                        canvas.drawLine(xOf(c), yOf(0), xOf(c), yOf(4), this.line);
                        canvas.drawLine(xOf(c), yOf(5), xOf(c), yOf(9), this.line);
                        break;
                }
                c++;
            } else {
                int r = 0;
                while (true) {
                    if (r >= 10) {
                        canvas.drawLine(xOf(3), yOf(0), xOf(5), yOf(2), this.line);
                        canvas.drawLine(xOf(5), yOf(0), xOf(3), yOf(2), this.line);
                        canvas.drawLine(xOf(3), yOf(7), xOf(5), yOf(9), this.line);
                        canvas.drawLine(xOf(5), yOf(7), xOf(3), yOf(9), this.line);
                        int[] lm = this.lastMove;
                        if (lm != null) {
                            canvas.drawCircle(xOf(lm[2]), yOf(lm[3]), this.cell * 0.42f, this.lastPaint);
                        }
                        float radius = this.cell * 0.42f;
                        this.redPaint.setTextSize(this.cell * 0.5f);
                        this.blackPaint.setTextSize(this.cell * 0.5f);
                        int r2 = 0;
                        for (int i2 = 10; r2 < i2; i2 = 10) {
                            int c2 = 0;
                            while (c2 < i) {
                                int p = g.getBoard()[r2][c2];
                                if (p != 0) {
                                    float cx = xOf(c2);
                                    float cy = yOf(r2);
                                    this.ringPaint.setColor(Color.parseColor(p > 0 ? "#C62828" : "#222222"));
                                    canvas.drawCircle(cx, cy, radius, this.fillPaint);
                                    canvas.drawCircle(cx, cy, radius, this.ringPaint);
                                    Paint tp = p > 0 ? this.redPaint : this.blackPaint;
                                    float baseline = cy - ((tp.descent() + tp.ascent()) / 2.0f);
                                    canvas.drawText(XiangqiGame.Companion.pieceText(p), cx, baseline, tp);
                                }
                                c2++;
                                i = 9;
                            }
                            r2++;
                            i = 9;
                        }
                        int[] sel = this.selected;
                        if (sel != null) {
                            canvas.drawRect(xOf(sel[1]) - radius, yOf(sel[0]) - radius, xOf(sel[1]) + radius, yOf(sel[0]) + radius, this.selPaint);
                        }
                        int[] pd = this.pending;
                        if (pd != null) {
                            float tx = xOf(pd[3]);
                            float ty = yOf(pd[2]);
                            canvas.drawCircle(tx, ty, radius, this.pendingFill);
                            canvas.drawRect(tx - radius, ty - radius, tx + radius, ty + radius, this.pendingPaint);
                            int piece = g.getBoard()[pd[0]][pd[1]];
                            if (piece != 0) {
                                Paint tp2 = piece > 0 ? this.redPaint : this.blackPaint;
                                float base = ty - ((tp2.descent() + tp2.ascent()) / 2.0f);
                                canvas.drawText(XiangqiGame.Companion.pieceText(piece), tx, base, tp2);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    canvas.drawLine(xOf(0), yOf(r), xOf(8), yOf(r), this.line);
                    r++;
                }
            }
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        Listener listener;
        Intrinsics.checkNotNullParameter(event, "event");
        if (event.getActionMasked() == 1) {
            int c = Math.round((event.getX() - this.originX) / this.cell);
            int r = Math.round((event.getY() - this.originY) / this.cell);
            boolean z = false;
            if (r >= 0 && r < 10) {
                if (c >= 0 && c < 9) {
                    z = true;
                }
                if (z && (listener = this.listener) != null) {
                    listener.onCellTap(r, c);
                }
            }
        }
        return true;
    }
}
