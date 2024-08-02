package co.edu.uptc.view.panels;

import javax.swing.*;
import java.awt.*;

public class GraphicalReportsPanel extends JPanel {

    private JButton pieChartButton;
    private JButton barChartButton;
    private JButton lineChartButton;
    private JButton customChartButton;
    private JButton backButton; // Nuevo botón "Back"

    public GraphicalReportsPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Añade espacio entre los botones

        pieChartButton = new JButton("Show Pie Chart");
        barChartButton = new JButton("Show Bar Chart");
        lineChartButton = new JButton("Show Line Chart");
        customChartButton = new JButton("Show Custom Chart");
        backButton = new JButton("Back"); // Inicializa el nuevo botón

        // Panel para contener los botones de gráficos y centrarlos verticalmente
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints buttonGbc = new GridBagConstraints();
        buttonGbc.insets = new Insets(10, 10, 10, 10); // Añade espacio entre los botones

        buttonGbc.gridx = 0;
        buttonGbc.gridy = 0;
        buttonPanel.add(pieChartButton, buttonGbc);

        buttonGbc.gridx = 1;
        buttonPanel.add(barChartButton, buttonGbc);

        buttonGbc.gridx = 2;
        buttonPanel.add(lineChartButton, buttonGbc);

        buttonGbc.gridx = 3;
        buttonPanel.add(customChartButton, buttonGbc);

        // Añadir el panel de botones gráficos al centro del panel principal
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0; // Añade peso vertical para centrar el contenido
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);

        // Añadir el botón "Back" en la parte inferior
        gbc.gridy = 1;
        gbc.weighty = 0.0; // Restablece el peso vertical
        gbc.anchor = GridBagConstraints.PAGE_END; // Alinea el botón en la parte inferior
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(backButton, gbc);
    }

    public JButton getPieChartButton() {
        return pieChartButton;
    }

    public JButton getBarChartButton() {
        return barChartButton;
    }

    public JButton getLineChartButton() {
        return lineChartButton;
    }

    public JButton getCustomChartButton() {
        return customChartButton;
    }

    public JButton getBackButton() {
        return backButton;
    }
}