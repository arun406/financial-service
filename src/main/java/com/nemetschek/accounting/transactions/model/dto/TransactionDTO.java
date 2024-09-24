package com.nemetschek.accounting.transactions.model.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.nemetschek.accounting.transactions.model.type.TransactionStatus;
import com.nemetschek.accounting.transactions.model.type.TransactionType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionDTO(
        String accountNumber,
        String transactionReference,
        @Valid
        TransactionType transactionType,

        @NotNull(message = "Amount is mandatory.")
        @DecimalMin(value = "0", inclusive = false, message = "Amount ")
        BigDecimal amount,

        TransactionStatus transactionStatus,

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        @JsonFormat(pattern = "MM/dd/yyyy")
        LocalDateTime transactionDate,
        String remarks) {

    public TransactionDTO withAccountNumber(String accountNumber) {
        return new TransactionDTO(accountNumber, transactionReference(), transactionType(), amount(),
                transactionStatus, transactionDate(), remarks());
    }
}
