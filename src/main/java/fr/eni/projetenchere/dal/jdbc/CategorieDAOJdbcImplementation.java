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
    final String DELETE_CATEGORIE = "DELETE FROM CATEGORIES WHERE no_categorie = ?";

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
        try (Connection connection = ConnectionProvider.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CATEGORIE);) {
            preparedStatement.setInt(1, categorie.getNoCategorie());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public Categorie selectByID(int id) throws SQLException {
        Categorie categorie = null;
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORIE_BY_ID);) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery();) {
                if (rs.next()) {
                    categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
                }
            }
        }
        return categorie;
    }

    @Override
    public Categorie selectByLibelle(String libelle) throws SQLException {
        Categorie categorie = null;
        System.out.println(libelle + " dans selectByLibelle");
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORIE_BY_LIBELLE);) {
            preparedStatement.setString(1, libelle);
            try (ResultSet rs = preparedStatement.executeQuery();) {
                if (rs.next()) {
                    categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
                }
            }
        }
        System.out.println(categorie + " dans selectByLibelle");
        return categorie;
    }

    @Override
    public List<Categorie> selectAll() throws SQLException {
        List<Categorie> categories = new ArrayList<>();
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORIE);
             ResultSet rs = preparedStatement.executeQuery();) {
            while (rs.next()) {
                categories.add(new Categorie(rs.getInt("no_categorie"), rs.getString("libelle")));
            }
        }
        return categories;
    }

    @Override
    public boolean checkLibelleAvailability(String libelle) throws SQLException {
        boolean isAvailable = false;
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_LIBELLE_AVAILABILITY);) {
            preparedStatement.setString(1, libelle);
            try (ResultSet rs = preparedStatement.executeQuery();) {
                isAvailable = !rs.next();
            }
        }
        return isAvailable;
    }

}
