package itst.ws.demopersistence.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemRoleResponse {
    private Long role_id;
    private String rolName;
}
