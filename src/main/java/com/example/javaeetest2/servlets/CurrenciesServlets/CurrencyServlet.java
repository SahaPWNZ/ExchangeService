package com.example.javaeetest2.servlets.CurrenciesServlets;

import com.example.javaeetest2.dto.ErrorResponseDTO;
import com.example.javaeetest2.exceptions.CastomException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/currency/*")
public class CurrencyServlet extends BaseCurrencyServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String code = request.getPathInfo().substring(1);
            response.getWriter().println(objectMapper.writeValueAsString(dataService.getCurrencyOnCode(code)));

        } catch (CastomException e) {
            response.setStatus(e.getCODE_OF_EXCEPTION());
            response.getWriter().println(objectMapper.writeValueAsString(new ErrorResponseDTO(e.getMessage())));
        }
    }
}