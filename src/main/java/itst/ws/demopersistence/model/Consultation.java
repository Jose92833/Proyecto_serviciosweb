package itst.ws.demopersistence.model;

import java.util.List; // Importa todas las anotaciones JPA

import jakarta.persistence.Column;             // Importa todas las anotaciones de Lombok
import jakarta.persistence.Entity;       // Importa List
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
@Table(name = "consultation")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consultation_id")
    private Integer consultationId;

    @Column(name = "motivo", nullable = false, length = 200)
    private String motivo;

    @ManyToMany
    @JoinTable(
        name = "consultation_disease",
        joinColumns = @JoinColumn(name = "consultation_id"),
        inverseJoinColumns = @JoinColumn(name = "disease_id")
    )
    private List<Disease> diseases;
}