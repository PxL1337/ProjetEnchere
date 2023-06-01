package fr.eni.projetenchere.servlet;

import fr.eni.projetenchere.dal.ConnectionProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;




import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "DatabaseTestServlet", value = {"/testDatabase"})
public class DatabaseTestServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String status;
        Exception exception = null;  // Add this line

        try {
            Connection conn = ConnectionProvider.getConnection();
            if (conn != null) {
                status = "La connexion à la base de données a réussi.";
                conn.close();
            } else {
                status = "La connexion à la base de données a échoué.";
            }
        } catch (SQLException e) {
            status = "Erreur lors de la tentative de connexion à la base de données: " + e.getMessage();
            exception = e;  // Add this line
        } catch (NullPointerException e) {
            status = "Les variables d'environnement ne sont pas définies correctement. Veuillez consulter les instructions ici: " +
                    "<a href='https://github.com/PxL1337/ProjetEnchere/blob/master/database_config.md'>Configuration d'accès à la base de données</a>";
        }

        request.setAttribute("status", status);
        request.setAttribute("exception", exception);  // Add this line
        request.getRequestDispatcher("/WEB-INF/views/testDatabase.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}