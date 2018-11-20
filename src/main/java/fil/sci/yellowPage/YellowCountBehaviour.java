package fil.sci.yellowPage;

import java.util.List;
import java.util.Random;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.util.Logger;

/**
 * Définit un comportement pour l'agent bob
 * 
 * @author SAUVAGE Celestine - HALABI Sami
 */
public class YellowCountBehaviour extends Behaviour{
	 Logger logger = jade.util.Logger.getMyLogger(this.getClass().getName()); 

	/**
	 * Id unique de la class
	 */
	private static final long serialVersionUID = 6066193130964823289L;
	
	/**
	 * 
	 */
	private List<AID> friends;
	
	/**
	 * 
	 */
	private int initTimer;
	
	/**
	 * 
	 */
	private boolean done;
	
	//Constructor 
	public YellowCountBehaviour(List<AID> friends){
		this.friends = friends;
		this.done = false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void action() {
		ACLMessage message = this.myAgent.receive();
		int performative = message.getPerformative();
		if(message != null && ACLMessage.INFORM == performative) {
			try{
				int value = Integer.parseInt(message.getContent());
				if(value != 0){
					ACLMessage response = new ACLMessage(ACLMessage.INFORM);
					response.addReceiver(this.friends.get(new Random().nextInt(this.friends.size())));
					response.setContent(Integer.toString(value-1));
					
					this.myAgent.send(response);
					logger.log(Logger.INFO,String.format("[Agent][%s] Message send", this.myAgent.getName()));
				}
				else{
					this.done = true;
					return;
				}
		    }catch(NumberFormatException e){
				logger.log(Logger.WARNING,String.format("[Agent][%s] Le message n'est pas une bombe", this.myAgent.getName()));
		    }
		}
		else if(ACLMessage.FAILURE == performative){
			this.friends.remove(message.getSender());
		}
		else{
			logger.log(Logger.WARNING,String.format("[Agent][%s] Le message n'est pas une initialisation", this.myAgent.getName()));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean done() {
		if(this.done){
			for(AID friend : this.friends){
				ACLMessage response = new ACLMessage(ACLMessage.FAILURE);
				response.addReceiver(friend);
				this.myAgent.send(response);
			}
			ACLMessage response = new ACLMessage(ACLMessage.INFORM);
			response.addReceiver(this.friends.get(new Random().nextInt(this.friends.size())));
			response.setContent(Integer.toString(this.initTimer));
			
			this.myAgent.send(response);
		}
		return this.done;
	}
}
