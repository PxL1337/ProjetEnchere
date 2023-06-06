CREATE VIEW EnchereVueListe AS
SELECT av.nom_article AS NomArticle, e.montant_enchere AS MontantEnchere, av.date_fin_encheres AS DateFinEnchere, u.pseudo AS PseudoUtilisateur
FROM ENCHERES e
JOIN ARTICLES_VENDUS av ON e.no_article = av.no_article
JOIN UTILISATEURS u ON e.no_utilisateur = u.no_utilisateur
WHERE av.date_debut_encheres <= GETDATE() AND av.date_fin_encheres >= GETDATE()
