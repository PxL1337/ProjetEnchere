package fr.eni.projetenchere.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import fr.eni.projetenchere.bll.CategorieManager;
import fr.eni.projetenchere.bo.Categorie;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "RootFilter", value = "/")
public class RootFilter extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("categories") == null) {
            CategorieManager categorieManager = CategorieManager.getInstance();
            List<Categorie> categories = null;
            try {
                categories = categorieManager.selectAllCategorie();
                request.getSession().setAttribute("categories", categories);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }





        response.sendRedirect(request.getContextPath() + "/accueil");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}