package itst.ws.demopersistence.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import itst.ws.demopersistence.dto.ConsultationRequest;
import itst.ws.demopersistence.dto.ConsultationResponse;
import itst.ws.demopersistence.service.ConsultationService;
import lombok.RequiredArgsConstructor;





@RestController
@RequestMapping("/api/consultations")
@RequiredArgsConstructor
public class ConsultationController {


    private final ConsultationService service;

    @GetMapping
    public ResponseEntity<List<ConsultationResponse>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultationResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ConsultationResponse> create(@RequestBody ConsultationRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultationResponse> update(@PathVariable Integer id,
            @RequestBody ConsultationRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}