package fil.sci.pingpong;

import java.util.Map;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * Définit un comportement de ping pong
 * 
 * Lorsque l'agent recois un message, il répond avec soit ping si pong, soit pong si ping, sinon what ?
 * 
 * @author SAUVAGE Celestine - HALABI Sami
 */
public class PingPongBehaviour extends CyclicBehaviour {

	/**
	 * Id unique de la class
	 */
	private static final long serialVersionUID = -3351151916341812194L;
	
	/**
	 * Map response
	 */
	private Map<String, String> mapResponse;
	
	//Constructor
	public PingPongBehaviour(Agent agent) {
		this.myAgent = agent;
		
		this.mapResponse.put("Ping", "Pong");
		this.mapResponse.put("Pong", "Ping");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void action() {
		ACLMessage message = this.myAgent.receive();
		
		if(message != null) {
			ACLMessage reply = new ACLMessage(ACLMessage.INFORM);
			reply.addReceiver(message.getSender());
			if(ACLMessage.INFORM == message.getPerformative()) { 
				String content = message.getContent();
				if(this.mapResponse.containsKey(content)){
					reply.setContent(this.mapResponse.get(content));
				}
				else{
					reply.setContent("What???");
				}
				
				System.out.println(reply.getContent());
				this.myAgent.send(reply);
			}
			else
				System.out.println("[Agent] What?????");
		}
	}
}
