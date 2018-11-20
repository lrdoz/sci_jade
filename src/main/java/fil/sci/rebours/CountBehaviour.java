/**
 * 
 */
package fil.sci.rebours;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.util.leap.Iterator;

/**
 * Class définie un agent qui affiche un message toute les 5 seconds
 * 
 * @author SAUVAGE Celestine - HALABI Sami
 */
public class CountBehaviour extends CyclicBehaviour {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 205596621055946442L;
	
	//Constructor
	public CountBehaviour(Agent agent) {
		this.myAgent = agent;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void action() {
		ACLMessage message = this.myAgent.receive();
		if(message != null) {
			ACLMessage reply = new ACLMessage(ACLMessage.INFORM);
			
			if(ACLMessage.REQUEST == message.getPerformative()) {
				Iterator replyTo = message.getAllReplyTo();
				while(replyTo.hasNext())
					reply.addReceiver((AID) replyTo.next());
			}
			else if(ACLMessage.INFORM == message.getPerformative())
				reply.addReceiver(reply.getSender());

			
			String myMessage = message.getContent();
		
			//Time est fini
			if(myMessage.equals("0")) {
				System.out.println("[Agent] BOOM");
				return;
			}
			reply.setContent(Integer.toString((Integer.parseInt(myMessage)-1)));
			this.myAgent.send(reply);
			}
		}
}
