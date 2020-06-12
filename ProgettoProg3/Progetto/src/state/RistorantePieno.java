package state;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import viewHomePage.HomePage;

/******************************************************
 * Questa classe permette di cambiare,in questo caso, *
 * lo stato corrente del ristorante	in "pieno"        *
 ******************************************************/
public class RistorantePieno implements Stato{
	
	ImageIcon iconaErrore = new ImageIcon(HomePage.class.getResource("/images/notPrenotazione.jpg"));
	
	//Settiamo la variabile di stato in pieno.
	private static final String stato ="capienza massima";
	
	//Questo metodo, in base al costruttore richiamato esternamente, fa visualizzare un comportamento diverso.
	@Override
	public void mostraStatoRist(JFrame frameVM) {
		 JOptionPane.showMessageDialog(frameVM, "Il ristorante con i posti inseriti supera la "+stato+", non puoi prenotarti.", null, 0, iconaErrore);
	}
}
