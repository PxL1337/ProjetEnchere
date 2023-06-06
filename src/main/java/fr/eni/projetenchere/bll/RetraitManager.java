package fr.eni.projetenchere.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.projetenchere.bo.Retrait;
import fr.eni.projetenchere.dal.RetraitDAO;
import fr.eni.projetenchere.dal.jdbc.DAOFactory;
import fr.eni.projetenchere.exception.BusinessException;

public class RetraitManager 
{
	private static RetraitManager instance;

	public static RetraitManager getInstance()
	{
		if (instance == null) {
			instance = new RetraitManager();
		}
		return instance;
	}
	
	private RetraitDAO retraitDAO;
	
	private RetraitManager() {
		retraitDAO = DAOFactory.getRetraitDAO();
	}
	
	public void insertRetrait(Retrait retrait) throws SQLException
	{
		retraitDAO.insert(retrait);
	}
	
	public void updateRetrait(Retrait retrait) throws SQLException
	{
		retraitDAO.update(retrait);
	}
	
	public void deleteRetrait(Retrait retrait) throws SQLException
	{
		retraitDAO.delete(retrait);
	}
	
	public Retrait selectRetraitByID(int ID) throws SQLException, BusinessException
	{
		return retraitDAO.selectByID(ID);
	}
	
	public List<Retrait> selectAllRetraits() throws SQLException, BusinessException
	{
		return retraitDAO.selectAll();
	}
}