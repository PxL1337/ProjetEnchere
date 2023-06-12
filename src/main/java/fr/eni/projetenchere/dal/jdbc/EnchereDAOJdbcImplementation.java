package fr.eni.projetenchere.dal.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetenchere.bll.ArticleManager;
import fr.eni.projetenchere.bll.CategorieManager;
import fr.eni.projetenchere.bll.UserManager;
import fr.eni.projetenchere.bo.ArticleVendu;
import fr.eni.projetenchere.bo.Enchere;
import fr.eni.projetenchere.bo.User;
import fr.eni.projetenchere.dal.ConnectionProvider;
import fr.eni.projetenchere.dal.EnchereDAO;

public class EnchereDAOJdbcImplementation implements EnchereDAO {
	
	
	final String INSERT_ENCHERE = "INSERT INTO ENCHERES(date_enchere, montant_enchere, no_article, no_utilisateur) VALUES(?, ?, ?, ?)";
	final String UPDATE_MONTANT_ENCHERE = "UPDATE ENCHERES SET montant_enchere=? WHERE no_enchere=?";

	final String UPDATE_NO_PROPRIETAIRE = "UPDATE ENCHERES SET no_utilisateur=? WHERE no_enchere=?";
	final String DELETE_ENCHERE = "DELETE FROM ENCHERES WHERE no_enchere=?";
	final String SELECT_ENCHERE_BY_ID = "SELECT * FROM ENCHERES WHERE no_enchere=?";

	final String SELECT_ALL_ENCHERES = "SELECT E.no_enchere, E.date_enchere, E.montant_enchere, A.no_article, A.nom_article, A.date_fin_encheres, U.no_utilisateur, U.pseudo " +
			"FROM ENCHERES E JOIN ARTICLES_VENDUS A ON A.no_article = E.no_article " +
			"JOIN UTILISATEURS U ON A.no_utilisateur = U.no_utilisateur ORDER BY E.date_enchere";
	
