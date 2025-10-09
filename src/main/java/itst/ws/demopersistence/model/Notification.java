package itst.ws.demopersistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "notification")
public class Notification {
    
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Integer notificationId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private SystemUser user; // Debe existir la entidad SystemUser

    @Column(name = "notification_message", nullable = false, length = 200)
    private String notificationMessage;

    @Column(name = "sent_date", columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private java.time.LocalDateTime sentDate;

    @Column(name = "read", nullable = false)
    private Boolean read = false;
}
