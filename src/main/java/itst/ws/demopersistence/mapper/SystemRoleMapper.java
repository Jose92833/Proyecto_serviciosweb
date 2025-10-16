package itst.ws.demopersistence.mapper;

import itst.ws.demopersistence.dto.SystemRoleRequest;
import itst.ws.demopersistence.dto.SystemRoleResponse;
import itst.ws.demopersistence.model.SystemRole;

public class SystemRoleMapper {

    public static SystemRoleResponse toResponse(SystemRole systemRole) {
        return SystemRoleResponse.builder()
                .role_id(systemRole.getRole_id())
                .rolName(systemRole.getRolName())
                .build();
    }

    public static SystemRole toEntity(SystemRoleRequest request) {
        SystemRole systemRole = new SystemRole();
        systemRole.setRolName(request.getRolName());
        return systemRole;
    }

    public static void updateEntity(SystemRole systemRole, SystemRoleRequest request) {
        systemRole.setRolName(request.getRolName());
    }
}
