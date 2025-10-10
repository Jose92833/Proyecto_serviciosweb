package com.veterinary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import com.veterinary.repository.ClinicalHistoryRepository;
import com.veterinary.model.ClinicalHistory;

@Service
@Transactional
public class ClinicalHistoryService {
    @Autowired
    private ClinicalHistoryRepository repository;

    public List<ClinicalHistory> getAll() {
        return repository.findActiveHistories();
    }

    public ClinicalHistory save(ClinicalHistory clinicalHistory) {
        return repository.save(clinicalHistory);
    }

    public ClinicalHistory getById(Integer id) {
        return repository.findById(id).get();
    }

    public void deactivate(Integer id) {
        ClinicalHistory clinicalHistory = repository.findById(id).get();
        clinicalHistory.setActive(false);
        repository.save(clinicalHistory);
    }

    public List<ClinicalHistory> findByPetId(Integer petId) {
        return repository.findByPetId(petId);
    }

    public List<ClinicalHistory> findByConsultationId(Integer consultationId) {
        return repository.findByConsultationId(consultationId);
    }

    public List<ClinicalHistory> findByTreatmentId(Integer treatmentId) {
        return repository.findByTreatmentId(treatmentId);
    }
}