import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        Reader smb = new Reader("circadianCTL.smb");
        smb.read();
        smb.display();

        Map<String, List<List<String>>> readSMB = smb.getRead();
        RegulatoryGraph m = new RegulatoryGraph(readSMB.get("VAR"), readSMB.get("REG"));

        List<RegulatoryGraph.Variable> V = m.getVariables();

        // STEP 1
        List<Automata> automatas = new ArrayList<>();

        for (RegulatoryGraph.Variable v : V) {
            automatas.add(new Automata(v.name, v.b_v));
        }

        // STEP 2
        for (Automata automata : automatas) {

        }

        // STEP 3
        List<Automata.LocalState> s0 = new ArrayList<>();
        // Automatas initialized at 0 because we don't have η0
        for (Automata automata : automatas) {
            s0.add(automata.getLocalStates(0));
        }

        // STEP 4
        List<List<String>> omega = new ArrayList<>();

    }
}
