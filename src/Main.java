import model.automata_network.Automata;
import model.automata_network.AutomataNetwork;
import model.automata_network.LocalState;
import model.automata_network.Transition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Reader smb = new Reader("circadianCTL.smb");
        smb.read();
        smb.display();

        Translator translator = new Translator(smb.getRead());
        translator.translate();


        //Example to test writer
        AutomataNetwork network = new AutomataNetwork();
        Automata a = new Automata("a", 1);
        Automata b = new Automata("b", 2);
        Automata c = new Automata("c", 2);
        network.addAutomata(a);
        network.addAutomata(b);
        network.addAutomata(c);
        // Condition : b 0 -> 2 when a=1 and c=2
        // Condition : c 1 -> 0 when b=1 and a=0
        List<LocalState> conditionb02 = new ArrayList<>();
        conditionb02.add(a.getLocalState(1));
        conditionb02.add(c.getLocalState(2));
        List<LocalState> conditionc10 = new ArrayList<>();
        conditionc10.add(a.getLocalState(0));
        conditionc10.add(b.getLocalState(1));
        // Transition b
        List<Transition> transitionsb0 = new ArrayList<>();
        Transition b02 = new Transition(b.getLocalState(0), b.getLocalState(2), conditionb02);
        transitionsb0.add(b02);
        b.getLocalState(0).setTransitions(transitionsb0);
        // Transition c
        List<Transition> transitionsc1 = new ArrayList<>();
        Transition c10 = new Transition(c.getLocalState(1), b.getLocalState(0), conditionc10);
        transitionsc1.add(c10);
        c.getLocalState(1).setTransitions(transitionsc1);

        Writer an = new Writer("test.an", network);
        an.write();
    }
}
