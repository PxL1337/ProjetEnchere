package fr.eni.projetenchere.bo;

public class Categorie 
{
    //-------------------- VARIABLES ZONE --------------------//
    private int noCategorie;
    private String libelle;

    //-------------------- CONSTRUCTOR ZONE --------------------//
    public Categorie() {}
    
	public Categorie(int noCategorie, String libelle) {
		this.noCategorie = noCategorie;
		this.libelle = libelle;
	}
	
    //---------------------------------------------GETTER SETTER ZONE-------------------------------------------------------//
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
