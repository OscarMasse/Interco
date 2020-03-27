package model.multiplexes;

public class State {
    private Variable variable;
    private int index;
    private boolean negative;

    public State(Variable variable, int index, boolean negative) {
        this.variable = variable;
        this.index = index;
        this.negative = negative;
        if (negative) variable.addNegativeState(this);
        else variable.addState(this);
    }

    public int getIndex() {
        return index;
    }

    public Variable getVariable() {
        return this.variable;
    }
}
