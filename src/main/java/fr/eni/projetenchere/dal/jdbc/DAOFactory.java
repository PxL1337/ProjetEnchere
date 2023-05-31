package fr.eni.projetenchere.dal.jdbc;

import fr.eni.projetenchere.dal.UtilisateurDAO;

public class DAOFactory
{
	public static UtilisateurDAO getUtilisateurDAO()
	{
		UtilisateurDAO utilisateurDAO = new UtilisateurDAOJdbcImplementation();
		return utilisateurDAO;
	}
}