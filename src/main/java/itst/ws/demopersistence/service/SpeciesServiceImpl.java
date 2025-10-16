package itst.ws.demopersistence.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import itst.ws.demopersistence.dto.SpeciesRequest;
import itst.ws.demopersistence.dto.SpeciesResponse;
import itst.ws.demopersistence.mapper.SpeciesMapper;
import itst.ws.demopersistence.model.Species;
import itst.ws.demopersistence.repository.SpeciesRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SpeciesServiceImpl implements SpeciesService {

    private final SpeciesRepository repository;

    @Override
    public List<SpeciesResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(SpeciesMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SpeciesResponse findById(Long speciesId) {
        Species entity = repository.findById(speciesId)
                .orElseThrow(() -> new EntityNotFoundException("Especie no encontrada con id: " + speciesId));

        return SpeciesMapper.toResponse(entity);
    }

    @Override
    public SpeciesResponse create(SpeciesRequest request) {
        Species toSave = SpeciesMapper.toEntity(request);
        Species saved = repository.save(toSave);
        return SpeciesMapper.toResponse(saved);
    }

    @Override
    public SpeciesResponse update(Long speciesId, SpeciesRequest request) {
        Species existing = repository.findById(speciesId)
                .orElseThrow(() -> new EntityNotFoundException("Especie no encontrada con id: " + speciesId));

        SpeciesMapper.updateEntity(existing, request);
        Species updated = repository.save(existing);

        return SpeciesMapper.toResponse(updated);
    }

    @Override
    public void delete(Long speciesId) {
        if (!repository.existsById(speciesId)) {
            throw new EntityNotFoundException("Especie no encontrada con id: " + speciesId);
        }
        repository.deleteById(speciesId);
    }
}
