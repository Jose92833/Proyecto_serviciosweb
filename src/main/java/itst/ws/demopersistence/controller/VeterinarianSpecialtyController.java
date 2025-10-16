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

import itst.ws.demopersistence.dto.VeterinarianSpecialtyRequest;
import itst.ws.demopersistence.dto.VeterinarianSpecialtyResponse;
import itst.ws.demopersistence.service.VeterinarianSpecialtyService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/veterinarian-specialties")
@RequiredArgsConstructor
public class VeterinarianSpecialtyController {

    private final VeterinarianSpecialtyService service;

    @GetMapping
    public ResponseEntity<List<VeterinarianSpecialtyResponse>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeterinarianSpecialtyResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<VeterinarianSpecialtyResponse> create(@RequestBody VeterinarianSpecialtyRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeterinarianSpecialtyResponse> update(@PathVariable Long id,
            @RequestBody VeterinarianSpecialtyRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
