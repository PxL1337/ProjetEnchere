package fr.eni.projetenchere.servlet.user;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/Profile", "/ModifyUserProfile", "/DeleteAccount", "/Profile"})
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        // Vérifiez si l'utilisateur est connecté
        if (httpRequest.getSession().getAttribute("utilisateurConnecte") == null) {
            // Si non, redirigez vers la page de connexion
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
        } else {
            // Si oui, continuez à traiter la requête
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}