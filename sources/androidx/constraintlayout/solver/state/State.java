package androidx.constraintlayout.solver.state;

import androidx.constraintlayout.solver.state.helpers.AlignHorizontallyReference;
import androidx.constraintlayout.solver.state.helpers.AlignVerticallyReference;
import androidx.constraintlayout.solver.state.helpers.BarrierReference;
import androidx.constraintlayout.solver.state.helpers.GuidelineReference;
import androidx.constraintlayout.solver.state.helpers.HorizontalChainReference;
import androidx.constraintlayout.solver.state.helpers.VerticalChainReference;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.solver.widgets.HelperWidget;
import java.util.HashMap;
import java.util.Iterator;
/* loaded from: classes.dex */
public class State {
    static final int CONSTRAINT_RATIO = 2;
    static final int CONSTRAINT_SPREAD = 0;
    static final int CONSTRAINT_WRAP = 1;
    public static final Integer PARENT = 0;
    static final int UNKNOWN = -1;
    protected HashMap<Object, Reference> mReferences = new HashMap<>();
    protected HashMap<Object, HelperReference> mHelperReferences = new HashMap<>();
    public final ConstraintReference mParent = new ConstraintReference(this);
    private int numHelpers = 0;

    /* loaded from: classes.dex */
    public enum Chain {
        SPREAD,
        SPREAD_INSIDE,
        PACKED
    }

    /* loaded from: classes.dex */
    public enum Constraint {
        LEFT_TO_LEFT,
        LEFT_TO_RIGHT,
        RIGHT_TO_LEFT,
        RIGHT_TO_RIGHT,
        START_TO_START,
        START_TO_END,
        END_TO_START,
        END_TO_END,
        TOP_TO_TOP,
        TOP_TO_BOTTOM,
        BOTTOM_TO_TOP,
        BOTTOM_TO_BOTTOM,
        BASELINE_TO_BASELINE,
        CENTER_HORIZONTALLY,
        CENTER_VERTICALLY
    }

    /* loaded from: classes.dex */
    public enum Direction {
        LEFT,
        RIGHT,
        START,
        END,
        TOP,
        BOTTOM
    }

    /* loaded from: classes.dex */
    public enum Helper {
        HORIZONTAL_CHAIN,
        VERTICAL_CHAIN,
        ALIGN_HORIZONTALLY,
        ALIGN_VERTICALLY,
        BARRIER,
        LAYER,
        FLOW
    }

    public State() {
        this.mReferences.put(PARENT, this.mParent);
    }

    public void reset() {
        this.mHelperReferences.clear();
    }

    public int convertDimension(Object value) {
        if (value instanceof Float) {
            return ((Float) value).intValue();
        }
        if (value instanceof Integer) {
            return ((Integer) value).intValue();
        }
        return 0;
    }

    public ConstraintReference createConstraintReference(Object key) {
        return new ConstraintReference(this);
    }

    public State width(Dimension dimension) {
        return setWidth(dimension);
    }

    public State height(Dimension dimension) {
        return setHeight(dimension);
    }

    public State setWidth(Dimension dimension) {
        this.mParent.setWidth(dimension);
        return this;
    }

