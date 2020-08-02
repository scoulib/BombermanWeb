package fr.univAngers.bombermanWeb.repository;

import fr.univAngers.bombermanWeb.domain.Joueur;
import fr.univAngers.bombermanWeb.domain.Partie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
public interface JoueurRepository extends JpaRepository<Joueur,Long> {
    Joueur findByPseudo(String pseudo);
    @Transactional
    @Modifying
    @Query("update Joueur j set j.nomJoueur = ?2,j.prenomJoueur = ?3,j.nbVictoires = ?4,j.nbDefaites=?5 where j.pseudo = ?1 ")
    void updateJoueur(String pseudo,String nomJoueur, String prenomJouer,int nbV,int nbD);
    @Query("select  distinct j from Joueur j,Partie p where p.joueur=j and p.dateCreation > ?1 order by j.nbVictoires DESC")
    List<Joueur> lastMonthJoueurs(Timestamp lastm);
    @Query(value = " select t.id_joueur from " +
            "( select distinct j.id_joueur, CASE when j.nb_defaites = 0 then j.nb_victoires else (j.nb_victoires/j.nb_defaites) END as ratio " +
            "from joueur j,partie p where p.id_joueur=j.id_joueur and p.date_creation > '2020-03-06') t " +
            "order by t.ratio DESC",nativeQuery = true)
    List<Long> lastMonthJoueursOrderByRatio(Timestamp lastm);
/*
 select t.j from
( select distinct j, CASE when j.nb_defaites = 0 then j.nb_victoires else (j.nb_victoires/j.nb_defaites) END as ratio
from joueur j,partie p where p.id_joueur=j.id_joueur and p.date_creation > '2020-03-06') t
order by t.ratio DESC;

 */

}