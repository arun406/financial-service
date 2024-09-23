package com.nemetschek.accounting.transactions.service.impl;

import com.nemetschek.accounting.accounts.exception.AccountNotFoundException;
import com.nemetschek.accounting.accounts.model.entity.Account;
import com.nemetschek.accounting.accounts.model.repository.AccountRepository;
import com.nemetschek.accounting.transactions.model.dto.TransactionDTO;
import com.nemetschek.accounting.transactions.model.repository.TransactionRepository;
import com.nemetschek.accounting.transactions.service.AccountTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.StampedLock;

/**
 * Perform Deposit
 *
 * @author arun kumar kandakatla
 */
@Service
public class AccountDepositService implements AccountTransactionService {

    private final StampedLock sl = new StampedLock();

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public boolean performTransaction(TransactionDTO dto) {
        long l = sl.writeLock();
        try {
            String accountNumber = dto.accountNumber();
            Account account = this.accountRepository.findByAccountNumber(accountNumber);
            if (account == null) {
                throw new AccountNotFoundException("Invalid account.");
            }

            account.setAvailableBalance(account.getBalance().add(dto.amount()));

            this.accountRepository.save(account);
        } finally {
            sl.unlockWrite(l);
        }
        return true;
    }
}
