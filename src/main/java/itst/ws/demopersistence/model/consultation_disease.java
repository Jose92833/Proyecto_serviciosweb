package itst.ws.demopersistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "consultation_disease")
public class consultation_disease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_id")
    private Integer cdId;

    @Column(name = "consultation_id ", nullable = false, length = 100)
    private String consultationId;

    @Column(name = "disease_id", nullable = false, length = 100)
    private String disease_id;
    
}
