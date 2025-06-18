package com.lautaropetelin.bazarAPI.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "API Bazar",
                description = "API para registrar ventas y manejar stock de productos y clientes en un Bazar.",
                version = "1.0.0",
                contact = @Contact(
                        name = "Lautaro Tomás Israel Petelin",
                        email = "lautisrra@gmail.com",
                        url = "https://www.linkedin.com/in/lautaro-petelin/"
                )
        ),
        servers = {
                @Server(
                        description = "DEV SERVER",
                        url = "http://localhost:8080"
                )
        }
)
public class SwaggerConfig {

    // http://localhost:8080/swagger-ui/index.html
}