package com.payment.doku.core.api.enums;

/**
 * @author YasasMa
 * @version 1.0.0.0
 */
public enum ApiConfig {

    API_CODE("DOKU"),
    DEV("DEV"),
    QA("QA"),
    UAT("UAT"),
    LIVE("LIVE"),
    DEFAULT_RESPONSE("Error Occurred before process response."),
    DEFAULT_USER("DOKU-API");

    private final String code;

    ApiConfig(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
