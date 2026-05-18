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

    private Customer customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Xiomara");
        customer.setLastName("Perez");
        customer.setAccountNumber("123456");
        customer.setBalance(1000.0);
    }

    @Test
    void shouldGetAllCustomers() {

        when(customerRepository.findAll()).thenReturn(List.of(customer));

        List<CustomerDTO> customers = customerService.getAllCustomers();

        assertEquals(1, customers.size());
        assertEquals("Xiomara", customers.get(0).getFirstName());
    }

    @Test
    void shouldGetCustomerById() {

        when(customerRepository.findById(1L))
                .thenReturn(Optional.of(customer));

        CustomerDTO result = customerService.getCustomerById(1L);

        assertNotNull(result);
        assertEquals("123456", result.getAccountNumber());
    }

    @Test
    void shouldCreateCustomer() {

        CustomerDTO dto = new CustomerDTO();
        dto.setFirstName("Ana");
        dto.setLastName("Lopez");
        dto.setAccountNumber("999");
        dto.setBalance(2000.0);

        when(customerRepository.save(any(Customer.class)))
                .thenReturn(customer);

        CustomerDTO result = customerService.createCustomer(dto);

        assertNotNull(result);
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void shouldUpdateCustomer() {

        CustomerDTO dto = new CustomerDTO();
        dto.setFirstName("Maria");
        dto.setLastName("Gomez");
        dto.setAccountNumber("888");
        dto.setBalance(5000.0);

        when(customerRepository.findById(1L))
                .thenReturn(Optional.of(customer));

        when(customerRepository.save(any(Customer.class)))
                .thenReturn(customer);

        CustomerDTO updated = customerService.updateCustomer(1L, dto);

        assertNotNull(updated);

        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void shouldDeleteCustomer() {

        when(customerRepository.existsById(1L))
                .thenReturn(true);

        customerService.deleteCustomer(1L);

        verify(customerRepository, times(1)).deleteById(1L);
    }

    @Test
    void shouldThrowExceptionWhenCustomerNotFound() {

        when(customerRepository.findById(1L))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> customerService.getCustomerById(1L)
        );

        assertEquals("Cliente no encontrado", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistingCustomer() {

        when(customerRepository.existsById(1L))
                .thenReturn(false);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> customerService.deleteCustomer(1L)
        );

        assertEquals("Cliente no existe", exception.getMessage());
    }
}
