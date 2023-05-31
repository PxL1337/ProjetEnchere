package fr.eni.projetenchere.bll;

public class RetraitManager 
{
	private static RetraitManager instance;
	
	public static RetraitManager getInstance()
	{
		if (instance == null) {
			instance = new RetraitManager();
		}
		
		return instance;
	}
	
	private RetraitManager() {}
}