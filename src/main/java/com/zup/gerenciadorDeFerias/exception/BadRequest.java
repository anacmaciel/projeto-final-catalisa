package com.zup.gerenciadorDeFerias.exception;

public class BadRequest extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public BadRequest(String message) {
        super(message);
    }

//    public BadRequest(String message, Throwable cause) {
//
//        super(message, cause);
//    }
}
