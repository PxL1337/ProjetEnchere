package fr.eni.projetenchere.servlet.articles;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import fr.eni.projetenchere.bll.ArticleManager;
import fr.eni.projetenchere.bll.CategorieManager;
import fr.eni.projetenchere.bll.UserManager;
import fr.eni.projetenchere.bo.ArticleVendu;
import fr.eni.projetenchere.bo.Categorie;
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Recuperer les donnees du formulaire
        String nomArticle = request.getParameter("nomArticle");
        String description = request.getParameter("description");
        String categorie = request.getParameter("categorie");
        String miseAPrix = request.getParameter("miseAPrix");
        String dateDebutEncheres = request.getParameter("dateDebutEncheres");
        String dateFinEncheres = request.getParameter("dateFinEncheres");
        String rue = request.getParameter("rue");
        String codePostal = request.getParameter("codePostal");
        String ville = request.getParameter("ville");

        // Récupérer l'ID de l'utilisateur à partir de la session
        HttpSession session = request.getSession();
        User utilisateurConnecte = (User) session.getAttribute("utilisateurConnecte");
        int idUtilisateur = utilisateurConnecte.getNoUtilisateur();

        ArticleManager articleManager = ArticleManager.getInstance();

        try {
            ArticleVendu article = articleManager.createdArticle(nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix, idUtilisateur, categorie);
            articleManager.validateArticle(article);
            articleManager.insert(article);

            // Rediriger vers la page d'accueil en cas de succès
            response.sendRedirect(request.getContextPath() + "/");
        } catch (BusinessException e) {
            // Utiliser UserManager pour obtenir les informations de l'utilisateur
            UserManager userManager = UserManager.getInstance();
            User user = null;
            try {
                user = userManager.selectUserByID(idUtilisateur);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            // Utiliser CategorieManager pour obtenir les catégories
            CategorieManager categorieManager = CategorieManager.getInstance();
            List<Categorie> categories = null;
            try {
                categories = categorieManager.selectAllCategorie();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            // Stocker les informations de l'utilisateur et les catégories comme attributs de la requête
            request.setAttribute("user", user);
            request.setAttribute("categories", categories);

            request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
            request.getRequestDispatcher("/WEB-INF/views/articles/creaVente.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}

