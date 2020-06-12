package login;
import java.sql.DriverManager;
import java.sql.SQLException;
import classidb.Database;

/****************************************************************************
 * Questa classe viene invocata nell'autenticazione all'avvio del programma	*
 * Contiene il design pattern Singleton.									*
 ****************************************************************************/
public class Login {

	 private static Login istance = null; //Utilizzo del design pattern Singleton
	 private Login () { //Rendo il costruttore privato per utilizzarlo solo tramite getIstanced

	 }

	 public static Login getIstanced() {
		 if(istance == null) { //se non è ancora stato istanziato
			 istance = new Login(); //istanzialo
		 }
		 return istance; //ritorna l'istanza
	 }

	 // Metodo che permette l'autenticazione al database e al programma da parte del cliente e del ristoratore: si inseriscono in input username 
	 // e password, dopodichè questo metodo apre una connessione al database , si prende tutte le tuple dalla tabella di "utente" e confronta tutti i
	 // campi di "username" e "password" con quelli precedentemente detti; se li trova e il campo "ruolo" associato ad essi è 1, allora si entra
	 // come ristoratore , altrimenti come utente; se non li trova il metodo ritorna un esito negativo. 
	 public int autenticazione(String usernameDaVerificare, String passwordDaVerificare) {
		 boolean esito = false, esitoRuolo = false;
		
		 try{
	
				Database D = Database.getIstance();
			    Class.forName("com.mysql.cj.jdbc.Driver");
				D.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbristorante?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","1234");
				D.stmt = D.conn.createStatement();
				D.rs = D.stmt.executeQuery("SELECT * FROM utente");
				while( (D.rs.next()) && (esito == false) ) {
					 if(D.rs.getString("password").equals(passwordDaVerificare) && D.rs.getString("username").equals(usernameDaVerificare)) {
					       esito = true;
						   if(D.rs.getInt("ruolo") == 1) {					
							   esitoRuolo = true;					
						   }			
					 }      			
				}
		        
		 }catch(ClassNotFoundException e){
		        System.out.println(e);
		        
		 }catch(SQLException e){
		        System.out.println(e);
	
		 }
		 if(esito == true && esitoRuolo == true){	
		        return 1;	
		 }
	     if(esito == true && esitoRuolo == false){
	    	 	return 2;
	     }
		   		return -1;
	 }

}