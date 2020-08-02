package fr.univAngers.bombermanWeb.domain;

import fr.univAngers.bombermanWeb.listeners.PartieEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@EntityListeners(PartieEntityListener.class)
public class Partie {
    @Id
    @GeneratedValue
    private Long idPartie;
    private boolean victoire;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idJoueur")
    private Joueur joueur;
    @Column(insertable = false, updatable = true)
    private Date dateMaj;
    @Column(insertable = true, updatable = false)
    private Date dateCreation;

    public Partie() { }


    public Partie(boolean resultat, Joueur joueur) {
        this.victoire = resultat;
        this.joueur = joueur;
        this.dateMaj = dateMaj;
        this.dateCreation = dateCreation;
    }

    public boolean isVictoire() {
        return victoire;
    }

    public Date getDateMaj() {
        return dateMaj;
    }

    public void setDateMaj(Date dateMaj) {
        this.dateMaj = dateMaj;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Long getIdPartie() {
        return idPartie;
    }

    public void setIdPartie(Long idPartie) {
        this.idPartie = idPartie;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public boolean getVictoire() {
        return victoire;
    }

    public void setVictoire(boolean victoire) {
        this.victoire = victoire;
    }


}
