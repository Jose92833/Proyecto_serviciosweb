package com.veterinary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import com.veterinary.repository.AppointmentStatusRepository;
import com.veterinary.model.AppointmentStatus;

@Service
@Transactional
public class AppointmentStatusService {
    @Autowired
    private AppointmentStatusRepository repository;

    public List<AppointmentStatus> getAll() {
        return repository.findActiveStatuses();
    }

    public AppointmentStatus save(AppointmentStatus appointmentStatus) {
        return repository.save(appointmentStatus);
    }

    public AppointmentStatus getById(Integer id) {
        return repository.findById(id).get();
    }

    public void deactivate(Integer id) {
        AppointmentStatus appointmentStatus = repository.findById(id).get();
        appointmentStatus.setActive(false);
        repository.save(appointmentStatus);
    }
}