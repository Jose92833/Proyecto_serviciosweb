package itst.ws.demopersistence.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import itst.ws.demopersistence.model.VeterinarianSpecialty;

public interface VeterinarianSpecialtyRepository extends JpaRepository<VeterinarianSpecialty, Long> {
    //encontrar especialidades por veterinario
    @Query("SELECT vs FROM VeterinarianSpecialty vs WHERE vs.veterinarian.veterinarian_id = :veterinarianId")
    List<VeterinarianSpecialty> findByVeterinarianId(@Param("veterinarianId") Long veterinarianId);
    //encontrar veterinarios por especialidad
    @Query("SELECT vs FROM VeterinarianSpecialty vs WHERE vs.specialty.specialty_id = :specialtyId")
    List<VeterinarianSpecialty> findBySpecialtyId(@Param("specialtyId") Long specialtyId);
    //verificar si ya existe una combinación veterinario-especialidad
    @Query("SELECT vs FROM VeterinarianSpecialty vs WHERE vs.veterinarian.veterinarian_id = :veterinarianId AND vs.specialty.specialty_id = :specialtyId")
    List<VeterinarianSpecialty> findByVeterinarianAndSpecialty(@Param("veterinarianId") Long veterinarianId, 
                                                              @Param("specialtyId") Long specialtyId);
}
