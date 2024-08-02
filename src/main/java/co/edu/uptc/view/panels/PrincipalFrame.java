package co.edu.uptc.view.panels;

import co.edu.uptc.view.controller.ViewController;

import javax.swing.*;

public class PrincipalFrame extends JFrame {


    private ViewController viewController = new ViewController();
    private MainPanel mainPanel;

     public  PrincipalFrame (){
         this.mainPanel = viewController.getMainPanel();
         panelConfig();
     }

     public void panelConfig(){
         setTitle("Login");
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         add(mainPanel);
         pack();
         setLocationRelativeTo(null);
         setVisible(true);
     }
}
