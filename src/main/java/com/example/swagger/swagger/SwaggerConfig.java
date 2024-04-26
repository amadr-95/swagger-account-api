package com.example.swagger.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Value("${openapi.dev-url}")
    private String devUrl;

    @Bean
    public OpenAPI getOpenApi(){
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.description("Server URL in Development environment");

        Contact contact = new Contact();
        contact.setEmail("amasabcar@outlook.com");
        contact.setName("Amador");

        License mitLicense = new License().name("MIT License");

        Info info = new Info()
                .title("Account Management")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage user accounts")
                .license(mitLicense);
        return new OpenAPI().info(info).servers(List.of(devServer));
    }

}
