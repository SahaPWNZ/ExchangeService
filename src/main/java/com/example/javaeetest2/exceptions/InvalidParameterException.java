package com.example.javaeetest2.exceptions;

import static jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST;

public class InvalidParameterException extends CastomException{


    public InvalidParameterException(String message) {
        super(message, SC_BAD_REQUEST);
    }
}
