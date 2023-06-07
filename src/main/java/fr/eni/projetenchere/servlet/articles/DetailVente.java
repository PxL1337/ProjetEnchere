package fr.eni.projetenchere.servlet.articles;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import fr.eni.projetenchere.bll.ArticleManager;
import fr.eni.projetenchere.bll.EnchereManager;
import fr.eni.projetenchere.bo.ArticleVendu;
import fr.eni.projetenchere.bo.Enchere;

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
		String id = request.getParameter("id");
		EnchereManager em = EnchereManager.getInstance();
		Enchere enchere = em.selectEnchereByID(Integer.parseInt(id));
		
		try {
			String dateEnchere = enchere.g
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		RetraitManager rm = RetraitManager.getInstance();
		Retrait retrait = null;
		
		try {
			retrait = am.selectArticleByID(currentRetrait.getNoRetrait());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		ArticleManager am = ArticleManager.getInstance();
		ArticleVendu article = null;
		
		try {
			article = am.selectArticleByID(currentArticle.getNoArticle());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		User currentUser = (User) request.getSession().getAttribute("utilisateurConnecte");
		// Maintenant, utilisez cet objet User pour récupérer les informations de l'utilisateur à partir de la base de données
		
			
		User user = null;
		try {
		user = um.selectUserByID(currentUser.getNoUtilisateur());
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	// Récupérer le message de la session, si présent
	if (request.getSession().getAttribute("message") != null) {
		String message = (String) request.getSession().getAttribute("message");
		request.getSession().removeAttribute("message");
		// Ajouter le message à la requête pour qu'il puisse être utilisé dans la JSP
		request.setAttribute("message", message);
	}

		request.setAttribute("user", user);
		request.getRequestDispatcher("/WEB-INF/views/user/ShowUser.jsp").forward(request, response);
}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
