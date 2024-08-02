package co.edu.uptc.persistence;

import co.edu.uptc.config.Config;
import co.edu.uptc.constants.CommonConstants;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class FilePlain {
    protected Config confValue;
    private static final String ERROR_WRITING_FILE = "Error al escribir en el archivo: ";

    public FilePlain() {
        confValue = Config.getInstance();
    }

    public Config getConfValue() {
        return confValue;
    }



    protected void writer(String rutaNombre, List<String> file) {
        StringBuilder strContent = new StringBuilder();
        for (String record : file) {
            strContent.append(record).append(CommonConstants.NEXT_LINE); // Añade un salto de línea después de cada registro
        }
        writeFile(rutaNombre, strContent.toString());
    }

    protected void writeFile(String filePath, String content) {
        filePath = "C:/Users/skson/IdeaProjects/Supplier/" + filePath;
        System.out.println(filePath);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, false))) { // Modo append
            bw.write(content);
        } catch (IOException e) {
            System.err.println(ERROR_WRITING_FILE + e.getMessage());
        }
    }


}