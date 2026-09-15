package com.xiaofan.bangfan;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: PokerCardView.kt */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001b\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J(\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020*2\u0006\u0010-\u001a\u00020*H\u0002J\u0010\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020\nH\u0002J0\u00101\u001a\u00020\u001d2\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u00020\n2\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u00020/2\u0006\u00108\u001a\u00020\nH\u0002J(\u00109\u001a\u00020\u001d2\u0006\u00102\u001a\u0002032\u0006\u0010:\u001a\u00020\n2\u0006\u0010;\u001a\u00020\u00102\u0006\u00105\u001a\u000206H\u0002J\u0010\u0010<\u001a\u00020\u00102\u0006\u00100\u001a\u00020\nH\u0002J\u0010\u0010=\u001a\u00020\u001d2\u0006\u00102\u001a\u000203H\u0014J\u0010\u0010>\u001a\u00020\u00102\u0006\u0010?\u001a\u00020@H\u0016J\u0010\u0010A\u001a\u00020/2\u0006\u00100\u001a\u00020\nH\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR$\u0010\u0011\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R$\u0010\u0016\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0013\"\u0004\b\u0018\u0010\u0015R\u000e\u0010\u0019\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u000e\u0010\"\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006B"}, d2 = {"Lcom/xiaofan/bangfan/PokerCardView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "border", "Landroid/graphics/Paint;", "v", "", "cardId", "getCardId", "()I", "setCardId", "(I)V", "", "chosen", "getChosen", "()Z", "setChosen", "(Z)V", "faceDown", "getFaceDown", "setFaceDown", "facePanel", "gold", "onTap", "Lkotlin/Function0;", "", "getOnTap", "()Lkotlin/jvm/functions/Function0;", "setOnTap", "(Lkotlin/jvm/functions/Function0;)V", "panelBorder", "pip", "shadow", "text", "white", "centerCropSrc", "Landroid/graphics/Rect;", "sW", "", "sH", "dW", "dH", "cornerLabel", "", "c", "drawFace", "canvas", "Landroid/graphics/Canvas;", "res", "body", "Landroid/graphics/RectF;", "tag", "ink", "drawPips", "rank", "red", "isRedSuit", "onDraw", "onTouchEvent", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "suitChar", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class PokerCardView extends View {
    private final Paint border;
    private int cardId;
    private boolean chosen;
    private boolean faceDown;
    private final Paint facePanel;
    private final Paint gold;
    private Function0<Unit> onTap;
    private final Paint panelBorder;
    private final Paint pip;
    private final Paint shadow;
    private final Paint text;
    private final Paint white;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public PokerCardView(Context context) {
        this(context, null, 2, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ PokerCardView(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PokerCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        Paint $this$white_u24lambda_u240 = new Paint(1);
        $this$white_u24lambda_u240.setColor(-1);
        $this$white_u24lambda_u240.setStyle(Paint.Style.FILL);
        this.white = $this$white_u24lambda_u240;
        Paint $this$border_u24lambda_u241 = new Paint(1);
        $this$border_u24lambda_u241.setStyle(Paint.Style.STROKE);
        this.border = $this$border_u24lambda_u241;
        Paint $this$gold_u24lambda_u242 = new Paint(1);
        $this$gold_u24lambda_u242.setStyle(Paint.Style.STROKE);
        $this$gold_u24lambda_u242.setColor(Color.parseColor("#F9A825"));
        $this$gold_u24lambda_u242.setStrokeWidth(5.0f);
        this.gold = $this$gold_u24lambda_u242;
        Paint $this$facePanel_u24lambda_u243 = new Paint(1);
        $this$facePanel_u24lambda_u243.setColor(Color.parseColor("#FBF6EA"));
        $this$facePanel_u24lambda_u243.setStyle(Paint.Style.FILL);
        this.facePanel = $this$facePanel_u24lambda_u243;
        Paint $this$panelBorder_u24lambda_u244 = new Paint(1);
        $this$panelBorder_u24lambda_u244.setStyle(Paint.Style.STROKE);
        $this$panelBorder_u24lambda_u244.setColor(Color.parseColor("#D8C9A8"));
        this.panelBorder = $this$panelBorder_u24lambda_u244;
        this.text = new Paint(1);
        this.pip = new Paint(1);
        Paint $this$shadow_u24lambda_u245 = new Paint(1);
        $this$shadow_u24lambda_u245.setColor(Color.argb(40, 0, 0, 0));
        $this$shadow_u24lambda_u245.setStyle(Paint.Style.FILL);
        this.shadow = $this$shadow_u24lambda_u245;
    }

    public final int getCardId() {
        return this.cardId;
    }

    public final void setCardId(int v) {
        this.cardId = v;
        invalidate();
    }

    public final boolean getFaceDown() {
        return this.faceDown;
    }

    public final void setFaceDown(boolean v) {
        this.faceDown = v;
        invalidate();
    }

    public final boolean getChosen() {
        return this.chosen;
    }

    public final void setChosen(boolean v) {
        this.chosen = v;
        invalidate();
    }

    public final Function0<Unit> getOnTap() {
        return this.onTap;
    }

    public final void setOnTap(Function0<Unit> function0) {
        this.onTap = function0;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int parseColor;
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        float w = getWidth();
        float h = getHeight();
        float r = TypedValue.applyDimension(1, 7.0f, getResources().getDisplayMetrics());
        RectF sh = new RectF(2.0f, 3.0f, w - 1.0f, h);
        canvas.drawRoundRect(sh, r, r, this.shadow);
        RectF body = new RectF(0.0f, 0.0f, w - 2.0f, h - 3.0f);
        if (this.faceDown) {
            CardArt cardArt = CardArt.INSTANCE;
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            Bitmap bmp = cardArt.bitmap(context, R.drawable.card_back);
            if (bmp != null) {
                int save = canvas.saveLayer(body, null);
                canvas.drawRoundRect(body, r, r, this.white);
                Rect src = centerCropSrc(bmp.getWidth(), bmp.getHeight(), body.width(), body.height());
                canvas.drawBitmap(bmp, src, body, (Paint) null);
                canvas.restoreToCount(save);
                this.border.setColor(Color.parseColor("#0E4F5E"));
                this.border.setStrokeWidth(2.0f);
                canvas.drawRoundRect(body, r, r, this.border);
                return;
            }
            return;
        }
        canvas.drawRoundRect(body, r, r, this.white);
        boolean red = isRedSuit(this.cardId);
        if (this.cardId >= 52) {
            parseColor = this.cardId == 53 ? Color.parseColor("#C62828") : Color.parseColor("#333333");
        } else {
            parseColor = red ? Color.parseColor("#C62828") : Color.parseColor("#222222");
        }
        int ink = parseColor;
        this.border.setColor(this.chosen ? Color.parseColor("#F9A825") : ink);
        this.border.setStrokeWidth(this.chosen ? 4.0f : 1.6f);
        canvas.drawRoundRect(body, r, r, this.border);
        if (this.chosen) {
            canvas.drawRoundRect(body, r, r, this.gold);
        }
        int rank = DdzRules.INSTANCE.rank(this.cardId);
        this.text.setColor(ink);
        this.text.setTextSize(w * 0.26f);
        this.text.setFakeBoldText(true);
        String label = cornerLabel(this.cardId);
        canvas.drawText(label, w * 0.1f, h * 0.2f, this.text);
        if (this.cardId < 52) {
            this.text.setTextSize(w * 0.22f);
            canvas.drawText(suitChar(this.cardId), w * 0.12f, h * 0.4f, this.text);
        }
        canvas.save();
        canvas.rotate(180.0f, w / 2.0f, h / 2.0f);
        this.text.setTextSize(w * 0.26f);
        canvas.drawText(label, 0.1f * w, h * 0.2f, this.text);
        if (this.cardId < 52) {
            this.text.setTextSize(w * 0.22f);
            canvas.drawText(suitChar(this.cardId), w * 0.12f, 0.4f * h, this.text);
        }
        canvas.restore();
        float f = w / 2.0f;
        if (this.cardId == 52) {
            drawFace(canvas, R.drawable.card_js, body, "小王", ink);
        } else if (this.cardId != 53) {
            if (rank != 11) {
                if (rank != 12) {
                    if (rank != 13) {
                        drawPips(canvas, rank, red, body);
                        return;
                    } else {
                        drawFace(canvas, red ? R.drawable.card_rk : R.drawable.card_bk, body, "K", ink);
                        return;
                    }
                }
                drawFace(canvas, red ? R.drawable.card_rq : R.drawable.card_bq, body, "Q", ink);
                return;
            }
            drawFace(canvas, red ? R.drawable.card_rj : R.drawable.card_bj, body, "J", ink);
        } else {
            drawFace(canvas, R.drawable.card_jb, body, "大王", ink);
        }
    }

    private final void drawFace(Canvas canvas, int res, RectF body, String tag, int ink) {
        float padX = body.width() * 0.1f;
        float top = body.height() * 0.3f;
        float bot = body.height() * 0.93f;
        RectF panel = new RectF(body.left + padX, top, body.right - padX, bot);
        canvas.drawRoundRect(panel, 6.0f, 6.0f, this.facePanel);
        CardArt cardArt = CardArt.INSTANCE;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        Bitmap bmp = cardArt.bitmap(context, res);
        if (bmp != null) {
            Rect src = centerCropSrc(bmp.getWidth(), bmp.getHeight(), panel.width(), panel.height());
            int save = canvas.saveLayer(panel, null);
            Paint $this$drawFace_u24lambda_u246 = new Paint(1);
            $this$drawFace_u24lambda_u246.setColor(Color.parseColor("#FBF6EA"));
            Unit unit = Unit.INSTANCE;
            canvas.drawRoundRect(panel, 6.0f, 6.0f, $this$drawFace_u24lambda_u246);
            canvas.drawBitmap(bmp, src, panel, (Paint) null);
            canvas.restoreToCount(save);
        }
        canvas.drawRoundRect(panel, 6.0f, 6.0f, this.panelBorder);
    }

    private final void drawPips(Canvas canvas, int rank, boolean red, RectF body) {
        double[][] layout;
        this.pip.setColor(Color.parseColor(red ? "#C62828" : "#222222"));
        this.pip.setTextSize(body.width() * 0.24f);
        this.pip.setTextAlign(Paint.Align.CENTER);
        String s = suitChar(this.cardId);
        float left = body.width() * 0.3f;
        float right = body.width() * 0.7f;
        float width = body.width() * 0.5f;
        double[] dArr = {0.34d, 0.5d, 0.66d, 0.82d};
        switch (rank) {
            case 3:
                layout = new double[][]{new double[]{0.5d, 0.34d}, new double[]{0.5d, 0.58d}, new double[]{0.5d, 0.82d}};
                break;
            case 4:
                layout = new double[][]{new double[]{0.3d, 0.36d}, new double[]{0.7d, 0.36d}, new double[]{0.3d, 0.8d}, new double[]{0.7d, 0.8d}};
                break;
            case 5:
                layout = new double[][]{new double[]{0.3d, 0.34d}, new double[]{0.7d, 0.34d}, new double[]{0.5d, 0.58d}, new double[]{0.3d, 0.82d}, new double[]{0.7d, 0.82d}};
                break;
            case 6:
                layout = new double[][]{new double[]{0.3d, 0.34d}, new double[]{0.7d, 0.34d}, new double[]{0.3d, 0.58d}, new double[]{0.7d, 0.58d}, new double[]{0.3d, 0.82d}, new double[]{0.7d, 0.82d}};
                break;
            case 7:
                layout = new double[][]{new double[]{0.3d, 0.32d}, new double[]{0.7d, 0.32d}, new double[]{0.5d, 0.45d}, new double[]{0.3d, 0.58d}, new double[]{0.7d, 0.58d}, new double[]{0.3d, 0.82d}, new double[]{0.7d, 0.82d}};
                break;
            case 8:
                layout = new double[][]{new double[]{0.3d, 0.3d}, new double[]{0.7d, 0.3d}, new double[]{0.5d, 0.43d}, new double[]{0.3d, 0.57d}, new double[]{0.7d, 0.57d}, new double[]{0.5d, 0.7d}, new double[]{0.3d, 0.84d}, new double[]{0.7d, 0.84d}};
                break;
            case 9:
                layout = new double[][]{new double[]{0.3d, 0.3d}, new double[]{0.7d, 0.3d}, new double[]{0.3d, 0.47d}, new double[]{0.7d, 0.47d}, new double[]{0.5d, 0.58d}, new double[]{0.3d, 0.7d}, new double[]{0.7d, 0.7d}, new double[]{0.3d, 0.86d}, new double[]{0.7d, 0.86d}};
                break;
            case 10:
            case 11:
            case 12:
            case 13:
            default:
                layout = new double[][]{new double[]{0.3d, 0.28d}, new double[]{0.7d, 0.28d}, new double[]{0.5d, 0.4d}, new double[]{0.3d, 0.52d}, new double[]{0.7d, 0.52d}, new double[]{0.3d, 0.66d}, new double[]{0.7d, 0.66d}, new double[]{0.5d, 0.78d}, new double[]{0.3d, 0.88d}, new double[]{0.7d, 0.88d}};
                break;
            case 14:
                layout = new double[][]{new double[]{0.5d, 0.58d}};
                break;
            case 15:
                layout = new double[][]{new double[]{0.5d, 0.36d}, new double[]{0.5d, 0.78d}};
                break;
        }
        int length = layout.length;
        int i = 0;
        while (i < length) {
            double[] pt = layout[i];
            float x = body.left + ((float) (body.width() * pt[0]));
            float y = body.top + ((float) (body.height() * pt[1]));
            canvas.drawText(s, x, y, this.pip);
            i++;
            left = left;
            right = right;
        }
        this.pip.setTextAlign(Paint.Align.LEFT);
    }

    private final String cornerLabel(int c) {
        return c >= 52 ? c == 53 ? "大" : "小" : DdzRules.INSTANCE.rank(c) == 14 ? "A" : DdzRules.INSTANCE.rank(c) == 15 ? "2" : String.valueOf(DdzRules.INSTANCE.rank(c));
    }

    private final String suitChar(int c) {
        switch (c % 4) {
            case 0:
                return "♦";
            case 1:
                return "♣";
            case 2:
                return "♥";
            default:
                return "♠";
        }
    }

    private final boolean isRedSuit(int c) {
        return c < 52 && (c % 4) % 2 == 0;
    }

    private final Rect centerCropSrc(float sW, float sH, float dW, float dH) {
        float scale = Math.max(dW / sW, dH / sH);
        float cropW = dW / scale;
        float cropH = dH / scale;
        int l = (int) ((sW - cropW) / 2.0f);
        int t = (int) ((sH - cropH) / 2.0f);
        return new Rect(l, t, ((int) cropW) + l, ((int) cropH) + t);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        Function0<Unit> function0;
        Intrinsics.checkNotNullParameter(event, "event");
        if (event.getActionMasked() == 1 && (function0 = this.onTap) != null) {
            function0.invoke();
        }
        return true;
    }
}
