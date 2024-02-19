package com.juangp.inditex.domain.exception;

public class RequestNotAcceptableException extends RuntimeException {
    public RequestNotAcceptableException() {
        super();
    }

    public RequestNotAcceptableException(String message) {
        super(message);
    }

    public RequestNotAcceptableException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestNotAcceptableException(Throwable cause) {
        super(cause);
    }

    protected RequestNotAcceptableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
