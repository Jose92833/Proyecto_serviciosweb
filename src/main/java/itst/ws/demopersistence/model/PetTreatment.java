package itst.ws.demopersistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetTreatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pet_treatment_id;

    private LocalDate applicationDate;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "treatment_id")
    private Treatment treatment;

    @ManyToOne
    @JoinColumn(name = "veterinarian_id")
    private Veterinarians veterinarian;
}
