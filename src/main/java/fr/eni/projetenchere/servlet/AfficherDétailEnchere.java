package fr.eni.projetenchere.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import fr.eni.projetenchere.bll.ArticleManager;
import fr.eni.projetenchere.bo.ArticleVendu;

/**
 * Servlet implementation class DétailEnchere
 */
@WebServlet(name = "DétailEnchere", value = "/Enchere")
public class AfficherDétailEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleVendu currentArticleVendu = (ArticleVendu) request.getSession().getAttribute("currentArticleVendu");
		// Maintenant, utilisez cet objet User pour récupérer les informations de l'utilisateur à partir de la base de données
		ArticleManager am = ArticleManager.getInstance();
		ArticleVendu articleVendu = null;
		
		try {
		    // Récupération des informations de l'article depuis la base de données
		    articleVendu = am.selectArticleByID(currentArticleVendu.getNoArticle());
		    request.setAttribute("articleVendu", articleVendu);

			} catch (SQLException e) {
			    e.printStackTrace();
			}
		
		
		
		// Récupérer le message de la session, si présent
				if (request.getSession().getAttribute("message") != null) {
					String message = (String) request.getSession().getAttribute("message");
					request.getSession().removeAttribute("message");
					// Ajouter le message à la requête pour qu'il puisse être utilisé dans la JSP
					request.setAttribute("message", message);
				}

					request.setAttribute("articleVendu", articleVendu);
					
					//Changer le getRequestDispatcher
					request.getRequestDispatcher("/WEB-INF/views/user/ShowUser.jsp").forward(request, response);
			}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
