package fr.eni.projetenchere.bo;

import java.sql.Date;
import java.text.SimpleDateFormat;


public class Enchere {
    //-------------------- VARIABLES ZONE --------------------//
	private int no_Enchere;
    private Date dateEnchere;
    private int montantEnchere;
    private int noArticle;
    private int no_encherisseur;	


    //-------------------- CONSTRUCTOR ZONE --------------------//
    public Enchere() {}
    
    public Enchere(int enchereID, Date dateEnchere, int montantEnchere, int articleID,
    		int encherisseur) 
    {
    	this.no_Enchere = enchereID;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.noArticle = articleID;
		this.no_encherisseur = encherisseur;
	}
    
    public Enchere(Date dateEnchere, int montantEnchere, int articleID,
    		int encherisseur) 
    {
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.noArticle = articleID;
		this.no_encherisseur = encherisseur;
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

	public int getNoEncherisseur() {
		return no_encherisseur;
	}

	public void setNoEncherisseur(int noEncherisseur) {
		this.no_encherisseur = noEncherisseur;
	}

    //---------------------------------------TO STRING ZONE---------------------------------------//
	@Override
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		return "Enchere n° " + getNo_Enchere()
				+ " du " + dateFormat.format(getDateEnchere())
				+ " au prix de " + getMontantEnchere()
				+ " associée à l'article n° " + getNoArticle()
				+ " de l'utilisateur n° " + getNoEncherisseur();
	}
    // --------------------------------------------------------------------------------------------------------------//
}
