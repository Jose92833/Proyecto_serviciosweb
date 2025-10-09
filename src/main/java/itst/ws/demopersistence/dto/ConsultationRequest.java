package itst.ws.demopersistence.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsultationRequest {
     private String motivo;
    private List<Integer> diseaseIds;
}

