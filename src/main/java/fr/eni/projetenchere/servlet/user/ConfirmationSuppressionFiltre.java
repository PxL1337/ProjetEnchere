package fr.eni.projetenchere.servlet.user;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@WebFilter("/DeleteAccount")
public class ConfirmationSuppressionFiltre implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Boolean confirmed = (Boolean) httpRequest.getSession().getAttribute("confirmedDelete");

        if (confirmed != null && confirmed) {
            httpRequest.getSession().removeAttribute("confirmedDelete");  // Remove the attribute after it's checked
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN); // Redirect to error page
        }
    }
}
