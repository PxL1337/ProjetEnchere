package fr.eni.projetenchere.bll;

public class UtilisateurManager {

	private static UtilisateurManager instance;
	
	public static UtilisateurManager getInstance()
	{
		if (instance == null) {
			instance = new UtilisateurManager();
		}
		
		return instance;
	}
	
	private UtilisateurManager() {}
}