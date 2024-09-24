package com.nemetschek.accounting.transactions.model.repository;

import com.nemetschek.accounting.transactions.model.entity.Transaction;
import com.nemetschek.accounting.transactions.model.type.TransactionType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class TransactionSpecifications {

    public static Specification<Transaction> hasAccountNumber(String accountNumber) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("accountNumber"), accountNumber));
    }

    public static Specification<Transaction> hasTransactionType(TransactionType transactionType) {
        return (((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("transactionType"), transactionType)));
    }

    public static Specification<Transaction> hasDateRange(LocalDate from, LocalDate to) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("transactionDate"), from, to));
    }
}
