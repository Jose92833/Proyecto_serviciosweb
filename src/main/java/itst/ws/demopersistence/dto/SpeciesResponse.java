package itst.ws.demopersistence.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpeciesResponse {
    private Long species_id;
    private String speciesName;
}
