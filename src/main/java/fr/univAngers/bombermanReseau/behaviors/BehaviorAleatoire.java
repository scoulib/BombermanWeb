package fr.univAngers.bombermanReseau.behaviors;



import fr.univAngers.bombermanReseau.affichageAPI.AgentAction;

import java.util.Random;

public class BehaviorAleatoire implements BehaviorAgent {
	@Override
	public AgentAction getAgentAction() {
		Random rand = new Random();
		int dep = rand.nextInt(3);	 
		return AgentAction.values()[dep];
	}

}
