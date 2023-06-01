package fr.eni.projetenchere.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.projetenchere.bo.User;

public interface UserDAO
{
	// CRUD - Create, Read, Update, Delete
	public void insert(User utilisateur);
	public void update(User utilisateur) throws SQLException;
	public void delete(User utilisateur);
	
	// Read section
	public User selectByID(int ID);
	public List<User> selectAll();

	// Check section
	public User selectByPseudoOrEmail(String pseudoOuEmail) throws SQLException;
}