package fr.eni.projetenchere.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetenchere.bo.User;
import fr.eni.projetenchere.dal.ConnectionProvider;
import fr.eni.projetenchere.dal.UserDAO;

public class UserDAOJdbcImplementation implements UserDAO {
	final String INSERT_USER = "INSERT INTO UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	final String UPDATE_USER = "UPDATE UTILISATEURS SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=? WHERE no_utilisateur=?";
	final String UPDATE_USER_CREDIT = "UPDATE UTILISATEURS SET credit=? WHERE no_utilisateur=?";
	final String UPDATE_USER_IS_ADMIN = "UPDATE UTILISATEURS SET administrateur=? WHERE no_utilisateur=?";
	
	final String SELECT_ALL_USERS = "SELECT * FROM UTILISATEURS";
	final String SELECT_USER_BY_ID = "SELECT * FROM UTILISATEURS WHERE no_utilisateur=?";
	final String SELECT_USER_BY_PSEUDO = "SELECT * FROM UTILISATEURS WHERE pseudo=?";
	final String SELECT_USER_BY_EMAIL = "SELECT * FROM UTILISATEURS WHERE email=?";
	
	final String CHECK_USER_PSEUDO = "SELECT pseudo FROM UTILISATEURS WHERE pseudo=?";
	final String CHECK_USER_EMAIL = "SELECT email FROM UTILISATEURS WHERE email=?";
	
	final String DELETE_USER_BY_ID = "DELETE FROM UTILISATEURS WHERE no_utilisateur=?";
	private final String DELETE_ALL_USERS = "DELETE FROM UTILISATEURS";

	private static final String SELECT_BY_PSEUDO_OR_EMAIL = "SELECT * FROM UTILISATEURS WHERE pseudo=? OR email=?";

	public void insert(User user) throws SQLException {
		try (Connection connection = ConnectionProvider.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS)) {

			preparedStatement.setString(1, user.getPseudo());
			preparedStatement.setString(2, user.getNom());
			preparedStatement.setString(3, user.getPrenom());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setString(5, user.getTelephone());
			preparedStatement.setString(6, user.getRue());
			preparedStatement.setString(7, user.getCodePostal());
			preparedStatement.setString(8, user.getVille());
			preparedStatement.setString(9, user.getMotDePasse());
			preparedStatement.setInt(10, user.getCredit());
			preparedStatement.setBoolean(11, user.isAdministrateur());

			// Exécuter l'instruction SQL
			int rowsAffected = preparedStatement.executeUpdate();

			if (rowsAffected > 0) {
				try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys();) {
					if (generatedKeys.next()) {
						int nouvelId = generatedKeys.getInt(1);

					}
				}
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void update(User user) throws SQLException
	{
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement =
						connection.prepareStatement(UPDATE_USER);)
		{
			preparedStatement.setString(1, user.getPseudo());
			preparedStatement.setString(2, user.getNom());
			preparedStatement.setString(3, user.getPrenom());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setString(5, user.getTelephone());
			preparedStatement.setString(6, user.getRue());
			preparedStatement.setString(7, user.getCodePostal());
			preparedStatement.setString(8, user.getVille());
			preparedStatement.setString(9, user.getMotDePasse());
			
			preparedStatement.setInt(10, user.getNoUtilisateur());
			
			preparedStatement.executeUpdate();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public User selectByID(int ID) throws SQLException
	{
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement =
						connection.prepareStatement(SELECT_USER_BY_ID);)
		{
			preparedStatement.setInt(1, ID);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next())
				{
					User user = mapAllUserData(resultSet);
					//System.out.println( "User found with ID [ " + ID + " ]" + user.toString());
					return user;
				}
			}
		}	
		
