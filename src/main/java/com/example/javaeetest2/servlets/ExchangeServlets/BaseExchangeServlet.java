package com.example.javaeetest2.servlets.ExchangeServlets;

import com.example.javaeetest2.service.DataManagementService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.servlet.http.HttpServlet;

public abstract class BaseExchangeServlet extends HttpServlet {
    static final ObjectMapper objectMapper = new ObjectMapper();
    final DataManagementService dataService = new DataManagementService();
    static {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }
}
