package itst.ws.demopersistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long species_id; // Maps to SERIAL PRIMARY KEY

    private String speciesName;

    @OneToMany(mappedBy = "species", cascade = CascadeType.ALL)
    private List<Breed> breeds = new ArrayList<>();

    @OneToMany(mappedBy = "species", cascade = CascadeType.ALL)
    private List<Pet> pets = new ArrayList<>();
}
