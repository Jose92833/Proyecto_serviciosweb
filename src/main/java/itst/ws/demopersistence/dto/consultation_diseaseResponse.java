package itst.ws.demopersistence.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class consultation_diseaseResponse {
    private Integer cdId;
    private String consultationId;
    private String diseaseId;
}
