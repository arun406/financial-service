package com.nemetschek.accounting.transactions.model.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.nemetschek.accounting.transactions.model.type.TransactionStatus;
import com.nemetschek.accounting.transactions.model.type.TransactionType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionDTO(
        @NotBlank(message = "Account number is mandatory")
        String accountNumber,
        String transactionReference,
        @NotBlank(message = "Transaction type is mandatory")
        @Valid
        TransactionType transactionType,

        @NotNull(message = "Amount is mandatory.")
        @DecimalMin(value = "0", inclusive = false, message = "Amount ")
        BigDecimal amount,

        TransactionStatus transactionStatus,

        @NotBlank(message = "Transaction Date is mandatory")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        @JsonFormat(pattern = "MM/dd/yyyy")
        LocalDateTime transactionDate,

        String remarks) {
}
