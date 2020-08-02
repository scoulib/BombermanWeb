package fr.univAngers.bombermanWeb.controllers;

import fr.univAngers.bombermanWeb.domain.Joueur;
import fr.univAngers.bombermanWeb.services.JoueurService;
import fr.univAngers.bombermanWeb.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/joueurs")
@CrossOrigin(origins = "http://localhost:4200")
public class JoueurController {
    @Autowired
    private JoueurService joueurService;

    @GetMapping
    public List<Joueur> listeJoueurs() {
        return joueurService.getJoueurs();
    }

    @GetMapping("/connecter")
    public Joueur connecter(@RequestParam String pseudo,@RequestParam String mdp) {
        return joueurService.connecter(pseudo,mdp);
    }
    @GetMapping(value = "/{pseudo}")
    public Joueur getJoueur(@PathVariable String pseudo) {
        return joueurService.getJoueurByPseudo(pseudo);
    }

    @GetMapping(value = "/classement")
    public List<Joueur> lastMonthClassementJoueurs(@RequestParam String type) {
        List<Joueur> joueurs = new ArrayList<>();
        if(type.compareTo("v")== 0)
            joueurs = joueurService.lastMonthClassement();
        else if(type.compareTo("r")== 0)
            joueurs = joueurService.lastMonthJoueursClassementByRatio();
        return joueurs;
    }
    @PostMapping
    public Joueur ajouterJoueur(@RequestBody Joueur joueur) {
        return joueurService.ajouter(joueur);
    }

    @DeleteMapping(value = "/{pseudo}")
    public ResponseEntity<?> deleteJoueur(@PathVariable String pseudo) {
        joueurService.deleteJoueur(pseudo);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{pseudo}")
    public ResponseEntity<?> majJoueur(@PathVariable String pseudo, @RequestBody Joueur joueur) {
        Joueur old = joueurService.getJoueurByPseudo(pseudo);
        old.setNomJoueur(joueur.getNomJoueur());
        old.setPrenomJoueur(joueur.getPrenomJoueur());
        joueurService.updateJoueur(old);
        return ResponseEntity.ok().build();

    }
}
