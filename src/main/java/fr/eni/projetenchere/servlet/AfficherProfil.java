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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // Vérifiez si l'utilisateur est déjà connecté
        if (request.getSession().getAttribute("utilisateurConnecte") != null) {
            // Si oui, affichage du profil
        	String id = request.getParameter("id");
        	User user = new User();
        	UserManager um = new UserManager();
        	user = um.selectUserByID(Integer.parseInt(id));
        	request.setAttribute("user", user);
        	request.getRequestDispatcher("/WEB-INF/views/ShowUser.jsp").forward(request, response);

        } else {
            // Si non, affichez la page de connexion
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
            
       }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
