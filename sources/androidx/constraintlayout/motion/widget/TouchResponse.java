package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.R;
import androidx.core.widget.NestedScrollView;
import org.xmlpull.v1.XmlPullParser;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class TouchResponse {
    private static final boolean DEBUG = false;
    static final int FLAG_DISABLE_POST_SCROLL = 1;
    static final int FLAG_DISABLE_SCROLL = 2;
    private static final int SIDE_BOTTOM = 3;
    private static final int SIDE_END = 6;
    private static final int SIDE_LEFT = 1;
    private static final int SIDE_MIDDLE = 4;
    private static final int SIDE_RIGHT = 2;
    private static final int SIDE_START = 5;
    private static final int SIDE_TOP = 0;
    private static final String TAG = "TouchResponse";
    private static final int TOUCH_DOWN = 1;
    private static final int TOUCH_END = 5;
    private static final int TOUCH_LEFT = 2;
    private static final int TOUCH_RIGHT = 3;
    private static final int TOUCH_START = 4;
    private static final int TOUCH_UP = 0;
    private float mLastTouchX;
    private float mLastTouchY;
    private final MotionLayout mMotionLayout;
    private static final float[][] TOUCH_SIDES = {new float[]{0.5f, 0.0f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}, new float[]{0.5f, 1.0f}, new float[]{0.5f, 0.5f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}};
    private static final float[][] TOUCH_DIRECTION = {new float[]{0.0f, -1.0f}, new float[]{0.0f, 1.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}};
    private int mTouchAnchorSide = 0;
    private int mTouchSide = 0;
    private int mOnTouchUp = 0;
    private int mTouchAnchorId = -1;
    private int mTouchRegionId = -1;
    private int mLimitBoundsTo = -1;
    private float mTouchAnchorY = 0.5f;
    private float mTouchAnchorX = 0.5f;
    private float mTouchDirectionX = 0.0f;
    private float mTouchDirectionY = 1.0f;
    private boolean mDragStarted = false;
    private float[] mAnchorDpDt = new float[2];
    private float mMaxVelocity = 4.0f;
    private float mMaxAcceleration = 1.2f;
    private boolean mMoveWhenScrollAtTop = true;
    private float mDragScale = 1.0f;
    private int mFlags = 0;
    private float mDragThreshold = 10.0f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TouchResponse(Context context, MotionLayout layout, XmlPullParser parser) {
        this.mMotionLayout = layout;
        fillFromAttributeList(context, Xml.asAttributeSet(parser));
    }

    public void setRTL(boolean rtl) {
        if (rtl) {
            TOUCH_DIRECTION[4] = TOUCH_DIRECTION[3];
            TOUCH_DIRECTION[5] = TOUCH_DIRECTION[2];
            TOUCH_SIDES[5] = TOUCH_SIDES[2];
            TOUCH_SIDES[6] = TOUCH_SIDES[1];
        } else {
            TOUCH_DIRECTION[4] = TOUCH_DIRECTION[2];
            TOUCH_DIRECTION[5] = TOUCH_DIRECTION[3];
            TOUCH_SIDES[5] = TOUCH_SIDES[1];
            TOUCH_SIDES[6] = TOUCH_SIDES[2];
        }
        this.mTouchAnchorX = TOUCH_SIDES[this.mTouchAnchorSide][0];
        this.mTouchAnchorY = TOUCH_SIDES[this.mTouchAnchorSide][1];
        this.mTouchDirectionX = TOUCH_DIRECTION[this.mTouchSide][0];
        this.mTouchDirectionY = TOUCH_DIRECTION[this.mTouchSide][1];
    }

    private void fillFromAttributeList(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.OnSwipe);
        fill(a);
        a.recycle();
    }

    private void fill(TypedArray a) {
        int N = a.getIndexCount();
        for (int i = 0; i < N; i++) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.OnSwipe_touchAnchorId) {
                this.mTouchAnchorId = a.getResourceId(attr, this.mTouchAnchorId);
            } else if (attr == R.styleable.OnSwipe_touchAnchorSide) {
                this.mTouchAnchorSide = a.getInt(attr, this.mTouchAnchorSide);
                this.mTouchAnchorX = TOUCH_SIDES[this.mTouchAnchorSide][0];
                this.mTouchAnchorY = TOUCH_SIDES[this.mTouchAnchorSide][1];
            } else if (attr == R.styleable.OnSwipe_dragDirection) {
                this.mTouchSide = a.getInt(attr, this.mTouchSide);
                this.mTouchDirectionX = TOUCH_DIRECTION[this.mTouchSide][0];
                this.mTouchDirectionY = TOUCH_DIRECTION[this.mTouchSide][1];
            } else if (attr == R.styleable.OnSwipe_maxVelocity) {
                this.mMaxVelocity = a.getFloat(attr, this.mMaxVelocity);
            } else if (attr == R.styleable.OnSwipe_maxAcceleration) {
                this.mMaxAcceleration = a.getFloat(attr, this.mMaxAcceleration);
            } else if (attr == R.styleable.OnSwipe_moveWhenScrollAtTop) {
                this.mMoveWhenScrollAtTop = a.getBoolean(attr, this.mMoveWhenScrollAtTop);
            } else if (attr == R.styleable.OnSwipe_dragScale) {
                this.mDragScale = a.getFloat(attr, this.mDragScale);
            } else if (attr == R.styleable.OnSwipe_dragThreshold) {
                this.mDragThreshold = a.getFloat(attr, this.mDragThreshold);
            } else if (attr == R.styleable.OnSwipe_touchRegionId) {
                this.mTouchRegionId = a.getResourceId(attr, this.mTouchRegionId);
            } else if (attr == R.styleable.OnSwipe_onTouchUp) {
                this.mOnTouchUp = a.getInt(attr, this.mOnTouchUp);
            } else if (attr == R.styleable.OnSwipe_nestedScrollFlags) {
                this.mFlags = a.getInteger(attr, 0);
            } else if (attr == R.styleable.OnSwipe_limitBoundsTo) {
                this.mLimitBoundsTo = a.getResourceId(attr, 0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setUpTouchEvent(float lastTouchX, float lastTouchY) {
        this.mLastTouchX = lastTouchX;
        this.mLastTouchY = lastTouchY;
        this.mDragStarted = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void processTouchEvent(MotionEvent event, MotionLayout.MotionTracker velocityTracker, int currentState, MotionScene motionScene) {
        float velocity;
        float pos;
        float change;
        velocityTracker.addMovement(event);
        switch (event.getAction()) {
            case 0:
                float tvx = event.getRawX();
                this.mLastTouchX = tvx;
                this.mLastTouchY = event.getRawY();
                this.mDragStarted = false;
                return;
            case 1:
                this.mDragStarted = false;
                velocityTracker.computeCurrentVelocity(1000);
                float tvx2 = velocityTracker.getXVelocity();
                float tvy = velocityTracker.getYVelocity();
                float currentPos = this.mMotionLayout.getProgress();
                if (this.mTouchAnchorId == -1) {
                    float minSize = Math.min(this.mMotionLayout.getWidth(), this.mMotionLayout.getHeight());
                    this.mAnchorDpDt[1] = this.mTouchDirectionY * minSize;
                    this.mAnchorDpDt[0] = this.mTouchDirectionX * minSize;
                } else {
                    this.mMotionLayout.getAnchorDpDt(this.mTouchAnchorId, currentPos, this.mTouchAnchorX, this.mTouchAnchorY, this.mAnchorDpDt);
                }
                float f = (this.mTouchDirectionX * this.mAnchorDpDt[0]) + (this.mTouchDirectionY * this.mAnchorDpDt[1]);
                if (this.mTouchDirectionX != 0.0f) {
                    velocity = tvx2 / this.mAnchorDpDt[0];
                } else {
                    velocity = tvy / this.mAnchorDpDt[1];
                }
                if (!Float.isNaN(velocity)) {
                    float pos2 = currentPos + (velocity / 3.0f);
                    pos = pos2;
                } else {
                    pos = currentPos;
                }
                if (pos != 0.0f && pos != 1.0f && this.mOnTouchUp != 3) {
                    this.mMotionLayout.touchAnimateTo(this.mOnTouchUp, ((double) pos) < 0.5d ? 0.0f : 1.0f, velocity);
                    if (0.0f >= currentPos || 1.0f <= currentPos) {
                        this.mMotionLayout.setState(MotionLayout.TransitionState.FINISHED);
                        return;
                    }
                    return;
                } else if (0.0f >= pos || 1.0f <= pos) {
                    this.mMotionLayout.setState(MotionLayout.TransitionState.FINISHED);
                    return;
                } else {
                    return;
                }
            case 2:
                float dy = event.getRawY() - this.mLastTouchY;
                float dx = event.getRawX() - this.mLastTouchX;
                float drag = (this.mTouchDirectionX * dx) + (this.mTouchDirectionY * dy);
                if (Math.abs(drag) > this.mDragThreshold || this.mDragStarted) {
                    float pos3 = this.mMotionLayout.getProgress();
                    if (!this.mDragStarted) {
                        this.mDragStarted = true;
                        this.mMotionLayout.setProgress(pos3);
                    }
                    if (this.mTouchAnchorId != -1) {
                        this.mMotionLayout.getAnchorDpDt(this.mTouchAnchorId, pos3, this.mTouchAnchorX, this.mTouchAnchorY, this.mAnchorDpDt);
                    } else {
                        float minSize2 = Math.min(this.mMotionLayout.getWidth(), this.mMotionLayout.getHeight());
                        this.mAnchorDpDt[1] = this.mTouchDirectionY * minSize2;
                        this.mAnchorDpDt[0] = this.mTouchDirectionX * minSize2;
                    }
                    float movmentInDir = (this.mTouchDirectionX * this.mAnchorDpDt[0]) + (this.mTouchDirectionY * this.mAnchorDpDt[1]);
                    if (Math.abs(movmentInDir * this.mDragScale) < 0.01d) {
                        this.mAnchorDpDt[0] = 0.01f;
                        this.mAnchorDpDt[1] = 0.01f;
                    }
                    if (this.mTouchDirectionX != 0.0f) {
                        change = dx / this.mAnchorDpDt[0];
                    } else {
                        change = dy / this.mAnchorDpDt[1];
                    }
                    float pos4 = Math.max(Math.min(pos3 + change, 1.0f), 0.0f);
                    if (pos4 != this.mMotionLayout.getProgress()) {
                        this.mMotionLayout.setProgress(pos4);
                        velocityTracker.computeCurrentVelocity(1000);
                        float tvx3 = velocityTracker.getXVelocity();
                        float tvy2 = velocityTracker.getYVelocity();
                        float velocity2 = this.mTouchDirectionX != 0.0f ? tvx3 / this.mAnchorDpDt[0] : tvy2 / this.mAnchorDpDt[1];
                        this.mMotionLayout.mLastVelocity = velocity2;
                    } else {
                        this.mMotionLayout.mLastVelocity = 0.0f;
                    }
                    this.mLastTouchX = event.getRawX();
                    this.mLastTouchY = event.getRawY();
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDown(float lastTouchX, float lastTouchY) {
        this.mLastTouchX = lastTouchX;
        this.mLastTouchY = lastTouchY;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getProgressDirection(float dx, float dy) {
        float pos = this.mMotionLayout.getProgress();
        this.mMotionLayout.getAnchorDpDt(this.mTouchAnchorId, pos, this.mTouchAnchorX, this.mTouchAnchorY, this.mAnchorDpDt);
        if (this.mTouchDirectionX != 0.0f) {
            if (this.mAnchorDpDt[0] == 0.0f) {
                this.mAnchorDpDt[0] = 1.0E-7f;
            }
            float velocity = (this.mTouchDirectionX * dx) / this.mAnchorDpDt[0];
            return velocity;
        }
        if (this.mAnchorDpDt[1] == 0.0f) {
            this.mAnchorDpDt[1] = 1.0E-7f;
        }
        float velocity2 = (this.mTouchDirectionY * dy) / this.mAnchorDpDt[1];
        return velocity2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void scrollUp(float dx, float dy) {
        float velocity;
        this.mDragStarted = false;
        float pos = this.mMotionLayout.getProgress();
        this.mMotionLayout.getAnchorDpDt(this.mTouchAnchorId, pos, this.mTouchAnchorX, this.mTouchAnchorY, this.mAnchorDpDt);
        float f = (this.mTouchDirectionX * this.mAnchorDpDt[0]) + (this.mTouchDirectionY * this.mAnchorDpDt[1]);
        if (this.mTouchDirectionX != 0.0f) {
            velocity = (this.mTouchDirectionX * dx) / this.mAnchorDpDt[0];
        } else {
            float velocity2 = this.mTouchDirectionY;
            velocity = (velocity2 * dy) / this.mAnchorDpDt[1];
        }
        if (!Float.isNaN(velocity)) {
            pos += velocity / 3.0f;
        }
        if (pos != 0.0f) {
            if ((this.mOnTouchUp != 3) & (pos != 1.0f)) {
                this.mMotionLayout.touchAnimateTo(this.mOnTouchUp, ((double) pos) >= 0.5d ? 1.0f : 0.0f, velocity);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void scrollMove(float dx, float dy) {
        float change;
        float f = (this.mTouchDirectionX * dx) + (this.mTouchDirectionY * dy);
        float pos = this.mMotionLayout.getProgress();
        if (!this.mDragStarted) {
            this.mDragStarted = true;
            this.mMotionLayout.setProgress(pos);
        }
        this.mMotionLayout.getAnchorDpDt(this.mTouchAnchorId, pos, this.mTouchAnchorX, this.mTouchAnchorY, this.mAnchorDpDt);
        float movmentInDir = (this.mTouchDirectionX * this.mAnchorDpDt[0]) + (this.mTouchDirectionY * this.mAnchorDpDt[1]);
        if (Math.abs(movmentInDir) < 0.01d) {
            this.mAnchorDpDt[0] = 0.01f;
            this.mAnchorDpDt[1] = 0.01f;
        }
        if (this.mTouchDirectionX != 0.0f) {
            change = (this.mTouchDirectionX * dx) / this.mAnchorDpDt[0];
        } else {
            float change2 = this.mTouchDirectionY;
            change = (change2 * dy) / this.mAnchorDpDt[1];
        }
        float pos2 = Math.max(Math.min(pos + change, 1.0f), 0.0f);
        if (pos2 != this.mMotionLayout.getProgress()) {
            this.mMotionLayout.setProgress(pos2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setupTouch() {
        View view = null;
        if (this.mTouchAnchorId != -1 && (view = this.mMotionLayout.findViewById(this.mTouchAnchorId)) == null) {
            Log.e(TAG, "cannot find TouchAnchorId @id/" + Debug.getName(this.mMotionLayout.getContext(), this.mTouchAnchorId));
        }
        if (view instanceof NestedScrollView) {
            NestedScrollView sv = (NestedScrollView) view;
            sv.setOnTouchListener(new View.OnTouchListener() { // from class: androidx.constraintlayout.motion.widget.TouchResponse.1
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view2, MotionEvent motionEvent) {
                    return false;
                }
            });
            sv.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() { // from class: androidx.constraintlayout.motion.widget.TouchResponse.2
                @Override // androidx.core.widget.NestedScrollView.OnScrollChangeListener
                public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                }
            });
        }
    }

    public void setAnchorId(int id) {
        this.mTouchAnchorId = id;
    }

    public int getAnchorId() {
        return this.mTouchAnchorId;
    }

    public void setTouchAnchorLocation(float x, float y) {
        this.mTouchAnchorX = x;
        this.mTouchAnchorY = y;
    }

    public void setMaxVelocity(float velocity) {
        this.mMaxVelocity = velocity;
    }

    public void setMaxAcceleration(float acceleration) {
        this.mMaxAcceleration = acceleration;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getMaxAcceleration() {
        return this.mMaxAcceleration;
    }

    public float getMaxVelocity() {
        return this.mMaxVelocity;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean getMoveWhenScrollAtTop() {
        return this.mMoveWhenScrollAtTop;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RectF getTouchRegion(ViewGroup layout, RectF rect) {
        View view;
        if (this.mTouchRegionId == -1 || (view = layout.findViewById(this.mTouchRegionId)) == null) {
            return null;
        }
        rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        return rect;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getTouchRegionId() {
        return this.mTouchRegionId;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RectF getLimitBoundsTo(ViewGroup layout, RectF rect) {
        View view;
        if (this.mLimitBoundsTo == -1 || (view = layout.findViewById(this.mLimitBoundsTo)) == null) {
            return null;
        }
        rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        return rect;
    }

    int getLimitBoundsToId() {
        return this.mLimitBoundsTo;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float dot(float dx, float dy) {
        return (this.mTouchDirectionX * dx) + (this.mTouchDirectionY * dy);
    }

    public String toString() {
        return this.mTouchDirectionX + " , " + this.mTouchDirectionY;
    }

    public int getFlags() {
        return this.mFlags;
    }
}
