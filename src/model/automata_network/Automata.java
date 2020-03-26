package model.automata_network;

import java.util.ArrayList;
import java.util.List;

public class Automata {

    private String name;
    private int nStates;
    private List<LocalState> localStates;

    public String getName() {
        return name;
    }

    public int getnStates() {
        return nStates;
    }

    public List<LocalState> getLocalStates() {
        return localStates;
    }

    public Automata(String name, int b_v) {
        this.name = name;
        this.nStates = b_v;
        this.localStates = new ArrayList<>();
        for (int i = 0; i < nStates; i++) {
            this.localStates.add(new LocalState(i));
        }
    }

    public Automata(int nStates) {
        this.nStates = nStates;
        this.localStates = new ArrayList<>();
        for (int i = 0; i < nStates; i++) {
            this.localStates.add(new LocalState(i));
        }
    }

    public LocalState getLocalState(int i) {
        return localStates.get(i);
    }

}
