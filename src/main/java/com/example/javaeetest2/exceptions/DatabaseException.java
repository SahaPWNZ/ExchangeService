package com.example.javaeetest2.exceptions;

import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

public class DatabaseException extends CastomException {
    public DatabaseException(String message) {
        super(message, SC_INTERNAL_SERVER_ERROR);
    }
}
