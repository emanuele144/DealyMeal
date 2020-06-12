package decoratorMenu;

/*****************************************************
 * Definizione della classe che estende il decorator *
 * del "Menu"                                        *
 *****************************************************/
public class BibitaDecorator extends AbstractDecoratorMenu{
	
	//Definizione variabile aggiuntiva
	private float costo;
	
	//Costruttore della classe
	public BibitaDecorator(AbstractMenu abstractMenu, float costo) {
		this.abstractMenu = abstractMenu;
		this.costo = costo;
	}
	
	//Override dei metodi della classe madre.
	@Override
	public String getGiorno() {
		return abstractMenu.getGiorno();
	}

	@Override
	public float getPrezzo() {
		return abstractMenu.getPrezzo()+costo;
	}

}
