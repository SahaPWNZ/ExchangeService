package com.example.javaeetest2.servlets.CurrenciesServlets;

import com.example.javaeetest2.dto.CurrencyRequestDTO;
import com.example.javaeetest2.dto.ErrorResponseDTO;
import com.example.javaeetest2.exceptions.CastomException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/currencies")
public class CurrenciesServlet extends BaseCurrencyServlet {
//    @Override
//    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        try {
//            String json = objectMapper.writeValueAsString(dataService.getCurrenciesList());
//            response.getWriter().println(json);
//        } catch (CastomException e) {
//            response.setStatus(e.getCODE_OF_EXCEPTION());
//            response.getWriter().println(objectMapper.writeValueAsString(new ErrorResponseDTO(e.getMessage())));
//        }
//    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            CurrencyRequestDTO curDTO = new CurrencyRequestDTO(
                    request.getParameter("Code"),
                    request.getParameter("FullName"),
                    request.getParameter("Sign"));

            response.getWriter().println(objectMapper.writeValueAsString(dataService.insertCurrency(curDTO)));
            response.setStatus(HttpServletResponse.SC_CREATED);
        } catch (CastomException e) {
            response.setStatus(e.getCODE_OF_EXCEPTION());
            response.getWriter().println(objectMapper.writeValueAsString(new ErrorResponseDTO(e.getMessage())));
        }
    }

    public void destroy() {
    }
}