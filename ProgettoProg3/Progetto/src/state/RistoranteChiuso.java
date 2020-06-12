package state;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import viewHomePage.HomePage;

/******************************************************
 * Questa classe permette di cambiare,in questo caso, *
 * lo stato corrente del ristorante	in "chiuso"       *
 ******************************************************/
public class RistoranteChiuso implements Stato{
	
	ImageIcon iconaErrore = new ImageIcon(HomePage.class.getResource("/images/notPrenotazione.jpg"));
	
	//Settiamo la variabile di stato in chiuso.
	private static final String stato ="Chiuso";
	
	//Questo metodo, in base al costruttore richiamato esternamente, fa visualizzare un comportamento diverso.
	@Override
	public void mostraStatoRist(JFrame frameVM) {
		 JOptionPane.showMessageDialog(frameVM, "Il ristorante è "+stato+" , non puoi prenotarti", null, 0, iconaErrore);		 
	}

}