package com.nemetschek.accounting.accounts.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nemetschek.accounting.accounts.model.type.AccountStatus;
import com.nemetschek.accounting.accounts.model.type.AccountType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Represents Account Information
 *
 * @param accountNumber
 * @param accountHolderName
 * @param accountType
 * @param accountStatus
 * @param availableBalance
 */
public record AccountDTO(
        String accountNumber,
        @NotBlank(message = "Account holder name is mandatory.")
        String accountHolderName,

        @Valid
        AccountType accountType,

        @Valid
        AccountStatus accountStatus,

        @Min(value = 0, message = "Balance should not be negative.")
        BigDecimal availableBalance,

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        @JsonFormat(pattern = "MM/dd/yyyy")
        LocalDate accountOpenDate,
        LocalDate accountClosedDate,
        LocalDate accountInactiveDate) {
}
