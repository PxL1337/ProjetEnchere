package fr.eni.projetenchere.servlet;

import java.io.IOException;

import fr.eni.projetenchere.bo.User;
import fr.eni.projetenchere.bll.UserManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "InscriptionServlet", value = "/inscription")
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/inscription.jsp");
        rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = request.getParameter("nom");
	    String pseudo = request.getParameter("pseudo");
	    String prenom = request.getParameter("prenom");
	    String email = request.getParameter("email");
	    String telephone = request.getParameter("tel");
	    String rue = request.getParameter("rue");
	    String codePostal = request.getParameter("codePostal");
	    String ville = request.getParameter("ville");
	    String motDePasse = request.getParameter("motDePasse");
	    
		User utilisateur = new User(pseudo,nom, prenom,email,telephone,rue,codePostal, ville, motDePasse);
		
		UserManager.getInstance().insertUser(utilisateur);
		
		//methode pour verifier l'insert
		
	}

}
