package com.example.javaeetest2.servlets;

import com.example.javaeetest2.service.DataManagementService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.servlet.http.HttpServlet;

public abstract class BaseServlet extends HttpServlet {
    public static final ObjectMapper objectMapper = new ObjectMapper();
    public final DataManagementService dataService = new DataManagementService();

    static {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

}
