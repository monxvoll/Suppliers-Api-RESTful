package co.edu.uptc.persistence;

import co.edu.uptc.config.Config;
import co.edu.uptc.constants.CommonConstants;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Clase para manejar la escritura de archivos de texto plano.
 * Utiliza la configuración de la clase Config para obtener los parámetros necesarios.
 *
 * @author @monx.voll
 */
public class FilePlain {
    protected Config confValue; // Instancia de Config para obtener configuraciones
    private static final String ERROR_WRITING_FILE = "Error al escribir en el archivo: "; // Mensaje de error para problemas de escritura

    /**
     * Constructor que inicializa la instancia de Config.
     */
    public FilePlain() {
        confValue = Config.getInstance();
    }

    /**
     * Obtiene la instancia de Config utilizada en esta clase.
     *
     * @return Instancia de Config
     */
    public Config getConfValue() {
        return confValue;
    }

    /**
     * Escribe una lista de registros en un archivo de texto plano.
     *
     * @param rutaNombre Ruta y nombre del archivo
     * @param file Lista de registros a escribir en el archivo
     */
    protected void writer(String rutaNombre, List<String> file) {
        StringBuilder strContent = new StringBuilder();
        for (String record : file) {
            strContent.append(record).append(CommonConstants.NEXT_LINE); // Añade un salto de línea después de cada registro
        }
        writeFile(rutaNombre, strContent.toString());
    }

    /**
     * Escribe el contenido en el archivo especificado.
     *
     * @param filePath Ruta del archivo
     * @param content Contenido a escribir en el archivo
     */
    protected void writeFile(String filePath, String content) {
        filePath = "C:/Users/skson/IdeaProjects/Supplier/" + filePath; // Añade la ruta base al archivo
        System.out.println(filePath); // Imprime la ruta del archivo (solo para depuración)
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, false))) { // Modo sobrescribir
            bw.write(content);
        } catch (IOException e) {
            System.err.println(ERROR_WRITING_FILE + e.getMessage()); // Imprime mensaje de error en caso de excepción
        }
    }
}