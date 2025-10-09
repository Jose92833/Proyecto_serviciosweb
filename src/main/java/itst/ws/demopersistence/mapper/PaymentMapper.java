package itst.ws.demopersistence.mapper;

import org.springframework.stereotype.Component;

import itst.ws.demopersistence.dto.PaymentRequest;
import itst.ws.demopersistence.dto.PaymentResponse;
import itst.ws.demopersistence.model.Consultation;
import itst.ws.demopersistence.model.Payment;


@Component 
public class PaymentMapper {
    
public Payment toEntity(PaymentRequest dto) {
        Payment entity = new Payment();
        entity.setAmount(dto.getAmount());

        if (dto.getConsultationId() != null) {
            Consultation c = new Consultation();
            c.setConsultationId(dto.getConsultationId());
            entity.setConsultation(c);
        }

        if (dto.getPaymentMethodId() != null) {
            PaymentMethod pm = new PaymentMethod();
            pm.setPaymentMethodId(dto.getPaymentMethodId());
            entity.setPaymentMethod(pm);
        }

        if (dto.getDiscountId() != null) {
            Discount d = new Discount();
            d.setDiscountId(dto.getDiscountId());
            entity.setDiscount(d);
        }

        return entity;
    }

    public PaymentResponse toResponse(Payment entity) {
        PaymentResponse dto = new PaymentResponse();
        dto.setPaymentId(entity.getPaymentId());
        dto.setAmount(entity.getAmount());
        dto.setPaymentDate(entity.getPaymentDate());

        if (entity.getConsultation() != null) {
            dto.setConsultationId(entity.getConsultation().getConsultationId());
            dto.setConsultationMotivo(entity.getConsultation().getMotivo());
        }

        if (entity.getPaymentMethod() != null) {
            dto.setPaymentMethodId(entity.getPaymentMethod().getPaymentMethodId());
            dto.setPaymentMethodName(entity.getPaymentMethod().getMethodName());
        }

        if (entity.getDiscount() != null) {
            dto.setDiscountId(entity.getDiscount().getDiscountId());
            dto.setDiscountPercentage(entity.getDiscount().getPercentage());
        }

        return dto;
    }
}
