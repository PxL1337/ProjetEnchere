package fr.eni.projetenchere.bo;


public class Retrait {
    //-------------------- VARIABLES ZONE --------------------//
    private int noArticle;
    private String rue;
    private String codePostal;
    private String ville;
	
    private ArticleVendu article;
	private User utilisateur;

    //-------------------- CONSTRUCTOR ZONE --------------------//
	 public Retrait(int noArticle, String rue, String codePostal, String ville, ArticleVendu article,
				User utilisateur) {
			this.noArticle = noArticle;
			this.rue = rue;
			this.codePostal = codePostal;
			this.ville = ville;
			this.article = article;
			this.utilisateur = utilisateur;
		}



	 public Retrait() {
	 }


	public Retrait(ArticleVendu article, User utilisateur) {
		this.article = article;
		this.utilisateur = utilisateur;
		this.noArticle = article.getNoArticle();
		this.rue = utilisateur.getRue();
		this.codePostal = utilisateur.getCodePostal();
		this.ville = utilisateur.getVille();
	}



	//---------------------------------------METHODE/FUNCTION ZONE---------------------------------------//


    public ArticleVendu getArticle() {
		return article;
	}



	public void setArticle(ArticleVendu article) {
		this.article = article;
	}



	public User getUtilisateur() {
		return utilisateur;
	}



	public void setUtilisateur(User utilisateur) {
		this.utilisateur = utilisateur;
	}



	//---------------------------------------------GETTER SETTER ZONE-------------------------------------------------------//
	public int getNoArticle() {
		return noArticle;
	}



	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}



	public String getRue() {
		return rue;
	}



	public void setRue(String rue) {
		this.rue = rue;
	}



	public String getCodePostal() {
		return codePostal;
	}



	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}



	public String getVille() {
		return ville;
	}



	public void setVille(String ville) {
		this.ville = ville;
	}

    //---------------------------------------TO STRING ZONE---------------------------------------//
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("retrait de l'article N° : ").append(noArticle)
		.append(article.getNomArticle()).append("\n adresse : \n")
		.append(rue).append("\n").append(codePostal)
		.append(ville).append("\n de l'ulisateur N° : ").append(utilisateur.getNoUtilisateur())
		.append(" ").append(utilisateur.getPseudo());
		return sb.toString();
	}
    // --------------------------------------------------------------------------------------------------------------//

}
