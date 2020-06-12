package classidb;
import javax.swing.JFrame;
import state.Stato;

/************************************************************************
 * Questa classe serve per definire le informazioni di ogni ristorante  *
 * associato ad un unico ristoratore. 				                    *
 ************************************************************************/
public class Ristorante {
	
		private String nomeRist;
		private String Via;
		private int CAP;
		private String citta;
		private String ristoratore;
		private int orarioC;
		private int orarioA;
		private Stato stato;
		private int capienza; 
		private int esitoR;
	   
		public Ristorante(String nomeRist, String Via, int CAP, String citta,int capienza, int orarioA, int orarioC, String ristoratore, int esitoR) {
			this.nomeRist = nomeRist;
			this.Via = Via;
			this.CAP = CAP;
			this.citta = citta;
			this.ristoratore = ristoratore;
			this.orarioA = orarioA;
			this.orarioC = orarioC;
			this.capienza = capienza;
			this.esitoR = esitoR;
		}
		
		public Ristorante(String nomeRist, String ristoratore) {
			this.nomeRist = nomeRist;
			this.ristoratore = ristoratore;
		}
		

		
		public Ristorante(String nomeRist) {
			this.nomeRist = nomeRist;
		}
		
		public Ristorante(Ristorante R) {
			this.nomeRist = R.nomeRist;
			this.Via = R.Via;
			this.citta = R.citta;
			this.CAP = R.CAP;
			this.ristoratore = R.ristoratore;
		}
		
		public Ristorante() {
			
		}

		public void setStato(Stato stato) {
	        this.stato = stato;
	    }
	 
	    public void statoCorrente(JFrame frameVM) {
	        stato.mostraStatoRist(frameVM);
	    }
		public String getNomeRist() {
			return this.nomeRist;
		}
		
	
		public String getVia() {
			return this.Via;
		}
		
		public int getEsitoR() {
			return this.esitoR;
		}
		
		public int getCapienza() {
			return this.capienza;
		}
		
		
		public int getCAP() {
			return this.CAP;
		}
		
		public int getOrarioA() {
			return this.orarioA;
		}
		
		public int getOrarioC() {
			return this.orarioC;
		}
		
		public String getCitta() {
			return this.citta;
		}
		
		public String getristoratore() {
			return this.ristoratore;
		}
		
		@Override
		public String toString() {
			return nomeRist;
		}
		
}

