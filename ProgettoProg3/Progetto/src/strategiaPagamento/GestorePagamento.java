package strategiaPagamento;
import enumerazione.Enum;

/**********************************************************************************************************************************
 * Questa classe implementa le strategie di pagamento: in base al valore della variabile Enum, passato come parametro di ingresso *
 * si utilizza una strategia piuttosto che un' altra.																			  *
 **********************************************************************************************************************************/
public class GestorePagamento {
	
	public static StrategiaPagamento setPagamentoStrategy(Enum Enum, float costo, int numeroCarta,int numeroPrenotati,int orarioP, String username, int idMenuP, int idBibita){
		
		StrategiaPagamento strategy=null;

			switch(Enum){
				case Bancomat:
						strategy = new BancomatStrategyA(); 
						strategy.pagamento(costo,numeroCarta,numeroPrenotati,orarioP,username,idMenuP,idBibita);
					break;
				case CartaDiCredito:
						strategy = new CartaDiCreditoStrategyB();
						strategy.pagamento(costo,numeroCarta,numeroPrenotati,orarioP,username,idMenuP,idBibita);
					break;	
				case PayPal:
						strategy = new PayPalStrategyC();
						strategy.pagamento(costo,numeroCarta,numeroPrenotati,orarioP,username,idMenuP,idBibita);
					break;		
			}
			return strategy;
	}
	
}
