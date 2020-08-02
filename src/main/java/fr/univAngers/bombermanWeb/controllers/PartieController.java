package fr.univAngers.bombermanWeb.controllers;

import fr.univAngers.bombermanWeb.domain.Partie;
import fr.univAngers.bombermanWeb.services.PartieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value="/parties")
@CrossOrigin(origins = "http://localhost:4200")
public class PartieController {
    @Autowired
    private PartieService partieService;

    @GetMapping
    public List<Partie> listeParties() {
        return partieService.getParties();
    }

    @PostMapping
    public Partie ajouterPartie(@RequestBody Partie partie) {
        return partieService.ajouter(partie);
    }
}
