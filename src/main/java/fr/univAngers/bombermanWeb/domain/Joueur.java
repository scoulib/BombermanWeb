package fr.univAngers.bombermanWeb.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Joueur  {
    @Id
    @GeneratedValue
    private Long idJoueur;
    private String nomJoueur;
    private String prenomJoueur;
    @Column(unique=true)
    private  String pseudo;
    private String motDePasse;
    @JsonBackReference
    @OneToMany(mappedBy = "joueur", fetch = FetchType.LAZY,cascade=CascadeType.REMOVE)
    private List<Partie> parties;
    private int nbVictoires;
    private int nbDefaites;

    public Joueur() {
    }

    public Joueur(String nomJoueur, String prenomJoueur, String pseudo, String motDePasse, List<Partie> parties, int nbVictoires, int nbDefaites) {
        this.nomJoueur = nomJoueur;
        this.prenomJoueur = prenomJoueur;
        this.pseudo = pseudo;
        this.motDePasse = motDePasse;
        this.parties = parties;
        this.nbVictoires = nbVictoires;
        this.nbDefaites = nbDefaites;
    }

    public int getNbVictoires() {
        return nbVictoires;
    }

    public void setNbVictoires(int nbVictoires) {
        this.nbVictoires = nbVictoires;
    }

    public int getNbDefaites() {
        return nbDefaites;
    }

    public void setNbDefaites(int nbDefaites) {
        this.nbDefaites = nbDefaites;
    }

    public Long getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(Long idJoueur) {
        this.idJoueur = idJoueur;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public List<Partie> getParties() {
        return parties;
    }

    public void setParties(List<Partie> parties) {
        this.parties = parties;
    }

    public String getNomJoueur() {
        return nomJoueur;
    }

    public void setNomJoueur(String nomJoueur) {
        this.nomJoueur = nomJoueur;
    }

    public String getPrenomJoueur() {
        return prenomJoueur;
    }

    public void setPrenomJoueur(String prenomJoueur) {
        this.prenomJoueur = prenomJoueur;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

}
