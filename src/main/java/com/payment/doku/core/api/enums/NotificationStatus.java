package com.payment.doku.core.api.enums;

public enum NotificationStatus {

    SUCCESS(200, "success"),
    ACCEPTED(202, "error_response_accepted"),
    DECLINE(400, "decline"),
    BILLING_ALREADY_PAID(400, "billing_already_paid"),
    BILLING_WAS_EXPIRED(400, "billing_was_expired"),
    BILLING_NOT_FOUND(404, "billing_not_found"),
    INVALID_SIGNATURE(401, "invalid_signature");

    private final Integer code;
    private final String status;

    NotificationStatus(Integer code, String status) {
        this.code = code;
        this.status = status;
    }

    public static NotificationStatus getInquiryStatusFromMessage(String message) {
        for (NotificationStatus ns : NotificationStatus.values()) {
            if (ns.getStatus().equals(message))
                return ns;
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
