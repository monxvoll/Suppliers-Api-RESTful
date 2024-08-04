package co.edu.uptc.config;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

	public class Config {
	private static Config config;
	private Properties propiedades;
	private String path;
	private String nameFileTXT;
	private String nameFileXML;
	private String nameFileJSON;
	private String nameFileSeri;
	private String nameFileCSV;

	private Config() {
		this.propiedades= new Properties();
		try (FileInputStream entrada = new FileInputStream("C:/Users/skson/IdeaProjects/Supplier/resources/conf/appconfig.properties")) {
			propiedades.load(entrada);
			this.path = propiedades.getProperty("app.file.path");
			this.nameFileTXT = propiedades.getProperty("app.file.name.txt");
			this.nameFileXML = propiedades.getProperty("app.file.name.xml");;
			this.nameFileJSON= propiedades.getProperty("app.file.name.json");
			this.nameFileSeri = propiedades.getProperty("app.file.name.ser");
			this.nameFileCSV= propiedades.getProperty("app.file.name.csv");
		} catch (IOException ex) {
			System.err.println("Error al cargar el archivo properties de configuración: " + ex.getMessage());
		}
	}


	public static Config getInstance() {
		if (config==null) {
			config= new Config();
		}
		return config;
	}

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
