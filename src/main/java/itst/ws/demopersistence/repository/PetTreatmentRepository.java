package itst.ws.demopersistence.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import itst.ws.demopersistence.model.PetTreatment;

public interface PetTreatmentRepository extends JpaRepository<PetTreatment, Long> {
    
    // Consulta para encontrar tratamientos por mascota
    @Query("SELECT pt FROM PetTreatment pt WHERE pt.pet.pet_id = :petId")
    List<PetTreatment> findByPetId(@Param("petId") Long petId);
    
    // Consulta para encontrar tratamientos por veterinario
    @Query("SELECT pt FROM PetTreatment pt WHERE pt.veterinarian.veterinarian_id = :veterinarianId")
    List<PetTreatment> findByVeterinarianId(@Param("veterinarianId") Long veterinarianId);
    
    // Consulta para encontrar tratamientos por tipo de tratamiento
    @Query("SELECT pt FROM PetTreatment pt WHERE pt.treatment.treatment_id = :treatmentId")
    List<PetTreatment> findByTreatmentId(@Param("treatmentId") Long treatmentId);
    
    // Consulta para encontrar tratamientos por rango de fechas
    @Query("SELECT pt FROM PetTreatment pt WHERE pt.applicationDate BETWEEN :startDate AND :endDate")
    List<PetTreatment> findByApplicationDateBetween(@Param("startDate") java.time.LocalDate startDate, 
                                                   @Param("endDate") java.time.LocalDate endDate);
}