	final String SELECT_ALL_ENCHERES_BY_CATEGORIE = "SELECT e.*, av.no_categorie, av.nom_article, av.date_fin_encheres, av.no_utilisateur, u.no_utilisateur, u.pseudo "
			+ "FROM ENCHERES e "
			+ "JOIN ARTICLES_VENDUS av ON e.no_article = av.no_article "
			+ "JOIN UTILISATEURS u ON av.no_utilisateur = u.no_utilisateur "
			+ "WHERE av.no_categorie =? "
			+ "ORDER BY e.date_enchere";
	
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
				preparedStatement.setInt(4, enchere.getNoProprietaire());
				
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
			preparedStatement.setInt(2, enchere.getNo_Enchere());
			
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
		
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement =
						connection.prepareStatement(SELECT_ENCHERE_BY_ID);)
		{
			preparedStatement.setInt(1, ID);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {

					Enchere enchere = mapAllEnchereData(resultSet);
					System.out.println("Enchere found with ID [ " + ID + " ]" + enchere.toString());
					return enchere;
				}
			}
		}
		

		return null;
	}

	@Override
	public List<Enchere> selectAll() throws SQLException {
		List<Enchere> encheres = new ArrayList<Enchere>();
		try (Connection connection = ConnectionProvider.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ENCHERES);) {
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Enchere enchere = mapAllEnchereListe(resultSet);
				encheres.add(enchere);
				System.out.println("Found enchere : " + enchere.toString());
			}
			// Removing sort function as the SQL query is already sorting the data
			return encheres;
		}
	}

	@Override
	public List<Enchere> selectAllFiltredByName(String nameFilter) throws SQLException 
	{
		List<Enchere> encheres = new ArrayList<Enchere>();
		try (Connection connection = ConnectionProvider.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(
					 "SELECT E.no_enchere, E.date_enchere, E.montant_enchere, A.no_article, A.nom_article, A.date_fin_encheres, U.no_utilisateur, U.pseudo " +
						"FROM ENCHERES E JOIN ARTICLES_VENDUS A ON A.no_article = E.no_article " +
						"JOIN UTILISATEURS U ON E.no_utilisateur = U.no_utilisateur "
						+ "WHERE A.nom_article "
						+ "LIKE " + "'%" + nameFilter + "%' "
						+ "ORDER BY E.date_enchere");) {
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Enchere enchere = mapAllEnchereListe(resultSet);
				encheres.add(enchere);
				System.out.println("Found enchere : " + enchere.toString());
			}
		}
			// Removing sort function as the SQL query is already sorting the data
			return encheres;
	}

	@Override
	public List<Enchere> selectAllFiltredByCategory(int categoryID) throws SQLException {
		List<Enchere> encheres = new ArrayList<Enchere>();
		try (Connection connection = ConnectionProvider.getConnection();
			 PreparedStatement preparedStatement = 
					 connection.prepareStatement(SELECT_ALL_ENCHERES_BY_CATEGORIE);)
		{
			//WHERE
			preparedStatement.setInt(1, categoryID);
			
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Enchere enchere = mapAllEnchereFiltredByCategory(resultSet);
				encheres.add(enchere);
				System.out.println("Found enchere : " + enchere.toString());
			}
		}
		// Removing sort function as the SQL query is already sorting the data
		return encheres;
	}

	@Override
	public void updateNoProprietaire(Enchere enchere, int EncherisseurID) {

		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("UPDATE ENCHERES SET no_utilisateur=? WHERE no_enchere=?");)
		{
			System.out.println("No proprietaire : " + EncherisseurID);
			preparedStatement.setInt(1, EncherisseurID);
			//WHERE
			preparedStatement.setInt(2, enchere.getNo_Enchere());

			preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	private Enchere mapAllEnchereListe(ResultSet resultSet) throws SQLException {
		// Creating new objects based on the result set		
		ArticleVendu article = new ArticleVendu(
				resultSet.getInt("no_article"), 
				resultSet.getString("nom_article"), 
				resultSet.getDate("date_fin_encheres"));
		/*
		 * ArticleVendu article =
		 * ArticleManager.getInstance().selectArticleByID(resultSet.getInt(
		 * "no_article"));
		 */
		/*
		 * article.setNoArticle(resultSet.getInt("no_article")); // Add this
		 * line article.setNomArticle(resultSet.getString("nom_article"));
		 * article.setDateFinEncheres(resultSet.getDate("date_fin_encheres"));
		 */
		
		System.out.println("ARTICLE DE L'ENCHERE : " + article.getNomArticle());

		User proprietaireArticle = new User();
		proprietaireArticle.setNoUtilisateur(resultSet.getInt("no_utilisateur"));
		proprietaireArticle.setPseudo(resultSet.getString("pseudo"));
		
		System.out.println("PROPRIETAIRE ARTICLE : " + proprietaireArticle);
		
		article.setUtilisateur(proprietaireArticle);

		Enchere enchere = new Enchere();
		enchere.setNo_Enchere(resultSet.getInt("no_enchere"));  // Add this line
		// Convert the Timestamp to Date
		Timestamp timestamp = resultSet.getTimestamp("date_enchere");
		Date date = new Date(timestamp.getTime());
		enchere.setDateEnchere(date);
		enchere.setMontantEnchere(resultSet.getInt("montant_enchere"));
		
		enchere.setArticle(article);
		enchere.setNoArticle(resultSet.getInt("no_article"));
		
		enchere.setProprietaire(proprietaireArticle);
		enchere.setNoProprietaire(resultSet.getInt("no_utilisateur"));
		
		System.out.println("MAPPING POUR L'AFFICHAGE DE TOUTES LES ENCHERES : " + enchere.toString());

		return enchere;
	}
	
	private Enchere mapAllEnchereFiltredByCategory(ResultSet resultSet) throws SQLException {
		// Creating new objects based on the result set
		ArticleVendu article = ArticleManager.getInstance().selectArticleByID(resultSet.getInt("no_article"));
		article.setNoArticle(resultSet.getInt("no_article"));
		article.setNomArticle(resultSet.getString("nom_article"));
		article.setDateFinEncheres(resultSet.getDate("date_fin_encheres"));
		article.setCategorie(
				CategorieManager.getInstance().selectCategorieByID(resultSet.getInt("no_categorie")));
		
		User proprietaire = new User();
		proprietaire.setNoUtilisateur(resultSet.getInt("no_utilisateur"));
		proprietaire.setPseudo(resultSet.getString("pseudo"));

		Enchere enchere = new Enchere();
		enchere.setNo_Enchere(resultSet.getInt("no_enchere"));  // Add this line
		// Convert the Timestamp to Date
		Timestamp timestamp = resultSet.getTimestamp("date_enchere");
		Date date = new Date(timestamp.getTime());
		enchere.setDateEnchere(date);
		enchere.setMontantEnchere(resultSet.getInt("montant_enchere"));
		enchere.setArticle(article);
		enchere.setNoArticle(resultSet.getInt("no_article"));		
		enchere.setProprietaire(proprietaire);
		enchere.setNoProprietaire(resultSet.getInt("no_utilisateur"));

		return enchere;
	}

	private Enchere mapAllEnchereData(ResultSet resultSet) throws SQLException {
		Enchere enchere = new Enchere();
		enchere.setNo_Enchere(resultSet.getInt("no_enchere"));  // Add this line
		// Convert the Timestamp to Date
		Timestamp timestamp = resultSet.getTimestamp("date_enchere");
		Date date = new Date(timestamp.getTime());
		enchere.setDateEnchere(date);
		enchere.setMontantEnchere(resultSet.getInt("montant_enchere"));
		
		ArticleVendu article = ArticleManager.getInstance().selectArticleByID(resultSet.getInt("no_article"));
		enchere.setNoArticle(resultSet.getInt("no_article"));
		enchere.setArticle(article);
		enchere.setNoProprietaire(resultSet.getInt("no_utilisateur"));

		User proprietaire = UserManager.getInstance().selectUserByID(resultSet.getInt("no_utilisateur"));
		enchere.setProprietaire(proprietaire);

		return enchere;
	}
}
