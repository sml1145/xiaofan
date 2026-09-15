package com.xiaofan.bangfan;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.xiaofan.bangfan.GomokuGame;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: GomokuView.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001=B\u001b\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010'\u001a\u00020(J(\u0010)\u001a\u00020(2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u00192\u0006\u0010-\u001a\u00020\u00192\u0006\u0010.\u001a\u00020\u0019H\u0002J\u0010\u0010/\u001a\u00020(2\u0006\u0010*\u001a\u00020+H\u0014J(\u00100\u001a\u00020(2\u0006\u00101\u001a\u00020\u00192\u0006\u00102\u001a\u00020\u00192\u0006\u00103\u001a\u00020\u00192\u0006\u00104\u001a\u00020\u0019H\u0014J\u0010\u00105\u001a\u0002062\u0006\u00107\u001a\u000208H\u0016J\b\u00109\u001a\u00020(H\u0002J\u000e\u0010:\u001a\u00020(2\u0006\u0010;\u001a\u00020\rJ\u001e\u0010<\u001a\u00020(2\u0006\u0010,\u001a\u00020\u00192\u0006\u0010-\u001a\u00020\u00192\u0006\u0010.\u001a\u00020\u0019R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u0019@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u000e\u0010\u001d\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u001f\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u0019@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001cR\u001e\u0010!\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u0019@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001cR\u000e\u0010#\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006>"}, d2 = {"Lcom/xiaofan/bangfan/GomokuView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "blackPaint", "Landroid/graphics/Paint;", "boardPaint", "cell", "", "game", "Lcom/xiaofan/bangfan/GomokuGame;", "highlightPaint", "lastPaint", "linePaint", "listener", "Lcom/xiaofan/bangfan/GomokuView$Listener;", "getListener", "()Lcom/xiaofan/bangfan/GomokuView$Listener;", "setListener", "(Lcom/xiaofan/bangfan/GomokuView$Listener;)V", "padding", "<set-?>", "", "pendingColor", "getPendingColor", "()I", "pendingFill", "pendingPaint", "pendingX", "getPendingX", "pendingY", "getPendingY", "shadowPaint", "starPaint", "whitePaint", "winPaint", "clearPending", "", "drawStone", "canvas", "Landroid/graphics/Canvas;", "x", "y", "color", "onDraw", "onSizeChanged", "w", "h", "oldw", "oldh", "onTouchEvent", "", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "rebuildShaders", "setGame", "g", "setPending", "Listener", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class GomokuView extends View {
    private final Paint blackPaint;
    private final Paint boardPaint;
    private float cell;
    private GomokuGame game;
    private final Paint highlightPaint;
    private final Paint lastPaint;
    private final Paint linePaint;
    private Listener listener;
    private float padding;
    private int pendingColor;
    private final Paint pendingFill;
    private final Paint pendingPaint;
    private int pendingX;
    private int pendingY;
    private final Paint shadowPaint;
    private final Paint starPaint;
    private final Paint whitePaint;
    private final Paint winPaint;

    /* compiled from: GomokuView.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/xiaofan/bangfan/GomokuView$Listener;", "", "onCellTap", "", "x", "", "y", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public interface Listener {
        void onCellTap(int i, int i2);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public GomokuView(Context context) {
        this(context, null, 2, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GomokuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        this.pendingX = -1;
        this.pendingY = -1;
        Paint $this$pendingPaint_u24lambda_u240 = new Paint(1);
        $this$pendingPaint_u24lambda_u240.setStyle(Paint.Style.STROKE);
        $this$pendingPaint_u24lambda_u240.setStrokeWidth(5.0f);
        $this$pendingPaint_u24lambda_u240.setColor(Color.rgb(46, 125, 50));
        this.pendingPaint = $this$pendingPaint_u24lambda_u240;
        Paint $this$pendingFill_u24lambda_u241 = new Paint(1);
        $this$pendingFill_u24lambda_u241.setStyle(Paint.Style.FILL);
        this.pendingFill = $this$pendingFill_u24lambda_u241;
        this.boardPaint = new Paint(1);
        Paint $this$linePaint_u24lambda_u242 = new Paint(1);
        $this$linePaint_u24lambda_u242.setColor(Color.rgb(92, 64, 51));
        $this$linePaint_u24lambda_u242.setStrokeWidth(1.5f);
        $this$linePaint_u24lambda_u242.setStyle(Paint.Style.STROKE);
        this.linePaint = $this$linePaint_u24lambda_u242;
        Paint $this$starPaint_u24lambda_u243 = new Paint(1);
        $this$starPaint_u24lambda_u243.setColor(Color.rgb(92, 64, 51));
        $this$starPaint_u24lambda_u243.setStyle(Paint.Style.FILL);
        this.starPaint = $this$starPaint_u24lambda_u243;
        this.blackPaint = new Paint(1);
        this.whitePaint = new Paint(1);
        this.shadowPaint = new Paint(1);
        Paint $this$lastPaint_u24lambda_u244 = new Paint(1);
        $this$lastPaint_u24lambda_u244.setColor(Color.rgb(255, 107, 107));
        $this$lastPaint_u24lambda_u244.setStyle(Paint.Style.STROKE);
        $this$lastPaint_u24lambda_u244.setStrokeWidth(4.0f);
        this.lastPaint = $this$lastPaint_u24lambda_u244;
        Paint $this$winPaint_u24lambda_u245 = new Paint(1);
        $this$winPaint_u24lambda_u245.setColor(Color.rgb(255, 107, 107));
        $this$winPaint_u24lambda_u245.setStyle(Paint.Style.STROKE);
        $this$winPaint_u24lambda_u245.setStrokeWidth(7.0f);
        $this$winPaint_u24lambda_u245.setStrokeCap(Paint.Cap.ROUND);
        this.winPaint = $this$winPaint_u24lambda_u245;
        this.highlightPaint = new Paint(1);
    }

    public /* synthetic */ GomokuView(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    public final Listener getListener() {
        return this.listener;
    }

    public final void setListener(Listener listener) {
        this.listener = listener;
    }

    public final int getPendingX() {
        return this.pendingX;
    }

    public final int getPendingY() {
        return this.pendingY;
    }

    public final int getPendingColor() {
        return this.pendingColor;
    }

    public final void setPending(int x, int y, int color) {
        this.pendingX = x;
        this.pendingY = y;
        this.pendingColor = color;
        invalidate();
    }

    public final void clearPending() {
        this.pendingX = -1;
        this.pendingY = -1;
        this.pendingColor = 0;
        invalidate();
    }

    public final void setGame(GomokuGame g) {
        Intrinsics.checkNotNullParameter(g, "g");
        this.game = g;
        invalidate();
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        rebuildShaders();
    }

    private final void rebuildShaders() {
        float size = Math.min(getWidth(), getHeight());
        if (size <= 0.0f) {
            return;
        }
        this.boardPaint.setShader(new LinearGradient(0.0f, 0.0f, size, size, new int[]{Color.rgb(217, 182, 120), Color.rgb(232, 201, 138), Color.rgb(217, 182, 120)}, new float[]{0.0f, 0.5f, 1.0f}, Shader.TileMode.CLAMP));
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int i;
        int i2;
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        GomokuGame g = this.game;
        if (g == null) {
            return;
        }
        float size = Math.min(getWidth(), getHeight());
        canvas.drawRect(0.0f, 0.0f, size, size, this.boardPaint);
        this.padding = 0.05f * size;
        float f = 14;
        this.cell = (size - (this.padding * 2)) / f;
        for (int i3 = 0; i3 < 15; i3++) {
            canvas.drawLine(this.padding, (i3 * this.cell) + this.padding, (this.cell * f) + this.padding, (i3 * this.cell) + this.padding, this.linePaint);
            canvas.drawLine((i3 * this.cell) + this.padding, this.padding, (i3 * this.cell) + this.padding, (this.cell * f) + this.padding, this.linePaint);
        }
        char c = 1;
        int[][] stars = {new int[]{3, 3}, new int[]{3, 11}, new int[]{11, 3}, new int[]{11, 11}, new int[]{7, 7}};
        int length = stars.length;
        int i4 = 0;
        while (i4 < length) {
            int[] iArr = stars[i4];
            int sx = iArr[0];
            int sy = iArr[c];
            canvas.drawCircle(this.padding + (sx * this.cell), this.padding + (sy * this.cell), this.cell * 0.12f, this.starPaint);
            i4++;
            c = 1;
        }
        for (int y = 0; y < 15; y++) {
            for (int x = 0; x < 15; x++) {
                int v = g.getBoard()[y][x];
                if (v != 0) {
                    drawStone(canvas, x, y, v);
                }
            }
        }
        GomokuGame.Move last = g.getLastMove();
        if (last != null && !g.getGameOver()) {
            canvas.drawCircle(this.padding + (last.getX() * this.cell), this.padding + (last.getY() * this.cell), this.cell * 0.2f, this.lastPaint);
        }
        List line = g.getWinLine();
        if (line != null && line.size() >= 2) {
            int[] a = (int[]) CollectionsKt.first((List<? extends Object>) line);
            int[] b = (int[]) CollectionsKt.last((List<? extends Object>) line);
            canvas.drawLine((a[0] * this.cell) + this.padding, (a[1] * this.cell) + this.padding, (b[0] * this.cell) + this.padding, (b[1] * this.cell) + this.padding, this.winPaint);
        }
        if (this.pendingX >= 0 && this.pendingY >= 0 && !g.getGameOver()) {
            float px = this.padding + (this.pendingX * this.cell);
            float py = this.padding + (this.pendingY * this.cell);
            float r = this.cell * 0.42f;
            Paint paint = this.pendingFill;
            if (this.pendingColor == 1) {
                i = 110;
                i2 = 20;
            } else {
                i = 140;
                i2 = 255;
            }
            paint.setColor(Color.argb(i, i2, i2, i2));
            canvas.drawCircle(px, py, r, this.pendingFill);
            canvas.drawCircle(px, py, 1.05f * r, this.pendingPaint);
        }
    }

    private final void drawStone(Canvas canvas, int x, int y, int color) {
        float px = this.padding + (x * this.cell);
        float py = this.padding + (y * this.cell);
        float r = this.cell * 0.42f;
        this.shadowPaint.setShader(new RadialGradient(px, py, r, new int[]{Color.argb(64, 0, 0, 0), Color.argb(0, 0, 0, 0)}, (float[]) null, Shader.TileMode.CLAMP));
        canvas.drawCircle((0.08f * r) + px, (0.12f * r) + py, r, this.shadowPaint);
        this.shadowPaint.setShader(null);
        Paint paint = color == 1 ? this.blackPaint : this.whitePaint;
        paint.setShader(new RadialGradient(px - (r * 0.3f), py - (0.3f * r), r, color == 1 ? new int[]{Color.rgb(90, 90, 90), Color.rgb(42, 42, 42), Color.rgb(10, 10, 10)} : new int[]{Color.rgb(255, 255, 255), Color.rgb(240, 240, 240), Color.rgb((int) ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION, (int) ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION, (int) ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION)}, new float[]{0.0f, 0.3f, 1.0f}, Shader.TileMode.CLAMP));
        canvas.drawCircle(px, py, r, paint);
        paint.setShader(null);
        this.highlightPaint.setColor(Color.argb(color == 1 ? 38 : 204, 255, 255, 255));
        canvas.drawCircle(px - (r * 0.25f), py - (r * 0.25f), r * 0.28f, this.highlightPaint);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        GomokuGame g;
        Listener listener;
        Intrinsics.checkNotNullParameter(event, "event");
        if (event.getActionMasked() != 1 || (g = this.game) == null || g.getGameOver()) {
            return true;
        }
        float size = Math.min(getWidth(), getHeight());
        float pad = 0.05f * size;
        float c = (size - (2 * pad)) / 14;
        int gx = Math.round((event.getX() - pad) / c);
        int gy = Math.round((event.getY() - pad) / c);
        boolean z = false;
        if (gx >= 0 && gx < 15) {
            if (gy >= 0 && gy < 15) {
                z = true;
            }
            if (z) {
                float dx = Math.abs(event.getX() - ((gx * c) + pad));
                float dy = Math.abs(event.getY() - ((gy * c) + pad));
                if (dx <= c * 0.5f && dy <= 0.5f * c && (listener = this.listener) != null) {
                    listener.onCellTap(gx, gy);
                }
            }
        }
        return true;
    }
}
