package fr.univAngers.bombermanWeb.repository;

import fr.univAngers.bombermanWeb.domain.Partie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Date;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
public interface PartieRepository extends JpaRepository<Partie,Long> {
   List<Partie> findAllByOrderByDateMaj();
}
