package fr.eni.projetenchere.servlet.user;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ConfirmDeleteAccount", value = "/ConfirmDeleteAccount")
public class ConfirmDeleteAccount extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("confirmedDelete", true);
        response.sendRedirect(request.getContextPath() + "/DeleteAccount");
    }
}