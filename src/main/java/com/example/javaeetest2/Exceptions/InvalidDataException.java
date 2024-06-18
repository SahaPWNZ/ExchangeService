package com.example.javaeetest2.Exceptions;

import static jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST;

public class InvalidDataException extends CastomException{


    public InvalidDataException(String message) {
        super(message, SC_BAD_REQUEST);
    }
}
