package fr.eni.projetenchere.dal.jdbc;

import java.sql.Connection;
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
	public void insert(Utilisateur utilisateur) 
	{
		// TODO Auto-generated method stub

	}
	
	public void update(Utilisateur utilisateur) 
	{
		// TODO Auto-generated method stub

	}
	
	public Utilisateur selectByID(int ID)
	{
		return null;
	}
	
	public List<Utilisateur> selectAll()
	{
		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		
		Connection connection = null;
		Statement statement;
		
		try 
		{
			connection = ConnectionProvider.getConnection();
			statement = connection.createStatement();
			
			// TODO: 
			// Créer un constructeur Utilisateur plus petit que celui déjà présent...
			// Aller chercher les infos de ce petit constructeur...
			// Stocker la query dans un final string comme filtre pour s'y retrouver plus facilement, 
			// voire pour pouvoir le réutiliser.
			ResultSet resulSet = statement.executeQuery("");
			Utilisateur utilisateur = null;
			
			while (resulSet.next()) 
			{
				utilisateur = new Utilisateur();
				utilisateurs.add(utilisateur);
			}			
		} 
		catch (SQLException e)  { e.printStackTrace(); }
		
		return utilisateurs;
	}
	
	public void delete(Utilisateur utilisateur) 
	{
		// TODO Auto-generated method stub
	}
}
