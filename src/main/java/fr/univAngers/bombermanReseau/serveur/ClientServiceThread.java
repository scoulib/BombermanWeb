package fr.univAngers.bombermanReseau.serveur;

import fr.univAngers.bombermanReseau.affichageAPI.AgentAction;
import fr.univAngers.bombermanReseau.controller.ControleurBombermanGame;
import fr.univAngers.bombermanReseau.model.BombermanGame;
import fr.univAngers.bombermanWeb.domain.Joueur;
import fr.univAngers.bombermanWeb.domain.Partie;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.Socket;
import java.util.Date;

import static fr.univAngers.bombermanReseau.client.State.login;

public class ClientServiceThread extends Thread{
        private Socket s;
        private fr.univAngers.bombermanReseau.client.State state = login;
        private BombermanGame bombermanGame;
        private ControleurBombermanGame controleurBombermanGame;
        private PrintWriter pw;
        private BufferedReader br;
        private  String data;
        private Joueur joueur;
        private RestTemplate restTemplate;
        private String urlJoueurs = "http://localhost:8080/joueurs";
    private String urlParties = "http://localhost:8080/parties";

        public ClientServiceThread(Socket socket) {
            super();
            s = socket ;
            restTemplate = new RestTemplate();
        }

    public  void setData() {
        data = bombermanGame.getJsonPanel();
    }

    public void run() {
            try {
                InputStream inputStream = s.getInputStream();
                InputStreamReader isr = new InputStreamReader(inputStream);
                 br = new BufferedReader(isr);
                OutputStream outputStream = s.getOutputStream();
                pw = new PrintWriter(outputStream,true);

                String requete;

                while ((requete = br.readLine()) != null) {
                    System.out.println("serveur a recu "+requete);


                    switch (state) {
                        case login:
                            processLogin(requete);
                            break;
                        case commande:
                            processCommand(requete);
                            break;
                    }
                }


            } catch (IOException | InterruptedException | JSONException e) {
                e.printStackTrace();
            } finally{
                try{
                    System.out.println("Connection Closing..");
                    if (br!=null){
                        br.close();
                        System.out.println(" Socket Input Stream Closed");
                    }

                    if(pw!=null){
                        pw.close();
                        System.out.println("Socket Out Closed");
                    }
                    if (s!=null){
                        s.close();
                        System.out.println("Socket Closed");
                    }

                }
                catch(IOException ie){
                    System.out.println("Socket Close Error");
                }
            }
        }

        void processLogin(String line) throws IOException, JSONException, InterruptedException {
            System.out.println("login process");
            JSONObject json = new JSONObject(line);
            String urlconnection = urlJoueurs +"/connecter?pseudo="+json.get("login").toString()+"&mdp="+json.get("mdp").toString();
            joueur = restTemplate.getForObject(urlconnection,Joueur.class);
            if (validUser(joueur)) {
                System.out.println(" passage en mode interprétation de commande");

                state = fr.univAngers.bombermanReseau.client.State.commande;
                bombermanGame = new BombermanGame(1000,this);
                controleurBombermanGame = new ControleurBombermanGame(bombermanGame);
                setData();
                envoyerPanel();
            }
            else {
                pw.println("Identifiants incorrects ....");
            }

        }

        void processCommand(String req) {
            switch(req) {
                case "init":
                    controleurBombermanGame.start();
                    break;

                case "run":
                    controleurBombermanGame.run();
                    break;

                case "step":
                    controleurBombermanGame.step();
                    break;

                case "pause":
                    controleurBombermanGame.stop();
                    break;
                case "haut":
                    bombermanGame.getPanel().setLast(AgentAction.MOVE_UP);
                    break;
                case "bas":
                    bombermanGame.getPanel().setLast(AgentAction.MOVE_DOWN);
                    break;
                case "droite":
                    bombermanGame.getPanel().setLast(AgentAction.MOVE_RIGHT);
                    break;
                case "stop":
                    bombermanGame.getPanel().setLast(AgentAction.STOP);
                    break;
                case "gauche":
                    bombermanGame.getPanel().setLast(AgentAction.MOVE_LEFT);
                    break;
                case "bomb":
                    bombermanGame.getPanel().setLast(AgentAction.PUT_BOMB);
                    break;
                default:
                    break;
            }
        }
        public  void envoyerPanel() throws InterruptedException {
            pw.println(data);
        }

        public boolean validUser(Joueur joueur ){
            return (joueur != null);
        }
        public void enregistrerResultat(boolean res) {
            Partie p = new Partie(res,joueur);
            HttpEntity<Partie> request = new HttpEntity<>(p);
            p = restTemplate.postForObject(urlParties,request,Partie.class);
            if(p != null) {
                System.out.println("patie crée avec succés !!");
            }
        }


}
