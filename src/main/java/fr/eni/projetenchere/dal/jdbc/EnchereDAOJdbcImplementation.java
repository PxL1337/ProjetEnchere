package fr.eni.projetenchere.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.eni.projetenchere.bll.ArticleManager;
import fr.eni.projetenchere.bll.UserManager;
import fr.eni.projetenchere.bo.Enchere;
import fr.eni.projetenchere.dal.ConnectionProvider;
import fr.eni.projetenchere.dal.EnchereDAO;

public class EnchereDAOJdbcImplementation implements EnchereDAO {
	
	
	final String INSERT_ENCHERE = "INSERT INTO ENCHERES(no_utilisateur, no_article, date_enchere, montant_enchere) VALUES( ?, ?, ?, ?)";
	final String UPDATE_MONTANT_ENCHERE = "UPDATE ENCHERES SET montant_enchere=? WHERE no_article=?";
	final String DELETE_ENCHERE = "DELETE FROM ENCHERES WHERE no_article=?";
	final String SELECT_ENCHERE_BY_ID = "SELECT * FROM ENCHERES WHERE no_article=?";
	
	
	@Override
	public void insert(Enchere enchere) throws SQLException {
		try (Connection connection = ConnectionProvider.getConnection();
				 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ENCHERE)) 
		{
				preparedStatement.setInt(1, enchere.getEncherisseur().getNoUtilisateur());
				preparedStatement.setInt(2, enchere.getNoArticle());
				preparedStatement.setDate(3, enchere.getDateEnchere());
				preparedStatement.setInt(4, enchere.getMontantEnchere());
				

				preparedStatement.executeUpdate();
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
	}

	@Override
	public void update(Enchere enchere) throws SQLException {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MONTANT_ENCHERE);){
			preparedStatement.setInt(1, enchere.getMontantEnchere());
			preparedStatement.setInt(2, enchere.getNoArticle());
			
			preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Enchere enchere) throws SQLException {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement =
						connection.prepareStatement(DELETE_ENCHERE);)
		{
			preparedStatement.setInt(1, enchere.getNoArticle());
			preparedStatement.executeUpdate();
		}	
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Enchere selectByID(int ID) throws SQLException {
		Enchere enchere = null;
		
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement =
						connection.prepareStatement(SELECT_ENCHERE_BY_ID);)
		{
			preparedStatement.setInt(1, ID);
			
			ResultSet resultSet = preparedStatement.executeQuery();			
			if (resultSet.next()) 
			{
				enchere.setArticle(ArticleManager.getInstance().selectArticleByID(resultSet.getInt("no_article")));
				enchere.setEncherisseur(UserManager.getInstance().selectUserByID(resultSet.getInt("no_article")));
				enchere.setDateEnchere(resultSet.getDate("date_enchere"));
				enchere.setMontantEnchere(resultSet.getInt("montant_enchere"));
				
			}
		}
		
		System.out.println("Enchere found with ID [ " + ID + " ]" + enchere.toString());
		return enchere;
	}

	@Override
	public List<Enchere> selectAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	/* private Enchere mapAllEnchereData(ResultSet rs, int ID) throws SQLException {
	 
	
		Enchere enchere = new Enchere();
		
		enchere.set(rs.getInt("no_utilisateur"));
		enchere.setNomArticle(rs.getString("no_article"));
		enchere.setDateDebutEncheres(rs.getDate("date_enchere"));
		enchere.setDateFinEncheres(rs.getDate("montant_enchere"));
		
		return enchere;
	}*/
}
