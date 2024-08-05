package co.edu.uptc.persistence;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import co.edu.uptc.model.Supplier;
import co.edu.uptc.model.Product;
import co.edu.uptc.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import co.edu.uptc.constants.CommonConstants;
import co.edu.uptc.enums.ETypeFile;

/**
 * Clase que maneja la persistencia de datos de proveedores y usuarios.
 * Soporta múltiples formatos de archivo: XML, JSON, CSV, TXT y objetos serializados.
 *
 * @author @monx.voll
 */
public class ManagementSupplier extends FilePlain {

	private static ManagementSupplier instance;
	private List<Supplier> ListSupplier;
	private List<User> userList;

	/**
	 * Constructor que inicializa las listas de proveedores y usuarios.
	 */
	public ManagementSupplier() {
		this.ListSupplier = new ArrayList<>();
		this.userList = new ArrayList<>();
	}

	/**
	 * Obtiene la instancia única de ManagementSupplier (Singleton).
	 *
	 * @return Instancia de ManagementSupplier
	 */
	public static synchronized ManagementSupplier getInstance() {
		if (instance == null) {
			instance = new ManagementSupplier();
		}
		return instance;
	}

	/**
	 * Guarda los datos de proveedores en el formato especificado.
	 *
	 * @param eTypeFile Tipo de archivo para guardar los datos
	 */
	public void dumpFile(ETypeFile eTypeFile) {
		switch (eTypeFile) {
			case XML:
				this.dumpFileXML();
				break;
			case TXT:
				this.dumpFilePlain(confValue.getPath().concat(confValue.getNameFileTXT()));
				break;
			case JSON:
				this.dumpFileJSON();
				break;
			case SER:
				this.dumpFileSeralizate();
				break;
			case CSV:
				this.dumpFilePlain(confValue.getPath().concat(confValue.getNameFileCSV()));
				break;
		}
	}

	/**
	 * Carga los datos de proveedores desde el formato especificado.
	 *
	 * @param eTypeFile Tipo de archivo para cargar los datos
	 */
	public void loadSupplier(ETypeFile eTypeFile) {
		getListSupplier().clear();
		switch (eTypeFile) {
			case TXT:
				this.loadFilePlain("C:/Users/skson/IdeaProjects/Supplier/resources/data/supplier.txt");
				break;
			case XML:
				this.loadFileXML("C:/Users/skson/IdeaProjects/Supplier/resources/data/supplier.xml");
				break;
			case JSON:
				this.loadFileJSON();
				break;
			case SER:
				this.loadFileSerializate();
				break;
			case CSV:
				this.loadFilePlain("C:/Users/skson/IdeaProjects/Supplier/resources/data/supplier.csv");
				break;
		}
	}

	/**
	 * Guarda los datos de proveedores en un archivo de texto plano.
	 *
	 * @param filePath Ruta y nombre del archivo
	 */
	public void dumpFilePlain(String filePath) {
		List<String> records = new ArrayList<>();
		for (Supplier supplier : ListSupplier) {
			StringBuilder contentSupplier = new StringBuilder();
			contentSupplier.append(supplier.getId()).append(CommonConstants.SEMI_COLON);
			contentSupplier.append(supplier.getName()).append(CommonConstants.SEMI_COLON);
			contentSupplier.append(supplier.getEmail()).append(CommonConstants.SEMI_COLON);
			contentSupplier.append(supplier.getAddress()).append(CommonConstants.SEMI_COLON);
			contentSupplier.append(supplier.getNameCompany()).append(CommonConstants.SEMI_COLON);
			contentSupplier.append(supplier.getPhoneNumber()).append(CommonConstants.SEMI_COLON);
			for (Product product : supplier.getProducts()) {
				contentSupplier.append(product.getProductId()).append(CommonConstants.COMMA)
						.append(product.getProductName()).append(CommonConstants.COMMA)
						.append(product.getPrice()).append(CommonConstants.COMMA)
						.append(product.getDescription()).append(CommonConstants.PIPE);
			}
			records.add(contentSupplier.toString());
		}
		this.writer(filePath, records);
	}

