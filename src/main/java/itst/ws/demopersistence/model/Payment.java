package itst.ws.demopersistence.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "payment")

public class Payment {
    
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Integer paymentId;

    @ManyToOne
    @JoinColumn(name = "consultation_id", referencedColumnName = "consultation_id")
    private Consultation consultation;

    @ManyToOne
    @JoinColumn(name = "payment_method_id", referencedColumnName = "payment_method_id")
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "discount_id", referencedColumnName = "discount_id")
    private Discount discount;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "payment_date", columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private LocalDateTime paymentDate;
}
