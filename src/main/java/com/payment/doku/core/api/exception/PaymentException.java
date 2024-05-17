package com.payment.doku.core.api.exception;

import com.payment.doku.core.api.enums.NotificationStatus;

public class PaymentException extends RuntimeException {

    private int statusCode;

    public PaymentException() {
    }

    public PaymentException(NotificationStatus notificationStatus) {
        super(notificationStatus.getStatus());
        statusCode = notificationStatus.getCode();
    }

    public PaymentException(String message) {
        super(message);
    }

    public PaymentException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaymentException(Throwable cause) {
        super(cause);
    }

    public PaymentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public int getStatusCode() {
        return statusCode;
    }
}
