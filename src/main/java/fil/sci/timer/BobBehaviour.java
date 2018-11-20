package fil.sci.timer;

import jade.core.behaviours.Behaviour;

/**
 * Définit un comportement pour l'agent bob
 * 
 * @author SAUVAGE Celestine - HALABI Sami
 */
public class BobBehaviour extends Behaviour{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3872520359427518921L;
	
	private Integer timer;
	
	
	//Constructor
	public BobBehaviour(Integer timer) {
		this.timer = timer;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void action() {
		System.out.println("[AGENTS] Hello World! My name is ");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean done() {
		return true;
	}

}
