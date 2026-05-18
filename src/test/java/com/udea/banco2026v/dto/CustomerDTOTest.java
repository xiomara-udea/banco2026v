package com.udea.banco2026v.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDTOTest {

    @Test
    void testGettersAndSetters() {

        CustomerDTO dto = new CustomerDTO();

        dto.setId(1L);
        dto.setFirstName("Ana");
        dto.setLastName("Lopez");
        dto.setAccountNumber("12345");
        dto.setBalance(5000.0);

        assertEquals(1L, dto.getId());
        assertEquals("Ana", dto.getFirstName());
        assertEquals("Lopez", dto.getLastName());
        assertEquals("12345", dto.getAccountNumber());
        assertEquals(5000.0, dto.getBalance());
    }
}