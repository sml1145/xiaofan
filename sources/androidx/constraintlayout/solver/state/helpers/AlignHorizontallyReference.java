package androidx.constraintlayout.solver.state.helpers;

import androidx.constraintlayout.solver.state.ConstraintReference;
import androidx.constraintlayout.solver.state.HelperReference;
import androidx.constraintlayout.solver.state.State;
import java.util.Iterator;
/* loaded from: classes.dex */
public class AlignHorizontallyReference extends HelperReference {
    private float mBias;
    private Object mEndToEnd;
    private Object mEndToStart;
    private Object mStartToEnd;
    private Object mStartToStart;

    public AlignHorizontallyReference(State state) {
        super(state, State.Helper.ALIGN_VERTICALLY);
        this.mBias = 0.5f;
    }

    @Override // androidx.constraintlayout.solver.state.HelperReference
    public void apply() {
        Iterator<Object> it = this.mReferences.iterator();
        while (it.hasNext()) {
            Object key = it.next();
            ConstraintReference reference = this.mState.constraints(key);
            reference.clearHorizontal();
            if (this.mStartToStart != null) {
                reference.startToStart(this.mStartToStart);
            } else if (this.mStartToEnd != null) {
                reference.startToEnd(this.mStartToEnd);
            } else {
                reference.startToStart(State.PARENT);
            }
            if (this.mEndToStart != null) {
                reference.endToStart(this.mEndToStart);
            } else if (this.mEndToEnd != null) {
                reference.endToEnd(this.mEndToEnd);
            } else {
                reference.endToEnd(State.PARENT);
            }
            if (this.mBias != 0.5f) {
                reference.horizontalBias(this.mBias);
            }
        }
    }

    public void startToStart(Object target) {
        this.mStartToStart = target;
    }

    public void startToEnd(Object target) {
        this.mStartToEnd = target;
    }

    public void endToStart(Object target) {
        this.mEndToStart = target;
    }

    public void endToEnd(Object target) {
        this.mEndToEnd = target;
    }

    public void bias(float bias) {
        this.mBias = bias;
    }
}
