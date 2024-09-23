package com.nemetschek.accounting.transactions.api;

import com.nemetschek.accounting.transactions.model.dto.TransactionDTO;
import com.nemetschek.accounting.transactions.service.TransactionService;
import com.nemetschek.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Transaction API
 *
 * @author arun kumar kandakatla
 */
@RequestMapping("/api/v1/accounts/{accountNumber}/transactions")
@RestController
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TransactionDTO>> create(@Valid @RequestBody TransactionDTO transactionDTO) {

        return null;
    }
}
