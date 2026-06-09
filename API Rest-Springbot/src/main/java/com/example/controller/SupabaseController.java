package com.example.controller;

import com.example.service.SupabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/*
 * SupabaseController
 *
 * Exposes a tiny REST API that forwards read-only requests to Supabase.
 * Endpoints:
 *  - GET /api/health         -> basic liveness check
 *  - GET /api/dados?tabela=  -> fetches all rows from the given table
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class SupabaseController {

    @Autowired
    private SupabaseService supabaseService;

    /*
     * GET /api/dados
     *
     * Returns the raw JSON response from Supabase for the requested table.
     * Basic validation is applied to the table name to reduce risk of
     * accidental malformed requests.
     */
    @GetMapping(value = "/dados", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<String> getDados(@RequestParam String tabela) {
        if (tabela == null || tabela.trim().isEmpty()) {
            return Mono.error(new IllegalArgumentException("Parâmetro 'tabela' é obrigatório"));
        }

        // Allow only letters, numbers and underscore to avoid accidental injection
        if (!tabela.matches("[A-Za-z0-9_]+")) {
            return Mono.error(new IllegalArgumentException("Tabela inválida: caracteres não permitidos"));
        }

        return supabaseService.getDados(tabela.trim());
    }

    /*
     * Simple health endpoint used by load balancers or manual checks.
     */
    @GetMapping(value = "/health", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<String> health() {
        return Mono.just("{\"status\":\"OK\"}");
    }
}
