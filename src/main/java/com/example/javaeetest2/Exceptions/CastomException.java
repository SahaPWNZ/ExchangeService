package com.example.javaeetest2.Exceptions;

public abstract class CastomException extends RuntimeException{

    private final int CODE_OF_EXCEPTION;

    protected CastomException(String message, int codeOfException) {
        super(message);
        CODE_OF_EXCEPTION = codeOfException;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public int getCODE_OF_EXCEPTION() {
        return CODE_OF_EXCEPTION;
    }
}
