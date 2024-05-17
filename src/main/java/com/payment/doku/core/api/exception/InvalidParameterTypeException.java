package com.payment.doku.core.api.exception;

public class InvalidParameterTypeException extends RuntimeException {

    public InvalidParameterTypeException() {
        super("Invalid type parsed as a parameter.");
    }

    public InvalidParameterTypeException(String message) {
        super(message);
    }

    public InvalidParameterTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidParameterTypeException(Throwable cause) {
        super(cause);
    }

    public InvalidParameterTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
