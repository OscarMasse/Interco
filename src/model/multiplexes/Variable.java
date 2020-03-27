package model.multiplexes;

import java.util.ArrayList;
import java.util.List;

public class Variable {
    private String name;
    private int b_v;
    private List<State> states;
    private List<State> negativeStates;
    private List<List<Multiplex>> omega;
    private List<Multiplex> beta;

    public Variable(String name, int b_v) {
        this.name = name;
        this.b_v = b_v;
        this.omega = new ArrayList<>();
        this.beta = new ArrayList<>();
        this.states = new ArrayList<>();
        this.negativeStates = new ArrayList<>();
        for (int i = 0; i <= b_v; i++) {
            new State(this, i, false);
            new State(this, i, true);
        }
    }

    public List<State> getNegativeStates() {
        return negativeStates;
    }

    public List<List<Multiplex>> getOmega() {
        return omega;
    }

    public List<Multiplex> getBeta() {
        return beta;
    }

    public String getName() {
        return name;
    }

    public int getB_v() {
        return b_v;
    }

    public State getState(int index) {
        return this.states.get(index);
    }

    public State getNegativeState(int index) {
        return this.negativeStates.get(index);
    }

    public void addState(State state) {
        this.states.add(state);
    }

    public void addNegativeState(State state) {
        this.negativeStates.add(state);
    }
}
