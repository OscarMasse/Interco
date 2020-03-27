package model.multiplexes;

import java.util.ArrayList;
import java.util.List;

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

    public List<State> represents() {
        List<State> states = new ArrayList<>();
        if (negative) {
            for (int i = this.index - 1; i >= 0; --i) {
                states.add(this.variable.getState(i));
            }
        } else {
            for (int i = this.index; i <= this.variable.getB_v(); ++i) {
                states.add(this.variable.getState(i));
            }
        }
        return states;
    }
}
