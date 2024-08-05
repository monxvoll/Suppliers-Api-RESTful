package co.edu.uptc.model;

import java.io.Serializable;
import java.util.List;

/**
 * Clase que representa un proveedor.
 * Implementa Serializable para permitir la serialización del objeto.
 *
 * @author @monx.voll
 */
public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L; // Identificador de versión para la serialización
    private String name; // Nombre del proveedor
    private String id; // Identificador del proveedor
    private List<Product> products; // Lista de productos ofrecidos por el proveedor
    private String nameCompany; // Nombre de la empresa del proveedor
    private String address; // Dirección del proveedor
    private String phoneNumber; // Número de teléfono del proveedor
    private String email; // Correo electrónico del proveedor

    /**
     * Constructor por defecto.
     */
    public Supplier() {
    }

    /**
     * Constructor con parámetros para inicializar todos los atributos.
     *
     * @param name Nombre del proveedor
     * @param id Identificador del proveedor
     * @param products Lista de productos ofrecidos por el proveedor
     * @param nameCompany Nombre de la empresa del proveedor
     * @param address Dirección del proveedor
     * @param phoneNumber Número de teléfono del proveedor
     * @param email Correo electrónico del proveedor
     */
    public Supplier(String name, String id, List<Product> products, String nameCompany, String address, String phoneNumber, String email) {
        this.name = name;
        this.id = id;
        this.products = products;
        this.nameCompany = nameCompany;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    /**
     * Representación en cadena del objeto Supplier.
     *
     * @return Cadena que representa el proveedor
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Supplier{");
        sb.append("name='").append(name).append('\'');
        sb.append(", id='").append(id).append('\'');
        sb.append(", nameCompany='").append(nameCompany).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", products=").append(products);
        sb.append('}');
        return sb.toString();
    }

    // Métodos getter y setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}