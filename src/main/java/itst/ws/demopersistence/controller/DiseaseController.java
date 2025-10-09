package itst.ws.demopersistence.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import itst.ws.demopersistence.dto.DiseaseRequest;
import itst.ws.demopersistence.dto.DiseaseResponse;
import itst.ws.demopersistence.service.DiseaseService;

@RestController
@RequestMapping("/diseases")
@CrossOrigin(origins = "*")

public class DiseaseController {
    
  @Autowired
    private DiseaseService diseaseService;

    @GetMapping
    public List<DiseaseResponse> getAll() {
        return diseaseService.findAll();
    }

    @GetMapping("/{id}")
    public DiseaseResponse getById(@PathVariable Integer id) {
        return diseaseService.findById(id);
    }

    @PostMapping
    public DiseaseResponse create(@RequestBody DiseaseRequest request) {
        return diseaseService.create(request);
    }

    @PutMapping("/{id}")
    public DiseaseResponse update(@PathVariable Integer id, @RequestBody DiseaseRequest request) {
        return diseaseService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        diseaseService.delete(id);
    }
}