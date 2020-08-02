package fr.univAngers.bombermanWeb;


import fr.univAngers.bombermanReseau.serveur.MainServeur;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class BombermanWebApplication {
    public static void main(String[] args) {

            SpringApplication.run(BombermanWebApplication.class, args);

            new MainServeur().start();
    }
}
