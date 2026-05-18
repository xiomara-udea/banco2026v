package com.udea.banco2026v.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void testConstructorAndGetters() {

        Customer customer = new Customer(
                1L,
                "12345",
                "Ana",
                "Lopez",
                5000.0
        );

        assertEquals(1L, customer.getId());
        assertEquals("12345", customer.getAccountNumber());
        assertEquals("Ana", customer.getFirstName());
        assertEquals("Lopez", customer.getLastName());
        assertEquals(5000.0, customer.getBalance());
    }

    @Test
    void testSetters() {

        Customer customer = new Customer();

        customer.setId(2L);
        customer.setAccountNumber("999");
        customer.setFirstName("Juan");
        customer.setLastName("Perez");
        customer.setBalance(1000.0);

        assertEquals(2L, customer.getId());
        assertEquals("999", customer.getAccountNumber());
    }
}