package itst.ws.demopersistence.dto;

import lombok.Data;

@Data
public class InvoiceRequest {
    
 private Integer paymentId;
    private String invoiceNumber;
    private Double total;
}