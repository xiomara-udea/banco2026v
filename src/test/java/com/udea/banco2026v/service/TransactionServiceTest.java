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

    private Customer sender;
    private Customer receiver;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        sender = new Customer();
        sender.setId(1L);
        sender.setAccountNumber("111");
        sender.setBalance(1000.0);

        receiver = new Customer();
        receiver.setId(2L);
        receiver.setAccountNumber("222");
        receiver.setBalance(500.0);
    }

    @Test
    void shouldTransferMoneySuccessfully() {

        TransactionDTO dto = new TransactionDTO(
                null,
                "111",
                "222",
                200.0,
                LocalDateTime.now()
        );

        when(customerRepository.findByAccountNumber("111"))
                .thenReturn(Optional.of(sender));

        when(customerRepository.findByAccountNumber("222"))
                .thenReturn(Optional.of(receiver));

        when(transactionRepository.save(any(Transaction.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        TransactionDTO result = transactionService.transferMoney(dto);

        assertNotNull(result);

        assertEquals(800.0, sender.getBalance());
        assertEquals(700.0, receiver.getBalance());

        verify(customerRepository, times(2)).save(any(Customer.class));
    }

    @Test
    void shouldThrowExceptionWhenBalanceIsInsufficient() {

        TransactionDTO dto = new TransactionDTO(
                null,
                "111",
                "222",
                5000.0,
                LocalDateTime.now()
        );

        when(customerRepository.findByAccountNumber("111"))
                .thenReturn(Optional.of(sender));

        when(customerRepository.findByAccountNumber("222"))
                .thenReturn(Optional.of(receiver));

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> transactionService.transferMoney(dto)
        );

        assertEquals(
                "Saldo insuficiente en la cuenta del remitente.",
                exception.getMessage()
        );
    }

    @Test
    void shouldThrowExceptionWhenSenderDoesNotExist() {

        TransactionDTO dto = new TransactionDTO(
                null,
                "999",
                "222",
                100.0,
                LocalDateTime.now()
        );

        when(customerRepository.findByAccountNumber("999"))
                .thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> transactionService.transferMoney(dto)
        );

        assertEquals(
                "La cuenta del remitente no existe.",
                exception.getMessage()
        );
    }
}