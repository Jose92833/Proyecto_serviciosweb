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

import itst.ws.demopersistence.dto.PetTreatmentRequest;
import itst.ws.demopersistence.dto.PetTreatmentResponse;
import itst.ws.demopersistence.service.PetTreatmentService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/pet-treatments")
@RequiredArgsConstructor
public class PetTreatmentController {

    private final PetTreatmentService service;

    @GetMapping
    public ResponseEntity<List<PetTreatmentResponse>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetTreatmentResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<PetTreatmentResponse> create(@RequestBody PetTreatmentRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetTreatmentResponse> update(@PathVariable Long id,
            @RequestBody PetTreatmentRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
