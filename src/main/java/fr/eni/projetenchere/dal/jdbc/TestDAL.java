package fr.eni.projetenchere.dal.jdbc;

import fr.eni.projetenchere.bo.User;

public class TestDAL 
{
	public static void main(String[] args) 
	{
		UserDAOJdbcImplementation userDAO = new UserDAOJdbcImplementation();
		
		
		/*
		 * for (int i = 0; i < 3; i++) { Utilisateur userTest = new Utilisateur(
		 * "GOLEM", "JOSE", "YANN", "golem@outlook.fr", "0688559977",
		 * "69 avenue de la grande maison", "Bordeaux", "33500", "golemPlanB",
		 * 200, true );
		 * 
		 * userDAO.insert(userTest); }
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
		 userDAO.selectByEmail(userDAO.selectByID(10).getEmail()); 
		  
		/* userDAO.deleteAllUser(); */
	}
}