package fr.univAngers.bombermanReseau.affichageAPI;




public enum AgentAction {
	MOVE_UP("haut"),
	MOVE_DOWN("bas"),MOVE_LEFT("gauche"),
	MOVE_RIGHT("droite"),
	STOP("stop"),
	PUT_BOMB("bomb");

	private String deplacement;
	AgentAction(String dep) {
		deplacement = dep;
	}

	public String getDeplacement() {
		return deplacement;
	}
}

