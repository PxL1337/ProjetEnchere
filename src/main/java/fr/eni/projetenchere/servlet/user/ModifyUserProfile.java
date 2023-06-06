package fr.eni.projetenchere.servlet.user;

import java.io.IOException;
import java.sql.SQLException;

import fr.eni.projetenchere.bll.UserManager;
import fr.eni.projetenchere.bo.User;
import fr.eni.projetenchere.exception.BusinessException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name = "modifyUserProfile", value = "/ModifyUserProfile")
public class ModifyUserProfile extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// affichez la page de modification de profil avec les informations actuelles de l'utilisateur
			User user = (User) request.getSession().getAttribute("utilisateurConnecte");
			request.setAttribute("user", user);
			request.getRequestDispatcher("/WEB-INF/views/user/modifyUserProfile.jsp").forward(request, response);

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
		String newPassword = request.getParameter("newMdp");
		String confirmationMdp = request.getParameter("confirmationMdp");


		UserManager userManager = UserManager.getInstance();

		User user = (User) request.getSession().getAttribute("utilisateurConnecte");
		try {
			userManager.validateAndUpdateUser(user, motDePasse, newPassword, confirmationMdp, pseudo, nom, prenom, email, telephone, rue, codePostal, ville);
			User connected = userManager.selectUserByPseudoOuEmail(user.getPseudo());
			request.getSession().setAttribute("utilisateurConnecte", connected);
			request.getSession().setAttribute("message", "Votre profil a été mis à jour avec succès.");
			response.sendRedirect(request.getContextPath() + "/Profile");
		} catch (BusinessException e) {
			request.setAttribute("utilisateur", user);
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			request.getRequestDispatcher("/WEB-INF/views/user/modifyUserProfile.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}