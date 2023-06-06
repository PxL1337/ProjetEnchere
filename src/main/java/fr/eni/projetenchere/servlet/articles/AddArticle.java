package fr.eni.projetenchere.servlet.articles;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;

import fr.eni.projetenchere.bll.ArticleManager;
import fr.eni.projetenchere.bll.CategorieManager;
import fr.eni.projetenchere.bll.UserManager;
import fr.eni.projetenchere.bll.utils.DateUtils;
import fr.eni.projetenchere.bo.ArticleVendu;
import fr.eni.projetenchere.bo.Categorie;
import fr.eni.projetenchere.bo.User;
import fr.eni.projetenchere.dal.UserDAO;
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

        System.out.println(nomArticle + "dans le servlet");
        System.out.println(description + "dans le servlet");
        System.out.println(miseAPrix + "dans le servlet");
        System.out.println(dateDebutEncheres + "dans le servlet");
        System.out.println(dateFinEncheres + "dans le servlet");
        System.out.println(rue + "dans le servlet");
        System.out.println(codePostal + "dans le servlet");
        System.out.println(ville + "dans le servlet");
        System.out.println(categorie + "dans le servlet");

        // Récupérer l'ID de l'utilisateur à partir de la session
        HttpSession session = request.getSession();
        User utilisateurConnecte = (User) session.getAttribute("utilisateurConnecte");
        int idUtilisateur = utilisateurConnecte.getNoUtilisateur();

        ArticleManager articleManager = ArticleManager.getInstance();


    }
}