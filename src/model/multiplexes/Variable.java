package model.multiplexes;

import java.util.ArrayList;
import java.util.List;

public class Variable {
    String name;
    int b_v;
    List<State> states;
    List<State> negativeStates;
    List<List<Multiplex>> omega;
    List<Multiplex> beta;

    Variable(String name, int b_v) {
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

    public List<Integer> getStates(int i) {
        List<Integer> states = new ArrayList<>();
        while (i <= this.b_v) {
            states.add(i);
            i++;
        }
        return states;
    }

    public List<Integer> getNegStates(int i) {
        List<Integer> negStates = new ArrayList<>();
        while (i > 0) {
            negStates.add(i);
            i--;
        }
        return negStates;
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
