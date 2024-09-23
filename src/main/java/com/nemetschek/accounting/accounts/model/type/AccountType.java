package com.nemetschek.accounting.accounts.model.type;

public enum AccountType {
    S("Saving"),
    D("Deposit"),
    L("Loan"),
    C("Checking");

    private String label;

    AccountType(String label) {
        this.label = label;
    }
}
