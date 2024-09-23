package com.nemetschek.accounting.accounts.model.type;

public enum UniqueIdType {
    A("Account", "ACC"),
    T("Transaction", "TXN");

    private String label;
    private String prefix;

    UniqueIdType(String label, String prefix) {
        this.label = label;
        this.prefix = prefix;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getLabel() {
        return this.label;
    }
}
