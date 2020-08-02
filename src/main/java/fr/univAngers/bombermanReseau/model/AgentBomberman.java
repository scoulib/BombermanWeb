package fr.univAngers.bombermanReseau.model;


import fr.univAngers.bombermanReseau.affichageAPI.AgentAction;
import fr.univAngers.bombermanReseau.affichageAPI.ColorAgent;

public class AgentBomberman extends Agent {

	public AgentBomberman(int x, int y, AgentAction agentAction, ColorAgent color, boolean isInvincible,
						  boolean isSick) {
		super(x, y, agentAction, color, isInvincible, isSick);
		this.isBomberman = true;
		// TODO Auto-generated constructor stub
	}
	
	public AgentBomberman() {
		super();
	}
	
	@Override
	public char getType() {
		return 'B';
	}

}
