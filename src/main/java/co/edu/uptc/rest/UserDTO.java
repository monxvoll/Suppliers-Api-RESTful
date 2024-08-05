package co.edu.uptc.rest;

import co.edu.uptc.model.User;
import co.edu.uptc.persistence.ManagementSupplier;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Clase para manejar las operaciones relacionadas con los usuarios a través de una API REST.
 * Permite obtener, agregar, y eliminar usuarios.
 *
 * @author @monx.voll
 */
@Path("/ManagementUser")
public class UserDTO {

    private final ManagementSupplier managementSupplier;

    /**
     * Constructor que inicializa la instancia de ManagementSupplier.
     */
    public UserDTO() {
        managementSupplier = ManagementSupplier.getInstance();
    }

    /**
     * Obtiene todos los usuarios.
     *
     * @return Respuesta con la lista de usuarios en formato JSON o un mensaje si no hay usuarios.
     */
    @GET
    @Path("/getUser")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        synchronized (managementSupplier) {
            managementSupplier.getUserList().clear();
            managementSupplier.loadFileJSONUser();
        }
        List<User> users = managementSupplier.getUserList();
        if (users.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).entity("No hay usuarios disponibles").build();
        }

        return Response.ok(users).build();
    }

    /**
     * Obtiene un usuario por nombre y contraseña.
     *
     * @param name     Nombre del usuario.
     * @param password Contraseña del usuario.
     * @return Respuesta indicando si el usuario existe y la contraseña es correcta.
     */
    @GET
    @Path("/getUserByName")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByName(@QueryParam("name") String name, @QueryParam("password") String password) {
        synchronized (managementSupplier) {
            managementSupplier.getUserList().clear();
            managementSupplier.loadFileJSONUser();
        }

        if (isNullOrEmpty(name) || isNullOrEmpty(password)) {
            return Response.status(Response.Status.OK).entity("Null").build();
        }

        User user = findByName(name);

        if (user != null && user.getUserPassword().equals(password)) {
            return Response.status(Response.Status.OK).entity("True").build();
        } else {
            return Response.status(Response.Status.OK).entity("False").build();
        }
    }

    /**
     * Agrega un nuevo usuario.
     *
     * @param user Usuario a agregar.
     * @return Respuesta indicando si el usuario fue agregado exitosamente o si ya existe.
     */
    @POST
    @Path("/addUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {
        synchronized (managementSupplier) {
            managementSupplier.loadFileJSONUser();
        }

        String userName = user.getUserName();
        String userPass = user.getUserPassword();

        if (findByName(userName) != null) {
            return Response.status(Response.Status.OK).entity("False").build();
        }

        if (isNullOrEmpty(userName) || isNullOrEmpty(userPass)) {
            return Response.status(Response.Status.OK).entity("Null").build();
        }

        User newUser = new User(userName, userPass);

        synchronized (managementSupplier) {
            managementSupplier.getUserList().add(newUser);
            managementSupplier.dumpFileJSONUser();
            managementSupplier.getUserList().clear();
        }

        return Response.status(Response.Status.OK).entity("True").build();
    }

    /**
     * Elimina un usuario por nombre y contraseña.
     *
     * @param name     Nombre del usuario a eliminar.
     * @param password Contraseña del usuario.
     * @return Respuesta indicando si el usuario fue eliminado o no.
     */
    @DELETE
    @Path("/deleteUser")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@QueryParam("name") String name, @QueryParam("password") String password) {
        if (isNullOrEmpty(name) || isNullOrEmpty(password)) {
            return Response.status(Response.Status.OK).entity("Null").build();
        }

        synchronized (managementSupplier) {
            managementSupplier.getUserList().clear();
            managementSupplier.loadFileJSONUser();

            User existingUser = findByName(name);
            if (existingUser == null) {
                return Response.status(Response.Status.OK).entity("Inex").build();
            }

            if (!existingUser.getUserPassword().equals(password)) {
                return Response.status(Response.Status.OK).entity("False").build();
            }

            managementSupplier.getUserList().remove(existingUser);
            managementSupplier.dumpFileJSONUser();
            managementSupplier.getUserList().clear();
            managementSupplier.loadFileJSONUser();
        }

        return Response.status(Response.Status.OK).entity("True").build();
    }

    /**
     * Busca un usuario por nombre en la lista de usuarios.
     *
     * @param name Nombre del usuario a buscar.
     * @return Usuario encontrado o null si no existe.
     */
    private User findByName(String name) {
        synchronized (managementSupplier) {
            for (User user : managementSupplier.getUserList()) {
                if (user.getUserName().equals(name)) {
                    return user;
                }
            }
        }
        return null;
    }

    /**
     * Verifica si un campo es nulo o vacío.
     *
     * @param field Campo a verificar.
     * @return Verdadero si el campo es nulo o vacío, falso en caso contrario.
     */
    private boolean isNullOrEmpty(String field) {
        return field == null || field.isEmpty();
    }
}