package com.udea.banco2026v.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udea.banco2026v.dto.CustomerDTO;
import com.udea.banco2026v.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
@AutoConfigureMockMvc(addFilters = false) // evita problemas de security en CI
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllCustomers() throws Exception {

        CustomerDTO customer = new CustomerDTO();
        customer.setId(1L);
        customer.setFirstName("Juan");
        customer.setLastName("Perez");
        customer.setAccountNumber("123");
        customer.setBalance(5000.0);

        when(customerService.getAllCustomers())
                .thenReturn(Arrays.asList(customer));

        mockMvc.perform(get("/api/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Juan"));
    }

    @Test
    void testGetCustomerById() throws Exception {

        CustomerDTO customer = new CustomerDTO();
        customer.setId(1L);
        customer.setFirstName("Juan");
        customer.setLastName("Perez");
        customer.setAccountNumber("123");
        customer.setBalance(5000.0);

        when(customerService.getCustomerById(1L))
                .thenReturn(customer);

        mockMvc.perform(get("/api/customers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Juan"));
    }

    @Test
    void testCreateCustomer() throws Exception {

        CustomerDTO request = new CustomerDTO();
        request.setFirstName("Juan");
        request.setLastName("Perez");
        request.setAccountNumber("123");
        request.setBalance(5000.0);

        CustomerDTO response = new CustomerDTO();
        response.setId(1L);
        response.setFirstName("Juan");
        response.setLastName("Perez");
        response.setAccountNumber("123");
        response.setBalance(5000.0);

        when(customerService.createCustomer(any(CustomerDTO.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Juan"));
    }

    @Test
    void testUpdateCustomer() throws Exception {

        CustomerDTO request = new CustomerDTO();
        request.setFirstName("Carlos");
        request.setLastName("Lopez");
        request.setAccountNumber("456");
        request.setBalance(8000.0);

        CustomerDTO response = new CustomerDTO();
        response.setId(1L);
        response.setFirstName("Carlos");
        response.setLastName("Lopez");
        response.setAccountNumber("456");
        response.setBalance(8000.0);

        when(customerService.updateCustomer(anyLong(), any(CustomerDTO.class)))
                .thenReturn(response);

        mockMvc.perform(put("/api/customers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Carlos"));
    }

    @Test
    void testDeleteCustomer() throws Exception {

        doNothing().when(customerService).deleteCustomer(1L);

        mockMvc.perform(delete("/api/customers/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testCreateCustomer_BalanceNull_ShouldFail() throws Exception {

        CustomerDTO request = new CustomerDTO();
        request.setFirstName("Juan");
        request.setLastName("Perez");
        request.setAccountNumber("123");
        request.setBalance(null);

        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}