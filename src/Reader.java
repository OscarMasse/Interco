import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reader {

    private String path;
    private File file;

    public Map<String, List<List<String>>> getRead() {
        return readSMB;
    }

    private enum KeyWords {
        VAR("VAR"),
        REG("REG"),
        PARA("PARA"),
        CTL("CTL"),
        FAIRCTL("FAIRCTL"),
        HOARE("HOARE"),
        PRE("PRE"),
        TRACE("TRACE"),
        POST("POST"),
        END("END");

        private String keyWord;

        KeyWords(final String keyWord) {
            this.keyWord = keyWord;
        }

        @Override
        public String toString() {
            return keyWord;
        }
    }

    private Map<String, List<List<String>>> readSMB;

    Reader(String path) {
        this.path = path;
        this.file = new File(path);
        this.readSMB = new HashMap<>();
    }

    public void read() throws IOException {
        BufferedReader smb = new BufferedReader(new FileReader(this.path));
        String line = smb.readLine();
        while (line != null){
            line = discardComments(line);
            if (line.contains("VAR")) {
                line = smb.readLine();
                line = readVAR(smb, line);
            } else if (line.contains("REG")) {
                line = smb.readLine();
                line = readREG(smb, line);
            } else if (line.contains("PARA")) {
                line = smb.readLine();
                line = readPARA(smb, line);
            } else {
                line = smb.readLine();
            }
        }
    }

    private String discardComments(String line) {
        if (line.contains("#")) {
            return line.split("#")[0];
        }
        return line;
    }

    private boolean containsKeyWord(String line) {
        boolean contains = false;
        for (KeyWords keyWord : KeyWords.values()) {
            if (line.contains(keyWord.toString())) {
                contains = true;
            }
        }
        return contains;
    }

    // VAR format : var = 0..2 ;
    private String readVAR(BufferedReader smb, String line) {
        String[] split;
        List<List<String>> var = new ArrayList<>();
        List<String> vari = new ArrayList<>();
        while (!containsKeyWord(line)) {
            line = discardComments(line);
            vari = new ArrayList<>();
            if (!line.isEmpty()) {
                line = line.replaceAll("=", "");
                line = line.replaceAll(";", "");
                line = line.replaceAll("\\.\\.", " ");
                split = line.split("\\s+");
//                for (String a : split) {
//                    System.out.println(a);
//                }
                for (int i = 0; i < split.length; i++) {
                    vari.add(split[i]);
                }
                var.add(vari);
            }
            try {
                line = smb.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.readSMB.put("VAR", var);
        return line;
    }

    // REG format : reg [cond] => var ;
    private String readREG(BufferedReader smb, String line) {
        String[] split;
        List<List<String>> reg = new ArrayList<>();
        List<String> regi = new ArrayList<>();
        while (!containsKeyWord(line)) {
            line = discardComments(line);
            regi = new ArrayList<>();
            if (!line.isEmpty()) {
                line = line.replaceAll("=>", "");
                line = line.replaceAll(";", "");
                line = line.replaceAll("\\[", "");
                line = line.replaceAll("]", "");
                split = line.split("\\s+");
//                for (String a : split) {
//                    System.out.println(a);
//                }
                for (int i = 0; i < split.length; i++) {
                    regi.add(split[i]);
                }
                reg.add(regi);
            }
            try {
                line = smb.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.readSMB.put("REG", reg);
        return line;
    }

    // PARA format : k = 0..1 ;
    private String readPARA(BufferedReader smb, String line) {
        String[] split;
        List<List<String>> para = new ArrayList<>();
        List<String> parai = new ArrayList<>();
        while (!containsKeyWord(line)) {
            line = discardComments(line);
            parai = new ArrayList<>();
            if (!line.isEmpty()) {
                line = line.replaceAll("=", "");
                line = line.replaceAll(";", "");
                line = line.replaceAll("\\.\\.", " ");
                split = line.split("\\s+");
//                for (String a : split) {
//                    System.out.println(a);
//                }
                for (int i = 0; i < split.length; i++) {
                    parai.add(split[i]);
                }
                para.add(parai);
            }
            try {
                line = smb.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.readSMB.put("PARA", para);
        }

        return line;
    }

    public void display() {
        for (Map.Entry<String, List<List<String>>> entry : readSMB.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    void createAutomataStates(Automata automata, int b_v) {

    }

    List<Integer> initializeAutomataStates(List<Integer> statesVariables) {
        List<Integer> S0 = new ArrayList<>();
        for (Integer i :
                statesVariables) {
            S0.add(i);
        }
        return S0;
    }

    void createOmega(Automata automata, List<List<Integer>> Em) {

    }
}