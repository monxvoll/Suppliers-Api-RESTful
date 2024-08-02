package co.edu.uptc.view.panels;

import javax.swing.*;
import java.awt.*;

public class AddProductsPanel extends JPanel {
    private JTextField supplierIdField;
    private JTextField productIdField;
    private JTextField productNameField;
    private JTextField priceField;
    private JTextField descriptionField;
    private JButton confirmButton;
    private JButton backButton;
    private JLabel supplierIdLabel;
    private JLabel productIdLabel;
    private JLabel productNameLabel;
    private JLabel priceLabel;
    private JLabel descriptionLabel;

    public AddProductsPanel() {
        initializer();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Dimension labelDimension = new Dimension(100, 20);
        Dimension fieldDimension = new Dimension(10, 20);

        supplierIdLabel.setPreferredSize(labelDimension);
        productIdLabel.setPreferredSize(labelDimension);
        productNameLabel.setPreferredSize(labelDimension);
        priceLabel.setPreferredSize(labelDimension);
        descriptionLabel.setPreferredSize(labelDimension);

        supplierIdField.setPreferredSize(fieldDimension);
        productIdField.setPreferredSize(fieldDimension);
        productNameField.setPreferredSize(fieldDimension);
        priceField.setPreferredSize(fieldDimension);
        descriptionField.setPreferredSize(fieldDimension);

        // Supplier ID
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(5, 5, 0, 5);
        add(supplierIdLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 0, 0, 5);
        add(supplierIdField, gbc);

        // Product ID
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(productIdLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(productIdField, gbc);

        // Product Name
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(productNameLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(productNameField, gbc);

        // Price
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(priceLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(priceField, gbc);

        // Description
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(descriptionLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(descriptionField, gbc);

        // Confirm Button
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 0, 5);
        add(confirmButton, gbc);

        // Back Button
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 5, 0, 10);
        add(backButton, gbc);
    }

    public void initializer() {
        this.supplierIdLabel = new JLabel("Supplier ID:");
        this.productIdLabel = new JLabel("Product ID:");
        this.productNameLabel = new JLabel("Product Name:");
        this.priceLabel = new JLabel("Product Price:");
        this.descriptionLabel = new JLabel("Description:");

        this.supplierIdField = new JTextField(10);
        this.productIdField = new JTextField(10);
        this.productNameField = new JTextField(10);
        this.priceField = new JTextField(10);
        this.descriptionField = new JTextField(10);

        this.confirmButton = new JButton("Confirm");
        this.backButton = new JButton("Back");
    }

    // Getters
    public JTextField getSupplierIdField() {
        return supplierIdField;
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

    public JButton getConfirmButton() {
        return confirmButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JLabel getSupplierIdLabel() {
        return supplierIdLabel;
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