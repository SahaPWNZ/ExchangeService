package com.example.javaeetest2.Servlets.ExchangeServlets;

import com.example.javaeetest2.DAO.ExchangeRatesDAO;
import com.example.javaeetest2.DTO.ExchangeRateWithAmountRequestDTO;
import com.example.javaeetest2.DTO.ExchangeRateWithAmountResponceDTO;
import com.example.javaeetest2.Service.ExchangeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Optional;

@WebServlet (value = "/exchange")
public class ExchangeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        ObjectMapper objectMapper = new ObjectMapper();
        PrintWriter writer = resp.getWriter();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        ExchangeRateWithAmountRequestDTO requestDTO =
                new ExchangeRateWithAmountRequestDTO(req.getParameter("from"), req.getParameter("to"), new BigDecimal(req.getParameter("amount")));



        ExchangeService service = new ExchangeService(requestDTO);
        ExchangeRateWithAmountResponceDTO respDTO = service.Exchange();
        writer.println(objectMapper.writeValueAsString(respDTO));

    }
}
