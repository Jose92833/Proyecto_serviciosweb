package itst.ws.demopersistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VetSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long schedule_id;

    private String weekday;
    private LocalTime startTime;
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "veterinarian_id")
    private Veterinarians veterinarian;
}
