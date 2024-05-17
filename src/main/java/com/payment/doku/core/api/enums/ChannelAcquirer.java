package com.payment.doku.core.api.enums;

public enum ChannelAcquirer {

    BCA("BCA"),
    MANDIRI("BANK_MANDIRI"),
    SYARIAH("BANK_SYARIAH_MANDIRI"),
    BRI("BRI"),
    DOKU("DOKU"),
    CIMB("BANK_CIMB"),
    BNI("BNI"),
    PERMATA("BANK_PERMATA"),
    ALFA("ALFA");

    private final String id;

    ChannelAcquirer(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
