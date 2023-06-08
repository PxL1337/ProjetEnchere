package fr.eni.projetenchere.servlet.articles;

import fr.eni.projetenchere.bll.*;
import fr.eni.projetenchere.bo.*;
import fr.eni.projetenchere.exception.BusinessException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DetailVente", value = "/detailvente")
/**
 * Servlet implementation class DetailVente
 */
public class DetailVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int enchereID = Integer.parseInt(request.getParameter("id"));

		System.out.println("id de l'enchère = " + enchereID);

		try {
			// Récupérer l'enchère
			Enchere enchere = EnchereManager.getInstance().selectEnchereByID(enchereID);
			System.out.println("enchère = " + enchere);

			// Récupérer l'article associé à l'enchère
			ArticleVendu article = ArticleManager.getInstance().selectArticleByID(enchere.getNoArticle());

			// Récupérer la catégorie associée à l'article
			Categorie categorie = CategorieManager.getInstance().selectCategorieByID(article.getNoCategorie());

			Retrait retrait;
			try {
				retrait = RetraitManager.getInstance().selectRetraitByID(article.getNoArticle());
			} catch (BusinessException e) {
				throw new RuntimeException(e);
			}


			// Récupérer l'utilisateur associé à l'enchère
			User user = UserManager.getInstance().selectUserByID(enchere.getNoProprietaire());

			// Passer l'enchère, l'article, la catégorie et l'utilisateur à la JSP
			request.setAttribute("enchere", enchere);
			request.setAttribute("article", article);
			request.setAttribute("categorie", categorie);
			request.setAttribute("retrait", retrait);
			request.setAttribute("user", user);

			System.out.println("USer = " + user.getPseudo());

			request.getRequestDispatcher("/WEB-INF/views/articles/detailVente.jsp").forward(request, response);
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
