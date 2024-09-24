package com.nemetschek.accounting.transactions.service.impl;

import com.nemetschek.accounting.accounts.exception.AccountNotFoundException;
import com.nemetschek.accounting.accounts.model.entity.Account;
import com.nemetschek.accounting.accounts.model.repository.AccountRepository;
import com.nemetschek.accounting.transactions.exception.InvalidTransactionException;
import com.nemetschek.accounting.transactions.model.dto.TransactionDTO;
import com.nemetschek.accounting.transactions.model.repository.TransactionRepository;
import com.nemetschek.accounting.transactions.model.type.TransactionType;
import com.nemetschek.accounting.transactions.service.AccountTransactionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.concurrent.locks.StampedLock;

/**
 * Withdrawal service
 *
 * @author arun kumar kandakatla
 */
@Service
public class AccountWithdrawalService implements AccountTransactionService {
    private final StampedLock sl = new StampedLock();

    @Value("${account.daily.withdrawal.limit:10000}")
    private BigDecimal withdrawalLimit;

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public AccountWithdrawalService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    @Override
    public boolean performTransaction(TransactionDTO dto) {
        long l = sl.writeLock();
        try {
            String accountNumber = dto.accountNumber();
            Account account = this.accountRepository.findByAccountNumber(accountNumber);
            if (account == null) {
                throw new AccountNotFoundException("Invalid account.");
            }

            // validate negative balance
            BigDecimal balance = account.getBalance().subtract(dto.amount());
            if (balance.compareTo(BigDecimal.ZERO) < 0) {
                throw new InvalidTransactionException("Insufficient balance.");
            }
            // validate daily transaction limit
            BigDecimal accountDailyWithdrawalAmount = this.transactionRepository
                    .findAccountDailyWithdrawalAmount(accountNumber, LocalDate.now(), TransactionType.O);
            if (accountDailyWithdrawalAmount == null) {
                accountDailyWithdrawalAmount = BigDecimal.ZERO;
            }
            if (dto.amount().add(accountDailyWithdrawalAmount).compareTo(withdrawalLimit) > 0) {
                throw new InvalidTransactionException("Exceeding daily withdrawal limit.");
            }
            account.setAvailableBalance(balance);

            this.accountRepository.save(account);

        } finally {
            sl.unlockWrite(l);
        }
        return true;
    }
}
