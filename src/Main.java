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
        RegulatoryGraph RG = new RegulatoryGraph(readSMB.get("VAR"), readSMB.get("REG"));

        List<RegulatoryGraph.Variable> V = RG.getVariables();
        List<String> M = RG.getMultiplexes();

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

        // STEP 5
        List<Pair<Object, Integer>> w1 = new ArrayList<>();
        List<List<Pair<Object, Integer>>> w2;
        List<String> omega1_i = new ArrayList<>();
        for (List<String> omega_i : omega) {
            for (String m : omega_i) {
                w1.add(takeElement(m, M));
            }

        }

    }

    private static Pair<Object, Integer> takeElement(String m, List<String> M) {
        return null;
    }

}
