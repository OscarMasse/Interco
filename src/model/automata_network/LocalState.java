package model.automata_network;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalState {
    public Map<LocalState, List<LocalState>> transitions;
    public int i;

    LocalState(int i) {
        this.transitions = new HashMap<>();
        this.i = i;
    }
}
