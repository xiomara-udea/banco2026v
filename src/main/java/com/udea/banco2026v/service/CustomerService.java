package com.udea.banco2026v.service;

import com.udea.banco2026v.dto.CustomerDTO;
import com.udea.banco2026v.entity.Customer;
import com.udea.banco2026v.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // ✅ Obtener todos los clientes
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ✅ Obtener cliente por ID
    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        return mapToDTO(customer);
    }

    // ✅ Crear cliente
    public CustomerDTO createCustomer(CustomerDTO dto) {
        Customer customer = new Customer();
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setBalance(dto.getBalance());
        customer.setAccountNumber(dto.getAccountNumber());

        Customer saved = customerRepository.save(customer);
        return mapToDTO(saved);
    }

    // ✅ Actualizar cliente
    public CustomerDTO updateCustomer(Long id, CustomerDTO dto) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setBalance(dto.getBalance());
        customer.setAccountNumber(dto.getAccountNumber());

        Customer updated = customerRepository.save(customer);
        return mapToDTO(updated);
    }

    // ✅ Borrar cliente
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Cliente no existe");
        }
        customerRepository.deleteById(id);
    }

    // 🔹 Mapeo a DTO
    private CustomerDTO mapToDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setBalance(customer.getBalance());
        dto.setAccountNumber(customer.getAccountNumber());
        return dto;
    }
}