package com.example.javaeetest2.Exceptions;

import static jakarta.servlet.http.HttpServletResponse.SC_CONFLICT;

public class ConflictException extends CastomException{
    public ConflictException(String message) {
        super(message, SC_CONFLICT);
    }
}
