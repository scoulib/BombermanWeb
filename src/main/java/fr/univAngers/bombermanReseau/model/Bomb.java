package fr.univAngers.bombermanReseau.model;


import fr.univAngers.bombermanReseau.affichageAPI.InfoBomb;
import fr.univAngers.bombermanReseau.affichageAPI.StateBomb;

public class Bomb {
    private int x;
    private int y;
    private int range;
     int cpt;

    StateBomb stateBomb;

    public Bomb() {
        cpt = 0;

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

    public void initialize(InfoBomb info) {
        x = info.getX();
        y = info.getY();
        stateBomb = info.getStateBomb();
        range = info.getRange();
    }
}
