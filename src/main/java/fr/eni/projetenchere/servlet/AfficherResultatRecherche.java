package fr.eni.projetenchere.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetenchere.bll.CategorieManager;
import fr.eni.projetenchere.bll.EnchereManager;
import fr.eni.projetenchere.bo.Categorie;
import fr.eni.projetenchere.bo.Enchere;

/**
 * Servlet implementation class AfficherResultatRecherche
 */
@WebServlet(name = "ResultatRecherche", value = "/recherche")
public class AfficherResultatRecherche extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<Enchere> encheres = new ArrayList<Enchere>();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String categorieFromAPost = request.getParameter("categorie");
			String filterFromAPost = request.getParameter("filter");
			
			System.out.println("CATEGORIE CHOISIE : " + categorieFromAPost);
			System.out.println("FILTRE RENSEIGNE : " + filterFromAPost);
			
			if (categorieFromAPost != null && !categorieFromAPost.isEmpty() ) {
				request.setAttribute(
						"categorie", 
						CategorieManager.getInstance().selectCategorieByID(Integer.parseInt(categorieFromAPost)).getLibelle());
			}
			request.setAttribute("filter", filterFromAPost);
			
			EnchereManager enchereManager = EnchereManager.getInstance();
			
			// Traitement par catégorie
			if (categorieFromAPost != null)
			{ 
				encheres = enchereManager.selectAllEncheresFiltredByCategory(Integer.parseInt(categorieFromAPost));
			}
			// Traitement par nom
			else { encheres = enchereManager.selectAllEncheresFiltredByName(filterFromAPost); }
			
			// Traitement double > categorie + nom
			if (categorieFromAPost != null && !filterFromAPost.isEmpty()) {
				encheres = enchereManager.selectAllEncheresFiltredByCategory(Integer.parseInt(categorieFromAPost));
				System.out.println("ENCHERES TROUVEES DANS LE TRAITEMENT PAR CATEGORIE : " + encheres);
				
				encheres = enchereManager.getEncheresByNameFromCateforyFilter(filterFromAPost, encheres);
				System.out.println("ENCHERES TROUVEES DANS LE DOUBLE TRAITEMENT : " + encheres);
			}			
			
			request.setAttribute("encheres", encheres);
			
			request.getRequestDispatcher("/WEB-INF/views/recherche.jsp").forward(request, response);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		System.out.println("ENCHERES DANS REQUETE " + request.getAttribute("encheres") +" DANS LE DOPOST");
	}

}
