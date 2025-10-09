package itst.ws.demopersistence.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import itst.ws.demopersistence.dto.ConsultationRequest;
import itst.ws.demopersistence.dto.ConsultationResponse;
import itst.ws.demopersistence.mapper.ConsultationMapper;
import itst.ws.demopersistence.model.Consultation;
import itst.ws.demopersistence.model.Disease;
import itst.ws.demopersistence.repository.ConsultationRepository;
import itst.ws.demopersistence.repository.DiseaseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class ConsultationServiceImpl implements ConsultationService {
   
    private final ConsultationRepository consultationRepository;
    private final DiseaseRepository diseaseRepository;

  @Override
    public List<ConsultationResponse> findAll() {
        return consultationRepository.findAll()
                .stream()
                .map(ConsultationMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ConsultationResponse findById(Integer consultationId) {
        Consultation consultation = consultationRepository.findById(consultationId)
                .orElseThrow(() -> new EntityNotFoundException("Consulta no encontrada con id: " + consultationId));

        return ConsultationMapper.toResponse(consultation);
    }

    @Override
    public ConsultationResponse create(ConsultationRequest request) {
        // Buscar las enfermedades seleccionadas
        List<Disease> diseases = diseaseRepository.findAllById(request.getDiseaseIds());

        Consultation consultation = Consultation.builder()
                .motivo(request.getMotivo())
                .diseases(diseases)
                .build();

        Consultation saved = consultationRepository.save(consultation);
        return ConsultationMapper.toResponse(saved);
    }

    @Override
    public ConsultationResponse update(Integer consultationId, ConsultationRequest request) {
        Consultation existing = consultationRepository.findById(consultationId)
                .orElseThrow(() -> new EntityNotFoundException("Consulta no encontrada con id: " + consultationId));

        List<Disease> diseases = diseaseRepository.findAllById(request.getDiseaseIds());

        ConsultationMapper.updateEntity(existing, request, diseases);

        Consultation updated = consultationRepository.save(existing);
        return ConsultationMapper.toResponse(updated);
    }

    @Override
    public void delete(Integer consultationId) {
        if (!consultationRepository.existsById(consultationId)) {
            throw new EntityNotFoundException("Consulta no encontrada con id: " + consultationId);
        }
        consultationRepository.deleteById(consultationId);
    }
}