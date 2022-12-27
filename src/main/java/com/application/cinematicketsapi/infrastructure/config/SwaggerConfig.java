package com.application.cinematicketsapi.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI crmOpenApi() {
        return new OpenAPI().info(new Info().title("Cinema Tickets API")
                                            .description("A simple seat reservation REST API application for a " +
                                                    "cinema"));
    }


}
