package co.edu.uptc.view.listeners;

import co.edu.uptc.enums.ETypeFile;
import co.edu.uptc.model.Supplier;
import co.edu.uptc.view.controller.ViewController;

import javax.swing.*;

public class ListenersDeleteMenu {

    private final ViewController viewController ;
    private int selectedIndex;

    public ListenersDeleteMenu(ViewController viewController) {
        this.viewController =  viewController;
        modifyTable();
        backToManagementInfoPanel();
        deleteSupplierFromTable();
    }

    public void modifyTable(){
        viewController.getMainPanel().getManagementInfoPanel().getDeleteButton().addActionListener(e -> {
            viewController.getMainPanel().getManagementInfoPanel().setVisible(false);
            viewController.getMainPanel().getDeleteSupplierPanel().setVisible(true);
        });
    }

    public void  deleteSupplierFromTable(){
        viewController.getMainPanel().getDeleteSupplierPanel().getConfirmButton().addActionListener(e ->{
            String id = viewController.getMainPanel().getDeleteSupplierPanel().getIdField().getText();
            if(findById(id)==null){
                JOptionPane.showMessageDialog(null,"No hay proveedores registrados este ID ");
            }else{
                JOptionPane.showMessageDialog(null,"Borrado con exito");
                viewController.getManagementSupplier().getListSupplier().remove(findById(id));
                viewController.getManagementSupplier().dumpFile(getFileType());
                viewController.getManagementSupplier().loadSupplier(getFileType());
            }

        });
    }

    public void backToManagementInfoPanel(){
        viewController.getMainPanel().getDeleteSupplierPanel().getBackButton().addActionListener(e -> {
            viewController.getMainPanel().getDeleteSupplierPanel().setVisible(false);
            viewController.getMainPanel().getManagementInfoPanel().setVisible(true);
        });
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
