import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Automata {

    private String name;
    private int nStates;
    private List<LocalState> localStates;
    private List<String> var;

    public Automata(List<String> var) {
        this.var = var;
    }

    public Automata(String name, int nStates) {
        this.name = name;
        this.nStates = nStates;
        this.localStates = new ArrayList<>();
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
