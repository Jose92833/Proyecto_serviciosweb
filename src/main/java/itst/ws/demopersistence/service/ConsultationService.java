package itst.ws.demopersistence.service;

import java.util.List;

import itst.ws.demopersistence.dto.ConsultationRequest;
import itst.ws.demopersistence.dto.ConsultationResponse;

public interface  ConsultationService {
   List<ConsultationResponse> findAll();

    ConsultationResponse findById(Integer consultationId);

    ConsultationResponse create(ConsultationRequest request);

    ConsultationResponse update(Integer consultationId, ConsultationRequest request);

    void delete(Integer consultationId);
}
