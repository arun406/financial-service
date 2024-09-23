package com.nemetschek.accounting.transactions.service;

import com.nemetschek.accounting.transactions.model.dto.TransactionDTO;
import com.nemetschek.accounting.transactions.model.type.TransactionType;

import java.time.LocalDate;
import java.util.List;

public interface TransactionService {

    /**
     * Creates the transaction
     *
     * @param transactionDTO
     * @return
     */
    TransactionDTO createTransaction(TransactionDTO transactionDTO);

    /**
     * list the transactions
     *
     * @param from
     * @param to
     * @param accountNumber
     * @param type
     * @return
     */
    List<TransactionDTO> listTransactions(LocalDate from, LocalDate to, String accountNumber, TransactionType type);

}
