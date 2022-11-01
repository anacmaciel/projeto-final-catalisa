package com.zup.gerenciadorDeFerias.exception;

public class UnprocessableEntityException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UnprocessableEntityException(String message) {
        super(message);
    }

    public UnprocessableEntityException(String message, Throwable cause) {
        super(message, cause);
    }

}
