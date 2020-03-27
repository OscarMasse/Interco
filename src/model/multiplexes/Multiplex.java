package model.multiplexes;

import java.util.ArrayList;
import java.util.List;

public class Multiplex {
    private String name;
    private String formula;
    private List<State> inputs;
    private Variable output;
    private boolean negative;

    public Multiplex(String name, String formula, List<State> inputs, Variable output, boolean negative) {
        this.name = name;
        this.formula = formula;
        this.inputs = inputs;
        this.output = output;
        this.negative = negative;
    }

    public Multiplex(String name, String formula) {
        this.name = name;
        this.formula = formula;
        this.inputs = new ArrayList<>();
        this.negative = false;
    }

    public void setOutput(Variable output) {
        this.output = output;
    }

    public String getFormula() {
        return this.formula;
    }

    public String getName() {
        return this.name;
    }

    public Variable getOuput() {
        return this.output;
    }

    public List<State> getInputs() {
        return this.inputs;
    }

    public void addInput(State input) {
        this.inputs.add(input);
    }
}
