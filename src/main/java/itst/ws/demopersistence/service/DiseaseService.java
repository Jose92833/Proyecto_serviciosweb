package itst.ws.demopersistence.service;

import java.util.List;

import itst.ws.demopersistence.dto.DiseaseRequest;
import itst.ws.demopersistence.dto.DiseaseResponse;

public interface DiseaseService {
    List<DiseaseResponse> findAll();
   
    DiseaseResponse findById(Integer idDisease);
   
    DiseaseResponse create(DiseaseRequest request);
   
    DiseaseResponse update(Integer idDisease, DiseaseRequest request);
   
    void delete(Integer idDisease);
}
