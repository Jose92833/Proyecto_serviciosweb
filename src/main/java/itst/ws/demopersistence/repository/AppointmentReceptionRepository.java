package com.veterinary.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.veterinary.model.AppointmentReception;

public interface AppointmentReceptionRepository extends JpaRepository<AppointmentReception, Integer> {
    
    @Query(value = "SELECT a FROM AppointmentReception a WHERE a.active = true")
    List<AppointmentReception> findActiveAppointments();
    
    @Query(value = "SELECT a FROM AppointmentReception a WHERE a.pet.id = :petId AND a.active = true")
    List<AppointmentReception> findByPetId(@Param("petId") Integer petId);
    
    @Query(value = "SELECT a FROM AppointmentReception a WHERE a.owner.id = :ownerId AND a.active = true")
    List<AppointmentReception> findByOwnerId(@Param("ownerId") Integer ownerId);
    
    @Query(value = "SELECT a FROM AppointmentReception a WHERE a.receptionist.id = :receptionistId AND a.active = true")
    List<AppointmentReception> findByReceptionistId(@Param("receptionistId") Integer receptionistId);
    
    @Query(value = "SELECT a FROM AppointmentReception a WHERE a.appointmentDate BETWEEN :startDate AND :endDate AND a.active = true")
    List<AppointmentReception> findByDateRange(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate);
}