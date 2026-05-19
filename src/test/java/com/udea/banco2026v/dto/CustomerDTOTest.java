package com.udea.banco2026v.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Test
    void testCustomerDTOSerialization() throws Exception {

        CustomerDTO dto = new CustomerDTO();

        dto.setId(1L);
        dto.setFirstName("Juan");

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(dto);

        assertTrue(json.contains("Juan"));
    }
}