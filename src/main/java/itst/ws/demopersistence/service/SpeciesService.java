package itst.ws.demopersistence.service;

import java.util.List;
import itst.ws.demopersistence.dto.SpeciesRequest;
import itst.ws.demopersistence.dto.SpeciesResponse;

public interface SpeciesService {
    List<SpeciesResponse> findAll();
    SpeciesResponse findById(Long speciesId);
    SpeciesResponse create(SpeciesRequest request);
    SpeciesResponse update(Long speciesId, SpeciesRequest request);
    void delete(Long speciesId);
}
