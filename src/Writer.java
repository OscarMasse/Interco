import model.automata_network.Automata;
import model.automata_network.LocalState;
import model.automata_network.Transition;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class Writer {

    private List<Automata> model;
    private String path;
    private File file;

    Writer(String path) {
        this.path = path;
        this.file = new File(path);
    }

    public void write() throws IOException {
        BufferedWriter an = new BufferedWriter(new FileWriter(this.path, true));
        writeVAR(an);
        an.write("\n");
        an.write("\n");
        writeTRANSI(an);
        an.close();
    }

    public void writeVAR(BufferedWriter an) throws IOException {
        for (Automata a : this.model) {
            an.write("\"" + a.getName() + "\" [0," + a.getnStates() + "]\n");
        }
    }

    public void writeTRANSI(BufferedWriter an) throws IOException {
        for (Automata a : this.model) {
            for (LocalState localState : a.getLocalStates()) {
                for (Transition transition : localState.getTransitions()) {
                    String transi = "\"" + a.getName() + "\"" + localState.getIndex() + " -> "
                            + transition.getDestination().getIndex() + " when ";
                    for (LocalState condition : transition.getConditions()) {
                        transi = transi + condition.getAutomataName() + "=" + condition.getIndex() + " and ";
                    }
                    transi.substring(0, transi.length() - 5);
                    an.write(transi + "\n");
                }
            }
        }
    }

}
