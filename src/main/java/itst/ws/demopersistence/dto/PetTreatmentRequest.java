package itst.ws.demopersistence.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetTreatmentRequest {
    private LocalDate applicationDate;
    private Long petId;
    private Long treatmentId;
    private Long veterinarianId;
}
