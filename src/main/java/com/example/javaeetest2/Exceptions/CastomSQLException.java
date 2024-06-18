package com.example.javaeetest2.Exceptions;

import java.sql.SQLException;

import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

public class CastomSQLException extends CastomException {
    public CastomSQLException(String message) {
        super(message, SC_INTERNAL_SERVER_ERROR);
    }
}
