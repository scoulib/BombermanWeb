package fr.univAngers.bombermanReseau.model;


import fr.univAngers.bombermanReseau.affichageAPI.AgentAction;
import fr.univAngers.bombermanReseau.affichageAPI.ColorAgent;

public class AgentEnnemi extends Agent {
	
	public AgentEnnemi() {
		super();
	}

	public AgentEnnemi(int x, int y, AgentAction agentAction, ColorAgent color, boolean isInvincible, boolean isSick) {
		super(x, y, agentAction, color, isInvincible, isSick);
		// TODO Auto-generated constructor stub
	}

	@Override
	public char getType() {
		// TODO Auto-generated method stub
		return 'E';
	}

}
