package com.udea.banco2026v.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void testCustomerGettersAndSetters() {
        Customer customer = new Customer();

        customer.setId(1L);
        customer.setFirstName("Juan");
        customer.setLastName("Perez");
        customer.setAccountNumber("123");
        customer.setBalance(5000.0);

        assertEquals(1L, customer.getId());
        assertEquals("Juan", customer.getFirstName());
        assertEquals("Perez", customer.getLastName());
        assertEquals("123", customer.getAccountNumber());
        assertEquals(5000.0, customer.getBalance());
    }

    @Test
    void testCustomerConstructor() {
        Customer customer = new Customer(
                1L,
                "123",
                "Juan",
                "Perez",
                5000.0
        );

        assertEquals(1L, customer.getId());
        assertEquals("Juan", customer.getFirstName());
        assertEquals("Perez", customer.getLastName());
        assertEquals("123", customer.getAccountNumber());
        assertEquals(5000.0, customer.getBalance());
    }
}