/**
 * 
 */
package fil.sci.rebours;

import jade.core.Agent;

/**
 * Class défini un agent qui affiche un message toute les 5 seconds
 * 
 * @author SAUVAGE Celestine - HALABI Sami
 */
public class CountArm extends Agent {

	/**
	 * Id unique de la class
	 */
	private static final long serialVersionUID = -3201016871478557595L;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setup() {
		this.addBehaviour(new CountBehaviour(this));
	}
}
