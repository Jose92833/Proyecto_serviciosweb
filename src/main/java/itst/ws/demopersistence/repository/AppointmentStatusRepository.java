package com.veterinary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.veterinary.model.AppointmentStatus;

public interface AppointmentStatusRepository extends JpaRepository<AppointmentStatus, Integer> {
    
    @Query(value = "SELECT s FROM AppointmentStatus s WHERE s.active = true")
    List<AppointmentStatus> findActiveStatuses();
}