
package co.edu.uptc.model;

import java.io.Serializable;
import java.util.List;

public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private String id;
    private List<Product> products;
    private String nameCompany;
    private String address;
    private String phoneNumber;
    private String email;

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

    public Supplier(){

    }
    public Supplier(String name, String id, List<Product> products, String nameCompany, String address, String phoneNumber, String email) {
        this.name = name;
        this.id = id;
        this.products = products;
        this.nameCompany = nameCompany;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }



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