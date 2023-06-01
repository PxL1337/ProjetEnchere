package fr.eni.projetenchere.dal.jdbc;

import fr.eni.projetenchere.dal.UserDAO;

public class DAOFactory
{
	public static UserDAO getUtilisateurDAO()
	{
		UserDAO utilisateurDAO = new UserDAOJdbcImplementation();
		return utilisateurDAO;
	}
}