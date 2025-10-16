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

import itst.ws.demopersistence.dto.VetScheduleRequest;
import itst.ws.demopersistence.dto.VetScheduleResponse;
import itst.ws.demopersistence.service.VetScheduleService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/vet-schedules")
@RequiredArgsConstructor
public class VetScheduleController {

    private final VetScheduleService service;

    @GetMapping
    public ResponseEntity<List<VetScheduleResponse>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VetScheduleResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<VetScheduleResponse> create(@RequestBody VetScheduleRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VetScheduleResponse> update(@PathVariable Long id,
            @RequestBody VetScheduleRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
