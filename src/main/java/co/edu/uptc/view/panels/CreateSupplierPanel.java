package co.edu.uptc.view.panels;

import javax.swing.*;
import java.awt.*;

public class CreateSupplierPanel extends JPanel {
    private JTextField nameCompanyField;
    private JTextField addressField;
    private JTextField emailField;
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField idField;
    private JButton confirmButton;
    private JButton backButton;
    private JLabel nameCompanyLabel;
    private JLabel addressLabel;
    private JLabel emailLabel;
    private JLabel nameLabel;
    private JLabel phoneLabel;
    private JLabel idLabel;

    private JTextField productIdField;
    private JTextField productNameField;
    private JTextField priceField;
    private JTextField descriptionField;
    private JLabel productIdLabel;
    private JLabel productNameLabel;
    private JLabel priceLabel;
    private JLabel descriptionLabel;

    public CreateSupplierPanel() {
        initializer();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();


        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Dimension labelDimension = new Dimension(100, 20);
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

        nameField.setPreferredSize(fieldDimension);
        nameCompanyField.setPreferredSize(fieldDimension);
        addressField.setPreferredSize(fieldDimension);
        emailField.setPreferredSize(fieldDimension);
        phoneField.setPreferredSize(fieldDimension);
        idField.setPreferredSize(fieldDimension);
        productIdField.setPreferredSize(fieldDimension);
        productNameField.setPreferredSize(fieldDimension);
        priceField.setPreferredSize(fieldDimension);
        descriptionField.setPreferredSize(fieldDimension);


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(5, 5, 0, 5);
        add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 0, 0, 5);
        add(nameField, gbc);

        // Nombre de la empresa
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(nameCompanyLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(nameCompanyField, gbc);

        // Dirección
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(addressLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(addressField, gbc);

        // Email
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(emailLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(emailField, gbc);

        // Número
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(phoneLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(phoneField, gbc);

        // Id
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(idLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(idField, gbc);

        // Product ID
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(productIdLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(productIdField, gbc);

        // Product Name
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(productNameLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(productNameField, gbc);

        // Price
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(priceLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(priceField, gbc);

        // Description
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(descriptionLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
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
        this.nameLabel = new JLabel("Name:");
        this.nameCompanyLabel = new JLabel("Company Name:");
        this.addressLabel = new JLabel("Address:");
        this.emailLabel = new JLabel("Email:");
        this.phoneLabel = new JLabel("Phone Number:");
        this.idLabel = new JLabel("ID:");
        this.productIdLabel = new JLabel("Product ID:");
        this.productNameLabel = new JLabel("Product Name:");
        this.priceLabel = new JLabel("Product Price:");
        this.descriptionLabel = new JLabel("Description:");

        this.nameField = new JTextField(10);
        this.nameCompanyField = new JTextField(10);
        this.addressField = new JTextField(10);
        this.emailField = new JTextField(10);
        this.phoneField = new JTextField(10);
        this.idField = new JTextField(10);
        this.productIdField = new JTextField(10);
        this.productNameField = new JTextField(10);
        this.priceField = new JTextField(10);
        this.descriptionField = new JTextField(10);

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

    public JButton getBackButton() {
        return backButton;
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
}