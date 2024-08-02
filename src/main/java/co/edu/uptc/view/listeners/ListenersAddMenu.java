package co.edu.uptc.view.listeners;

import co.edu.uptc.enums.ETypeFile;
import co.edu.uptc.model.Product;
import co.edu.uptc.model.Supplier;
import co.edu.uptc.view.controller.ViewController;

import javax.swing.*;
import java.util.Objects;

public class ListenersAddMenu {

    private final ViewController viewController ;
    private int selectedIndex;

    public ListenersAddMenu(ViewController viewController) {

        this.viewController = viewController;
        changeVisibility();
        backVisibility();
        getInformation();
    }

    public void changeVisibility(){
        viewController.getMainPanel().getManagementInfoPanel().getAddProductButton().addActionListener(e -> {
            viewController.getMainPanel().getManagementInfoPanel().setVisible(false);
            viewController.getMainPanel().getAddProductsPanel().setVisible(true);
        });
    }


    public void backVisibility(){
        viewController.getMainPanel().getAddProductsPanel().getBackButton().addActionListener(e -> {
            viewController.getMainPanel().getAddProductsPanel().setVisible(false);
            viewController.getMainPanel().getManagementInfoPanel().setVisible(true);

        });
    }

    public void getInformation() {
        viewController.getMainPanel().getAddProductsPanel().getConfirmButton().addActionListener(e -> {
            String supplierId = viewController.getMainPanel().getAddProductsPanel().getSupplierIdField().getText();
            String productId = viewController.getMainPanel().getAddProductsPanel().getProductIdField().getText();
            String name = viewController.getMainPanel().getAddProductsPanel().getProductNameField().getText();
            String price = viewController.getMainPanel().getAddProductsPanel().getPriceField().getText();
            String description = viewController.getMainPanel().getAddProductsPanel().getDescriptionField().getText();
            checkInformation(supplierId.trim(),productId.trim(),name.trim(),price.trim(),description.trim());
        });
    }

    private void checkInformation(String supplierId, String productId, String name, String price, String description) {
        if (isNullOrEmpty(supplierId, "Por favor ingrese un id de proveedor valido")) return;
        if (!isNumeric(supplierId, "Digite un id de proveedor válido (Valor numérico)")) return;

        if (isNullOrEmpty(productId, "Por favor ingrese un id de producto valido")) return;
        if (!isNumeric(productId, "Digite un id de producto válido (Valor numérico)")) return;

        if (isNullOrEmpty(name, "Por favor digite un nombre valido")) return;
        if (isNullOrEmpty(description, "Por favor digite una descripcion valida")) return;
        if (isNullOrEmpty(price, "Por favor digite un precio valido")) return;
        if (!isNumeric(price, "Digite un precio válido (Valor numérico)")) return;
        addTheProduct(supplierId,productId,name,price,description);
    }

    private boolean isNullOrEmpty(String field, String message) {
        if (field == null || field.isEmpty()) {
            JOptionPane.showMessageDialog(null, message);
            return true;
        }
        return false;
    }

    private boolean isNumeric(String field, String message) {
        try {
            Integer.parseInt(field);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, message);
            return false;
        }
        return true;
    }


    private void addTheProduct(String supplierId, String productId, String name, String price, String description){
        for(Supplier supplier : viewController.getManagementSupplier().getListSupplier()){

                if (findById(supplierId) == null){
                    JOptionPane.showMessageDialog(null, "Este proveedor no existe " + supplierId);
                    break;
                }else if(findProductById(Integer.parseInt(productId)) != null){
                    JOptionPane.showMessageDialog(null, "Este proveedor ya tiene este producto " + productId);
                    break;
                }else if (Objects.equals(supplier.getId(), supplierId)) {
                    Product product = new Product(Integer.parseInt(productId), name, Double.parseDouble(price), description);
                    supplier.getProducts().add(product);
                    viewController.getManagementSupplier().dumpFile(getFileType());
                    viewController.getManagementSupplier().loadSupplier(getFileType());
                    JOptionPane.showMessageDialog(null, "Producto añadido al proveedor con exito");
                    break;
                }
        }
    }




    private Supplier findById(String id) {
        for (Supplier supplier : viewController.getManagementSupplier().getListSupplier()) {
            if (supplier.getId().equals(id)) {
                return supplier;
            }
        }
        return null;
    }

    private Product findProductById(int id){
        for (Supplier supplier : viewController.getManagementSupplier().getListSupplier()) {
            for (Product product : supplier.getProducts())
            if (product.getProductId() == id) {
                return product;
            }
        }
        return null;
    }


    private ETypeFile getFileType() {
        JComboBox<String> comboBox = viewController.getMainPanel().getManagementInfoPanel().getHeaderMenu();
        selectedIndex = comboBox.getSelectedIndex();
        switch (selectedIndex) {
            case 0:
                return ETypeFile.SER;
            case 1:
                return ETypeFile.XML;
            case 2:
                return ETypeFile.CSV;
            case 3:
                return ETypeFile.JSON;
            case 4:
                return ETypeFile.PLAIN;
            default:
                return null;
        }
    }
}
