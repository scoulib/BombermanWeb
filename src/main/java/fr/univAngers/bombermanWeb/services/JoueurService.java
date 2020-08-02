package fr.univAngers.bombermanWeb.services;

import fr.univAngers.bombermanWeb.domain.Joueur;
import fr.univAngers.bombermanWeb.exceptions.RessourceIntrouvableException;
import fr.univAngers.bombermanWeb.repository.JoueurRepository;
import fr.univAngers.bombermanWeb.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class JoueurService {
     @Autowired
    private JoueurRepository joueurRepository;

     public List<Joueur> getJoueurs() { return joueurRepository.findAll();}
     public Joueur getJoueur(Long id) { return  joueurRepository.findById(id).orElseThrow(()-> new RessourceIntrouvableException("Joueur","id",id));}
    public Joueur ajouter(Joueur joueur)
    {
        System.out.println(joueur);
        joueur.setMotDePasse(SecurityUtil.chiffrerMotDePasse(joueur.getMotDePasse()));
        return joueurRepository.save(joueur);
    }
    public Joueur getJoueurByPseudo(String pseudo) { return  joueurRepository.findByPseudo(pseudo);}
    public Joueur connecter(String login, String mdp) {
        Joueur j = getJoueurByPseudo(login);
        if (j != null ) {

            if (SecurityUtil.verifierMotPasse(mdp, j.getMotDePasse())) {
                return j;
            }

        }
        return null;
        }

    public void deleteJoueur(String pseudo) {
        joueurRepository.delete(getJoueurByPseudo(pseudo));
    }

    public void updateJoueur(Joueur joueur) {
        joueurRepository.updateJoueur(joueur.getPseudo(),joueur.getNomJoueur(),joueur.getPrenomJoueur(),joueur.getNbVictoires(),joueur.getNbDefaites());
    }

    public List<Joueur> lastMonthClassement() {
        LocalDate d = LocalDate.now().minusMonths(1);
        Date lastMonth = java.sql.Date.valueOf(d);
         return joueurRepository.lastMonthJoueurs( new Timestamp(lastMonth.getTime()));
    }

    public List<Joueur> lastMonthJoueursClassementByRatio() {
        LocalDate d = LocalDate.now().minusMonths(1);
        Date lastMonth = java.sql.Date.valueOf(d);
        List<Long> ids = joueurRepository.lastMonthJoueursOrderByRatio( new Timestamp(lastMonth.getTime()));
        List<Joueur> joueurs = new ArrayList<>();
        for (Long id:ids) {
            joueurs.add(getJoueur(id));
        }
        return joueurs;
    }
}
