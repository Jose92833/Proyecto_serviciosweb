package itst.ws.demopersistence.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import itst.ws.demopersistence.dto.PetTreatmentRequest;
import itst.ws.demopersistence.dto.PetTreatmentResponse;
import itst.ws.demopersistence.mapper.PetTreatmentMapper;
import itst.ws.demopersistence.model.PetTreatment;
import itst.ws.demopersistence.repository.PetTreatmentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PetTreatmentServiceImpl implements PetTreatmentService {

    private final PetTreatmentRepository repository;

    @Override
    public List<PetTreatmentResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(PetTreatmentMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PetTreatmentResponse findById(Long petTreatmentId) {
        PetTreatment entity = repository.findById(petTreatmentId)
                .orElseThrow(() -> new EntityNotFoundException("Tratamiento de mascota no encontrado con id: " + petTreatmentId));

        return PetTreatmentMapper.toResponse(entity);
    }

    @Override
    public PetTreatmentResponse create(PetTreatmentRequest request) {
        PetTreatment toSave = PetTreatmentMapper.toEntity(request);
        PetTreatment saved = repository.save(toSave);
        return PetTreatmentMapper.toResponse(saved);
    }

    @Override
    public PetTreatmentResponse update(Long petTreatmentId, PetTreatmentRequest request) {
        PetTreatment existing = repository.findById(petTreatmentId)
                .orElseThrow(() -> new EntityNotFoundException("Tratamiento de mascota no encontrado con id: " + petTreatmentId));

        PetTreatmentMapper.updateEntity(existing, request);
        PetTreatment updated = repository.save(existing);

        return PetTreatmentMapper.toResponse(updated);
    }

    @Override
    public void delete(Long petTreatmentId) {
        if (!repository.existsById(petTreatmentId)) {
            throw new EntityNotFoundException("Tratamiento de mascota no encontrado con id: " + petTreatmentId);
        }
        repository.deleteById(petTreatmentId);
    }
}
