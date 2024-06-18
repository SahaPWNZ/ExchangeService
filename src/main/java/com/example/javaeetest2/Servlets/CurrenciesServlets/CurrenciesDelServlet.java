package com.example.javaeetest2.Servlets.CurrenciesServlets;

import com.example.javaeetest2.DAO.CurrenciesDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value = "/currencyDel/*")
public class CurrenciesDelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getPathInfo().substring(1);
        CurrenciesDAO curDAO = new CurrenciesDAO();
        try {
            curDAO.deliteCurrencyOnCode(code);
            System.out.println(code + " Валюта с данным кодом удалена");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
