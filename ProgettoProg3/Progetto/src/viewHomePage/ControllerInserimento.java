package viewHomePage;

/**************************************************************************
 * Questa classe serve per controllare gli inserimenti errati dell'utente * 
 **************************************************************************/
public class ControllerInserimento{
	//Questa metodo controlla se la stringa passata come parametro di ingresso sia un numero oppure no.
	 public static boolean eUnNumero(String str){  
	  
		 try{  
			 @SuppressWarnings("unused")
			double d = Double.parseDouble(str);  
		 }  

		 catch(NumberFormatException nfe){  
			 return false;  
		 }  
		 	return true;  
	}

	 //Questo metodo controlla se la stringa passata sia null o vuota.
	 public static boolean eVuota(String str) {

		 if ((str.equals(null) || (str.equals(""))))
			 return true;
		 else 
			 return false;
	 }

}