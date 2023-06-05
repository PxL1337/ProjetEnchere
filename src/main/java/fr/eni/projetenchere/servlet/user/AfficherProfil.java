package fr.eni.projetenchere.servlet.user;

import java.io.IOException;

import fr.eni.projetenchere.bll.UserManager;
import fr.eni.projetenchere.bo.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AfficherProfil
 */
@WebServlet(name = "AfficherProfil", value = "/Profile")
public class AfficherProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			User currentUser = (User) request.getSession().getAttribute("utilisateurConnecte");
			// Maintenant, utilisez cet objet User pour récupérer les informations de l'utilisateur à partir de la base de données
			UserManager um = UserManager.getInstance();
			User user = um.selectUserByID(currentUser.getNoUtilisateur());

		// Récupérer le message de la session, si présent
		if (request.getSession().getAttribute("message") != null) {
			String message = (String) request.getSession().getAttribute("message");
			request.getSession().removeAttribute("message");
			// Ajouter le message à la requête pour qu'il puisse être utilisé dans la JSP
			request.setAttribute("message", message);
		}

			request.setAttribute("user", user);
			request.getRequestDispatcher("/WEB-INF/views/user/ShowUser.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
