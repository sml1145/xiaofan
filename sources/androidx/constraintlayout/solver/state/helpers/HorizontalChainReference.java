package androidx.constraintlayout.solver.state.helpers;

import androidx.constraintlayout.solver.state.ConstraintReference;
import androidx.constraintlayout.solver.state.State;
import java.util.Iterator;
/* loaded from: classes.dex */
public class HorizontalChainReference extends ChainReference {
    private Object mEndToEnd;
    private Object mEndToStart;
    private Object mStartToEnd;
    private Object mStartToStart;

    public HorizontalChainReference(State state) {
        super(state, State.Helper.HORIZONTAL_CHAIN);
    }

    @Override // androidx.constraintlayout.solver.state.HelperReference
    public void apply() {
        ConstraintReference first = null;
        ConstraintReference previous = null;
        Iterator<Object> it = this.mReferences.iterator();
        while (it.hasNext()) {
            Object key = it.next();
            this.mState.constraints(key).clearHorizontal();
        }
        Iterator<Object> it2 = this.mReferences.iterator();
        while (it2.hasNext()) {
            Object key2 = it2.next();
            ConstraintReference reference = this.mState.constraints(key2);
            if (first == null) {
                first = reference;
                if (this.mStartToStart != null) {
                    first.startToStart(this.mStartToStart);
                } else if (this.mStartToEnd != null) {
                    first.startToEnd(this.mStartToEnd);
                } else {
                    first.startToStart(State.PARENT);
                }
            }
            if (previous != null) {
                previous.endToStart(reference.getKey());
                reference.startToEnd(previous.getKey());
            }
            previous = reference;
        }
        if (previous != null) {
            if (this.mEndToStart != null) {
                previous.endToStart(this.mEndToStart);
            } else if (this.mEndToEnd != null) {
                previous.endToEnd(this.mEndToEnd);
            } else {
                previous.endToEnd(State.PARENT);
            }
        }
        if (first != null && this.mBias != 0.5f) {
            first.horizontalBias(this.mBias);
        }
        switch (this.mStyle) {
            case SPREAD:
                first.setHorizontalChainStyle(0);
                return;
            case SPREAD_INSIDE:
                first.setHorizontalChainStyle(1);
                return;
            case PACKED:
                first.setHorizontalChainStyle(2);
                return;
            default:
                return;
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
}
