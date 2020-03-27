package model.automata_network;

import java.util.ArrayList;
import java.util.List;

public class AutomataNetwork {
    private List<Automata> network;

    public AutomataNetwork() {
        this.network = new ArrayList<>();
    }

    public void addAutomata(Automata automata) {
        this.network.add(automata);
    }

    public List<Automata> getAutomatas() {
        return this.network;
    }
}
