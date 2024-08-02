package co.edu.uptc.view.listeners;

import co.edu.uptc.enums.ETypeFile;
import co.edu.uptc.model.Product;
import co.edu.uptc.model.Supplier;
import co.edu.uptc.model.SupplierSorter;
import co.edu.uptc.view.controller.ViewController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;

public class ListenersHeaderMenu {
    private final ViewController viewController;
    private final  SupplierSorter supplierSorter;
    private int selectedIndex;


    public ListenersHeaderMenu(ViewController viewController) {
        this.viewController = viewController;
        this.supplierSorter = new SupplierSorter();
        actionListenerHeaderMenu();
        setupKeyListener();
    }

    public void actionListenerHeaderMenu() {
        viewController.getMainPanel().getManagementInfoPanel().getHeaderMenu().addActionListener(e -> {
            JComboBox<String> comboBox = viewController.getMainPanel().getManagementInfoPanel().getHeaderMenu();
            selectedIndex = comboBox.getSelectedIndex();
            switch (selectedIndex) {
                case 0:
                    viewController.getListSER();
                    break;
                case 1:
                    viewController.getListXML();
                    break;
                case 2:
                    viewController.getListCSV();
                    break;
                case 3:
                    viewController.getListJSON();
                    break;
                case 4:
                    viewController.getLisTXT();
                    break;
                default:
                    throw new IllegalStateException("Valor erroneo " + selectedIndex);
            }
            updateTableWithSuppliers();
        });
    }

    private void updateTableWithSuppliers() {
        List<Supplier> suppliers = viewController.getManagementSupplier().getListSupplier();
        String[] columnNames = {"Name", "ID", "Products", "Name Company", "Address", "Phone Number", "Email"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Supplier supplier : suppliers) {
            StringBuilder productsInfo = new StringBuilder();
            for (Product product : supplier.getProducts()) {
                productsInfo.append(String.format("ID: %d, Name: %s, Price: %.2f, Desc: %s\n",
                        product.getProductId(), product.getProductName(), product.getPrice(), product.getDescription()));
            }
            Object[] row = {
                    supplier.getName(),
                    supplier.getId(),
                    productsInfo.toString(),
                    supplier.getNameCompany(),
                    supplier.getAddress(),
                    supplier.getPhoneNumber(),
                    supplier.getEmail()
            };
            model.addRow(row);
        }

        viewController.getMainPanel().getManagementInfoPanel().getTable().setModel(model);
    }


