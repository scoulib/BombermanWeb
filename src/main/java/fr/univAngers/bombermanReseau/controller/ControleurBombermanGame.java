package fr.univAngers.bombermanReseau.controller;


import fr.univAngers.bombermanReseau.model.BombermanGame;
import fr.univAngers.bombermanReseau.model.Game;

public class ControleurBombermanGame implements InterfaceControleur{

	private Game game;
	
	public ControleurBombermanGame(BombermanGame game) {
		this.game = game;
	}
	@Override
	public void start() {
		// TODO Auto-generated method stub
		this.game.init();
	}
	@Override
	public void step() {
		// TODO Auto-generated method stub
		try {
			this.game.step();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.game.launch();
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		System.out.println("le jeu est en pause");
		this.game.stop();
	}

	@Override
	public void setTime(double time) {
		// TODO Auto-generated method stub
		this.game.setTemps((long)time);
		
	}


}
