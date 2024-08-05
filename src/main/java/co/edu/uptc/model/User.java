package co.edu.uptc.model;

/**
 * Clase que representa un usuario con nombre de usuario y contraseña.
 *
 * @author @monx.voll
 */
public class User {
    private String userName; // Nombre de usuario
    private String userPassword; // Contraseña del usuario

    /**
     * Constructor por defecto.
     */
    public User() {
    }

    public User(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    // Métodos getter y setter
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * Representación en cadena del objeto User.
     *
     * @return Cadena que representa el usuario
     */
    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}