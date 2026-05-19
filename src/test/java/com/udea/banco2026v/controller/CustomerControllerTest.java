package com.udea.banco2026v.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udea.banco2026v.dto.CustomerDTO;
import com.udea.banco2026v.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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

        when(customerService.getCustomerById(1L))
                .thenReturn(customer);

        mockMvc.perform(get("/api/customers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Juan"));
    }

    @Test
    void testCreateCustomer() throws Exception {

        CustomerDTO customer = new CustomerDTO();
        customer.setId(1L);
        customer.setFirstName("Juan");
        customer.setLastName("Perez");
        customer.setAccountNumber("123");
        customer.setBalance(5000.0); // ✅ FIX

        when(customerService.createCustomer(any(CustomerDTO.class)))
                .thenReturn(customer);

        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Juan"));
    }

    @Test
    void testUpdateCustomer() throws Exception {

        CustomerDTO customer = new CustomerDTO();
        customer.setFirstName("Carlos");

        when(customerService.updateCustomer(anyLong(), any(CustomerDTO.class)))
                .thenReturn(customer);

        mockMvc.perform(put("/api/customers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Carlos"));
    }

    @Test
    void testDeleteCustomer() throws Exception {

        doNothing().when(customerService).deleteCustomer(1L);

        mockMvc.perform(delete("/api/customers/1"))
                .andExpect(status().isNoContent());
    }
}