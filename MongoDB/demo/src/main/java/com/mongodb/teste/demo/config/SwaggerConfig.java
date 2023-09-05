package com.mongodb.teste.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI openAPI() {
    return new OpenAPI()
            .info(new Info()
                    .title("BD 2 - MongoDB")
                    .description("CRUD completo usando MongoDB")
                    .version("1.0")
                    .termsOfService("Termo de uso: Open Source")
                    .license(new License()
                            .name("Apache 2.0")
                            .url("https://github.com/RicardoJun10r/BDmongoDB")
                    )
            ).externalDocs(
                    new ExternalDocumentation()
                    .description("Ricardo Junior, Vitor Duarte e João Felipe")
                    .url("https://github.com/RicardoJun10r/BDmongoDB"));
    }
    
}
