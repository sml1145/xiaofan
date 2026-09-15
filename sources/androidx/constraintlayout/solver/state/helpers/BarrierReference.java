package androidx.constraintlayout.solver.state.helpers;

import androidx.constraintlayout.solver.state.HelperReference;
import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.solver.widgets.Barrier;
import androidx.constraintlayout.solver.widgets.HelperWidget;
/* loaded from: classes.dex */
public class BarrierReference extends HelperReference {
    private Barrier mBarrierWidget;
    private State.Direction mDirection;
    private int mMargin;

    public BarrierReference(State state) {
        super(state, State.Helper.BARRIER);
    }

    public void setBarrierDirection(State.Direction barrierDirection) {
        this.mDirection = barrierDirection;
    }

    public void margin(Object value) {
        margin(this.mState.convertDimension(value));
    }

    public void margin(int value) {
        this.mMargin = value;
    }

    @Override // androidx.constraintlayout.solver.state.HelperReference
    public HelperWidget getHelperWidget() {
        if (this.mBarrierWidget == null) {
            this.mBarrierWidget = new Barrier();
        }
        return this.mBarrierWidget;
    }

    @Override // androidx.constraintlayout.solver.state.HelperReference
    public void apply() {
        getHelperWidget();
        int direction = 0;
        switch (this.mDirection) {
            case LEFT:
            case START:
                direction = 0;
                break;
            case RIGHT:
            case END:
                direction = 1;
                break;
            case TOP:
                direction = 2;
                break;
            case BOTTOM:
                direction = 3;
                break;
        }
        this.mBarrierWidget.setBarrierType(direction);
        this.mBarrierWidget.setMargin(this.mMargin);
    }
}
