package fr.eni.projetenchere.bll;

import fr.eni.projetenchere.dal.RetraitDAO;
import fr.eni.projetenchere.dal.jdbc.DAOFactory;

public class RetraitManager {
	private static RetraitManager instance;
	private RetraitDAO retraitDAO;

	/*private RetraitManager() {
		retraitDAO = DAOFactory.getRetraitDAO();
	}*/

	public static synchronized RetraitManager getInstance() {
		if (instance == null) {
			instance = new RetraitManager();
		}
		return instance;
	}
}