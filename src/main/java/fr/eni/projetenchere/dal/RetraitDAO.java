package fr.eni.projetenchere.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.projetenchere.bo.Retrait;
import fr.eni.projetenchere.exception.BusinessException;

public interface RetraitDAO {
	
	// CRUD - Create, Read, Update, Delete
		public void insert(Retrait retrait) throws SQLException;
		public void update(Retrait retrait) throws SQLException;
		public void delete(Retrait retrait) throws SQLException;
		
		// Read section
		public Retrait selectByID(int ID) throws SQLException, BusinessException;
		public List<Retrait> selectAll() throws SQLException, BusinessException;
}