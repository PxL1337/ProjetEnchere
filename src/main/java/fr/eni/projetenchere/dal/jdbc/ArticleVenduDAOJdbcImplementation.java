package fr.eni.projetenchere.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetenchere.bo.ArticleVendu;
import fr.eni.projetenchere.dal.ArticleDAO;
import fr.eni.projetenchere.dal.ConnectionProvider;

public class ArticleVenduDAOJdbcImplementation implements ArticleDAO
{
	final String INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente) VALUES( ?, ?, ?, ?, ?, ? )";
	
	final String SELECT_ALL_ARTICLES = "SELECT * FROM VueArticles";
	final String SELECT_ARTICLE_BY_ID = "SELECT * FROM VueArticles WHERE no_article=?";
	final String SELECT_ARTICLE_BY_NAME = "SELECT * FROM VueArticles WHERE nom_article=?";

	// + UTILISER LA VUE POUR LES AUTRES SELECT
	
	final String UPDATE_ARTICLE = "UPDATE ARTICLES_VENDUS SET nom_article=?, description=?, date_debut_encheres=?, date_fin_encheres=?, prix_initial=?, prix_vente=? WHERE no_article=?";
	
	final String DELETE_ARTICLE_BY_ID = "DELETE FROM ARTICLES_VENDUS WHERE no_article=?";
	
	@Override
	public void insert(ArticleVendu article) throws SQLException {
		try (Connection connection = ConnectionProvider.getConnection();
				 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS)) 
		{
				preparedStatement.setString(1, article.getNomArticle());
				preparedStatement.setString(2, article.getDescription());
				preparedStatement.setDate(3, article.getDateDebutEncheres());
				preparedStatement.setDate(4, article.getDateFinEncheres());
				preparedStatement.setInt(5, article.getPrixInitial());
				preparedStatement.setInt(6, article.getPrixVente());

				// Exécuter l'instruction SQL
				if (preparedStatement.executeUpdate() > 0) {
					ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
					if (generatedKeys.next()) {
						int nouvelId = generatedKeys.getInt(1);
						System.out.println("Nouvel utilisateur ajouté avec l'identifiant : " + nouvelId);
					}
				}
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
	}

	@Override
	public void update(ArticleVendu article) throws SQLException {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement =
						connection.prepareStatement(UPDATE_ARTICLE);)
		{
			preparedStatement.setString(1, article.getNomArticle());
			preparedStatement.setString(2, article.getDescription());
			preparedStatement.setDate(3, article.getDateDebutEncheres());
			preparedStatement.setDate(4, article.getDateFinEncheres());
			preparedStatement.setInt(5, article.getPrixInitial());
			preparedStatement.setInt(6, article.getPrixVente());
			
			preparedStatement.setInt(7, article.getNoArticle());
			
			preparedStatement.executeUpdate();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}		
	}

	@Override
	public void delete(ArticleVendu article) throws SQLException {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement =
						connection.prepareStatement(DELETE_ARTICLE_BY_ID);)
		{
			preparedStatement.setInt(1, article.getNoArticle());
			preparedStatement.executeUpdate();
		}		
	}

	@Override
	public ArticleVendu selectByID(int ID) throws SQLException 
	{
		ArticleVendu article = null;
		
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement =
						connection.prepareStatement(SELECT_ARTICLE_BY_ID);)
		{
			preparedStatement.setInt(1, ID);
			
			ResultSet resultSet = preparedStatement.executeQuery();			
			if (resultSet.next()) 
			{
				article = mapAllArticleData(resultSet);	
			}
		}
		
		System.out.println("User found with ID [ " + ID + " ]" + article.toString());
		return article;
	}

	@Override
	public List<ArticleVendu> selectAll() throws SQLException 
	{
		List<ArticleVendu> articles = new ArrayList<ArticleVendu>();
		
		try (Connection connection = ConnectionProvider.getConnection();
				Statement statement = connection.createStatement();)
		{					
			ResultSet resultSet = statement.executeQuery(SELECT_ALL_ARTICLES);
			
			ArticleVendu article = null;
			while (resultSet.next()) 
			{
				article = mapAllArticleData(resultSet);				
				articles.add(article);
				System.out.println("Found user : " + article.toString());
			}
			
			statement.close();
			connection.close();
		}
		
		return articles;
	}
	
	public ArticleVendu selectByName(String comparedPseudo) throws SQLException
	{
		ArticleVendu article = null;
		
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement =
						connection.prepareStatement(SELECT_ARTICLE_BY_NAME);)
		{
			preparedStatement.setString(1, comparedPseudo);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) 
			{
				article = mapAllArticleData(resultSet);
			}
		}
		
		System.out.println(
				"User found with pseudo [ " + comparedPseudo + " ]" + article.toString());
		return article;
	}
	
	private ArticleVendu mapAllArticleData(ResultSet rs) throws SQLException {
		ArticleVendu article = new ArticleVendu();
		
		article.setNoArticle(rs.getInt("no_article"));
		article.setNomArticle(rs.getString("nom_article"));
		article.setDescription(rs.getString("description"));
		article.setDateDebutEncheres(rs.getDate("date_debut_encheres"));
		article.setDateFinEncheres(rs.getDate("date_fin_encheres"));
		article.setPrixInitial(rs.getInt("prix_initial"));
		article.setPrixVente(rs.getInt("prix_vente"));
		
		return article;
	}

}