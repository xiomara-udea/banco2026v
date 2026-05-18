package com.udea.banco2026v.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransferRequestDTOTest {

    @Test
    void testGettersAndSetters() {

        TransferRequestDTO dto = new TransferRequestDTO();

        dto.setSenderAccountNumber("111");
        dto.setReceiverAccountNumber("222");
        dto.setAmount(3000.0);

        assertEquals("111", dto.getSenderAccountNumber());
        assertEquals("222", dto.getReceiverAccountNumber());
        assertEquals(3000.0, dto.getAmount());
    }
}