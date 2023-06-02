package fr.eni.projetenchere.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.projetenchere.bo.User;
import fr.eni.projetenchere.dal.jdbc.UserDAOJdbcImplementation;

public class UserManager {
	
	//SINGLETON

	private static UserManager instance;
	
	public static UserManager getInstance()
	{
		if (instance == null) {
			instance = new UserManager();
		}
		
		return instance;
	}

	private UserDAOJdbcImplementation userDAOJdbc;	
	
	public UserManager()
	{
		userDAOJdbc = new UserDAOJdbcImplementation();
	}
	
	public void insertUser(User user){
	    userDAOJdbc.insert(user);	
	}
	
	public void updateUser(User user){
	    userDAOJdbc.update(user);	
	}
	
	public void deleteUser(User user){
	    userDAOJdbc.delete(user);	
	}
	
	public void updateUserCredit(User user, int newValue) {
		userDAOJdbc.updateCredit(user, newValue);
	}
	
	public void updateUserIsAdmin(User user, boolean newValue) {
		userDAOJdbc.updateIsAdmin(user, newValue);
	}
	
	public User selectUserByID(int ID) {
		return userDAOJdbc.selectByID(ID);
	}
	
	public List<User> selectAllUser() {
		return userDAOJdbc.selectAll();
	}
	
	public User selectUserByPseudoOuEmail(String pseudoOuEmail) throws SQLException {
        return userDAOJdbc.selectByPseudoOrEmail(pseudoOuEmail);
    }
	
	public User selectUserByPseudo(String comparedPseudo){
		return userDAOJdbc.selectByPseudo(comparedPseudo);
	}
	
	public User selectUserByEmail(String comparedEmail){
		return userDAOJdbc.selectByEmail(comparedEmail);
	}
	
	// CHECK SECTION ------------------------------------------------------------------------
	
//noUser
	
	//Pseudo
	
	public boolean checkPseudoAvailability(String pseudo) {			
	    return userDAOJdbc.doesThisPseudoAlreadyExists(pseudo);
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
	public boolean checkEmailAvailability(String email) {		
		return userDAOJdbc.doesThisEmailAlreadyExists(email);
	}


	//rue
	//ville
	
	//codePostal	
	public static boolean validateCodePostal(String codePostal) {
		String postalCodePattern = "\\d{5}";
		return codePostal.matches(postalCodePattern);
	}

	//motDePasse	
	public static boolean validateMotDePasse(String motDePasse) {
		return motDePasse.length() >=6 && motDePasse.matches(".*[A-Z].*");		
	}
	
	//credit
	//administrateur
}