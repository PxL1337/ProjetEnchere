package fr.eni.projetenchere.bo;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import fr.eni.projetenchere.bll.CategorieManager;
import fr.eni.projetenchere.bll.UserManager;


public class ArticleVendu 
{
    //-------------------- VARIABLES ZONE --------------------//
    private int noArticle;
    private String nomArticle;
    private String description;
    private Date dateDebutEncheres;
    private Date dateFinEncheres;
    private int prixInitial;
    private int prixVente;
    private User utilisateur;
    private Categorie categorie;

	private int no_categorie;

    //-------------------- CONSTRUCTOR ZONE --------------------//
    public ArticleVendu() {}
    
    // READ
    public ArticleVendu(int noArticle, String nomArticle, String description, Date dateDebutEncheres,
			Date dateFinEncheres, int prixInitial, int prixVente, int userID, int categoryID) throws SQLException
    {
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;		
		this.utilisateur = UserManager.getInstance().selectUserByID(userID);	
		this.categorie = CategorieManager.getInstance().selectCategorieByID(categoryID);
    }

    // INSERT
    public ArticleVendu(String nomArticle, String description, Date dateDebutEncheres, Date dateFinEncheres,
			int prixInitial, int prixVente, int userID, int categoryID) throws SQLException
    {
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;		
		this.utilisateur = UserManager.getInstance().selectUserByID(userID);		
		this.categorie = CategorieManager.getInstance().selectCategorieByID(categoryID);		 
	}
    
    // AFFICHAGE ENCHERES
    public ArticleVendu(int noArticle, String nomArticle, Date dateFinEncheres) throws SQLException
    {
    	this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.dateFinEncheres = dateFinEncheres;
	}

		//---------------------------------------------GETTER SETTER ZONE-------------------------------------------------------//
		public int getNoArticle() {
			return noArticle;
		}


		public void setNoArticle(int noArticle) {
			this.noArticle = noArticle;
		}


		public String getNomArticle() {
			return nomArticle;
		}


		public void setNomArticle(String nomArticle) {
			this.nomArticle = nomArticle;
		}


		public String getDescription() {
			return description;
		}


		public void setDescription(String description) {
			this.description = description;
		}


		public Date getDateDebutEncheres() {
			return dateDebutEncheres;
		}


		public void setDateDebutEncheres(Date dateDebutEncheres) {
			this.dateDebutEncheres = dateDebutEncheres;
		}


		public Date getDateFinEncheres() {
			return dateFinEncheres;
		}


		public void setDateFinEncheres(Date dateFinEncheres) {
			this.dateFinEncheres = dateFinEncheres;
		}


		public int getPrixInitial() {
			return prixInitial;
		}


		public void setPrixInitial(int prixInitial) {
			this.prixInitial = prixInitial;
		}


		public int getPrixVente() {
			return prixVente;
		}


		public void setPrixVente(int prixVente) {
			this.prixVente = prixVente;
		}


		public User getUtilisateur() {
			return utilisateur;
		}


		public void setUtilisateur(User utilisateur) {
			this.utilisateur = utilisateur;
		}


		public Categorie getCategorie() {
			return categorie;
		}


		public void setCategorie(Categorie categorie) {
			this.categorie = categorie;
		}

	public int getNoCategorie() {
		return this.no_categorie;
	}
    

    //---------------------------------------TO STRING ZONE---------------------------------------//
		@Override
		public String toString() {
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			StringBuilder sb = new StringBuilder();
			sb
			.append("Article N° ").append(noArticle).append(" : ")
			.append(nomArticle).append("\n Description de l'article : ")
			.append(description).append("\n Date des enchères : du ")
			.append(dateFormat.format(dateDebutEncheres)).append(" au ")
			.append(dateFormat.format(dateFinEncheres))
			.append("\n Prix initial").append(prixInitial)
			.append((prixVente != 0) ? "Plus haute enchère " + String.valueOf(prixVente) : "Aucune enchère n'a été faite pour l'instant")
			.append("\n Vendeur : ").append(utilisateur.getPseudo())
			.append("\n Catégorie : ")
			.append(categorie.getLibelle());
			
			return sb.toString();
		}
    // --------------------------------------------------------------------------------------------------------------//

}

