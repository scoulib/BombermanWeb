package fr.univAngers.bombermanReseau.model;

public abstract class Game  implements Runnable{
	protected int turn;
	protected int maxturn;
	protected boolean isRunning;
	protected Thread thread;
	protected long temps;
	
	public Game(int maxturn) {
		this.maxturn = maxturn;
		this.temps=500;
	}

	public void init() {

		System.out.println("Initialisation du jeu");
		this.turn=0;
		this.isRunning=true;
		initializeGame();
		
		
	}
	
	public int getTurn() {
		return turn;
	}

	public void setTemps(long temps) {
		this.temps = temps;
	}

	public abstract void initializeGame();
	
	public void step() throws InterruptedException {
		this.turn++;
		System.out.println(" tour "+turn);
		if( gameContinue() && turn<=maxturn)
			{
				takeTurn();
			}
		else {
			this.isRunning=false;
			gameOver();
		}
	}
	
	public abstract void takeTurn() throws InterruptedException;
	
	public abstract void gameOver();
	
	public abstract boolean gameContinue();
	
	public void run() {
		do {
			try {
				step();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(temps);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while( isRunning);
	}
	
	public void stop() {
		this.isRunning = false;
	}
	
	public void launch() {
		this.isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
}
