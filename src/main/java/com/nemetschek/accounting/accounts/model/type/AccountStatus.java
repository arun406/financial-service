package com.nemetschek.accounting.accounts.model.type;

public enum AccountStatus {
    A("Active"),
    B("Blocked"),
    C("Closed"),
    I("Inactive");

    private String label;

    AccountStatus(String label) {
        this.label = label;
    }
}
