package com.nemetschek.accounting.transactions.service.impl;

import com.nemetschek.accounting.transactions.model.type.TransactionType;
import com.nemetschek.accounting.transactions.service.AccountTransactionService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AccountTransactionServiceFactory {

    private static final Map<TransactionType, AccountTransactionService> serviceMap = new HashMap<>();

    private final AccountWithdrawalService accountWithdrawalService;
    private final AccountDepositService accountDepositService;

    public AccountTransactionServiceFactory(AccountWithdrawalService accountWithdrawalService,
                                            AccountDepositService accountDepositService) {
        this.accountWithdrawalService = accountWithdrawalService;
        this.accountDepositService = accountDepositService;
        init();
    }

    /**
     * Initialize the map by hard coding. This implementation can be changed using annotation scanning.
     */
    private void init() {
        serviceMap.put(TransactionType.I, accountDepositService);
        serviceMap.put(TransactionType.O, accountWithdrawalService);
    }

    /**
     * @param transactionType
     * @return
     */
    public static AccountTransactionService getService(TransactionType transactionType) {
        return serviceMap.get(transactionType);
    }
}
