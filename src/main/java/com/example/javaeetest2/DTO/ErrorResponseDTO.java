package com.example.javaeetest2.DTO;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ErrorResponseDTO {
    private final String message;

    public ErrorResponseDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
