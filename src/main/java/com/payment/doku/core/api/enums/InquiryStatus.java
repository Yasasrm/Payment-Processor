package com.payment.doku.core.api.enums;

public enum InquiryStatus {

    SUCCESS(200, "success"),
    DECLINE(400, "decline"),
    BILLING_ALREADY_PAID(400, "billing_already_paid"),
    BILLING_WAS_EXPIRED(400, "billing_was_expired"),
    BILLING_NOT_FOUND(404, "billing_not_found"),
    INVALID_SIGNATURE(401, "invalid_signature");

    private final Integer code;
    private final String status;

    InquiryStatus(Integer code, String status) {
        this.code = code;
        this.status = status;
    }

    public static InquiryStatus getInquiryStatusFromMessage(String message) {
        for (InquiryStatus is : InquiryStatus.values()) {
            if (is.getStatus().equals(message))
                return is;
        }
        return DECLINE;
    }

    public Integer getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }
}
