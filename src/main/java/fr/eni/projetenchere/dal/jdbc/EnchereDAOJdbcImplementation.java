package fr.eni.projetenchere.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import fr.eni.projetenchere.bll.ArticleManager;
import fr.eni.projetenchere.bll.UserManager;
import fr.eni.projetenchere.bo.Enchere;
import fr.eni.projetenchere.dal.ConnectionProvider;
import fr.eni.projetenchere.dal.EnchereDAO;

public class EnchereDAOJdbcImplementation implements EnchereDAO {
	
	
	final String INSERT_ENCHERE = "INSERT INTO ENCHERES(date_enchere, montant_enchere, no_article, no_utilisateur) VALUES(?, ?, ?, ?)";
	final String UPDATE_MONTANT_ENCHERE = "UPDATE ENCHERES SET montant_enchere=? WHERE no_article=?";
	final String DELETE_ENCHERE = "DELETE FROM ENCHERES WHERE no_article=?";
	final String SELECT_ENCHERE_BY_ID = "SELECT * FROM ENCHERES WHERE no_article=?";
	final String SELECT_ALL_ENCHERES = "SELECT * FROM ENCHERES";
	
	@Override
	public void insert(Enchere enchere) throws SQLException 
	{
		try (Connection connection = ConnectionProvider.getConnection();
				 PreparedStatement preparedStatement = 
						 connection.prepareStatement(INSERT_ENCHERE, PreparedStatement.RETURN_GENERATED_KEYS)) 
		{
				preparedStatement.setDate(1, enchere.getDateEnchere());
				preparedStatement.setInt(2, enchere.getMontantEnchere());
				preparedStatement.setInt(3, enchere.getNoArticle());
				preparedStatement.setInt(4, enchere.getNoEncherisseur());
				
				int rowsAffected = preparedStatement.executeUpdate();
				
				if(rowsAffected > 0) 
				{
					try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys();)
					{
						if (generatedKeys.next())
						{
							int nouvelId = generatedKeys.getInt(1);
							System.out.println("Nouvel Id : " + nouvelId);
							
							enchere.setNo_Enchere(nouvelId);
							System.out.println("Nouvelle enchere cree avec l'identifiant : " + nouvelId);
						}							
					}
					finally{
						System.out.println(enchere.toString() + " in insertion");
					}
				}
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
	}

	@Override
	public void updateMontantEnchere(Enchere enchere, int montant) throws SQLException {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MONTANT_ENCHERE);)
		{
			preparedStatement.setInt(1, montant);
			//WHERE
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
				enchere = mapAllEnchereData(resultSet);
				
			}
		}
		
		System.out.println("Enchere found with ID [ " + ID + " ]" + enchere.toString());
		return enchere;
	}

	@Override
	public List<Enchere> selectAll() throws SQLException {
		List<Enchere> encheres = new ArrayList<Enchere>();
		try (Connection connection = ConnectionProvider.getConnection();
				Statement statement = connection.createStatement();) {
			ResultSet resultSet = statement.executeQuery(SELECT_ALL_ENCHERES);

			Enchere enchere = null;
			while (resultSet.next()) {

				enchere = mapAllEnchereData(resultSet);
				encheres.add(enchere);
				System.out.println("Found enchere : " + enchere.toString());
			}
			//methode que je ne comprends pas j'ai juste pas trouvé de solutions alternatives donc à tester 
			Collections.sort(encheres, new Comparator<Enchere>() {
		            @Override
		            public int compare(Enchere enchere1, Enchere enchere2) {
		                return enchere1.getDateEnchere().compareTo(enchere2.getDateEnchere());
		            }
		        });
			return encheres;
		}
	}
	
	
	private Enchere mapAllEnchereData(ResultSet resultSet) throws SQLException 
	{
		Enchere enchere = new Enchere();
		
		enchere.setDateEnchere(resultSet.getDate("date_enchere"));
		enchere.setMontantEnchere(resultSet.getInt("montant_enchere"));
		enchere.setNoArticle(
				ArticleManager.getInstance().selectArticleByID(resultSet.getInt("no_article")).getNoArticle());
		enchere.setNoEncherisseur(
				UserManager.getInstance().selectUserByID(resultSet.getInt("no_utilisateur")).getNoUtilisateur());
		
		return enchere;
	}
}
