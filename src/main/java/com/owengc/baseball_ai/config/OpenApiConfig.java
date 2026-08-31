package com.owengc.baseball_ai.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI baseballApiInfo() {
        return new OpenAPI().info(new Info()
                .title("Baseball Analytics API")
                .version("v1")
                .description("REST API over the Lahman Baseball Database, covering players, teams, and season statistics from 1871 to 2025."));
    }
}