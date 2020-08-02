package fr.univAngers.bombermanReseau.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.univAngers.bombermanReseau.affichageAPI.*;
import fr.univAngers.bombermanReseau.behaviors.BehaviorAleatoire;
import fr.univAngers.bombermanReseau.behaviors.BehaviorKeyboard;
import fr.univAngers.bombermanReseau.serveur.ClientServiceThread;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class BombermanGame extends Game{
	
	private ArrayList<Agent> agents;
	private ArrayList<Bomb> bombs;
	private String filename="./layouts/niveau1.lay";
	private Map map;
	private PanelBomberman panel;
	private ClientServiceThread clientServiceThread;
	private double proba = 0.0;
	
	public BombermanGame(int maxturn, ClientServiceThread c) {
		super(maxturn);
		try {
			map = new Map(filename);
			panel = new PanelBomberman(map);
			agents = new ArrayList<>();
			clientServiceThread = c;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated constructor stub
	}

	
	public  void initializeGame() {
			FabriqueAgent fabrique = new FabriqueAgent();
			bombs = new ArrayList<>();
			for (InfoAgent infoAgent:map.getStart_agents()) {
				Agent nouv = fabrique.creerAgent(infoAgent.getType());
				nouv.initialize(infoAgent);
				nouv.setBombermanGame(this);
				if(nouv.getType() == 'B') {
					System.out.println("Bomberman");
					nouv.setBehaviourAgent(new BehaviorKeyboard(panel));
				} else {
					System.out.println("Ennemi");
				nouv.setBehaviourAgent(new BehaviorAleatoire());}
				agents.add(nouv);
			}
		
	}

	public void setPanel(PanelBomberman pane) {
		this.panel = pane ;
	}

	public PanelBomberman getPanel() {
		return panel;
	}

	public String getJsonPanel() {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		System.out.println(gson.toJson(panel));
		return gson.toJson(panel);
	}

	public boolean isLegalMove(Agent agent, AgentAction deplacement) {
		if(agent.getType() == 'V' && agent.getBehaviourAgent() instanceof BehaviorAleatoire) {
			switch (deplacement) {
			case MOVE_LEFT:
					return !(0 == agent.getX()-1);
			case MOVE_RIGHT:
				return !(map.getSizeX() == agent.getX()+1);
			case MOVE_DOWN:
				return !(map.getSizeY() == agent.getY()+1);
			case MOVE_UP:
				return !(0 == agent.getY()-1);
			default:
				return false;
			}
		}
		switch (deplacement) {
		case MOVE_LEFT:
				//System.out.println(" x: "+(agent.getX()-1)+" y:"+agent.getY()+"\n");
				return !(map.get_walls()[agent.getX()-1][agent.getY()] || map.getStart_brokable_walls()[agent.getX()-1][agent.getY()]);
		case MOVE_RIGHT:
				//System.out.println(" x: "+(agent.getX()+1)+" y:"+agent.getY()+"\n");
				return !(map.get_walls()[agent.getX()+1][agent.getY()] || map.getStart_brokable_walls()[agent.getX()+1][agent.getY()]);
		case MOVE_DOWN:
				//System.out.println(" x: "+(agent.getX())+" y:"+(agent.getY()+1)+"\n");
				return !(map.get_walls()[agent.getX()][agent.getY()+1] || map.getStart_brokable_walls()[agent.getX()][agent.getY()+1]);
		case MOVE_UP:
				return !(map.get_walls()[agent.getX()][agent.getY()-1] || map.getStart_brokable_walls()[agent.getX()][agent.getY()-1]);
		default:
			return false;
		}
	}
	
	
	public void moveAgent(Agent agent, AgentAction deplacement) {
		if(isLegalMove(agent, deplacement)) {
			switch (deplacement) {
			case MOVE_LEFT:
				agent.setX(agent.getX()-1);
				break;
			case MOVE_RIGHT:
				agent.setX(agent.getX()+1);
				break;
			case MOVE_DOWN:
				agent.setY(agent.getY()+1);
				break;
			case MOVE_UP:
				agent.setY(agent.getY()-1);
				break;
			default:
				break;
			}
		}
	}
	
	public void takeTurn() throws InterruptedException {
		
		boolean isEndGame = false;
		//S'il reste un seul agent et que c'est un bomberman victoire
		if(agents.size() == 1 && agents.get(0).isBomberman())
		{
			gameOver();
			isEndGame = true;
			clientServiceThread.enregistrerResultat(true);
		}
			
		for(Agent a : agents) {
	        if (a.isBomberman()) {
	            for (Agent g : agents) {
	                if (a != g && a.getX() == g.getX() && a.getY() == g.getY()) {
	                    gameOver();
	                    isEndGame = true;
	                    //defaite
						clientServiceThread.enregistrerResultat(false);
	                    break;
	                }
	            }
	        }
		}

		for( Bomb b : bombs) {
			b.cpt++;
			switch (b.cpt) {
				case 1:
					b.setStateBomb(StateBomb.Step2);
					break;
				case 2:
					b.setStateBomb(StateBomb.Step3);
					break;
				case 3:
					b.setStateBomb(StateBomb.Boom);
					break;

			}
		}

		for(int i = 0;i< panel.getListInfoBombs().size(); ++i)
		{
			if(bombs.get(i).getX() == panel.getListInfoBombs().get(i).getX() && bombs.get(i).getY() == panel.getListInfoBombs().get(i).getY() ) {
				panel.getListInfoBombs().get(i).setStateBomb(bombs.get(i).getStateBomb());
				if(bombs.get(i).cpt == 4)
				{
					System.out.println("recherche de murs  et ennemis à supprimer");
					for(int j = 0; j< bombs.get(i).getRange() ; j++ ) {
						Iterator<InfoAgent> iterator = map.getStart_agents().iterator();
						Iterator<Agent> iteratorAgent = agents.iterator();
						while( iterator.hasNext() && iteratorAgent.hasNext()) {
								InfoAgent infoAgent = iterator.next();
								Agent agent = iteratorAgent.next();

							proba = (Math.random() * (100));
								if( j+bombs.get(i).getX() < map.getSizeX() && ( !(infoAgent.getType() == 'B') && infoAgent.getX() == j+bombs.get(i).getX() && infoAgent.getY() == bombs.get(i).getY()))
								{
									System.out.println("ennemi à droite");
									iterator.remove();
									iteratorAgent.remove();
								}

							if( bombs.get(i).getX()-j > 0 &&  ( !(infoAgent.getType() == 'B') && infoAgent.getX() == bombs.get(i).getX()-j && infoAgent.getY() == bombs.get(i).getY()))
							{
								System.out.println("ennemi à gauche");
								iterator.remove();
								iteratorAgent.remove();
							}

							if( j+bombs.get(i).getY() < map.getSizeY() && ( !(infoAgent.getType() == 'B') && infoAgent.getX() == bombs.get(i).getX() && infoAgent.getY() == j+bombs.get(i).getY()))
							{
								System.out.println("ennemi en bas");
								iterator.remove();
								iteratorAgent.remove();

							}

							if( bombs.get(i).getY() - j > 0 && ( !(infoAgent.getType() == 'B') && infoAgent.getX() == bombs.get(i).getX() && infoAgent.getY() == bombs.get(i).getY()-j))
							{
								System.out.println("ennemi en haut");
								iterator.remove();
								iteratorAgent.remove();
							}
						}
						Random rand = new Random();
						int dep = rand.nextInt(3);
						if( j+bombs.get(i).getX() < map.getSizeX() && map.getStart_brokable_walls()[j+bombs.get(i).getX()][bombs.get(i).getY()])
							{
								System.out.println("mur cassable à droite");
								map.getStart_brokable_walls()[j+bombs.get(i).getX()][bombs.get(i).getY()] = false ;
								if(proba > 60) {
								InfoItem infoItem = new InfoItem(j+bombs.get(i).getX(),bombs.get(i).getY(),ItemType.values()[dep]);
								panel.getListInfoItems().add(infoItem);
								}
							}

						if( bombs.get(i).getX()-j > 0 && map.getStart_brokable_walls()[bombs.get(i).getX()-j][bombs.get(i).getY()])
							{
								System.out.println("mur cassable à gauche");
								map.getStart_brokable_walls()[bombs.get(i).getX()-j][bombs.get(i).getY()] = false ;
								if(proba > 60) {
									InfoItem infoItem = new InfoItem(bombs.get(i).getX()-j,bombs.get(i).getY(),ItemType.values()[dep]);
									panel.getListInfoItems().add(infoItem);
								}
							}

						if( j+bombs.get(i).getY() < map.getSizeY() && map.getStart_brokable_walls()[bombs.get(i).getX()][bombs.get(i).getY()+j])
							{
								System.out.println("mur cassable en bas");
								map.getStart_brokable_walls()[bombs.get(i).getX()][bombs.get(i).getY()+j] = false ;
								if(proba > 60) {
									InfoItem infoItem = new InfoItem(bombs.get(i).getX(),bombs.get(i).getY()+j,ItemType.values()[dep]);
									panel.getListInfoItems().add(infoItem);
								}
							}

						if( bombs.get(i).getY() - j > 0 && map.getStart_brokable_walls()[bombs.get(i).getX()][bombs.get(i).getY()-j])
							{
								System.out.println("mur cassable en haut");
								map.getStart_brokable_walls()[bombs.get(i).getX()][bombs.get(i).getY()-j] = false ;
								if(proba > 60) {
									InfoItem infoItem = new InfoItem(bombs.get(i).getX(),bombs.get(i).getY()-j,ItemType.values()[dep]);
									panel.getListInfoItems().add(infoItem);
								}
							}
					}
					panel.getListInfoBombs().remove(i);
					bombs.remove(i);
				}
			}
		}

		
		if (!isEndGame) {
		
		for(Agent a: agents) {
			
			if(a.getType() == 'B') {
				AgentAction agentAction = a.getBehaviourAgent().getAgentAction();
				if(agentAction == AgentAction.PUT_BOMB) {
					InfoBomb infoBomb = new InfoBomb(a.getX(),a.getY(),15,StateBomb.Step1);
					Bomb nouvBomb = new Bomb();
					nouvBomb.initialize(infoBomb);
					bombs.add(nouvBomb);
					panel.getListInfoBombs().add(infoBomb);
				} else {
					moveAgent(a,agentAction );
				}
			}
			else
				moveAgent(a, a.getBehaviourAgent().getAgentAction());
		}
	
		for (int i = 0;i<agents.size(); ++i) {
			map.getStart_agents().get(i).setX(agents.get(i).getX());
			map.getStart_agents().get(i).setY(agents.get(i).getY());
			}
		}
		clientServiceThread.setData();
		clientServiceThread.envoyerPanel();
	}
	
	
	public Map getMap() {
		return this.map;
	}


	public void setMap(Map map) {
		this.map = map;
	}


	public ArrayList<Agent> getAgents() {
		return agents;
	}


	public String getFilename() {
		return filename;
	}


	public void gameOver() {
		System.out.println("Fin du jeu");
		stop();
	}
	
	public boolean gameContinue() {
		return true;
	}
}
