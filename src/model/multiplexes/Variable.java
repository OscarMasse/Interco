package model.multiplexes;

import java.util.ArrayList;
import java.util.List;

public class Variable {
    String name;
    int b_v;
    List<List<String>> omega;
    List<String> beta;

    public List<List<String>> getOmega() {
        return omega;
    }

    public List<String> getBeta() {
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

    Variable(String name, int b_v) {
        this.name = name;
        this.b_v = b_v;
        this.omega = new ArrayList<>();
        this.beta = new ArrayList<>();
    }
}
