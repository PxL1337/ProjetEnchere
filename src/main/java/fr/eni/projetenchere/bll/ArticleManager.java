package fr.eni.projetenchere.bll;

import fr.eni.projetenchere.dal.ArticleDAO;
import fr.eni.projetenchere.dal.jdbc.DAOFactory;
import fr.eni.projetenchere.exception.BusinessException;

import java.sql.SQLException;

public class ArticleManager
{
	private static ArticleManager instance;
	private ArticleDAO articleDAO;
	
	public static ArticleManager getInstance()
	{
		if (instance == null) {
			instance = new ArticleManager();
		}
		
		return instance;
	}


	/**private ArticleManager()
	{
		articleDAO = DAOFactory.getArticleDAO();
	}*/

	/**public void insert(Article article) throws SQLException, BusinessException {
		validateArticle(article);
		articleDAO.insert(article);
	}

	public void update(Article article) throws SQLException { articleDAO.update(article); }

	public void delete(Article article) throws SQLException { articleDAO.delete(article); }


	public Article selectByID(int ID) throws SQLException { return articleDAO.selectByID(ID); }

	private void validateArticle(Article article) throws BusinessException {
		//TODO: validate article
	}*/





}
