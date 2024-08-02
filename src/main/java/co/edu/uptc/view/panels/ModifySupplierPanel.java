package co.edu.uptc.view.panels;

import javax.swing.*;
import java.awt.*;

public class ModifySupplierPanel extends JPanel {

    private JTextField nameCompanyField;
    private JTextField addressField;
    private JTextField emailField;
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField idField;
    private JTextField productIdField;
    private JTextField productNameField;
    private JTextField priceField;
    private JTextField descriptionField;
    private JButton confirmButton;
    private JButton backButton;
    private JLabel nameCompanyLabel;
    private JLabel addressLabel;
    private JLabel emailLabel;
    private JLabel nameLabel;
    private JLabel phoneLabel;
    private JLabel idLabel;
    private JLabel productIdLabel;
    private JLabel productNameLabel;
    private JLabel priceLabel;
    private JLabel descriptionLabel;

    public ModifySupplierPanel() {
        initializer();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();


        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Dimension labelDimension = new Dimension(120, 20);
        Dimension fieldDimension = new Dimension(10, 20);

        nameLabel.setPreferredSize(labelDimension);
        nameCompanyLabel.setPreferredSize(labelDimension);
        addressLabel.setPreferredSize(labelDimension);
        emailLabel.setPreferredSize(labelDimension);
        phoneLabel.setPreferredSize(labelDimension);
        idLabel.setPreferredSize(labelDimension);
        productIdLabel.setPreferredSize(labelDimension);
        productNameLabel.setPreferredSize(labelDimension);
        priceLabel.setPreferredSize(labelDimension);
        descriptionLabel.setPreferredSize(labelDimension);

        // Nombre
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(5, 5, 0, 5);
        add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(nameField, gbc);

        // Nombre de la empresa
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(nameCompanyLabel, gbc);

        gbc.gridx = 1;
        add(nameCompanyField, gbc);

        // Dirección
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(addressLabel, gbc);

        gbc.gridx = 1;
        add(addressField, gbc);

        // Email
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(emailLabel, gbc);

        gbc.gridx = 1;
        add(emailField, gbc);

        // Número
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(phoneLabel, gbc);

        gbc.gridx = 1;
        add(phoneField, gbc);

        // Id
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(idLabel, gbc);

        gbc.gridx = 1;
        add(idField, gbc);

        // ID del Producto
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(productIdLabel, gbc);

        gbc.gridx = 1;
        add(productIdField, gbc);

        // Nombre del Producto
        gbc.gridx = 0;
        gbc.gridy = 7;
        add(productNameLabel, gbc);

        gbc.gridx = 1;
        add(productNameField, gbc);

        // Precio
        gbc.gridx = 0;
        gbc.gridy = 8;
        add(priceLabel, gbc);

        gbc.gridx = 1;
        add(priceField, gbc);

        // Descripción
        gbc.gridx = 0;
        gbc.gridy = 9;
        add(descriptionLabel, gbc);

        gbc.gridx = 1;
        add(descriptionField, gbc);

        // Botón de confirmar
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 0, 5);
        add(confirmButton, gbc);

        // Botón de retroceder
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 5, 0, 10);
        add(backButton, gbc);
    }

    public void initializer() {
        this.nameLabel = new JLabel("New Name:");
        this.nameCompanyLabel = new JLabel("New Company Name:");
        this.addressLabel = new JLabel("New Address:");
        this.emailLabel = new JLabel("New Email:");
        this.phoneLabel = new JLabel("New Phone Number:");
        this.idLabel = new JLabel("ID:");
        this.productIdLabel = new JLabel("New Product ID:");
        this.productNameLabel = new JLabel("New Product Name:");
        this.priceLabel = new JLabel("New Price:");
        this.descriptionLabel = new JLabel("New Description:");
        this.nameField = new JTextField(15);
        this.nameCompanyField = new JTextField(15);
        this.addressField = new JTextField(15);
        this.emailField = new JTextField(15);
        this.phoneField = new JTextField(15);
        this.idField = new JTextField(15);
        this.productIdField = new JTextField(15);
        this.productNameField = new JTextField(15);
        this.priceField = new JTextField(15);
        this.descriptionField = new JTextField(15);
        this.confirmButton = new JButton("Confirm");
        this.backButton = new JButton("Back");
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getNameCompanyField() {
        return nameCompanyField;
    }

    public JTextField getAddressField() {
        return addressField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }

    public JLabel getNameLabel() {
        return nameLabel;
    }

    public JLabel getNameCompanyLabel() {
        return nameCompanyLabel;
    }

    public JLabel getAddressLabel() {
        return addressLabel;
    }

    public JLabel getEmailLabel() {
        return emailLabel;
    }

    public JTextField getPhoneField() {
        return phoneField;
    }

    public JLabel getPhoneLabel() {
        return phoneLabel;
    }

    public JTextField getIdField() {
        return idField;
    }

    public JLabel getIdLabel() {
        return idLabel;
    }

    public JTextField getProductIdField() {
        return productIdField;
    }

    public JTextField getProductNameField() {
        return productNameField;
    }

    public JTextField getPriceField() {
        return priceField;
    }

    public JTextField getDescriptionField() {
        return descriptionField;
    }

    public JLabel getProductIdLabel() {
        return productIdLabel;
    }

    public JLabel getProductNameLabel() {
        return productNameLabel;
    }

    public JLabel getPriceLabel() {
        return priceLabel;
    }

    public JLabel getDescriptionLabel() {
        return descriptionLabel;
    }

    public JButton getBackButton() {
        return backButton;
    }
}
