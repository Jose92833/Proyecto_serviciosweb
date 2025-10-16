package itst.ws.demopersistence.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetTreatmentResponse {
    private Long pet_treatment_id;
    private LocalDate applicationDate;
    private Long petId;
    private Long treatmentId;
    private Long veterinarianId;
  
}
