package co.edu.uptc.model;

import java.io.Serializable;

/**
 * Clase que representa un producto.
 * Implementa Serializable para permitir la serialización del objeto.
 *
 * @author @monx.voll
 */
public class Product implements Serializable {

    private static final long serialVersionUID = 1L; // Identificador de versión para la serialización
    private Integer productId; // Identificador único del producto
    private String productName; // Nombre del producto
    private Double price; // Precio del producto
    private String description; // Descripción del producto

    /**
     * Constructor por defecto.
     */
    public Product() {
    }

    /**
     * Constructor con parámetros para inicializar todos los atributos.
     *
     * @param productId Identificador único del producto
     * @param productName Nombre del producto
     * @param price Precio del producto
     * @param description Descripción del producto
     */
    public Product(Integer productId, String productName, Double price, String description) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.description = description;
    }

    /**
     * Representación en cadena del objeto Product.
     *
     * @return Cadena que representa el producto
     */
    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }

    // Métodos getter y setter

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}