package itst.ws.demopersistence.service;

import java.util.List;
import itst.ws.demopersistence.dto.VeterinarianSpecialtyRequest;
import itst.ws.demopersistence.dto.VeterinarianSpecialtyResponse;

public interface VeterinarianSpecialtyService {
    List<VeterinarianSpecialtyResponse> findAll();
    VeterinarianSpecialtyResponse findById(Long vetSpecId);
    VeterinarianSpecialtyResponse create(VeterinarianSpecialtyRequest request);
    VeterinarianSpecialtyResponse update(Long vetSpecId, VeterinarianSpecialtyRequest request);
    void delete(Long vetSpecId);
}
