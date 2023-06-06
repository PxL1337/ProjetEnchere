package fr.eni.projetenchere.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetenchere.bo.Retrait;
import fr.eni.projetenchere.dal.ConnectionProvider;
import fr.eni.projetenchere.dal.RetraitDAO;
import fr.eni.projetenchere.exception.BusinessException;

public class RetraitDAOJdbcImplementation implements RetraitDAO {

	final String INSERT_RETRAIT = "INSERT INTO RETRAITS (no_article, rue, code_postal, ville) VALUES (?, ?, ?, ?)";
	
	final String UPDATE_RETRAIT = "UPDATE RETRAITS SET rue=?, code_postal=?, ville=? WHERE no_article=?";
	
	final String SELECT_RETRAIT_BY_ID = "SELECT * FROM RETRAITS WHERE no_article=?";
	final String SELECT_ALL_RETRAITS = "SELECT * FROM RETRAITS";
	
	final String DELETE_RETRAIT = "DELETE FROM RETRAITS WHERE no_article=?";

	@Override
	public void insert(Retrait retrait) throws SQLException 
	{
		try(Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(
						INSERT_RETRAIT, PreparedStatement.RETURN_GENERATED_KEYS)) 
		{
				preparedStatement.setInt(1, retrait.getNoArticle());
	            preparedStatement.setString(2, retrait.getRue());
	            preparedStatement.setString(3, retrait.getCodePostal());
	            preparedStatement.setString(4, retrait.getVille());
	            
				preparedStatement.executeUpdate();

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
	}

	@Override
	public void update(Retrait retrait) throws SQLException {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement = 
						connection.prepareStatement(UPDATE_RETRAIT);)
		{
			preparedStatement.setString(1, retrait.getRue());
            preparedStatement.setString(2, retrait.getCodePostal());
            preparedStatement.setString(3, retrait.getVille());

			// WHERE
			preparedStatement.setInt(4, retrait.getNoArticle());

			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Retrait retrait) throws SQLException {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement = 
						connection.prepareStatement(DELETE_RETRAIT);) 
		{
			//WHERE
			preparedStatement.setInt(1, retrait.getNoArticle());
			preparedStatement.executeUpdate();
		}
		
	}

	@Override
	public Retrait selectByID(int ID) throws SQLException, BusinessException 
	{
		Retrait retrait = null;

		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement = 
						connection.prepareStatement(SELECT_RETRAIT_BY_ID);)
		{
			//WHERE
			preparedStatement.setInt(1, ID);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				retrait = mapAllRetraitData(resultSet);
			}
		}

		System.out.println(
				"User found with ID [ " + ID + " ]" + retrait.toString());
		return retrait;
	}

	@Override
	public List<Retrait> selectAll() throws SQLException, BusinessException {
		List<Retrait> retraits = new ArrayList<Retrait>();

		try (Connection connection = ConnectionProvider.getConnection();
				Statement statement = connection.createStatement();) 
		{
			ResultSet resultSet = statement.executeQuery(SELECT_ALL_RETRAITS);

			Retrait retrait = null;
			while (resultSet.next()) {
				retrait = mapAllRetraitData(resultSet);
				retraits.add(retrait);
				System.out.println("Found user : " + retrait.toString());
			}
		}

		return retraits;
	}

	private Retrait mapAllRetraitData(ResultSet rs) throws SQLException, BusinessException {
		Retrait retrait = new Retrait();

		retrait.setNoArticle(rs.getInt("no_article"));
		retrait.setRue(rs.getString("rue"));
		retrait.setCodePostal(rs.getString("code_postal"));
		retrait.setVille(rs.getString("ville"));
		
		return retrait;
	}
}