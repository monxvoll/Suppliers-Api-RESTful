package co.edu.uptc.view.panels;

import javax.swing.*;
import java.awt.*;

public class DeleteSupplierPanel extends JPanel {

    private JTextField idField;
    private JButton confirmButton;
    private JButton backButton;
    private JLabel idLabel;

    public DeleteSupplierPanel() {
        initializer();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();


        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Dimension labelDimension = new Dimension(100, 20);
        Dimension fieldDimension = new Dimension(10, 20);

        idLabel.setPreferredSize(labelDimension);

        // Id
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(5, 5, 0, 5);
        add(idLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(idField, gbc);

        // Botón de confirmar
        gbc.gridx = 0;
        gbc.gridy = 1;
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
        this.idLabel = new JLabel("Digite el ID del proveedor que desea eliminar:");
        this.idField = new JTextField(10);
        this.confirmButton = new JButton("Confirm");
        this.backButton = new JButton("Back");
    }

    public JButton getConfirmButton() {
        return confirmButton;
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
}