package fil.sci.timer;

import jade.core.Agent;

/**
 * Class définit un agent qui affiche un message toute les 5 seconds
 * 
 * @author SAUVAGE Celestine - HALABI Sami
 */
public class Bob extends Agent {

	/**
	 * Id unique de la class
	 */
	private static final long serialVersionUID = 3714589918558831764L;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void setup() {
	  	System.out.println("[AGENTS] Coucou je viens de commencer à être initialisé\n");

		//Récupération des argument
		Integer timer = new Integer("0");
		
		Object[] args = getArguments();
		
		// Si un paramètre on récupère la valeur
		if(args != null && args.length > 0) 
			timer = new Integer((String) args[0]);
		
		this.addBehaviour(new BobBehaviour(this, timer.longValue()));
	} 
}