	/**
	 * Carga los datos de proveedores desde un archivo de texto plano.
	 *
	 * @param path Ruta del archivo
	 */
	public void loadFilePlain(String path) {
		File file = new File(path);

		// Verifica que el archivo exista y no esté vacío
		if (!file.exists()) {
			System.err.println("El archivo no existe: " + path);
			return;
		}

		if (file.length() == 0) {
			System.err.println("El archivo está vacío, no se encontraron proveedores.");
			return;
		}

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(line, ";");

				// Verifica si hay al menos 6 tokens
				if (tokenizer.countTokens() >= 6) {
					String id = tokenizer.nextToken().trim();
					String name = tokenizer.nextToken().trim();
					String email = tokenizer.nextToken().trim();
					String address = tokenizer.nextToken().trim();
					String companyName = tokenizer.nextToken().trim();
					String phoneNumber = tokenizer.nextToken().trim();

					Supplier supplier = new Supplier(name, id, new ArrayList<>(), companyName, address, phoneNumber, email);

					// Procesar productos si existen
					if (tokenizer.hasMoreTokens()) {
						String productsToken = tokenizer.nextToken().trim();
						if (!productsToken.isEmpty()) {
							String[] products = productsToken.split("\\|");
							for (String product : products) {
								String[] productDetails = product.split(",");
								if (productDetails.length == 4) {
									try {
										Integer productId = Integer.valueOf(productDetails[0].trim());
										String productName = productDetails[1].trim();
										Double productPrice = Double.parseDouble(productDetails[2].trim());
										String productDescription = productDetails[3].trim();
										supplier.getProducts().add(new Product(productId, productName, productPrice, productDescription));
									} catch (NumberFormatException e) {
										System.err.println("Error al convertir ID o precio del producto: " + product + " - " + e.getMessage());
									}
								} else {
									System.err.println("Error en el formato del producto: " + product);
								}
							}
						}
					}

					getListSupplier().add(supplier);

				} else {
					System.err.println("Error al cargar proveedor, línea insuficiente: " + line);
				}
			}
		} catch (IOException e) {
			System.err.println("Error al leer el archivo: " + e.getMessage());
		}
	}

	/**
	 * Guarda los datos de proveedores en un archivo XML.
	 */
	public void dumpFileXML() {
		String rutaArchivo = confValue.getPath().concat(confValue.getNameFileXML());
		StringBuilder lines = new StringBuilder();
		lines.append("<XML version=\"1.0\" encoding=\"UTF-8\"> \n");
		for (Supplier supplier : ListSupplier) {
			lines.append("<Supplier>\n");
			lines.append("<number>").append(supplier.getPhoneNumber()).append("</number>\n");
			lines.append("<nameCompany>").append(supplier.getNameCompany()).append("</nameCompany>\n");
			lines.append("<address>").append(supplier.getAddress()).append("</address>\n");
			lines.append("<email>").append(supplier.getEmail()).append("</email>\n");
			lines.append("<name>").append(supplier.getName()).append("</name>\n");
			lines.append("<id>").append(supplier.getId()).append("</id>\n");
			lines.append("<products>\n");
			for (Product product : supplier.getProducts()) {
				lines.append("<product>\n");
				lines.append("<productId>").append(product.getProductId()).append("</productId>\n");
				lines.append("<productName>").append(product.getProductName()).append("</productName>\n");
				lines.append("<price>").append(product.getPrice()).append("</price>\n");
				lines.append("<description>").append(product.getDescription()).append("</description>\n");
				lines.append("</product>\n");
			}
			lines.append("</products>\n");
			lines.append("</Supplier>\n");
		}
		lines.append("</XML>");
		this.writeFile(rutaArchivo, lines.toString());
	}

	/**
	 * Carga los datos de proveedores desde un archivo XML.
	 *
	 * @param filePath Ruta del archivo XML
	 */
	public void loadFileXML(String filePath) {
		try {
			File file = new File(filePath);

			// Verifica que el archivo no esté vacío
			if (file.length() == 0) {
				System.err.println("El archivo está vacío, no se encontraron proveedores.");
				return;
			} else {
				System.out.println("Información cargada correctamente");
			}

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);

			doc.getDocumentElement().normalize();

			NodeList supplierList = doc.getElementsByTagName("Supplier");

			for (int i = 0; i < supplierList.getLength(); i++) {
				Node supplierNode = supplierList.item(i);

				if (supplierNode.getNodeType() == Node.ELEMENT_NODE) {
					Element supplierElement = (Element) supplierNode;

					// Verifica que los elementos existan antes de acceder a ellos
					String name = getTextContent(supplierElement, "name");
					String id = getTextContent(supplierElement, "id");
					String phoneNumber = getTextContent(supplierElement, "number");
					String nameCompany = getTextContent(supplierElement, "nameCompany");
					String address = getTextContent(supplierElement, "address");
					String email = getTextContent(supplierElement, "email");

					NodeList productList = supplierElement.getElementsByTagName("product");
					List<Product> products = new ArrayList<>();

					for (int j = 0; j < productList.getLength(); j++) {
						Node productNode = productList.item(j);

						if (productNode.getNodeType() == Node.ELEMENT_NODE) {
							Element productElement = (Element) productNode;

							Integer productId = Integer.parseInt(getTextContent(productElement, "productId"));
							String productName = getTextContent(productElement, "productName");
							Double price = Double.parseDouble(getTextContent(productElement, "price"));
							String description = getTextContent(productElement, "description");

							products.add(new Product(productId, productName, price, description));
						}
					}

					Supplier supplier = new Supplier(name, id, products, nameCompany, address, phoneNumber, email);
					ListSupplier.add(supplier);
				}
			}
		} catch (ParserConfigurationException | IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.err.println("Error de formato numérico en el XML: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Obtiene el contenido de texto de un elemento XML dado un nombre de etiqueta.
	 *
	 * @param element Elemento XML
	 * @param tagName Nombre de la etiqueta
	 * @return Contenido de texto del elemento
	 */
	private String getTextContent(Element element, String tagName) {
		NodeList nodeList = element.getElementsByTagName(tagName);
		if (nodeList.getLength() > 0) {
			return nodeList.item(0).getTextContent();
		}
		return "";
	}

	/**
	 * Guarda los datos de proveedores en un archivo JSON.
	 */
	public void dumpFileJSON() {
		String rutaArchivo = confValue.getPath() + confValue.getNameFileJSON();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonString = gson.toJson(this.ListSupplier) + System.lineSeparator();
		writeFile(rutaArchivo, jsonString);
		System.out.println("Archivo JSON guardado correctamente.");
	}

	/**
	 * Carga los datos de proveedores desde un archivo JSON.
	 */
	public void loadFileJSON() {
		String fileName = "C:/Users/skson/IdeaProjects/Supplier/resources/data/supplier.json";
		try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
			Gson gson = new Gson();
			Type supplierListType = new TypeToken<List<Supplier>>() {}.getType();
			List<Supplier> suppliers = gson.fromJson(br, supplierListType);
			if (suppliers != null && !suppliers.isEmpty()) {
				this.ListSupplier.addAll(suppliers);
				System.out.println("Información cargada correctamente.");
			} else {
				System.out.println("El archivo está vacío, no se encontraron proveedores.");
			}
		} catch (IOException e) {
			System.err.println("Error al cargar el archivo JSON: " + e.getMessage());
		}
	}

	/**
	 * Guarda los datos de proveedores en un archivo serializado.
	 */
	public void dumpFileSeralizate() {
		String rutaArchivo = "C:/Users/skson/IdeaProjects/Supplier/resources/data/supplier.ser";
		try (FileOutputStream fileOut = new FileOutputStream(rutaArchivo);
			 ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
			out.writeObject(this.ListSupplier);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Guarda los datos de usuarios en un archivo JSON.
	 */
	public void dumpFileJSONUser() {
		String rutaArchivo = "resources/data/login/user.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonString = gson.toJson(this.userList) + System.lineSeparator();
		writeFile(rutaArchivo, jsonString);
		System.out.println("Archivo JSON guardado correctamente.");
	}

	/**
	 * Carga los datos de usuarios desde un archivo JSON.
	 */
	public void loadFileJSONUser() {
		getUserList().clear();
		String fileName = "C:/Users/skson/IdeaProjects/Supplier/resources/data/login/user.json";
		try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
			Gson gson = new Gson();
			Type userListType = new TypeToken<List<User>>() {}.getType();
			List<User> users = gson.fromJson(br, userListType);
			if (users != null && !users.isEmpty()) {
				this.userList.addAll(users);
				System.out.println("Información cargada correctamente.");
			} else {
				System.out.println("El archivo está vacío, no se encontraron proveedores.");
			}
		} catch (IOException e) {
			System.err.println("Error al cargar el archivo JSON: " + e.getMessage());
		}
	}

	/**
	 * Carga los datos de proveedores desde un archivo serializado.
	 */
	public void loadFileSerializate() {
		String rutaArchivo = "C:/Users/skson/IdeaProjects/Supplier/resources/data/supplier.ser";
		File file = new File(rutaArchivo);

		// Verifica que el archivo no esté vacío y que exista
		if (!file.exists()) {
			System.err.println("El archivo no existe: " + rutaArchivo);
			return;
		}

		if (file.length() == 0) {
			System.err.println("El archivo está vacío, no se encontraron proveedores.");
			return;
		}

		try (FileInputStream fileIn = new FileInputStream(file);
			 ObjectInputStream in = new ObjectInputStream(fileIn)) {
			List<Supplier> suppliers = (List<Supplier>) in.readObject();
			if (suppliers != null && !suppliers.isEmpty()) {
				this.ListSupplier.addAll(suppliers);
				System.out.println("Información cargada correctamente desde: " + rutaArchivo);
			} else {
				System.err.println("No se encontraron proveedores en el archivo.");
			}
		} catch (FileNotFoundException e) {
			System.err.println("Archivo no encontrado: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Error al leer el archivo: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println("Clase no encontrada durante la deserialización: " + e.getMessage());
		}
	}

	/**
	 * Obtiene la lista de proveedores.
	 *
	 * @return Lista de proveedores
	 */
	public List<Supplier> getListSupplier() {
		return ListSupplier;
	}

	/**
	 * Obtiene la lista de usuarios.
	 *
	 * @return Lista de usuarios
	 */
	public List<User> getUserList() {
		return userList;
	}
}