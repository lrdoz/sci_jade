package fil.sci.yellowPage;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.util.Logger;

/**
 * Définit un comportement pour l'agent bob
 * 
 * @author SAUVAGE Celestine - HALABI Sami
 */
public class AgentArm extends Agent{
	 Logger logger = jade.util.Logger.getMyLogger(this.getClass().getName()); 

	/**
	 * Id unique de la class
	 */
	private static final long serialVersionUID = -8800234766384038765L;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setup() {
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(this.getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType("Boomb");
		sd.setName("Bommer Team");
		dfd.addServices(sd);
		
		//L'agent s'enregistre sur le serivce de page jaune
		try {
			DFService.register(this, dfd);
			this.addBehaviour(new InitCountBehaviour(this));
		}
		catch (FIPAException fe) {
			logger.log(Logger.INFO,String.format("[Agent][%s] Registry to yellow page failed", this.getName()));
		}
	}
}
