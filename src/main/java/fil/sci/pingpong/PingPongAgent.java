package fil.sci.pingpong;

import jade.core.Agent;

/**
 * Class définit un agent ping ponog, avec le comportement ping pong
 * 
 * @author SAUVAGE Celestine - HALABI Sami
 */
public class PingPongAgent extends Agent{
	
	/**
	 * Id unique de la class
	 */
	private static final long serialVersionUID = 546730946268726148L;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void setup() {
		this.addBehaviour(new PingPongBehaviour(this));
	} 
}
