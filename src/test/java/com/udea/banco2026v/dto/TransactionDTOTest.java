package com.udea.banco2026v.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TransactionDTOTest {

    @Test
    void testGettersAndSetters() {

        TransactionDTO dto = new TransactionDTO();

        LocalDateTime now = LocalDateTime.now();

        dto.setId(1L);
        dto.setSenderAccountNumber("111");
        dto.setReceiverAccountNumber("222");
        dto.setAmount(1000.0);
        dto.setTimestamp(now);

        assertEquals(1L, dto.getId());
        assertEquals("111", dto.getSenderAccountNumber());
        assertEquals("222", dto.getReceiverAccountNumber());
        assertEquals(1000.0, dto.getAmount());
        assertEquals(now, dto.getTimestamp());
    }
}