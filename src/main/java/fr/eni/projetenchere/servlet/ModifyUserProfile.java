package fr.eni.projetenchere.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetenchere.bll.UserManager;
import fr.eni.projetenchere.bo.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ModifyUserProfile", value = "/ModifyUserProfile")
public class ModifyUserProfile extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
    	String userConnectedValue = "utilisateurConnecte";
    	
    	// Vérifiez si l'utilisateur est déjà connecté...
        if (request.getSession().getAttribute(userConnectedValue) != null) 
        {
        	// Stocker l'utilisateur connecté pour réutiliser ses infos ensuite...
        	User connectedUser = (User) request.getAttribute(userConnectedValue);
            // Et afficher la page de modification.
            request.getRequestDispatcher(
            		"/WEB-INF/views/modifyUserProfile.jsp").forward(request, response);            
        }
        else 
        {
        	// Si oui, redirection vers une autre page ou affichez un message.
            request.setAttribute("message", "Vous êtes déjà connecté");
            request.getRequestDispatcher("/").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
    	// Il faut vérifier les infos avant de les envoyer et effectuer la MàJ
    	
    	// On récupère toutes les infos nouvelles de l'utilisateur
    	String ID = request.getParameter("no_utilisateur");
    	String pseudo = request.getParameter("pseudo");
    	String name = request.getParameter("nom");
    	String firstName = request.getParameter("prenom");
    	String mail = request.getParameter("email");
    	String phone = request.getParameter("telephone");
    	String address = request.getParameter("rue");
    	String zipCode = request.getParameter("code_postal");
    	String city = request.getParameter("ville");
    	String password = request.getParameter("mot_de_passe");
    	
    	// Stocker les nouvelles valeurs dans un nouvel utilisateur
    	User newUser = new User(
    			Integer.parseInt(ID), pseudo, name, firstName, mail, phone, 
    			address, zipCode, city, password);
    	
    	UserManager.getInstance().updateUser(newUser);
    }
}