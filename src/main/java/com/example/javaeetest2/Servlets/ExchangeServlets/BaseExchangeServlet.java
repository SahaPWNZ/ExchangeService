package com.example.javaeetest2.Servlets.ExchangeServlets;

import com.example.javaeetest2.Service.DataManagementService;
import com.example.javaeetest2.Service.ValidationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.servlet.http.HttpServlet;

public abstract class BaseExchangeServlet extends HttpServlet {
    static final ObjectMapper objectMapper = new ObjectMapper();
    final DataManagementService dataService = new DataManagementService();
    final ValidationService validationService = new ValidationService();
    static {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }
}
