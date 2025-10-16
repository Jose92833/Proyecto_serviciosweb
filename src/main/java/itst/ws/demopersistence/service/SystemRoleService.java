package itst.ws.demopersistence.service;

import java.util.List;
import itst.ws.demopersistence.dto.SystemRoleRequest;
import itst.ws.demopersistence.dto.SystemRoleResponse;

public interface SystemRoleService {
    List<SystemRoleResponse> findAll();
    SystemRoleResponse findById(Long roleId);
    SystemRoleResponse create(SystemRoleRequest request);
    SystemRoleResponse update(Long roleId, SystemRoleRequest request);
    void delete(Long roleId);
}
