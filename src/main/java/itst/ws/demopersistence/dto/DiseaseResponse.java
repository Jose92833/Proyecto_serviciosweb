package itst.ws.demopersistence.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiseaseResponse {
     private Integer idDisease;
    private String diseaseName;
    private String descripcion;
}
