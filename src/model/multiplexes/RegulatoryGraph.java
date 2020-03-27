package model.multiplexes;

import data_structures.Pair;

import java.util.ArrayList;
import java.util.List;

public class RegulatoryGraph {

    private List<Variable> variables; // V
    private List<Multiplex> multiplexes; // M

    private List<Pair<Multiplex, Variable>> em; // Multiplex, Output(Variable/Multiplex)
    private List<Pair<State, Multiplex>> ev; // Input(Variable/Multiplex, State), Multiplex

    public RegulatoryGraph(List<List<String>> var, List<List<String>> reg) {
        this.variables = new ArrayList<>();
        for (List list : var) {
            variables.add(new Variable(list.get(0).toString(), Integer.parseInt(list.get(list.size() - 1).toString())));
        }

        this.multiplexes = new ArrayList<>();
        for (List list : reg) {
            Multiplex m = new Multiplex(list.get(0).toString(), list.get(1).toString());
            for (Variable v : variables) {
                if (v.name.matches(list.get(2).toString())) m.setOutput(v);
            }
            multiplexes.add(m);
        }

        this.ev = new ArrayList<>();
        String[] formula;
        String condition;
        int index;
        int number;
        boolean varCondition = true;
        for (Multiplex multiplex : multiplexes) {
            formula = multiplex.getFormula().split("&");
            for (int j = 0; j < formula.length; j++) {
                condition = formula[j];
                for (Variable v : variables) {
                    if (condition.contains(v.name)) {
                        index = condition.indexOf("=");
                        number = Integer.parseInt(String.valueOf(condition.charAt(index + 1)));
                        // We can not know if state is negated, yet
                        this.ev.add(new Pair<>(v.getState(number), multiplex));
                        multiplex.addInput(v.getState(number));
                        varCondition = false;
                    }
                }
                // When we will consider that multiplex output/inputs can be multiplexes:
/*
                if (!varCondition) {
                    for (Multiplex m : this.multiplexes) {
                        if (condition.contains(m.getName())) {
                            this.ev.add(new Triplets<>(m, 1, multiplex));
                        }
                    }
                    varCondition = true;
                }
*/
            }
        }

        this.em = new ArrayList<>();
        for (Multiplex multiplex : multiplexes) {
            em.add(new Pair<>(multiplex, multiplex.getOuput()));
        }
    }


    public List<Pair<State, Multiplex>> getEv() {
        return ev;
    }

    public List<Pair<Multiplex, Variable>> getEm() {
        return em;
    }

    public List<Variable> getVariables() {
        return variables;
    }

    public List<Multiplex> getMultiplexes() {
        return multiplexes;
    }

}