package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class Application {

    /*
     * Application
     *
     * Main entry point for the Spring Boot application. Exposes a
     * `WebClient.Builder` bean so services can create WebClient instances
     * (used by `SupabaseService` to call the Supabase REST API).
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}
