package com.example.controller;

import com.example.service.SupabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class SupabaseController {

    @Autowired
    private SupabaseService supabaseService;

    @GetMapping("/dados")
    public Mono<String> getDados(@RequestParam String tabela) {
        return supabaseService.getDados(tabela);
    }

    @GetMapping("/health")
    public Mono<String> health() {
        return Mono.just("{\"status\":\"OK\"}");
    }
}
