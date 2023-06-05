package fr.eni.projetenchere.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.projetenchere.bo.User;
import fr.eni.projetenchere.dal.UserDAO;
import fr.eni.projetenchere.dal.jdbc.DAOFactory;
import fr.eni.projetenchere.dal.jdbc.UserDAOJdbcImplementation;
import fr.eni.projetenchere.exception.BusinessException;
import org.mindrot.jbcrypt.BCrypt;

public class UserManager {
	public final int DEFAULT_USER_CREDIT = 100;
	public final boolean DEFAULT_IS_ADMIN_VALUE = false;
	//SINGLETON

	private static UserManager instance;
	
	public static UserManager getInstance()
	{
		if (instance == null) {
			instance = new UserManager();
		}
		
		return instance;
	}

	private UserDAO userDAO;
	
	private UserManager()
	{
		userDAO = DAOFactory.getUtilisateurDAO();
	}
	
	public void insertUser(User user) throws SQLException {
		String hashedPassword = BCrypt.hashpw(user.getMotDePasse(), BCrypt.gensalt());
		user.setMotDePasse(hashedPassword);
		userDAO.insert(user);
	}
	
	public void updateUser(User user) throws SQLException {
		userDAO.update(user);
	}
	
	public void deleteUser(User user) throws SQLException {
	    userDAO.delete(user);
	}
	
	public void updateUserCredit(User user, int newValue) throws SQLException {
		userDAO.updateCredit(user, newValue);
	}
	
	public void updateUserIsAdmin(User user, boolean newValue) throws SQLException {
		userDAO.updateIsAdmin(user, newValue);
		
	}
	
	public User selectUserByID(int ID) throws SQLException {
		return userDAO.selectByID(ID);
	}
	
	public List<User> selectAllUser() throws SQLException {
		return userDAO.selectAll();
	}
	
	public User selectUserByPseudoOuEmail(String pseudoOuEmail) throws SQLException {
        return userDAO.selectByPseudoOrEmail(pseudoOuEmail);
    }
	
	public User selectUserByPseudo(String comparedPseudo) throws SQLException {
		return userDAO.selectByPseudo(comparedPseudo);
	}
	
	public User selectUserByEmail(String comparedEmail) throws SQLException {
		return userDAO.selectByEmail(comparedEmail);
	}

	
	// CHECK SECTION ------------------------------------------------------------------------
	
//noUser
	
	//Pseudo
	
	public boolean checkPseudoAvailability(String pseudo) throws SQLException {			
	    return userDAO.doesThisPseudoAlreadyExists(pseudo);
	}
	
	//Telephone
	
	public static boolean validatePhoneNumber(String phoneNumber) {
		String phoneNumberPattern = "^\\+?[1-9]\\d{1,14}$";
		return phoneNumber.matches(phoneNumberPattern);
	}	

	// VALIDATIO SECTION ------------------------------------------------------------------------
	
	//nom	
	public static boolean validateNom(String nom) {
		String nomPattern = "^[a-zA-Z]+$";
		return nom.matches(nomPattern);
		}
	
	//prenom	
	public static boolean validatePrenom(String prenom) {
		String prenomPattern = "^[a-zA-Z]+$";
		return prenom.matches(prenomPattern);
		}
	
	//email
	public boolean checkEmailAvailability(String email) throws SQLException{		
		return userDAO.doesThisEmailAlreadyExists(email);
	}

	public static boolean validateEmail(String email) {
		String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
		return email.matches(emailPattern);
	}

	//rue
	//ville
	
	//codePostal	
	public static boolean validateCodePostal(String codePostal) throws SQLException{
		String postalCodePattern = "\\d{5}";
		return codePostal.matches(postalCodePattern);
	}

	//motDePasse	
	public static boolean validateMotDePasse(String motDePasse) throws SQLException{
		return motDePasse.length() >=6 && motDePasse.matches(".*[A-Z].*");		
	}
	
	//credit
	//administrateur

	//Password

	public boolean checkPassword(int userId, String password) throws BusinessException, SQLException {
		// Retrieve the user from the database based on the userId
		User user = selectUserByID(userId);

		// Check the password
		if (BCrypt.checkpw(password, user.getMotDePasse())) {
			return true;
		} else {
			return false;
		}
	}

