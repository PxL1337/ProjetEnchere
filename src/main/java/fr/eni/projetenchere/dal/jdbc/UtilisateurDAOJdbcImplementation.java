package fr.eni.projetenchere.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetenchere.bo.Utilisateur;
import fr.eni.projetenchere.dal.ConnectionProvider;
import fr.eni.projetenchere.dal.UtilisateurDAO;

public class UtilisateurDAOJdbcImplementation implements UtilisateurDAO 
{	
	final String INSERT_USER = "INSERT INTO UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	final String UPDATE_USER = "UPDATE UTILISATEURS SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=? WHERE no_utilisateur=?";
	final String UPDATE_USER_CREDIT = "UPDATE UTILISATEURS SET credit=? WHERE no_utilisateur=?";
	
	final String SELECT_ALL_USERS = "SELECT * FROM UTILISATEURS";
	final String SELECT_USER_BY_ID = "SELECT * FROM UTILISATEURS WHERE no_utilisateur=?";
	final String SELECT_USER_BY_PSEUDO = "SELECT * FROM UTILISATEURS WHERE pseudo=?";
	final String SELECT_USER_BY_EMAIL = "SELECT * FROM UTILISATEURS WHERE email=?";
	
	final String CHECK_USER_PSEUDO = "SELECT pseudo FROM UTILISATEURS WHERE pseudo=?";
	final String CHECK_USER_EMAIL = "SELECT email FROM UTILISATEURS WHERE email=?";
	
	final String DELETE_USER_BY_ID = "DELETE FROM UTILISATEURS WHERE no_utilisateur=?";
	private final String DELETE_ALL_USERS = "DELETE FROM UTILISATEURS";
	
