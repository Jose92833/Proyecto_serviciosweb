package itst.ws.demopersistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import itst.ws.demopersistence.model.consultation_disease;

public interface  consultation_diseaseRepository extends JpaRepository<consultation_disease, Integer> {
    
}
