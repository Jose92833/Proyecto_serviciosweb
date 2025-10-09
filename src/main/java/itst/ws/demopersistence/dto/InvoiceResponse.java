package itst.ws.demopersistence.dto;

import java.time.LocalDateTime;

import lombok.Data;


@Data
public class InvoiceResponse {
     private Integer invoiceId;
    private Integer paymentId;
    private String invoiceNumber;
    private LocalDateTime invoiceDate;
    private Double total;
}