		return null;
	}
	
	@Override
	public List<User> selectAll() throws SQLException
	{
		List<User> users = new ArrayList<User>();
		
		try (Connection connection = ConnectionProvider.getConnection();
				Statement statement = connection.createStatement();)
		{
			try (ResultSet resultSet = statement.executeQuery(SELECT_ALL_USERS)) {
				while (resultSet.next()) {
					User user = mapAllUserData(resultSet);
					users.add(user);
					System.out.println("Found user : " + user.toString());
				}
			}

		}
		
		return users;
	}
	
	@Override
	public void delete(User user) throws SQLException
	{
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement =
						connection.prepareStatement(DELETE_USER_BY_ID);)
		{
			preparedStatement.setInt(1, user.getNoUtilisateur());
			preparedStatement.executeUpdate();
		}
	}
	
	public void updateCredit(User user, int newValue) throws SQLException
	{
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement =
						connection.prepareStatement(UPDATE_USER_CREDIT);)
		{
			preparedStatement.setInt(1, newValue);
			
			preparedStatement.setInt(2, user.getNoUtilisateur());
			preparedStatement.executeUpdate();
		}
	}
	
	public void updateIsAdmin(User user, boolean newValue) throws SQLException
	{
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement =
						connection.prepareStatement(UPDATE_USER_IS_ADMIN);)
		{
			preparedStatement.setBoolean(1, newValue);
			
			preparedStatement.setInt(2, user.getNoUtilisateur());
			preparedStatement.executeUpdate();
		}
	}
	
	public User selectByPseudo(String comparedPseudo) throws SQLException
	{
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement =
						connection.prepareStatement(SELECT_USER_BY_PSEUDO);)
		{
			preparedStatement.setString(1, comparedPseudo);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					System.out.println(
							"User found with pseudo [ " + comparedPseudo + " ]" + mapUserDataUptoCity(resultSet).toString());
					return mapUserDataUptoCity(resultSet);
				}
			}
		}		
		
		return null;
	}
	
	public User selectByEmail(String comparedEmail) throws SQLException
	{
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement =
						connection.prepareStatement(SELECT_USER_BY_EMAIL);)
		{
			preparedStatement.setString(1, comparedEmail);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					System.out.println( "User found with email [ " + comparedEmail + " ]"
							+ mapUserDataUptoCity(resultSet).toString());
					return mapUserDataUptoCity(resultSet);
				}
			}
		}
		
		return null;
	}
	
	@Override
	public User selectByPseudoOrEmail(String pseudoOuEmail) throws SQLException {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement ps = connection.prepareStatement(SELECT_BY_PSEUDO_OR_EMAIL)) 
		{
			ps.setString(1, pseudoOuEmail);
			ps.setString(2, pseudoOuEmail);
			
			try (ResultSet rs = ps.executeQuery()) 
			{
				if (rs.next()) { return mapAllUserData(rs); }
			}
		}
		
		return null;
	}
	
	public boolean doesThisPseudoAlreadyExists(String comparedPseudo) throws SQLException
	{
		try (Connection connection = ConnectionProvider.getConnection();				
				PreparedStatement preparedStatement =
						connection.prepareStatement(CHECK_USER_PSEUDO);	)
		{					
			preparedStatement.setString(1, comparedPseudo);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {

				if (resultSet.next()) {
					System.out.println("Le pseudo spécifié [" + comparedPseudo + "] existe déjà.");
					return true;
				}
			}
		} 
		
		System.out.println("Le pseudo spécifié [" + comparedPseudo + "] n'est pas présent dans la BDD.");
		return false;
	}
	
	public boolean doesThisEmailAlreadyExists(String comparedEmail) throws SQLException
	{
		try (Connection connection = ConnectionProvider.getConnection();				
				PreparedStatement preparedStatement = 
						connection.prepareStatement(CHECK_USER_EMAIL);)
		{		
			preparedStatement.setString(1, comparedEmail);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {

				if (resultSet.next()) {
					System.out.println("L'email spécifié [" + comparedEmail + "] existe déjà");
					return true;
				}
			}
		} 
		
		System.out.println("L'email spécifié [" + comparedEmail + "] n'est pas présent dans la BDD.");
		return false;
	}
	
	private User mapUserDataUptoCity(ResultSet rs) throws SQLException {
		User user = new User();
		
		user.setNoUtilisateur(rs.getInt("no_utilisateur"));
		user.setPseudo(rs.getString("pseudo"));
		user.setNom(rs.getString("nom"));
		user.setPrenom(rs.getString("prenom"));
		user.setEmail(rs.getString("email"));
		user.setTelephone(rs.getString("telephone"));
		user.setRue(rs.getString("rue"));
		user.setCodePostal(rs.getString("code_postal"));
		user.setVille(rs.getString("ville"));
		
		return user;
	}
	
	private User mapAllUserData(ResultSet rs) throws SQLException {
		User user = new User();
		
		user.setNoUtilisateur(rs.getInt("no_utilisateur"));
		user.setPseudo(rs.getString("pseudo"));
		user.setNom(rs.getString("nom"));
		user.setPrenom(rs.getString("prenom"));
		user.setEmail(rs.getString("email"));
		user.setTelephone(rs.getString("telephone"));
		user.setRue(rs.getString("rue"));
		user.setCodePostal(rs.getString("code_postal"));
		user.setVille(rs.getString("ville"));
		user.setMotDePasse(rs.getString("mot_de_passe"));
		user.setCredit(rs.getInt("credit"));
		user.setAdministrateur(rs.getBoolean("administrateur"));
		
		return user;
	}
	
	// DEBUG PURPOSE ONLY --------------------------------------------------
	
	public void deleteAllUser()
	{
		try (Connection connection = ConnectionProvider.getConnection();				
				PreparedStatement preparedStatement = 
						connection.prepareStatement(DELETE_ALL_USERS);)
		{
			preparedStatement.executeUpdate();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}