package com.veterinary.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.veterinary.model.AppointmentOnline;

public interface AppointmentOnlineRepository extends JpaRepository<AppointmentOnline, Integer> {
    
    @Query(value = "SELECT a FROM AppointmentOnline a WHERE a.active = true")
    List<AppointmentOnline> findActiveAppointments();
    
    @Query(value = "SELECT a FROM AppointmentOnline a WHERE a.pet.id = :petId AND a.active = true")
    List<AppointmentOnline> findByPetId(@Param("petId") Integer petId);
    
    @Query(value = "SELECT a FROM AppointmentOnline a WHERE a.owner.id = :ownerId AND a.active = true")
    List<AppointmentOnline> findByOwnerId(@Param("ownerId") Integer ownerId);
    
    @Query(value = "SELECT a FROM AppointmentOnline a WHERE a.status.id = :statusId AND a.active = true")
    List<AppointmentOnline> findByStatusId(@Param("statusId") Integer statusId);
    
    @Query(value = "SELECT a FROM AppointmentOnline a WHERE a.appointmentDate BETWEEN :startDate AND :endDate AND a.active = true")
    List<AppointmentOnline> findByDateRange(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate);
}