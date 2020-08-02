package fr.univAngers.bombermanReseau.affichageAPI;


import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class InfoBomb implements Serializable {
	@Expose
	private int x;
	@Expose
	private int y;
	@Expose
	private int range;
	@Expose
	StateBomb stateBomb;

	public InfoBomb(int x, int y, int range, StateBomb stateBomb) {
		this.x=x;
		this.y=y;
		this.range=range;
		this.stateBomb = stateBomb;

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



	public StateBomb getStateBomb() {
		return stateBomb;
	}



	public void setStateBomb(StateBomb stateBomb) {
		this.stateBomb = stateBomb;
	}



	public int getRange() {
		return range;
	}


	public void setRange(int range) {
		this.range = range;
	}


	
}
	