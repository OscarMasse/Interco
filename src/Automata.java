import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Automata {

    private int nStates;
    private List<LocalState> localStates;
    private List<String> var;

    public Automata(List<String> var) {
        this.var = var;
    }

    public Automata(int nStates) {
        this.nStates = nStates;
        this.localStates = new ArrayList<>();
        for (int i = 0; i < nStates; i++) {
            this.localStates.add(new LocalState());
        }
    }

    public int b_v() {
        return nStates + 1;
    }

    class LocalState {
        public Map<LocalState, List<LocalState>> transitions;
        public Map<LocalState, List<LocalState>> negTransitions;

        LocalState() {
            this.transitions = new HashMap<>();
            this.negTransitions = new HashMap<>();
        }
    }
}
