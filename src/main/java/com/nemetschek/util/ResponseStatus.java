package com.nemetschek.util;

public enum ResponseStatus {
    S("Success"),
    F("Failure");

    private final String label;

    ResponseStatus(String label) {
        this.label = label;
    }
}
