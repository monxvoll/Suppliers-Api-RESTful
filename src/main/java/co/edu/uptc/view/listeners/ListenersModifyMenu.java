package co.edu.uptc.view.listeners;

import co.edu.uptc.enums.ETypeFile;
import co.edu.uptc.model.Product;
import co.edu.uptc.model.Supplier;
import co.edu.uptc.view.controller.ViewController;

import javax.swing.*;
import java.util.ArrayList;

public class ListenersModifyMenu {
    private final ViewController viewController;
    private int selectedIndex;

    public ListenersModifyMenu(ViewController viewController) {
        this.viewController = viewController;
        goToModifyTable();
        backToManagementInfoPanel();
        modifyTable();
    }

    public void modifyTable() {
        viewController.getMainPanel().getModifySupplierPanel().getConfirmButton().addActionListener(e -> {
            String newEmail = viewController.getMainPanel().getModifySupplierPanel().getEmailField().getText();
            String newAdress = viewController.getMainPanel().getModifySupplierPanel().getAddressField().getText();
            String newName = viewController.getMainPanel().getModifySupplierPanel().getNameField().getText();
            String newCompanyName = viewController.getMainPanel().getModifySupplierPanel().getNameCompanyField().getText();
            String newPhoneNumber = viewController.getMainPanel().getModifySupplierPanel().getPhoneField().getText();
            String id = viewController.getMainPanel().getModifySupplierPanel().getIdField().getText();
            String productId = viewController.getMainPanel().getModifySupplierPanel().getProductIdField().getText();
            String productName = viewController.getMainPanel().getModifySupplierPanel().getProductNameField().getText();
            String price = viewController.getMainPanel().getModifySupplierPanel().getPriceField().getText();
            String description = viewController.getMainPanel().getModifySupplierPanel().getDescriptionField().getText();
            dataCheckerSupplier(newEmail.trim(), newAdress.trim(), newName.trim(), newCompanyName.trim(), newPhoneNumber.trim(), id,productId.trim(),productName.trim(),price.trim(),description.trim());

        });
    }

    public void goToModifyTable() {
        viewController.getMainPanel().getManagementInfoPanel().getEditButton().addActionListener(e -> {
            viewController.getMainPanel().getManagementInfoPanel().setVisible(false);
            viewController.getMainPanel().getModifySupplierPanel().setVisible(true);
        });
    }

    public void backToManagementInfoPanel() {
        viewController.getMainPanel().getModifySupplierPanel().getBackButton().addActionListener(e -> {
            viewController.getMainPanel().getModifySupplierPanel().setVisible(false);
            viewController.getMainPanel().getManagementInfoPanel().setVisible(true);
        });
    }

    private void dataCheckerSupplier(String email, String address, String name, String nameCompany, String phone, String id,
                                     String productId, String productName, String price, String description) {
        if (email == null || email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Digite un email válido");
        } else if (address == null || address.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Digite una dirección válida");
        } else if (name == null || name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Digite un nombre válido");
        } else if (nameCompany == null || nameCompany.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Digite un nombre de empresa válido");
        } else if (phone == null || phone.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Digite un teléfono válido (Valor numerico)");
        } else {
            try {
                Integer.parseInt(phone);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Digite un número de teléfono válido (Valor numerico)");
                return;
            }
            if (id == null || id.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Digite un ID válido");
            } else if (productId == null || productId.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Digite un ID de producto válido (Valor numerico)");
            } else if (productName == null || productName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Digite un nombre de producto válido");
            } else if (price == null || price.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Digite un precio válido (Valor numerico)");
            } else {
                try {
                    Double.parseDouble(price); // Verificación para asegurar que el precio sea un número decimal
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Digite un precio válido (Valor numerico)");
                    return;
                }
                try {
                    Integer.parseInt(productId); // Verificación para asegurar que el producti d sea entero
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Digite un ID de producto valido (Valor numerico)");
                    return;
                }
                    if (description == null || description.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Digite una descripción válida");
                    } else {
                        Supplier supplier = new Supplier(name, id, new ArrayList<>(), nameCompany, address, phone, email);
                        Product product = new Product(Integer.parseInt(productId), productName, Double.parseDouble(price), description);

                        if (findById(id) == null) {
                            JOptionPane.showMessageDialog(null, "ID inexistente");
                        } else {
                            viewController.getManagementSupplier().getListSupplier().remove(findById(id));
                            viewController.getManagementSupplier().getListSupplier().add(supplier);
                            supplier.getProducts().add(product);
                            viewController.getManagementSupplier().dumpFile(getFileType());
                            viewController.getManagementSupplier().loadSupplier(getFileType());
                            JOptionPane.showMessageDialog(null, "Proveedor actualizado con Éxito");
                        }
                    }
            }
        }
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

    private Supplier findById(String id) {
        for (Supplier supplier : viewController.getManagementSupplier().getListSupplier()) {
            if (supplier.getId().equals(id)) {
                return supplier;
            }
        }
        return null;
    }



}
