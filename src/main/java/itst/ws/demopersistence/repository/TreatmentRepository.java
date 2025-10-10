package com.veterinary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.veterinary.model.Treatment;

public interface TreatmentRepository extends JpaRepository<Treatment, Integer> {
    
    @Query(value = "SELECT t FROM Treatment t WHERE t.active = true")
    List<Treatment> findActiveTreatments();
    
    @Query(value = "SELECT t FROM Treatment t WHERE t.name LIKE %:name% AND t.active = true")
    List<Treatment> findByNameContaining(@Param("name") String name);
}