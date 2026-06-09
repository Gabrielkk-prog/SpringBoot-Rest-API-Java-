package com.example.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/*
 * SupabaseService
 *
 * Service responsible for communicating with the Supabase REST API.
 * It uses a Spring WebClient (reactive) to perform requests and returns
 * a reactive Mono<String> containing the JSON response or an error.
 */
@Service
public class SupabaseService {

    private final WebClient webClient;

    /*
     * Base URL for the Supabase project (e.g. https://xyz.supabase.co)
     * Configured in `application.properties` via `supabase.url`.
     */
    @Value("${supabase.url}")
    private String supabaseUrl;

    /*
     * Anonymous public key for Supabase. Keep this in environment variables
     * or a secure secrets manager for production usage.
     */
    @Value("${supabase.anon-key}")
    private String supabaseAnonKey;

    public SupabaseService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    /*
     * getDados
     *
     * Fetches all rows from the given Supabase table using the REST endpoint
     * /rest/v1/{table}. The method performs basic error handling: if Supabase
     * returns a non-2xx status the returned Mono will signal an error with a
     * descriptive message. Consumers (controller) may convert that into a
     * friendly JSON response.
     */
    public Mono<String> getDados(String tabela) {
        return this.webClient.get()
                .uri(supabaseUrl + "/rest/v1/" + tabela)
                .header("apikey", supabaseAnonKey)
                .header("Authorization", "Bearer " + supabaseAnonKey)
                .header("Content-Type", "application/json")
                .exchangeToMono(response -> {
                    if (response.statusCode().is2xxSuccessful()) {
                        return response.bodyToMono(String.class);
                    } else {
                        return response.bodyToMono(String.class)
                                .flatMap(body -> Mono.error(new RuntimeException("Supabase error: "
                                        + response.statusCode() + " - " + body)));
                    }
                });
    }
}
