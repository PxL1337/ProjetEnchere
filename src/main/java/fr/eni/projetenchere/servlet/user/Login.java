package fr.eni.projetenchere.servlet.user;

import java.io.IOException;
import java.sql.SQLException;

import fr.eni.projetenchere.bll.UserManager;
import fr.eni.projetenchere.bo.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet(name = "Connection", value = "/login")
public class Login extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Vérifiez si l'utilisateur est déjà connecté
        if (request.getSession().getAttribute("utilisateurConnecte") != null) {
            // Si oui, redirection vers une autre page ou affichez un message
            request.getSession().setAttribute("message", "Vous êtes déjà connecté");
            response.sendRedirect(request.getContextPath() + "/");
        } else {
            // Si non, affichez la page de connexion
            request.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pseudoOuEmail = request.getParameter("pseudoOuEmail");
        String motDePasse = request.getParameter("motDePasse");

        //Valider les données utilisateur
        UserManager userManager = UserManager.getInstance();
        User utilisateur = null;
        try {
            utilisateur = userManager.selectUserByPseudoOuEmail(pseudoOuEmail);
        } catch (SQLException e) {
            // Logger l'erreur
            e.printStackTrace();

            // Afficher un message d'erreur
            request.setAttribute("loginSqlError", "Une erreur est survenue lors de la tentative de connexion. Veuillez réessayer plus tard.");
        }

        if (utilisateur != null && BCrypt.checkpw(motDePasse, utilisateur.getMotDePasse())) {
            // Si les données sont valides, demarrer une session
            HttpSession session = request.getSession();
            session.setAttribute("utilisateurConnecte", utilisateur);

            // Créer un cookie pour l'utilisateur
            Cookie cookie = new Cookie("utilisateurConnecte", utilisateur.getPseudo());
            cookie.setMaxAge(60 * 5); // 5 minutes
            response.addCookie(cookie);

            // Redirection vers la page d'accueil
            response.sendRedirect(request.getContextPath() + "/");
    } else {
            // Si les données ne sont pas valides, afficher un message d'erreur
            request.setAttribute("loginError", "Pseudo ou mot de passe incorrect");
            request.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(request, response);
        }
    }
}