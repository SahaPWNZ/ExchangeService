package com.example.javaeetest2.Servlets.CurrenciesServlets;

import com.example.javaeetest2.Service.DataManagementService;
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
