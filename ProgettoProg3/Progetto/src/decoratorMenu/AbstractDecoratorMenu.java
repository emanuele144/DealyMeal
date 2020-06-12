package decoratorMenu;

/*************************************************
 * Definizione della classe astratta "Decorator" *
 *************************************************/
public abstract class AbstractDecoratorMenu extends AbstractMenu{
	
	//Definizioni dei metodi e dell'oggetto della classe da ereditare.
	protected AbstractMenu abstractMenu;
	
	public abstract String getGiorno();
	
	public abstract float getPrezzo();
	
}
