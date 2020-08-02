package fr.univAngers.bombermanReseau.model;


import fr.univAngers.bombermanReseau.affichageAPI.AgentAction;
import fr.univAngers.bombermanReseau.affichageAPI.ColorAgent;
import fr.univAngers.bombermanReseau.affichageAPI.InfoAgent;
import fr.univAngers.bombermanReseau.behaviors.BehaviorAgent;

public abstract class Agent {
	private int x;
	private int y;
	private AgentAction agentAction;
	private ColorAgent color;
	private boolean isInvincible;
	private boolean isSick;
	
	private BombermanGame bombermanGame;
	protected boolean isBomberman;
	protected BehaviorAgent behaviourAgent;
	
	public Agent() {
		
	}
	
	public Agent(int x, int y, AgentAction agentAction, ColorAgent color, boolean isInvincible, boolean isSick) {
		this.x=x;
		this.y=y;
		this.agentAction = agentAction;
		this.color = color;
		
		this.isInvincible = isInvincible;
		this.isSick = isSick;
	}
	
	

	
	public void setBehaviourAgent(BehaviorAgent behaviourAgent) {
		this.behaviourAgent = behaviourAgent;
	}
	
	

	public BehaviorAgent getBehaviourAgent() {
		return behaviourAgent;
	}
	
	

	public BombermanGame getBombermanGame() {
		return bombermanGame;
	}
	

	public void setBombermanGame(BombermanGame bombermanGame) {
		this.bombermanGame = bombermanGame;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	


	
	public boolean isBomberman(boolean isBomberman) {
		 return this.isBomberman;
	}

	public ColorAgent getColor() {
		return color;
	}

	public void setColor(ColorAgent color) {
		this.color = color;
	}


	public boolean isInvincible() {
		return isInvincible;
	}


	public void setInvincible(boolean isInvincible) {
		this.isInvincible = isInvincible;
	}


	public boolean isSick() {
		return isSick;
	}


	public void setSick(boolean isSick) {
		this.isSick = isSick;
	}


	public AgentAction getAgentAction() {
		return agentAction;
	}


	public void setAgentAction(AgentAction agentAction) {
		this.agentAction = agentAction;
	}
	
	 public abstract  char getType();
	 
	 public boolean isBomberman() {
		 return getType() == 'B';
	 }
	 
	 public void initialize(InfoAgent infoAgent) {
		 this.x = infoAgent.getX();
		 this.y = infoAgent.getY();
		 this.agentAction = infoAgent.getAgentAction();
		 this.color = infoAgent.getColor();
		 this.isInvincible = infoAgent.isInvincible();
		 this.isSick = infoAgent.isSick();	 
	 }
	
	
}
