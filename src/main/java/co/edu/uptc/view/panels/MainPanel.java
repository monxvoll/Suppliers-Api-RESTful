package co.edu.uptc.view.panels;

import javax.swing.*;
import java.awt.*;

public class MainPanel  extends JPanel {
    private LoginMenuPanel loginMenuPanel;
    private ManagementInfoPanel managementInfoPanel;
    private CreateSupplierPanel updatePanel;
    private ModifySupplierPanel modifySupplierPanel;
    private DeleteSupplierPanel deleteSupplierPanel;
    private AddProductsPanel addProductsPanel;
    private GraphicalReportsPanel graphicalReportsPanel;
    public MainPanel() {

        loginMenuPanel  = new LoginMenuPanel();
        managementInfoPanel = new ManagementInfoPanel();
        updatePanel = new CreateSupplierPanel();
        deleteSupplierPanel = new DeleteSupplierPanel();
        modifySupplierPanel = new ModifySupplierPanel();
        addProductsPanel = new AddProductsPanel();
        graphicalReportsPanel = new GraphicalReportsPanel();
        loginMenuPanel.setVisible(false);
        managementInfoPanel.setVisible(true);
        updatePanel.setVisible(false);
        modifySupplierPanel.setVisible(false);
        deleteSupplierPanel.setVisible(false);
        addProductsPanel.setVisible(false);
        graphicalReportsPanel.setVisible(false);
        add(updatePanel);
        add(loginMenuPanel);
        add(managementInfoPanel);
        add(modifySupplierPanel);
        add(deleteSupplierPanel);
        add(addProductsPanel);
        add(graphicalReportsPanel);
        updatePanel.setPreferredSize(new Dimension(600, 600));
        deleteSupplierPanel.setPreferredSize(new Dimension(600, 600));
        modifySupplierPanel.setPreferredSize(new Dimension(600,600));
        addProductsPanel.setPreferredSize(new Dimension(600,600));
        graphicalReportsPanel.setPreferredSize(new Dimension(600,600));
    }

    public CreateSupplierPanel getUpdatePanel() {
        return updatePanel;
    }

    public void setUpdatePanel(CreateSupplierPanel updatePanel) {
        this.updatePanel = updatePanel;
    }

    public LoginMenuPanel getLoginMenuPanel() {
        return loginMenuPanel;
    }

    public void setLoginMenuPanel(LoginMenuPanel loginMenuPanel) {
        this.loginMenuPanel = loginMenuPanel;
    }

    public ManagementInfoPanel getManagementInfoPanel() {
        return managementInfoPanel;
    }

    public void setManagementInfoPanel(ManagementInfoPanel managementInfoPanel) {
        this.managementInfoPanel = managementInfoPanel;
    }

    public DeleteSupplierPanel getDeleteSupplierPanel() {
        return deleteSupplierPanel;
    }

    public void setDeleteSupplierPanel(DeleteSupplierPanel deleteSupplierPanel) {
        this.deleteSupplierPanel = deleteSupplierPanel;
    }

    public ModifySupplierPanel getModifySupplierPanel() {
        return modifySupplierPanel;
    }

    public void setModifySupplierPanel(ModifySupplierPanel modifySupplierPanel) {
        this.modifySupplierPanel = modifySupplierPanel;
    }

    public AddProductsPanel getAddProductsPanel() {
        return addProductsPanel;
    }

    public void setAddProductsPanel(AddProductsPanel addProductsPanel) {
        this.addProductsPanel = addProductsPanel;
    }

    public GraphicalReportsPanel getGraphicalReportsPanel() {
        return graphicalReportsPanel;
    }

    public void setGraphicalReportsPanel(GraphicalReportsPanel graphicalReportsPanel) {
        this.graphicalReportsPanel = graphicalReportsPanel;
    }
}
