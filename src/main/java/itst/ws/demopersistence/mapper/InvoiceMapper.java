package itst.ws.demopersistence.mapper;

import org.springframework.stereotype.Component;

import itst.ws.demopersistence.dto.InvoiceRequest;
import itst.ws.demopersistence.dto.InvoiceResponse;
import itst.ws.demopersistence.model.Invoice;
import itst.ws.demopersistence.model.Payment;

@Component
public class InvoiceMapper {
    public Invoice toEntity(InvoiceRequest dto) {
        Invoice entity = new Invoice();
        entity.setInvoiceNumber(dto.getInvoiceNumber());
        entity.setTotal(dto.getTotal());

        if (dto.getPaymentId() != null) {
            Payment p = new Payment();
            p.setPaymentId(dto.getPaymentId());
            entity.setPayment(p);
        }
        return entity;
    }

    public InvoiceResponse toResponse(Invoice entity) {
        InvoiceResponse dto = new InvoiceResponse();
        dto.setInvoiceId(entity.getInvoiceId());
        dto.setInvoiceNumber(entity.getInvoiceNumber());
        dto.setInvoiceDate(entity.getInvoiceDate());
        dto.setTotal(entity.getTotal());

        if (entity.getPayment() != null) {
            dto.setPaymentId(entity.getPayment().getPaymentId());
        }

        return dto;
    }
}
