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

    private Map<String, List<String>> readSMB;

    ;

    Reader(String path) {
        this.path = path;
        this.file = new File(path);
        this.readSMB = new HashMap<>();
    }

    public void read() throws IOException {
        BufferedReader smb = new BufferedReader(new FileReader(this.path));
        String line = smb.readLine();
        while (line != null){
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
                try {
                    line = smb.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
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
    // NO COMMENTS IN VAR
    private String readVAR(BufferedReader smb, String line) {
        String[] split;
        List<String> var = new ArrayList<>();

        while (!containsKeyWord(line)) {
            if (!line.replaceAll("\\s+", "").equals("\n")) {
                line = line.replaceAll("=", "");
                line = line.replaceAll(";", "");
                line = line.replaceAll("\\.\\.", " ");
                split = line.split("\\s+");
                for (String a : split) {
                    System.out.println(a);
                }
                for (int i = 0; i < split.length; i++) {
                    var.add(split[i]);
                }
                this.readSMB.put("VAR", var);
            }
            try {
                line = smb.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return line;
    }

    // REG format : reg [cond] => var ;
    private String readREG(BufferedReader smb, String line) {
        String[] split;
        List<String> reg = new ArrayList<>();
        while (!containsKeyWord(line)) {
            if (!line.replaceAll("\\s+", "").equals("\n")) {
                line = line.replaceAll("=>", "");
                line = line.replaceAll(";", "");
                line = line.replaceAll("\\[", "");
                line = line.replaceAll("]", "");
                split = line.split("\\s+");
                for (String a : split) {
                    System.out.println(a);
                }
                for (int i = 0; i < split.length; i++) {
                    reg.add(split[i]);
                }
                this.readSMB.put("REG", reg);
            }
            try {
                line = smb.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return line;
    }

    // PARA format : k = 0..1 ;
    private String readPARA(BufferedReader smb, String line) {
        String[] split;
        List<String> para = new ArrayList<>();
        while (!containsKeyWord(line)) {
            if (!line.replaceAll("\\s+", "").equals("\n")) {
                line = line.replaceAll("=", "");
                line = line.replaceAll(";", "");
                line = line.replaceAll("\\.\\.", " ");
                split = line.split("\\s+");
                for (String a : split) {
                    System.out.println(a);
                }
                for (int i = 0; i < split.length; i++) {
                    para.add(split[i]);
                }
                this.readSMB.put("PARA", para);
            }
            try {
                line = smb.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return line;
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
}