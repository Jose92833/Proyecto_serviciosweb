package itst.ws.demopersistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import itst.ws.demopersistence.model.Disease;

public interface DiseaseRepository extends JpaRepository<Disease, Integer> {
    
}
