package com.nemetschek.accounting.transactions.model.repository;

import com.nemetschek.accounting.transactions.model.entity.Transaction;
import com.nemetschek.accounting.transactions.model.type.TransactionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Page<Transaction> findByAccountNumber(String accountNumber, Pageable pageable);

    /**
     * @param accountNumber
     * @param transactionDate
     * @param transactionType
     * @return
     */
    @Query("SELECT SUM(tx.amount) FROM Transaction AS tx " +
            "GROUP BY tx.accountNumber, tx.transactionDate " +
            "HAVING  accountNumber = :accountNumber " +
            "and tx.transactionType = :transactionType and tx.transactionDate = :transactionDate "
    )
    BigDecimal findAccountDailyWithdrawalAmount(String accountNumber, LocalDate transactionDate,
                                                TransactionType transactionType);
}
