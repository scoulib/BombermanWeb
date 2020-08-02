package fr.univAngers.bombermanReseau.controller;

public interface InterfaceControleur {
	public void start();
	public void step();
	public void run();
	public void stop();
	public void setTime(double time);
}
