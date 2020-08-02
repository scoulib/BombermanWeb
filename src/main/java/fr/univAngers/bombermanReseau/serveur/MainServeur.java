package fr.univAngers.bombermanReseau.serveur;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServeur extends Thread {
        private int port = 2345;

    public  void run() {
        try {
            ServerSocket socketServeur = new ServerSocket(port);
            while (true) {
               Socket socketClient = socketServeur.accept();
               System.out.println("client connecte");
               new ClientServiceThread(socketClient).start();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
