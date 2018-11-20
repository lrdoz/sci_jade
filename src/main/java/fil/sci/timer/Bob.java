package fil.sci.timer;

import jade.core.Agent;

/**
 * Class définie un agent qui affiche un message toute les 5 seconds
 * 
 * @author SAUVAGE Celestine - HALABI Sami
 */
public class Bob extends Agent {

	/**
	 * Serialisation de class
	 */
	private static final long serialVersionUID = 3714589918558831764L;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void setup() {
		//Récupération des argument
		
		Integer timer = new Integer("0");
		
		Object[] args = getArguments();
		
		// Si un paramètre on récupère la valeur
		if(args != null && args.length > 0) 
			timer = new Integer((String) args[0]);
		
	  	System.out.println("[AGENTS] Coucou je viens de commencer à être initialisé\n");
	  	
		try {
			while(true) {
				Thread.sleep(timer);
				System.out.println("[AGENTS] Hello World! My name is "+getLocalName());
			}
		} catch (InterruptedException e) {
			throw new IllegalStateException("[AGENTS] Problème lors de l'éxécution du timer");
		}
	} 
}