/**
 * 
 */
package fil.sci.rebours;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.util.Logger;

/**
 * Class définit un agent qui déclenche une bombe
 * 
 * @author SAUVAGE Celestine - HALABI Sami
 */
public class CountBehaviour extends CyclicBehaviour {
	 Logger logger = jade.util.Logger.getMyLogger(this.getClass().getName()); 
	 
		/**
		 * Id unique de la class
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
			logger.log(Logger.INFO,String.format("[Agent][%s] Receive Message", this.myAgent.getName()));
			
			ACLMessage response = message.createReply();
			response.setPerformative(ACLMessage.INFORM);
			
			String myMessage = message.getContent();
			//Time est fini
			if(myMessage.equals("0")) {
				logger.log(Logger.INFO,String.format("[Agent][%s] Boom", this.myAgent.getName()));
				return;
			}
			try{
				response.setContent(Integer.toString((Integer.parseInt(myMessage)-1)));
				this.myAgent.send(response);
				logger.log(Logger.INFO,String.format("[Agent][%s] Message send", this.myAgent.getName()));
		    }catch(NumberFormatException e){
				logger.log(Logger.WARNING,String.format("[Agent][%s] Le message n'est pas une bombe", this.myAgent.getName()));
		    }
		}
	}
}
