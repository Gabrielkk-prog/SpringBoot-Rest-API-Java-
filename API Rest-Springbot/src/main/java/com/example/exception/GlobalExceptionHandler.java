package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/*
 * GlobalExceptionHandler
 *
 * Centralized exception handling for the REST API. Converts thrown exceptions
 * into standardized JSON error responses with HTTP status codes.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /*
     * handleGenericException
     *
     * Catches all unhandled RuntimeExceptions and returns a 500 error
     * with a standardized JSON response.
     */
    @ExceptionHandler(RuntimeException.class)
    public Mono<ResponseEntity<Map<String, Object>>> handleGenericException(RuntimeException ex) {
        Map<String, Object> body = buildErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body));
    }

    /*
     * handleWebExchangeBindException
     *
     * Catches validation/binding errors (e.g., missing or invalid request parameters)
     * and returns a 400 error with details.
     */
    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<Map<String, Object>>> handleWebExchangeBindException(
            WebExchangeBindException ex) {
        Map<String, Object> body = buildErrorResponse(
                "Erro de validação: " + ex.getBindingResult().getFieldError(),
                HttpStatus.BAD_REQUEST);
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body));
    }

    /*
     * buildErrorResponse
     *
     * Helper method to construct a consistent error response JSON structure.
     */
    private Map<String, Object> buildErrorResponse(String message, HttpStatus status) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        return body;
    }
}
