package fr.eni.projetenchere.dal.jdbc;

import fr.eni.projetenchere.bo.Utilisateur;

public class TestDAL 
{
	public static void main(String[] args) 
	{
		UtilisateurDAOJdbcImplementation userDAO = new UtilisateurDAOJdbcImplementation();
		
		
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
		
		
		
		  Utilisateur utilisateur = new Utilisateur( 3, "vzevez", "JOSvevE",
		  "YAvvNN", "TORTUEGENIALE@outlook.fr", "0688559977",
		  "69 avenue de la grande maison", "Bordeaux", "33500", "golemPlanB");
		  
		  // Tu récupèrs l'utilisateur que tu veux
		  // Modifies 
		  userDAO.update(utilisateur);
		 
		 
		
		/* userDAO.delete(userDAO.selectByID(2)); */
		
		
		/*
		 * for (Utilisateur user : userDAO.selectAll()) {
		 * System.out.println("User found : " + user.toString()); }
		 */
		 
		
		/* userDAO.updateUserCredit( userDAO.selectByID(3), 50); */
		/* userDAO.selectByPseudo(userDAO.selectByID(3).getPseudo()); */
		  
		/* userDAO.deleteAllUser(); */
	}
}