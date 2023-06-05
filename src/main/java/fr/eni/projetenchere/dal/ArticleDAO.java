package fr.eni.projetenchere.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.projetenchere.bo.ArticleVendu;

public interface ArticleDAO {
		// CRUD - Create, Read, Update, Delete
		public void insert(ArticleVendu article) throws SQLException;
		public void update(ArticleVendu article) throws SQLException;
		public void delete(ArticleVendu article) throws SQLException;
		
		// Read section
		public ArticleVendu selectByID(int ID) throws SQLException;
		public List<ArticleVendu> selectAll() throws SQLException;
}