package com.example.javaeetest2.Servlets.CurrenciesServlets;

import com.example.javaeetest2.Service.DataManagementService;
import com.example.javaeetest2.Service.ValidationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet
public abstract class baseCurrencyServlet extends HttpServlet {
    static final  ObjectMapper objectMapper = new ObjectMapper();
    final  DataManagementService dataService = new DataManagementService();
    final  ValidationService validationService = new ValidationService();
    static {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }
}
