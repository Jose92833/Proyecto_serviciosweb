package itst.ws.demopersistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "veterinarian_specialty")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VeterinarianSpecialty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vet_spec_id;


    @ManyToOne
    @JoinColumn(name = "veterinarian_id", nullable = false)
    private Veterinarians veterinarian;

    @ManyToOne
    @JoinColumn(name = "specialty_id", nullable = false)
    private Specialty specialty;
}
