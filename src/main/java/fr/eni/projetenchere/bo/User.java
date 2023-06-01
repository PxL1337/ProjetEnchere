package fr.eni.projetenchere.bo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class User {
    //-------------------- VARIABLES ZONE --------------------//
    private int noUtilisateur;
    private String nom;
    private String pseudo;
    private String prenom;
    private String email;
    private String telephone;
    private String rue;
    private String codePostal;
    private String ville;
    
    private String motDePasse;
    private int credit = 100;
    private boolean administrateur = false;
    private List<Enchere> listeEncheresUtilisateur;
    private List<ArticleVendu> listeArticleUtilisateur;

    //-------------------- CONSTRUCTOR ZONE --------------------//

    public User() {

    }

    /**
     * Constructeur pour recuperation DATABASE
     *
     * @param noUtilisateur
     * @param pseudo
     * @param nom
     * @param prenom
     * @param email
     * @param telephone
     * @param rue
     * @param codePostal
     * @param ville
     * @param motDePasse
     * @param credit
     * @param administrateur 
     */
    public User(int noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone,
                       String rue, String codePostal, String ville, String motDePasse, int credit, boolean administrateur) {
        this.noUtilisateur = noUtilisateur;
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.motDePasse = motDePasse;
        this.credit = credit;
        this.administrateur = administrateur;
    }

	

    public User(String pseudo, String nom, String prenom, String email, String telephone, String rue, String codePostal, 
			String ville, String motDePasse, int credit, boolean administrateur) {
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.ville = ville;
		this.codePostal = codePostal;
		this.motDePasse = motDePasse;
		this.credit = credit;
		this.administrateur = administrateur;
	}

	public User(int noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville) {
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.ville = ville;
		this.codePostal = codePostal;
	}



	public User(int noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse) {
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = motDePasse;
	}
	public User(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse) {
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = motDePasse;
	}

	//---------------------------------------METHODE/FUNCTION ZONE---------------------------------------//
    public void addEnchereToList(Enchere enchere) {
		listeEncheresUtilisateur.add(enchere);
	}
    
    public void addArticleToList (ArticleVendu article) {
    	listeArticleUtilisateur.add(article);
	}

    //---------------------------------------------GETTER SETTER ZONE-------------------------------------------------------//
    public int getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public boolean isAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(boolean administrateur) {
		this.administrateur = administrateur;
	}

    // --------hashage du mot de passe----------//


    public List<Enchere> getListeEncheresUtilisateur() {
		return listeEncheresUtilisateur;
	}

	public void setListeEncheresUtilisateur(List<Enchere> listeEncheresUtilisateur) {
		this.listeEncheresUtilisateur = listeEncheresUtilisateur;
	}

	public List<ArticleVendu> getListeArticleUtilisateur() {
		return listeArticleUtilisateur;
	}

	public void setListeArticleUtilisateur(List<ArticleVendu> listeArticleUtilisateur) {
		this.listeArticleUtilisateur = listeArticleUtilisateur;
	}

	//---------------------------------------TOSTRING ZONE---------------------------------------//
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("Utilisateur N° ").append(noUtilisateur)
	      .append(" : ").append(pseudo)
	      .append("\n nom : ").append(nom)
	      .append(" prenom : ").append(prenom)
	      .append("\n email : ").append(email)
	      .append("\n telephone : ").append(telephone)
	      .append("\n adresse : \n").append(rue)
	      .append("\n ").append(codePostal)
	      .append(" ").append(ville)
	      .append("\n motDePasse : ").append(motDePasse)
	      .append("\n credit : ").append(credit)
	      .append("\n administrateur : ").append(administrateur)
	      ;

	    return sb.toString();
	}

    // --------------------------------------------------------------------------------------------------------------//

}
