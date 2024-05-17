package com.payment.doku.core.api.exception;

import com.payment.doku.core.api.enums.InquiryStatus;

public class InquiryException extends RuntimeException {

    private int statusCode;

    public InquiryException() {
    }

    public InquiryException(InquiryStatus inquiryStatus) {
        super(inquiryStatus.getStatus());
        statusCode = inquiryStatus.getCode();
    }

    public InquiryException(String message) {
        super(message);
    }

    public InquiryException(String message, Throwable cause) {
        super(message, cause);
    }

    public InquiryException(Throwable cause) {
        super(cause);
    }

    public InquiryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public int getStatusCode() {
        return statusCode;
    }
}
