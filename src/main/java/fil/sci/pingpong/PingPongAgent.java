package fil.sci.pingpong;

import jade.core.Agent;

/**
 * 
 * 
 * @author SAUVAGE Celestine - HALABI Sami
 */
public class PingPongAgent extends Agent{
	
	/**
	 * 
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
