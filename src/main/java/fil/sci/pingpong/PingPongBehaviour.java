package fil.sci.pingpong;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

/**
 * Définit un comportement de ping pong
 * 
 * @author SAUVAGE Celestine - HALABI Sami
 */
public class PingPongBehaviour extends Behaviour {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3351151916341812194L;
	
	
	//Constructor
	public PingPongBehaviour(Agent agent) {
		this.myAgent = agent;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void action() {
		while (true) {
			ACLMessage message = this.myAgent.receive();
			
			if(message != null) {
				ACLMessage reply = new ACLMessage(ACLMessage.INFORM);
				reply.addReceiver(message.getSender());
				String content = "";
				if(ACLMessage.INFORM == message.getPerformative()) { 
					if(message.getContent().equals("Ping")) {
						System.out.println("[Agent] Pong");
						content = "Pong";
					}
					else if (message.getContent().equals("Pong")) {
						System.out.println("[Agent] Ping");
						content = "Ping";
					}
					else {
						System.out.println("[Agent] What?????");
						content = "What";
					}
					reply.setContent(content);
					this.myAgent.send(reply);
				}
				else
					System.out.println("[Agent] What?????");
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean done() {
		return true;
	}
}
