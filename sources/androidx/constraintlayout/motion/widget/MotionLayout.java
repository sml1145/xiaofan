package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.TextView;
import androidx.constraintlayout.motion.utils.StopLogic;
import androidx.constraintlayout.motion.widget.MotionScene;
import androidx.constraintlayout.solver.widgets.Barrier;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.solver.widgets.Flow;
import androidx.constraintlayout.solver.widgets.Guideline;
import androidx.constraintlayout.solver.widgets.Helper;
import androidx.constraintlayout.solver.widgets.HelperWidget;
import androidx.constraintlayout.solver.widgets.VirtualLayout;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Constraints;
import androidx.constraintlayout.widget.R;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes.dex */
public class MotionLayout extends ConstraintLayout implements NestedScrollingParent3 {
    private static final boolean DEBUG = false;
    public static final int DEBUG_SHOW_NONE = 0;
    public static final int DEBUG_SHOW_PATH = 2;
    public static final int DEBUG_SHOW_PROGRESS = 1;
    private static final float EPSILON = 1.0E-5f;
    public static boolean IS_IN_EDIT_MODE = false;
    static final int MAX_KEY_FRAMES = 50;
    static final String TAG = "MotionLayout";
    public static final int TOUCH_UP_COMPLETE = 0;
    public static final int TOUCH_UP_COMPLETE_TO_END = 2;
    public static final int TOUCH_UP_COMPLETE_TO_START = 1;
    public static final int TOUCH_UP_DECELERATE = 4;
    public static final int TOUCH_UP_DECELERATE_AND_COMPLETE = 5;
    public static final int TOUCH_UP_STOP = 3;
    public static final int VELOCITY_LAYOUT = 1;
    public static final int VELOCITY_POST_LAYOUT = 0;
    public static final int VELOCITY_STATIC_LAYOUT = 3;
    public static final int VELOCITY_STATIC_POST_LAYOUT = 2;
    boolean firstDown;
    private float lastPos;
    private float lastY;
    private long mAnimationStartTime;
    private int mBeginState;
    private RectF mBoundsCheck;
    int mCurrentState;
    int mDebugPath;
    private DecelerateInterpolator mDecelerateLogic;
    private DesignTool mDesignTool;
    DevModeDraw mDevModeDraw;
    private int mEndState;
    int mEndWrapHeight;
    int mEndWrapWidth;
    HashMap<View, MotionController> mFrameArrayList;
    private int mFrames;
    int mHeightMeasureMode;
    private boolean mInLayout;
    boolean mInTransition;
    boolean mIndirectTransition;
    private boolean mInteractionEnabled;
    Interpolator mInterpolator;
    boolean mIsAnimating;
    private boolean mKeepAnimating;
    private KeyCache mKeyCache;
    private long mLastDrawTime;
    private float mLastFps;
    private int mLastHeightMeasureSpec;
    int mLastLayoutHeight;
    int mLastLayoutWidth;
    float mLastVelocity;
    private int mLastWidthMeasureSpec;
    private float mListenerPosition;
    private int mListenerState;
    protected boolean mMeasureDuringTransition;
    Model mModel;
    private boolean mNeedsFireTransitionCompleted;
    int mOldHeight;
    int mOldWidth;
    private ArrayList<MotionHelper> mOnHideHelpers;
    private ArrayList<MotionHelper> mOnShowHelpers;
    float mPostInterpolationPosition;
    private View mRegionView;
    MotionScene mScene;
    float mScrollTargetDT;
    float mScrollTargetDX;
    float mScrollTargetDY;
    long mScrollTargetTime;
    int mStartWrapHeight;
    int mStartWrapWidth;
    private StateCache mStateCache;
    private StopLogic mStopLogic;
    private boolean mTemporalInterpolator;
    ArrayList<Integer> mTransitionCompleted;
    private float mTransitionDuration;
    float mTransitionGoalPosition;
    private boolean mTransitionInstantly;
    float mTransitionLastPosition;
    private long mTransitionLastTime;
    private TransitionListener mTransitionListener;
    private ArrayList<TransitionListener> mTransitionListeners;
    float mTransitionPosition;
    TransitionState mTransitionState;
    boolean mUndergoingMotion;
    int mWidthMeasureMode;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public interface MotionTracker {
        void addMovement(MotionEvent motionEvent);

        void clear();

        void computeCurrentVelocity(int i);

        void computeCurrentVelocity(int i, float f);

        float getXVelocity();

        float getXVelocity(int i);

        float getYVelocity();

        float getYVelocity(int i);

        void recycle();
    }

    /* loaded from: classes.dex */
    public interface TransitionListener {
        void onTransitionChange(MotionLayout motionLayout, int i, int i2, float f);

        void onTransitionCompleted(MotionLayout motionLayout, int i);

        void onTransitionStarted(MotionLayout motionLayout, int i, int i2);

        void onTransitionTrigger(MotionLayout motionLayout, int i, boolean z, float f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public enum TransitionState {
        UNDEFINED,
        SETUP,
        MOVING,
        FINISHED
    }

    public MotionLayout(Context context) {
        super(context);
        this.mLastVelocity = 0.0f;
        this.mBeginState = -1;
        this.mCurrentState = -1;
        this.mEndState = -1;
        this.mLastWidthMeasureSpec = 0;
        this.mLastHeightMeasureSpec = 0;
        this.mInteractionEnabled = true;
        this.mFrameArrayList = new HashMap<>();
        this.mAnimationStartTime = 0L;
        this.mTransitionDuration = 1.0f;
        this.mTransitionPosition = 0.0f;
        this.mTransitionLastPosition = 0.0f;
        this.mTransitionGoalPosition = 0.0f;
        this.mInTransition = false;
        this.mIndirectTransition = false;
        this.mDebugPath = 0;
        this.mTemporalInterpolator = false;
        this.mStopLogic = new StopLogic();
        this.mDecelerateLogic = new DecelerateInterpolator();
        this.firstDown = true;
        this.mUndergoingMotion = false;
        this.mKeepAnimating = false;
        this.mOnShowHelpers = null;
        this.mOnHideHelpers = null;
        this.mTransitionListeners = null;
        this.mFrames = 0;
        this.mLastDrawTime = -1L;
        this.mLastFps = 0.0f;
        this.mListenerState = 0;
        this.mListenerPosition = 0.0f;
        this.mIsAnimating = false;
        this.mMeasureDuringTransition = false;
        this.mKeyCache = new KeyCache();
        this.mInLayout = false;
        this.mTransitionState = TransitionState.UNDEFINED;
        this.mModel = new Model();
        this.mNeedsFireTransitionCompleted = false;
        this.mBoundsCheck = new RectF();
        this.mRegionView = null;
        this.mTransitionCompleted = new ArrayList<>();
        init(null);
    }

    public MotionLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mLastVelocity = 0.0f;
        this.mBeginState = -1;
        this.mCurrentState = -1;
        this.mEndState = -1;
        this.mLastWidthMeasureSpec = 0;
        this.mLastHeightMeasureSpec = 0;
        this.mInteractionEnabled = true;
        this.mFrameArrayList = new HashMap<>();
        this.mAnimationStartTime = 0L;
        this.mTransitionDuration = 1.0f;
        this.mTransitionPosition = 0.0f;
        this.mTransitionLastPosition = 0.0f;
        this.mTransitionGoalPosition = 0.0f;
        this.mInTransition = false;
        this.mIndirectTransition = false;
        this.mDebugPath = 0;
        this.mTemporalInterpolator = false;
        this.mStopLogic = new StopLogic();
        this.mDecelerateLogic = new DecelerateInterpolator();
        this.firstDown = true;
        this.mUndergoingMotion = false;
        this.mKeepAnimating = false;
        this.mOnShowHelpers = null;
        this.mOnHideHelpers = null;
        this.mTransitionListeners = null;
        this.mFrames = 0;
        this.mLastDrawTime = -1L;
        this.mLastFps = 0.0f;
        this.mListenerState = 0;
        this.mListenerPosition = 0.0f;
        this.mIsAnimating = false;
        this.mMeasureDuringTransition = false;
        this.mKeyCache = new KeyCache();
        this.mInLayout = false;
        this.mTransitionState = TransitionState.UNDEFINED;
        this.mModel = new Model();
        this.mNeedsFireTransitionCompleted = false;
        this.mBoundsCheck = new RectF();
        this.mRegionView = null;
        this.mTransitionCompleted = new ArrayList<>();
        init(attrs);
    }

    public MotionLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mLastVelocity = 0.0f;
        this.mBeginState = -1;
        this.mCurrentState = -1;
        this.mEndState = -1;
        this.mLastWidthMeasureSpec = 0;
        this.mLastHeightMeasureSpec = 0;
        this.mInteractionEnabled = true;
        this.mFrameArrayList = new HashMap<>();
        this.mAnimationStartTime = 0L;
        this.mTransitionDuration = 1.0f;
        this.mTransitionPosition = 0.0f;
        this.mTransitionLastPosition = 0.0f;
        this.mTransitionGoalPosition = 0.0f;
        this.mInTransition = false;
        this.mIndirectTransition = false;
        this.mDebugPath = 0;
        this.mTemporalInterpolator = false;
        this.mStopLogic = new StopLogic();
        this.mDecelerateLogic = new DecelerateInterpolator();
        this.firstDown = true;
        this.mUndergoingMotion = false;
        this.mKeepAnimating = false;
        this.mOnShowHelpers = null;
        this.mOnHideHelpers = null;
        this.mTransitionListeners = null;
        this.mFrames = 0;
        this.mLastDrawTime = -1L;
        this.mLastFps = 0.0f;
        this.mListenerState = 0;
        this.mListenerPosition = 0.0f;
        this.mIsAnimating = false;
        this.mMeasureDuringTransition = false;
        this.mKeyCache = new KeyCache();
        this.mInLayout = false;
        this.mTransitionState = TransitionState.UNDEFINED;
        this.mModel = new Model();
        this.mNeedsFireTransitionCompleted = false;
        this.mBoundsCheck = new RectF();
        this.mRegionView = null;
        this.mTransitionCompleted = new ArrayList<>();
        init(attrs);
    }

