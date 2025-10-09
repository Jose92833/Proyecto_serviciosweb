package itst.ws.demopersistence.model;

import java.util.List;

import jakarta.persistence.Column;       
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "disease")
public class Disease {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "disease_id")
    private Integer idDisease;

    @Column(name = "disease_name", nullable = false, length = 100)
    private String diseaseName;

    @Column(name = "descripcion", nullable = false, length = 255)
    private String descripcion;

    @ManyToMany(mappedBy = "diseases")
    private List<Consultation> consultation;
    

}

