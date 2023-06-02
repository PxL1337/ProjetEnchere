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

@WebServlet(name = "Inscription", value = "/Inscription")
public class Inscription extends HttpServlet {
    

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: Implement
    }

    
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
        UserManager userManager = new UserManager();
        boolean utilisateur = true;
        
        try {
            utilisateur = userManager.checkEmailAvailability(email);
        } catch (SQLException e) {
            // Logger l'erreur
            e.printStackTrace();

            // Afficher un message d'erreur
            request.setAttribute("inscriptionSqlError", "L'email saisit existe déjà");
        }
        try {
            utilisateur = userManager.checkPseudoAvailability(pseudo);
        } catch (SQLException e) {
            // Logger l'erreur
            e.printStackTrace();

            // Afficher un message d'erreur
            request.setAttribute("inscriptionSqlError", "Le pseudo saisit existe déjà");
        }
        try {
            utilisateur = userManager.validateCodePostal(codePostal);
        } catch (SQLException e) {
            // Logger l'erreur
            e.printStackTrace();

            // Afficher un message d'erreur
            request.setAttribute("inscriptionSqlError", "Le code postal doit comporter 5 chiffres");
        }
        try {
            utilisateur = userManager.validateMotDePasse(motDePasse);
        } catch (SQLException e) {
            // Logger l'erreur
            e.printStackTrace();
            
            // Afficher un message d'erreur
            request.setAttribute("inscriptionSqlError", "Le mot de passe doit faire au moins 6 caracteres et doit posséder au moins une majuscule");
        }
        
        

        if (utilisateur = true) {
        	if(nom.isEmpty() || prenom.isEmpty() || pseudo.isEmpty() || email.isEmpty() || rue.isEmpty() || ville.isEmpty() || codePostal.isEmpty() || motDePasse.isEmpty()) {
        		response.sendRedirect("login.jsp");
        	}
           else {
	            // Si il manque des données, afficher un message d'erreur
	            request.setAttribute("inscriptionError", "Vous devez remplir tous les champs");
	            request.getRequestDispatcher("/WEB-INF/views/inscription.jsp").forward(request, response);
        }
        
        
        }
    
        else {
            // Si les données ne sont pas valides, afficher un message d'erreur
            request.setAttribute("inscriptionError", "Un champ n'est pas correct");
            request.getRequestDispatcher("/WEB-INF/views/inscription.jsp").forward(request, response);
        }
}
}