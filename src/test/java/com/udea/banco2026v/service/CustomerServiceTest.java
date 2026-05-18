package com.udea.banco2026v.service;

import com.udea.banco2026v.dto.CustomerDTO;
import com.udea.banco2026v.entity.Customer;
import com.udea.banco2026v.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCustomers() {

        Customer customer = new Customer(
                1L,
                "123",
                "Ana",
                "Lopez",
                5000.0
        );

        when(customerRepository.findAll()).thenReturn(List.of(customer));

        List<CustomerDTO> result = customerService.getAllCustomers();

        assertEquals(1, result.size());
        assertEquals("Ana", result.get(0).getFirstName());
    }

    @Test
    void testGetCustomerById() {

        Customer customer = new Customer(
                1L,
                "123",
                "Ana",
                "Lopez",
                5000.0
        );

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        CustomerDTO result = customerService.getCustomerById(1L);

        assertEquals("Ana", result.getFirstName());
    }

    @Test
    void testCreateCustomer() {

        CustomerDTO dto = new CustomerDTO();
        dto.setFirstName("Ana");
        dto.setLastName("Lopez");
        dto.setAccountNumber("123");
        dto.setBalance(5000.0);

        Customer saved = new Customer(
                1L,
                "123",
                "Ana",
                "Lopez",
                5000.0
        );

        when(customerRepository.save(any(Customer.class))).thenReturn(saved);

        CustomerDTO result = customerService.createCustomer(dto);

        assertEquals("Ana", result.getFirstName());
    }

    @Test
    void testUpdateCustomer() {

        Customer customer = new Customer(
                1L,
                "123",
                "Ana",
                "Lopez",
                5000.0
        );

        CustomerDTO dto = new CustomerDTO();
        dto.setFirstName("Maria");
        dto.setLastName("Perez");
        dto.setAccountNumber("999");
        dto.setBalance(7000.0);

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        CustomerDTO result = customerService.updateCustomer(1L, dto);

        assertEquals("Maria", result.getFirstName());
    }

    @Test
    void testDeleteCustomer() {

        when(customerRepository.existsById(1L)).thenReturn(true);

        customerService.deleteCustomer(1L);

        verify(customerRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteCustomerWhenNotExists() {

        when(customerRepository.existsById(1L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            customerService.deleteCustomer(1L);
        });

        assertEquals("Cliente no existe", exception.getMessage());
    }
}