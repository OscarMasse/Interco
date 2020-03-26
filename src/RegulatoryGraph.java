import java.util.ArrayList;
import java.util.List;

public class RegulatoryGraph {

    private List<Variable> variables; // V
    private List<String> multiplexes; // m
    private List<String> formulas;
    private List<List<Variable>> inputs;
    private List<Variable> outputs;

    private List<Pair<String, Object>> em; // Multiplex, Output(Variable/Multiplex)
    private List<Trouple<Object, Integer, String>> ev; // Input(Variable/Multiplex, State), Multiplex

    public List<Trouple<Object, Integer, String>> getEv() {
        return ev;
    }

    public List<Pair<String, Object>> getEm() {
        return em;
    }

    public RegulatoryGraph(List<List<String>> var, List<List<String>> reg) {
        this.variables = new ArrayList<>();
        for (List list : var) {
            variables.add(new Variable(list.get(0).toString(), Integer.valueOf(list.get(list.size() - 1).toString())));
        }

        this.multiplexes = new ArrayList<>();
        this.formulas = new ArrayList<>();
        this.outputs = new ArrayList<>();
        for (List list : reg) {
            multiplexes.add(list.get(0).toString());
            formulas.add(list.get(1).toString());
            for (Variable v : variables) {
                if (v.name.matches(list.get(2).toString())) outputs.add(v);
            }
        }

        this.ev = new ArrayList<>();
        String[] formula;
        String condition;
        int index;
        int number;
        boolean varCondition = true;
        for (int i = 0; i < this.multiplexes.size(); i++) {
            formula = formulas.get(i).split("&");
            for (int j = 0; j < formula.length; j++) {
                condition = formula[j];
                for (Variable v : variables) {
                    if (condition.contains(v.name)) {
                        index = condition.indexOf("=");
                        number = Integer.parseInt(String.valueOf(condition.charAt(index + 1)));
                        this.ev.add(new Trouple<Object, Integer, String>(v, number, this.multiplexes.get(i)));
                        varCondition = false;
                    }
                }
                if (!varCondition) {
                    for (String m : this.multiplexes) {
                        if (condition.contains(m)) {
                            this.ev.add(new Trouple<Object, Integer, String>(m, 1, this.multiplexes.get(i)));
                        }
                    }
                    varCondition = true;
                }
            }
        }

        this.inputs = new ArrayList<>();
        List<Variable> input;
        int count = 0;
        for (String s : formulas) {
            input = new ArrayList<>();
            for (Variable v : variables) {
                if (s.contains(v.name))
                    // count the occurrence of v, because the same var can input m multiple times
                    count = (s.length() - s.replaceAll(v.name, "").length()) / v.name.length();
                for (int i = 0; i < count; i++) {
                    input.add(v);
                }
            }
            if (!input.isEmpty()) inputs.add(input);
            else System.out.println("NO INPUT FOUND IN THIS FORMULA" + s);
        }
        this.em = new ArrayList<>();
        for (int i = 0; i < multiplexes.size(); i++) {
            em.add(new Pair<String, Object>(formulas.get(i), outputs.get(i)));
        }
        this.ev = new ArrayList<>();
    }

    public List<Variable> getVariables() {
        return variables;
    }

    public List<String> getMultiplexes() {
        return multiplexes;
    }

    class Variable {
        String name;
        int b_v;
        List<List<String>> omega;
        List<String> beta;

        public List<List<String>> getOmega() {
            return omega;
        }

        public List<String> getBeta() {
            return beta;
        }

        public String getName() {
            return name;
        }

        public int getB_v() {
            return b_v;
        }

        public List<Integer> getStates(int i) {
            List<Integer> states = new ArrayList<>();
            while (i <= this.b_v) {
                states.add(i);
                i++;
            }
            return states;
        }

        public List<Integer> getNegStates(int i) {
            List<Integer> negStates = new ArrayList<>();
            while (i > 0) {
                negStates.add(i);
                i--;
            }
            return negStates;
        }

        Variable(String name, int b_v) {
            this.name = name;
            this.b_v = b_v;
            this.omega = new ArrayList<>();
            this.beta = new ArrayList<>();
        }
    }
}