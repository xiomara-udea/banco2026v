package com.udea.banco2026v.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void testConstructorAndGetters() {

        LocalDateTime now = LocalDateTime.now();

        Transaction transaction = new Transaction(
                1L,
                "111",
                "222",
                1500.0,
                now
        );

        assertEquals(1L, transaction.getId());
        assertEquals("111", transaction.getSenderAccountNumber());
        assertEquals("222", transaction.getReceiverAccountNumber());
        assertEquals(1500.0, transaction.getAmount());
        assertEquals(now, transaction.getTimestamp());
    }

    @Test
    void testSetters() {

        Transaction transaction = new Transaction();

        transaction.setId(2L);
        transaction.setSenderAccountNumber("333");
        transaction.setReceiverAccountNumber("444");
        transaction.setAmount(2500.0);

        assertEquals(2L, transaction.getId());
        assertEquals("333", transaction.getSenderAccountNumber());
    }
}