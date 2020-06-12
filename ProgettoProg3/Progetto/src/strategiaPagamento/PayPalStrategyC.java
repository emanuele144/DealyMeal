package strategiaPagamento;
import java.sql.SQLException;

import classidb.Database;

/********************************************************************
 * Questa classe definisce la strategia di pagamento di tipo PayPal *
 ********************************************************************/
public class PayPalStrategyC implements StrategiaPagamento {
	
	/*Questo metodo implementa la strategia di pagamento di tipo PayPal: inserisce una tupla nella tabella pagamento con la tipologia di
	* carta = PayPal.*/
	@Override
	public void pagamento(float costo, int numeroCarta,int numeroPrenotati, int orarioP, String username, int idMenuP, int idBibita){
		
		Database D = Database.getIstance();
		
		try{
			  	Class.forName("com.mysql.jdbc.Driver");
			  	D.openConnection();        
			  	D.stmt.executeUpdate("INSERT INTO `dbristorante`.`pagamento` (`tipologiaCarta`, `importo`,`numCarta`,`numeroPrenotati`,`orarioPrenotazione`,`usernameCl`,`idMenuP`, `idAggiunta`) VALUES ('PayPal', '"+costo+"', '"+numeroCarta+"','"+numeroPrenotati+"', '"+orarioP+"', '"+username+"', '"+idMenuP+"', '"+idBibita+"')" );         
	            
		}catch(ClassNotFoundException e) {
	        	System.out.println(e);
	    }
	    catch(SQLException e)
	    {
	        	System.out.println(e);
	    }
	}
	
}