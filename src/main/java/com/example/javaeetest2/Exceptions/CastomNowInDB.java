package com.example.javaeetest2.Exceptions;

import static jakarta.servlet.http.HttpServletResponse.SC_CONFLICT;
import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

public class CastomNowInDB extends CastomException{
    public CastomNowInDB(String message) {
        super(message, SC_CONFLICT);
    }
}
