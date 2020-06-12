package state;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import viewHomePage.HomePage;

/******************************************************
 * Questa classe permette di cambiare,in questo caso, *
 * lo stato corrente del ristorante	in "aperto"       *
 ******************************************************/
public class RistoranteAperto implements Stato{
	
	ImageIcon iconaSuccesso = new ImageIcon(HomePage.class.getResource("/images/succCli.jpg"));
	
	//Settiamo la variabile di stato in aperto.
	private static final String stato ="Aperto";
	
	//Questo metodo, in base al costruttore richiamato esternamente, fa visualizzare un comportamento diverso.
	@Override
	public void mostraStatoRist(JFrame frameVM) {
		 JOptionPane.showMessageDialog(frameVM, "Il ristorante è "+stato+" , puoi prenotarti", null, 0, iconaSuccesso);
	}
}
