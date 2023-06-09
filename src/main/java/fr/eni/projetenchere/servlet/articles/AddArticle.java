package fr.eni.projetenchere.servlet.articles;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.eni.projetenchere.bll.ArticleManager;
import fr.eni.projetenchere.bll.CategorieManager;
import fr.eni.projetenchere.bll.EnchereManager;
import fr.eni.projetenchere.bll.RetraitManager;
import fr.eni.projetenchere.bll.UserManager;
import fr.eni.projetenchere.bo.ArticleVendu;
import fr.eni.projetenchere.bo.Categorie;
import fr.eni.projetenchere.bo.Enchere;
import fr.eni.projetenchere.bo.Retrait;
import fr.eni.projetenchere.bo.User;
import fr.eni.projetenchere.exception.BusinessException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "AddArticle", value = "/AddArticle")
public class AddArticle extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer l'ID de l'utilisateur à partir de la session
        HttpSession session = request.getSession();
        User utilisateurConnecte = (User) session.getAttribute("utilisateurConnecte");
        int idUtilisateur = utilisateurConnecte.getNoUtilisateur();

        // Utiliser UserManager pour obtenir les informations de l'utilisateur
        UserManager userManager = UserManager.getInstance();
        User user = null;
        try {
            user = userManager.selectUserByID(idUtilisateur);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Utiliser CategorieManager pour obtenir les catégories
        CategorieManager categorieManager = CategorieManager.getInstance();
        List<Categorie> categories = null;
        try {
            categories = categorieManager.selectAllCategorie();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Obtenir la date du jour et la date du lendemain
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);

        // Stocker les informations de l'utilisateur, les catégories et les dates comme attributs de la requête
        request.setAttribute("user", user);
        request.setAttribute("categories", categories);
        request.setAttribute("dateDebut", today);
        request.setAttribute("dateFin", tomorrow);

        // Transférer la requête à la JSP
        request.getRequestDispatcher("/WEB-INF/views/articles/creaVente.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Recuperer les donnees du formulaire
        String nomArticle = request.getParameter("nomArticle");
        String description = request.getParameter("description");
        int categorieId = Integer.parseInt(request.getParameter("categorie"));
        int miseAPrix = Integer.parseInt(request.getParameter("miseAPrix"));
        LocalDate dateDebutEncheres = LocalDate.parse(request.getParameter("dateDebutEncheres"));
        LocalDate dateFinEncheres = LocalDate.parse(request.getParameter("dateFinEncheres"));
        String rue = request.getParameter("rue");
        String codePostal = request.getParameter("codePostal");
        String ville = request.getParameter("ville");

        // Stocker les champs du formulaire dans une Map
        Map<String, String> formulaire = new HashMap<>();
        formulaire.put("nomArticle", nomArticle);
        formulaire.put("description", description);
        formulaire.put("categorie", request.getParameter("categorie"));
        formulaire.put("miseAPrix", request.getParameter("miseAPrix"));
        formulaire.put("dateDebutEncheres", request.getParameter("dateDebutEncheres"));
        formulaire.put("dateFinEncheres", request.getParameter("dateFinEncheres"));
        formulaire.put("rue", rue);
        formulaire.put("codePostal", codePostal);
        formulaire.put("ville", ville);

        // Récupérer l'ID de l'utilisateur à partir de la session
        HttpSession session = request.getSession();
        User utilisateurConnecte = (User) session.getAttribute("utilisateurConnecte");
        int idUtilisateur = utilisateurConnecte.getNoUtilisateur();

        // Récupérer les informations de l'utilisateur
        CategorieManager categorieManager = CategorieManager.getInstance();
        Categorie categorie = null;
        try {
            categorie = categorieManager.selectCategorieByID(categorieId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Créer un objet ArticleVendu
        ArticleVendu article = new ArticleVendu();
        article.setNomArticle(nomArticle);
        article.setDescription(description);
        article.setCategorie(categorie);
        article.setPrixInitial(miseAPrix);
        article.setPrixVente(miseAPrix);
        article.setDateDebutEncheres(Date.valueOf(dateDebutEncheres));
        article.setDateFinEncheres(Date.valueOf(dateFinEncheres));
        article.setUtilisateur(utilisateurConnecte);


        ArticleManager articleManager = ArticleManager.getInstance();
        // Valider l'article
        try {
            articleManager.validateArticle(article);
        } catch (BusinessException e) {
            request.setAttribute("article", article);
            request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
            request.setAttribute("formulaire", formulaire);
            request.getRequestDispatcher("/WEB-INF/views/articles/creaVente.jsp").forward(request, response);
            return;
        }

        // Ajouter l'article à la base de données
        try {
            articleManager.insert(article);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Retrait retrait = new Retrait();
        RetraitManager retraitManager = RetraitManager.getInstance();

        try {
            retrait.setNoArticle(article.getNoArticle());
            retrait.setRue(rue);
            retrait.setCodePostal(codePostal);
            retrait.setVille(ville);
            retraitManager.insertRetrait(retrait);
        } catch (BusinessException e) {
            request.setAttribute("retrait", retrait);
            request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
            request.getRequestDispatcher("/WEB-INF/views/articles/creaVente.jsp").forward(request, response);
            return;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        Enchere enchere = new Enchere();
        EnchereManager enchereManager = EnchereManager.getInstance();       
        
        try {
        	enchere.setDateEnchere(Date.valueOf(dateDebutEncheres));
            enchere.setMontantEnchere(miseAPrix);
            enchere.setNoArticle(article.getNoArticle());
            enchere.setNoProprietaire(utilisateurConnecte.getNoUtilisateur());
            
			enchereManager.insertEnchere(enchere);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        System.out.println("Context Path: " + request.getContextPath());
        request.getSession().setAttribute("message", "L'enchère a été créée avec succès");
        response.sendRedirect(request.getContextPath() + "/");
    }

}

