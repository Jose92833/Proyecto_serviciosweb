package itst.ws.demopersistence.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import itst.ws.demopersistence.dto.VeterinarianSpecialtyRequest;
import itst.ws.demopersistence.dto.VeterinarianSpecialtyResponse;
import itst.ws.demopersistence.mapper.VeterinarianSpecialtyMapper;
import itst.ws.demopersistence.model.VeterinarianSpecialty;
import itst.ws.demopersistence.repository.VeterinarianSpecialtyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VeterinarianSpecialtyServiceImpl implements VeterinarianSpecialtyService {

    private final VeterinarianSpecialtyRepository repository;

    @Override
    public List<VeterinarianSpecialtyResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(VeterinarianSpecialtyMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public VeterinarianSpecialtyResponse findById(Long vetSpecId) {
        VeterinarianSpecialty entity = repository.findById(vetSpecId)
                .orElseThrow(() -> new EntityNotFoundException("Especialidad de veterinario no encontrada con id: " + vetSpecId));

        return VeterinarianSpecialtyMapper.toResponse(entity);
    }

    @Override
    public VeterinarianSpecialtyResponse create(VeterinarianSpecialtyRequest request) {
        VeterinarianSpecialty toSave = VeterinarianSpecialtyMapper.toEntity(request);
        VeterinarianSpecialty saved = repository.save(toSave);
        return VeterinarianSpecialtyMapper.toResponse(saved);
    }

    @Override
    public VeterinarianSpecialtyResponse update(Long vetSpecId, VeterinarianSpecialtyRequest request) {
        VeterinarianSpecialty existing = repository.findById(vetSpecId)
                .orElseThrow(() -> new EntityNotFoundException("Especialidad de veterinario no encontrada con id: " + vetSpecId));

        VeterinarianSpecialtyMapper.updateEntity(existing, request);
        VeterinarianSpecialty updated = repository.save(existing);

        return VeterinarianSpecialtyMapper.toResponse(updated);
    }

    @Override
    public void delete(Long vetSpecId) {
        if (!repository.existsById(vetSpecId)) {
            throw new EntityNotFoundException("Especialidad de veterinario no encontrada con id: " + vetSpecId);
        }
        repository.deleteById(vetSpecId);
    }
}
