package fr.univAngers.bombermanReseau.behaviors;


import fr.univAngers.bombermanReseau.affichageAPI.AgentAction;

public class BehaviorBirdImmobile implements BehaviorAgent {

	@Override
	public AgentAction getAgentAction() {
		return AgentAction.STOP;
	}

}
