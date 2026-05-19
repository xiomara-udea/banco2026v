package com.udea.banco2026v.config;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void testHandleIllegalArgumentException() {

        IllegalArgumentException ex =
                new IllegalArgumentException("Balance cannot be null");

        ResponseEntity<String> response = handler.handle(ex);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Balance cannot be null", response.getBody());
    }
}