package com.udea.banco2026v.mapper;

import com.udea.banco2026v.dto.CustomerDTO;
import com.udea.banco2026v.entity.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {

    @Test
    void testEntityToDTO_Mapping() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Juan");
        customer.setLastName("Perez");
        customer.setAccountNumber("123");
        customer.setBalance(5000.0);

        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setAccountNumber(customer.getAccountNumber());
        dto.setBalance(customer.getBalance());

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("Juan", dto.getFirstName());
        assertEquals("Perez", dto.getLastName());
        assertEquals("123", dto.getAccountNumber());
        assertEquals(5000.0, dto.getBalance());
    }

    @Test
    void testDTOToEntity_Mapping() {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(2L);
        dto.setFirstName("Ana");
        dto.setLastName("Lopez");
        dto.setAccountNumber("999");
        dto.setBalance(1000.0);

        Customer customer = new Customer();
        customer.setId(dto.getId());
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setAccountNumber(dto.getAccountNumber());
        customer.setBalance(dto.getBalance());

        assertNotNull(customer);
        assertEquals(2L, customer.getId());
        assertEquals("Ana", customer.getFirstName());
        assertEquals("Lopez", customer.getLastName());
        assertEquals("999", customer.getAccountNumber());
        assertEquals(1000.0, customer.getBalance());
    }
}