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
            localStates.add(new LocalState());
        }
    }

    public Automata(int nStates) {
        this.nStates = nStates;
        this.localStates = new ArrayList<>();
        for (int i = 0; i < nStates; i++) {
            this.localStates.add(new LocalState());
        }
    }

    class LocalState {
        public Map<LocalState, List<LocalState>> transitions;

        LocalState() {
            this.transitions = new HashMap<>();
        }
    }

    class Transition {
        LocalState origin, destination;
        List<LocalState> conditions;
    }
}
