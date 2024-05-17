package com.payment.doku.core.api.enums;

public enum FieldFlag {

    DEFAULT("", 0),
    MANDATORY("*", -2147483648);

    private final String stringValue;
    private final Integer intValue;

    FieldFlag(String stringValue, Integer intValue) {
        this.stringValue = stringValue;
        this.intValue = intValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public Integer getIntValue() {
        return intValue;
    }
}
