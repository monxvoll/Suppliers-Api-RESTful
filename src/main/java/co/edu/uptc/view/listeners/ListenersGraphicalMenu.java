package co.edu.uptc.view.listeners;

import co.edu.uptc.enums.ETypeFile;
import co.edu.uptc.model.Supplier;
import co.edu.uptc.view.controller.ViewController;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;

import java.util.Map;

public class ListenersGraphicalMenu {

    private final ViewController viewController ;
    private int selectedIndex;

    private static Map<String , Integer> map = new HashMap<>();


    public ListenersGraphicalMenu(ViewController viewController) {
        this.viewController = viewController;
        changeVisibility();
        backVisibility();
        showCakeGraph();
        showBarsGraphs(map);
        showLineGraphs(map);
        showCustomGraph(map);
    }

    public void changeVisibility(){
        viewController.getMainPanel().getManagementInfoPanel().getGraphicalReportsButton().addActionListener(e -> {
            viewController.getMainPanel().getManagementInfoPanel().setVisible(false);
            viewController.getMainPanel().getGraphicalReportsPanel().setVisible(true);
        });
    }


    public void backVisibility(){
        viewController.getMainPanel().getGraphicalReportsPanel().getBackButton().addActionListener(e -> {
            viewController.getMainPanel().getGraphicalReportsPanel().setVisible(false);
            viewController.getMainPanel().getManagementInfoPanel().setVisible(true);

        });
    }

    public void fillMap(){
        map.clear(); // Limpia el mapa antes de llenarlo nuevamente
        // Llenar el mapa con la cantidad de productos por proveedor
        for (Supplier supplier : viewController.getManagementSupplier().getListSupplier()) {
            String supplierName = supplier.getName();
            int productsSize = supplier.getProducts().size();
            map.put(supplierName, productsSize);
        }
    }

    public void showCakeGraph() {

        viewController.getMainPanel().getGraphicalReportsPanel().getPieChartButton().addActionListener(e -> {
            viewController.getManagementSupplier().loadSupplier(getFileType());
            if(!viewController.getManagementSupplier().getListSupplier().isEmpty()) {
                fillMap();
                DefaultPieDataset dataset = new DefaultPieDataset();

                map.forEach((k, v) -> {
                    dataset.setValue(k, v);
                });

                JFreeChart chart = ChartFactory.createPieChart(
                        "Cantidad de productos por proveedor", dataset, true, true, false);
                ChartPanel panel = new ChartPanel(chart);

                Frame frame = new Frame();
                frame.setTitle("Titulo");
                frame.setSize(500, 500);
                frame.add(panel);

                // Agregar WindowListener para capturar el evento de cierre de la ventana
                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        frame.dispose(); // Cierra correctamente la ventana
                    }
                });

                frame.setVisible(true);

            }else {
                JOptionPane.showMessageDialog(null,"No existen proveedores actualmente");
            }
        });
    }

    public void showBarsGraphs(Map<String, Integer> map) {

        viewController.getMainPanel().getGraphicalReportsPanel().getBarChartButton().addActionListener(e -> {
            viewController.getManagementSupplier().loadSupplier(getFileType());
            if(!viewController.getManagementSupplier().getListSupplier().isEmpty()) {
                fillMap();
                DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
                map.forEach((k, v) -> {
                    dataSet.setValue(v, "Cantidad de productos por proveedor", k);
                });
                JFreeChart chart = ChartFactory.createBarChart3D(
                        null, /* Nombre del gráfico*/
                        null, /* Nombre de las barras o columnas*/
                        null, /* Nombre de la enumeración */
                        dataSet /* Datos*/,
                        PlotOrientation.VERTICAL, /* Orientacion*/
                        true,    /* Leyenda de cada barra */
                        true,    /* Herramientas */
                        false    /* URL*/
                );

                ChartPanel panel = new ChartPanel(chart);

                JFrame frame = new JFrame();
                frame.setTitle("Titulo");
                frame.setSize(500, 500);
                frame.add(panel);
                frame.setVisible(true);

            }else {
                JOptionPane.showMessageDialog(null,"No existen proveedores actualmente");
            }
        });
    }

    public void showLineGraphs(Map<String, Integer> map) {
        viewController.getMainPanel().getGraphicalReportsPanel().getLineChartButton().addActionListener(e -> {
            viewController.getManagementSupplier().loadSupplier(getFileType());
            if(!viewController.getManagementSupplier().getListSupplier().isEmpty()) {
                fillMap();
                DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
                map.forEach((k, v) -> {
                    dataSet.setValue(v, "Cantidad de productos por proveedor", k);
                });
                JFreeChart chart = ChartFactory.createLineChart(
                        null, /* Nombre del gráfico*/
                        null, /* Nombre de las barras o columnas*/
                        null, /* Nombre de la enumeración */
                        dataSet /* Datos*/,
                        PlotOrientation.VERTICAL, /* Orientacion*/
                        true,    /* Leyenda de cada barra */
                        true,    /* Herramientas */
                        false    /* URL*/
                );

                ChartPanel panel = new ChartPanel(chart);

                JFrame frame = new JFrame();
                frame.setTitle("Titulo");
                frame.setSize(500, 500);
                frame.add(panel);
                frame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "No existen proveedores actualmente");
            }
        });
    }

    public void showCustomGraph(Map<String, Integer> map) {
        viewController.getMainPanel().getGraphicalReportsPanel().getCustomChartButton().addActionListener(e -> {
            viewController.getManagementSupplier().loadSupplier(getFileType());
            if (!viewController.getManagementSupplier().getListSupplier().isEmpty()) {
                fillMap();
                DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
                map.forEach((k, v) -> {
                    dataSet.addValue(v, "Cantidad de productos", k);
                });

                JFreeChart chart = ChartFactory.createAreaChart(
                        "Cantidad de productos por proveedor", // Título del gráfico
                        "Proveedores", // Etiqueta del eje X
                        "Cantidad de productos", // Etiqueta del eje Y
                        dataSet, // Datos
                        PlotOrientation.VERTICAL, // Orientación
                        true, // Incluir leyenda
                        true, // Incluir tooltips
                        false // Excluir URLs
                );

                ChartPanel panel = new ChartPanel(chart);
                JFrame frame = new JFrame();
                frame.setTitle("Gráfico Personalizado");
                frame.setSize(800, 600);
                frame.add(panel);
                frame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "No existen proveedores actualmente");
            }
        });
    }


    public ETypeFile getFileType() {
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

}
