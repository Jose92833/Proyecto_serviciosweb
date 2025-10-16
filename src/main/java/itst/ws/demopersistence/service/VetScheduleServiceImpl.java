package itst.ws.demopersistence.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import itst.ws.demopersistence.dto.VetScheduleRequest;
import itst.ws.demopersistence.dto.VetScheduleResponse;
import itst.ws.demopersistence.mapper.VetScheduleMapper;
import itst.ws.demopersistence.model.VetSchedule;
import itst.ws.demopersistence.repository.VetScheduleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VetScheduleServiceImpl implements VetScheduleService {

    private final VetScheduleRepository repository;

    @Override
    public List<VetScheduleResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(VetScheduleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public VetScheduleResponse findById(Long scheduleId) {
        VetSchedule entity = repository.findById(scheduleId)
                .orElseThrow(() -> new EntityNotFoundException("Horario de veterinario no encontrado con id: " + scheduleId));

        return VetScheduleMapper.toResponse(entity);
    }

    @Override
    public VetScheduleResponse create(VetScheduleRequest request) {
        VetSchedule toSave = VetScheduleMapper.toEntity(request);
        VetSchedule saved = repository.save(toSave);
        return VetScheduleMapper.toResponse(saved);
    }

    @Override
    public VetScheduleResponse update(Long scheduleId, VetScheduleRequest request) {
        VetSchedule existing = repository.findById(scheduleId)
                .orElseThrow(() -> new EntityNotFoundException("Horario de veterinario no encontrado con id: " + scheduleId));

        VetScheduleMapper.updateEntity(existing, request);
        VetSchedule updated = repository.save(existing);

        return VetScheduleMapper.toResponse(updated);
    }

    @Override
    public void delete(Long scheduleId) {
        if (!repository.existsById(scheduleId)) {
            throw new EntityNotFoundException("Horario de veterinario no encontrado con id: " + scheduleId);
        }
        repository.deleteById(scheduleId);
    }
}
