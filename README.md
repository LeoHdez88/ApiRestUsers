# **README: Cómo Probar la Aplicación**
**Requisitos Previos**

**Java 17:** Asegúrate de tener instalado Java 17 o una versión compatible.

**Maven:** Utilizado para la gestión de dependencias y la compilación del proyecto.

**IntelliJ IDEA:** Aunque puedes usar cualquier IDE, el proyecto está configurado con IntelliJ.

**Postman/Swagger:** Para probar la API REST.

1. **Configuración del Proyecto**

Clonar el Repositorio

git clone *https://github.com/LeoHdez88/ApiRestUsers.git*

1. **Compilar y Construir el Proyecto**

-Navega al directorio del proyecto y ejecuta:

*mvn clean install*

-Esto descargará las dependencias necesarias y construirá la aplicación.

1. **Ejecutar la Aplicación**

-Inicia la aplicación con:

*mvn spring-boot:run*

La aplicación estará disponible en [ http://localhost:8080](%20http://localhost:8080).

1. **Pruebas de la Aplicación**
- Ejecutar Pruebas Unitarias

  Puedes ejecutar las pruebas unitarias desde la línea de comandos:

*mvn test*

Esto ejecutará las pruebas JUnit creadas para UserService y UserController.

- Probar la API con Swagger

  Una vez que la aplicación esté en funcionamiento, navega a:

[*http://localhost:8080/swagger-ui.html*](http://localhost:8080/swagger-ui.html)

Desde aquí, podrás explorar y probar todos los endpoints de la API.

- Probar la API con Postman

  Importa los siguientes endpoints en Postman para probar:

*GET /api/user: Obtiene la lista de usuarios.*

*POST /api/user: Crea un nuevo usuario.*

Ejemplo de body:

json

{

`  `"name": "Juan Rodriguez",

`  `"email": "juan@rodriguez.org",

`  `"password": "hunter2",

`  `"phones": [

`    `{

`      `"number": "1234567",

`      `"citycode": "1",

`      `"countrycode": "57"

`    `}

`  `]

}

Verificar Respuestas

Asegúrate de que los endpoints respondan con el formato correcto y los códigos de estado adecuados (200 OK, 400 BAD REQUEST, etc.).
