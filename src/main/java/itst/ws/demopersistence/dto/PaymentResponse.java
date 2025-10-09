package itst.ws.demopersistence.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PaymentResponse {
    private Integer paymentId;
    private Integer consultationId;
    private String consultationMotivo;
    private Integer paymentMethodId;
    private String paymentMethodName;
    private Integer discountId;
    private Double discountPercentage;
    private Double amount;
    private LocalDateTime paymentDate; 
}
