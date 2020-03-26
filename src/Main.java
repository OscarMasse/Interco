import model.automata_network.Automata;
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
        List<Automata> model = new ArrayList<>();
        Automata a = new Automata("a", 1);
        Automata b = new Automata("b", 2);
        model.add(a);
        model.add(b);

        List<LocalState> conditionb02 = new ArrayList<>();
        conditionb02.add(a.getLocalState(1));

        List<Transition> transitionsb = new ArrayList<>();
        Transition b02 = new Transition(b.getLocalState(0), b.getLocalState(2), conditionb02);
        transitionsb.add(b02);
        b.getLocalState(0).setTransitions(transitionsb);

        Writer an = new Writer("test.an", model);
        an.write();
    }
}
