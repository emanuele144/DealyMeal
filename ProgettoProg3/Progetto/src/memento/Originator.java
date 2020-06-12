package memento;

/***********************************************************************
 * Questa classe definisce l'Originator che acquisisce lo stato        *
 * corrente e lo stato salvato precedentemente della spesa effettuata  *
 * dal cliente. 			 										   *
 ***********************************************************************/
public class Originator {
	
	//Definizione dell'ultima Spesa e dell'ultimo pagamento effettuati dal cliente.
	private float ultimoPagamento;
	private float spesaTotaleCorrente;
	
	//Istanziamo l'oggetto della classe. 
	@SuppressWarnings("unused")
	private Originator originatorCliente;

	//Richiama il costruttore per creare un nuovo oggetto della classe.
	public Originator() {
		originatorCliente = this;
	}

	//Metodo che setta il nuovo valore della spesa e l'ultimo pagamento.
	public void nuovaSpesa(float importo) {
		ultimoPagamento = importo;
		spesaTotaleCorrente = spesaTotaleCorrente + ultimoPagamento;
	}
	
	//Settiamo la spesa corrente.
	public void setSpesaTotaleCorrente(float spesa){
		spesaTotaleCorrente = spesa;
	}
	
	//Settiamo l'ultimo pagamento effettuato dal cliente.
	public void setUltimoPagamento(float pag){
		ultimoPagamento = pag;
	}
	
	//Prendiamo il valore corrente della spesa.
	public float getSpesaTotaleCorrente(){
		return spesaTotaleCorrente;
	}
	
	//Prendiamo l'ultimo pagamento effettuato dal cliente. 
	public float getUltimoPagamento(){
		return ultimoPagamento;
	}
	
	//Metodo che crea il memento
	public Memento creaMemento(){
		return new SpesaMemento();
	}
	
	//Classe interna che implementa il memento.
	class SpesaMemento implements Memento {
		
		//variabili dell'ulitmo stato catturato
		private float mem_SpesaTotaleCorrente;
		private float mem_ultimoPagamento;
		
		//Crea un nuovo memento
		public SpesaMemento(){
			mem_SpesaTotaleCorrente = spesaTotaleCorrente;
			mem_ultimoPagamento = ultimoPagamento;
		}
		
		//Si ripristina allo stato precedentemente catturato.
		public void restoreState() {
			spesaTotaleCorrente = mem_SpesaTotaleCorrente;
			ultimoPagamento = mem_ultimoPagamento;
		}
	}

}
