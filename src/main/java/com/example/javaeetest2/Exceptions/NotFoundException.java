package com.example.javaeetest2.Exceptions;

import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;

public class NotFoundException extends CastomException{
    public NotFoundException(String message) {
        super(message, SC_NOT_FOUND);
    }
}
