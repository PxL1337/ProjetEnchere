package fr.eni.projetenchere.bll;

import fr.eni.projetenchere.bo.Categorie;
import fr.eni.projetenchere.dal.CategorieDAO;
import fr.eni.projetenchere.dal.jdbc.DAOFactory;
import fr.eni.projetenchere.exception.BusinessException;

import java.sql.SQLException;
import java.util.List;

public class CategorieManager {
	private static CategorieManager instance;
	private CategorieDAO categorieDAO;

	private CategorieManager(CategorieDAO categorieDAO) {
		this.categorieDAO = categorieDAO;
	}

	public static synchronized CategorieManager getInstance(CategorieDAO categorieDAO) {
		if (instance == null) {
			instance = new CategorieManager(categorieDAO);
		}
		return instance;
	}

	public void insert(Categorie categorie) throws BusinessException, SQLException {
		validateCategorie(categorie);
		categorieDAO.insert(categorie);
	}

	public void update(Categorie categorie) throws BusinessException, SQLException {
		validateCategorie(categorie);
		categorieDAO.update(categorie);
	}

	public void delete(Categorie categorie) throws SQLException {
		categorieDAO.delete(categorie);
	}

	public Categorie selectCategorieByID(int ID) throws SQLException {
		return categorieDAO.selectByID(ID);
	}

	public Categorie selectCategorieByLibelle(String libelle) throws SQLException {
		return categorieDAO.selectByLibelle(libelle);
	}

	public List<Categorie> selectAllCategorie() throws SQLException {
		return categorieDAO.selectAll();
	}

	public boolean checkLibelleAvailability(String libelle) throws SQLException {
		return categorieDAO.checkLibelleAvailability(libelle);
	}

	public void validateCategorie(Categorie categorie) throws BusinessException, SQLException {
		BusinessException businessException = new BusinessException();
		if (checkLibelleAvailability(categorie.getLibelle()) ){
			businessException.ajouterErreur(CodeErreur.CATEGORIE_LIBELLE_EXISTANT);
		}
		if (categorie.getLibelle() == null || categorie.getLibelle().trim().length() == 0) {
			businessException.ajouterErreur(CodeErreur.CATEGORIE_LIBELLE_NULL);
		}
		if (categorie.getLibelle().length() > 30) {
			businessException.ajouterErreur(CodeErreur.CATEGORIE_LIBELLE_TROP_LONG);
		}
		if (businessException.hasErreurs()) {
			throw businessException;
		}
	}
}
