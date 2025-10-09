package itst.ws.demopersistence.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class consultantion_diseaseRequest {
    private String consultationId;
    private String diseaseId;
}
