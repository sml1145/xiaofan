package androidx.constraintlayout.solver;

import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.Arrays;
import java.util.HashMap;
/* loaded from: classes.dex */
public class LinearSystem {
    public static final boolean DEBUG = false;
    private static final boolean DEBUG_CONSTRAINTS = false;
    public static final boolean FULL_DEBUG = false;
    public static final boolean MEASURE = false;
    static final boolean SIMPLIFY_SYNONYMS = false;
    public static Metrics sMetrics;
    final Cache mCache;
    private Row mGoal;
    ArrayRow[] mRows;
    private Row mTempGoal;
    private static int POOL_SIZE = 1000;
    private static final boolean USE_SYNONYMS = true;
    public static boolean OPTIMIZED_ENGINE = USE_SYNONYMS;
    public static long ARRAY_ROW_CREATION = 0;
    public static long OPTIMIZED_ARRAY_ROW_CREATION = 0;
    int mVariablesID = 0;
    private HashMap<String, SolverVariable> mVariables = null;
    private int TABLE_SIZE = 32;
    private int mMaxColumns = this.TABLE_SIZE;
    public boolean graphOptimizer = false;
    public boolean newgraphOptimizer = false;
    private boolean[] mAlreadyTestedCandidates = new boolean[this.TABLE_SIZE];
    int mNumColumns = 1;
    int mNumRows = 0;
    private int mMaxRows = this.TABLE_SIZE;
    private SolverVariable[] mPoolVariables = new SolverVariable[POOL_SIZE];
    private int mPoolVariablesCount = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface Row {
        void addError(SolverVariable solverVariable);

        void clear();

        SolverVariable getKey();

        SolverVariable getPivotCandidate(LinearSystem linearSystem, boolean[] zArr);

        void initFromRow(Row row);

        boolean isEmpty();

        void updateFromFinalVariable(LinearSystem linearSystem, SolverVariable solverVariable, boolean z);

        void updateFromRow(ArrayRow arrayRow, boolean z);

        void updateFromSystem(LinearSystem linearSystem);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class ValuesRow extends ArrayRow {
        public ValuesRow(Cache cache) {
            this.variables = new SolverVariableValues(this, cache);
        }
    }

    public LinearSystem() {
        this.mRows = null;
        this.mRows = new ArrayRow[this.TABLE_SIZE];
        releaseRows();
        this.mCache = new Cache();
        this.mGoal = new PriorityGoalRow(this.mCache);
        if (OPTIMIZED_ENGINE) {
            this.mTempGoal = new ValuesRow(this.mCache);
        } else {
            this.mTempGoal = new ArrayRow(this.mCache);
        }
    }

    public void fillMetrics(Metrics metrics) {
        sMetrics = metrics;
    }

    public static Metrics getMetrics() {
        return sMetrics;
    }

    private void increaseTableSize() {
        this.TABLE_SIZE *= 2;
        this.mRows = (ArrayRow[]) Arrays.copyOf(this.mRows, this.TABLE_SIZE);
        this.mCache.mIndexedVariables = (SolverVariable[]) Arrays.copyOf(this.mCache.mIndexedVariables, this.TABLE_SIZE);
        this.mAlreadyTestedCandidates = new boolean[this.TABLE_SIZE];
        this.mMaxColumns = this.TABLE_SIZE;
        this.mMaxRows = this.TABLE_SIZE;
        if (sMetrics != null) {
            sMetrics.tableSizeIncrease++;
            sMetrics.maxTableSize = Math.max(sMetrics.maxTableSize, this.TABLE_SIZE);
            sMetrics.lastTableSize = sMetrics.maxTableSize;
        }
    }

    private void releaseRows() {
        if (OPTIMIZED_ENGINE) {
            for (int i = 0; i < this.mRows.length; i++) {
                ArrayRow row = this.mRows[i];
                if (row != null) {
                    this.mCache.optimizedArrayRowPool.release(row);
                }
                this.mRows[i] = null;
            }
            return;
        }
        for (int i2 = 0; i2 < this.mRows.length; i2++) {
            ArrayRow row2 = this.mRows[i2];
            if (row2 != null) {
                this.mCache.arrayRowPool.release(row2);
            }
            this.mRows[i2] = null;
        }
    }

    public void reset() {
        for (int i = 0; i < this.mCache.mIndexedVariables.length; i++) {
            SolverVariable variable = this.mCache.mIndexedVariables[i];
            if (variable != null) {
                variable.reset();
            }
        }
        this.mCache.solverVariablePool.releaseAll(this.mPoolVariables, this.mPoolVariablesCount);
        this.mPoolVariablesCount = 0;
        Arrays.fill(this.mCache.mIndexedVariables, (Object) null);
        if (this.mVariables != null) {
            this.mVariables.clear();
        }
        this.mVariablesID = 0;
        this.mGoal.clear();
        this.mNumColumns = 1;
        for (int i2 = 0; i2 < this.mNumRows; i2++) {
            this.mRows[i2].used = false;
        }
        releaseRows();
        this.mNumRows = 0;
        if (OPTIMIZED_ENGINE) {
            this.mTempGoal = new ValuesRow(this.mCache);
        } else {
            this.mTempGoal = new ArrayRow(this.mCache);
        }
    }

    public SolverVariable createObjectVariable(Object anchor) {
        if (anchor == null) {
            return null;
        }
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable variable = null;
        if (anchor instanceof ConstraintAnchor) {
            variable = ((ConstraintAnchor) anchor).getSolverVariable();
            if (variable == null) {
                ((ConstraintAnchor) anchor).resetSolverVariable(this.mCache);
                variable = ((ConstraintAnchor) anchor).getSolverVariable();
            }
            if (variable.id == -1 || variable.id > this.mVariablesID || this.mCache.mIndexedVariables[variable.id] == null) {
                if (variable.id != -1) {
                    variable.reset();
                }
                this.mVariablesID++;
                this.mNumColumns++;
                variable.id = this.mVariablesID;
                variable.mType = SolverVariable.Type.UNRESTRICTED;
                this.mCache.mIndexedVariables[this.mVariablesID] = variable;
            }
        }
        return variable;
    }

    public ArrayRow createRow() {
        ArrayRow row;
        if (OPTIMIZED_ENGINE) {
            row = this.mCache.optimizedArrayRowPool.acquire();
            if (row == null) {
                row = new ValuesRow(this.mCache);
                OPTIMIZED_ARRAY_ROW_CREATION++;
            } else {
                row.reset();
            }
        } else {
            row = this.mCache.arrayRowPool.acquire();
            if (row == null) {
                row = new ArrayRow(this.mCache);
                ARRAY_ROW_CREATION++;
            } else {
                row.reset();
            }
        }
        SolverVariable.increaseErrorId();
        return row;
    }

    public SolverVariable createSlackVariable() {
        if (sMetrics != null) {
            sMetrics.slackvariables++;
        }
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable variable = acquireSolverVariable(SolverVariable.Type.SLACK, null);
        this.mVariablesID++;
        this.mNumColumns++;
        variable.id = this.mVariablesID;
        this.mCache.mIndexedVariables[this.mVariablesID] = variable;
        return variable;
    }

    public SolverVariable createExtraVariable() {
        if (sMetrics != null) {
            sMetrics.extravariables++;
        }
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable variable = acquireSolverVariable(SolverVariable.Type.SLACK, null);
        this.mVariablesID++;
        this.mNumColumns++;
        variable.id = this.mVariablesID;
        this.mCache.mIndexedVariables[this.mVariablesID] = variable;
        return variable;
    }

    private void addError(ArrayRow row) {
        row.addError(this, 0);
    }

    private void addSingleError(ArrayRow row, int sign) {
        addSingleError(row, sign, 0);
    }

    void addSingleError(ArrayRow row, int sign, int strength) {
        SolverVariable error = createErrorVariable(strength, null);
        row.addSingleError(error, sign);
    }

    private SolverVariable createVariable(String name, SolverVariable.Type type) {
        if (sMetrics != null) {
            sMetrics.variables++;
        }
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable variable = acquireSolverVariable(type, null);
        variable.setName(name);
        this.mVariablesID++;
        this.mNumColumns++;
        variable.id = this.mVariablesID;
        if (this.mVariables == null) {
            this.mVariables = new HashMap<>();
        }
        this.mVariables.put(name, variable);
        this.mCache.mIndexedVariables[this.mVariablesID] = variable;
        return variable;
    }

    public SolverVariable createErrorVariable(int strength, String prefix) {
        if (sMetrics != null) {
            sMetrics.errors++;
        }
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable variable = acquireSolverVariable(SolverVariable.Type.ERROR, prefix);
        this.mVariablesID++;
        this.mNumColumns++;
        variable.id = this.mVariablesID;
        variable.strength = strength;
        this.mCache.mIndexedVariables[this.mVariablesID] = variable;
        this.mGoal.addError(variable);
        return variable;
    }

    private SolverVariable acquireSolverVariable(SolverVariable.Type type, String prefix) {
        SolverVariable variable = this.mCache.solverVariablePool.acquire();
        if (variable == null) {
            variable = new SolverVariable(type, prefix);
            variable.setType(type, prefix);
        } else {
            variable.reset();
            variable.setType(type, prefix);
        }
        if (this.mPoolVariablesCount >= POOL_SIZE) {
            POOL_SIZE *= 2;
            this.mPoolVariables = (SolverVariable[]) Arrays.copyOf(this.mPoolVariables, POOL_SIZE);
        }
        SolverVariable[] solverVariableArr = this.mPoolVariables;
        int i = this.mPoolVariablesCount;
        this.mPoolVariablesCount = i + 1;
        solverVariableArr[i] = variable;
        return variable;
    }

    Row getGoal() {
        return this.mGoal;
    }

    ArrayRow getRow(int n) {
        return this.mRows[n];
    }

    float getValueFor(String name) {
        SolverVariable v = getVariable(name, SolverVariable.Type.UNRESTRICTED);
        if (v == null) {
            return 0.0f;
        }
        return v.computedValue;
    }

    public int getObjectVariableValue(Object anchor) {
        SolverVariable variable = ((ConstraintAnchor) anchor).getSolverVariable();
        if (variable != null) {
            return (int) (variable.computedValue + 0.5f);
        }
        return 0;
    }

    SolverVariable getVariable(String name, SolverVariable.Type type) {
        if (this.mVariables == null) {
            this.mVariables = new HashMap<>();
        }
        SolverVariable variable = this.mVariables.get(name);
        if (variable == null) {
            return createVariable(name, type);
        }
        return variable;
    }

    public void minimize() throws Exception {
        if (sMetrics != null) {
            sMetrics.minimize++;
        }
        if (this.graphOptimizer || this.newgraphOptimizer) {
            if (sMetrics != null) {
                sMetrics.graphOptimizer++;
            }
            boolean fullySolved = USE_SYNONYMS;
            int i = 0;
            while (true) {
                if (i >= this.mNumRows) {
                    break;
                }
                ArrayRow r = this.mRows[i];
                if (r.isSimpleDefinition) {
                    i++;
                } else {
                    fullySolved = false;
                    break;
                }
            }
            if (!fullySolved) {
                minimizeGoal(this.mGoal);
                return;
            }
            if (sMetrics != null) {
                sMetrics.fullySolved++;
            }
            computeValues();
            return;
        }
        minimizeGoal(this.mGoal);
    }

    void minimizeGoal(Row goal) throws Exception {
        if (sMetrics != null) {
            sMetrics.minimizeGoal++;
            sMetrics.maxVariables = Math.max(sMetrics.maxVariables, this.mNumColumns);
            sMetrics.maxRows = Math.max(sMetrics.maxRows, this.mNumRows);
        }
        enforceBFS(goal);
        optimize(goal, false);
        computeValues();
    }

    final void cleanupRows() {
        int i = 0;
        while (i < this.mNumRows) {
            ArrayRow current = this.mRows[i];
            if (current.variables.getCurrentSize() == 0) {
                current.isSimpleDefinition = USE_SYNONYMS;
            }
            if (current.isSimpleDefinition) {
                current.variable.computedValue = current.constantValue;
                current.variable.removeFromRow(current);
                for (int j = i; j < this.mNumRows - 1; j++) {
                    this.mRows[j] = this.mRows[j + 1];
                }
                this.mRows[this.mNumRows - 1] = null;
                this.mNumRows--;
                i--;
            }
            i++;
        }
    }

    public void addConstraint(ArrayRow row) {
        SolverVariable pivotCandidate;
        if (row == null) {
            return;
        }
        if (sMetrics != null) {
            sMetrics.constraints++;
            if (row.isSimpleDefinition) {
                sMetrics.simpleconstraints++;
            }
        }
        if (this.mNumRows + 1 >= this.mMaxRows || this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        boolean added = false;
        if (!row.isSimpleDefinition) {
            row.updateFromSystem(this);
            if (row.isEmpty()) {
                return;
            }
            row.ensurePositiveConstant();
            if (row.chooseSubject(this)) {
                SolverVariable extra = createExtraVariable();
                row.variable = extra;
                addRow(row);
                added = USE_SYNONYMS;
                this.mTempGoal.initFromRow(row);
                optimize(this.mTempGoal, USE_SYNONYMS);
                if (extra.definitionId == -1) {
                    if (row.variable == extra && (pivotCandidate = row.pickPivot(extra)) != null) {
                        if (sMetrics != null) {
                            sMetrics.pivots++;
                        }
                        row.pivot(pivotCandidate);
                    }
                    if (!row.isSimpleDefinition) {
                        row.variable.updateReferencesWithNewDefinition(row);
                    }
                    this.mNumRows--;
                }
            }
            if (!row.hasKeyVariable()) {
                return;
            }
        }
        if (!added) {
            addRow(row);
        }
    }

    private final void addRow(ArrayRow row) {
        if (OPTIMIZED_ENGINE) {
            if (this.mRows[this.mNumRows] != null) {
                this.mCache.optimizedArrayRowPool.release(this.mRows[this.mNumRows]);
            }
        } else if (this.mRows[this.mNumRows] != null) {
            this.mCache.arrayRowPool.release(this.mRows[this.mNumRows]);
        }
        this.mRows[this.mNumRows] = row;
        row.variable.definitionId = this.mNumRows;
        this.mNumRows++;
        row.variable.updateReferencesWithNewDefinition(row);
    }

    public void removeRow(ArrayRow row) {
        if (row.isSimpleDefinition && row.variable != null) {
            if (row.variable.definitionId != -1) {
                for (int i = row.variable.definitionId; i < this.mNumRows - 1; i++) {
                    this.mRows[i] = this.mRows[i + 1];
                }
                int i2 = this.mNumRows;
                this.mNumRows = i2 - 1;
            }
            row.variable.setFinalValue(this, row.constantValue);
        }
    }

    private final int optimize(Row goal, boolean b) {
        if (sMetrics != null) {
            sMetrics.optimize++;
        }
        boolean done = false;
        int tries = 0;
        for (int i = 0; i < this.mNumColumns; i++) {
            this.mAlreadyTestedCandidates[i] = false;
        }
        while (!done) {
            if (sMetrics != null) {
                sMetrics.iterations++;
            }
            tries++;
            if (tries >= this.mNumColumns * 2) {
                return tries;
            }
            if (goal.getKey() != null) {
                this.mAlreadyTestedCandidates[goal.getKey().id] = USE_SYNONYMS;
            }
            SolverVariable pivotCandidate = goal.getPivotCandidate(this, this.mAlreadyTestedCandidates);
            if (pivotCandidate != null) {
                if (this.mAlreadyTestedCandidates[pivotCandidate.id]) {
                    return tries;
                }
                this.mAlreadyTestedCandidates[pivotCandidate.id] = USE_SYNONYMS;
            }
            if (pivotCandidate != null) {
                float min = Float.MAX_VALUE;
                int pivotRowIndex = -1;
                for (int i2 = 0; i2 < this.mNumRows; i2++) {
                    ArrayRow current = this.mRows[i2];
                    SolverVariable variable = current.variable;
                    if (variable.mType != SolverVariable.Type.UNRESTRICTED && !current.isSimpleDefinition && current.hasVariable(pivotCandidate)) {
                        float a_j = current.variables.get(pivotCandidate);
                        if (a_j < 0.0f) {
                            float value = (-current.constantValue) / a_j;
                            if (value < min) {
                                min = value;
                                pivotRowIndex = i2;
                            }
                        }
                    }
                }
                if (pivotRowIndex > -1) {
                    ArrayRow pivotEquation = this.mRows[pivotRowIndex];
                    pivotEquation.variable.definitionId = -1;
                    if (sMetrics != null) {
                        sMetrics.pivots++;
                    }
                    pivotEquation.pivot(pivotCandidate);
                    pivotEquation.variable.definitionId = pivotRowIndex;
                    pivotEquation.variable.updateReferencesWithNewDefinition(pivotEquation);
                }
            } else {
                done = USE_SYNONYMS;
            }
        }
        return tries;
    }

    private int enforceBFS(Row goal) throws Exception {
        float f;
        int tries = 0;
        boolean infeasibleSystem = false;
        int i = 0;
        while (true) {
            f = 0.0f;
            if (i >= this.mNumRows) {
                break;
            }
            SolverVariable variable = this.mRows[i].variable;
            if (variable.mType == SolverVariable.Type.UNRESTRICTED || this.mRows[i].constantValue >= 0.0f) {
                i++;
            } else {
                infeasibleSystem = USE_SYNONYMS;
                break;
            }
        }
        if (infeasibleSystem) {
            boolean done = false;
            tries = 0;
            while (!done) {
                if (sMetrics != null) {
                    sMetrics.bfs++;
                }
                tries++;
                float min = Float.MAX_VALUE;
                int strength = 0;
                int pivotRowIndex = -1;
                int pivotColumnIndex = -1;
                int i2 = 0;
                while (i2 < this.mNumRows) {
                    ArrayRow current = this.mRows[i2];
                    SolverVariable variable2 = current.variable;
                    if (variable2.mType != SolverVariable.Type.UNRESTRICTED && !current.isSimpleDefinition && current.constantValue < f) {
                        int j = 1;
                        while (j < this.mNumColumns) {
                            SolverVariable candidate = this.mCache.mIndexedVariables[j];
                            float a_j = current.variables.get(candidate);
                            if (a_j > f) {
                                for (int k = 0; k < 9; k++) {
                                    float value = candidate.strengthVector[k] / a_j;
                                    if ((value < min && k == strength) || k > strength) {
                                        min = value;
                                        pivotRowIndex = i2;
                                        pivotColumnIndex = j;
                                        strength = k;
                                    }
                                }
                            }
                            j++;
                            f = 0.0f;
                        }
                    }
                    i2++;
                    f = 0.0f;
                }
                if (pivotRowIndex != -1) {
                    ArrayRow pivotEquation = this.mRows[pivotRowIndex];
                    pivotEquation.variable.definitionId = -1;
                    if (sMetrics != null) {
                        sMetrics.pivots++;
                    }
                    pivotEquation.pivot(this.mCache.mIndexedVariables[pivotColumnIndex]);
                    pivotEquation.variable.definitionId = pivotRowIndex;
                    pivotEquation.variable.updateReferencesWithNewDefinition(pivotEquation);
                } else {
                    done = USE_SYNONYMS;
                }
                if (tries > this.mNumColumns / 2) {
                    done = USE_SYNONYMS;
                }
                f = 0.0f;
            }
        }
        return tries;
    }

    private void computeValues() {
        for (int i = 0; i < this.mNumRows; i++) {
            ArrayRow row = this.mRows[i];
            row.variable.computedValue = row.constantValue;
        }
    }

    private void displayRows() {
        displaySolverVariables();
        String s = "";
        for (int i = 0; i < this.mNumRows; i++) {
            s = (s + this.mRows[i]) + "\n";
        }
        System.out.println(s + this.mGoal + "\n");
    }

    public void displayReadableRows() {
        displaySolverVariables();
        String s = "";
        for (int i = 0; i < this.mVariablesID; i++) {
            SolverVariable variable = this.mCache.mIndexedVariables[i];
            if (variable != null && variable.isFinalValue) {
                s = s + " $[" + i + "] => " + variable + " = " + variable.computedValue + "\n";
            }
        }
        String s2 = s + "\n\n #  ";
        for (int i2 = 0; i2 < this.mNumRows; i2++) {
            s2 = (s2 + this.mRows[i2].toReadableString()) + "\n #  ";
        }
        if (this.mGoal != null) {
            s2 = s2 + "Goal: " + this.mGoal + "\n";
        }
        System.out.println(s2);
    }

    public void displayVariablesReadableRows() {
        displaySolverVariables();
        String s = "";
        for (int i = 0; i < this.mNumRows; i++) {
            if (this.mRows[i].variable.mType == SolverVariable.Type.UNRESTRICTED) {
                s = (s + this.mRows[i].toReadableString()) + "\n";
            }
        }
        System.out.println(s + this.mGoal + "\n");
    }

    public int getMemoryUsed() {
        int actualRowSize = 0;
        for (int i = 0; i < this.mNumRows; i++) {
            if (this.mRows[i] != null) {
                actualRowSize += this.mRows[i].sizeInBytes();
            }
        }
        return actualRowSize;
    }

    public int getNumEquations() {
        return this.mNumRows;
    }

    public int getNumVariables() {
        return this.mVariablesID;
    }

    void displaySystemInformations() {
        int rowSize = 0;
        for (int i = 0; i < this.TABLE_SIZE; i++) {
            if (this.mRows[i] != null) {
                rowSize += this.mRows[i].sizeInBytes();
            }
        }
        int actualRowSize = 0;
        for (int i2 = 0; i2 < this.mNumRows; i2++) {
            if (this.mRows[i2] != null) {
                actualRowSize += this.mRows[i2].sizeInBytes();
            }
        }
        System.out.println("Linear System -> Table size: " + this.TABLE_SIZE + " (" + getDisplaySize(this.TABLE_SIZE * this.TABLE_SIZE) + ") -- row sizes: " + getDisplaySize(rowSize) + ", actual size: " + getDisplaySize(actualRowSize) + " rows: " + this.mNumRows + "/" + this.mMaxRows + " cols: " + this.mNumColumns + "/" + this.mMaxColumns + " 0 occupied cells, " + getDisplaySize(0));
    }

    private void displaySolverVariables() {
        String s = "Display Rows (" + this.mNumRows + "x" + this.mNumColumns + ")\n";
        System.out.println(s);
    }

    private String getDisplaySize(int n) {
        int mb = ((n * 4) / 1024) / 1024;
        if (mb > 0) {
            return "" + mb + " Mb";
        }
        int kb = (n * 4) / 1024;
        return kb > 0 ? "" + kb + " Kb" : "" + (n * 4) + " bytes";
    }

    public Cache getCache() {
        return this.mCache;
    }

    private String getDisplayStrength(int strength) {
        if (strength == 1) {
            return "LOW";
        }
        if (strength == 2) {
            return "MEDIUM";
        }
        if (strength == 3) {
            return "HIGH";
        }
        if (strength == 4) {
            return "HIGHEST";
        }
        if (strength == 5) {
            return "EQUALITY";
        }
        if (strength == 8) {
            return "FIXED";
        }
        if (strength == 6) {
            return "BARRIER";
        }
        return "NONE";
    }

    public void addGreaterThan(SolverVariable a, SolverVariable b, int margin, int strength) {
        ArrayRow row = createRow();
        SolverVariable slack = createSlackVariable();
        slack.strength = 0;
        row.createRowGreaterThan(a, b, slack, margin);
        if (strength != 8) {
            float slackValue = row.variables.get(slack);
            addSingleError(row, (int) ((-1.0f) * slackValue), strength);
        }
        addConstraint(row);
    }

    public void addGreaterBarrier(SolverVariable a, SolverVariable b, int margin, boolean hasMatchConstraintWidgets) {
        ArrayRow row = createRow();
        SolverVariable slack = createSlackVariable();
        slack.strength = 0;
        row.createRowGreaterThan(a, b, slack, margin);
        addConstraint(row);
    }

    public void addLowerThan(SolverVariable a, SolverVariable b, int margin, int strength) {
        ArrayRow row = createRow();
        SolverVariable slack = createSlackVariable();
        slack.strength = 0;
        row.createRowLowerThan(a, b, slack, margin);
        if (strength != 8) {
            float slackValue = row.variables.get(slack);
            addSingleError(row, (int) ((-1.0f) * slackValue), strength);
        }
        addConstraint(row);
    }

    public void addLowerBarrier(SolverVariable a, SolverVariable b, int margin, boolean hasMatchConstraintWidgets) {
        ArrayRow row = createRow();
        SolverVariable slack = createSlackVariable();
        slack.strength = 0;
        row.createRowLowerThan(a, b, slack, margin);
        addConstraint(row);
    }

    public void addCentering(SolverVariable a, SolverVariable b, int m1, float bias, SolverVariable c, SolverVariable d, int m2, int strength) {
        ArrayRow row = createRow();
        row.createRowCentering(a, b, m1, bias, c, d, m2);
        if (strength != 8) {
            row.addError(this, strength);
        }
        addConstraint(row);
    }

    public void addRatio(SolverVariable a, SolverVariable b, SolverVariable c, SolverVariable d, float ratio, int strength) {
        ArrayRow row = createRow();
        row.createRowDimensionRatio(a, b, c, d, ratio);
        if (strength != 8) {
            row.addError(this, strength);
        }
        addConstraint(row);
    }

    public ArrayRow addEquality(SolverVariable a, SolverVariable b, int margin, int strength) {
        if (strength == 8 && b.isFinalValue && a.definitionId == -1) {
            a.setFinalValue(this, b.computedValue + margin);
            return null;
        }
        ArrayRow row = createRow();
        row.createRowEquals(a, b, margin);
        if (strength != 8) {
            row.addError(this, strength);
        }
        addConstraint(row);
        return row;
    }

    public void addEquality(SolverVariable a, int value) {
        if (a.definitionId == -1) {
            a.setFinalValue(this, value);
            return;
        }
        int idx = a.definitionId;
        if (a.definitionId != -1) {
            ArrayRow row = this.mRows[idx];
            if (row.isSimpleDefinition) {
                row.constantValue = value;
                return;
            } else if (row.variables.getCurrentSize() == 0) {
                row.isSimpleDefinition = USE_SYNONYMS;
                row.constantValue = value;
                return;
            } else {
                ArrayRow newRow = createRow();
                newRow.createRowEquals(a, value);
                addConstraint(newRow);
                return;
            }
        }
        ArrayRow row2 = createRow();
        row2.createRowDefinition(a, value);
        addConstraint(row2);
    }

    public static ArrayRow createRowDimensionPercent(LinearSystem linearSystem, SolverVariable variableA, SolverVariable variableC, float percent) {
        ArrayRow row = linearSystem.createRow();
        return row.createRowDimensionPercent(variableA, variableC, percent);
    }

    public void addCenterPoint(ConstraintWidget widget, ConstraintWidget target, float angle, int radius) {
        SolverVariable Al = createObjectVariable(widget.getAnchor(ConstraintAnchor.Type.LEFT));
        SolverVariable At = createObjectVariable(widget.getAnchor(ConstraintAnchor.Type.TOP));
        SolverVariable Ar = createObjectVariable(widget.getAnchor(ConstraintAnchor.Type.RIGHT));
        SolverVariable Ab = createObjectVariable(widget.getAnchor(ConstraintAnchor.Type.BOTTOM));
        SolverVariable Bl = createObjectVariable(target.getAnchor(ConstraintAnchor.Type.LEFT));
        SolverVariable Bt = createObjectVariable(target.getAnchor(ConstraintAnchor.Type.TOP));
        SolverVariable Br = createObjectVariable(target.getAnchor(ConstraintAnchor.Type.RIGHT));
        SolverVariable Bb = createObjectVariable(target.getAnchor(ConstraintAnchor.Type.BOTTOM));
        ArrayRow row = createRow();
        float angleComponent = (float) (Math.sin(angle) * radius);
        row.createRowWithAngle(At, Ab, Bt, Bb, angleComponent);
        addConstraint(row);
        ArrayRow row2 = createRow();
        float angleComponent2 = (float) (Math.cos(angle) * radius);
        row2.createRowWithAngle(Al, Ar, Bl, Br, angleComponent2);
        addConstraint(row2);
    }
}
