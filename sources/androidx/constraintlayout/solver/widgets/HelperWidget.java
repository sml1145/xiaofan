package androidx.constraintlayout.solver.widgets;

import java.util.Arrays;
import java.util.HashMap;
/* loaded from: classes.dex */
public class HelperWidget extends ConstraintWidget implements Helper {
    public ConstraintWidget[] mWidgets = new ConstraintWidget[4];
    public int mWidgetsCount = 0;

    @Override // androidx.constraintlayout.solver.widgets.Helper
    public void updateConstraints(ConstraintWidgetContainer container) {
    }

    @Override // androidx.constraintlayout.solver.widgets.Helper
    public void add(ConstraintWidget widget) {
        if (widget == this || widget == null) {
            return;
        }
        if (this.mWidgetsCount + 1 > this.mWidgets.length) {
            this.mWidgets = (ConstraintWidget[]) Arrays.copyOf(this.mWidgets, this.mWidgets.length * 2);
        }
        this.mWidgets[this.mWidgetsCount] = widget;
        this.mWidgetsCount++;
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void copy(ConstraintWidget src, HashMap<ConstraintWidget, ConstraintWidget> map) {
        super.copy(src, map);
        HelperWidget srcHelper = (HelperWidget) src;
        this.mWidgetsCount = 0;
        int count = srcHelper.mWidgetsCount;
        for (int i = 0; i < count; i++) {
            add(map.get(srcHelper.mWidgets[i]));
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.Helper
    public void removeAllIds() {
        this.mWidgetsCount = 0;
        Arrays.fill(this.mWidgets, (Object) null);
    }
}
