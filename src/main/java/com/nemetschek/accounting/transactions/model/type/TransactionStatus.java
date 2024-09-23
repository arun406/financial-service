package com.nemetschek.accounting.transactions.model.type;

public enum TransactionStatus {

    S("success"), F("failure");
    private String label;
    TransactionStatus(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
