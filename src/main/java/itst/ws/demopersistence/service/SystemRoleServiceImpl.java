package itst.ws.demopersistence.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import itst.ws.demopersistence.dto.SystemRoleRequest;
import itst.ws.demopersistence.dto.SystemRoleResponse;
import itst.ws.demopersistence.model.SystemRole;
import itst.ws.demopersistence.repository.SystemRoleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SystemRoleServiceImpl implements SystemRoleService {

    private final SystemRoleRepository repository;

    @Override
    public List<SystemRoleResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(entity -> new SystemRoleResponse(
                    entity.getRole_id(),
                    entity.getRolName()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public SystemRoleResponse findById(Long roleId) {
        SystemRole entity = repository.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("Rol no encontrado con id: " + roleId));

        return new SystemRoleResponse(
            entity.getRole_id(),
            entity.getRolName()
        );
    }

    @Override
    public SystemRoleResponse create(SystemRoleRequest request) {
        SystemRole toSave = new SystemRole();
        toSave.setRolName(request.getRolName());

        SystemRole saved = repository.save(toSave);

        return new SystemRoleResponse(
            saved.getRole_id(),
            saved.getRolName()
        );
    }

    @Override
    public SystemRoleResponse update(Long roleId, SystemRoleRequest request) {
        SystemRole existing = repository.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("Rol no encontrado con id: " + roleId));

        existing.setRolName(request.getRolName());

        SystemRole updated = repository.save(existing);

        return new SystemRoleResponse(
            updated.getRole_id(),
            updated.getRolName()
        );
    }

    @Override
    public void delete(Long roleId) {
        if (!repository.existsById(roleId)) {
            throw new EntityNotFoundException("Rol no encontrado con id: " + roleId);
        }
        repository.deleteById(roleId);
    }
}
