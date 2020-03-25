import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Reader {

    private String path;
    private File file;
    private Map<String, List<String>> readSMB;

    Reader(String path){
        this.path = path;
        this.file = new File(path);
    }

    private void read() throws IOException {
        BufferedReader smb = new BufferedReader(new FileReader(this.path));
        String line = smb.readLine();
        while (line != null){
            if (line.charAt(0) == 'V' && line.charAt(1) == 'A' && line.charAt(2) == 'R'){
                readVAR(smb,line);
            }
            else if (line.charAt(0) == 'R' && line.charAt(1) == 'E' && line.charAt(2) == 'G'){
                readREG(smb,line);
            }
            else if (line.charAt(0) == 'P' && line.charAt(1) == 'A' && line.charAt(2) == 'R' && line.charAt(3) == 'A'){
                readPARA(smb,line);
            }
        }
    }

    private void  readVAR(BufferedReader smb, String line){
        String[] split;
        List<String> var = new ArrayList<>();
        while(!(line.contains("REG")) && !(line.contains("PARA")) && !(line.contains("CTL")) && !(line.contains("FAIRCTL")) && (!line.contains("END"))) {
            if (line != "\n" || line.contains("#")) {
                split = line.split(" ");
                var.add(split[0]);
                var.add(split[2]);
                var.add(split[split.length - 1]);
                this.readSMB.put("VAR", var);
            }
        }
    }

    private void  readPARA(BufferedReader smb, String line){

    }

    private void  readREG(BufferedReader smb, String line){

    }
}