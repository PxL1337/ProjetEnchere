package fr.eni.projetenchere.bo;

import fr.eni.projetenchere.bll.CodeErreur;
import fr.eni.projetenchere.exception.BusinessException;

public class Retrait {
	
    //-------------------- VARIABLES ZONE --------------------//
	
    private int noArticle;
    private String rue;
    private String codePostal;
    private String ville;

	private BusinessException businessException = new BusinessException();
	
    //-------------------- CONSTRUCTOR ZONE --------------------//
	
	public Retrait() {}
	
	 public Retrait(int noArticle, String rue, String codePostal, String ville, int articleID) throws BusinessException 
	 {
		 	setNoArticle(articleID);		 	
			setRue(rue);
			setCodePostal(codePostal);
			setVille(ville);
	}

	//---------------------------------------------GETTER SETTER ZONE-------------------------------------------------------//
	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int articleID) throws BusinessException 
	{
		this.noArticle = articleID;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) throws BusinessException
	{
		if (!isAddressValid(rue)) {
			businessException.ajouterErreur(CodeErreur.RETRAIT_RUE_TROP_LONG);
			throw businessException;
		}
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) throws BusinessException
	{
		if (!isZipCodeValid(codePostal)) {
			businessException.ajouterErreur(CodeErreur.RETRAIT_CODE_POSTAL_TROP_LONG);
			return;
		}
		
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) throws BusinessException
	{
		if (!isCityValid(ville)) {
			businessException.ajouterErreur(CodeErreur.RETRAIT_VILLE_TROP_LONGUE);
			return;
		}
		
		this.ville = ville;
	}

	private boolean isAddressValid(String address) {
		return address != null && !address.isEmpty() && address.length() <= 30;
	}
	
	private boolean isZipCodeValid(String zipCode)
	{
		return zipCode.length() <= 15;
	}
	
	private boolean isCityValid(String city)
	{
		return city.length() <= 30;
	}

    //---------------------------------------TO STRING ZONE---------------------------------------//
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb
		.append("Retrait de l'article N° : ").append(noArticle)
		.append("\n adresse : \n").append(rue + "\n")
		.append(codePostal)
		.append(ville);
		
		return sb.toString();
	}
    // --------------------------------------------------------------------------------------------------------------//

}
