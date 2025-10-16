package itst.ws.demopersistence.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VeterinarianSpecialtyRequest {
    private Long veterinarianId;
    private Long specialtyId;
}
