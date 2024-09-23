package com.nemetschek.accounting.accounts.service.impl;

import com.nemetschek.accounting.accounts.exception.AccountNotFoundException;
import com.nemetschek.accounting.accounts.model.dto.AccountDTO;
import com.nemetschek.accounting.accounts.model.entity.Account;
import com.nemetschek.accounting.accounts.model.mapper.AccountMapper;
import com.nemetschek.accounting.accounts.model.repository.AccountRepository;
import com.nemetschek.accounting.accounts.model.type.AccountStatus;
import com.nemetschek.accounting.accounts.model.type.UniqueIdType;
import com.nemetschek.accounting.accounts.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Account Service implementation class
 *
 * @author arun kumar kandakatla
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Creates the Account with the received information.
     *
     * @param accountDTO
     * @return
     */
    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        Account entity = accountMapper.toEntity(accountDTO);
        String accountNumber = UniqueIdGenerator.generateUniqueId(UniqueIdType.A);
        entity.setAccountNumber(accountNumber);
        entity.setAccountStatus(AccountStatus.A);
        entity.setAccountOpenDate(LocalDate.now());
        this.accountRepository.save(entity);
        AccountDTO dto = accountMapper.toDto(entity);
        return dto;
    }

    /**
     * Retrieve the account by account number
     *
     * @param accountNumber
     * @return
     */
    @Override
    public AccountDTO getAccount(String accountNumber) {
        Account entity = this.accountRepository.findByAccountNumber(accountNumber);
        if (entity == null) {
            throw new AccountNotFoundException("Account not found.");
        }
        AccountDTO dto = this.accountMapper.toDto(entity);
        return dto;
    }


    /**
     * Delete the Account by Account Number.
     *
     * @param accountNumber
     */
    @Override
    public void deleteAccount(String accountNumber) {
        Account entity = this.accountRepository.findByAccountNumber(accountNumber);
        if (entity == null) {
            throw new AccountNotFoundException("Account not found.");
        }
        entity.setAccountStatus(AccountStatus.C);
        entity.setAccountClosedDate(LocalDate.now());
        this.accountRepository.save(entity);
    }

    /**
     * Update the Account information of account number
     *
     * @param accountNumber
     * @param accountDTO
     * @return
     */
    @Override
    public AccountDTO updateAccount(String accountNumber, AccountDTO accountDTO) {
        Account entity = this.accountRepository.findByAccountNumber(accountNumber);
        if (entity == null) {
            throw new AccountNotFoundException("Account not found.");
        }
        entity.setAccountNumber(accountNumber);
        entity.setAccountType(accountDTO.accountType());
        entity.setAccountStatus(accountDTO.accountStatus());
        entity.setAccountClosedDate(null);
        entity.setAccountHolderName(accountDTO.accountHolderName());
//        entity.setAvailableBalance(accountDTO.availableBalance());

        this.accountRepository.save(entity);
        return this.accountMapper.toDto(entity);
    }
}
