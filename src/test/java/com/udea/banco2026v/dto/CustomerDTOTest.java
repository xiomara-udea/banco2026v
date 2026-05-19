package com.udea.banco2026v.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDTOTest {

    @Test
    void testCustomerDTO() {

        CustomerDTO dto = new CustomerDTO();

        dto.setId(1L);
        dto.setFirstName("Juan");
        dto.setLastName("Perez");
        dto.setAccountNumber("123");
        dto.setBalance(5000.0);

        assertEquals(1L, dto.getId());
        assertEquals("Juan", dto.getFirstName());
        assertEquals("Perez", dto.getLastName());
        assertEquals("123", dto.getAccountNumber());
        assertEquals(5000.0, dto.getBalance());
    }
}