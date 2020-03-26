import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Reader smb = new Reader("circadianCTL.smb");
        smb.read();
        smb.display();

        Translator translator = new Translator(smb.getRead());
        translator.translate();
    }
}
