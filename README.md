# Proyecto de Gestión de Proveedores y Usuarios

Este proyecto proporciona una API RESTful para gestionar proveedores, productos y usuarios. La API permite realizar operaciones CRUD sobre proveedores y usuarios, así como gestionar productos asociados a los proveedores.

## Algunas Clases Principales

### 1. `SupplierDTO`
Proporciona los servicios REST para la gestión de proveedores y productos.

#### Endpoints:
- `POST /ManagementSupplier/addSupplier`
  - Agrega un nuevo proveedor.
  - Parámetros: `Supplier` (JSON), `fileType` (QueryParam)

- `GET /ManagementSupplier/getSuppliers`
  - Obtiene la lista de proveedores.
  - Parámetros: `fileType` (QueryParam)

- `PUT /ManagementSupplier/updateSupplier`
  - Actualiza la información de un proveedor existente.
  - Parámetros: `Supplier` (JSON), `fileType` (QueryParam)

- `PUT /ManagementSupplier/updateProduct`
  - Actualiza un producto en un proveedor específico.
  - Parámetros: `supplierId` (QueryParam), `Product` (JSON), `fileType` (QueryParam)

- `DELETE /ManagementSupplier/deleteSupplier`
  - Elimina un proveedor por su ID.
  - Parámetros: `id` (QueryParam), `fileType` (QueryParam)

- `DELETE /ManagementSupplier/deleteProduct`
  - Elimina un producto de un proveedor específico.
  - Parámetros: `productId` (QueryParam), `supplierId` (QueryParam), `fileType` (QueryParam)

- `POST /ManagementSupplier/addProductToSupplier`
  - Añade un producto a un proveedor.
  - Parámetros: `supplierId` (QueryParam), `Product` (JSON), `fileType` (QueryParam)

- `GET /ManagementSupplier/getProductById`
  - Obtiene productos por el ID del proveedor.
  - Parámetros: `id` (QueryParam), `fileType` (QueryParam)

### 2. `UserDTO`
Maneja las operaciones relacionadas con los usuarios a través de una API REST.

#### Endpoints:
- `GET /ManagementUser/getUser`
  - Obtiene todos los usuarios.

- `GET /ManagementUser/getUserByName`
  - Obtiene un usuario por nombre y contraseña.
  - Parámetros: `name` (QueryParam), `password` (QueryParam)

- `POST /ManagementUser/addUser`
  - Agrega un nuevo usuario.
  - Parámetros: `User` (JSON)

- `DELETE /ManagementUser/deleteUser`
  - Elimina un usuario por nombre y contraseña.
  - Parámetros: `name` (QueryParam), `password` (QueryParam)

### 3. `ETypeFile`
Enumeración que define los tipos de archivo soportados en la aplicación.
- Valores: `XML`, `CSV`, `TXT`, `JSON`, `SER`

## Instalación

1. Clona el repositorio:
   ```bash
   git clone <URL del repositorio>