    protected long getNanoTime() {
        return System.nanoTime();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MotionTracker obtainVelocityTracker() {
        return MyTracker.obtain();
    }

    public void enableTransition(int transitionID, boolean enable) {
        MotionScene.Transition t = getTransition(transitionID);
        if (enable) {
            t.setEnable(true);
            return;
        }
        if (t == this.mScene.mCurrentTransition) {
            List<MotionScene.Transition> transitions = this.mScene.getTransitionsWithState(this.mCurrentState);
            Iterator<MotionScene.Transition> it = transitions.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                MotionScene.Transition transition = it.next();
                if (transition.isEnabled()) {
                    this.mScene.mCurrentTransition = transition;
                    break;
                }
            }
        }
        t.setEnable(false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setState(TransitionState newState) {
        if (newState == TransitionState.FINISHED && this.mCurrentState == -1) {
            return;
        }
        TransitionState oldState = this.mTransitionState;
        this.mTransitionState = newState;
        if (oldState == TransitionState.MOVING && newState == TransitionState.MOVING) {
            fireTransitionChange();
        }
        switch (oldState) {
            case UNDEFINED:
            case SETUP:
                if (newState == TransitionState.MOVING) {
                    fireTransitionChange();
                }
                if (newState == TransitionState.FINISHED) {
                    fireTransitionCompleted();
                    return;
                }
                return;
            case MOVING:
                if (newState == TransitionState.FINISHED) {
                    fireTransitionCompleted();
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* loaded from: classes.dex */
    private static class MyTracker implements MotionTracker {
        private static MyTracker me = new MyTracker();
        VelocityTracker tracker;

        private MyTracker() {
        }

        public static MyTracker obtain() {
            me.tracker = VelocityTracker.obtain();
            return me;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker
        public void recycle() {
            this.tracker.recycle();
            this.tracker = null;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker
        public void clear() {
            this.tracker.clear();
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker
        public void addMovement(MotionEvent event) {
            if (this.tracker != null) {
                this.tracker.addMovement(event);
            }
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker
        public void computeCurrentVelocity(int units) {
            this.tracker.computeCurrentVelocity(units);
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker
        public void computeCurrentVelocity(int units, float maxVelocity) {
            this.tracker.computeCurrentVelocity(units, maxVelocity);
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker
        public float getXVelocity() {
            return this.tracker.getXVelocity();
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker
        public float getYVelocity() {
            return this.tracker.getYVelocity();
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker
        public float getXVelocity(int id) {
            return this.tracker.getXVelocity(id);
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker
        public float getYVelocity(int id) {
            return getYVelocity(id);
        }
    }

    public void setTransition(int beginId, int endId) {
        if (!isAttachedToWindow()) {
            if (this.mStateCache == null) {
                this.mStateCache = new StateCache();
            }
            this.mStateCache.setStartState(beginId);
            this.mStateCache.setEndState(endId);
        } else if (this.mScene != null) {
            this.mBeginState = beginId;
            this.mEndState = endId;
            this.mScene.setTransition(beginId, endId);
            this.mModel.initFrom(this.mLayoutWidget, this.mScene.getConstraintSet(beginId), this.mScene.getConstraintSet(endId));
            rebuildScene();
            this.mTransitionLastPosition = 0.0f;
            transitionToStart();
        }
    }

    public void setTransition(int transitionId) {
        if (this.mScene != null) {
            MotionScene.Transition transition = getTransition(transitionId);
            int i = this.mCurrentState;
            this.mBeginState = transition.getStartConstraintSetId();
            this.mEndState = transition.getEndConstraintSetId();
            if (!isAttachedToWindow()) {
                if (this.mStateCache == null) {
                    this.mStateCache = new StateCache();
                }
                this.mStateCache.setStartState(this.mBeginState);
                this.mStateCache.setEndState(this.mEndState);
                return;
            }
            float pos = Float.NaN;
            if (this.mCurrentState == this.mBeginState) {
                pos = 0.0f;
            } else if (this.mCurrentState == this.mEndState) {
                pos = 1.0f;
            }
            this.mScene.setTransition(transition);
            this.mModel.initFrom(this.mLayoutWidget, this.mScene.getConstraintSet(this.mBeginState), this.mScene.getConstraintSet(this.mEndState));
            rebuildScene();
            this.mTransitionLastPosition = Float.isNaN(pos) ? 0.0f : pos;
            if (Float.isNaN(pos)) {
                Log.v(TAG, Debug.getLocation() + " transitionToStart ");
                transitionToStart();
                return;
            }
            setProgress(pos);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setTransition(MotionScene.Transition transition) {
        this.mScene.setTransition(transition);
        setState(TransitionState.SETUP);
        if (this.mCurrentState == this.mScene.getEndId()) {
            this.mTransitionLastPosition = 1.0f;
            this.mTransitionPosition = 1.0f;
            this.mTransitionGoalPosition = 1.0f;
        } else {
            this.mTransitionLastPosition = 0.0f;
            this.mTransitionPosition = 0.0f;
            this.mTransitionGoalPosition = 0.0f;
        }
        this.mTransitionLastTime = transition.isTransitionFlag(1) ? -1L : getNanoTime();
        int newBeginState = this.mScene.getStartId();
        int newEndState = this.mScene.getEndId();
        if (newBeginState == this.mBeginState && newEndState == this.mEndState) {
            return;
        }
        this.mBeginState = newBeginState;
        this.mEndState = newEndState;
        this.mScene.setTransition(this.mBeginState, this.mEndState);
        this.mModel.initFrom(this.mLayoutWidget, this.mScene.getConstraintSet(this.mBeginState), this.mScene.getConstraintSet(this.mEndState));
        this.mModel.setMeasuredId(this.mBeginState, this.mEndState);
        this.mModel.reEvaluateState();
        rebuildScene();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout
    public void loadLayoutDescription(int motionScene) {
        if (motionScene != 0) {
            try {
                this.mScene = new MotionScene(getContext(), this, motionScene);
                if (isAttachedToWindow()) {
                    this.mScene.readFallback(this);
                    this.mModel.initFrom(this.mLayoutWidget, this.mScene.getConstraintSet(this.mBeginState), this.mScene.getConstraintSet(this.mEndState));
                    rebuildScene();
                    this.mScene.setRtl(isRtl());
                    return;
                }
                return;
            } catch (Exception ex) {
                throw new IllegalArgumentException("unable to parse MotionScene file", ex);
            }
        }
        this.mScene = null;
    }

    @Override // android.view.View
    public boolean isAttachedToWindow() {
        return super.isAttachedToWindow();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout
    public void setState(int id, int screenWidth, int screenHeight) {
        setState(TransitionState.SETUP);
        this.mCurrentState = id;
        this.mBeginState = -1;
        this.mEndState = -1;
        if (this.mConstraintLayoutSpec != null) {
            this.mConstraintLayoutSpec.updateConstraints(id, screenWidth, screenHeight);
        } else if (this.mScene != null) {
            this.mScene.getConstraintSet(id).applyTo(this);
        }
    }

    public void setInterpolatedProgress(float pos) {
        if (this.mScene != null) {
            setState(TransitionState.MOVING);
            Interpolator interpolator = this.mScene.getInterpolator();
            if (interpolator != null) {
                setProgress(interpolator.getInterpolation(pos));
                return;
            }
        }
        setProgress(pos);
    }

    public void setProgress(float pos, float velocity) {
        if (!isAttachedToWindow()) {
            if (this.mStateCache == null) {
                this.mStateCache = new StateCache();
            }
            this.mStateCache.setProgress(pos);
            this.mStateCache.setVelocity(velocity);
            return;
        }
        setProgress(pos);
        setState(TransitionState.MOVING);
        this.mLastVelocity = velocity;
        animateTo(1.0f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class StateCache {
        float mProgress = Float.NaN;
        float mVelocity = Float.NaN;
        int startState = -1;
        int endState = -1;
        final String KeyProgress = "motion.progress";
        final String KeyVelocity = "motion.velocity";
        final String KeyStartState = "motion.StartState";
        final String KeyEndState = "motion.EndState";

        StateCache() {
        }

        void apply() {
            if (this.startState != -1 || this.endState != -1) {
                if (this.startState == -1) {
                    MotionLayout.this.transitionToState(this.endState);
                } else if (this.endState == -1) {
                    MotionLayout.this.setState(this.startState, -1, -1);
                } else {
                    MotionLayout.this.setTransition(this.startState, this.endState);
                }
                MotionLayout.this.setState(TransitionState.SETUP);
            }
            if (Float.isNaN(this.mVelocity)) {
                if (Float.isNaN(this.mProgress)) {
                    return;
                }
                MotionLayout.this.setProgress(this.mProgress);
                return;
            }
            MotionLayout.this.setProgress(this.mProgress, this.mVelocity);
            this.mProgress = Float.NaN;
            this.mVelocity = Float.NaN;
            this.startState = -1;
            this.endState = -1;
        }

        public Bundle getTransitionState() {
            Bundle bundle = new Bundle();
            bundle.putFloat("motion.progress", this.mProgress);
            bundle.putFloat("motion.velocity", this.mVelocity);
            bundle.putInt("motion.StartState", this.startState);
            bundle.putInt("motion.EndState", this.endState);
            return bundle;
        }

        public void setTransitionState(Bundle bundle) {
            this.mProgress = bundle.getFloat("motion.progress");
            this.mVelocity = bundle.getFloat("motion.velocity");
            this.startState = bundle.getInt("motion.StartState");
            this.endState = bundle.getInt("motion.EndState");
        }

        public void setProgress(float progress) {
            this.mProgress = progress;
        }

        public void setEndState(int endState) {
            this.endState = endState;
        }

        public void setVelocity(float mVelocity) {
            this.mVelocity = mVelocity;
        }

        public void setStartState(int startState) {
            this.startState = startState;
        }

        public void recordState() {
            this.endState = MotionLayout.this.mEndState;
            this.startState = MotionLayout.this.mBeginState;
            this.mVelocity = MotionLayout.this.getVelocity();
            this.mProgress = MotionLayout.this.getProgress();
        }
    }

    public void setTransitionState(Bundle bundle) {
        if (this.mStateCache == null) {
            this.mStateCache = new StateCache();
        }
        this.mStateCache.setTransitionState(bundle);
        if (isAttachedToWindow()) {
            this.mStateCache.apply();
        }
    }

    public Bundle getTransitionState() {
        if (this.mStateCache == null) {
            this.mStateCache = new StateCache();
        }
        this.mStateCache.recordState();
        return this.mStateCache.getTransitionState();
    }

    public void setProgress(float pos) {
        if (!isAttachedToWindow()) {
            if (this.mStateCache == null) {
                this.mStateCache = new StateCache();
            }
            this.mStateCache.setProgress(pos);
            return;
        }
        if (pos <= 0.0f) {
            this.mCurrentState = this.mBeginState;
            if (this.mTransitionLastPosition == 0.0f) {
                setState(TransitionState.FINISHED);
            }
        } else if (pos >= 1.0f) {
            this.mCurrentState = this.mEndState;
            if (this.mTransitionLastPosition == 1.0f) {
                setState(TransitionState.FINISHED);
            }
        } else {
            this.mCurrentState = -1;
            setState(TransitionState.MOVING);
        }
        if (this.mScene == null) {
            return;
        }
        this.mTransitionInstantly = true;
        this.mTransitionGoalPosition = pos;
        this.mTransitionPosition = pos;
        this.mTransitionLastTime = -1L;
        this.mAnimationStartTime = -1L;
        this.mInterpolator = null;
        this.mInTransition = true;
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupMotionViews() {
        MotionLayout motionLayout = this;
        int n = getChildCount();
        motionLayout.mModel.build();
        motionLayout.mInTransition = true;
        int layoutWidth = getWidth();
        int layoutHeight = getHeight();
        int arc = motionLayout.mScene.gatPathMotionArc();
        if (arc != -1) {
            for (int i = 0; i < n; i++) {
                MotionController motionController = motionLayout.mFrameArrayList.get(motionLayout.getChildAt(i));
                if (motionController != null) {
                    motionController.setPathMotionArc(arc);
                }
            }
        }
        for (int i2 = 0; i2 < n; i2++) {
            MotionController motionController2 = motionLayout.mFrameArrayList.get(motionLayout.getChildAt(i2));
            if (motionController2 != null) {
                motionLayout.mScene.getKeyFrames(motionController2);
                motionController2.setup(layoutWidth, layoutHeight, motionLayout.mTransitionDuration, getNanoTime());
            }
        }
        float stagger = motionLayout.mScene.getStaggered();
        if (stagger != 0.0f) {
            boolean flip = ((double) stagger) < 0.0d;
            boolean useMotionStagger = false;
            float stagger2 = Math.abs(stagger);
            float min = Float.MAX_VALUE;
            float max = -3.4028235E38f;
            int i3 = 0;
            while (true) {
                if (i3 >= n) {
                    break;
                }
                MotionController f = motionLayout.mFrameArrayList.get(motionLayout.getChildAt(i3));
                if (!Float.isNaN(f.mMotionStagger)) {
                    useMotionStagger = true;
                    break;
                }
                float x = f.getFinalX();
                float y = f.getFinalY();
                float mdist = flip ? y - x : y + x;
                min = Math.min(min, mdist);
                max = Math.max(max, mdist);
                i3++;
            }
            if (useMotionStagger) {
                float min2 = Float.MAX_VALUE;
                float max2 = -3.4028235E38f;
                for (int i4 = 0; i4 < n; i4++) {
                    MotionController f2 = motionLayout.mFrameArrayList.get(motionLayout.getChildAt(i4));
                    if (!Float.isNaN(f2.mMotionStagger)) {
                        min2 = Math.min(min2, f2.mMotionStagger);
                        max2 = Math.max(max2, f2.mMotionStagger);
                    }
                }
                for (int i5 = 0; i5 < n; i5++) {
                    MotionController f3 = motionLayout.mFrameArrayList.get(motionLayout.getChildAt(i5));
                    if (!Float.isNaN(f3.mMotionStagger)) {
                        f3.mStaggerScale = 1.0f / (1.0f - stagger2);
                        if (flip) {
                            f3.mStaggerOffset = stagger2 - (((max2 - f3.mMotionStagger) / (max2 - min2)) * stagger2);
                        } else {
                            f3.mStaggerOffset = stagger2 - (((f3.mMotionStagger - min2) * stagger2) / (max2 - min2));
                        }
                    }
                }
                return;
            }
            int i6 = 0;
            while (i6 < n) {
                MotionController f4 = motionLayout.mFrameArrayList.get(motionLayout.getChildAt(i6));
                float x2 = f4.getFinalX();
                float y2 = f4.getFinalY();
                float mdist2 = flip ? y2 - x2 : y2 + x2;
                f4.mStaggerScale = 1.0f / (1.0f - stagger2);
                f4.mStaggerOffset = stagger2 - (((mdist2 - min) * stagger2) / (max - min));
                i6++;
                motionLayout = this;
            }
        }
    }

    public void touchAnimateTo(int touchUpMode, float position, float currentVelocity) {
        if (this.mScene == null || this.mTransitionLastPosition == position) {
            return;
        }
        this.mTemporalInterpolator = true;
        this.mAnimationStartTime = getNanoTime();
        this.mTransitionDuration = this.mScene.getDuration() / 1000.0f;
        this.mTransitionGoalPosition = position;
        this.mInTransition = true;
        switch (touchUpMode) {
            case 0:
            case 1:
            case 2:
                if (touchUpMode == 1) {
                    position = 0.0f;
                } else if (touchUpMode == 2) {
                    position = 1.0f;
                }
                this.mStopLogic.config(this.mTransitionLastPosition, position, currentVelocity, this.mTransitionDuration, this.mScene.getMaxAcceleration(), this.mScene.getMaxVelocity());
                int currentState = this.mCurrentState;
                this.mTransitionGoalPosition = position;
                this.mCurrentState = currentState;
                this.mInterpolator = this.mStopLogic;
                break;
            case 4:
                this.mDecelerateLogic.config(currentVelocity, this.mTransitionLastPosition, this.mScene.getMaxAcceleration());
                this.mInterpolator = this.mDecelerateLogic;
                break;
            case 5:
                if (willJump(currentVelocity, this.mTransitionLastPosition, this.mScene.getMaxAcceleration())) {
                    this.mDecelerateLogic.config(currentVelocity, this.mTransitionLastPosition, this.mScene.getMaxAcceleration());
                    this.mInterpolator = this.mDecelerateLogic;
                    break;
                } else {
                    this.mStopLogic.config(this.mTransitionLastPosition, position, currentVelocity, this.mTransitionDuration, this.mScene.getMaxAcceleration(), this.mScene.getMaxVelocity());
                    this.mLastVelocity = 0.0f;
                    int currentState2 = this.mCurrentState;
                    this.mTransitionGoalPosition = position;
                    this.mCurrentState = currentState2;
                    this.mInterpolator = this.mStopLogic;
                    break;
                }
        }
        this.mTransitionInstantly = false;
        this.mAnimationStartTime = getNanoTime();
        invalidate();
    }

    private static boolean willJump(float velocity, float position, float maxAcceleration) {
        if (velocity > 0.0f) {
            float time = velocity / maxAcceleration;
            float pos = (velocity * time) - (((maxAcceleration * time) * time) / 2.0f);
            return position + pos > 1.0f;
        }
        float pos2 = -velocity;
        float time2 = pos2 / maxAcceleration;
        float pos3 = (velocity * time2) + (((maxAcceleration * time2) * time2) / 2.0f);
        return position + pos3 < 0.0f;
    }

    /* loaded from: classes.dex */
    class DecelerateInterpolator extends MotionInterpolator {
        float maxA;
        float initalV = 0.0f;
        float currentP = 0.0f;

        DecelerateInterpolator() {
        }

        public void config(float velocity, float position, float maxAcceleration) {
            this.initalV = velocity;
            this.currentP = position;
            this.maxA = maxAcceleration;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionInterpolator, android.animation.TimeInterpolator
        public float getInterpolation(float time) {
            if (this.initalV > 0.0f) {
                if (this.initalV / this.maxA < time) {
                    time = this.initalV / this.maxA;
                }
                MotionLayout.this.mLastVelocity = this.initalV - (this.maxA * time);
                float pos = (this.initalV * time) - (((this.maxA * time) * time) / 2.0f);
                return this.currentP + pos;
            }
            float pos2 = this.initalV;
            if ((-pos2) / this.maxA < time) {
                time = (-this.initalV) / this.maxA;
            }
            MotionLayout.this.mLastVelocity = this.initalV + (this.maxA * time);
            float pos3 = (this.initalV * time) + (((this.maxA * time) * time) / 2.0f);
            return this.currentP + pos3;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionInterpolator
        public float getVelocity() {
            return MotionLayout.this.mLastVelocity;
        }
    }

    void animateTo(float position) {
        if (this.mScene == null) {
            return;
        }
        if (this.mTransitionLastPosition != this.mTransitionPosition && this.mTransitionInstantly) {
            this.mTransitionLastPosition = this.mTransitionPosition;
        }
        if (this.mTransitionLastPosition == position) {
            return;
        }
        this.mTemporalInterpolator = false;
        float currentPosition = this.mTransitionLastPosition;
        this.mTransitionGoalPosition = position;
        this.mTransitionDuration = this.mScene.getDuration() / 1000.0f;
        setProgress(this.mTransitionGoalPosition);
        this.mInterpolator = this.mScene.getInterpolator();
        this.mTransitionInstantly = false;
        this.mAnimationStartTime = getNanoTime();
        this.mInTransition = true;
        this.mTransitionPosition = currentPosition;
        this.mTransitionLastPosition = currentPosition;
        invalidate();
    }

    private void computeCurrentPositions() {
        int n = getChildCount();
        for (int i = 0; i < n; i++) {
            View v = getChildAt(i);
            MotionController frame = this.mFrameArrayList.get(v);
            if (frame != null) {
                frame.setStartCurrentState(v);
            }
        }
    }

    public void transitionToStart() {
        animateTo(0.0f);
    }

    public void transitionToEnd() {
        animateTo(1.0f);
    }

    public void transitionToState(int id) {
        if (!isAttachedToWindow()) {
            if (this.mStateCache == null) {
                this.mStateCache = new StateCache();
            }
            this.mStateCache.setEndState(id);
            return;
        }
        transitionToState(id, -1, -1);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x002f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void transitionToState(int r23, int r24, int r25) {
        /*
            Method dump skipped, instructions count: 377
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionLayout.transitionToState(int, int, int):void");
    }

    public float getVelocity() {
        return this.mLastVelocity;
    }

    public void getViewVelocity(View view, float posOnViewX, float posOnViewY, float[] returnVelocity, int type) {
        float position;
        float v = this.mLastVelocity;
        float position2 = this.mTransitionLastPosition;
        if (this.mInterpolator == null) {
            position = position2;
        } else {
            float dir = Math.signum(this.mTransitionGoalPosition - this.mTransitionLastPosition);
            float interpos = this.mInterpolator.getInterpolation(this.mTransitionLastPosition + EPSILON);
            float position3 = this.mInterpolator.getInterpolation(this.mTransitionLastPosition);
            v = (dir * ((interpos - position3) / EPSILON)) / this.mTransitionDuration;
            position = position3;
        }
        if (this.mInterpolator instanceof MotionInterpolator) {
            v = ((MotionInterpolator) this.mInterpolator).getVelocity();
        }
        MotionController f = this.mFrameArrayList.get(view);
        if ((type & 1) == 0) {
            f.getPostLayoutDvDp(position, view.getWidth(), view.getHeight(), posOnViewX, posOnViewY, returnVelocity);
        } else {
            f.getDpDt(position, posOnViewX, posOnViewY, returnVelocity);
        }
        if (type < 2) {
            returnVelocity[0] = returnVelocity[0] * v;
            returnVelocity[1] = returnVelocity[1] * v;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class Model {
        int mEndId;
        int mStartId;
        ConstraintWidgetContainer mLayoutStart = new ConstraintWidgetContainer();
        ConstraintWidgetContainer mLayoutEnd = new ConstraintWidgetContainer();
        ConstraintSet mStart = null;
        ConstraintSet mEnd = null;

        Model() {
        }

        void copy(ConstraintWidgetContainer src, ConstraintWidgetContainer dest) {
            ConstraintWidget child_d;
            ArrayList<ConstraintWidget> children = src.getChildren();
            HashMap<ConstraintWidget, ConstraintWidget> map = new HashMap<>();
            map.put(src, dest);
            dest.getChildren().clear();
            dest.copy(src, map);
            Iterator<ConstraintWidget> it = children.iterator();
            while (it.hasNext()) {
                ConstraintWidget child_s = it.next();
                if (child_s instanceof Barrier) {
                    child_d = new Barrier();
                } else if (child_s instanceof Guideline) {
                    child_d = new Guideline();
                } else if (child_s instanceof Flow) {
                    child_d = new Flow();
                } else if (child_s instanceof Helper) {
                    child_d = new HelperWidget();
                } else {
                    child_d = new ConstraintWidget();
                }
                dest.add(child_d);
                map.put(child_s, child_d);
            }
            Iterator<ConstraintWidget> it2 = children.iterator();
            while (it2.hasNext()) {
                ConstraintWidget child_s2 = it2.next();
                map.get(child_s2).copy(child_s2, map);
            }
        }

        void initFrom(ConstraintWidgetContainer baseLayout, ConstraintSet start, ConstraintSet end) {
            this.mStart = start;
            this.mEnd = end;
            this.mLayoutStart = new ConstraintWidgetContainer();
            this.mLayoutEnd = new ConstraintWidgetContainer();
            this.mLayoutStart.setMeasurer(MotionLayout.this.mLayoutWidget.getMeasurer());
            this.mLayoutEnd.setMeasurer(MotionLayout.this.mLayoutWidget.getMeasurer());
            this.mLayoutStart.removeAllChildren();
            this.mLayoutEnd.removeAllChildren();
            copy(MotionLayout.this.mLayoutWidget, this.mLayoutStart);
            copy(MotionLayout.this.mLayoutWidget, this.mLayoutEnd);
            if (MotionLayout.this.mTransitionLastPosition > 0.5d) {
                if (start != null) {
                    setupConstraintWidget(this.mLayoutStart, start);
                }
                setupConstraintWidget(this.mLayoutEnd, end);
            } else {
                setupConstraintWidget(this.mLayoutEnd, end);
                if (start != null) {
                    setupConstraintWidget(this.mLayoutStart, start);
                }
            }
            this.mLayoutStart.setRtl(MotionLayout.this.isRtl());
            this.mLayoutStart.updateHierarchy();
            this.mLayoutEnd.setRtl(MotionLayout.this.isRtl());
            this.mLayoutEnd.updateHierarchy();
            ViewGroup.LayoutParams layoutParams = MotionLayout.this.getLayoutParams();
            if (layoutParams != null) {
                if (layoutParams.width == -2) {
                    this.mLayoutStart.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.WRAP_CONTENT);
                    this.mLayoutEnd.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.WRAP_CONTENT);
                }
                if (layoutParams.height == -2) {
                    this.mLayoutStart.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.WRAP_CONTENT);
                    this.mLayoutEnd.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.WRAP_CONTENT);
                }
            }
        }

        private void setupConstraintWidget(ConstraintWidgetContainer base, ConstraintSet cset) {
            SparseArray<ConstraintWidget> mapIdToWidget = new SparseArray<>();
            Constraints.LayoutParams layoutParams = new Constraints.LayoutParams(-2, -2);
            mapIdToWidget.clear();
            mapIdToWidget.put(0, base);
            mapIdToWidget.put(MotionLayout.this.getId(), base);
            Iterator<ConstraintWidget> it = base.getChildren().iterator();
            while (it.hasNext()) {
                ConstraintWidget child = it.next();
                mapIdToWidget.put(((View) child.getCompanionWidget()).getId(), child);
            }
            Iterator<ConstraintWidget> it2 = base.getChildren().iterator();
            while (it2.hasNext()) {
                ConstraintWidget child2 = it2.next();
                View view = (View) child2.getCompanionWidget();
                cset.applyToLayoutParams(view.getId(), layoutParams);
                child2.setWidth(cset.getWidth(view.getId()));
                child2.setHeight(cset.getHeight(view.getId()));
                if (view instanceof ConstraintHelper) {
                    cset.applyToHelper((ConstraintHelper) view, child2, layoutParams, mapIdToWidget);
                    if (view instanceof androidx.constraintlayout.widget.Barrier) {
                        ((androidx.constraintlayout.widget.Barrier) view).validateParams();
                    }
                }
                layoutParams.resolveLayoutDirection(MotionLayout.this.getLayoutDirection());
                MotionLayout.this.applyConstraintsFromLayoutParams(false, view, child2, layoutParams, mapIdToWidget);
                if (cset.getVisibilityMode(view.getId()) == 1) {
                    child2.setVisibility(view.getVisibility());
                } else {
                    child2.setVisibility(cset.getVisibility(view.getId()));
                }
            }
            Iterator<ConstraintWidget> it3 = base.getChildren().iterator();
            while (it3.hasNext()) {
                ConstraintWidget child3 = it3.next();
                if (child3 instanceof VirtualLayout) {
                    Helper helper = (Helper) child3;
                    ((ConstraintHelper) child3.getCompanionWidget()).updatePreLayout(base, helper, mapIdToWidget);
                    VirtualLayout virtualLayout = (VirtualLayout) helper;
                    virtualLayout.captureWidgets();
                }
            }
        }

        ConstraintWidget getWidget(ConstraintWidgetContainer container, View view) {
            if (container.getCompanionWidget() == view) {
                return container;
            }
            ArrayList<ConstraintWidget> children = container.getChildren();
            int count = children.size();
            for (int i = 0; i < count; i++) {
                ConstraintWidget widget = children.get(i);
                if (widget.getCompanionWidget() == view) {
                    return widget;
                }
            }
            return null;
        }

        private void debugLayoutParam(String str, ConstraintLayout.LayoutParams params) {
            String a = " " + (params.startToStart != -1 ? "SS" : "__");
            Log.v(MotionLayout.TAG, str + (((((((((((a + (params.startToEnd != -1 ? "|SE" : "|__")) + (params.endToStart != -1 ? "|ES" : "|__")) + (params.endToEnd != -1 ? "|EE" : "|__")) + (params.leftToLeft != -1 ? "|LL" : "|__")) + (params.leftToRight != -1 ? "|LR" : "|__")) + (params.rightToLeft != -1 ? "|RL" : "|__")) + (params.rightToRight != -1 ? "|RR" : "|__")) + (params.topToTop != -1 ? "|TT" : "|__")) + (params.topToBottom != -1 ? "|TB" : "|__")) + (params.bottomToTop != -1 ? "|BT" : "|__")) + (params.bottomToBottom != -1 ? "|BB" : "|__")));
        }

        private void debugWidget(String str, ConstraintWidget child) {
            String str2;
            String str3;
            String str4;
            StringBuilder append = new StringBuilder().append(" ");
            String str5 = "__";
            if (child.mTop.mTarget != null) {
                str2 = "T" + (child.mTop.mTarget.mType == ConstraintAnchor.Type.TOP ? "T" : "B");
            } else {
                str2 = "__";
            }
            String a = append.append(str2).toString();
            StringBuilder append2 = new StringBuilder().append(a);
            if (child.mBottom.mTarget != null) {
                str3 = "B" + (child.mBottom.mTarget.mType == ConstraintAnchor.Type.TOP ? "T" : "B");
            } else {
                str3 = "__";
            }
            String a2 = append2.append(str3).toString();
            StringBuilder append3 = new StringBuilder().append(a2);
            if (child.mLeft.mTarget != null) {
                str4 = "L" + (child.mLeft.mTarget.mType == ConstraintAnchor.Type.LEFT ? "L" : "R");
            } else {
                str4 = "__";
            }
            String a3 = append3.append(str4).toString();
            StringBuilder append4 = new StringBuilder().append(a3);
            if (child.mRight.mTarget != null) {
                str5 = "R" + (child.mRight.mTarget.mType == ConstraintAnchor.Type.LEFT ? "L" : "R");
            }
            String a4 = append4.append(str5).toString();
            Log.v(MotionLayout.TAG, str + a4 + " ---  " + child);
        }

        private void debugLayout(String title, ConstraintWidgetContainer c) {
            String cName = title + " " + Debug.getName((View) c.getCompanionWidget());
            Log.v(MotionLayout.TAG, cName + "  ========= " + c);
            int count = c.getChildren().size();
            for (int i = 0; i < count; i++) {
                String str = cName + "[" + i + "] ";
                ConstraintWidget child = c.getChildren().get(i);
                String a = "" + (child.mTop.mTarget != null ? "T" : "_");
                String a2 = ((a + (child.mBottom.mTarget != null ? "B" : "_")) + (child.mLeft.mTarget != null ? "L" : "_")) + (child.mRight.mTarget != null ? "R" : "_");
                View v = (View) child.getCompanionWidget();
                String name = Debug.getName(v);
                if (v instanceof TextView) {
                    name = name + "(" + ((Object) ((TextView) v).getText()) + ")";
                }
                Log.v(MotionLayout.TAG, str + "  " + name + " " + child + " " + a2);
            }
            Log.v(MotionLayout.TAG, cName + " done. ");
        }

        public void reEvaluateState() {
            measure(MotionLayout.this.mLastWidthMeasureSpec, MotionLayout.this.mLastHeightMeasureSpec);
            MotionLayout.this.setupMotionViews();
        }

        public void measure(int widthMeasureSpec, int heightMeasureSpec) {
            boolean recompute_start_end_size;
            int width;
            int height;
            int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
            int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
            MotionLayout.this.mWidthMeasureMode = widthMode;
            MotionLayout.this.mHeightMeasureMode = heightMode;
            int optimisationLevel = MotionLayout.this.getOptimizationLevel();
            if (MotionLayout.this.mCurrentState == MotionLayout.this.getStartState()) {
                MotionLayout.this.resolveSystem(this.mLayoutEnd, optimisationLevel, widthMeasureSpec, heightMeasureSpec);
                if (this.mStart != null) {
                    MotionLayout.this.resolveSystem(this.mLayoutStart, optimisationLevel, widthMeasureSpec, heightMeasureSpec);
                }
            } else {
                if (this.mStart != null) {
                    MotionLayout.this.resolveSystem(this.mLayoutStart, optimisationLevel, widthMeasureSpec, heightMeasureSpec);
                }
                MotionLayout.this.resolveSystem(this.mLayoutEnd, optimisationLevel, widthMeasureSpec, heightMeasureSpec);
            }
            if ((MotionLayout.this.getParent() instanceof MotionLayout) && widthMode == 1073741824 && heightMode == 1073741824) {
                recompute_start_end_size = false;
            } else {
                recompute_start_end_size = true;
            }
            if (recompute_start_end_size) {
                MotionLayout.this.mWidthMeasureMode = widthMode;
                MotionLayout.this.mHeightMeasureMode = heightMode;
                if (MotionLayout.this.mCurrentState == MotionLayout.this.getStartState()) {
                    MotionLayout.this.resolveSystem(this.mLayoutEnd, optimisationLevel, widthMeasureSpec, heightMeasureSpec);
                    if (this.mStart != null) {
                        MotionLayout.this.resolveSystem(this.mLayoutStart, optimisationLevel, widthMeasureSpec, heightMeasureSpec);
                    }
                } else {
                    if (this.mStart != null) {
                        MotionLayout.this.resolveSystem(this.mLayoutStart, optimisationLevel, widthMeasureSpec, heightMeasureSpec);
                    }
                    MotionLayout.this.resolveSystem(this.mLayoutEnd, optimisationLevel, widthMeasureSpec, heightMeasureSpec);
                }
                MotionLayout.this.mStartWrapWidth = this.mLayoutStart.getWidth();
                MotionLayout.this.mStartWrapHeight = this.mLayoutStart.getHeight();
                MotionLayout.this.mEndWrapWidth = this.mLayoutEnd.getWidth();
                MotionLayout.this.mEndWrapHeight = this.mLayoutEnd.getHeight();
                MotionLayout.this.mMeasureDuringTransition = (MotionLayout.this.mStartWrapWidth == MotionLayout.this.mEndWrapWidth && MotionLayout.this.mStartWrapHeight == MotionLayout.this.mEndWrapHeight) ? false : true;
            }
            int width2 = MotionLayout.this.mStartWrapWidth;
            int height2 = MotionLayout.this.mStartWrapHeight;
            if (MotionLayout.this.mWidthMeasureMode == Integer.MIN_VALUE || MotionLayout.this.mWidthMeasureMode == 0) {
                int width3 = (int) (MotionLayout.this.mStartWrapWidth + (MotionLayout.this.mPostInterpolationPosition * (MotionLayout.this.mEndWrapWidth - MotionLayout.this.mStartWrapWidth)));
                width = width3;
            } else {
                width = width2;
            }
            if (MotionLayout.this.mHeightMeasureMode == Integer.MIN_VALUE || MotionLayout.this.mHeightMeasureMode == 0) {
                int height3 = (int) (MotionLayout.this.mStartWrapHeight + (MotionLayout.this.mPostInterpolationPosition * (MotionLayout.this.mEndWrapHeight - MotionLayout.this.mStartWrapHeight)));
                height = height3;
            } else {
                height = height2;
            }
            boolean isWidthMeasuredTooSmall = this.mLayoutStart.isWidthMeasuredTooSmall() || this.mLayoutEnd.isWidthMeasuredTooSmall();
            boolean isHeightMeasuredTooSmall = this.mLayoutStart.isHeightMeasuredTooSmall() || this.mLayoutEnd.isHeightMeasuredTooSmall();
            MotionLayout.this.resolveMeasuredDimension(widthMeasureSpec, heightMeasureSpec, width, height, isWidthMeasuredTooSmall, isHeightMeasuredTooSmall);
        }

        public void build() {
            int n = MotionLayout.this.getChildCount();
            MotionLayout.this.mFrameArrayList.clear();
            for (int i = 0; i < n; i++) {
                View v = MotionLayout.this.getChildAt(i);
                MotionLayout.this.mFrameArrayList.put(v, new MotionController(v));
            }
            for (int i2 = 0; i2 < n; i2++) {
                View v2 = MotionLayout.this.getChildAt(i2);
                MotionController motionController = MotionLayout.this.mFrameArrayList.get(v2);
                if (motionController != null) {
                    if (this.mStart != null) {
                        ConstraintWidget startWidget = getWidget(this.mLayoutStart, v2);
                        if (startWidget != null) {
                            motionController.setStartState(startWidget, this.mStart);
                        } else if (MotionLayout.this.mDebugPath != 0) {
                            Log.e(MotionLayout.TAG, Debug.getLocation() + "no widget for  " + Debug.getName(v2) + " (" + v2.getClass().getName() + ")");
                        }
                    }
                    if (this.mEnd != null) {
                        ConstraintWidget endWidget = getWidget(this.mLayoutEnd, v2);
                        if (endWidget != null) {
                            motionController.setEndState(endWidget, this.mEnd);
                        } else if (MotionLayout.this.mDebugPath != 0) {
                            Log.e(MotionLayout.TAG, Debug.getLocation() + "no widget for  " + Debug.getName(v2) + " (" + v2.getClass().getName() + ")");
                        }
                    }
                }
            }
        }

        public void setMeasuredId(int startId, int endId) {
            this.mStartId = startId;
            this.mEndId = endId;
        }

        public boolean isNotConfiguredWith(int startId, int endId) {
            return (startId == this.mStartId && endId == this.mEndId) ? false : true;
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (!this.mMeasureDuringTransition && this.mCurrentState == -1 && this.mScene != null && this.mScene.mCurrentTransition != null && this.mScene.mCurrentTransition.getLayoutDuringTransition() == 0) {
            return;
        }
        super.requestLayout();
    }

    @Override // android.view.View
    public String toString() {
        Context context = getContext();
        return Debug.getName(context, this.mBeginState) + "->" + Debug.getName(context, this.mEndState) + " (pos:" + this.mTransitionLastPosition + " Dpos/Dt:" + this.mLastVelocity;
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (this.mScene == null) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        boolean recalc = (this.mLastWidthMeasureSpec == widthMeasureSpec && this.mLastHeightMeasureSpec == heightMeasureSpec) ? false : true;
        if (this.mNeedsFireTransitionCompleted) {
            this.mNeedsFireTransitionCompleted = false;
            onNewStateAttachHandlers();
            processTransitionCompleted();
            recalc = true;
        }
        if (this.mDirtyHierarchy) {
            recalc = true;
        }
        this.mLastWidthMeasureSpec = widthMeasureSpec;
        this.mLastHeightMeasureSpec = heightMeasureSpec;
        int startId = this.mScene.getStartId();
        int endId = this.mScene.getEndId();
        boolean setMeasure = true;
        if ((recalc || this.mModel.isNotConfiguredWith(startId, endId)) && this.mBeginState != -1) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            this.mModel.initFrom(this.mLayoutWidget, this.mScene.getConstraintSet(startId), this.mScene.getConstraintSet(endId));
            this.mModel.reEvaluateState();
            this.mModel.setMeasuredId(startId, endId);
            setMeasure = false;
        }
        if (this.mMeasureDuringTransition || setMeasure) {
            int heightPadding = getPaddingTop() + getPaddingBottom();
            int widthPadding = getPaddingLeft() + getPaddingRight();
            int androidLayoutWidth = this.mLayoutWidget.getWidth() + widthPadding;
            int androidLayoutHeight = this.mLayoutWidget.getHeight() + heightPadding;
            if (this.mWidthMeasureMode == Integer.MIN_VALUE || this.mWidthMeasureMode == 0) {
                androidLayoutWidth = (int) (this.mStartWrapWidth + (this.mPostInterpolationPosition * (this.mEndWrapWidth - this.mStartWrapWidth)));
                requestLayout();
            }
            if (this.mHeightMeasureMode == Integer.MIN_VALUE || this.mHeightMeasureMode == 0) {
                androidLayoutHeight = (int) (this.mStartWrapHeight + (this.mPostInterpolationPosition * (this.mEndWrapHeight - this.mStartWrapHeight)));
                requestLayout();
            }
            setMeasuredDimension(androidLayoutWidth, androidLayoutHeight);
        }
        evaluateLayout();
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public boolean onStartNestedScroll(View child, View target, int axes, int type) {
        if (this.mScene == null || this.mScene.mCurrentTransition == null || this.mScene.mCurrentTransition.getTouchResponse() == null || (this.mScene.mCurrentTransition.getTouchResponse().getFlags() & 2) != 0) {
            return false;
        }
        return true;
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedScrollAccepted(View child, View target, int axes, int type) {
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onStopNestedScroll(View target, int type) {
        if (this.mScene == null) {
            return;
        }
        this.mScene.processScrollUp(this.mScrollTargetDX / this.mScrollTargetDT, this.mScrollTargetDY / this.mScrollTargetDT);
    }

    @Override // androidx.core.view.NestedScrollingParent3
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type, int[] consumed) {
        if (this.mUndergoingMotion || dxConsumed != 0 || dyConsumed != 0) {
            consumed[0] = consumed[0] + dxUnconsumed;
            consumed[1] = consumed[1] + dyUnconsumed;
        }
        this.mUndergoingMotion = false;
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedPreScroll(final View target, int dx, int dy, int[] consumed, int type) {
        TouchResponse touchResponse;
        int regionId;
        if (this.mScene == null || this.mScene.mCurrentTransition == null || !this.mScene.mCurrentTransition.isEnabled()) {
            return;
        }
        MotionScene.Transition currentTransition = this.mScene.mCurrentTransition;
        if (currentTransition != null && currentTransition.isEnabled() && (touchResponse = currentTransition.getTouchResponse()) != null && (regionId = touchResponse.getTouchRegionId()) != -1 && target.getId() != regionId) {
            return;
        }
        if (this.mScene != null && this.mScene.getMoveWhenScrollAtTop() && ((this.mTransitionPosition == 1.0f || this.mTransitionPosition == 0.0f) && target.canScrollVertically(-1))) {
            return;
        }
        if (currentTransition.getTouchResponse() != null && (this.mScene.mCurrentTransition.getTouchResponse().getFlags() & 1) != 0) {
            float dir = this.mScene.getProgressDirection(dx, dy);
            if ((this.mTransitionLastPosition <= 0.0f && dir < 0.0f) || (this.mTransitionLastPosition >= 1.0f && dir > 0.0f)) {
                target.setNestedScrollingEnabled(false);
                target.post(new Runnable() { // from class: androidx.constraintlayout.motion.widget.MotionLayout.1
                    @Override // java.lang.Runnable
                    public void run() {
                        target.setNestedScrollingEnabled(true);
                    }
                });
                return;
            }
        }
        float dir2 = this.mTransitionPosition;
        long time = getNanoTime();
        this.mScrollTargetDX = dx;
        this.mScrollTargetDY = dy;
        this.mScrollTargetDT = (float) ((time - this.mScrollTargetTime) * 1.0E-9d);
        this.mScrollTargetTime = time;
        this.mScene.processScrollMove(dx, dy);
        if (dir2 != this.mTransitionPosition) {
            consumed[0] = dx;
            consumed[1] = dy;
        }
        evaluate(false);
        if (consumed[0] != 0 || consumed[1] != 0) {
            this.mUndergoingMotion = true;
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }

    /* loaded from: classes.dex */
    private class DevModeDraw {
        private static final int DEBUG_PATH_TICKS_PER_MS = 16;
        DashPathEffect mDashPathEffect;
        Paint mFillPaint;
        int mKeyFrameCount;
        float[] mKeyFramePoints;
        Paint mPaintGraph;
        Paint mPaintKeyframes;
        Path mPath;
        int[] mPathMode;
        float[] mPoints;
        private float[] mRectangle;
        int mShadowTranslate;
        Paint mTextPaint;
        final int RED_COLOR = -21965;
        final int KEYFRAME_COLOR = -2067046;
        final int GRAPH_COLOR = -13391360;
        final int SHADOW_COLOR = 1996488704;
        final int DIAMOND_SIZE = 10;
        Rect mBounds = new Rect();
        boolean mPresentationMode = false;
        Paint mPaint = new Paint();

        public DevModeDraw() {
            this.mShadowTranslate = 1;
            this.mPaint.setAntiAlias(true);
            this.mPaint.setColor(-21965);
            this.mPaint.setStrokeWidth(2.0f);
            this.mPaint.setStyle(Paint.Style.STROKE);
            this.mPaintKeyframes = new Paint();
            this.mPaintKeyframes.setAntiAlias(true);
            this.mPaintKeyframes.setColor(-2067046);
            this.mPaintKeyframes.setStrokeWidth(2.0f);
            this.mPaintKeyframes.setStyle(Paint.Style.STROKE);
            this.mPaintGraph = new Paint();
            this.mPaintGraph.setAntiAlias(true);
            this.mPaintGraph.setColor(-13391360);
            this.mPaintGraph.setStrokeWidth(2.0f);
            this.mPaintGraph.setStyle(Paint.Style.STROKE);
            this.mTextPaint = new Paint();
            this.mTextPaint.setAntiAlias(true);
            this.mTextPaint.setColor(-13391360);
            this.mTextPaint.setTextSize(MotionLayout.this.getContext().getResources().getDisplayMetrics().density * 12.0f);
            this.mRectangle = new float[8];
            this.mFillPaint = new Paint();
            this.mFillPaint.setAntiAlias(true);
            this.mDashPathEffect = new DashPathEffect(new float[]{4.0f, 8.0f}, 0.0f);
            this.mPaintGraph.setPathEffect(this.mDashPathEffect);
            this.mKeyFramePoints = new float[100];
            this.mPathMode = new int[50];
            if (this.mPresentationMode) {
                this.mPaint.setStrokeWidth(8.0f);
                this.mFillPaint.setStrokeWidth(8.0f);
                this.mPaintKeyframes.setStrokeWidth(8.0f);
                this.mShadowTranslate = 4;
            }
        }

        public void draw(Canvas canvas, HashMap<View, MotionController> frameArrayList, int duration, int debugPath) {
            if (frameArrayList == null || frameArrayList.size() == 0) {
                return;
            }
            canvas.save();
            if (!MotionLayout.this.isInEditMode() && (debugPath & 1) == 2) {
                String str = MotionLayout.this.getContext().getResources().getResourceName(MotionLayout.this.mEndState) + ":" + MotionLayout.this.getProgress();
                canvas.drawText(str, 10.0f, MotionLayout.this.getHeight() - 30, this.mTextPaint);
                canvas.drawText(str, 11.0f, MotionLayout.this.getHeight() - 29, this.mPaint);
            }
            for (MotionController motionController : frameArrayList.values()) {
                int mode = motionController.getDrawPath();
                if (debugPath > 0 && mode == 0) {
                    mode = 1;
                }
                if (mode != 0) {
                    this.mKeyFrameCount = motionController.buildKeyFrames(this.mKeyFramePoints, this.mPathMode);
                    if (mode >= 1) {
                        int frames = duration / 16;
                        if (this.mPoints == null || this.mPoints.length != frames * 2) {
                            this.mPoints = new float[frames * 2];
                            this.mPath = new Path();
                        }
                        canvas.translate(this.mShadowTranslate, this.mShadowTranslate);
                        this.mPaint.setColor(1996488704);
                        this.mFillPaint.setColor(1996488704);
                        this.mPaintKeyframes.setColor(1996488704);
                        this.mPaintGraph.setColor(1996488704);
                        motionController.buildPath(this.mPoints, frames);
                        drawAll(canvas, mode, this.mKeyFrameCount, motionController);
                        this.mPaint.setColor(-21965);
                        this.mPaintKeyframes.setColor(-2067046);
                        this.mFillPaint.setColor(-2067046);
                        this.mPaintGraph.setColor(-13391360);
                        canvas.translate(-this.mShadowTranslate, -this.mShadowTranslate);
                        drawAll(canvas, mode, this.mKeyFrameCount, motionController);
                        if (mode == 5) {
                            drawRectangle(canvas, motionController);
                        }
                    }
                }
            }
            canvas.restore();
        }

        public void drawAll(Canvas canvas, int mode, int keyFrames, MotionController motionController) {
            if (mode == 4) {
                drawPathAsConfigured(canvas);
            }
            if (mode == 2) {
                drawPathRelative(canvas);
            }
            if (mode == 3) {
                drawPathCartesian(canvas);
            }
            drawBasicPath(canvas);
            drawTicks(canvas, mode, keyFrames, motionController);
        }

        private void drawBasicPath(Canvas canvas) {
            canvas.drawLines(this.mPoints, this.mPaint);
        }

        private void drawTicks(Canvas canvas, int mode, int keyFrames, MotionController motionController) {
            int viewWidth;
            int viewHeight;
            if (motionController.mView == null) {
                viewWidth = 0;
                viewHeight = 0;
            } else {
                int viewWidth2 = motionController.mView.getWidth();
                int viewHeight2 = motionController.mView.getHeight();
                viewWidth = viewWidth2;
                viewHeight = viewHeight2;
            }
            for (int i = 1; i < keyFrames - 1; i++) {
                if (mode != 4 || this.mPathMode[i - 1] != 0) {
                    float x = this.mKeyFramePoints[i * 2];
                    float y = this.mKeyFramePoints[(i * 2) + 1];
                    this.mPath.reset();
                    this.mPath.moveTo(x, y + 10.0f);
                    this.mPath.lineTo(x + 10.0f, y);
                    this.mPath.lineTo(x, y - 10.0f);
                    this.mPath.lineTo(x - 10.0f, y);
                    this.mPath.close();
                    motionController.getKeyFrame(i - 1);
                    if (mode == 4) {
                        if (this.mPathMode[i - 1] == 1) {
                            drawPathRelativeTicks(canvas, x - 0.0f, y - 0.0f);
                        } else if (this.mPathMode[i - 1] == 2) {
                            drawPathCartesianTicks(canvas, x - 0.0f, y - 0.0f);
                        } else if (this.mPathMode[i - 1] == 3) {
                            drawPathScreenTicks(canvas, x - 0.0f, y - 0.0f, viewWidth, viewHeight);
                        }
                        canvas.drawPath(this.mPath, this.mFillPaint);
                    }
                    if (mode == 2) {
                        drawPathRelativeTicks(canvas, x - 0.0f, y - 0.0f);
                    }
                    if (mode == 3) {
                        drawPathCartesianTicks(canvas, x - 0.0f, y - 0.0f);
                    }
                    if (mode == 6) {
                        drawPathScreenTicks(canvas, x - 0.0f, y - 0.0f, viewWidth, viewHeight);
                    }
                    if (0.0f == 0.0f && 0.0f == 0.0f) {
                        canvas.drawPath(this.mPath, this.mFillPaint);
                    } else {
                        drawTranslation(canvas, x - 0.0f, y - 0.0f, x, y);
                    }
                }
            }
            if (this.mPoints.length > 1) {
                canvas.drawCircle(this.mPoints[0], this.mPoints[1], 8.0f, this.mPaintKeyframes);
                canvas.drawCircle(this.mPoints[this.mPoints.length - 2], this.mPoints[this.mPoints.length - 1], 8.0f, this.mPaintKeyframes);
            }
        }

        private void drawTranslation(Canvas canvas, float x1, float y1, float x2, float y2) {
            canvas.drawRect(x1, y1, x2, y2, this.mPaintGraph);
            canvas.drawLine(x1, y1, x2, y2, this.mPaintGraph);
        }

        private void drawPathRelative(Canvas canvas) {
            canvas.drawLine(this.mPoints[0], this.mPoints[1], this.mPoints[this.mPoints.length - 2], this.mPoints[this.mPoints.length - 1], this.mPaintGraph);
        }

        private void drawPathAsConfigured(Canvas canvas) {
            boolean path = false;
            boolean cart = false;
            for (int i = 0; i < this.mKeyFrameCount; i++) {
                if (this.mPathMode[i] == 1) {
                    path = true;
                }
                if (this.mPathMode[i] == 2) {
                    cart = true;
                }
            }
            if (path) {
                drawPathRelative(canvas);
            }
            if (cart) {
                drawPathCartesian(canvas);
            }
        }

        private void drawPathRelativeTicks(Canvas canvas, float x, float y) {
            float x1 = this.mPoints[0];
            float y1 = this.mPoints[1];
            float x2 = this.mPoints[this.mPoints.length - 2];
            float y2 = this.mPoints[this.mPoints.length - 1];
            float dist = (float) Math.hypot(x1 - x2, y1 - y2);
            float t = (((x - x1) * (x2 - x1)) + ((y - y1) * (y2 - y1))) / (dist * dist);
            float xp = x1 + ((x2 - x1) * t);
            float yp = y1 + ((y2 - y1) * t);
            Path path = new Path();
            path.moveTo(x, y);
            path.lineTo(xp, yp);
            float len = (float) Math.hypot(xp - x, yp - y);
            String text = "" + (((int) ((len * 100.0f) / dist)) / 100.0f);
            getTextBounds(text, this.mTextPaint);
            float off = (len / 2.0f) - (this.mBounds.width() / 2);
            canvas.drawTextOnPath(text, path, off, -20.0f, this.mTextPaint);
            canvas.drawLine(x, y, xp, yp, this.mPaintGraph);
        }

        void getTextBounds(String text, Paint paint) {
            paint.getTextBounds(text, 0, text.length(), this.mBounds);
        }

        private void drawPathCartesian(Canvas canvas) {
            float x1 = this.mPoints[0];
            float y1 = this.mPoints[1];
            float x2 = this.mPoints[this.mPoints.length - 2];
            float y2 = this.mPoints[this.mPoints.length - 1];
            canvas.drawLine(Math.min(x1, x2), Math.max(y1, y2), Math.max(x1, x2), Math.max(y1, y2), this.mPaintGraph);
            canvas.drawLine(Math.min(x1, x2), Math.min(y1, y2), Math.min(x1, x2), Math.max(y1, y2), this.mPaintGraph);
        }

        private void drawPathCartesianTicks(Canvas canvas, float x, float y) {
            float x1 = this.mPoints[0];
            float y1 = this.mPoints[1];
            float x2 = this.mPoints[this.mPoints.length - 2];
            float y2 = this.mPoints[this.mPoints.length - 1];
            float minx = Math.min(x1, x2);
            float maxy = Math.max(y1, y2);
            float xgap = x - Math.min(x1, x2);
            float ygap = Math.max(y1, y2) - y;
            String text = "" + (((int) (((xgap * 100.0f) / Math.abs(x2 - x1)) + 0.5d)) / 100.0f);
            getTextBounds(text, this.mTextPaint);
            float off = (xgap / 2.0f) - (this.mBounds.width() / 2);
            canvas.drawText(text, off + minx, y - 20.0f, this.mTextPaint);
            canvas.drawLine(x, y, Math.min(x1, x2), y, this.mPaintGraph);
            String text2 = "" + (((int) (((ygap * 100.0f) / Math.abs(y2 - y1)) + 0.5d)) / 100.0f);
            getTextBounds(text2, this.mTextPaint);
            float off2 = (ygap / 2.0f) - (this.mBounds.height() / 2);
            canvas.drawText(text2, x + 5.0f, maxy - off2, this.mTextPaint);
            canvas.drawLine(x, y, x, Math.max(y1, y2), this.mPaintGraph);
        }

        private void drawPathScreenTicks(Canvas canvas, float x, float y, int viewWidth, int viewHeight) {
            String text = "" + (((int) ((((x - (viewWidth / 2)) * 100.0f) / (MotionLayout.this.getWidth() - viewWidth)) + 0.5d)) / 100.0f);
            getTextBounds(text, this.mTextPaint);
            float off = (x / 2.0f) - (this.mBounds.width() / 2);
            canvas.drawText(text, off + 0.0f, y - 20.0f, this.mTextPaint);
            canvas.drawLine(x, y, Math.min(0.0f, 1.0f), y, this.mPaintGraph);
            String text2 = "" + (((int) ((((y - (viewHeight / 2)) * 100.0f) / (MotionLayout.this.getHeight() - viewHeight)) + 0.5d)) / 100.0f);
            getTextBounds(text2, this.mTextPaint);
            float off2 = (y / 2.0f) - (this.mBounds.height() / 2);
            canvas.drawText(text2, x + 5.0f, 0.0f - off2, this.mTextPaint);
            canvas.drawLine(x, y, x, Math.max(0.0f, 1.0f), this.mPaintGraph);
        }

        private void drawRectangle(Canvas canvas, MotionController motionController) {
            this.mPath.reset();
            for (int i = 0; i <= 50; i++) {
                float p = i / 50;
                motionController.buildRect(p, this.mRectangle, 0);
                this.mPath.moveTo(this.mRectangle[0], this.mRectangle[1]);
                this.mPath.lineTo(this.mRectangle[2], this.mRectangle[3]);
                this.mPath.lineTo(this.mRectangle[4], this.mRectangle[5]);
                this.mPath.lineTo(this.mRectangle[6], this.mRectangle[7]);
                this.mPath.close();
            }
            this.mPaint.setColor(1140850688);
            canvas.translate(2.0f, 2.0f);
            canvas.drawPath(this.mPath, this.mPaint);
            canvas.translate(-2.0f, -2.0f);
            this.mPaint.setColor(SupportMenu.CATEGORY_MASK);
            canvas.drawPath(this.mPath, this.mPaint);
        }
    }

    private void debugPos() {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            Log.v(TAG, " " + Debug.getLocation() + " " + Debug.getName(this) + " " + Debug.getName(getContext(), this.mCurrentState) + " " + Debug.getName(child) + child.getLeft() + " " + child.getTop());
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        evaluate(false);
        super.dispatchDraw(canvas);
        if (this.mScene == null) {
            return;
        }
        if ((this.mDebugPath & 1) == 1 && !isInEditMode()) {
            this.mFrames++;
            long currentDrawTime = getNanoTime();
            if (this.mLastDrawTime != -1) {
                long delay = currentDrawTime - this.mLastDrawTime;
                if (delay > 200000000) {
                    float fps = this.mFrames / (((float) delay) * 1.0E-9f);
                    this.mLastFps = ((int) (fps * 100.0f)) / 100.0f;
                    this.mFrames = 0;
                    this.mLastDrawTime = currentDrawTime;
                }
            } else {
                this.mLastDrawTime = currentDrawTime;
            }
            Paint paint = new Paint();
            paint.setTextSize(42.0f);
            float p = ((int) (getProgress() * 1000.0f)) / 10.0f;
            String str = (this.mLastFps + " fps " + Debug.getState(this, this.mBeginState) + " -> ") + Debug.getState(this, this.mEndState) + " (progress: " + p + " ) state=" + (this.mCurrentState == -1 ? "undefined" : Debug.getState(this, this.mCurrentState));
            paint.setColor(ViewCompat.MEASURED_STATE_MASK);
            canvas.drawText(str, 11.0f, getHeight() - 29, paint);
            paint.setColor(-7864184);
            canvas.drawText(str, 10.0f, getHeight() - 30, paint);
        }
        if (this.mDebugPath > 1) {
            if (this.mDevModeDraw == null) {
                this.mDevModeDraw = new DevModeDraw();
            }
            this.mDevModeDraw.draw(canvas, this.mFrameArrayList, this.mScene.getDuration(), this.mDebugPath);
        }
    }

    private void evaluateLayout() {
        int i;
        float dir = Math.signum(this.mTransitionGoalPosition - this.mTransitionLastPosition);
        long currentTime = getNanoTime();
        float deltaPos = 0.0f;
        if (!(this.mInterpolator instanceof StopLogic)) {
            deltaPos = ((((float) (currentTime - this.mTransitionLastTime)) * dir) * 1.0E-9f) / this.mTransitionDuration;
        }
        float position = this.mTransitionLastPosition + deltaPos;
        boolean done = false;
        if (this.mTransitionInstantly) {
            position = this.mTransitionGoalPosition;
        }
        if ((dir > 0.0f && position >= this.mTransitionGoalPosition) || (dir <= 0.0f && position <= this.mTransitionGoalPosition)) {
            position = this.mTransitionGoalPosition;
            done = true;
        }
        if (this.mInterpolator != null && !done) {
            if (this.mTemporalInterpolator) {
                float time = ((float) (currentTime - this.mAnimationStartTime)) * 1.0E-9f;
                position = this.mInterpolator.getInterpolation(time);
            } else {
                position = this.mInterpolator.getInterpolation(position);
            }
        }
        if ((dir > 0.0f && position >= this.mTransitionGoalPosition) || (dir <= 0.0f && position <= this.mTransitionGoalPosition)) {
            position = this.mTransitionGoalPosition;
        }
        this.mPostInterpolationPosition = position;
        int n = getChildCount();
        long time2 = getNanoTime();
        int i2 = 0;
        while (i2 < n) {
            View child = getChildAt(i2);
            MotionController frame = this.mFrameArrayList.get(child);
            if (frame != null) {
                i = i2;
                frame.interpolate(child, position, time2, this.mKeyCache);
            } else {
                i = i2;
            }
            i2 = i + 1;
        }
        if (this.mMeasureDuringTransition) {
            requestLayout();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void evaluate(boolean force) {
        if (this.mTransitionLastTime == -1) {
            this.mTransitionLastTime = getNanoTime();
        }
        if (this.mTransitionLastPosition > 0.0f && this.mTransitionLastPosition < 1.0f) {
            this.mCurrentState = -1;
        }
        boolean newState = false;
        if (this.mKeepAnimating || (this.mInTransition && (force || this.mTransitionGoalPosition != this.mTransitionLastPosition))) {
            float dir = Math.signum(this.mTransitionGoalPosition - this.mTransitionLastPosition);
            long currentTime = getNanoTime();
            float deltaPos = 0.0f;
            if (!(this.mInterpolator instanceof MotionInterpolator)) {
                deltaPos = ((((float) (currentTime - this.mTransitionLastTime)) * dir) * 1.0E-9f) / this.mTransitionDuration;
                this.mLastVelocity = deltaPos;
            }
            float position = this.mTransitionLastPosition + deltaPos;
            boolean done = false;
            if (this.mTransitionInstantly) {
                position = this.mTransitionGoalPosition;
            }
            if ((dir > 0.0f && position >= this.mTransitionGoalPosition) || (dir <= 0.0f && position <= this.mTransitionGoalPosition)) {
                position = this.mTransitionGoalPosition;
                this.mInTransition = false;
                done = true;
            }
            this.mTransitionLastPosition = position;
            this.mTransitionPosition = position;
            this.mTransitionLastTime = currentTime;
            if (this.mInterpolator != null && !done) {
                if (this.mTemporalInterpolator) {
                    float time = ((float) (currentTime - this.mAnimationStartTime)) * 1.0E-9f;
                    float position2 = this.mInterpolator.getInterpolation(time);
                    this.mTransitionLastPosition = position2;
                    this.mTransitionLastTime = currentTime;
                    if (!(this.mInterpolator instanceof MotionInterpolator)) {
                        position = position2;
                    } else {
                        float lastVelocity = ((MotionInterpolator) this.mInterpolator).getVelocity();
                        this.mLastVelocity = lastVelocity;
                        if (Math.abs(lastVelocity) * this.mTransitionDuration <= EPSILON) {
                            this.mInTransition = false;
                        }
                        if (lastVelocity > 0.0f && position2 >= 1.0f) {
                            position2 = 1.0f;
                            this.mTransitionLastPosition = 1.0f;
                            this.mInTransition = false;
                        }
                        if (lastVelocity < 0.0f && position2 <= 0.0f) {
                            this.mTransitionLastPosition = 0.0f;
                            this.mInTransition = false;
                            position = 0.0f;
                        } else {
                            position = position2;
                        }
                    }
                } else {
                    float p2 = position;
                    position = this.mInterpolator.getInterpolation(position);
                    if (this.mInterpolator instanceof MotionInterpolator) {
                        this.mLastVelocity = ((MotionInterpolator) this.mInterpolator).getVelocity();
                    } else {
                        this.mLastVelocity = ((this.mInterpolator.getInterpolation(p2 + deltaPos) - position) * dir) / deltaPos;
                    }
                }
            }
            float p22 = this.mLastVelocity;
            if (Math.abs(p22) > EPSILON) {
                setState(TransitionState.MOVING);
            }
            if ((dir > 0.0f && position >= this.mTransitionGoalPosition) || (dir <= 0.0f && position <= this.mTransitionGoalPosition)) {
                position = this.mTransitionGoalPosition;
                this.mInTransition = false;
            }
            if (position >= 1.0f || position <= 0.0f) {
                this.mInTransition = false;
                setState(TransitionState.FINISHED);
            }
            int n = getChildCount();
            this.mKeepAnimating = false;
            long time2 = getNanoTime();
            this.mPostInterpolationPosition = position;
            for (int i = 0; i < n; i++) {
                View child = getChildAt(i);
                MotionController frame = this.mFrameArrayList.get(child);
                if (frame != null) {
                    this.mKeepAnimating = frame.interpolate(child, position, time2, this.mKeyCache) | this.mKeepAnimating;
                }
            }
            int i2 = (dir > 0.0f ? 1 : (dir == 0.0f ? 0 : -1));
            boolean end = (i2 > 0 && position >= this.mTransitionGoalPosition) || (dir <= 0.0f && position <= this.mTransitionGoalPosition);
            if (!this.mKeepAnimating && !this.mInTransition && end) {
                setState(TransitionState.FINISHED);
            }
            if (this.mMeasureDuringTransition) {
                requestLayout();
            }
            this.mKeepAnimating |= !end;
            if (position <= 0.0f && this.mBeginState != -1 && this.mCurrentState != this.mBeginState) {
                newState = true;
                this.mCurrentState = this.mBeginState;
                ConstraintSet set = this.mScene.getConstraintSet(this.mBeginState);
                set.applyCustomAttributes(this);
                setState(TransitionState.FINISHED);
            }
            if (position >= 1.0d && this.mCurrentState != this.mEndState) {
                newState = true;
                this.mCurrentState = this.mEndState;
                ConstraintSet set2 = this.mScene.getConstraintSet(this.mEndState);
                set2.applyCustomAttributes(this);
                setState(TransitionState.FINISHED);
            }
            if (this.mKeepAnimating || this.mInTransition) {
                invalidate();
            } else if ((dir > 0.0f && position == 1.0f) || (dir < 0.0f && position == 0.0f)) {
                setState(TransitionState.FINISHED);
            }
            if ((!this.mKeepAnimating && this.mInTransition && dir > 0.0f && position == 1.0f) || (dir < 0.0f && position == 0.0f)) {
                onNewStateAttachHandlers();
            }
        }
        if (this.mTransitionLastPosition >= 1.0f) {
            if (this.mCurrentState != this.mEndState) {
                newState = true;
            }
            this.mCurrentState = this.mEndState;
        } else if (this.mTransitionLastPosition <= 0.0f) {
            if (this.mCurrentState != this.mBeginState) {
                newState = true;
            }
            this.mCurrentState = this.mBeginState;
        }
        this.mNeedsFireTransitionCompleted |= newState;
        if (newState && !this.mInLayout) {
            requestLayout();
        }
        this.mTransitionPosition = this.mTransitionLastPosition;
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        this.mInLayout = true;
        try {
            if (this.mScene == null) {
                super.onLayout(changed, left, top, right, bottom);
                return;
            }
            int w = right - left;
            int h = bottom - top;
            if (this.mLastLayoutWidth != w || this.mLastLayoutHeight != h) {
                rebuildScene();
                evaluate(true);
            }
            this.mLastLayoutWidth = w;
            this.mLastLayoutHeight = h;
            this.mOldWidth = w;
            this.mOldHeight = h;
        } finally {
            this.mInLayout = false;
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout
    protected void parseLayoutDescription(int id) {
        this.mConstraintLayoutSpec = null;
    }

    private void init(AttributeSet attrs) {
        IS_IN_EDIT_MODE = isInEditMode();
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MotionLayout);
            int N = a.getIndexCount();
            boolean apply = true;
            for (int i = 0; i < N; i++) {
                int attr = a.getIndex(i);
                if (attr == R.styleable.MotionLayout_layoutDescription) {
                    int n = a.getResourceId(attr, -1);
                    this.mScene = new MotionScene(getContext(), this, n);
                } else if (attr == R.styleable.MotionLayout_currentState) {
                    this.mCurrentState = a.getResourceId(attr, -1);
                } else if (attr == R.styleable.MotionLayout_motionProgress) {
                    this.mTransitionGoalPosition = a.getFloat(attr, 0.0f);
                    this.mInTransition = true;
                } else if (attr == R.styleable.MotionLayout_applyMotionScene) {
                    apply = a.getBoolean(attr, apply);
                } else if (attr == R.styleable.MotionLayout_showPaths) {
                    if (this.mDebugPath == 0) {
                        this.mDebugPath = a.getBoolean(attr, false) ? 2 : 0;
                    }
                } else if (attr == R.styleable.MotionLayout_motionDebug) {
                    this.mDebugPath = a.getInt(attr, 0);
                }
            }
            a.recycle();
            if (this.mScene == null) {
                Log.e(TAG, "WARNING NO app:layoutDescription tag");
            }
            if (!apply) {
                this.mScene = null;
            }
        }
        if (this.mDebugPath != 0) {
            checkStructure();
        }
        if (this.mCurrentState == -1 && this.mScene != null) {
            this.mCurrentState = this.mScene.getStartId();
            this.mBeginState = this.mScene.getStartId();
            this.mEndState = this.mScene.getEndId();
        }
    }

    public void setScene(MotionScene scene) {
        this.mScene = scene;
        this.mScene.setRtl(isRtl());
        rebuildScene();
    }

    private void checkStructure() {
        if (this.mScene == null) {
            Log.e(TAG, "CHECK: motion scene not set! set \"app:layoutDescription=\"@xml/file\"");
            return;
        }
        checkStructure(this.mScene.getStartId(), this.mScene.getConstraintSet(this.mScene.getStartId()));
        SparseIntArray startToEnd = new SparseIntArray();
        SparseIntArray endToStart = new SparseIntArray();
        Iterator<MotionScene.Transition> it = this.mScene.getDefinedTransitions().iterator();
        while (it.hasNext()) {
            MotionScene.Transition definedTransition = it.next();
            if (definedTransition == this.mScene.mCurrentTransition) {
                Log.v(TAG, "CHECK: CURRENT");
            }
            checkStructure(definedTransition);
            int startId = definedTransition.getStartConstraintSetId();
            int endId = definedTransition.getEndConstraintSetId();
            String startString = Debug.getName(getContext(), startId);
            String endString = Debug.getName(getContext(), endId);
            if (startToEnd.get(startId) == endId) {
                Log.e(TAG, "CHECK: two transitions with the same start and end " + startString + "->" + endString);
            }
            if (endToStart.get(endId) == startId) {
                Log.e(TAG, "CHECK: you can't have reverse transitions" + startString + "->" + endString);
            }
            startToEnd.put(startId, endId);
            endToStart.put(endId, startId);
            if (this.mScene.getConstraintSet(startId) == null) {
                Log.e(TAG, " no such constraintSetStart " + startString);
            }
            if (this.mScene.getConstraintSet(endId) == null) {
                Log.e(TAG, " no such constraintSetEnd " + startString);
            }
        }
    }

    private void checkStructure(int csetId, ConstraintSet set) {
        String setName = Debug.getName(getContext(), csetId);
        int size = getChildCount();
        for (int i = 0; i < size; i++) {
            View v = getChildAt(i);
            int id = v.getId();
            if (id == -1) {
                Log.w(TAG, "CHECK: " + setName + " ALL VIEWS SHOULD HAVE ID's " + v.getClass().getName() + " does not!");
            }
            ConstraintSet.Constraint c = set.getConstraint(id);
            if (c == null) {
                Log.w(TAG, "CHECK: " + setName + " NO CONSTRAINTS for " + Debug.getName(v));
            }
        }
        int[] ids = set.getKnownIds();
        for (int i2 = 0; i2 < ids.length; i2++) {
            int id2 = ids[i2];
            String idString = Debug.getName(getContext(), id2);
            if (findViewById(ids[i2]) == null) {
                Log.w(TAG, "CHECK: " + setName + " NO View matches id " + idString);
            }
            if (set.getHeight(id2) == -1) {
                Log.w(TAG, "CHECK: " + setName + "(" + idString + ") no LAYOUT_HEIGHT");
            }
            if (set.getWidth(id2) == -1) {
                Log.w(TAG, "CHECK: " + setName + "(" + idString + ") no LAYOUT_HEIGHT");
            }
        }
    }

    private void checkStructure(MotionScene.Transition transition) {
        Log.v(TAG, "CHECK: transition = " + transition.debugString(getContext()));
        Log.v(TAG, "CHECK: transition.setDuration = " + transition.getDuration());
        if (transition.getStartConstraintSetId() == transition.getEndConstraintSetId()) {
            Log.e(TAG, "CHECK: start and end constraint set should not be the same!");
        }
    }

    public void setDebugMode(int debugMode) {
        this.mDebugPath = debugMode;
        invalidate();
    }

    public void getDebugMode(boolean showPaths) {
        this.mDebugPath = showPaths ? 2 : 1;
        invalidate();
    }

    private boolean handlesTouchEvent(float x, float y, View view, MotionEvent event) {
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            int count = group.getChildCount();
            for (int i = 0; i < count; i++) {
                View child = group.getChildAt(i);
                if (handlesTouchEvent(view.getLeft() + x, view.getTop() + y, child, event)) {
                    return true;
                }
            }
        }
        this.mBoundsCheck.set(view.getLeft() + x, view.getTop() + y, view.getRight() + x, view.getBottom() + y);
        return event.getAction() == 0 ? this.mBoundsCheck.contains(event.getX(), event.getY()) && view.onTouchEvent(event) : view.onTouchEvent(event);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent event) {
        MotionScene.Transition currentTransition;
        TouchResponse touchResponse;
        int regionId;
        RectF region;
        if (this.mScene != null && this.mInteractionEnabled && (currentTransition = this.mScene.mCurrentTransition) != null && currentTransition.isEnabled() && (touchResponse = currentTransition.getTouchResponse()) != null && ((event.getAction() != 0 || (region = touchResponse.getTouchRegion(this, new RectF())) == null || region.contains(event.getX(), event.getY())) && (regionId = touchResponse.getTouchRegionId()) != -1)) {
            if (this.mRegionView == null || this.mRegionView.getId() != regionId) {
                this.mRegionView = findViewById(regionId);
            }
            if (this.mRegionView != null) {
                this.mBoundsCheck.set(this.mRegionView.getLeft(), this.mRegionView.getTop(), this.mRegionView.getRight(), this.mRegionView.getBottom());
                if (this.mBoundsCheck.contains(event.getX(), event.getY()) && !handlesTouchEvent(0.0f, 0.0f, this.mRegionView, event)) {
                    return onTouchEvent(event);
                }
            }
        }
        return false;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        if (this.mScene != null && this.mInteractionEnabled && this.mScene.supportTouch()) {
            MotionScene.Transition currentTransition = this.mScene.mCurrentTransition;
            if (currentTransition != null && !currentTransition.isEnabled()) {
                return super.onTouchEvent(event);
            }
            this.mScene.processTouchEvent(event, getCurrentState(), this);
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mScene != null && this.mCurrentState != -1) {
            ConstraintSet cSet = this.mScene.getConstraintSet(this.mCurrentState);
            this.mScene.readFallback(this);
            if (cSet != null) {
                cSet.applyTo(this);
            }
            this.mBeginState = this.mCurrentState;
        }
        onNewStateAttachHandlers();
        if (this.mStateCache != null) {
            this.mStateCache.apply();
        }
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int layoutDirection) {
        if (this.mScene != null) {
            this.mScene.setRtl(isRtl());
        }
    }

    private void onNewStateAttachHandlers() {
        if (this.mScene == null) {
            return;
        }
        if (this.mScene.autoTransition(this, this.mCurrentState)) {
            requestLayout();
            return;
        }
        if (this.mCurrentState != -1) {
            this.mScene.addOnClickListeners(this, this.mCurrentState);
        }
        if (this.mScene.supportTouch()) {
            this.mScene.setupTouch();
        }
    }

    public int getCurrentState() {
        return this.mCurrentState;
    }

    public float getProgress() {
        return this.mTransitionLastPosition;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void getAnchorDpDt(int mTouchAnchorId, float pos, float locationX, float locationY, float[] mAnchorDpDt) {
        HashMap<View, MotionController> hashMap = this.mFrameArrayList;
        View v = getViewById(mTouchAnchorId);
        MotionController f = hashMap.get(v);
        if (f != null) {
            f.getDpDt(pos, locationX, locationY, mAnchorDpDt);
            float y = v.getY();
            float deltaPos = pos - this.lastPos;
            float deltaY = y - this.lastY;
            if (deltaPos != 0.0f) {
                float f2 = deltaY / deltaPos;
            }
            this.lastPos = pos;
            this.lastY = y;
            return;
        }
        String idName = v == null ? "" + mTouchAnchorId : v.getContext().getResources().getResourceName(mTouchAnchorId);
        Log.w(TAG, "WARNING could not find view id " + idName);
    }

    public long getTransitionTimeMs() {
        if (this.mScene != null) {
            this.mTransitionDuration = this.mScene.getDuration() / 1000.0f;
        }
        return this.mTransitionDuration * 1000.0f;
    }

    public void setTransitionListener(TransitionListener listener) {
        this.mTransitionListener = listener;
    }

    public void addTransitionListener(TransitionListener listener) {
        if (this.mTransitionListeners == null) {
            this.mTransitionListeners = new ArrayList<>();
        }
        this.mTransitionListeners.add(listener);
    }

    public boolean removeTransitionListener(TransitionListener listener) {
        if (this.mTransitionListeners == null) {
            return false;
        }
        return this.mTransitionListeners.remove(listener);
    }

    public void fireTrigger(int triggerId, boolean positive, float progress) {
        if (this.mTransitionListener != null) {
            this.mTransitionListener.onTransitionTrigger(this, triggerId, positive, progress);
        }
        if (this.mTransitionListeners != null) {
            Iterator<TransitionListener> it = this.mTransitionListeners.iterator();
            while (it.hasNext()) {
                TransitionListener listeners = it.next();
                listeners.onTransitionTrigger(this, triggerId, positive, progress);
            }
        }
    }

    private void fireTransitionChange() {
        if ((this.mTransitionListener != null || (this.mTransitionListeners != null && !this.mTransitionListeners.isEmpty())) && this.mListenerPosition != this.mTransitionPosition) {
            if (this.mListenerState != -1) {
                if (this.mTransitionListener != null) {
                    this.mTransitionListener.onTransitionStarted(this, this.mBeginState, this.mEndState);
                }
                if (this.mTransitionListeners != null) {
                    Iterator<TransitionListener> it = this.mTransitionListeners.iterator();
                    while (it.hasNext()) {
                        TransitionListener listeners = it.next();
                        listeners.onTransitionStarted(this, this.mBeginState, this.mEndState);
                    }
                }
                this.mIsAnimating = true;
            }
            this.mListenerState = -1;
            this.mListenerPosition = this.mTransitionPosition;
            if (this.mTransitionListener != null) {
                this.mTransitionListener.onTransitionChange(this, this.mBeginState, this.mEndState, this.mTransitionPosition);
            }
            if (this.mTransitionListeners != null) {
                Iterator<TransitionListener> it2 = this.mTransitionListeners.iterator();
                while (it2.hasNext()) {
                    TransitionListener listeners2 = it2.next();
                    listeners2.onTransitionChange(this, this.mBeginState, this.mEndState, this.mTransitionPosition);
                }
            }
            this.mIsAnimating = true;
        }
    }

    protected void fireTransitionCompleted() {
        if ((this.mTransitionListener != null || (this.mTransitionListeners != null && !this.mTransitionListeners.isEmpty())) && this.mListenerState == -1) {
            this.mListenerState = this.mCurrentState;
            int lastState = -1;
            if (!this.mTransitionCompleted.isEmpty()) {
                lastState = this.mTransitionCompleted.get(this.mTransitionCompleted.size() - 1).intValue();
            }
            if (lastState != this.mCurrentState && this.mCurrentState != -1) {
                this.mTransitionCompleted.add(Integer.valueOf(this.mCurrentState));
            }
        }
        processTransitionCompleted();
    }

    private void processTransitionCompleted() {
        if (this.mTransitionListener == null && (this.mTransitionListeners == null || this.mTransitionListeners.isEmpty())) {
            return;
        }
        this.mIsAnimating = false;
        Iterator<Integer> it = this.mTransitionCompleted.iterator();
        while (it.hasNext()) {
            Integer state = it.next();
            if (this.mTransitionListener != null) {
                this.mTransitionListener.onTransitionCompleted(this, state.intValue());
            }
            if (this.mTransitionListeners != null) {
                Iterator<TransitionListener> it2 = this.mTransitionListeners.iterator();
                while (it2.hasNext()) {
                    TransitionListener listeners = it2.next();
                    listeners.onTransitionCompleted(this, state.intValue());
                }
            }
        }
        this.mTransitionCompleted.clear();
    }

    public DesignTool getDesignTool() {
        if (this.mDesignTool == null) {
            this.mDesignTool = new DesignTool(this);
        }
        return this.mDesignTool;
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public void onViewAdded(View view) {
        super.onViewAdded(view);
        if (view instanceof MotionHelper) {
            MotionHelper helper = (MotionHelper) view;
            if (this.mTransitionListeners == null) {
                this.mTransitionListeners = new ArrayList<>();
            }
            this.mTransitionListeners.add(helper);
            if (helper.isUsedOnShow()) {
                if (this.mOnShowHelpers == null) {
                    this.mOnShowHelpers = new ArrayList<>();
                }
                this.mOnShowHelpers.add(helper);
            }
            if (helper.isUseOnHide()) {
                if (this.mOnHideHelpers == null) {
                    this.mOnHideHelpers = new ArrayList<>();
                }
                this.mOnHideHelpers.add(helper);
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        if (this.mOnShowHelpers != null) {
            this.mOnShowHelpers.remove(view);
        }
        if (this.mOnHideHelpers != null) {
            this.mOnHideHelpers.remove(view);
        }
    }

    public void setOnShow(float progress) {
        if (this.mOnShowHelpers != null) {
            int count = this.mOnShowHelpers.size();
            for (int i = 0; i < count; i++) {
                MotionHelper helper = this.mOnShowHelpers.get(i);
                helper.setProgress(progress);
            }
        }
    }

    public void setOnHide(float progress) {
        if (this.mOnHideHelpers != null) {
            int count = this.mOnHideHelpers.size();
            for (int i = 0; i < count; i++) {
                MotionHelper helper = this.mOnHideHelpers.get(i);
                helper.setProgress(progress);
            }
        }
    }

    public int[] getConstraintSetIds() {
        if (this.mScene == null) {
            return null;
        }
        return this.mScene.getConstraintSetIds();
    }

    public ConstraintSet getConstraintSet(int id) {
        if (this.mScene == null) {
            return null;
        }
        return this.mScene.getConstraintSet(id);
    }

    @Deprecated
    public void rebuildMotion() {
        Log.e(TAG, "This method is deprecated. Please call rebuildScene() instead.");
        rebuildScene();
    }

    public void rebuildScene() {
        this.mModel.reEvaluateState();
        invalidate();
    }

    public void updateState(int stateId, ConstraintSet set) {
        if (this.mScene != null) {
            this.mScene.setConstraintSet(stateId, set);
        }
        updateState();
        if (this.mCurrentState == stateId) {
            set.applyTo(this);
        }
    }

    public void updateState() {
        this.mModel.initFrom(this.mLayoutWidget, this.mScene.getConstraintSet(this.mBeginState), this.mScene.getConstraintSet(this.mEndState));
        rebuildScene();
    }

    public ArrayList<MotionScene.Transition> getDefinedTransitions() {
        if (this.mScene == null) {
            return null;
        }
        return this.mScene.getDefinedTransitions();
    }

    public int getStartState() {
        return this.mBeginState;
    }

    public int getEndState() {
        return this.mEndState;
    }

    public float getTargetPosition() {
        return this.mTransitionGoalPosition;
    }

    public void setTransitionDuration(int milliseconds) {
        if (this.mScene == null) {
            Log.e(TAG, "MotionScene not defined");
        } else {
            this.mScene.setDuration(milliseconds);
        }
    }

    public MotionScene.Transition getTransition(int id) {
        return this.mScene.getTransitionById(id);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int lookUpConstraintId(String id) {
        if (this.mScene == null) {
            return 0;
        }
        return this.mScene.lookUpConstraintId(id);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getConstraintSetNames(int id) {
        if (this.mScene == null) {
            return null;
        }
        return this.mScene.lookUpConstraintName(id);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void disableAutoTransition(boolean disable) {
        if (this.mScene == null) {
            return;
        }
        this.mScene.disableAutoTransition(disable);
    }

    public void setInteractionEnabled(boolean enabled) {
        this.mInteractionEnabled = enabled;
    }

    public boolean isInteractionEnabled() {
        return this.mInteractionEnabled;
    }

    private void fireTransitionStarted(MotionLayout motionLayout, int mBeginState, int mEndState) {
        if (this.mTransitionListener != null) {
            this.mTransitionListener.onTransitionStarted(this, mBeginState, mEndState);
        }
        if (this.mTransitionListeners != null) {
            Iterator<TransitionListener> it = this.mTransitionListeners.iterator();
            while (it.hasNext()) {
                TransitionListener listeners = it.next();
                listeners.onTransitionStarted(motionLayout, mBeginState, mEndState);
            }
        }
    }
}
