package com.example.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class SupabaseService {

    private final WebClient webClient;
    
    @Value("${supabase.url}")
    private String supabaseUrl;
    
    @Value("${supabase.anon-key}")
    private String supabaseAnonKey;

    public SupabaseService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public Mono<String> getDados(String tabela) {
        return this.webClient.get()
                .uri(supabaseUrl + "/rest/v1/" + tabela)
                .header("apikey", supabaseAnonKey)
                .header("Authorization", "Bearer " + supabaseAnonKey)
                .header("Content-Type", "application/json")
                .retrieve()
                .bodyToMono(String.class);
    }
}
