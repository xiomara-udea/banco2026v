package com.udea.banco2026v.service;

import com.udea.banco2026v.dto.CustomerDTO;
import com.udea.banco2026v.entity.Customer;
import com.udea.banco2026v.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateCustomer() {

        CustomerDTO dto = new CustomerDTO();
        dto.setFirstName("Xiomara");
        dto.setLastName("Perez");
        dto.setAccountNumber("12345");
        dto.setBalance(1000.0);

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setAccountNumber(dto.getAccountNumber());
        customer.setBalance(dto.getBalance());

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        CustomerDTO result = customerService.createCustomer(dto);

        assertNotNull(result);
        assertEquals("Xiomara", result.getFirstName());
        assertEquals(1000.0, result.getBalance());

        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void shouldGetCustomerById() {

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Ana");
        customer.setLastName("Lopez");
        customer.setAccountNumber("999");
        customer.setBalance(5000.0);

        when(customerRepository.findById(1L))
                .thenReturn(Optional.of(customer));

        CustomerDTO result = customerService.getCustomerById(1L);

        assertEquals("Ana", result.getFirstName());
        assertEquals(5000.0, result.getBalance());
    }

    @Test
    void shouldDeleteCustomer() {

        when(customerRepository.existsById(1L)).thenReturn(true);

        customerService.deleteCustomer(1L);

        verify(customerRepository, times(1)).deleteById(1L);
    }

    @Test
    void shouldThrowExceptionWhenCustomerDoesNotExist() {

        when(customerRepository.existsById(1L)).thenReturn(false);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> customerService.deleteCustomer(1L)
        );

        assertEquals("Cliente no existe", exception.getMessage());
    }
}
