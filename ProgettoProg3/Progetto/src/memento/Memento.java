package memento;

/****************************************************************
 * Questa classe definisce il memento usato per l'operazione di *
 * pagamento della prenotazione da parte del cliente			*
 ****************************************************************/
public interface Memento {
	
	//Definizione del metodo di ripristino allo stato iniziale.
	public void restoreState();
	
}
