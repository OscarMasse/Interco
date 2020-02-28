import java.io.File;
import java.util.List;
import java.util.Map;

public class Reader {

    private String path;
    private File file;
    private Map<String, List<String>> read;

    Reader(String path){
        this.path = path;
        this.file = new File(path);
    }

    private void read(){
        

    }
}
