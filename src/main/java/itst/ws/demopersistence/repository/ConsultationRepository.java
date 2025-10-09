package itst.ws.demopersistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import itst.ws.demopersistence.model.Consultation;

public interface  ConsultationRepository extends JpaRepository<Consultation, Integer> {
    
    
}
