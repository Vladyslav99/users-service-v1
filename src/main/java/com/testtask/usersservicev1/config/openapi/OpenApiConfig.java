package com.testtask.usersservicev1.config.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Spring Boot REST API")
                        .version("1.0.0")
                        .description("This is RESTful API application for aggregating users from all db sources")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
