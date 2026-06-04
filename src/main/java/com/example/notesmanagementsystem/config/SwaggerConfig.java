package com.example.notesmanagementsystem.config;

import ch.qos.logback.classic.spi.ConfiguratorRank;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Notes Management System API 📝")
                        .version("1.0.0")
                        .description("REST API for managing personal notes, including creation, retrieval, updating, deleting, and searching capabilities.")
                        .contact(new Contact()
                                .name("Your Name")
                                .email("your.email@example.com")));
    }
}
