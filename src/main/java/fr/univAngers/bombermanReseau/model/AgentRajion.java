package fr.univAngers.bombermanReseau.model;


import fr.univAngers.bombermanReseau.affichageAPI.AgentAction;
import fr.univAngers.bombermanReseau.affichageAPI.ColorAgent;

public class AgentRajion extends Agent {
	
	public AgentRajion() {
		super();
		
	}

	public AgentRajion(int x, int y, AgentAction agentAction, ColorAgent color, boolean isInvincible, boolean isSick) {
		super(x, y, agentAction, color, isInvincible, isSick);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public  char getType() {
		return 'R';
	}

}
