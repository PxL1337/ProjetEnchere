package fr.eni.projetenchere.servlet;

import java.io.IOException;
import java.sql.SQLException;

import fr.eni.projetenchere.bll.UserManager;
import fr.eni.projetenchere.bo.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList; import java.util.List;

@WebServlet(name = "Inscription", value = "/Inscription")
public class Inscription extends HttpServlet {
    

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 // Vérifiez si l'utilisateur est déjà connecté
        if (request.getSession().getAttribute("utilisateurConnecte") != null) {
            // Si oui, redirection vers une autre page ou affichez un message
            request.setAttribute("message", "Vous êtes déjà connecté");
            request.getRequestDispatcher("/WEB-INF/views/accueil.jsp").forward(request, response);
        } else {
            // Si non, affichez la page de connexion
            request.getRequestDispatcher("/WEB-INF/views/inscription.jsp").forward(request, response);
        }
    }

    
/*
 * on doit gerer les exceptionsql ici
 * et toutes les autres exceptions
 * 
 */
    
    List<String> errorMessages = new ArrayList<String>();
        
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	String pseudo = request.getParameter("pseudo");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");
        String rue = request.getParameter("rue");
        String ville = request.getParameter("ville");
        String codePostal = request.getParameter("codePostal");
        String motDePasse = request.getParameter("motDePasse");

        //Valider les données utilisateur
        UserManager userManager = UserManager.getInstance();
       
        
        try {
			if (userManager.checkEmailAvailability(email) 
					& userManager.checkPseudoAvailability(pseudo) 
					& userManager.validateCodePostal(codePostal) 
					& userManager.validateMotDePasse(motDePasse)) 
			{
				request.getRequestDispatcher(request.getContextPath()+"/");
			}
      
			else {
				if(userManager.checkEmailAvailability(email) == false) 
				{
					errorMessages.add("Email indisponible");
				}
				
					
				if(userManager.checkPseudoAvailability(pseudo) == false) 
				{
			    		errorMessages.add("Pseudo indisponible");
				}
				
				if(userManager.validateCodePostal(codePostal) == false) 
				{
			    		errorMessages.add("Code postal erroné");
				}
				
				if(userManager.validateMotDePasse(motDePasse) == false) 
				{
			    		errorMessages.add("Mot de passe erroné");
				}
				
				
				// Si il manque des données, afficher un message d'erreur
			    request.setAttribute("inscriptionError", "Vous devez remplir tous les champs");
			    request.getRequestDispatcher("/WEB-INF/views/inscription.jsp").forward(request, response);
			    }
		} catch (SQLException | ServletException | IOException e) {
			e.printStackTrace();
		}
        

}
}
