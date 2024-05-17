package com.payment.doku.core.api.exception;

public class InvalidRequestObjectException extends RuntimeException {
    public InvalidRequestObjectException() {
        super("Invalid type of object received as request.");
    }

    public InvalidRequestObjectException(String message) {
        super(message);
    }

    public InvalidRequestObjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidRequestObjectException(Throwable cause) {
        super(cause);
    }

    public InvalidRequestObjectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
