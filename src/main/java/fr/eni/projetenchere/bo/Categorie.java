package fr.eni.projetenchere.bo;

import java.util.List;

public class Categorie {
    //-------------------- VARIABLES ZONE --------------------//
    private int noCategorie;
    private String libelle;
	private List <ArticleVendu> ArticlesDeLaCategorie;

    //-------------------- CONSTRUCTOR ZONE --------------------//
	 public Categorie(int noCategorie, String libelle, List<ArticleVendu> articlesDeLaCategorie) {
			this.noCategorie = noCategorie;
			this.libelle = libelle;
			ArticlesDeLaCategorie = articlesDeLaCategorie;
		}
	 

    public Categorie() {
	}
    
    

	public Categorie(String libelle, List<ArticleVendu> articlesDeLaCategorie) {
		this.libelle = libelle;
		ArticlesDeLaCategorie = articlesDeLaCategorie;
	}


	public Categorie(int noCategorie, String libelle) {
		this.noCategorie = noCategorie;
		this.libelle = libelle;
	}


	//---------------------------------------METHODE/FUNCTION ZONE---------------------------------------//

		public void addArticleToList (ArticleVendu article) {
			ArticlesDeLaCategorie.add(article);
		}
    //---------------------------------------------GETTER SETTER ZONE-------------------------------------------------------//

   

	public List<ArticleVendu> getArticlesDeLaCategorie() {
		return ArticlesDeLaCategorie;
	}
	public void setArticlesDeLaCategorie(List<ArticleVendu> articlesDeLaCategorie) {
		ArticlesDeLaCategorie = articlesDeLaCategorie;
	}
	public int getNoCategorie() {
		return noCategorie;
	}
	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
    //---------------------------------------TO STRING ZONE---------------------------------------//
	@Override
	public String toString() {
		return "Catégorie N° " + noCategorie + libelle;
	}
    // --------------------------------------------------------------------------------------------------------------//
}
