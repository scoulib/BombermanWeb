package fr.univAngers.bombermanReseau.affichageAPI;


import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class InfoItem implements Serializable {
	@Expose
	private int x;
	@Expose
	private int y;
	@Expose
	private ItemType type;



	public InfoItem(int x, int y, ItemType type) {
		this.x=x;
		this.y=y;
		this.type=type;

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


	public ItemType getType() {
		return type;
	}


	public void setType(ItemType type) {
		this.type = type;
	}

	
}
	