package com.udea.banco2026v.service;

import com.udea.banco2026v.dto.TransactionDTO;
import com.udea.banco2026v.entity.Customer;
import com.udea.banco2026v.entity.Transaction;
import com.udea.banco2026v.repository.CustomerRepository;
import com.udea.banco2026v.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransferMoney() {

        Customer sender = new Customer(1L, "111", "Ana", "Lopez", 5000.0);
        Customer receiver = new Customer(2L, "222", "Juan", "Perez", 2000.0);

        TransactionDTO dto = new TransactionDTO();
        dto.setSenderAccountNumber("111");
        dto.setReceiverAccountNumber("222");
        dto.setAmount(1000.0);
        dto.setTimestamp(LocalDateTime.now());

        when(customerRepository.findByAccountNumber("111"))
                .thenReturn(Optional.of(sender));

        when(customerRepository.findByAccountNumber("222"))
                .thenReturn(Optional.of(receiver));

        when(transactionRepository.save(any(Transaction.class)))
                .thenAnswer(i -> i.getArgument(0));

        TransactionDTO result = transactionService.transferMoney(dto);

        assertEquals(1000.0, result.getAmount());
    }

    @Test
    void testTransferMoneyInsufficientBalance() {

        Customer sender = new Customer(1L, "111", "Ana", "Lopez", 100.0);
        Customer receiver = new Customer(2L, "222", "Juan", "Perez", 2000.0);

        TransactionDTO dto = new TransactionDTO();
        dto.setSenderAccountNumber("111");
        dto.setReceiverAccountNumber("222");
        dto.setAmount(1000.0);

        when(customerRepository.findByAccountNumber("111"))
                .thenReturn(Optional.of(sender));

        when(customerRepository.findByAccountNumber("222"))
                .thenReturn(Optional.of(receiver));

        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class,
                        () -> transactionService.transferMoney(dto));

        assertEquals("Saldo insuficiente en la cuenta del remitente.",
                exception.getMessage());
    }

    @Test
    void testGetTransactionsForAccount() {

        Transaction transaction = new Transaction(
                1L,
                "111",
                "222",
                1000.0,
                LocalDateTime.now()
        );

        when(transactionRepository
                .findBySenderAccountNumberOrReceiverAccountNumber("111", "111"))
                .thenReturn(List.of(transaction));

        List<TransactionDTO> result =
                transactionService.getTransactionsForAccount("111");

        assertEquals(1, result.size());
    }
}