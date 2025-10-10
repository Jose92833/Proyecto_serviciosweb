package com.veterinary.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import com.veterinary.dto.TreatmentDTO;
import com.veterinary.model.Treatment;
import com.veterinary.service.TreatmentService;

@RestController
@RequestMapping("treatment")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@Tag(name = "Treatment", description = "Provides methods for managing treatments")
public class TreatmentController {
    @Autowired
    private TreatmentService service;

    @Autowired
    private ModelMapper modelMapper;

    @Operation(summary = "Get all treatments")
    @ApiResponse(responseCode = "200", description = "Found treatments", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TreatmentDTO.class))) })
    @GetMapping
    public List<TreatmentDTO> getAll() {
        List<Treatment> treatments = service.getAll();
        return treatments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get a treatment by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Treatment found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TreatmentDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid treatment id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Treatment not found", content = @Content) })
    @GetMapping("{id}")
    public ResponseEntity<TreatmentDTO> getById(@PathVariable Integer id) {
        return new ResponseEntity<TreatmentDTO>(convertToDTO(service.getById(id)), HttpStatus.OK);
    }

    @Operation(summary = "Register a treatment")
    @ApiResponse(responseCode = "201", description = "Registered treatment", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = TreatmentDTO.class)) })
    @PostMapping
    public ResponseEntity<TreatmentDTO> add(@Valid @RequestBody TreatmentDTO treatmentDTO) {
        TreatmentDTO savedTreatment = convertToDTO(service.save(convertToEntity(treatmentDTO)));
        return new ResponseEntity<TreatmentDTO>(savedTreatment, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a treatment")
    @ApiResponse(responseCode = "200", description = "Updated treatment", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = TreatmentDTO.class)) })
    @PutMapping("{id}")
    public ResponseEntity<TreatmentDTO> update(@Valid @RequestBody TreatmentDTO treatmentDTO, @PathVariable Integer id) {
        Treatment existingTreatment = service.getById(id);
        modelMapper.map(treatmentDTO, existingTreatment);
        TreatmentDTO updatedTreatment = convertToDTO(service.save(existingTreatment));
        return new ResponseEntity<>(updatedTreatment, HttpStatus.OK);
    }

    @Operation(summary = "Deactivate a treatment")
    @ApiResponse(responseCode = "200", description = "Treatment deactivated")
    @PutMapping("{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Integer id) {
        service.deactivate(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private TreatmentDTO convertToDTO(Treatment treatment) {
        return modelMapper.map(treatment, TreatmentDTO.class);
    }

    private Treatment convertToEntity(TreatmentDTO treatmentDTO) {
        return modelMapper.map(treatmentDTO, Treatment.class);
    }
}