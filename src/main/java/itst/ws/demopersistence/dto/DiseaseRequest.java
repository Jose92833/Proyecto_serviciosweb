package itst.ws.demopersistence.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiseaseRequest {
    private String diseaseName;
    private String descripcion;
}
