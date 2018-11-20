package fil.sci.timer;

import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;

/**
 * Définit un comportement pour l'agent bob
 * 
 * @author SAUVAGE Celestine - HALABI Sami
 */
public class BobBehaviour extends WakerBehaviour{
	
	/**
	 * Id unique de la class
	 */
	private static final long serialVersionUID = -3872520359427518921L;

	
	//Constructor
	public BobBehaviour(Agent a, long timeout) {
		super(a, timeout);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void handleElapsedTimeout() {
		System.out.println("[AGENTS] Hello World! My name is ");
	}
}
