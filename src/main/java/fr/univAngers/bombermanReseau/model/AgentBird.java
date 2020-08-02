package fr.univAngers.bombermanReseau.model;


import fr.univAngers.bombermanReseau.affichageAPI.AgentAction;
import fr.univAngers.bombermanReseau.affichageAPI.ColorAgent;
import fr.univAngers.bombermanReseau.behaviors.BehaviorBirdImmobile;

public class AgentBird extends Agent {
	
	public AgentBird() {
		super();
	}
	
	public AgentBird(int x, int y, AgentAction agentAction, ColorAgent color, boolean isInvincible, boolean isSick) {
		super(x, y, agentAction, color, isInvincible, isSick);
		// TODO Auto-generated constructor stub
		this. behaviourAgent = new BehaviorBirdImmobile();
	}
	
	@Override
	public char getType() {
		return 'V';
	}

}
