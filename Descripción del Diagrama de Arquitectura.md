**Descripción del Diagrama de Arquitectura**

**1. Paquete configs:**

-   **Responsabilidad:** Configuraciones de la aplicación, como Swagger.

-   **Componentes:** Clases de configuración (e.g., SwaggerConfig).

**Visualización:**

-   Caja que contiene clases de configuración específicas.

-   Conectada al resto del sistema para ilustrar su uso transversal.

**2. Paquete controllers:**

-   **Responsabilidad:** Exponer las API RESTful.

-   **Componentes:** Controlador de usuario (UserController).

**Visualización:**

-   Caja que contiene UserController.

-   Conectado al paquete services para ilustrar la interacción con la
    lógica de negocio.

**3. Paquete entities:**

-   **Responsabilidad:** Definir las entidades de la base de datos.

-   **Componentes:** Clases de entidades (User, Phone).

**Visualización:**

-   Caja que contiene las entidades User y Phone.

-   Conectada al paquete repositories para ilustrar la relación con la
    persistencia de datos.

**4. Paquete model:**

-   **Responsabilidad:** Definir los DTOs para la transferencia de
    datos.

-   **Componentes:** Clases DTO (UserRequestDTO, UserResponseDTO,
    PhoneDTO).

**Visualización:**

-   Caja que contiene los DTOs.

-   Conectada al paquete controllers para ilustrar su uso en la
    comunicación entre el frontend y el backend.

-   También conectada al paquete services para su uso en la lógica de
    negocio.

**5. Paquete repositories:**

-   **Responsabilidad:** Gestión de la persistencia de datos.

-   **Componentes:** Repositorio de usuario (UserRepository).

**Visualización:**

-   Caja que contiene UserRepository.

-   Conectada al paquete entities para ilustrar las operaciones CRUD
    sobre las entidades.

-   Conectada al paquete services para ilustrar su uso en la lógica de
    negocio.

**6. Paquete services:**

-   **Responsabilidad:** Implementación de la lógica de negocio.

-   **Componentes:** Servicios de usuario (UserService).

**Visualización:**

-   Caja que contiene UserService.

-   Conectada al paquete repositories para ilustrar la interacción con
    la persistencia de datos.

-   Conectada al paquete controllers para ilustrar la exposición de la
    lógica de negocio a través de APIs RESTful.

**7. Paquete utility:**

-   **Responsabilidad:** Proveer constantes y utilidades generales.

-   **Componentes:** Clases de utilidades (e.g., Constants).

**Visualización:**

-   Caja que contiene las clases de utilidad.

-   Conectada transversalmente a services, controllers, y repositories
    para ilustrar su uso en toda la aplicación.
