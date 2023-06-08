package fr.eni.projetenchere.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.projetenchere.bo.Enchere;

public interface EnchereDAO {
	
	// CRUD - Create, Read, Update, Delete
	public void insert(Enchere enchere) throws SQLException;
	public void updateMontantEnchere(Enchere enchere, int montant) throws SQLException;
	public void delete(Enchere enchere) throws SQLException;
	
	// Read section
	public Enchere selectByID(int ID) throws SQLException;
	public List<Enchere> selectAll() throws SQLException;
	
	public List<Enchere> selectAllFiltredByName(String nameFilter) throws SQLException;
	public List<Enchere> selectAllFiltredByCategory(int categoryID) throws SQLException;
}
