package itst.ws.demopersistence.service;

import java.util.List;

import itst.ws.demopersistence.dto.PaymentRequest;
import itst.ws.demopersistence.dto.PaymentResponse;


public interface PaymentService {
     PaymentResponse save(PaymentRequest dto);
    List<PaymentResponse> findAll();
    PaymentResponse findById(Integer id);
    void delete(Integer id);
}
