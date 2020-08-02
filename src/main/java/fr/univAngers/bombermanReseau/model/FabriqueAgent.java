package fr.univAngers.bombermanReseau.model;

public class FabriqueAgent implements AgentFactory{

	@Override
	public Agent creerAgent(char type) {
		// TODO Auto-generated method stub
		switch(type) {
		case 'B':
			return new AgentBomberman();
		case 'R':
			return new AgentRajion();
		case 'E':
			return new AgentEnnemi();
		case 'V':
			return new AgentBird();
		default:
				return null;
		}
	}
	

}
