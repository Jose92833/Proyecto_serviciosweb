package itst.ws.demopersistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import itst.ws.demopersistence.model.Invoice;

public interface InvoiceRepository  extends JpaRepository<Invoice, Integer> {
    
}
