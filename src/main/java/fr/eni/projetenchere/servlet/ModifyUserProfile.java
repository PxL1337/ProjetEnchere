package fr.eni.projetenchere.servlet;

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

@WebServlet(name = "modifyUserProfile", value = "/modifyUserProfile")
public class ModifyUserProfile extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Vérifiez si l'utilisateur est connecté
		if (request.getSession().getAttribute("utilisateurConnecte") == null) {
			// Si non, redirection vers la page de connexion ou afficher un message d'erreur
			request.setAttribute("message", "Vous n'êtes pas connecté");
			request.getRequestDispatcher("/WEB-INF/views/connexion.jsp").forward(request, response);
		} else {
			// Si oui, affichez la page de modification de profil avec les informations actuelles de l'utilisateur
			User user = (User) request.getSession().getAttribute("utilisateurConnecte");
			request.setAttribute("user", user);
			request.getRequestDispatcher("/WEB-INF/views/modification_profil.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Récupérez les valeurs des champs du formulaire de modification de profil
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		String currentMdp = request.getParameter("currentMdp");
		String newMdp = request.getParameter("newMdp");
		String confirmationMdp = request.getParameter("confirmationMdp");

		UserManager userManager = UserManager.getInstance();
		BusinessException businessException = new BusinessException();

		try {
			// Vérifiez les règles de validation et effectuez les modifications si tout est valide
			User user = (User) request.getSession().getAttribute("utilisateurConnecte");

			if (!userManager.verifyPassword(user, currentMdp)) {
				businessException.ajouterErreur(CodeErreur.MDP_INCORRECT);
			}

			if (!userManager.validateMotDePasse(newMdp)) {
				businessException.ajouterErreur(CodeErreur.MDP_INVALIDE);
			}

			if (!newMdp.equals(confirmationMdp)) {
				businessException.ajouterErreur(CodeErreur.CONFIRMATION_MDP_INCORRECTE);
			}

			if (businessException.hasErreurs()) {
				request.setAttribute("user", user);
				request.setAttribute("listeCodesErreur", businessException.getListeCodesErreur());
				request.getRequestDispatcher("/WEB-INF/views/mmodifyUserProfile.jsp").forward(request, response);
			} else {
				user.setPseudo(pseudo);
				user.setNom(nom);
				user.setPrenom(prenom);
				user.setEmail(email);
				user.setTelephone(telephone);
				user.setRue(rue);
				user.setCodePostal(codePostal);
				user.setVille(ville);

				if (!newMdp.isEmpty()) {
					user.setMotDePasse(newMdp);
				}

				userManager.updateUser(user);

				// Mettez à jour la session avec les nouvelles informations de l'utilisateur
				request.getSession().setAttribute("utilisateurConnecte", user);

				// Redirigez vers la page de profil mise à jour ou affichez un message de succès
				request.setAttribute("message", "Votre profil a été mis à jour avec succès");
				request.getRequestDispatcher("/WEB-INF/views/profil.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}