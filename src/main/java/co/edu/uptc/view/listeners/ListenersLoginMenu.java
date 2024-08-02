package co.edu.uptc.view.listeners;

import co.edu.uptc.model.User;
import co.edu.uptc.view.controller.ViewController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class ListenersLoginMenu {
    private final ViewController viewController ;

    public ListenersLoginMenu(ViewController viewController) {
        this.viewController = viewController;
        actionListenerLogin();
        addActionListener();
    }

    public  void actionListenerLogin(){
        viewController.getMainPanel().getLoginMenuPanel().getConfirm().addActionListener(e -> {
            String password =  viewController.getMainPanel().getLoginMenuPanel().getPassword().getText();
            String user =viewController.getMainPanel().getLoginMenuPanel().getUser().getText();
            if(viewController.getListUser().isEmpty()){
                JOptionPane.showMessageDialog(null,"Aun no hay usuarios registrados");
            }
            for (User userA : viewController.getListUser()){
                if(userA.getUserName().equals(user)&&(userA.getUserPassword().equals(password))){
                    viewController.getMainPanel().getLoginMenuPanel().setVisible(false);
                    viewController.getMainPanel().getManagementInfoPanel().setVisible(true);
                    SwingUtilities.getWindowAncestor(viewController.getMainPanel()).pack();
                }else {
                    JOptionPane.showMessageDialog(null,"Usuario o Contraseña Incorrecto");
                }
            }
        });

    }


    public void addActionListener() {
        viewController.getMainPanel().getLoginMenuPanel().getAddButton().addActionListener(e -> {
            String userName = viewController.getMainPanel().getLoginMenuPanel().getUser().getText();
            String userPassword = String.valueOf(viewController.getMainPanel().getLoginMenuPanel().getPassword().getPassword());

            User newUser = new User(userName, userPassword);

            viewController.getListUser().add(newUser);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Type userListType = new TypeToken<List<User>>() {}.getType();
            String json = gson.toJson(viewController.getListUser(), userListType);
            try (FileWriter writer = new FileWriter("resources/data/login/user.json")) {
                writer.write(json);
                JOptionPane.showMessageDialog(null, "Usuario añadido exitosamente.");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al añadir usuario.");
            }
        });
    }
}
