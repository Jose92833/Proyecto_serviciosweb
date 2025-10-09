package itst.ws.demopersistence.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import itst.ws.demopersistence.dto.consultantion_diseaseRequest;
import itst.ws.demopersistence.dto.consultation_diseaseResponse;
import itst.ws.demopersistence.model.consultation_disease;
import itst.ws.demopersistence.repository.consultation_diseaseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConsultationDiseaseServiceImpl implements Consultation_diseaseService {

    private final consultation_diseaseRepository repository;

    @Override
    public List<consultation_diseaseResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(entity -> consultation_diseaseResponse.builder()
                        .cdId(entity.getCdId())
                        .consultationId(entity.getConsultationId())
                        .diseaseId(entity.getDisease_id())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public consultation_diseaseResponse findById(Integer cdId) {
        consultation_disease entity = repository.findById(cdId)
                .orElseThrow(() -> new EntityNotFoundException("Registro no encontrado con id: " + cdId));

        return consultation_diseaseResponse.builder()
                .cdId(entity.getCdId())
                .consultationId(entity.getConsultationId())
                .diseaseId(entity.getDisease_id())
                .build();
    }

    @Override
    public consultation_diseaseResponse create(consultantion_diseaseRequest request) {
        consultation_disease toSave = consultation_disease.builder()
                .consultationId(request.getConsultationId())
                .disease_id(request.getDiseaseId())
                .build();

        consultation_disease saved = repository.save(toSave);

        return consultation_diseaseResponse.builder()
                .cdId(saved.getCdId())
                .consultationId(saved.getConsultationId())
                .diseaseId(saved.getDisease_id())
                .build();
    }

    @Override
    public consultation_diseaseResponse update(Integer cdId, consultantion_diseaseRequest request) {
        consultation_disease existing = repository.findById(cdId)
                .orElseThrow(() -> new EntityNotFoundException("Registro no encontrado con id: " + cdId));

        existing.setConsultationId(request.getConsultationId());
        existing.setDisease_id(request.getDiseaseId());

        consultation_disease updated = repository.save(existing);

        return consultation_diseaseResponse.builder()
                .cdId(updated.getCdId())
                .consultationId(updated.getConsultationId())
                .diseaseId(updated.getDisease_id())
                .build();
    }

    @Override
    public void delete(Integer cdId) {
        if (!repository.existsById(cdId)) {
            throw new EntityNotFoundException("Registro no encontrado con id: " + cdId);
        }
        repository.deleteById(cdId);
    }
}
