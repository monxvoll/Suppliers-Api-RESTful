package co.edu.uptc.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Clase Config para manejar la configuración del archivo properties.
 * Esta clase utiliza el patrón Singleton para asegurar que solo haya una instancia.
 *
 * @author @monx.voll
 */
public class Config {
	private static Config config; // Instancia única de Config (Singleton)
	private Properties propiedades; // Propiedades cargadas desde el archivo
	private String path; // Ruta del archivo de configuración
	private String nameFileTXT; // Nombre del archivo TXT
	private String nameFileXML; // Nombre del archivo XML
	private String nameFileJSON; // Nombre del archivo JSON
	private String nameFileSeri; // Nombre del archivo serializado
	private String nameFileCSV; // Nombre del archivo CSV

	/**
	 * Constructor privado para implementar el patrón Singleton.
	 * Carga las propiedades desde el archivo de configuración.
	 */
	private Config() {
		this.propiedades = new Properties();
		try (FileInputStream entrada = new FileInputStream("C:/Users/skson/IdeaProjects/Supplier/resources/conf/appconfig.properties")) {
			propiedades.load(entrada);
			this.path = propiedades.getProperty("app.file.path");
			this.nameFileTXT = propiedades.getProperty("app.file.name.txt");
			this.nameFileXML = propiedades.getProperty("app.file.name.xml");
			this.nameFileJSON = propiedades.getProperty("app.file.name.json");
			this.nameFileSeri = propiedades.getProperty("app.file.name.ser");
			this.nameFileCSV = propiedades.getProperty("app.file.name.csv");
		} catch (IOException ex) {
			System.err.println("Error al cargar el archivo properties de configuración: " + ex.getMessage());
		}
	}

	/**
	 * Método para obtener la instancia única de Config (Singleton).
	 *
	 * @return Instancia única de Config
	 */
	public static Config getInstance() {
		if (config == null) {
			config = new Config();
		}
		return config;
	}

	// Métodos getter para acceder a las propiedades

	public String getPath() {
		return path;
	}

	public String getNameFileTXT() {
		return nameFileTXT;
	}

	public String getNameFileXML() {
		return nameFileXML;
	}

	public String getNameFileJSON() {
		return nameFileJSON;
	}

	public String getNameFileCSV() {
		return nameFileCSV;
	}

	public String getNameFileSeri() {
		return nameFileSeri;
	}
}
