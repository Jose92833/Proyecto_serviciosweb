package com.veterinary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import com.veterinary.repository.AppointmentNoteRepository;
import com.veterinary.model.AppointmentNote;

@Service
@Transactional
public class AppointmentNoteService {
    @Autowired
    private AppointmentNoteRepository repository;

    public List<AppointmentNote> getAll() {
        return repository.findActiveNotes();
    }

    public AppointmentNote save(AppointmentNote appointmentNote) {
        return repository.save(appointmentNote);
    }

    public AppointmentNote getById(Integer id) {
        return repository.findById(id).get();
    }

    public void deactivate(Integer id) {
        AppointmentNote appointmentNote = repository.findById(id).get();
        appointmentNote.setActive(false);
        repository.save(appointmentNote);
    }

    public List<AppointmentNote> findByAppointmentId(Integer appointmentId) {
        return repository.findByAppointmentId(appointmentId);
    }
}