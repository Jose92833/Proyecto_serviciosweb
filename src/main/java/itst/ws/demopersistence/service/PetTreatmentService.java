package itst.ws.demopersistence.service;

import java.util.List;
import itst.ws.demopersistence.dto.PetTreatmentRequest;
import itst.ws.demopersistence.dto.PetTreatmentResponse;

public interface PetTreatmentService {
    List<PetTreatmentResponse> findAll();
    PetTreatmentResponse findById(Long petTreatmentId);
    PetTreatmentResponse create(PetTreatmentRequest request);
    PetTreatmentResponse update(Long petTreatmentId, PetTreatmentRequest request);
    void delete(Long petTreatmentId);
}
