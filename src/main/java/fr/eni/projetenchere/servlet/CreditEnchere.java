package fr.eni.projetenchere.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import fr.eni.projetenchere.bll.EnchereManager;
import fr.eni.projetenchere.bo.Enchere;
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
		int enchereID = Integer.parseInt(request.getParameter("enchereID"));
		
			// Récupérer l'enchère
			Enchere enchere = EnchereManager.getInstance().selectEnchereByID(enchereID);
			boolean propositionValide = EnchereManager.validerPropositionCredit(enchere, encherisseur, proposition);
			
	}
	
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
