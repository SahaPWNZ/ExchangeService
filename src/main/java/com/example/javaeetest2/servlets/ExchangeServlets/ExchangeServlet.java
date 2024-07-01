package com.example.javaeetest2.servlets.ExchangeServlets;

import com.example.javaeetest2.dto.ErrorResponseDTO;
import com.example.javaeetest2.dto.ExchangeRateReqDTO;
import com.example.javaeetest2.exceptions.CastomException;
import com.example.javaeetest2.service.ExchangeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet (value = "/exchange")
public class ExchangeServlet extends BaseExchangeServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ExchangeRateReqDTO requestWithAmountDTO = dataService.getDtoByRequest(
                    req.getParameter("from"),
                    req.getParameter("to"),
                    new BigDecimal(req.getParameter("amount")));
            ExchangeService service = new ExchangeService(requestWithAmountDTO);
            resp.getWriter().println(objectMapper.writeValueAsString(service.Exchange()));
        }
        catch (CastomException e){
            resp.setStatus(e.getCODE_OF_EXCEPTION());
            resp.getWriter().println(objectMapper.writeValueAsString(new ErrorResponseDTO(e.getMessage())));
        }
    }
}