	@Override
	public void insert(Utilisateur utilisateur)
	{
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement = 
						connection.prepareStatement(
						INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);)
		{
			preparedStatement.setString(1, utilisateur.getPseudo());
			preparedStatement.setString(2, utilisateur.getNom());
			preparedStatement.setString(3, utilisateur.getPrenom());
			preparedStatement.setString(4, utilisateur.getEmail());
			preparedStatement.setString(5, utilisateur.getTelephone());
			preparedStatement.setString(6, utilisateur.getRue());
			preparedStatement.setString(7, utilisateur.getCodePostal());
			preparedStatement.setString(8, utilisateur.getVille());
			preparedStatement.setString(9, utilisateur.getMotDePasse());
			preparedStatement.setInt(10, utilisateur.getCredit());
			preparedStatement.setBoolean(11, utilisateur.isAdministrateur());
			  
			debugAffectedRows(preparedStatement);
			
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int nouvelId = generatedKeys.getInt(1);
                System.out.println("Nouvel utilisateur ajouté avec l'identifiant : " + nouvelId);
            }
		} 
		catch (SQLException e )
		{
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(Utilisateur utilisateur) 
	{
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement =
						connection.prepareStatement(UPDATE_USER);)
		{
			preparedStatement.setString(1, utilisateur.getPseudo());
			preparedStatement.setString(2, utilisateur.getNom());
			preparedStatement.setString(3, utilisateur.getPrenom());
			preparedStatement.setString(4, utilisateur.getEmail());
			preparedStatement.setString(5, utilisateur.getTelephone());
			preparedStatement.setString(6, utilisateur.getRue());
			preparedStatement.setString(7, utilisateur.getCodePostal());
			preparedStatement.setString(8, utilisateur.getVille());
			preparedStatement.setString(9, utilisateur.getMotDePasse());
			
			preparedStatement.setInt(10, utilisateur.getNoUtilisateur());
			
			debugAffectedRows(preparedStatement);
			
			preparedStatement.executeUpdate();
		} 
		catch (SQLException e) 
		{
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public Utilisateur selectByID(int ID)
	{		
		Utilisateur utilisateur = null;
		
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement =
						connection.prepareStatement(SELECT_USER_BY_ID);)
		{
			preparedStatement.setInt(1, ID);
			
			ResultSet resultSet = preparedStatement.executeQuery();			
			if (resultSet.next()) 
			{
				utilisateur = new Utilisateur();
				
				utilisateur.setNoUtilisateur(resultSet.getInt("no_utilisateur"));
				utilisateur.setPseudo(resultSet.getString("pseudo"));
				utilisateur.setNom(resultSet.getString("nom"));
				utilisateur.setPrenom(resultSet.getString("prenom"));
				utilisateur.setEmail(resultSet.getString("email"));
				utilisateur.setTelephone(resultSet.getString("telephone"));
				utilisateur.setRue(resultSet.getString("rue"));
				utilisateur.setCodePostal(resultSet.getString("code_postal"));
				utilisateur.setVille(resultSet.getString("ville"));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		System.out.println(
				"User found with ID [ " + ID + " ]"
		+ utilisateur.toString());
		return utilisateur;
	}
	
	@Override
	public List<Utilisateur> selectAll()
	{
		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		
		try (Connection connection = ConnectionProvider.getConnection();
				Statement statement = connection.createStatement();)
		{					
			ResultSet resultSet = statement.executeQuery(SELECT_ALL_USERS);
			
			Utilisateur utilisateur = null;
			while (resultSet.next()) 
			{
				utilisateur = new Utilisateur();
				
				utilisateur.setNoUtilisateur(resultSet.getInt("no_utilisateur"));
				utilisateur.setPseudo(resultSet.getString("pseudo"));
				utilisateur.setNom(resultSet.getString("nom"));
				utilisateur.setPrenom(resultSet.getString("prenom"));
				utilisateur.setEmail(resultSet.getString("email"));
				utilisateur.setTelephone(resultSet.getString("telephone"));
				utilisateur.setRue(resultSet.getString("rue"));
				utilisateur.setCodePostal(resultSet.getString("code_postal"));
				utilisateur.setVille(resultSet.getString("ville"));
				utilisateur.setMotDePasse(resultSet.getString("mot_de_passe"));
				utilisateur.setCredit(resultSet.getInt("credit"));
				utilisateur.setAdministrateur(resultSet.getBoolean("administrateur"));
				
				utilisateurs.add(utilisateur);
				/*
				 * System.out.println("Found user : " + utilisateur.toString());
				 */
			}
			
			statement.close();
			connection.close();
		} 
		catch (SQLException e)  
		{ 
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage()); 
		}
		
		return utilisateurs;
	}
	
	@Override
	public void delete(Utilisateur utilisateur) 
	{
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement =
						connection.prepareStatement(DELETE_USER_BY_ID);)
		{
			preparedStatement.setInt(1, utilisateur.getNoUtilisateur());
			preparedStatement.executeUpdate();
			
			debugAffectedRows(preparedStatement);
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void updateUserCredit(Utilisateur utilisateur, int newValue)
	{
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement =
						connection.prepareStatement(UPDATE_USER_CREDIT);)
		{
			preparedStatement.setInt(1, newValue);
			
			preparedStatement.setInt(2, utilisateur.getNoUtilisateur());
			preparedStatement.executeUpdate();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public Utilisateur selectByPseudo(String comparedPseudo)
	{
		Utilisateur utilisateur = null;
		
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement =
						connection.prepareStatement(SELECT_USER_BY_PSEUDO);)
		{
			preparedStatement.setString(1, comparedPseudo);
			
			ResultSet resultSet = preparedStatement.executeQuery();			
			if (resultSet.next()) 
			{
				utilisateur = new Utilisateur();
				
				utilisateur.setNoUtilisateur(resultSet.getInt("no_utilisateur"));
				utilisateur.setPseudo(resultSet.getString("pseudo"));
				utilisateur.setNom(resultSet.getString("nom"));
				utilisateur.setPrenom(resultSet.getString("prenom"));
				utilisateur.setEmail(resultSet.getString("email"));
				utilisateur.setTelephone(resultSet.getString("telephone"));
				utilisateur.setRue(resultSet.getString("rue"));
				utilisateur.setCodePostal(resultSet.getString("code_postal"));
				utilisateur.setVille(resultSet.getString("ville"));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		System.out.println(
				"User found with pseudo [ " + comparedPseudo + " ]"
		+ utilisateur.toString());
		return utilisateur;
	}
	
	public Utilisateur selectByEmail(String comparedEmail)
	{
		Utilisateur utilisateur = null;
		
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement =
						connection.prepareStatement(SELECT_USER_BY_EMAIL);)
		{
			preparedStatement.setString(1, comparedEmail);
			
			ResultSet resultSet = preparedStatement.executeQuery();			
			if (resultSet.next()) 
			{
				utilisateur = new Utilisateur();
				
				utilisateur.setNoUtilisateur(resultSet.getInt("no_utilisateur"));
				utilisateur.setPseudo(resultSet.getString("pseudo"));
				utilisateur.setNom(resultSet.getString("nom"));
				utilisateur.setPrenom(resultSet.getString("prenom"));
				utilisateur.setEmail(resultSet.getString("email"));
				utilisateur.setTelephone(resultSet.getString("telephone"));
				utilisateur.setRue(resultSet.getString("rue"));
				utilisateur.setCodePostal(resultSet.getString("code_postal"));
				utilisateur.setVille(resultSet.getString("ville"));
			}			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		
		System.out.println( "User found with email [ " + comparedEmail + " ]"
		+ utilisateur.toString());
		
		return utilisateur;
	}
	
	public boolean doesThisPseudoAlreadyExists(String comparedPseudo)
	{
		try (Connection connection = ConnectionProvider.getConnection();				
				PreparedStatement preparedStatement =
						connection.prepareStatement(CHECK_USER_PSEUDO);	)
		{					
			preparedStatement.setString(1, comparedPseudo);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.isBeforeFirst()) 
			{
				System.out.println("Le pseudo spécifié [" + comparedPseudo + "] existe déjà.");
				return true; 
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		System.out.println("Le pseudo spécifié [" + comparedPseudo + "] n'est pas présent dans la BDD.");
		return false;
	}
	
	public boolean doesThisEmailAlreadyExists(String comparedEmail)
	{
		try (Connection connection = ConnectionProvider.getConnection();				
				PreparedStatement preparedStatement = 
						connection.prepareStatement(CHECK_USER_PSEUDO);)
		{		
			preparedStatement.setString(1, comparedEmail);			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.isBeforeFirst()) 
			{
				System.out.println("L'email spécifié [" + comparedEmail + "] existe déjà");
				return true; 
			}			
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		System.out.println("L'email spécifié [" + comparedEmail + "] n'est pas présent dans la BDD.");
		return false;
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
	
	private void debugAffectedRows(PreparedStatement pS)
	{
		int rowsAffected = 0;
		try {
			rowsAffected = pS.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(rowsAffected + " Rows affected.");
	}
}
