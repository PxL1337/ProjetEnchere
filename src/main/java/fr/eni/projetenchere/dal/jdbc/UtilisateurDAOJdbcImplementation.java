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
	final String INSERT_USER = "INSERT INTO UTILISATEURS(no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	final String UPDATE_USER = "UPDATE UTILISATEURS SET no_utilisateur=?, pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=? WHERE no_utilisateur=?";
	final String UPDATE_USER_CREDIT = "UPDATE UTILISATEURS SET no_utilisateur=?, credit=? WHERE no_utilisateur=?";
	
	final String SELECT_USER_BY_ID = "SELECT * FROM UTILISATEURS WHERE no_utilisateur=?";
	final String SELECT_USER_ALL_INFOS = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville  FROM UTILISATEURS";
	
	final String CHECK_USER_PSEUDO = "SELECT pseudo FROM UTILISATEURS WHERE pseudo=?";
	
	final String DELETE_USER_BY_ID = "DELETE FROM UTILISATEURS WHERE no_utilisateur=?";
	
	@Override
	public void insert(Utilisateur utilisateur)
	{
		try 
		{
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);
			
			preparedStatement.setInt(1, utilisateur.getNoUtilisateur());
			preparedStatement.setString(2, utilisateur.getPseudo());
			preparedStatement.setString(3, utilisateur.getNom());
			preparedStatement.setString(4, utilisateur.getPrenom());
			preparedStatement.setString(5, utilisateur.getEmail());
			preparedStatement.setString(6, utilisateur.getTelephone());
			preparedStatement.setString(7, utilisateur.getRue());
			preparedStatement.setString(8, utilisateur.getCodePostal());
			preparedStatement.setString(9, utilisateur.getVille());
			preparedStatement.setString(10, utilisateur.getMotDePasse());
			preparedStatement.setInt(11, utilisateur.getCredit());
			preparedStatement.setBoolean(12, utilisateur.isAdministrateur());
			  
			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println(rowsAffected + " Rows affected.");
			  
			preparedStatement.close();
			connection.close();
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
		try 
		{
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
			
			preparedStatement.setInt(1, utilisateur.getNoUtilisateur());
			preparedStatement.setString(2, utilisateur.getPseudo());
			preparedStatement.setString(3, utilisateur.getNom());
			preparedStatement.setString(4, utilisateur.getPrenom());
			preparedStatement.setString(5, utilisateur.getEmail());
			preparedStatement.setString(6, utilisateur.getTelephone());
			preparedStatement.setString(7, utilisateur.getRue());
			preparedStatement.setString(8, utilisateur.getCodePostal());
			preparedStatement.setString(9, utilisateur.getVille());
			preparedStatement.setString(10, utilisateur.getMotDePasse());
			preparedStatement.setInt(11, utilisateur.getCredit());
			preparedStatement.setBoolean(12, utilisateur.isAdministrateur());
			
			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println(rowsAffected + " Rows affected.");
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
			
			connection.close();
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
		
		try 
		{			
			Connection connection = ConnectionProvider.getConnection();
			
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);			
			preparedStatement.setInt(1, ID);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			utilisateur = (Utilisateur) resultSet;
			
			preparedStatement.close();
			connection.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		
		return utilisateur;
	}
	
	@Override
	public List<Utilisateur> selectAll()
	{
		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		
		try 
		{
			Connection connection = ConnectionProvider.getConnection();
			Statement statement = connection.createStatement();			
			ResultSet resulSet = statement.executeQuery(SELECT_USER_ALL_INFOS);
			
			Utilisateur utilisateur = null;
			while (resulSet.next()) 
			{
				utilisateur = new Utilisateur();
				
				utilisateur.setNoUtilisateur(resulSet.getInt("no_utilisateur"));
				utilisateur.setPseudo(resulSet.getString("pseudo"));
				utilisateur.setNom(resulSet.getString("nom"));
				utilisateur.setPrenom(resulSet.getString("prenom"));
				utilisateur.setEmail(resulSet.getString("email"));
				utilisateur.setTelephone(resulSet.getString("telephone"));
				utilisateur.setRue(resulSet.getString("rue"));
				utilisateur.setCodePostal(resulSet.getString("code_postal"));
				utilisateur.setVille(resulSet.getString("ville"));				 
				
				utilisateurs.add(utilisateur);
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
		try 
		{
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID);
			
			preparedStatement.setInt(1, utilisateur.getNoUtilisateur());
			preparedStatement.executeUpdate();
			
			preparedStatement.close();
			connection.close();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void updateUserCredit(Utilisateur utilisateur)
	{
		try 
		{
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_CREDIT);
			
			preparedStatement.setInt(1, utilisateur.getNoUtilisateur());
			preparedStatement.setInt(2, utilisateur.getCredit());
			preparedStatement.executeUpdate();
			
			preparedStatement.close();
			connection.close();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean doesThisPseudoAlreadyExists(String comparedPseudo)
	{
		try 
		{
			Connection connection = ConnectionProvider.getConnection();
			
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);			
			preparedStatement.setString(1, comparedPseudo);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.isBeforeFirst()) 
			{
				preparedStatement.close();
				connection.close();
				return true;
			}			
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
}
