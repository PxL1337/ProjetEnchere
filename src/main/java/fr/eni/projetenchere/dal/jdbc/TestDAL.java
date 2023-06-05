package fr.eni.projetenchere.dal.jdbc;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;


import java.sql.Connection;

import fr.eni.projetenchere.bo.ArticleVendu;
import fr.eni.projetenchere.bo.User;
import org.junit.Before;

public class TestDAL 
{

		// Vos méthodes de test vont ici
	public static void main(String[] args) 
	{
		/*
		 * UserDAOJdbcImplementation userDAO = new UserDAOJdbcImplementation();
		 */
		
		ArticleVenduDAOJdbcImplementation articleDAO = new ArticleVenduDAOJdbcImplementation();
		
		Date date = Date.valueOf(LocalDate.now());
		
		try {
			ArticleVendu article = new ArticleVendu(
					"Test Article", "Test Description", date, date, 150, 150, 23, 1);
			articleDAO.insert(article);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
		 * for (int i = 0; i < 3; i++) { Utilisateur userTest = new Utilisateur(
		 * "GOLEM", "JOSE", "YANN", "golem@outlook.fr", "0688559977",
		 * "69 avenue de la grande maison", "Bordeaux", "33500", "golemPlanB",
		 * 200, true );
		 * 
		 * userDAO.insert(userTest); }
		 */

		/*
		 * User userTest = new User("GOLEM", "JOSE", "YANN", "golem@outlook.fr",
		 * "0688559977", "69 avenue de la grande maison", "Bordeaux", "33500",
		 * "golemPlanB", 200, true );
		 */
		/*
		 * no_utilisateur=?, pseudo=?, nom=?, prenom=?, email=?, telephone=?,
		 * rue=?, code_postal=?, ville=?, mot_de_passe=?
		 */
		
		
		
		/*
		 * User utilisateur = new User( 10, "p", "JOSvevE", "YAvvNN",
		 * "TORTUEGENIALE@outlook.fr", "0688559977",
		 * "69 avenue de la grande maison", "Bordeaux", "33500", "mdp");
		 * 
		 * userDAO.update(utilisateur);
		 */
		 
		
		/* userDAO.delete(userDAO.selectByID(12)); */
		
		
		/*
		 * for (Utilisateur user : userDAO.selectAll()) {
		 * System.out.println("User found : " + user.toString()); }
		 */
		 
		
		/* userDAO.updateUserCredit( userDAO.selectByID(3), 50); */
		/*
		 * try { userDAO.selectByEmail(userDAO.selectByID(10).getEmail()); }
		 * catch (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		  
		/* userDAO.deleteAllUser(); */
	}
}