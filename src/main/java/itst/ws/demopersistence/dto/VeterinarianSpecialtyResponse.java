package itst.ws.demopersistence.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VeterinarianSpecialtyResponse {
    private Long vet_spec_id;
    private Long veterinarianId;
    private Long specialtyId;
   
}
