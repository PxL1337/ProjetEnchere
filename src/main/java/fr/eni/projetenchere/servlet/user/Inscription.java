package fr.eni.projetenchere.servlet.user;

import java.io.IOException;
import java.sql.SQLException;

import fr.eni.projetenchere.bll.UserManager;
import fr.eni.projetenchere.bll.CodeErreur;
import fr.eni.projetenchere.bo.User;
import fr.eni.projetenchere.exception.BusinessException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "inscription", value = "/inscription")
public class Inscription extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Vérifiez si l'utilisateur est déjà connecté
        if (request.getSession().getAttribute("utilisateurConnecte") != null) {
            // Si oui, redirection vers une autre page ou affichez un message
            request.setAttribute("message", "Vous êtes déjà connecté");
            response.sendRedirect(request.getContextPath() + "/");
        } else {
            // Si non, affichez la page de connexion
            request.getRequestDispatcher("/WEB-INF/views/user/inscription.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pseudo = request.getParameter("pseudo");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");
        String rue = request.getParameter("rue");
        String codePostal = request.getParameter("codePostal");
        String ville = request.getParameter("ville");
        String motDePasse = request.getParameter("motDePasse");
        String passwordConfirmation = request.getParameter("confirmationMotDePasse");

        UserManager userManager = UserManager.getInstance();
        User user = null;

        try {
            user = new User(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, userManager.DEFAULT_USER_CREDIT, userManager.DEFAULT_IS_ADMIN_VALUE);
            userManager.validate(user, passwordConfirmation);
            userManager.insertUser(user);

            User connected = userManager.selectUserByPseudoOuEmail(user.getPseudo());

            // Créer une nouvelle session avec l'utilisateur de la base de données
            request.getSession().setAttribute("utilisateurConnecte", connected);

            // Rediriger vers la page d'accueil
            response.sendRedirect(request.getContextPath() + "/");
        } catch (BusinessException e) {
            request.setAttribute("utilisateur", user);
            request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
            request.getRequestDispatcher("/WEB-INF/views/user/inscription.jsp").forward(request, response);
            e.clearErrors();
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("erreur", "Une erreur est survenue lors de l'inscription. Veuillez réessayer plus tard.");
            request.getRequestDispatcher("/WEB-INF/views/user/inscription.jsp").forward(request, response);
        }
    }
}