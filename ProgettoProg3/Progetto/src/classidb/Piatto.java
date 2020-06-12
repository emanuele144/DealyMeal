package classidb;

/*******************************************************************
 * Questa classe serve per definire i piatti da associare ai menù  *
 * dei ristoratori.												   *
 *******************************************************************/
public class Piatto{
	
	private String nomePiatto;
	private float prezzo;
	private String descrizione;
	private String tipologia;
	private String ristoratore;
	
	public Piatto(String nomePiatto, float prezzo, String descrizione, String tipologia, String ristoratore) {
		this.nomePiatto = nomePiatto;
		this.prezzo = prezzo;
		this.descrizione = descrizione;
		this.tipologia = tipologia;
		this.ristoratore = ristoratore;
	}
	
	public Piatto(String nomePiatto, float prezzo, String ristoratore) {
		this.nomePiatto = nomePiatto;
		this.prezzo = prezzo;
		this.ristoratore = ristoratore;
	}
	
	public Piatto(String nomePiatto, String descrizione, float prezzo, String ristoratore) {
		this.nomePiatto = nomePiatto;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.ristoratore = ristoratore;
	}
	
	public Piatto(String nomePiatto) {
		this.nomePiatto = nomePiatto;
	}
	
	public Piatto(Piatto P) {
		this.prezzo = P.prezzo;
		this.nomePiatto = P.nomePiatto;
		this.descrizione = P.descrizione;
		this.tipologia = P.tipologia;
		this.ristoratore = P.ristoratore;
	}
	
	public Piatto() {
		
	}

	public String getNomePiatto() {
		return this.nomePiatto;
	}
	
	public float getPrezzo() {
		return this.prezzo;
	}
	
	public String getDescrizione() {
		return this.descrizione;
	}
	
	public String getTipologia() {
		return this.tipologia;
	}
	
	@Override
	public String toString() {
		return nomePiatto;
	}
	
}