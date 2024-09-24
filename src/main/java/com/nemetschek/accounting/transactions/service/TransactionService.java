package com.nemetschek.accounting.transactions.service;

import com.nemetschek.accounting.transactions.model.dto.TransactionDTO;
import com.nemetschek.accounting.transactions.model.dto.TransactionsList;
import com.nemetschek.accounting.transactions.model.type.TransactionType;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

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
     * @param pageable
     * @param from
     * @param to
     * @param accountNumber
     * @param type
     * @return
     */
    TransactionsList listTransactions(Pageable pageable, LocalDate from, LocalDate to, String accountNumber,
                                      TransactionType type);

}
