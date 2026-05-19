package com.udea.banco2026v.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udea.banco2026v.dto.TransactionDTO;
import com.udea.banco2026v.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testTransferMoney() throws Exception {

        TransactionDTO dto = new TransactionDTO(
                1L,
                "123",
                "456",
                1000.0,
                LocalDateTime.now()
        );

        when(transactionService.transferMoney(any(TransactionDTO.class)))
                .thenReturn(dto);

        mockMvc.perform(post("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.senderAccountNumber").value("123"));
    }

    @Test
    void testGetTransactionsForAccount() throws Exception {

        TransactionDTO dto = new TransactionDTO(
                1L,
                "123",
                "456",
                1000.0,
                LocalDateTime.now()
        );

        when(transactionService.getTransactionsForAccount("123"))
                .thenReturn(List.of(dto));

        mockMvc.perform(get("/api/transactions/123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].senderAccountNumber").value("123"));
    }
}