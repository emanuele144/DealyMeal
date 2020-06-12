package state;
import javax.swing.JFrame;

/******************************************************
 * Questa classe permette di cambiare,in questo caso, *
 * lo stato corrente del ristorante	                  *
 ******************************************************/
public interface Stato {
	
	//Mostra lo stato corrente di un ristorante.
	public void mostraStatoRist(JFrame frameVM);
	
}
