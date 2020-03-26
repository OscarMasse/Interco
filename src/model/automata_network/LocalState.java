package model.automata_network;

import java.util.ArrayList;
import java.util.List;

public class LocalState {
    private List<Transition> transitions;
    private int index;
    private String automataName;

    LocalState(int index, String name) {
        this.transitions = new ArrayList<>();
        this.index = index;
        this.automataName = name;
    }

    public void setTransitions(List<Transition> transitions) {
        this.transitions = transitions;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public String getAutomataName() {
        return automataName;
    }

    public int getIndex() {
        return this.index;
    }
}
