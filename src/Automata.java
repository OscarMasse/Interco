import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Automata {

    private int nStates;
    private List<String> var;

    public Automata(List<String> var) {
        this.var = var;
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
