package com.payment.doku.core.api.enums;

public enum PaymentMethod {

    VIRTUAL_ACCOUNT("VIRTUAL_ACCOUNT"),
    ONLINE_TO_OFFLINE("ONLINE_TO_OFFLINE");

    private final String id;

    PaymentMethod(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
