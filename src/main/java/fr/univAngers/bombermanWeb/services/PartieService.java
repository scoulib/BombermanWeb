package fr.univAngers.bombermanWeb.services;

import fr.univAngers.bombermanWeb.domain.Joueur;
import fr.univAngers.bombermanWeb.domain.Partie;
import fr.univAngers.bombermanWeb.repository.PartieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class PartieService {
    @Autowired
    private PartieRepository partieRepository;
    @Autowired
    private JoueurService joueurService;

    public Partie ajouter(Partie partie) {
        Joueur j = partie.getJoueur();
        //mettre à jour nbVictoires ou nbDefaites
        if(partie.getVictoire()) {
            j.setNbVictoires(1+j.getNbVictoires());

        }else {
            j.setNbDefaites(1+j.getNbDefaites());
        }
        System.out.println(" trigger j: v"+j.getNbVictoires()+" D:"+j.getNbDefaites()+" ID"+j.getIdJoueur());
        joueurService.updateJoueur(partie.getJoueur());

        return partieRepository.save(partie);
    }

    public List<Partie> getParties() {
        return partieRepository.findAllByOrderByDateMaj();
    }

}
