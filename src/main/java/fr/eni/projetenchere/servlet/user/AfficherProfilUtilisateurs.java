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

/**
 * Servlet implementation class AfficherProfilUtilisateurs
 */
@WebServlet(name = "AfficherProfilUtilisateur", value = "/ProfileUser")
public class AfficherProfilUtilisateurs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		UserManager um = UserManager.getInstance();
		User user = null;
		try {
			user = um.selectUserByID(Integer.valueOf(id));
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		
		if (request.getSession().getAttribute("message") != null) {
			String message = (String) request.getSession().getAttribute("message");
			request.getSession().removeAttribute("message");
			// Ajouter le message à la requête pour qu'il puisse être utilisé dans la JSP
			request.setAttribute("message", message);
		}

			request.setAttribute("user", user);
			request.getRequestDispatcher("/WEB-INF/views/user/ShowUser.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
