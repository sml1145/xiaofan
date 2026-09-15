package com.xiaofan.bangfan;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ViewFlipper;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: HorizontalPager.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 +2\u00020\u0001:\u0003+,-B\u001b\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\nH\u0002J\u0006\u0010\u001b\u001a\u00020\u0016J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0016H\u0002J\b\u0010\u001f\u001a\u00020\u001dH\u0014J\u0010\u0010 \u001a\u00020\u00102\u0006\u0010!\u001a\u00020\"H\u0016J\u0010\u0010#\u001a\u00020\u00102\u0006\u0010!\u001a\u00020\"H\u0016J\u0016\u0010$\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00162\u0006\u0010%\u001a\u00020\u0010J\u0010\u0010&\u001a\u00020\u001d2\b\u0010'\u001a\u0004\u0018\u00010\u0012J\u0010\u0010(\u001a\u00020\u001d2\u0006\u0010)\u001a\u00020\u0016H\u0016J\u000e\u0010*\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/xiaofan/bangfan/HorizontalPager;", "Landroid/widget/ViewFlipper;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "detector", "Landroid/view/GestureDetector;", "downX", "", "downY", "inFromLeft", "Landroid/view/animation/Animation;", "inFromRight", "intercepting", "", "listener", "Lcom/xiaofan/bangfan/HorizontalPager$OnPageChangedListener;", "outToLeft", "outToRight", "swipeMinDxPx", "", "tracking", "buildSlide", "fromX", "toX", "getCurrentPage", "notifyPage", "", "index", "onAttachedToWindow", "onInterceptTouchEvent", "ev", "Landroid/view/MotionEvent;", "onTouchEvent", "scrollToPage", "smooth", "setOnPageChangedListener", "l", "setVisibility", "visibility", "showPage", "Companion", "GestureListener", "OnPageChangedListener", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class HorizontalPager extends ViewFlipper {
    private static final long ANIM_MS = 220;
    public static final Companion Companion = new Companion(null);
    private static final float SWIPE_MIN_VELOCITY = 320.0f;
    private final GestureDetector detector;
    private float downX;
    private float downY;
    private final Animation inFromLeft;
    private final Animation inFromRight;
    private volatile boolean intercepting;
    private OnPageChangedListener listener;
    private final Animation outToLeft;
    private final Animation outToRight;
    private final int swipeMinDxPx;
    private boolean tracking;

    /* compiled from: HorizontalPager.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/xiaofan/bangfan/HorizontalPager$OnPageChangedListener;", "", "onPageChanged", "", "index", "", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public interface OnPageChangedListener {
        void onPageChanged(int i);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public HorizontalPager(Context context) {
        this(context, null, 2, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ HorizontalPager(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HorizontalPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        this.swipeMinDxPx = (int) (context.getResources().getDisplayMetrics().density * 48.0f);
        this.inFromRight = buildSlide(1.0f, 0.0f);
        this.outToLeft = buildSlide(0.0f, -1.0f);
        this.inFromLeft = buildSlide(-1.0f, 0.0f);
        this.outToRight = buildSlide(0.0f, 1.0f);
        setInAnimation(this.inFromRight);
        setOutAnimation(this.outToLeft);
        this.detector = new GestureDetector(context, new GestureListener());
        this.detector.setIsLongpressEnabled(false);
        setAutoStart(false);
    }

    private final Animation buildSlide(float fromX, float toX) {
        TranslateAnimation anim = new TranslateAnimation(2, fromX, 2, toX, 2, 0.0f, 2, 0.0f);
        anim.setDuration(ANIM_MS);
        anim.setInterpolator(new AccelerateInterpolator());
        return anim;
    }

    public final void setOnPageChangedListener(OnPageChangedListener l) {
        this.listener = l;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Intrinsics.checkNotNullParameter(ev, "ev");
        try {
            this.detector.onTouchEvent(ev);
        } catch (Throwable th) {
        }
        switch (ev.getActionMasked()) {
            case 0:
                this.downX = ev.getX();
                this.downY = ev.getY();
                this.tracking = true;
                this.intercepting = false;
                break;
            case 1:
            case 3:
                this.intercepting = false;
                break;
            case 2:
                if (this.tracking) {
                    float dx = ev.getX() - this.downX;
                    float dy = ev.getY() - this.downY;
                    if (Math.abs(dx) > this.swipeMinDxPx && Math.abs(dx) > Math.abs(dy) * 1.4f) {
                        this.intercepting = true;
                        break;
                    }
                }
                break;
        }
        return this.intercepting;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent ev) {
        Intrinsics.checkNotNullParameter(ev, "ev");
        try {
            this.detector.onTouchEvent(ev);
        } catch (Throwable th) {
        }
        switch (ev.getActionMasked()) {
            case 0:
                this.downX = ev.getX();
                this.downY = ev.getY();
                this.tracking = true;
                return true;
            case 1:
                if (this.tracking) {
                    float dx = ev.getX() - this.downX;
                    float dy = ev.getY() - this.downY;
                    if (Math.abs(dx) > this.swipeMinDxPx && Math.abs(dx) > Math.abs(dy) * 1.4f) {
                        showPage(dx < 0.0f ? getDisplayedChild() + 1 : getDisplayedChild() - 1);
                    }
                }
                this.tracking = false;
                this.intercepting = false;
                return true;
            case 2:
                if (this.tracking) {
                    float dx2 = ev.getX() - this.downX;
                    float dy2 = ev.getY() - this.downY;
                    if (Math.abs(dx2) > this.swipeMinDxPx && Math.abs(dx2) > Math.abs(dy2) * 1.4f) {
                        this.intercepting = true;
                        break;
                    }
                }
                break;
            case 3:
                this.tracking = false;
                this.intercepting = false;
                return true;
        }
        if (this.intercepting) {
            return true;
        }
        return super.onTouchEvent(ev);
    }

    /* compiled from: HorizontalPager.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0006\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J*\u0010\u0007\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016J*\u0010\r\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000bH\u0016J\u0010\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0011"}, d2 = {"Lcom/xiaofan/bangfan/HorizontalPager$GestureListener;", "Landroid/view/GestureDetector$SimpleOnGestureListener;", "(Lcom/xiaofan/bangfan/HorizontalPager;)V", "onDown", "", "e", "Landroid/view/MotionEvent;", "onFling", "e1", "e2", "velocityX", "", "velocityY", "onScroll", "distanceX", "distanceY", "onSingleTapUp", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {
        public GestureListener() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent e) {
            Intrinsics.checkNotNullParameter(e, "e");
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent e) {
            Intrinsics.checkNotNullParameter(e, "e");
            return false;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Intrinsics.checkNotNullParameter(e2, "e2");
            if (e1 == null) {
                return false;
            }
            float dx = Math.abs(e2.getX() - e1.getX());
            float dy = Math.abs(e2.getY() - e1.getY());
            if (dx > HorizontalPager.this.swipeMinDxPx && dx > 1.6f * dy) {
                HorizontalPager.this.intercepting = true;
                return true;
            }
            return false;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Intrinsics.checkNotNullParameter(e2, "e2");
            return false;
        }
    }

    public final void showPage(int index) {
        int count = getChildCount();
        if (count == 0) {
            return;
        }
        int target = Math.max(0, Math.min(count - 1, index));
        int current = getDisplayedChild();
        if (target == current) {
            notifyPage(current);
            return;
        }
        if (target < current) {
            setInAnimation(this.inFromLeft);
            setOutAnimation(this.outToRight);
        } else {
            setInAnimation(this.inFromRight);
            setOutAnimation(this.outToLeft);
        }
        setDisplayedChild(target);
        notifyPage(target);
    }

    public final void scrollToPage(int index, boolean smooth) {
        showPage(index);
    }

    public final int getCurrentPage() {
        return getDisplayedChild();
    }

    private final void notifyPage(int index) {
        try {
            OnPageChangedListener onPageChangedListener = this.listener;
            if (onPageChangedListener != null) {
                onPageChangedListener.onPageChanged(index);
            }
        } catch (Throwable th) {
        }
    }

    @Override // android.widget.ViewFlipper, android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        stopFlipping();
        setAutoStart(false);
    }

    @Override // android.view.View
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (visibility != 0) {
            stopFlipping();
        }
    }

    /* compiled from: HorizontalPager.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/xiaofan/bangfan/HorizontalPager$Companion;", "", "()V", "ANIM_MS", "", "SWIPE_MIN_VELOCITY", "", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
