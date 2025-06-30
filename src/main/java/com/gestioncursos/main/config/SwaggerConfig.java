package com.gestioncursos.main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info()
        .title("Gestión de Cursos API")
        .version("1.0")
        .description("API para la gestión de cursos")
        );
    }
}
