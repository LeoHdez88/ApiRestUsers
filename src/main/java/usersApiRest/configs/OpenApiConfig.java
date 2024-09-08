package usersApiRest.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Creacion de Usuarios",
                version = "1.0.0",
                description = "Ejercicio Java ApiRest full",
                contact=@Contact(name = "LeonardoHernandez", url = "https://www.linkedin.com/in/leonardo-hernandez-6770961b7/", email = "Leonardohernandez@gmail.com")
        )
)
public class OpenApiConfig {

}
