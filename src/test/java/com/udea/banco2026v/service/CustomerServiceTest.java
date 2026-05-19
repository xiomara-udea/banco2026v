package com.udea.banco2026v.service;

import com.udea.banco2026v.dto.CustomerDTO;
import com.udea.banco2026v.entity.Customer;
import com.udea.banco2026v.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void testGetCustomerById_Success() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Juan");

        when(customerRepository.findById(1L))
                .thenReturn(Optional.of(customer));

        CustomerDTO result = customerService.getCustomerById(1L);

        assertEquals("Juan", result.getFirstName());
    }

    // 🔥 NUEVO: SUBE COBERTURA
    @Test
    void testGetCustomerById_NotFound() {
        when(customerRepository.findById(1L))
                .thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> customerService.getCustomerById(1L));

        assertEquals("Cliente no encontrado", ex.getMessage());
    }

    @Test
    void testDeleteCustomer_Success() {

        when(customerRepository.existsById(1L)).thenReturn(true);
        doNothing().when(customerRepository).deleteById(1L);

        assertDoesNotThrow(() -> customerService.deleteCustomer(1L));

        verify(customerRepository).existsById(1L);
        verify(customerRepository).deleteById(1L);
    }

    // 🔥 NUEVO
    @Test
    void testDeleteCustomer_NotFound() {
        when(customerRepository.existsById(1L)).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> customerService.deleteCustomer(1L));

        assertEquals("Cliente no existe", ex.getMessage());
    }

    @Test
    void testCustomerDTO_AllFields() {
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
    void testCustomerDTO_GettersSetters() {
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
    void testUpdateCustomer_Success() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Juan");

        CustomerDTO dto = new CustomerDTO();
        dto.setFirstName("Carlos");

        when(customerRepository.findById(1L))
                .thenReturn(Optional.of(customer));

        when(customerRepository.save(any(Customer.class)))
                .thenAnswer(i -> i.getArgument(0));

        CustomerDTO result = customerService.updateCustomer(1L, dto);

        assertEquals("Carlos", result.getFirstName());
    }

    // 🔥 NUEVO
    @Test
    void testUpdateCustomer_NotFound() {
        when(customerRepository.findById(1L))
                .thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> customerService.updateCustomer(1L, new CustomerDTO()));

        assertEquals("Cliente no encontrado", ex.getMessage());
    }

    @Test
    void testCreateCustomer() {
        CustomerDTO dto = new CustomerDTO();
        dto.setFirstName("Ana");
        dto.setLastName("Lopez");
        dto.setAccountNumber("123");
        dto.setBalance(1000.0);

        Customer saved = new Customer();
        saved.setId(1L);
        saved.setFirstName("Ana");
        saved.setLastName("Lopez");
        saved.setAccountNumber("123");
        saved.setBalance(1000.0);

        when(customerRepository.save(any(Customer.class)))
                .thenReturn(saved);

        CustomerDTO result = customerService.createCustomer(dto);

        assertEquals("Ana", result.getFirstName());
    }

    @Test
    void testCustomerDTO_NoArgsConstructor() {
        CustomerDTO dto = new CustomerDTO();
        assertNotNull(dto);
    }
}