package fr.univAngers.bombermanWeb.listeners;

import fr.univAngers.bombermanWeb.domain.Partie;
import fr.univAngers.bombermanWeb.repository.JoueurRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.PrePersist;
import java.sql.Timestamp;
import java.util.Date;

public class PartieEntityListener {
    @Autowired
    private JoueurRepository joueurRepository;

    @PrePersist
    void onCreate(Partie partie) {
        Date t = new Date();
        partie.setDateCreation(t);
        partie.setDateMaj(t);

    }
}
