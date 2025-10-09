package itst.ws.demopersistence.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import itst.ws.demopersistence.dto.PaymentRequest;
import itst.ws.demopersistence.dto.PaymentResponse;
import itst.ws.demopersistence.mapper.PaymentMapper;
import itst.ws.demopersistence.model.Payment;
import itst.ws.demopersistence.repository.PaymentRepositor;
import lombok.RequiredArgsConstructor;





@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepositor repository;
    private final PaymentMapper mapper;

    @Override
    public PaymentResponse save(PaymentRequest dto) {
        Payment entity = mapper.toEntity(dto);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public List<PaymentResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentResponse findById(Integer id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElse(null);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}

