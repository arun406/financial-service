package com.nemetschek.accounting.accounts.model.entity;

import com.nemetschek.accounting.accounts.model.type.AccountStatus;
import com.nemetschek.accounting.accounts.model.type.AccountType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.concurrent.locks.StampedLock;

@Data
@Entity
@Table(name = "ACCOUNT")
public class Account {

    private StampedLock lock = new StampedLock();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(name = "ACCOUNT_NUM")
    private String accountNumber;
    @Column(name = "NAME")
    private String accountHolderName;

    @Enumerated
    @Column(name = "TYPE")
    private AccountType accountType;
    @Setter
    @Enumerated
    @Column(name = "STATUS")
    private AccountStatus accountStatus;

    @Column(name = "AVAIL_BALANCE")
    private BigDecimal availableBalance;

    @Column(name = "OPEN_DATETIME")
    private LocalDate accountOpenDate;

    @Column(name = "CLOSED_DATETIME")
    private LocalDate accountClosedDate;
    @Column(name = "INACTIVE_DATETIME")
    private LocalDate accountInactiveDate;

    public BigDecimal getBalance() {
        long stamp = lock.tryOptimisticRead();
        BigDecimal currentBalance = availableBalance;
        if (!lock.validate(stamp)) {
            stamp = lock.readLock();
            try {
                currentBalance = availableBalance;
            } finally {
                lock.unlockRead(stamp);
            }
        }
        return currentBalance;
    }

    // Getters and Setters


    public Long accountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String accountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String accountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public AccountType accountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public AccountStatus accountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    
    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }

    public LocalDate accountOpenDate() {
        return accountOpenDate;
    }

    public void setAccountOpenDate(LocalDate accountOpenDate) {
        this.accountOpenDate = accountOpenDate;
    }

    public LocalDate accountClosedDate() {
        return accountClosedDate;
    }

    public void setAccountClosedDate(LocalDate accountClosedDate) {
        this.accountClosedDate = accountClosedDate;
    }

    public LocalDate accountInactiveDate() {
        return accountInactiveDate;
    }

    public void setAccountInactiveDate(LocalDate accountInactiveDate) {
        this.accountInactiveDate = accountInactiveDate;
    }
}

