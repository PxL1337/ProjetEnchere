CREATE VIEW VueArticles AS
SELECT 
    AV.no_article,
    AV.nom_article,
    AV.description,
    AV.date_debut_encheres,
    AV.date_fin_encheres,
    AV.prix_initial,
    AV.prix_vente,
    U.no_utilisateur AS no_utilisateur,
    U.pseudo AS pseudo_utilisateur,
    U.email AS email_utilisateur,
    C.libelle AS libelle_categorie
FROM
    ARTICLES_VENDUS AV
    INNER JOIN UTILISATEURS U ON AV.no_utilisateur = U.no_utilisateur
    INNER JOIN CATEGORIES C ON AV.no_categorie = C.no_categorie;

