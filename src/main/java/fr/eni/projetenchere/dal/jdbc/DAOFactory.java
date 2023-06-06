package fr.eni.projetenchere.dal.jdbc;

import fr.eni.projetenchere.dal.ArticleDAO;
import fr.eni.projetenchere.dal.CategorieDAO;
import fr.eni.projetenchere.dal.EnchereDAO;
import fr.eni.projetenchere.dal.UserDAO;

public class DAOFactory
{
	public static UserDAO getUtilisateurDAO() {
		UserDAO utilisateurDAO = new UserDAOJdbcImplementation();
		return utilisateurDAO;
	}

	public static ArticleDAO getArticleDAO() {
		ArticleDAO articleDAO = new ArticleVenduDAOJdbcImplementation();
		return articleDAO;
	}

	public static EnchereDAO getEnchereDAO() {
		EnchereDAO enchereDAO = new EnchereDAOJdbcImplementation();
		return enchereDAO;
	}

	public static CategorieDAO getCategorieDAO() {
		CategorieDAO categorieDAO = new CategorieDAOJdbcImplementation();
		return categorieDAO;
	}

	/**public static RetraitDAO getRetraitDAO() {
		RetraitDAO retraitDAO = new RetraitDAOJdbcImplementation();
		return retraitDAO;
	}*/

}