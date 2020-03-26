import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Automata {

    private String name;
    private int nStates;
    private List<LocalState> localStates;

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

    public LocalState getLocalStates(int i) {
        return localStates.get(i);
    }

    class LocalState {
        public Map<LocalState, List<LocalState>> transitions;
        public int i;

        LocalState(int i) {
            this.transitions = new HashMap<>();
            this.i = i;
        }
    }

    class Transition {
        LocalState origin, destination;
        List<LocalState> conditions;
    }
}
