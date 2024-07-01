package com.example.javaeetest2.servlets.CurrenciesServlets;

import com.example.javaeetest2.service.DataManagementService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet
public abstract class BaseCurrencyServlet extends HttpServlet {
    static final  ObjectMapper objectMapper = new ObjectMapper();
    final  DataManagementService dataService = new DataManagementService();
    static {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }
}
