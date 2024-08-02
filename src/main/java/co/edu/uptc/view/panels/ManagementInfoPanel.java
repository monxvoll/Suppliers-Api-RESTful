package co.edu.uptc.view.panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ManagementInfoPanel extends JPanel {

    private JComboBox<String> headerMenu;
    private JTable table;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton addProductButton;
    private JButton graphicalReportsButton; // Nuevo botón para "Graphical Reports"

    public ManagementInfoPanel() {

        setPreferredSize(new Dimension(800, 600));
        setLayout(new BorderLayout());

        headerMenu = new JComboBox<>(new String[]{"SER", "XML", "CSV", "JSON", "TXT"});
        JPanel headerPanel = new JPanel();
        headerPanel.add(headerMenu);
        add(headerPanel, BorderLayout.NORTH);

        String[] columnNames = {"Columna 1", "Columna 2", "Columna 3", "Columna 4", "Columna 5", "Columna 6", "Columna 7"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        JScrollPane tableScrollPane = new JScrollPane(table);
        add(tableScrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        addButton = new JButton("Add Supplier");
        editButton = new JButton("Edit Supplier");
        deleteButton = new JButton("Delete Supplier");
        addProductButton = new JButton("Add Product");
        graphicalReportsButton = new JButton("Graphical Reports"); // Inicializa el nuevo botón

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(addProductButton);
        buttonPanel.add(graphicalReportsButton); // Añade el nuevo botón al panel

        add(buttonPanel, BorderLayout.SOUTH);

    }

    public JComboBox<String> getHeaderMenu() {
        return headerMenu;
    }

    public JTable getTable() {
        return table;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getAddProductButton() {
        return addProductButton;
    }

    public JButton getGraphicalReportsButton() {
        return graphicalReportsButton;
    }
}
