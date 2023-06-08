package fr.eni.projetenchere.servlet;

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

@WebServlet(name = "DetailVente", value = "/detailvente")
/**
 * Servlet implementation class CreditEnchere
 */
public class CreditEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String propositionStr = request.getParameter("proposition");
		int proposition = Integer.parseInt(propositionStr);
		int enchereID = Integer.parseInt(request.getParameter("id"));
		EnchereManager enchereManager = EnchereManager.getInstance();
			// Récupérer l'enchère
			
			try {
				Enchere enchere = enchereManager.selectEnchereByID(enchereID);
				User encherisseur = UserManager.getInstance().selectUserByID(enchereID);
				boolean propositionValide = enchereManager.validerPropositionCredit(enchere, encherisseur , proposition);
				if (propositionValide) {
					UserManager.getInstance().updateUserCredit(encherisseur, encherisseur.getCredit() - proposition);
					enchereManager.updateEnchereMontant(enchere, proposition);
					enchereManager.updateNoProprietaire(encherisseur.getNoUtilisateur());
				}else {
					BusinessException be = new BusinessException();
					be.ajouterErreur(CodeErreur.ENCHERE_TRANSACTION_INVALIDE);
					throw be;
					request.getRequestDispatcher("/detailvente").forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
