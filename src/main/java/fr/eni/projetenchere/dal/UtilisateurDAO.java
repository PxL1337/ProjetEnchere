package fr.eni.projetenchere.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.projetenchere.bo.Utilisateur;

public interface UtilisateurDAO 
{
	// CRUD - Create, Read, Update, Delete
	public void insert(Utilisateur utilisateur);
	public void update(Utilisateur utilisateur) throws SQLException;
	public void delete(Utilisateur utilisateur);
	
	// Read section
	public Utilisateur selectByID(int ID);
	public List<Utilisateur> selectAll();

	// Check section
	public Utilisateur selectByPseudoOuEmail(String pseudoOuEmail) throws SQLException;
}