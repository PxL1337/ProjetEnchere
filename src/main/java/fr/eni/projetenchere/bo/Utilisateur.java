package fr.eni.projetenchere.bo;

import java.util.ArrayList;
import java.util.List;

public class Utilisateur {
    //-------------------- VARIABLES ZONE --------------------//
    private int noUtilisateur;
    private String pseudo;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String rue;
    private String ville;
    private String codePostal;
    private String motDePasse;
    private int credit = 100;
    private boolean administrateur = false;

    //-------------------- CONSTRUCTOR ZONE --------------------//

    public Utilisateur() {

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
    public Utilisateur(int noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone,
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

    //---------------------------------------METHODE/FUNCTION ZONE---------------------------------------//


    //---------------------------------------------GETTER SETTER ZONE-------------------------------------------------------//


    // --------hashage du mot de passe----------//


    //---------------------------------------TOSTRING ZONE---------------------------------------//

    // --------------------------------------------------------------------------------------------------------------//

}
