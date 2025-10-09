package itst.ws.demopersistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import itst.ws.demopersistence.model.Payment;

public interface PaymentRepositor extends JpaRepository<Payment, Integer>  {
    
}
