package fr.eni.projetenchere.messages;

import java.util.ResourceBundle;

public class LecteurMessage {
	private final static String MESSAGES_PROPERTIES =  "fr.eni.projetenchere.messages_erreur";
	private static ResourceBundle rb;
	
	static {
		try {
			rb = ResourceBundle.getBundle(MESSAGES_PROPERTIES);
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
		
	}
	
	public static String getMessage(int code) {
		String message = "";
		try {
			if (rb != null) {
				message ="Erreur "+ code + " : " + rb.getString(String.valueOf(code));
			}else {
				message = "problème à la lecture du fichier contenant les messages ";
			}
		}catch (Exception e){
			e.printStackTrace();
			e.getMessage();
			message = "Une erreur inconnu est survenue";
		}
		
		
		return message;
	}
	
}


			
			
