package fr.eni.projetenchere.servlet;

import fr.eni.projetenchere.bll.ArticleManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import fr.eni.projetenchere.bll.CodeErreur;
import fr.eni.projetenchere.bll.EnchereManager;
import fr.eni.projetenchere.bll.UserManager;
import fr.eni.projetenchere.bo.Enchere;
import fr.eni.projetenchere.bo.User;
import fr.eni.projetenchere.exception.BusinessException;

@WebServlet(name = "CreditEnchere", value = "/creditenchere")
/**
 * Servlet implementation class CreditEnchere
 */
public class CreditEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/articles/detailVente.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String propositionStr = request.getParameter("proposition");
		int proposition = Integer.parseInt(propositionStr);
		//System.out.println("proposition : " + proposition);
		int enchereID = Integer.parseInt(request.getParameter("id"));
		//System.out.println("enchereID : " + enchereID);
		EnchereManager enchereManager = EnchereManager.getInstance();
		// Récupérer l'enchère


		BusinessException be = new BusinessException();
		
		try {
			Enchere enchere = enchereManager.selectEnchereByID(enchereID);
			User utilisateurConnecte = (User) request.getSession().getAttribute("utilisateurConnecte");
			boolean propositionValide = enchereManager.validerPropositionCredit(enchere, utilisateurConnecte , proposition);
			if (propositionValide) {
				System.out.println("Encher.getmontant : " + enchere.getMontantEnchere());
				System.out.println("Article.getPrixInitial : " + ArticleManager.getInstance().selectArticleByID(enchere.getNoArticle()).getPrixInitial());
				if (enchere.getMontantEnchere() > ArticleManager.getInstance().selectArticleByID(enchere.getNoArticle()).getPrixInitial()) {
					UserManager.getInstance().updateUserCredit(utilisateurConnecte, utilisateurConnecte.getCredit() - proposition);
				}



				enchereManager.updateEnchereMontant(enchere, proposition);

				System.out.println("IDutilisateurConnecte : " + utilisateurConnecte.getNoUtilisateur());
				System.out.println("IDproprietaire : " + enchere.getProprietaire().getNoUtilisateur());
				System.out.println("IDenchere : " + enchere.getNo_Enchere());


				enchereManager.updateEnchereNoProprietaire(enchere, utilisateurConnecte.getNoUtilisateur());
				request.getSession().setAttribute("derniereEnchereID", enchereID);
				response.sendRedirect(request.getContextPath() + "/detailvente" + "?id=" + enchereID);
				
				return;
			}
			
			if(proposition <= enchere.getMontantEnchere())
			{
				be.ajouterErreur(CodeErreur.ENCHERE_PROPOSITION_INFERIEURE_OU_EGALE);
				request.setAttribute("listeCodesErreur", be.getListeCodesErreur());
				throw be;
			}
			
			if(utilisateurConnecte.getCredit() < enchere.getMontantEnchere())
			{				
				be.ajouterErreur(CodeErreur.ENCHERE_UTILISATEUR_FOND_INSUFFISANT);
				request.setAttribute("listeCodesErreur", be.getListeCodesErreur());
				throw be;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BusinessException e) {
			
			String errorCode = be.getListeCodesErreur().toString();
			errorCode = errorCode.replace("[", "").replace("]", "");
			
			System.out.println("Code erreur : " + errorCode);
			
			response.sendRedirect(request.getContextPath() + "/detailvente?id=" + enchereID + "&error=" + errorCode);
			be.clearErrors();
		}
	}

}
