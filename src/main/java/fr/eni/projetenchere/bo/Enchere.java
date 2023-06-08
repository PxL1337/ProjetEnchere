package fr.eni.projetenchere.bo;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import fr.eni.projetenchere.bll.ArticleManager;
import fr.eni.projetenchere.bll.UserManager;


public class Enchere {
    //-------------------- VARIABLES ZONE --------------------//
	private int no_Enchere;
    private Date dateEnchere;
    private int montantEnchere;
    private int noArticle;
    private int no_utilisateur;

    private ArticleVendu article;
    private User proprietaire;

    //-------------------- CONSTRUCTOR ZONE --------------------//
    public Enchere() {}
    
    public Enchere(int enchereID, Date dateEnchere, int montantEnchere, int articleID,
    		int proprietaireID) throws SQLException
    {
    	this.no_Enchere = enchereID;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.noArticle = articleID;
		this.no_utilisateur = proprietaireID;
		
		setArticle(ArticleManager.getInstance().selectArticleByID(articleID));
		setProprietaire(UserManager.getInstance().selectUserByID(proprietaireID));
	}
    
    public Enchere(Date dateEnchere, int montantEnchere, int articleID,
    		int proprietaireID) throws SQLException
    {
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.noArticle = articleID;
		this.no_utilisateur = proprietaireID;
		
		setArticle(ArticleManager.getInstance().selectArticleByID(articleID));
		setProprietaire(UserManager.getInstance().selectUserByID(proprietaireID));
	}

    //---------------------------------------------GETTER SETTER ZONE-------------------------------------------------------//
    public int getNo_Enchere() {
		return no_Enchere;
	}

	public void setNo_Enchere(int no_Enchere) {
		this.no_Enchere = no_Enchere;
	}
    
    
    public Date getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(Date dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public int getMontantEnchere() {
		return montantEnchere;
	}

	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public int getNoProprietaire() {
		return no_utilisateur;
	}

	public void setNoProprietaire(int id) {
		this.no_utilisateur = id;
	}

    //---------------------------------------TO STRING ZONE---------------------------------------//
	@Override
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		return "Enchere n° " + getNo_Enchere()
				+ " du " + dateFormat.format(getDateEnchere())
				+ " au prix de " + getMontantEnchere()
				+ " associée à l'article n° " + getNoArticle()
				+ " de l'utilisateur n° " + getNoProprietaire();
	}
    // --------------------------------------------------------------------------------------------------------------//

	public User getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(User proprietaire) {
		this.proprietaire = proprietaire;
	}

	public ArticleVendu getArticle() {
		return article;
	}

	public void setArticle(ArticleVendu article) {
		this.article = article;
	}
}
