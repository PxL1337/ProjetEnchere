package fr.eni.projetenchere.messages;

import java.util.ResourceBundle;

import jakarta.servlet.http.HttpServletRequest;

public class LecteurMessage {
	private final static String MESSAGES_PROPERTIES =  "messages_erreurs";
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
	
	public static String getErrorCodeFromURI(HttpServletRequest request, String errorPattern )
	{
		String URI = request.getQueryString();
		String errorCode = "";
		
		int errorPatternIndex = URI.indexOf(errorPattern);
		boolean occurenceFound = errorPatternIndex != -1;
		
		if (occurenceFound)
		{
			int errorCodeStartIndex = errorPatternIndex + errorPattern.length();
			errorCode = URI.substring(errorCodeStartIndex);
		}
		
		return errorCode;
	}
}