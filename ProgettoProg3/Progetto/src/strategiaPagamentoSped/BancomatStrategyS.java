package strategiaPagamentoSped;
import java.sql.SQLException;
import classidb.Database;

/*****************************************************************************************
 * Questa classe definisce la strategia di pagamento per la spedizione di tipo Bancomat  *
 *****************************************************************************************/
public class BancomatStrategyS implements StrategiaPagamentoS
{
		/*Questo metodo implementa la strategia di pagamento di tipo Bancomat: inserisce una tupla nella tabella spedizione con la tipologia di
		* carta = Bancomat.*/
		@Override
		public void pagamentoS(String Via, int Cap, String citta, String giorno, int ora, int quantitaP, String cliente, int idPiatto, float importo, int numCarta){
			
			Database D = Database.getIstance();
			
			try{
				  	Class.forName("com.mysql.jdbc.Driver");
				  	D.openConnection();        
				  	D.stmt.executeUpdate("INSERT INTO `dbristorante`.`spedizione` (`Via`, `CAP`,`citta`,`giorno`,`ora`,`quantitaP`,`utente`, `idPiattoS`, `tipologiaCarta`, `importo`, `numCarta`) VALUES ('"+Via+"', '"+Cap+"', '"+citta+"', '"+giorno+"', '"+ora+"', '"+quantitaP+"', '"+cliente+"', '"+idPiatto+"', 'Bancomat', '"+importo+"', '"+numCarta+"')" );           	
		            
			}catch(ClassNotFoundException e) {
					System.out.println(e);
		    }
		    catch(SQLException e)
		    {
		    		System.out.println(e);
		    }
			
		}

}