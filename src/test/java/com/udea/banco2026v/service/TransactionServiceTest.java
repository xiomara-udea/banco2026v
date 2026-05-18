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

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldTransferMoneySuccessfully() {

        Customer sender = new Customer();
        sender.setAccountNumber("111");
        sender.setBalance(1000.0);

        Customer receiver = new Customer();
        receiver.setAccountNumber("222");
        receiver.setBalance(500.0);

        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setSenderAccountNumber("111");
        transaction.setReceiverAccountNumber("222");
        transaction.setAmount(200.0);
        transaction.setTimestamp(LocalDateTime.now());

        TransactionDTO dto = new TransactionDTO();
        dto.setSenderAccountNumber("111");
        dto.setReceiverAccountNumber("222");
        dto.setAmount(200.0);
        dto.setTimestamp(LocalDateTime.now());

        when(customerRepository.findByAccountNumber("111"))
                .thenReturn(Optional.of(sender));

        when(customerRepository.findByAccountNumber("222"))
                .thenReturn(Optional.of(receiver));

        when(transactionRepository.save(any(Transaction.class)))
                .thenReturn(transaction);

        TransactionDTO result = transactionService.transferMoney(dto);

        assertNotNull(result);
        assertEquals(800.0, sender.getBalance());
        assertEquals(700.0, receiver.getBalance());

        verify(customerRepository, times(1)).save(sender);
        verify(customerRepository, times(1)).save(receiver);
    }

    @Test
    void shouldThrowExceptionWhenBalanceIsInsufficient() {

        Customer sender = new Customer();
        sender.setAccountNumber("111");
        sender.setBalance(100.0);

        Customer receiver = new Customer();
        receiver.setAccountNumber("222");
        receiver.setBalance(500.0);

        TransactionDTO dto = new TransactionDTO();
        dto.setSenderAccountNumber("111");
        dto.setReceiverAccountNumber("222");
        dto.setAmount(1000.0);

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
}
