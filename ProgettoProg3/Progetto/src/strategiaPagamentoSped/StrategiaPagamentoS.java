package strategiaPagamentoSped;

/****************************************************************************
 * Questa interfaccia definisce la strategia di pagamento per la spedizione *
 ****************************************************************************/
public interface StrategiaPagamentoS {
	
	//Istanziamo questo metodo che verr� implementato diversamente in base alla classe che lo estender�.
	public void pagamentoS(String Via, int Cap, String citta, String giorno, int ora, int quantitaP, String cliente, int idPiatto, float importo, int numCarta);
	
}
