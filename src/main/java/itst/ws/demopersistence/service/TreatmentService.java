package com.veterinary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import com.veterinary.repository.TreatmentRepository;
import com.veterinary.model.Treatment;

@Service
@Transactional
public class TreatmentService {
    @Autowired
    private TreatmentRepository repository;

    public List<Treatment> getAll() {
        return repository.findActiveTreatments();
    }

    public Treatment save(Treatment treatment) {
        return repository.save(treatment);
    }

    public Treatment getById(Integer id) {
        return repository.findById(id).get();
    }

    public void deactivate(Integer id) {
        Treatment treatment = repository.findById(id).get();
        treatment.setActive(false);
        repository.save(treatment);
    }

    public List<Treatment> findByNameContaining(String name) {
        return repository.findByNameContaining(name);
    }
}