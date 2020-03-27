import data_structures.Pair;
import model.automata_network.Automata;
import model.automata_network.LocalState;
import model.multiplexes.Multiplex;
import model.multiplexes.RegulatoryGraph;
import model.multiplexes.State;
import model.multiplexes.Variable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Translator {

    private Map<String, List<List<String>>> read;
    private RegulatoryGraph RG;
    private List<Variable> V;
    private List<Multiplex> M;

    private List<Automata> automatas;
    private List<LocalState> s0;

    private Map<Integer, List<Multiplex>> f;

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

    public static List<List<State>> cartesianProduct(List<?>... lists) {
        if (lists.length < 2) {
            System.out.println("Can't have a product of fewer than two sets (got " +
                    lists.length + ")");
            return null;
        }
//            throw new IllegalArgumentException(
//                    "Can't have a product of fewer than two sets (got " +
//                            lists.length + ")");

        return _cartesianProduct(0, lists);
    }

    private static List<List<State>> _cartesianProduct(int index, List<?>... lists) {
        List<List<State>> ret = new ArrayList<>();
        if (index == lists.length) {
            ret.add(new ArrayList<>());
        } else {
            for (Object obj : lists[index]) {
                for (List<State> list : _cartesianProduct(index + 1, lists)) {
                    list.add((State) obj);
                    ret.add(list);
                }
            }
        }
        return ret;
    }

    private void step3() {
        s0 = new ArrayList<>();
        // Automatas initialized at 0 because we don't have η0
        for (Automata automata : automatas) {
            s0.add(automata.getLocalState(0));
        }
    }

    private void step6() {
    }

    private void step7() {
    }
    // When we will consider that multiplex output/inputs can be multiplexes:
/*
    private State takeElement(Multiplex m) {
        return null;
    }
*/

    private void step4() {
        for (Variable v : V) {
            for (Pair<Multiplex, Variable> couple : RG.getEm()) {
                Variable var = couple.getRight();
                if (var.getName().equals(v.getName())) {
                    v.getBeta().add(couple.getLeft());
                }
            }
            v.getOmega().add(new ArrayList<>());
            int n = v.getBeta().size();
            int N = (int) Math.pow(2d, Double.valueOf(n));
            for (int i = 1; i < N; i++) {
                String code = Integer.toBinaryString(N | i).substring(1);
                ArrayList<Multiplex> temp = new ArrayList<>();
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
            List<State> w1 = new ArrayList<>();
            List<List<State>> w2 = new ArrayList<>();
            List<Multiplex> omega1_i = new ArrayList<>();
            for (List<Multiplex> omega_i : v.getOmega()) {
                for (Multiplex m : omega_i) {
                    // w1.add(takeElement(m));
                    for (State state : m.getInputs()) {
                        w1.add(state);
                    }
                }
            }
            for (Multiplex m : RG.getMultiplexes()) {
                if (!v.getBeta().contains(m)) omega1_i.add(m);
            }
            for (Multiplex m : omega1_i) {
                w2.add(negate(m));
            }

            List<List<State>> ec = cartesianProduct(w2);

            this.f = new HashMap<>();
            if (ec != null) {
                for (List<State> g : ec) {
                    // TODO
                }
            }

        }
    }

    private List<State> negate(Multiplex m) {
        List<State> list = new ArrayList<>();
        for (State state : m.getInputs()) {
            for (Variable variable : this.V) {
                if (state.getVariable().equals(variable))
                    list.add(variable.getNegativeState(state.getIndex()));
            }
        }
        return list;
    }
}
