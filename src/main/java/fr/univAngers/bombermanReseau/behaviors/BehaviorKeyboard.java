package fr.univAngers.bombermanReseau.behaviors;


import fr.univAngers.bombermanReseau.affichageAPI.AgentAction;
import fr.univAngers.bombermanReseau.affichageAPI.PanelBomberman;

public class BehaviorKeyboard implements BehaviorAgent{
	private PanelBomberman panelBomberman;

	public BehaviorKeyboard(PanelBomberman panelBomberman) {
		this.panelBomberman = panelBomberman;
	}

	@Override
	public AgentAction getAgentAction() {
        return panelBomberman.getAction();
	}	

}
