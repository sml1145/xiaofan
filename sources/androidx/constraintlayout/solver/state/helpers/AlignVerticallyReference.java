package androidx.constraintlayout.solver.state.helpers;

import androidx.constraintlayout.solver.state.ConstraintReference;
import androidx.constraintlayout.solver.state.HelperReference;
import androidx.constraintlayout.solver.state.State;
import java.util.Iterator;
/* loaded from: classes.dex */
public class AlignVerticallyReference extends HelperReference {
    private float mBias;
    private Object mBottomToBottom;
    private Object mBottomToTop;
    private Object mTopToBottom;
    private Object mTopToTop;

    public AlignVerticallyReference(State state) {
        super(state, State.Helper.ALIGN_VERTICALLY);
        this.mBias = 0.5f;
    }

    @Override // androidx.constraintlayout.solver.state.HelperReference
    public void apply() {
        Iterator<Object> it = this.mReferences.iterator();
        while (it.hasNext()) {
            Object key = it.next();
            ConstraintReference reference = this.mState.constraints(key);
            reference.clearVertical();
            if (this.mTopToTop != null) {
                reference.topToTop(this.mTopToTop);
            } else if (this.mTopToBottom != null) {
                reference.topToBottom(this.mTopToBottom);
            } else {
                reference.topToTop(State.PARENT);
            }
            if (this.mBottomToTop != null) {
                reference.bottomToTop(this.mBottomToTop);
            } else if (this.mBottomToBottom != null) {
                reference.bottomToBottom(this.mBottomToBottom);
            } else {
                reference.bottomToBottom(State.PARENT);
            }
            if (this.mBias != 0.5f) {
                reference.verticalBias(this.mBias);
            }
        }
    }

    public void topToTop(Object target) {
        this.mTopToTop = target;
    }

    public void topToBottom(Object target) {
        this.mTopToBottom = target;
    }

    public void bottomToTop(Object target) {
        this.mBottomToTop = target;
    }

    public void bottomToBottom(Object target) {
        this.mBottomToBottom = target;
    }

    public void bias(float bias) {
        this.mBias = bias;
    }
}
