package com.example.javaeetest2.Servlets.ExchangeServlets;

import com.example.javaeetest2.DTO.ErrorResponseDTO;
import com.example.javaeetest2.DTO.ExchangeRateRequestDTO;
import com.example.javaeetest2.Exceptions.CastomException;
import com.example.javaeetest2.Service.ExchangeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

@WebServlet (value = "/exchange")
public class ExchangeServlet extends baseExchangeServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        try {
            validationService.isValidCode( req.getParameter("from"));
            validationService.isValidCode(req.getParameter("to"));
            validationService.isValidRate(req.getParameter("amount"));
            ExchangeRateRequestDTO requestWithAmountDTO = dataService.getDtoByRequest(
                    req.getParameter("from"),
                    req.getParameter("to"),
                    new BigDecimal(req.getParameter("amount")));
            ExchangeService service = new ExchangeService(requestWithAmountDTO);
            writer.println(objectMapper.writeValueAsString(service.Exchange()));
        }
        catch (CastomException e){
            resp.setStatus(e.getCODE_OF_EXCEPTION());
            resp.getWriter().println(objectMapper.writeValueAsString(new ErrorResponseDTO(e.getMessage())));
        }
    }
}
