package itst.ws.demopersistence.service;

import java.util.List;

import itst.ws.demopersistence.dto.InvoiceRequest;
import itst.ws.demopersistence.dto.InvoiceResponse;



public interface InvoiceService {
      InvoiceResponse save(InvoiceRequest dto);
    List<InvoiceResponse> findAll();
    InvoiceResponse findById(Integer id);
    void delete(Integer id);
}
