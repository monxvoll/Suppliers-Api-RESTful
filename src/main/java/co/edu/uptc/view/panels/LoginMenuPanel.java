package co.edu.uptc.view.panels;

import javax.swing.*;
import java.awt.*;

public class LoginMenuPanel extends JPanel {
    private JTextField user;
    private JPasswordField password;
    private JButton confirm;
    private JButton addButton;
    private JLabel passwordLabel;
    private JLabel userLabel;

    public LoginMenuPanel(){
        initializer();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(10, 10, 0, 10);
        add(userLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(10, 0, 0, 10);
        add(user, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(10, 10, 0, 10);
        add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(10, 0, 0, 10);
        add(password, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 0, 10);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(confirm);
        buttonPanel.add(addButton);
        add(buttonPanel, gbc);
    }

    public void initializer(){
        this.passwordLabel = new JLabel("Contraseña");
        this.userLabel = new JLabel("Usuario");
        this.user = new JTextField(15);
        this.password = new JPasswordField(15);
        this.confirm = new JButton("Enviar");
        this.addButton = new JButton("Añadir");
    }

    public JTextField getUser() {
        return user;
    }

    public void setUser(JTextField user) {
        this.user = user;
    }

    public JPasswordField getPassword() {
        return password;
    }

    public void setPassword(JPasswordField password) {
        this.password = password;
    }

    public JButton getConfirm() {
        return confirm;
    }

    public void setConfirm(JButton confirm) {
        this.confirm = confirm;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public void setAddButton(JButton addButton) {
        this.addButton = addButton;
    }
}