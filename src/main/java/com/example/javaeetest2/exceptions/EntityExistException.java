package com.example.javaeetest2.exceptions;

import static jakarta.servlet.http.HttpServletResponse.SC_CONFLICT;

public class EntityExistException extends CastomException{
    public EntityExistException(String message) {
        super(message, SC_CONFLICT);
    }
}
