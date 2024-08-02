package co.edu.uptc.view.listeners;

import co.edu.uptc.enums.ETypeFile;
import co.edu.uptc.model.Product;
import co.edu.uptc.model.Supplier;
import co.edu.uptc.view.controller.ViewController;

import javax.swing.*;
import java.util.ArrayList;

public class ListenersUpdateMenu {

    private final ViewController viewController;
    private int selectedIndex;

    public ListenersUpdateMenu(ViewController viewController) {
        this.viewController = viewController;
        actionListenerUpdatePanel();
        actionListenerUpdateNewSupplier();
        backToManagementInfoPanel();
    }


    public void actionListenerUpdatePanel() {
        viewController.getMainPanel().getManagementInfoPanel().getAddButton().addActionListener(e -> {
            viewController.getMainPanel().getManagementInfoPanel().setVisible(false);
            viewController.getMainPanel().getUpdatePanel().setVisible(true);
        });
    }

    public void actionListenerUpdateNewSupplier() {
        viewController.getMainPanel().getUpdatePanel().getConfirmButton().addActionListener(e -> {
            String email = viewController.getMainPanel().getUpdatePanel().getEmailField().getText();
            String address = viewController.getMainPanel().getUpdatePanel().getAddressField().getText();
            String name = viewController.getMainPanel().getUpdatePanel().getNameField().getText();
            String nameCompany = viewController.getMainPanel().getUpdatePanel().getNameCompanyField().getText();
            String phone = viewController.getMainPanel().getUpdatePanel().getPhoneField().getText();
            String id = viewController.getMainPanel().getUpdatePanel().getIdField().getText();
            String productId = viewController.getMainPanel().getUpdatePanel().getProductIdField().getText();
            String productName = viewController.getMainPanel().getUpdatePanel().getProductNameField().getText();
            String price = viewController.getMainPanel().getUpdatePanel().getPriceField().getText();
            String description = viewController.getMainPanel().getUpdatePanel().getDescriptionField().getText();
            dataCheckerSupplier(email.trim(), address.trim(), name.trim(), nameCompany.trim(), phone.trim(), id,productId.trim(),productName.trim(),price.trim(),description.trim());

        });
    }

    private Supplier findById(String id) {
        for (Supplier supplier : viewController.getManagementSupplier().getListSupplier()) {
            if (supplier.getId().equals(id)) {
                return supplier;
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


    private void dataCheckerSupplier(String email, String address, String name, String nameCompany, String phone, String id,
                                     String productId, String productName, String price, String description) {
        if (isNullOrEmpty(email, "Digite un email válido")) return;
        if(isEmail(email,"Digite un email valido")!=2) return;
        if (isNullOrEmpty(address, "Digite una dirección válida")) return;
        if (isNullOrEmpty(name, "Digite un nombre válido")) return;
        if (isNullOrEmpty(nameCompany, "Digite un nombre de empresa válido")) return;
        if (isNullOrEmpty(phone, "Digite un teléfono válido (Valor numérico)")) return;
        if (!isNumeric(phone, "Digite un número de teléfono válido (Valor numérico)")) return;
        if (isNullOrEmpty(id, "Digite un ID válido")) return;
        if (findById(id) != null) {
            JOptionPane.showMessageDialog(null, "El ID " + id + " ya existe");
            return;
        }
        if (isNullOrEmpty(productId, "Digite un ID de producto válido (Valor numérico)")) return;
        if (!isNumeric(productId, "Digite un ID de producto válido (Valor numérico)")) return;
        if (isNullOrEmpty(productName, "Digite un nombre de producto válido")) return;
        if (isNullOrEmpty(price, "Digite un precio válido (Valor numérico)")) return;
        if (!isDecimal(price, "Digite un precio válido (Valor numérico)")) return;
        if (isNullOrEmpty(description, "Digite una descripción válida")) return;
            Product product = new Product(Integer.parseInt(productId), productName, Double.parseDouble(price), description);
            Supplier supplier = new Supplier(name, id, new ArrayList<>(), nameCompany, address, phone, email);
            supplier.getProducts().add(product);
            viewController.getManagementSupplier().getListSupplier().add(supplier);
            viewController.getManagementSupplier().dumpFile(getFileType());
            viewController.getManagementSupplier().loadSupplier(getFileType());
            JOptionPane.showMessageDialog(null, "Proveedor añadido con Éxito");

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

    private int isEmail(String email,String message){
        int checker=0;
        //ejemplo@gmail.com
        for (int i=0;i<email.length(); i++){
            char character = email.charAt(i);//Obtenemos la letra de la posicion i
            if(character=='@'||character =='.'){
                checker++;
            }
        }
        if(checker!=2){
            JOptionPane.showMessageDialog(null,message);
        }
        return checker;
    }

    private boolean isDecimal(String field, String message) {
        try {
            Double.parseDouble(field);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, message);
            return false;
        }
        return true;
    }


    public void backToManagementInfoPanel(){
        viewController.getMainPanel().getUpdatePanel().getBackButton().addActionListener(e -> {
            viewController.getMainPanel().getUpdatePanel().setVisible(false);
            viewController.getMainPanel().getManagementInfoPanel().setVisible(true);
        });
    }



}
