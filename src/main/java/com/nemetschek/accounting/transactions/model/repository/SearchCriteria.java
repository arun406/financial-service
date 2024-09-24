package com.nemetschek.accounting.transactions.model.repository;

import com.nemetschek.accounting.transactions.model.type.TransactionType;

import java.time.LocalDate;

public record SearchCriteria(LocalDate from, LocalDate to, String accountNumber, TransactionType type) {
}
