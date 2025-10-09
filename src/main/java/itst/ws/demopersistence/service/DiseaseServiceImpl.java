package itst.ws.demopersistence.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import itst.ws.demopersistence.dto.DiseaseRequest;
import itst.ws.demopersistence.dto.DiseaseResponse;
import itst.ws.demopersistence.model.Disease;
import itst.ws.demopersistence.repository.DiseaseRepository;

@Service


public class DiseaseServiceImpl implements DiseaseService{
    
  @Autowired
    private DiseaseRepository diseaseRepository;

    @Override
    public List<DiseaseResponse> findAll() {
        return diseaseRepository.findAll()
                .stream()
                .map(disease -> DiseaseResponse.builder()
                        .idDisease(disease.getIdDisease())
                        .diseaseName(disease.getDiseaseName())
                        .descripcion(disease.getDescripcion())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public DiseaseResponse findById(Integer idDisease) {
        Disease disease = diseaseRepository.findById(idDisease)
                .orElseThrow(() -> new RuntimeException("Disease not found with ID: " + idDisease));

        return DiseaseResponse.builder()
                .idDisease(disease.getIdDisease())
                .diseaseName(disease.getDiseaseName())
                .descripcion(disease.getDescripcion())
                .build();
    }

    @Override
    public DiseaseResponse create(DiseaseRequest request) {
        Disease disease = Disease.builder()
                .diseaseName(request.getDiseaseName())
                .descripcion(request.getDescripcion())
                .build();

        Disease saved = diseaseRepository.save(disease);

        return DiseaseResponse.builder()
                .idDisease(saved.getIdDisease())
                .diseaseName(saved.getDiseaseName())
                .descripcion(saved.getDescripcion())
                .build();
    }

    @Override
    public DiseaseResponse update(Integer idDisease, DiseaseRequest request) {
        Disease existing = diseaseRepository.findById(idDisease)
                .orElseThrow(() -> new RuntimeException("Disease not found with ID: " + idDisease));

        existing.setDiseaseName(request.getDiseaseName());
        existing.setDescripcion(request.getDescripcion());

        Disease updated = diseaseRepository.save(existing);

        return DiseaseResponse.builder()
                .idDisease(updated.getIdDisease())
                .diseaseName(updated.getDiseaseName())
                .descripcion(updated.getDescripcion())
                .build();
    }

    @Override
    public void delete(Integer idDisease) {
        if (!diseaseRepository.existsById(idDisease)) {
            throw new RuntimeException("Disease not found with ID: " + idDisease);
        }
        diseaseRepository.deleteById(idDisease);
    }
}