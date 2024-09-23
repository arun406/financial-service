package com.nemetschek.accounting.transactions.service;

import com.nemetschek.accounting.transactions.model.dto.TransactionDTO;

public interface AccountTransactionService {
    boolean performTransaction(TransactionDTO dto);
}
