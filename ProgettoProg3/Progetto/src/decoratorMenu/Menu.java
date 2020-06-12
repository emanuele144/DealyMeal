package decoratorMenu;

/*********************************
 * Definizione della classe Menu *
 *********************************/
public class Menu extends AbstractMenu{
	
	//Definizione delle variabili e dei costruttori.
	private String giorno;
	private float prezzo;
	
	public Menu(String giorno,float prezzo) {
		this.giorno = giorno;
		this.prezzo = prezzo;
	}
	
	//Overloading del costruttore
	public Menu(String giorno) {
		this.giorno = giorno;
	}
	
	public Menu(float prezzo) {
		this.prezzo = prezzo;
	}
	
	public Menu() {
		
	}
	
	//Metodo che ritorna il giorno
	@Override
	public String getGiorno() {
		return this.giorno;
	}
	
	//Metodo che ritorna il prezzo
	@Override
	public float getPrezzo() {
		return this.prezzo;
	}
	
	//Override della classe Object.
	@Override
	public String toString() {
		return giorno;
	}

}
	

