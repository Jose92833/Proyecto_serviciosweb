package itst.ws.demopersistence.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import itst.ws.demopersistence.model.VetSchedule;

public interface VetScheduleRepository extends JpaRepository<VetSchedule, Long> {
    
    //encontrar horarios por veterinario
    @Query("SELECT vs FROM VetSchedule vs WHERE vs.veterinarian.veterinarian_id = :veterinarianId")
    List<VetSchedule> findByVeterinarianId(@Param("veterinarianId") Long veterinarianId);
    
    //encontrar horarios por día de la semana
    @Query("SELECT vs FROM VetSchedule vs WHERE vs.weekday = :weekday")
    List<VetSchedule> findByWeekday(@Param("weekday") String weekday);
    
    //encontrar horarios por veterinario y día de la semana
    @Query("SELECT vs FROM VetSchedule vs WHERE vs.veterinarian.veterinarian_id = :veterinarianId AND vs.weekday = :weekday")
    List<VetSchedule> findByVeterinarianIdAndWeekday(@Param("veterinarianId") Long veterinarianId, 
                                                    @Param("weekday") String weekday);
    
    //encontrar horarios que se superponen (para validación)
    @Query("SELECT vs FROM VetSchedule vs WHERE vs.veterinarian.veterinarian_id = :veterinarianId " +
           "AND vs.weekday = :weekday " +
           "AND ((vs.startTime BETWEEN :startTime AND :endTime) OR (vs.endTime BETWEEN :startTime AND :endTime))")
    List<VetSchedule> findOverlappingSchedules(@Param("veterinarianId") Long veterinarianId,
                                              @Param("weekday") String weekday,
                                              @Param("startTime") java.time.LocalTime startTime,
                                              @Param("endTime") java.time.LocalTime endTime);
    
    //encontrar horarios activos en un rango de tiempo específico
    @Query("SELECT vs FROM VetSchedule vs WHERE vs.weekday = :weekday " +
           "AND vs.startTime <= :time AND vs.endTime >= :time")
    List<VetSchedule> findActiveSchedulesByTime(@Param("weekday") String weekday,
                                               @Param("time") java.time.LocalTime time);
}
