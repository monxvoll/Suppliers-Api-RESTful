package co.edu.uptc.view.main;

import co.edu.uptc.view.panels.PrincipalFrame;

import javax.swing.*;

public class Runner {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(PrincipalFrame::new);
    }
}
