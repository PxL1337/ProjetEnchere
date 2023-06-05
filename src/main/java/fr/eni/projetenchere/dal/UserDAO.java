package fr.eni.projetenchere.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.projetenchere.bo.User;

public interface UserDAO
{
	// CRUD - Create, Read, Update, Delete
	public void insert(User utilisateur) throws SQLException;
	public void update(User utilisateur) throws SQLException;
	public void delete(User utilisateur) throws SQLException;
	
	// Read section
	public User selectByID(int ID) throws SQLException;
	public List<User> selectAll() throws SQLException;

	// Check section
	public User selectByPseudoOrEmail(String pseudoOuEmail) throws SQLException;

	void updateCredit(User user, int newValue) throws SQLException;
	void updateIsAdmin(User user, boolean newValue) throws SQLException;
	User selectByPseudo(String comparedPseudo) throws SQLException;
	User selectByEmail(String comparedEmail) throws SQLException;
	boolean doesThisPseudoAlreadyExists(String pseudo) throws SQLException;
	boolean doesThisEmailAlreadyExists(String email) throws SQLException;
}