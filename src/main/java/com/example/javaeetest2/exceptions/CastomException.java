package com.example.javaeetest2.exceptions;

import lombok.Getter;

@Getter
public abstract class CastomException extends RuntimeException{

    private final int CODE_OF_EXCEPTION;

    protected CastomException(String message, int codeOfException) {
        super(message);
        CODE_OF_EXCEPTION = codeOfException;
    }

}
