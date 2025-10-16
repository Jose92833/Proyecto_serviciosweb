package itst.ws.demopersistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import itst.ws.demopersistence.model.Species;

public interface SpeciesRepository extends JpaRepository<Species, Long> {
 
}
