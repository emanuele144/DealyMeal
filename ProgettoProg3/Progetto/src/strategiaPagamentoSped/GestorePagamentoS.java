package strategiaPagamentoSped;
import enumerazione.Enum;

/**********************************************************************************************************************************
 * Questa classe implementa le strategie di pagamento: in base al valore della variabile Enum, passato come parametro di ingresso *
 * si utilizza una strategia piuttosto che un' altra.																			  *
 **********************************************************************************************************************************/
public class GestorePagamentoS {
	
	public static StrategiaPagamentoS setPagamentoStrategy(Enum Enum,String Via, int Cap, String citta, String giorno, int ora, int quantitaP, String cliente, int idPiatto, float importo, int numCarta){
		
		StrategiaPagamentoS strategy=null;

			switch(Enum){
				case Bancomat:
						strategy = new BancomatStrategyS(); 
						strategy.pagamentoS(Via,Cap,citta,giorno,ora,quantitaP,cliente,idPiatto,importo,numCarta);
					break;
				case CartaDiCredito:
						strategy = new CartaDiCredito();
						strategy.pagamentoS(Via,Cap,citta,giorno,ora,quantitaP,cliente,idPiatto,importo,numCarta);
					break;	
				case PayPal:
						strategy = new PayPalS();
						strategy.pagamentoS(Via,Cap,citta,giorno,ora,quantitaP,cliente,idPiatto,importo,numCarta);
					break;		
			}
			return strategy;
	}
	
}
