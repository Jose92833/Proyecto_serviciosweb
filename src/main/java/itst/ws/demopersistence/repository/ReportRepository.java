package itst.ws.demopersistence.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import itst.ws.demopersistence.model.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {
    
    // Consulta para encontrar reportes por tipo de reporte
    @Query("SELECT r FROM Report r WHERE r.typeReport = :typeReport")
    List<Report> findByTypeReport(@Param("typeReport") String typeReport);
    
    // Consulta para encontrar reportes por ID de usuario
    @Query("SELECT r FROM Report r WHERE r.user.user_id = :userId")
    List<Report> findByUserId(@Param("userId") Long userId);
    
    // Consulta para encontrar reportes por tipo de reporte y usuario
    @Query("SELECT r FROM Report r WHERE r.typeReport = :typeReport AND r.user.user_id = :userId")
    List<Report> findByTypeReportAndUserId(@Param("typeReport") String typeReport, 
                                           @Param("userId") Long userId);
    
    // Consulta nativa para encontrar reportes recientes (últimos 7 días)
    @Query(value = "SELECT * FROM report WHERE report_date >= CURRENT_DATE - 7", nativeQuery = true)
    List<Report> findRecentReports();
    
    // Consulta para encontrar reportes por rango de fechas
    @Query("SELECT r FROM Report r WHERE r.report_date BETWEEN :startDate AND :endDate")
    List<Report> findByReportDateBetween(@Param("startDate") java.time.LocalDateTime startDate, 
                                         @Param("endDate") java.time.LocalDateTime endDate);
    
    // Consulta para contar reportes por tipo
    @Query("SELECT r.typeReport, COUNT(r) FROM Report r GROUP BY r.typeReport")
    List<Object[]> countReportsByType();
}
