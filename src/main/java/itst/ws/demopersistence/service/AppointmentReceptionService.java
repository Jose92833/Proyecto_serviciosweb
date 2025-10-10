package com.veterinary.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import com.veterinary.repository.AppointmentReceptionRepository;
import com.veterinary.model.AppointmentReception;

@Service
@Transactional
public class AppointmentReceptionService {
    @Autowired
    private AppointmentReceptionRepository repository;

    public List<AppointmentReception> getAll() {
        return repository.findActiveAppointments();
    }

    public AppointmentReception save(AppointmentReception appointmentReception) {
        return repository.save(appointmentReception);
    }

    public AppointmentReception getById(Integer id) {
        return repository.findById(id).get();
    }

    // NO tiene deactivate porque ya maneja active en la entidad
    public List<AppointmentReception> findByPetId(Integer petId) {
        return repository.findByPetId(petId);
    }

    public List<AppointmentReception> findByOwnerId(Integer ownerId) {
        return repository.findByOwnerId(ownerId);
    }

    public List<AppointmentReception> findByReceptionistId(Integer receptionistId) {
        return repository.findByReceptionistId(receptionistId);
    }

    public List<AppointmentReception> findByDateRange(Timestamp startDate, Timestamp endDate) {
        return repository.findByDateRange(startDate, endDate);
    }
}