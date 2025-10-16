package itst.ws.demopersistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long report_id;

    @Column(name = "type_report", nullable = false, length = 50)
    private String typeReport;

    private LocalDateTime report_date = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private SystemUser user;
}
