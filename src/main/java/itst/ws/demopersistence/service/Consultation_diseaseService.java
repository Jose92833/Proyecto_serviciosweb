package itst.ws.demopersistence.service;

import java.util.List;

import itst.ws.demopersistence.dto.consultantion_diseaseRequest;
import itst.ws.demopersistence.dto.consultation_diseaseResponse;


public interface Consultation_diseaseService {

    
List<consultation_diseaseResponse> findAll();

    consultation_diseaseResponse findById(Integer cdId);

    consultation_diseaseResponse create(consultantion_diseaseRequest request);

    consultation_diseaseResponse update(Integer cdId, consultantion_diseaseRequest request);

    void delete(Integer cdId);
}
