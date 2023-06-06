package fr.eni.projetenchere.bll;

import fr.eni.projetenchere.bll.utils.DateUtils;
import fr.eni.projetenchere.bo.ArticleVendu;
import fr.eni.projetenchere.dal.ArticleDAO;
import fr.eni.projetenchere.dal.jdbc.DAOFactory;
import fr.eni.projetenchere.exception.BusinessException;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;

public class ArticleManager {
	private static ArticleManager instance;

	public static ArticleManager getInstance() {
		if (instance == null) {
			instance = new ArticleManager();
		}

		return instance;
	}

	private ArticleDAO articleDAO;
	private BusinessException businessException;

	private ArticleManager() {
		articleDAO = DAOFactory.getArticleDAO();
		businessException = new BusinessException();
	}

	public void insert(ArticleVendu article) throws SQLException
	{
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

	public ArticleVendu createdArticle(String nomArticle, String description, String dateDebutEncheresStr, String dateFinEncheresStr, String miseAPrixStr, int idUtilisateur, String idCategorie) throws BusinessException, SQLException, ParseException {
		// Recupérer l'id de la catégorie
		int id = Integer.parseInt(idCategorie);

		// Conversion des dates
		Date dateDebutEncheres = DateUtils.stringToDate(dateDebutEncheresStr);
		Date dateFinEncheres = DateUtils.stringToDate(dateFinEncheresStr);

		// Conversion de la mise à prix
		int miseAPrix = Integer.parseInt(miseAPrixStr);

		// Création de l'article
		ArticleVendu article = new ArticleVendu(nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix, miseAPrix, idUtilisateur, id);

		return article;
	}

	public void validateArticle(ArticleVendu article) throws BusinessException {
		BusinessException businessException = new BusinessException();
		if (article.getNomArticle() == null || article.getNomArticle().trim().isEmpty() || article.getNomArticle().length() > 30) {
			businessException.ajouterErreur(CodeErreur.ARTICLE_NOM_ERREUR);
		}
		if (article.getDescription() == null || article.getDescription().trim().isEmpty() || article.getDescription().length() > 300) {
			businessException.ajouterErreur(CodeErreur.ARTICLE_DESCRIPTION_ERREUR);
		}
		if (article.getDateDebutEncheres().after(article.getDateFinEncheres())) {
			businessException.ajouterErreur(CodeErreur.ARTICLE_DATE_ERREUR);
		}
		if (article.getPrixInitial() <= 0) {
			businessException.ajouterErreur(CodeErreur.ARTICLE_PRIX_ERREUR);
		}
		if (businessException.hasErreurs()) {
			throw businessException;
		}
	}
}