package itst.ws.demopersistence.service;

import java.util.List;
import java.util.stream.Collectors;

import itst.ws.demopersistence.dto.InvoiceRequest;
import itst.ws.demopersistence.dto.InvoiceResponse;
import itst.ws.demopersistence.mapper.InvoiceMapper;
import itst.ws.demopersistence.model.Invoice;
import itst.ws.demopersistence.repository.InvoiceRepository;

public class InvoiceServiceImpl implements InvoiceService {
    
    private final InvoiceRepository repository = null;
    private final InvoiceMapper mapper = null;

   

    @Override
    public InvoiceResponse save(InvoiceRequest dto) {
        Invoice entity = mapper.toEntity(dto);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public List<InvoiceResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceResponse findById(Integer id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElse(null);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
