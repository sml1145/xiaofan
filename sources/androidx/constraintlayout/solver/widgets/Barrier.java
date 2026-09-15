package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.HashMap;
/* loaded from: classes.dex */
public class Barrier extends HelperWidget {
    public static final int BOTTOM = 3;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int TOP = 2;
    private int mBarrierType = 0;
    private boolean mAllowsGoneWidget = true;
    private int mMargin = 0;

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public boolean allowedInBarrier() {
        return true;
    }

    public int getBarrierType() {
        return this.mBarrierType;
    }

    public void setBarrierType(int barrierType) {
        this.mBarrierType = barrierType;
    }

    public void setAllowsGoneWidget(boolean allowsGoneWidget) {
        this.mAllowsGoneWidget = allowsGoneWidget;
    }

    public boolean allowsGoneWidget() {
        return this.mAllowsGoneWidget;
    }

    @Override // androidx.constraintlayout.solver.widgets.HelperWidget, androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void copy(ConstraintWidget src, HashMap<ConstraintWidget, ConstraintWidget> map) {
        super.copy(src, map);
        Barrier srcBarrier = (Barrier) src;
        this.mBarrierType = srcBarrier.mBarrierType;
        this.mAllowsGoneWidget = srcBarrier.mAllowsGoneWidget;
        this.mMargin = srcBarrier.mMargin;
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public String toString() {
        String debug = "[Barrier] " + getDebugName() + " {";
        for (int i = 0; i < this.mWidgetsCount; i++) {
            ConstraintWidget widget = this.mWidgets[i];
            if (i > 0) {
                debug = debug + ", ";
            }
            debug = debug + widget.getDebugName();
        }
        return debug + "}";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void markWidgets() {
        for (int i = 0; i < this.mWidgetsCount; i++) {
            ConstraintWidget widget = this.mWidgets[i];
            if (this.mBarrierType == 0 || this.mBarrierType == 1) {
                widget.setInBarrier(0, true);
            } else if (this.mBarrierType == 2 || this.mBarrierType == 3) {
                widget.setInBarrier(1, true);
            }
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void addToSolver(LinearSystem system) {
        this.mListAnchors[0] = this.mLeft;
        this.mListAnchors[2] = this.mTop;
        this.mListAnchors[1] = this.mRight;
        this.mListAnchors[3] = this.mBottom;
        for (int i = 0; i < this.mListAnchors.length; i++) {
            this.mListAnchors[i].mSolverVariable = system.createObjectVariable(this.mListAnchors[i]);
        }
        int i2 = this.mBarrierType;
        if (i2 >= 0 && this.mBarrierType < 4) {
            ConstraintAnchor position = this.mListAnchors[this.mBarrierType];
            boolean hasMatchConstraintWidgets = false;
            int i3 = 0;
            while (true) {
                if (i3 >= this.mWidgetsCount) {
                    break;
                }
                ConstraintWidget widget = this.mWidgets[i3];
                if (this.mAllowsGoneWidget || widget.allowedInBarrier()) {
                    if ((this.mBarrierType == 0 || this.mBarrierType == 1) && widget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widget.mLeft.mTarget != null && widget.mRight.mTarget != null) {
                        hasMatchConstraintWidgets = true;
                        break;
                    } else if ((this.mBarrierType == 2 || this.mBarrierType == 3) && widget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widget.mTop.mTarget != null && widget.mBottom.mTarget != null) {
                        hasMatchConstraintWidgets = true;
                        break;
                    }
                }
                i3++;
            }
            boolean mHasHorizontalCenteredDependents = this.mLeft.hasCenteredDependents() || this.mRight.hasCenteredDependents();
            boolean mHasVerticalCenteredDependents = this.mTop.hasCenteredDependents() || this.mBottom.hasCenteredDependents();
            boolean applyEqualityOnReferences = !hasMatchConstraintWidgets && ((this.mBarrierType == 0 && mHasHorizontalCenteredDependents) || ((this.mBarrierType == 2 && mHasVerticalCenteredDependents) || ((this.mBarrierType == 1 && mHasHorizontalCenteredDependents) || (this.mBarrierType == 3 && mHasVerticalCenteredDependents))));
            int equalityOnReferencesStrength = 5;
            if (!applyEqualityOnReferences) {
                equalityOnReferencesStrength = 4;
            }
            for (int i4 = 0; i4 < this.mWidgetsCount; i4++) {
                ConstraintWidget widget2 = this.mWidgets[i4];
                if (this.mAllowsGoneWidget || widget2.allowedInBarrier()) {
                    SolverVariable target = system.createObjectVariable(widget2.mListAnchors[this.mBarrierType]);
                    widget2.mListAnchors[this.mBarrierType].mSolverVariable = target;
                    int margin = 0;
                    if (widget2.mListAnchors[this.mBarrierType].mTarget != null && widget2.mListAnchors[this.mBarrierType].mTarget.mOwner == this) {
                        margin = 0 + widget2.mListAnchors[this.mBarrierType].mMargin;
                    }
                    if (this.mBarrierType == 0 || this.mBarrierType == 2) {
                        system.addLowerBarrier(position.mSolverVariable, target, this.mMargin - margin, hasMatchConstraintWidgets);
                    } else {
                        system.addGreaterBarrier(position.mSolverVariable, target, this.mMargin + margin, hasMatchConstraintWidgets);
                    }
                    system.addEquality(position.mSolverVariable, target, this.mMargin + margin, equalityOnReferencesStrength);
                }
            }
            if (this.mBarrierType == 0) {
                system.addEquality(this.mRight.mSolverVariable, this.mLeft.mSolverVariable, 0, 8);
                system.addEquality(this.mLeft.mSolverVariable, this.mParent.mRight.mSolverVariable, 0, 4);
                system.addEquality(this.mLeft.mSolverVariable, this.mParent.mLeft.mSolverVariable, 0, 0);
            } else if (this.mBarrierType == 1) {
                system.addEquality(this.mLeft.mSolverVariable, this.mRight.mSolverVariable, 0, 8);
                system.addEquality(this.mLeft.mSolverVariable, this.mParent.mLeft.mSolverVariable, 0, 4);
                system.addEquality(this.mLeft.mSolverVariable, this.mParent.mRight.mSolverVariable, 0, 0);
            } else if (this.mBarrierType == 2) {
                system.addEquality(this.mBottom.mSolverVariable, this.mTop.mSolverVariable, 0, 8);
                system.addEquality(this.mTop.mSolverVariable, this.mParent.mBottom.mSolverVariable, 0, 4);
                system.addEquality(this.mTop.mSolverVariable, this.mParent.mTop.mSolverVariable, 0, 0);
            } else if (this.mBarrierType == 3) {
                system.addEquality(this.mTop.mSolverVariable, this.mBottom.mSolverVariable, 0, 8);
                system.addEquality(this.mTop.mSolverVariable, this.mParent.mTop.mSolverVariable, 0, 4);
                system.addEquality(this.mTop.mSolverVariable, this.mParent.mBottom.mSolverVariable, 0, 0);
            }
        }
    }

    public void setMargin(int margin) {
        this.mMargin = margin;
    }

    public int getMargin() {
        return this.mMargin;
    }
}
