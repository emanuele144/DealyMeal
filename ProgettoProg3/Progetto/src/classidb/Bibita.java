package classidb;

/*******************************************************************
 * Questa classe serve per definire le bibite da associare ai menù *
 * dei ristoratori.												   *
 *******************************************************************/
public class Bibita {

	private String nomeBibita;
	private float prezzo;
	private String username;
	private String tipologia;
	
	public Bibita(float prezzo,String nomeBibita,String username, String tipologia) {
		this.nomeBibita = nomeBibita;
		this.prezzo = prezzo;
		this.username=username;
		this.tipologia=tipologia;
	}

	public Bibita(String nomeBibita) {
		this.nomeBibita = nomeBibita;
	}
	
	public Bibita() {
		
	}

	public String getNomeBibita() {
		return this.nomeBibita;
	}
	
	public String getTipologia() {
		return this.tipologia;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public float getPrezzo() {
		return this.prezzo;
	}
	
	@Override
	public String toString() {
		return nomeBibita;
	}

	
}