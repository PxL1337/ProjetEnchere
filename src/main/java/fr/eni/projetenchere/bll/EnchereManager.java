package fr.eni.projetenchere.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.projetenchere.bo.Enchere;
import fr.eni.projetenchere.dal.EnchereDAO;
import fr.eni.projetenchere.dal.jdbc.DAOFactory;
import fr.eni.projetenchere.exception.BusinessException;

public class EnchereManager
{
	private static EnchereManager instance;

	public static EnchereManager getInstance() {
		if (instance == null) {
			instance = new EnchereManager();
		}
		return instance;
	}

	private EnchereDAO enchereDAO;
	private BusinessException businessException;
	
	private EnchereManager() 
	{
		enchereDAO = DAOFactory.getEnchereDAO();
		businessException = new BusinessException();
	}
	
	public void insertEnchere(Enchere enchere) throws SQLException
	{
		enchereDAO.insert(enchere);
	}
	
	public void updateEnchereMontant(Enchere enchere, int montant) throws SQLException
	{
		enchereDAO.updateMontantEnchere(enchere, montant);
	}
	
	public void deleteEnchere(Enchere enchere) throws SQLException
	{
		enchereDAO.delete(enchere);
	}
	
	// Lister les enchères en mode déconnecté
	public Enchere selectEnchereByID(int ID) throws SQLException {
		return enchereDAO.selectByID(ID);
	}

	// Lister les enchères en mode connecté
	public List<Enchere> selectAllEncheres(int userId) throws SQLException {
		return enchereDAO.selectAll();
	}
}