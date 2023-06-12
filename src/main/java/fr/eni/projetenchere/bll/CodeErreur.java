package fr.eni.projetenchere.bll;

public class CodeErreur {

	public static final String ERROR_URL_PATTERN = "&error=";
	
    // User
	public static final int PSEUDO_INVALIDE = 20000;
    public static final int NOM_INVALIDE = 20001;
    public static final int PRENOM_INVALIDE = 20002;
    public static final int EMAIL_INVALIDE = 20003;
    public static final int TELEPHONE_INVALIDE = 20004;
    public static final int RUE_INVALIDE = 20005;
    public static final int CODE_POSTAL_INVALIDE = 20006;
    public static final int VILLE_INVALIDE = 20007;
    public static final int MDP_INVALIDE = 20008;
    public static final int CREDIT_INVALIDE = 20009;
    public static final int EMAIL_EXISTANT = 20010;
    public static final int PSEUDO_EXISTANT = 20011;
    public static final int ECHEC_CONNEXION_MDP_INCORRECT = 20012;
    public static final int PSEUDO_INEXISTANT = 20013;
    public static final int NO_RETRAIT_INVALIDE = 20014;
    public static final int NO_CATEGORIE_INVALIDE = 20015;
    public static final int NO_UTILISATEUR_INVALIDE = 20016;
    public static final int PRIX_VENTE_INVALIDE = 20017;
    public static final int PRIX_VENTE_INITIAL_INFERIEUR = 20018;
    public static final int PRIX_VENTE_INITIAL_INVALIDE = 20019;
    public static final int DATE_DEBUT_INFERIEUR = 20020;
    public static final int DATE_DEBUT_INFERIEUR_JOUR = 20021;
    public static final int DESCRITPION_INVALIDE = 20022;
    public static final int MONTANT_INVALIDE = 20025;
    public static final int ENCHERE_INFERIEUR_PRIX_DE_VENTE = 20026;
    public static final int MDP_INCORRECT = 20027;
    public static final int CONFIRMATION_MDP_INCORRECTE = 20028;
    public static final int SUPPRESSION_COMPTE_IMPOSSIBLE = 20029;

    // ARTICLES - 20100 a 20199
    public static final int ARTICLE_INVALIDE = 20100;
    public static final int NO_ARTICLE_INVALIDE = 20101;
    public static final int ARTICLE_NOM_INVALIDE = 20102;
    public static final int ARTICLE_DESCRIPTION_INVALIDE = 20103;
    public static final int ARTICLE_PRIX_INITIAL_INVALIDE = 20104;
    public static final int ARTICLE_UTILISATEUR_INVALIDE = 20105;
    public static final int ARTICLE_CATEGORIE_INVALIDE = 20106;
    public static final int ARTICLE_NOM_ERREUR = 20107;
    public static final int ARTICLE_DESCRIPTION_ERREUR = 20108;
    public static final int ARTICLE_DATE_ERREUR = 20109;
    public static final int ARTICLE_PRIX_ERREUR = 20110;

    // Categorie - 20200 a 20299
    public static final int CATEGORIE_LIBELLE_NULL = 20200;
    public static final int CATEGORIE_LIBELLE_TROP_LONG = 20201;
    public static final int CATEGORIE_LIBELLE_EXISTANT = 200202;

    // Retrait - 20300 a 20399
    public static final int RETRAIT_ARTICLE_INVALIDE = 20300;
    public static final int RETRAIT_RUE_TROP_LONG = 20301;
    public static final int RETRAIT_CODE_POSTAL_TROP_LONG = 20302;
    public static final int RETRAIT_VILLE_TROP_LONGUE = 20303;
    
    // ENCHERE - 20400 a 20499
    public static final int ENCHERE_DATE_INVALIDE = 20400;
    public static final int ENCHERE_NO_ARTICLE_INVALIDE = 20401;
    public static final int ENCHERE_NO_UTILISATEUR_INVALIDE = 20402;
    public static final int ENCHERE_PROPOSITION_INFERIEURE_OU_EGALE = 20403;
    public static final int ENCHERE_UTILISATEUR_FOND_INSUFFISANT = 20404;
}
