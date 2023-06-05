package fr.eni.projetenchere.dal;

import fr.eni.projetenchere.bo.Categorie;

import java.sql.SQLException;
import java.util.List;

public interface CategorieDAO {

    void insert(Categorie categorie) throws SQLException;
    void update(Categorie categorie) throws SQLException;
    void delete(Categorie categorie) throws SQLException;
    Categorie selectByID(int id) throws SQLException;
    Categorie selectByLibelle(String libelle) throws SQLException;


    List<Categorie> selectAll() throws SQLException;

    boolean checkLibelleAvailability(String libelle) throws SQLException;
}
