package fr.eni.projetenchere.dal.jdbc;

import fr.eni.projetenchere.bo.Categorie;
import fr.eni.projetenchere.dal.CategorieDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetenchere.bo.User;
import fr.eni.projetenchere.dal.ConnectionProvider;

public class CategorieDAOJdbcImplementation implements CategorieDAO {

    final String INSERT_CATEGORIE = "INSERT INTO CATEGORIES (libelle) VALUES (?)";
    final String UPDATE_CATEGORIE = "UPDATE CATEGORIES SET libelle = ? WHERE no_categorie = ?";
    final String SELECT_CATEGORIE_BY_ID = "SELECT * FROM CATEGORIES WHERE no_categorie = ?";
    final String SELECT_CATEGORIE_BY_LIBELLE = "SELECT * FROM CATEGORIES WHERE libelle = ?";
    final String SELECT_ALL_CATEGORIE = "SELECT * FROM CATEGORIES";
    final String CHECK_LIBELLE_AVAILABILITY = "SELECT libelle FROM CATEGORIES WHERE libelle = ?";

    public void insert(Categorie categorie) throws SQLException {
        try(Connection connection = ConnectionProvider.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CATEGORIE, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, categorie.getLibelle());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 1) {
                try (ResultSet rs = preparedStatement.getGeneratedKeys();) {
                    if (rs.next()) {
                        categorie.setNoCategorie(rs.getInt(1));
                        System.out.println("Nouvelle catégorie ajoutée avec l'identifiant : " + categorie.getNoCategorie());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Categorie categorie) throws SQLException {
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CATEGORIE);) {
            preparedStatement.setString(1, categorie.getLibelle());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Categorie categorie) throws SQLException {

    }

    @Override
    public Categorie selectByID(int id) throws SQLException {
        return null;
    }

    @Override
    public Categorie selectByLibelle(String libelle) throws SQLException {
        return null;
    }

    @Override
    public List<Categorie> selectAll() throws SQLException {
        return null;
    }

    @Override
    public void selectAllByArticle(int id) throws SQLException {

    }

    @Override
    public boolean checkLibelleAvailability(String libelle) throws SQLException {
        return false;
    }

}
