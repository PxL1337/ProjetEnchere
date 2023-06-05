package fr.eni.projetenchere.bll;

import fr.eni.projetenchere.bo.ArticleVendu;
import fr.eni.projetenchere.dal.ArticleDAO;
import fr.eni.projetenchere.dal.jdbc.DAOFactory;
import fr.eni.projetenchere.exception.BusinessException;

import java.sql.SQLException;

public class ArticleManager
{
	private static ArticleManager instance;
	
	public static ArticleManager getInstance()
	{
		if (instance == null) {
			instance = new ArticleManager();
		}
		
		return instance; 
	}
	
	private ArticleDAO articleDAO;
	private BusinessException businessException;

	private ArticleManager()
	{
		articleDAO = DAOFactory.getArticleDAO();
		businessException = new BusinessException();
	}

	public void insert(ArticleVendu article) throws SQLException, BusinessException 
	{
		if ( !isThisArticleValid(article) )
		{
			businessException.ajouterErreur(CodeErreur.ARTICLE_INVALIDE);
			return;
		}
		
		articleDAO.insert(article);
	}

	public void update(ArticleVendu article) throws SQLException { 
		articleDAO.update(article); 
	}

	public void delete(ArticleVendu article) throws SQLException { 
		articleDAO.delete(article); 
	}

	public ArticleVendu selectByID(int ID) throws SQLException {
		return articleDAO.selectByID(ID);
	}
	
	public void updateArticleSellingPrice(ArticleVendu article, int newPrice) throws SQLException
	{
		articleDAO.updateSellingPrice(article, newPrice);
	}

	private boolean isThisArticleValid(ArticleVendu article) throws BusinessException, SQLException {
		return articleDAO.isValid(article.getNoArticle());
	}

	public ArticleVendu selectArticleByID(int ID) throws SQLException {
		return articleDAO.selectByID(ID);
	}
}