	public void validate(User user, String passwordConfirmation) throws BusinessException, SQLException {
		BusinessException businessException = new BusinessException();

		if (checkPseudoAvailability(user.getPseudo())) {
			businessException.ajouterErreur(CodeErreur.PSEUDO_EXISTANT);
		}

		if (!validateEmail(user.getEmail())) {
			businessException.ajouterErreur(CodeErreur.EMAIL_INVALIDE);
		}

		if (checkEmailAvailability(user.getEmail())) {
			businessException.ajouterErreur(CodeErreur.EMAIL_EXISTANT);
		}

		if (!validateNom(user.getNom())) {
			businessException.ajouterErreur(CodeErreur.NOM_INVALIDE);
		}

		if (!validatePrenom(user.getPrenom())) {
			businessException.ajouterErreur(CodeErreur.PRENOM_INVALIDE);
		}

		if (!validateCodePostal(user.getCodePostal())) {
			businessException.ajouterErreur(CodeErreur.CODE_POSTAL_INVALIDE);
		}

		if (!validateMotDePasse(user.getMotDePasse())) {
			businessException.ajouterErreur(CodeErreur.MDP_INVALIDE);
		}

		if (!user.getMotDePasse().equals(passwordConfirmation)) {
			businessException.ajouterErreur(CodeErreur.CONFIRMATION_MDP_INCORRECTE);
		}

		if (businessException.hasErreurs()) {
			throw businessException;
		}
	}

	public void validateAndUpdateUser(User user, String currentPassword, String newPassword, String confirmationPassword, String pseudo, String nom, String prenom, String email, String telephone, String rue, String codePostal, String ville) throws BusinessException, SQLException {
		BusinessException businessException = new BusinessException();

		// Vérifiez si le mot de passe actuel est correct
		if (!this.checkPassword(user.getNoUtilisateur(), currentPassword)) {
			businessException.ajouterErreur(CodeErreur.MDP_INCORRECT);
		}

		// Vérifiez si le nouveau mot de passe et la confirmation du mot de passe correspondent
		if (newPassword != null && !newPassword.isEmpty() && confirmationPassword != null && !confirmationPassword.isEmpty()) {
			if (!newPassword.equals(confirmationPassword)) {
				businessException.ajouterErreur(CodeErreur.CONFIRMATION_MDP_INCORRECTE);
			} else if (!UserManager.validateMotDePasse(newPassword)) {
				businessException.ajouterErreur(CodeErreur.MDP_INVALIDE);
			} else {
				user.setMotDePasse(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
			}
		}

		// Vérifiez si le pseudo est disponible
		if (pseudo != null && !pseudo.isEmpty() && !pseudo.equals(user.getPseudo())) {
			if (this.checkPseudoAvailability(pseudo)) {
				businessException.ajouterErreur(CodeErreur.PSEUDO_EXISTANT);
			} else {
				user.setPseudo(pseudo);
			}
		}

		// Vérifiez si l'email est disponible
		if (email != null && !email.isEmpty() && !email.equals(user.getEmail())) {
			if (!validateEmail(email)) {
				businessException.ajouterErreur(CodeErreur.EMAIL_INVALIDE);
			} else if (this.checkEmailAvailability(email)) {
				businessException.ajouterErreur(CodeErreur.EMAIL_EXISTANT);
			} else {
				user.setEmail(email);
			}
		}

		// Mettez à jour les autres champs de l'utilisateur
		if (nom != null && !nom.isEmpty() && !nom.equals(user.getNom())) {
			user.setNom(nom);
		}

		if (prenom != null && !prenom.isEmpty() && !prenom.equals(user.getPrenom())) {
			user.setPrenom(prenom);
		}

		if (telephone != null && !telephone.isEmpty() && !telephone.equals(user.getTelephone())) {
			user.setTelephone(telephone);
		}

		if (rue != null && !rue.isEmpty() && !rue.equals(user.getRue())) {
			user.setRue(rue);
		}

		if (codePostal != null && !codePostal.isEmpty() && !codePostal.equals(user.getCodePostal())) {
			user.setCodePostal(codePostal);
		}

		if (ville != null && !ville.isEmpty() && !ville.equals(user.getVille())) {
			user.setVille(ville);
		}

		// S'il y a des erreurs, lancez une exception
		if (businessException.hasErreurs()) {
			throw businessException;
		}

		// Si tout va bien, mettez à jour l'utilisateur
		this.updateUser(user);
	}

}