    public void setupKeyListener() {
        // Obtiene el panel principal donde se van a escuchar las combinaciones de teclas
        JPanel panel = viewController.getMainPanel().getManagementInfoPanel();
        InputMap inputMap = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = panel.getActionMap();

        // Define las claves de acción para cada combinación
        String actionKeyA = "actionA";
        String actionKeyB = "actionB";
        String actionKeyC = "actionC";

        // Asocia las teclas Ctrl + A, Ctrl + B, Ctrl + C con sus respectivas claves de acción
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK), actionKeyA);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_B, KeyEvent.CTRL_DOWN_MASK), actionKeyB);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK), actionKeyC);

        // Define las acciones para Ctrl + A
        actionMap.put(actionKeyA, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Llama al método handleCombination para manejar las teclas adicionales 1 y 2
                handleCombination(KeyEvent.VK_1, KeyEvent.VK_2 ,actionKeyA);
            }
        });

        // Define las acciones para Ctrl + B
        actionMap.put(actionKeyB, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Llama al método handleCombination para manejar las teclas adicionales 1 y 2
                handleCombination(KeyEvent.VK_1, KeyEvent.VK_2, actionKeyB);
            }
        });

        // Define las acciones para Ctrl + C
        actionMap.put(actionKeyC, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Llama al método handleCombination para manejar las teclas adicionales 1 y 2
                handleCombination(KeyEvent.VK_1, KeyEvent.VK_2, actionKeyC);
            }
        });
    }

    private void handleCombination(int key1, int key2, String actionKey) {
        // Obtiene el administrador de enfoque del teclado
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();

        if (manager.getActiveWindow() != null) {
            // Define un despachador de eventos de teclas
            KeyEventDispatcher dispatcher = new KeyEventDispatcher() {
                @Override
                public boolean dispatchKeyEvent(KeyEvent ke) {
                    // Si se presiona una tecla
                    if (ke.getID() == KeyEvent.KEY_PRESSED) {
                        if (ke.getKeyCode() == key1) {

                            // Si la teclas presionas son control + ? + 1 llama al metodo descendente
                            assigmentDescending(actionKey);
                            manager.removeKeyEventDispatcher(this);
                            return true;
                        } else if (ke.getKeyCode() == key2) {
                            // Si la teclas presionas son control + ? + 2 llama al metodo descendente
                            assigmentAscending(actionKey);
                            manager.removeKeyEventDispatcher(this);
                            return true;
                        }
                    }
                    return false;
                }
            };
            // Añade el despachador de eventos de teclas al administrador de enfoque
            manager.addKeyEventDispatcher(dispatcher);
        }
    }


    private void assigmentDescending(String actionKey){
        switch (actionKey){
            case "actionA":
                supplierSorter.bubbleSortByNameDescending(viewController.getManagementSupplier().getListSupplier());
                updateTableWithSuppliers();
                //Tambien se ordenan los archivos
                viewController.getManagementSupplier().dumpFile(getFileType());
                viewController.getManagementSupplier().loadSupplier(getFileType());
                System.out.println("Informacion ordenada de manera descendente para el atributo (nombre) usando (burbuja)");
                break;
            case "actionB":
                supplierSorter.insertionSortByIdDescending(viewController.getManagementSupplier().getListSupplier());
                updateTableWithSuppliers();
                //Tambien se ordenan los archivos
                viewController.getManagementSupplier().dumpFile(getFileType());
                viewController.getManagementSupplier().loadSupplier(getFileType());
                System.out.println("Informacion ordenada de manera descendente para el atributo (id) usando (insersion)");
                break;
            case "actionC":
                supplierSorter.selectionSortByEmailFirstCharDescending(viewController.getManagementSupplier().getListSupplier());
                updateTableWithSuppliers();
                //Tambien se ordenan los archivos
                viewController.getManagementSupplier().dumpFile(getFileType());
                viewController.getManagementSupplier().loadSupplier(getFileType());
                System.out.println("Informacion ordenada de manera descendente para el atributo (email) usando (seleccion)");
                break;
        }
    }


    private void assigmentAscending(String actionKey){
        switch (actionKey){
            case "actionA":
                supplierSorter.bubbleSortByNameAscending(viewController.getManagementSupplier().getListSupplier());
                updateTableWithSuppliers();
                //Tambien se ordenan los archivos
                viewController.getManagementSupplier().dumpFile(getFileType());
                viewController.getManagementSupplier().loadSupplier(getFileType());
                System.out.println("Informacion ordenada de manera ascendente para el atributo (nombre) usando (burbuja) ");
                break;
            case "actionB":
                supplierSorter.insertionSortByIdAscending(viewController.getManagementSupplier().getListSupplier());
                updateTableWithSuppliers();
                //Tambien se ordenan los archivos
                viewController.getManagementSupplier().dumpFile(getFileType());
                viewController.getManagementSupplier().loadSupplier(getFileType());
                System.out.println("Informacion ordenada de manera ascendente para el atributo (id) usando (insersion) ");
                break;
            case "actionC":
                supplierSorter.selectionSortByEmailFirstCharAscending(viewController.getManagementSupplier().getListSupplier());
                updateTableWithSuppliers();
                //Tambien se ordenan los archivos
                viewController.getManagementSupplier().dumpFile(getFileType());
                viewController.getManagementSupplier().loadSupplier(getFileType());
                System.out.println("Informacion ordenada de manera ascendente para el atributo (email) usando (seleccion)");
                break;
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

}
