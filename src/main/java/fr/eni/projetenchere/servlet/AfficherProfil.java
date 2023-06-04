package fr.eni.projetenchere.servlet;

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
		if (request.getSession().getAttribute("utilisateurConnecte") != null) {
			User currentUser = (User) request.getSession().getAttribute("utilisateurConnecte");
			// Maintenant, utilisez cet objet User pour récupérer les informations de l'utilisateur à partir de la base de données
			UserManager um = UserManager.getInstance();
			User user = um.selectUserByID(currentUser.getNoUtilisateur());

			request.setAttribute("user", user);
			request.getRequestDispatcher("/WEB-INF/views/ShowUser.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
