package com.nemetschek.accounting.transactions.model.repository;

import com.nemetschek.accounting.transactions.model.entity.Transaction;
import com.nemetschek.accounting.transactions.model.type.TransactionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long>, JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {

    Page<Transaction> findByAccountNumber(String accountNumber, Pageable pageable);

    /**
     * @param accountNumber
     * @param transactionDate
     * @param transactionType
     * @return
     */
    @Query("SELECT SUM(t.amount) FROM Transaction t " +
            "GROUP BY t.accountNumber, t.transactionDate " +
            "HAVING  t.accountNumber = :accountNumber " +
            "and t.transactionType = :transactionType and CAST(t.transactionDate AS DATE) = :transactionDate "
    )
    BigDecimal findAccountDailyWithdrawalAmount(@Param("accountNumber") String accountNumber,
                                                @Param("transactionDate") LocalDate transactionDate,
                                                @Param("transactionType") TransactionType transactionType);
}
