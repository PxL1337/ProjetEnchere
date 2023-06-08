package fr.eni.projetenchere.servlet.articles;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import fr.eni.projetenchere.bll.ArticleManager;
import fr.eni.projetenchere.bll.CodeErreur;
import fr.eni.projetenchere.bll.EnchereManager;
import fr.eni.projetenchere.bll.RetraitManager;
import fr.eni.projetenchere.bll.UserManager;
import fr.eni.projetenchere.bo.ArticleVendu;
import fr.eni.projetenchere.bo.Enchere;
import fr.eni.projetenchere.bo.Retrait;
import fr.eni.projetenchere.bo.User;
import fr.eni.projetenchere.exception.BusinessException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateArticle
 */
@WebServlet(name = "UpdateArticle", value = "/UpdateArticle")
public class UpdateArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int enchereID = Integer.parseInt(request.getParameter("id"));
		Enchere enchere;
		String referer = request.getHeader("Referer");
		try {
			enchere = EnchereManager.getInstance().selectEnchereByID(enchereID);
			if (enchere.getProprietaire() == (User) request.getSession().getAttribute("utilisateurConnecte")) {
				BusinessException be = new BusinessException();
				be.ajouterErreur(CodeErreur.ARTICLE_UTILISATEUR_INVALIDE);
				if (referer != null && !referer.isEmpty()) {
		            response.sendRedirect(referer);
		        } else {
		            response.sendRedirect("/accueil");
		        }
				throw be;

			}else {
				request.getRequestDispatcher("/WEB-INF/views/articles/ModifierVente.jsp").forward(request, response);
			}
			
			
		} catch (SQLException | BusinessException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int enchereID  = Integer.parseInt(request.getParameter("id"));
			
		String nomArticle = request.getParameter("nomArticle");
	        String description = request.getParameter("description");
	        int categorieId = Integer.parseInt(request.getParameter("categorie"));
	        int miseAPrix = Integer.parseInt(request.getParameter("miseAPrix"));
	        LocalDate dateDebutEncheres = LocalDate.parse(request.getParameter("dateDebutEncheres"));
	        LocalDate dateFinEncheres = LocalDate.parse(request.getParameter("dateFinEncheres"));
	        String rue = request.getParameter("rue");
	        String codePostal = request.getParameter("codePostal");
	        String ville = request.getParameter("ville");
	        
	        
	        Enchere enchere;
	        ArticleVendu article;
	        User user;
	        Retrait retrait;
	        try {
				enchere = EnchereManager.getInstance().selectEnchereByID(enchereID);
				enchere.getArticle().setNomArticle(nomArticle);
				enchere.getArticle().setDescription(description);
				enchere.getArticle().setDateDebutEncheres(Date.valueOf(dateDebutEncheres));
				enchere.getArticle().setDateFinEncheres(Date.valueOf(dateFinEncheres));
				enchere.getProprietaire().setRue(rue);
				enchere.getProprietaire().setCodePostal(codePostal);
				enchere.getProprietaire().setVille(ville);
				
				
				article = enchere.getArticle();
				user = enchere.getProprietaire();
				retrait = new Retrait(article.getNoArticle(),rue,codePostal,ville);
				
				EnchereManager.getInstance().updateEnchereMontant(enchere, miseAPrix);
				ArticleManager.getInstance().update(article);
				RetraitManager.getInstance().updateRetrait(retrait);
				UserManager.getInstance().updateUser(user);
				request.getRequestDispatcher("/WEB-INF/views/articles/detailVente.jsp").forward(request, response);
				
			} catch (SQLException | BusinessException e) {
				e.printStackTrace();
			}
	
	        		
	       /*public Retrait(int noArticle, String rue, String codePostal, String ville, int articleID);*/
	}

}
