package fr.eni.projetenchere.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import fr.eni.projetenchere.bll.EnchereManager;
import fr.eni.projetenchere.bo.Enchere;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AfficherLesEncheres
 */
@WebServlet(name = "pageAccueil", value = "/")
public class AfficherLesEncheres extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Enchere> encheres = EnchereManager.getInstance().selectAllEncheres();
			request.setAttribute("encheres", encheres);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
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
	}

}
