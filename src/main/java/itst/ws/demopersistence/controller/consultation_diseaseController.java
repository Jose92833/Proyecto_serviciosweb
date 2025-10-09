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

import itst.ws.demopersistence.dto.consultantion_diseaseRequest;
import itst.ws.demopersistence.dto.consultation_diseaseResponse;
import itst.ws.demopersistence.service.Consultation_diseaseService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/consultation-disease")
@RequiredArgsConstructor
public class consultation_diseaseController {

    private final Consultation_diseaseService service = null;

    @GetMapping
    public ResponseEntity<List<consultation_diseaseResponse>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<consultation_diseaseResponse> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<consultation_diseaseResponse> create(@RequestBody consultantion_diseaseRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<consultation_diseaseResponse> update(@PathVariable("id") Integer id,
            @RequestBody consultantion_diseaseRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}