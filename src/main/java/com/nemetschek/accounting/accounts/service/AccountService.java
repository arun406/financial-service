package com.nemetschek.accounting.accounts.service;

import com.nemetschek.accounting.accounts.model.dto.AccountDTO;

public interface AccountService {

    AccountDTO createAccount(AccountDTO accountDTO);

    AccountDTO getAccount(String accountNumber);

    void deleteAccount(String accountNumber);

    AccountDTO updateAccount(String accountNumber, AccountDTO accountDTO);

}