    public State setHeight(Dimension dimension) {
        this.mParent.setHeight(dimension);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Reference reference(Object key) {
        return this.mReferences.get(key);
    }

    public ConstraintReference constraints(Object key) {
        Reference reference = this.mReferences.get(key);
        if (reference == null) {
            reference = createConstraintReference(key);
            this.mReferences.put(key, reference);
            reference.setKey(key);
        }
        if (reference instanceof ConstraintReference) {
            return (ConstraintReference) reference;
        }
        return null;
    }

    private String createHelperKey() {
        StringBuilder append = new StringBuilder().append("__HELPER_KEY_");
        int i = this.numHelpers;
        this.numHelpers = i + 1;
        return append.append(i).append("__").toString();
    }

    public HelperReference helper(Object key, Helper type) {
        if (key == null) {
            key = createHelperKey();
        }
        HelperReference reference = this.mHelperReferences.get(key);
        if (reference == null) {
            switch (type) {
                case HORIZONTAL_CHAIN:
                    reference = new HorizontalChainReference(this);
                    break;
                case VERTICAL_CHAIN:
                    reference = new VerticalChainReference(this);
                    break;
                case ALIGN_HORIZONTALLY:
                    reference = new AlignHorizontallyReference(this);
                    break;
                case ALIGN_VERTICALLY:
                    reference = new AlignVerticallyReference(this);
                    break;
                case BARRIER:
                    reference = new BarrierReference(this);
                    break;
                default:
                    reference = new HelperReference(this, type);
                    break;
            }
            this.mHelperReferences.put(key, reference);
        }
        return reference;
    }

    public GuidelineReference horizontalGuideline(Object key) {
        return guideline(key, 0);
    }

    public GuidelineReference verticalGuideline(Object key) {
        return guideline(key, 1);
    }

    public GuidelineReference guideline(Object key, int orientation) {
        Reference reference = this.mReferences.get(key);
        if (reference == null) {
            GuidelineReference guidelineReference = new GuidelineReference(this);
            guidelineReference.setOrientation(orientation);
            guidelineReference.setKey(key);
            this.mReferences.put(key, guidelineReference);
            reference = guidelineReference;
        }
        return (GuidelineReference) reference;
    }

    public BarrierReference barrier(Object key, Direction direction) {
        BarrierReference reference = (BarrierReference) helper(key, Helper.BARRIER);
        reference.setBarrierDirection(direction);
        return reference;
    }

    public VerticalChainReference verticalChain(Object... references) {
        VerticalChainReference reference = (VerticalChainReference) helper(null, Helper.VERTICAL_CHAIN);
        reference.add(references);
        return reference;
    }

    public HorizontalChainReference horizontalChain(Object... references) {
        HorizontalChainReference reference = (HorizontalChainReference) helper(null, Helper.HORIZONTAL_CHAIN);
        reference.add(references);
        return reference;
    }

    public AlignHorizontallyReference centerHorizontally(Object... references) {
        AlignHorizontallyReference reference = (AlignHorizontallyReference) helper(null, Helper.ALIGN_HORIZONTALLY);
        reference.add(references);
        return reference;
    }

    public AlignVerticallyReference centerVertically(Object... references) {
        AlignVerticallyReference reference = (AlignVerticallyReference) helper(null, Helper.ALIGN_VERTICALLY);
        reference.add(references);
        return reference;
    }

    public void directMapping() {
        for (Object key : this.mReferences.keySet()) {
            ConstraintReference reference = constraints(key);
            reference.setView(key);
        }
    }

    public void map(Object key, Object view) {
        ConstraintReference reference = constraints(key);
        reference.setView(view);
    }

    public void apply(ConstraintWidgetContainer container) {
        container.removeAllChildren();
        this.mParent.getWidth().apply(this, container, 0);
        this.mParent.getHeight().apply(this, container, 1);
        for (Object key : this.mHelperReferences.keySet()) {
            HelperWidget helperWidget = this.mHelperReferences.get(key).getHelperWidget();
            if (helperWidget != null) {
                Reference constraintReference = this.mReferences.get(key);
                if (constraintReference == null) {
                    constraintReference = constraints(key);
                }
                constraintReference.setConstraintWidget(helperWidget);
            }
        }
        for (Object key2 : this.mReferences.keySet()) {
            Reference reference = this.mReferences.get(key2);
            if (reference != this.mParent) {
                ConstraintWidget widget = reference.getConstraintWidget();
                widget.setParent(null);
                if (reference instanceof GuidelineReference) {
                    reference.apply();
                }
                container.add(widget);
            } else {
                reference.setConstraintWidget(container);
            }
        }
        for (Object key3 : this.mHelperReferences.keySet()) {
            HelperReference reference2 = this.mHelperReferences.get(key3);
            if (reference2.getHelperWidget() != null) {
                Iterator<Object> it = reference2.mReferences.iterator();
                while (it.hasNext()) {
                    Object keyRef = it.next();
                    reference2.getHelperWidget().add(this.mReferences.get(keyRef).getConstraintWidget());
                }
                reference2.apply();
            }
        }
        for (Object key4 : this.mReferences.keySet()) {
            this.mReferences.get(key4).apply();
        }
    }
}
