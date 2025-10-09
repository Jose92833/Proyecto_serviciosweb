package itst.ws.demopersistence.dto;

import lombok.Data;

@Data
public class PaymentRequest {
    private Integer consultationId;
    private Integer paymentMethodId;
    private Integer discountId;
    private Double amount;
}
