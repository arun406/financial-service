package com.nemetschek.accounting.transactions.model.type;

public enum TransactionType {

    I("in"), O("out");
    private String label;

    TransactionType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
