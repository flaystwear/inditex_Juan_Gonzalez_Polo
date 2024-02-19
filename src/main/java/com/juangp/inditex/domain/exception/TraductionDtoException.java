package com.juangp.inditex.domain.exception;

public class TraductionDtoException extends RuntimeException{
    public TraductionDtoException() {
        super();
    }
    public TraductionDtoException(String message) {
        super(message);
    }
    public TraductionDtoException(String message, Throwable cause) {
        super(message, cause);
    }
    public TraductionDtoException(Throwable cause) {
        super(cause);
    }
}
