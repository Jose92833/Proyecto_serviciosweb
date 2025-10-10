package com.veterinary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.veterinary.model.ClinicalHistory;

public interface ClinicalHistoryRepository extends JpaRepository<ClinicalHistory, Integer> {
    
    @Query(value = "SELECT h FROM ClinicalHistory h WHERE h.active = true")
    List<ClinicalHistory> findActiveHistories();
    
    @Query(value = "SELECT h FROM ClinicalHistory h WHERE h.pet.id = :petId AND h.active = true")
    List<ClinicalHistory> findByPetId(@Param("petId") Integer petId);
    
    @Query(value = "SELECT h FROM ClinicalHistory h WHERE h.consultation.id = :consultationId AND h.active = true")
    List<ClinicalHistory> findByConsultationId(@Param("consultationId") Integer consultationId);
    
    @Query(value = "SELECT h FROM ClinicalHistory h WHERE h.treatment.id = :treatmentId AND h.active = true")
    List<ClinicalHistory> findByTreatmentId(@Param("treatmentId") Integer treatmentId);
}