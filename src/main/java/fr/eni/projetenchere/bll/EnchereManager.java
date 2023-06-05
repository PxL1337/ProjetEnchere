package fr.eni.projetenchere.bll;

import fr.eni.projetenchere.dal.EnchereDAO;
import fr.eni.projetenchere.dal.jdbc.DAOFactory;

import java.sql.SQLException;

public class EnchereManager
{
	private static EnchereManager instance;
	private EnchereDAO enchereDAO;

	/*private EnchereManager() {
		enchereDAO = DAOFactory.getEnchereDAO();
	}*/

	public static synchronized EnchereManager getInstance() {
		if (instance == null) {
			instance = new EnchereManager();
		}
		return instance;
	}

	// Lister les enchères en mode déconnecté
	/**public List<Article> listCurrentAuctions() throws SQLException {
		return articleDAO.selectAllCurrent();
	}

	// Lister les enchères en mode connecté
	public List<Article> listUserAuctions(int userId) throws SQLException {
		return articleDAO.selectByUserId(ID);
	}*/




}
