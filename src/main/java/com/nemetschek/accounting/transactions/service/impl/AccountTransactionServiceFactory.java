package com.nemetschek.accounting.transactions.service.impl;

import com.nemetschek.accounting.transactions.model.type.TransactionType;
import com.nemetschek.accounting.transactions.service.AccountTransactionService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AccountTransactionServiceFactory {

    private static final Map<TransactionType, AccountTransactionService> serviceMap = new HashMap<>();

    /**
     * Initialize the map by hard coding. This implementation can be changed using annotation scanning.
     */
    static {
        serviceMap.put(TransactionType.I, new AccountDepositService());
        serviceMap.put(TransactionType.O, new AccountWithdrawalService());
    }

    /**
     * @param transactionType
     * @return
     */
    public static AccountTransactionService getService(TransactionType transactionType) {
        return serviceMap.get(transactionType);
    }
}
