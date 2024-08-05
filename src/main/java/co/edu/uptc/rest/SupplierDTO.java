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
import java.util.Objects;

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
        if (isNullOrEmpty(supplier.getId()) ||
                isNullOrEmpty(supplier.getName()) ||
                isNullOrEmpty(supplier.getEmail()) ||
                isNullOrEmpty(supplier.getAddress()) ||
                isNullOrEmpty(supplier.getNameCompany()) ||
                isNullOrEmpty(supplier.getPhoneNumber()) ||
                isNullOrEmpty(fileType)) {
            return Response.ok("Null").build();
        }

        if (!isValidEmail(supplier.getEmail())) {
            return Response.ok("InvalidEmail").build();
        }

        if (!isPhoneNumberValid(supplier.getPhoneNumber())) {
            return Response.ok("InvalidPhone").build();
        }

        ETypeFile type;
        try {
            type = ETypeFile.valueOf(fileType.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Response.ok("InvalidFile").build();
        }
        synchronized (managementSupplier) {

            managementSupplier.loadSupplier(type);
        }

        if (findById(supplier.getId()) != null) {
            return Response.ok("Existence").build();
        }
        Supplier newSupplier = new Supplier(supplier.getName(), supplier.getId(), new ArrayList<>(), supplier.getNameCompany(), supplier.getAddress(), supplier.getPhoneNumber(), supplier.getEmail());
        synchronized (managementSupplier) {
            managementSupplier.getListSupplier().add(newSupplier);
            managementSupplier.dumpFile(type);
            managementSupplier.getListSupplier().clear();
        }

        return Response.ok("True").build();
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
        String id = supplier.getId();
        if (isNullOrEmpty(id)) {
            return Response.ok("NullId").build();
        }

        if (isNullOrEmpty(fileType)) {
            return Response.ok("NullFile").build();
        }

        ETypeFile type;
        try {
            type = ETypeFile.valueOf(fileType.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Response.ok("invalidType").build();
        }


        if (isNullOrEmpty(supplier.getEmail()) &&
                isNullOrEmpty(supplier.getAddress()) &&
                isNullOrEmpty(supplier.getName()) &&
                isNullOrEmpty(supplier.getNameCompany()) &&
                isNullOrEmpty(supplier.getPhoneNumber())) {
            return Response.ok("One").build();
        }

        synchronized (managementSupplier) {
            managementSupplier.loadSupplier(type);
            managementSupplier.dumpFile(type);
            Supplier existingSupplier = findById(id);
            if (existingSupplier == null) {
                return Response.ok("Inex").build();
            }

            if (!isNullOrEmpty(supplier.getEmail())) {
                if (!isValidEmail(supplier.getEmail())) {
                    return Response.status(Response.Status.BAD_REQUEST).entity("Email").build();
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
                    return Response.ok("Phone").build();
                }
                existingSupplier.setPhoneNumber(supplier.getPhoneNumber());
            }
            managementSupplier.getListSupplier().remove(existingSupplier);
            managementSupplier.getListSupplier().add(existingSupplier);
            managementSupplier.dumpFile(type);
            managementSupplier.getListSupplier().clear();
            managementSupplier.loadSupplier(type);
        }

        return Response.ok("True").build();
    }

    @PUT
    @Path("/updateProduct")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(@QueryParam("supplierId") String supplierId, Product updatedProduct, @QueryParam("fileType") String fileType) {

        if (Objects.isNull(updatedProduct.getProductId())) {
            return Response.ok("NullId").build();
        }

        if (isNullOrEmpty(supplierId)) {
            return Response.ok("Null").build();
        }

        if (isNullOrEmpty(fileType)) {
            return Response.ok("NullFile").build();
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
                return Response.ok("InexSupplier").build();
            }

            Product existingProduct = findProductById(existingSupplier, updatedProduct.getProductId());
            if (existingProduct == null) {
                return Response.ok("InexProduct").build();
            }

            Double price = updatedProduct.getPrice();
            if (price != null && (price.isNaN() || price.isInfinite() || price <= 0)) {
                return Response.ok("NoPrice").build();
            }

            if (isNullOrEmpty(updatedProduct.getProductName()) &&
                    isNullOrEmpty(updatedProduct.getDescription()) &&
                    price == null) {
                return Response.ok("NoFields").build();
            }

            if (!isNullOrEmpty(updatedProduct.getProductName())) {
                existingProduct.setProductName(updatedProduct.getProductName());
            }
            if (!isNullOrEmpty(updatedProduct.getDescription())) {
                existingProduct.setDescription(updatedProduct.getDescription());
            }
            if (price != null) {
                existingProduct.setPrice(price);
            }

            managementSupplier.dumpFile(type);
            managementSupplier.loadSupplier(type);
        }

        return Response.ok("True").build();
    }

    private Product findProductById(Supplier supplier, Integer productId) {
        for (Product product : supplier.getProducts()) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        return null;
    }

    @DELETE
    @Path("/deleteSupplier")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSupplier(@QueryParam("id") String id, @QueryParam("fileType") String fileType) {
        if (isNullOrEmpty(fileType)) {
            return Response.ok("Null").build();
        }
        if (isNullOrEmpty(id)) {
            return Response.ok("Null").build();
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
    public Response addProductToSupplier(@QueryParam("supplierId") String supplierId, Product updatedProduct, @QueryParam("fileType") String fileType) {
        if (Objects.isNull(updatedProduct.getProductId())) {
            return Response.status(Response.Status.BAD_REQUEST).entity("NullId").build();
        }

        if (isNullOrEmpty(supplierId)) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Null").build();
        }

        if (isNullOrEmpty(fileType)) {
            return Response.status(Response.Status.BAD_REQUEST).entity("NullFile").build();
        }

        ETypeFile type;
        try {
            type = ETypeFile.valueOf(fileType.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("InvalidFile").build();
        }

        synchronized (managementSupplier) {
            managementSupplier.loadSupplier(type);

            Supplier existingSupplier = findById(supplierId);
            if (existingSupplier == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("InexSupplier").build();
            }

            // Verificación de existencia del producto sin usar streams
            for (Product product : existingSupplier.getProducts()) {
                if (product.getProductId().equals(updatedProduct.getProductId())) {
                    return Response.status(Response.Status.CONFLICT).entity("InvalidProduct").build();
                }
            }

            Double price = updatedProduct.getPrice();
            if (price != null && (price.isNaN() || price.isInfinite() || price <= 0)) {
                return Response.status(Response.Status.BAD_REQUEST).entity("NoPrice").build();
            }

            if (isNullOrEmpty(updatedProduct.getProductName()) ||
                    isNullOrEmpty(updatedProduct.getDescription()) ||
                    price == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("NoFields").build();
            }

            // Actualización del producto
            updatedProduct.setProductName(updatedProduct.getProductName());
            updatedProduct.setDescription(updatedProduct.getDescription());
            updatedProduct.setPrice(price);

            existingSupplier.getProducts().add(updatedProduct);
            managementSupplier.dumpFile(type);
            managementSupplier.getListSupplier().clear();
        }

        return Response.status(Response.Status.CREATED).entity("True").build();
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