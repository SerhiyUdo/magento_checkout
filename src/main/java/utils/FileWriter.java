package utils;

import java.io.IOException;

public class FileWriter {

    public static void writeToFile(String path, String data) {
        java.io.FileWriter fw = null;
        try {
            fw = new java.io.FileWriter(path);
            fw.write(data);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
