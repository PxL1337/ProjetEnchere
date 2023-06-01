package fr.eni.projetenchere.bo;

import java.sql.Date;
import java.text.SimpleDateFormat;


public class Enchere {
    //-------------------- VARIABLES ZONE --------------------//
    private Date dateEnchere;
    private int montantEnchere;
    private int noArticle;

    private ArticleVendu article;
    private User encherisseur;
	


    //-------------------- CONSTRUCTOR ZONE --------------------//
    public Enchere(Date dateEnchere, int montantEnchere, ArticleVendu article,
			User encherisseur) {
		this.dateEnchere = article.getDateDebutEncheres();
		this.montantEnchere = article.getPrixInitial();
		this.noArticle = article.getNoArticle();
		this.article = article;
		this.encherisseur = encherisseur;
		encherisseur.addEnchereToList(this);
	}



	public Enchere() {
	}





    //---------------------------------------METHODE/FUNCTION ZONE---------------------------------------//
		public void updateMontantEnchereArticle() {
			if (article == null) return;
			article.setPrixVente(montantEnchere);
		}

    //---------------------------------------------GETTER SETTER ZONE-------------------------------------------------------//
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



	public ArticleVendu getArticle() {
		return article;
	}



	public void setArticle(ArticleVendu article) {
		this.article = article;
	}



	public User getEncherisseur() {
		return encherisseur;
	}



	public void setEncherisseur(User encherisseur) {
		this.encherisseur = encherisseur;
	}

    //---------------------------------------TO STRING ZONE---------------------------------------//
	@Override
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return "Enchere du " + dateFormat.format(dateEnchere) + "";
	}
    // --------------------------------------------------------------------------------------------------------------//
}
