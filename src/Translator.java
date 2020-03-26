import data_structures.Pair;
import data_structures.Triplets;
import model.automata_network.Automata;
import model.automata_network.LocalState;
import model.multiplexes.RegulatoryGraph;
import model.multiplexes.Variable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Translator {

    private Map<String, List<List<String>>> read;
    private RegulatoryGraph RG;
    private List<Variable> V;
    private List<String> M;

    private List<Automata> automatas;
    private List<LocalState> s0;

    public Translator(Map<String, List<List<String>>> read) {
        this.read = read;
    }

    public void translate() {
        RG = new RegulatoryGraph(read.get("VAR"), read.get("REG"));
        V = RG.getVariables();
        M = RG.getMultiplexes();

        step1();
        step2();
        step3();
        step4();
        step5();
        step6();
        step7();
    }

    private void step1() {
        automatas = new ArrayList<>();
        for (Variable v : V) {
            automatas.add(new Automata(v.getName(), v.getB_v()));
        }
    }

    private void step2() {
        // Done in step1
    }

    private void step3() {
        s0 = new ArrayList<>();
        // Automatas initialized at 0 because we don't have η0
        for (Automata automata : automatas) {
            s0.add(automata.getLocalState(0));
        }
    }

    private void step4() {
        for (Variable v : V) {
            for (Pair couple : RG.getEm()) {
                if ((couple.getRight()) instanceof Variable) {
                    Variable var = (Variable) (couple.getRight());
                    if (var.getName().equals(v.getName())) {
                        v.getBeta().add((String) couple.getLeft());
                    }
                }
            }
            v.getOmega().add(new ArrayList<String>());
            int n = v.getBeta().size();
            int N = (int) Math.pow(2d, Double.valueOf(n));
            for (int i = 1; i < N; i++) {
                String code = Integer.toBinaryString(N | i).substring(1);
                ArrayList<String> temp = new ArrayList<>();
                for (int j = 0; j < n; j++) {
                    if (code.charAt(j) == '1') {
                        temp.add(v.getBeta().get(j));
                    }
                }
                v.getOmega().add(temp);
            }
        }
    }

    private void step5() {
        for (Variable v : V) {

            List<Pair<Variable, Integer>> w1 = new ArrayList<>();
            List<List<Pair<Variable, Integer>>> w2 = new ArrayList<>();
            List<String> omega1_i = new ArrayList<>();
            for (List<String> omega_i : v.getOmega()) {
                for (String m : omega_i) {
                    w1.add(takeElement(m));
                }
            }
            for (String m : RG.getMultiplexes()) {
                if (!v.getBeta().contains(m)) omega1_i.add(m);
            }
            for (String m : omega1_i) {
                w2.add(negate(m));
            }
        }
    }

    private void step6() {
    }

    private void step7() {
    }

    private Pair<Variable, Integer> takeElement(String m) {
        for (Triplets<Object, Integer, String> ev : RG.getEv()) {
            if (ev.getRight().equals(m)) {
                if (RG.getMultiplexes().contains(ev.getLeft())) {
                    return (takeElement(ev.getLeft().toString()));
                } else {
                    return (new Pair<>((Variable) ev.getLeft(), ev.getMiddle()));
                }
            }
        }
        return null;
    }

    private List<Pair<Variable, Integer>> negate(String m) {
        return null;
    }
}
