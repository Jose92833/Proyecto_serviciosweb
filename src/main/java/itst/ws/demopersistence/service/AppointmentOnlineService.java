package com.veterinary.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import com.veterinary.repository.AppointmentOnlineRepository;
import com.veterinary.model.AppointmentOnline;

@Service
@Transactional
public class AppointmentOnlineService {
    @Autowired
    private AppointmentOnlineRepository repository;

    public List<AppointmentOnline> getAll() {
        return repository.findActiveAppointments();
    }

    public AppointmentOnline save(AppointmentOnline appointmentOnline) {
        return repository.save(appointmentOnline);
    }

    public AppointmentOnline getById(Integer id) {
        return repository.findById(id).get();
    }

    // NO tiene deactivate porque ya maneja active en la entidad
    public List<AppointmentOnline> findByPetId(Integer petId) {
        return repository.findByPetId(petId);
    }

    public List<AppointmentOnline> findByOwnerId(Integer ownerId) {
        return repository.findByOwnerId(ownerId);
    }

    public List<AppointmentOnline> findByStatusId(Integer statusId) {
        return repository.findByStatusId(statusId);
    }

    public List<AppointmentOnline> findByDateRange(Timestamp startDate, Timestamp endDate) {
        return repository.findByDateRange(startDate, endDate);
    }
}