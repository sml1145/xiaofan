package androidx.constraintlayout.solver.state.helpers;

import androidx.constraintlayout.solver.state.ConstraintReference;
import androidx.constraintlayout.solver.state.State;
import java.util.Iterator;
/* loaded from: classes.dex */
public class VerticalChainReference extends ChainReference {
    private Object mBottomToBottom;
    private Object mBottomToTop;
    private Object mTopToBottom;
    private Object mTopToTop;

    public VerticalChainReference(State state) {
        super(state, State.Helper.VERTICAL_CHAIN);
    }

    @Override // androidx.constraintlayout.solver.state.HelperReference
    public void apply() {
        ConstraintReference first = null;
        ConstraintReference previous = null;
        Iterator<Object> it = this.mReferences.iterator();
        while (it.hasNext()) {
            Object key = it.next();
            this.mState.constraints(key).clearVertical();
        }
        Iterator<Object> it2 = this.mReferences.iterator();
        while (it2.hasNext()) {
            Object key2 = it2.next();
            ConstraintReference reference = this.mState.constraints(key2);
            if (first == null) {
                first = reference;
                if (this.mTopToTop != null) {
                    first.topToTop(this.mTopToTop);
                } else if (this.mTopToBottom != null) {
                    first.topToBottom(this.mTopToBottom);
                } else {
                    first.topToTop(State.PARENT);
                }
            }
            if (previous != null) {
                previous.bottomToTop(reference.getKey());
                reference.topToBottom(previous.getKey());
            }
            previous = reference;
        }
        if (previous != null) {
            if (this.mBottomToTop != null) {
                previous.bottomToTop(this.mBottomToTop);
            } else if (this.mBottomToBottom != null) {
                previous.bottomToBottom(this.mBottomToBottom);
            } else {
                previous.bottomToBottom(State.PARENT);
            }
        }
        if (first != null && this.mBias != 0.5f) {
            first.verticalBias(this.mBias);
        }
        switch (this.mStyle) {
            case SPREAD:
                first.setVerticalChainStyle(0);
                return;
            case SPREAD_INSIDE:
                first.setVerticalChainStyle(1);
                return;
            case PACKED:
                first.setVerticalChainStyle(2);
                return;
            default:
                return;
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
}
