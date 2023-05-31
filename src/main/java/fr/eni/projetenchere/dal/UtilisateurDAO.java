package fr.eni.projetenchere.dal;

import java.util.List;

import fr.eni.projetenchere.bo.Utilisateur;

public interface UtilisateurDAO 
{
	// CRUD - Create, Read, Update, Delete
	public void insert(Utilisateur utilisateur);
	public void update(Utilisateur utilisateur);
	public void delete(Utilisateur utilisateur);
	
	public Utilisateur selectByID(int ID);
	public List<Utilisateur> selectAll();
}