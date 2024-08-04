package co.edu.uptc.rest;

import co.edu.uptc.enums.ETypeFile;
import co.edu.uptc.model.Product;
import co.edu.uptc.model.Supplier;
import co.edu.uptc.persistence.ManagementSupplier;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/ManagementSupplier")
public class SupplierDTO {

    private ManagementSupplier managementSupplier;

    public SupplierDTO() {
        managementSupplier = ManagementSupplier.getInstance();
    }

    @POST
    @Path("/addSupplier")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addSupplier(Supplier supplier, @QueryParam("fileType") String fileType) {
        if (isNullOrEmpty(fileType)) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Debe especificar un tipo de archivo").build();
        }

        ETypeFile type;
        try {
            type = ETypeFile.valueOf(fileType.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Tipo de archivo no válido").build();
        }
        synchronized (managementSupplier) {

            managementSupplier.loadSupplier(type);
        }

        String id = supplier.getId();
        if (isNullOrEmpty(id)) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Digite un ID válido").build();
        }
        if (findById(id) != null) {
            return Response.status(Response.Status.CONFLICT).entity("El ID " + id + " ya existe").build();
        }

        if (isNullOrEmpty(supplier.getEmail()) || !isValidEmail(supplier.getEmail())) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Digite un email válido").build();
        }
        if (isNullOrEmpty(supplier.getAddress())) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Digite una dirección válida").build();
        }
        if (isNullOrEmpty(supplier.getName())) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Digite un nombre válido").build();
        }
        if (isNullOrEmpty(supplier.getNameCompany())) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Digite un nombre de empresa válido").build();
        }
        if (!isPhoneNumberValid(supplier.getPhoneNumber())) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Digite un teléfono válido").build();
        }

        List<Product> products = supplier.getProducts();
        if (products == null || products.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Debe proporcionar al menos un producto").build();
        }

        for (Product product : products) {
            if (product.getProductId() <= 0 || isNullOrEmpty(product.getProductName()) || product.getPrice() <= 0 || isNullOrEmpty(product.getDescription())) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Digite todos los campos del producto válidos").build();
            }
        }

        Supplier newSupplier = new Supplier(supplier.getName(), id, new ArrayList<>(), supplier.getNameCompany(), supplier.getAddress(), supplier.getPhoneNumber(), supplier.getEmail());
        newSupplier.getProducts().addAll(products);


        synchronized (managementSupplier) {
            managementSupplier.getListSupplier().add(newSupplier);
            managementSupplier.dumpFile(type);
            managementSupplier.getListSupplier().clear();
        }

        return Response.ok("Proveedor añadido con éxito").build();
    }

    @GET
    @Path("/getSuppliers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSuppliers(@QueryParam("fileType") String fileType) {
        if (isNullOrEmpty(fileType)) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Debe especificar un tipo de archivo").build();
        }

        ETypeFile type;
        try {
            type = ETypeFile.valueOf(fileType.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Tipo de archivo no válido").build();
        }

        synchronized (managementSupplier) {
            managementSupplier.getListSupplier().clear();
            managementSupplier.loadSupplier(type);
        }

        List<Supplier> suppliers = managementSupplier.getListSupplier();
        if (suppliers.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).entity("No hay proveedores disponibles").build();
        }

        return Response.ok(suppliers).build();
    }

    @PUT
    @Path("/updateSupplier")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSupplier(Supplier supplier, @QueryParam("fileType") String fileType) {
        if (isNullOrEmpty(fileType)) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Debe especificar un tipo de archivo").build();
        }

        ETypeFile type;
        try {
            type = ETypeFile.valueOf(fileType.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Tipo de archivo no válido").build();
        }

        String id = supplier.getId();
        if (isNullOrEmpty(id)) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Digite un ID válido").build();
        }

        synchronized (managementSupplier) {
            managementSupplier.getListSupplier().clear();
            managementSupplier.loadSupplier(type);

            Supplier existingSupplier = findById(id);
            if (existingSupplier == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("El proveedor con ID " + id + " no existe").build();
            }

            if (!isNullOrEmpty(supplier.getEmail())) {
                if (!isValidEmail(supplier.getEmail())) {
                    return Response.status(Response.Status.BAD_REQUEST).entity("Digite un email válido").build();
                }
                existingSupplier.setEmail(supplier.getEmail());
            }

            if (!isNullOrEmpty(supplier.getAddress())) {
                existingSupplier.setAddress(supplier.getAddress());
            }

            if (!isNullOrEmpty(supplier.getName())) {
                existingSupplier.setName(supplier.getName());
            }

            if (!isNullOrEmpty(supplier.getNameCompany())) {
                existingSupplier.setNameCompany(supplier.getNameCompany());
            }

            if (!isNullOrEmpty(supplier.getPhoneNumber())) {
                if (!isPhoneNumberValid(supplier.getPhoneNumber())) {
                    return Response.status(Response.Status.BAD_REQUEST).entity("Digite un teléfono válido").build();
                }
                existingSupplier.setPhoneNumber(supplier.getPhoneNumber());
            }

            if (supplier.getProducts() != null) {
                for (Product product : supplier.getProducts()) {
                    if (product.getProductId() <= 0 || isNullOrEmpty(product.getProductName()) || product.getPrice() <= 0 || isNullOrEmpty(product.getDescription())) {
                        return Response.status(Response.Status.BAD_REQUEST).entity("Digite todos los campos del producto válidos").build();
                    }
                }
                existingSupplier.setProducts(supplier.getProducts());
            }

            managementSupplier.getListSupplier().remove(existingSupplier);
            managementSupplier.getListSupplier().add(existingSupplier);
            managementSupplier.dumpFile(type);
            managementSupplier.getListSupplier().clear();
            managementSupplier.loadSupplier(type);
        }

        return Response.ok("Proveedor actualizado con éxito").build();
    }

    @DELETE
    @Path("/deleteSupplier")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSupplier(@QueryParam("id") String id, @QueryParam("fileType") String fileType) {
        if (isNullOrEmpty(fileType)) {
            return Response.ok("Null").build();
        }

        ETypeFile type;
        try {
            type = ETypeFile.valueOf(fileType.toUpperCase());
        } catch (IllegalArgumentException e) {
             return Response.ok("invalidType").build();
        }

        if (isNullOrEmpty(id)) {
              return Response.ok("Null").build();
        }

        synchronized (managementSupplier) {
            managementSupplier.getListSupplier().clear();
            managementSupplier.loadSupplier(type);

            Supplier existingSupplier = findById(id);
            if (existingSupplier == null) {
                  return Response.ok("Inex").build();
            }

            managementSupplier.getListSupplier().remove(existingSupplier);
            managementSupplier.dumpFile(type);
            managementSupplier.getListSupplier().clear();
            managementSupplier.loadSupplier(type);
        }

        return Response.ok("True").build();
    }
    @DELETE
    @Path("/deleteProduct")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@QueryParam("productId") String productId, @QueryParam("supplierId") String supplierId, @QueryParam("fileType") String fileType) {
        if (isNullOrEmpty(fileType)) {
            return Response.ok("Null").build();
        }
        if (isNullOrEmpty(supplierId)) {
            return Response.ok("Null").build();
        }
        if (isNullOrEmpty(productId)) {
            return Response.ok("Null").build();
        }

        int productIdParam;
        try {
            productIdParam = Integer.parseInt(productId);
        } catch (NumberFormatException e) {
            return Response.ok("invalidId").build();
        }

        ETypeFile type;
        try {
            type = ETypeFile.valueOf(fileType.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Response.ok("invalidType").build();
        }

        synchronized (managementSupplier) {
            managementSupplier.getListSupplier().clear();
            managementSupplier.loadSupplier(type);

            Supplier existingSupplier = findById(supplierId);
            if (existingSupplier == null) {
                return Response.ok("InexS").build();
            } else {
                for (Product product : existingSupplier.getProducts()) {
                    if (product.getProductId() == productIdParam) {
                        existingSupplier.getProducts().remove(product);
                        managementSupplier.dumpFile(type);
                        return Response.ok("True").build();
                    }
                }
                return Response.ok("False").build();
            }
        }
    }

    @POST
    @Path("/addProductToSupplier")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProductToSupplier(@QueryParam("id") String id, Product product, @QueryParam("fileType") String fileType) {
        if (isNullOrEmpty(id)) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Digite un ID válido").build();
        }

        ETypeFile type;
        try {
            type = ETypeFile.valueOf(fileType.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Tipo de archivo no válido").build();
        }

        synchronized (managementSupplier) {
            managementSupplier.getListSupplier().clear();
            managementSupplier.loadSupplier(type);

            Supplier existingSupplier = findById(id);
            if (existingSupplier == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("El proveedor con ID " + id + " no existe").build();
            }

            if (product.getProductId() <= 0 || isNullOrEmpty(product.getProductName()) || product.getPrice() <= 0 || isNullOrEmpty(product.getDescription())) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Digite todos los campos del producto válidos").build();
            }

            existingSupplier.getProducts().add(product);
            managementSupplier.dumpFile(type);
            managementSupplier.getListSupplier().clear();
            managementSupplier.loadSupplier(type);
        }

        return Response.ok("Producto añadido con éxito").build();
    }

    @GET
    @Path("/getProductById")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getProductsByCode(@QueryParam("id") String id, @QueryParam("fileType") ETypeFile fileType) {
        synchronized (managementSupplier) {
            managementSupplier.loadSupplier(fileType);


            if (managementSupplier.getListSupplier().isEmpty()) {
                return Response.status(Response.Status.OK).entity(new ArrayList<>()).build();
            }

            for (Supplier supplier : managementSupplier.getListSupplier()) {
                if (supplier.getId().equals(id)) {
                    return Response.ok(supplier.getProducts()).build();
                }
            }
            return Response.status(Response.Status.NOT_FOUND).entity(new ArrayList<>()).build();
        }
    }

    private Supplier findById(String id) {
        synchronized (managementSupplier) {
            for (Supplier supplier : managementSupplier.getListSupplier()) {
                if (supplier.getId().equals(id)) {
                    return supplier;
                }
            }
        }
        return null;
    }

    private boolean isNullOrEmpty(String field) {
        return field == null || field.isEmpty();
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("\\d{10}");
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$");
    }
}