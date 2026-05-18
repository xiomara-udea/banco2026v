package com.udea.banco2026v.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udea.banco2026v.dto.CustomerDTO;
import com.udea.banco2026v.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllCustomers() throws Exception {

        CustomerDTO dto = new CustomerDTO();
        dto.setId(1L);
        dto.setFirstName("Ana");

        Mockito.when(customerService.getAllCustomers())
                .thenReturn(List.of(dto));

        mockMvc.perform(get("/api/customers"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetCustomerById() throws Exception {

        CustomerDTO dto = new CustomerDTO();
        dto.setId(1L);

        Mockito.when(customerService.getCustomerById(1L))
                .thenReturn(dto);

        mockMvc.perform(get("/api/customers/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateCustomer() throws Exception {

        CustomerDTO dto = new CustomerDTO();
        dto.setFirstName("Ana");
        dto.setLastName("Lopez");
        dto.setAccountNumber("123");
        dto.setBalance(5000.0);

        Mockito.when(customerService.createCustomer(any(CustomerDTO.class)))
                .thenReturn(dto);

        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateCustomer() throws Exception {

        CustomerDTO dto = new CustomerDTO();
        dto.setFirstName("Maria");

        Mockito.when(customerService.updateCustomer(Mockito.eq(1L), any(CustomerDTO.class)))
                .thenReturn(dto);

        mockMvc.perform(put("/api/customers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteCustomer() throws Exception {

        doNothing().when(customerService).deleteCustomer(1L);

        mockMvc.perform(delete("/api/customers/1"))
                .andExpect(status().isNoContent());
    }
}