package fr.eni.projetenchere.bll;

import fr.eni.projetenchere.bll.utils.DateUtils;
import fr.eni.projetenchere.bo.ArticleVendu;
import fr.eni.projetenchere.dal.ArticleDAO;
import fr.eni.projetenchere.dal.jdbc.DAOFactory;
import fr.eni.projetenchere.exception.BusinessException;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;

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

	public void createArticle(String nomArticle, String description, String dateDebutEncheresStr, String dateFinEncheresStr, String miseAPrixStr, int idUtilisateur, String categorieLibelle) throws BusinessException, SQLException, ParseException, ParseException {
		// Recupérer l'id de la catégorie
		int idCategorie = CategorieManager.getInstance().selectCategorieByID(Integer.parseInt(categorieLibelle)).getNoCategorie();
		System.out.println("idCategorie = " + idCategorie);

		// Conversion des dates
		Date dateDebutEncheres = DateUtils.stringToDate(dateDebutEncheresStr);
		Date dateFinEncheres = DateUtils.stringToDate(dateFinEncheresStr);

		// Conversion de la mise à prix
		int miseAPrix = Integer.parseInt(miseAPrixStr);

		// Création de l'article
		ArticleVendu article = new ArticleVendu(nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix, miseAPrix, idUtilisateur, idCategorie);

		// Insertion de l'article dans la base de données
		insert(article);
	}
}