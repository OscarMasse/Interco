package model.automata_network;

import java.util.ArrayList;
import java.util.List;

public class LocalState {
    private List<Transition> transitions;
    private int index;
    private String automataName;

    public List<Transition> getTransitions() {
        return transitions;
    }

    public String getAutomataName() {
        return automataName;
    }

    LocalState(int index) {
        this.transitions = new ArrayList<>();
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }
}
