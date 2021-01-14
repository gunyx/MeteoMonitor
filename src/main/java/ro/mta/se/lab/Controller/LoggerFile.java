package ro.mta.se.lab.Controller;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Gunyx
 * Clasa responsabila de scrierea in fisierul de iesire
 */
public class LoggerFile {

    public LoggerFile() {
    }

    public void scriereFisier(String fisierIesire, String info) throws IOException {
        FileWriter fileOut = null;
        fileOut = new FileWriter(fisierIesire, true);

        fileOut.write(info + "\n");

        fileOut.close();
    }
}
