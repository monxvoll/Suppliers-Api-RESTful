package co.edu.uptc.rest;


import co.edu.uptc.model.User;
import co.edu.uptc.persistence.ManagementSupplier;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/ManagementUser")
public class UserDTO {

    private ManagementSupplier managementSupplier;

    public UserDTO() {
        managementSupplier = ManagementSupplier.getInstance();
    }


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


    @GET
    @Path("/getUserByName")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByName(@QueryParam("name") String name , @QueryParam("password") String password ) {

        synchronized (managementSupplier) {
            managementSupplier.getUserList().clear();
            managementSupplier.loadFileJSONUser();
        }
        User user = findByName(name);

        if(isNullOrEmpty(name)){
            return Response.status(Response.Status.OK).entity("Null").build();
        }
        if(isNullOrEmpty(password)){
            return Response.status(Response.Status.OK).entity("Null").build();
        }

        if((user!=null)&&(user.getUserPassword().equals(password))){
            return Response.status(Response.Status.OK).entity("True").build();
        }else {
            return Response.status(Response.Status.OK).entity("False").build();
        }

    }




    @POST
    @Path("/addUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(User user){

        synchronized (managementSupplier) {
            managementSupplier.loadFileJSONUser();
        }
        String userName = user.getUserName();
        String userPass = user.getUserPassword();

        if(findByName(userName)!=null){
            return Response.status(Response.Status.OK).entity("False").build();
        }

        if(isNullOrEmpty(userName)){
            return Response.status(Response.Status.OK).entity("Null").build();
        }
        if(isNullOrEmpty(userPass)){
            return Response.status(Response.Status.OK).entity("Null").build();
        }
        User user1 = new User(userName,userPass);

        synchronized (managementSupplier){

            managementSupplier.getUserList().add(user1);
            managementSupplier.dumpFileJSONUser();
            managementSupplier.getUserList().clear();
        }

        return Response.status(Response.Status.OK).entity("True").build();
    }


    @DELETE
    @Path("/deleteUser")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSupplier(@QueryParam("name") String name,@QueryParam("password") String password) {
        if (isNullOrEmpty(name)||isNullOrEmpty(password)) {
            return Response.status(Response.Status.OK).entity("Null").build();
        }

        synchronized (managementSupplier) {
            managementSupplier.getUserList().clear();
            managementSupplier.loadFileJSONUser();

            User existingUser = findByName(name);
            if (existingUser == null) {
                return Response.status(Response.Status.OK).entity("Inex").build();
            }

            if(!existingUser.getUserPassword().equals(password)){
                return Response.status(Response.Status.OK).entity("False").build();
            }
            managementSupplier.getUserList().remove(existingUser);
            managementSupplier.dumpFileJSONUser();
            managementSupplier.getUserList().clear();
            managementSupplier.loadFileJSONUser();
        }

        return Response.status(Response.Status.OK).entity("True").build();
    }



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

    private boolean isNullOrEmpty(String field) {
        return field == null || field.isEmpty();
    }
}
