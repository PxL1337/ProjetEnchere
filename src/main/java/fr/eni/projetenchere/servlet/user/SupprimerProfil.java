package fr.eni.projetenchere.servlet.user;

import java.io.IOException;
import java.sql.SQLException;

import fr.eni.projetenchere.bll.UserManager;
import fr.eni.projetenchere.bo.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SupprimerCompte", value = "/DeleteAccount")
public class SupprimerProfil extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer l'utilisateur de la session
        User user = (User) request.getSession().getAttribute("utilisateurConnecte");

            // Appeler la méthode de suppression d'utilisateur dans UserManager
            UserManager um = UserManager.getInstance();
            
            try {
				um.deleteUser(user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            // Terminer la session
            request.getSession().invalidate();

            // Rediriger vers la page d'accueil
            response.sendRedirect(request.getContextPath() + "/");

    }
}
