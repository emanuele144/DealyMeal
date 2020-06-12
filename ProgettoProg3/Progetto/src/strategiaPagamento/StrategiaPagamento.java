package strategiaPagamento;

/**********************************************************
 * Questa interfaccia definisce la strategia di pagamento *
 **********************************************************/
public interface StrategiaPagamento {
	
	//Istanziamo questo metodo che verr� implementato diversamente in base alla classe che lo estender�.
	public void pagamento(float costo, int numeroCarta, int numeroPrenotati, int orarioP,String username, int idMenuP,int idBibita);
	
}
