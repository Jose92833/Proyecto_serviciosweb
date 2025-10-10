package com.veterinary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.veterinary.model.AppointmentNote;

public interface AppointmentNoteRepository extends JpaRepository<AppointmentNote, Integer> {
    
    @Query(value = "SELECT n FROM AppointmentNote n WHERE n.active = true")
    List<AppointmentNote> findActiveNotes();
    
    @Query(value = "SELECT n FROM AppointmentNote n WHERE n.appointmentOnline.id = :appointmentId AND n.active = true")
    List<AppointmentNote> findByAppointmentId(@Param("appointmentId") Integer appointmentId);
}