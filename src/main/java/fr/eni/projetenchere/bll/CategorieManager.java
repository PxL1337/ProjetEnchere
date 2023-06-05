package fr.eni.projetenchere.bll;

import fr.eni.projetenchere.dal.CategorieDAO;

public class CategorieManager
{
	private static CategorieManager instance;
	private CategorieDAO categorieDAO;
	/**private CategorieManager() {
		categorieDAO = DAOFactory.getCategorieDAO();
	}*/

	public static CategorieManager getInstance()
	{
		if (instance == null) {
			instance = new CategorieManager();
		}
		
		return instance;
	}
	



}
