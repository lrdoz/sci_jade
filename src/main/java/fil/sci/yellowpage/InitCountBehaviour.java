/**
 * 
 */
package fil.sci.yellowpage;

import java.util.ArrayList;
import java.util.List;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.util.Logger;

/**
 * Définit un comportement pour l'agent page jaune
 * 
 * @author SAUVAGE Celestine - HALABI Sami
 */
public class InitCountBehaviour extends Behaviour {
	 Logger logger = jade.util.Logger.getMyLogger(this.getClass().getName()); 


	/**
	 * Id unique de la class
	 */
	private static final long serialVersionUID = -8904958887480801918L;
	
	/**
	 * Status of behaviour
	 */
	private boolean done;
	
	//Constructor 
	public InitCountBehaviour(Agent agent){
		this.myAgent = agent;
		this.done = false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void action() {
		ACLMessage message = this.myAgent.receive();
		
		String name = this.myAgent.getName();
		
		if(message != null && ACLMessage.PROPOSE == message.getPerformative()) {
			logger.log(Logger.INFO,String.format("[Agent][%s] Initialisation de la bombe !!!", name));
			
			int value = Integer.parseInt(message.getContent());
			
			//Récupération de des amies
			DFAgentDescription template = new DFAgentDescription();
			ServiceDescription sd = new ServiceDescription();
			sd.setType("Boomb");
			try {
				DFAgentDescription[] result = DFService.search(this.myAgent, template);
				
				List<AID> friends = new ArrayList<>();
				for (DFAgentDescription df : result) {
					if(! df.getName().equals(this.myAgent.getAID()))
						friends.add(df.getName());
				}
				
				this.myAgent.addBehaviour(new YellowCountBehaviour(friends, value));
				this.done = true;
			}
			catch (FIPAException | NumberFormatException fe) {
				logger.log(Logger.WARNING,String.format("[Agent][%s] Pas d'amis trouvé", name));
				return;
			}
		}
		else if(message != null){
			logger.log(Logger.WARNING,String.format("[Agent][%s] Le message n'est pas une initialisation", name));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean done() {
		return this.done;
	}
}
