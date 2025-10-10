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
import com.veterinary.dto.ClinicalHistoryRequestDTO;
import com.veterinary.model.ClinicalHistory;
import com.veterinary.service.ClinicalHistoryService;

@RestController
@RequestMapping("clinical_history")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@Tag(name = "Clinical History", description = "Provides methods for managing clinical histories")
public class ClinicalHistoryController {
    @Autowired
    private ClinicalHistoryService service;

    @Autowired
    private ModelMapper modelMapper;

    @Operation(summary = "Get all clinical histories")
    @ApiResponse(responseCode = "200", description = "Found clinical histories", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ClinicalHistoryRequestDTO.class))) })
    @GetMapping
    public List<ClinicalHistoryRequestDTO> getAll() {
        List<ClinicalHistory> clinicalHistories = service.getAll();
        return clinicalHistories.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get a clinical history by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clinical history found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ClinicalHistoryRequestDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid clinical history id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Clinical history not found", content = @Content) })
    @GetMapping("{id}")
    public ResponseEntity<ClinicalHistoryRequestDTO> getById(@PathVariable Integer id) {
        return new ResponseEntity<ClinicalHistoryRequestDTO>(convertToDTO(service.getById(id)), HttpStatus.OK);
    }

    @Operation(summary = "Get clinical histories by pet")
    @ApiResponse(responseCode = "200", description = "Found clinical histories", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ClinicalHistoryRequestDTO.class))) })
    @GetMapping("/pet/{petId}")
    public List<ClinicalHistoryRequestDTO> getByPetId(@PathVariable Integer petId) {
        List<ClinicalHistory> clinicalHistories = service.findByPetId(petId);
        return clinicalHistories.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get clinical histories by consultation")
    @ApiResponse(responseCode = "200", description = "Found clinical histories", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ClinicalHistoryRequestDTO.class))) })
    @GetMapping("/consultation/{consultationId}")
    public List<ClinicalHistoryRequestDTO> getByConsultationId(@PathVariable Integer consultationId) {
        List<ClinicalHistory> clinicalHistories = service.findByConsultationId(consultationId);
        return clinicalHistories.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Register a clinical history")
    @ApiResponse(responseCode = "201", description = "Registered clinical history", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ClinicalHistoryRequestDTO.class)) })
    @PostMapping
    public ResponseEntity<ClinicalHistoryRequestDTO> add(@Valid @RequestBody ClinicalHistoryRequestDTO clinicalHistoryRequestDTO) {
        ClinicalHistoryRequestDTO savedClinicalHistory = convertToDTO(service.save(convertToEntity(clinicalHistoryRequestDTO)));
        return new ResponseEntity<ClinicalHistoryRequestDTO>(savedClinicalHistory, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a clinical history")
    @ApiResponse(responseCode = "200", description = "Updated clinical history", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ClinicalHistoryRequestDTO.class)) })
    @PutMapping("{id}")
    public ResponseEntity<ClinicalHistoryRequestDTO> update(@Valid @RequestBody ClinicalHistoryRequestDTO clinicalHistoryRequestDTO, @PathVariable Integer id) {
        ClinicalHistory existingClinicalHistory = service.getById(id);
        modelMapper.map(clinicalHistoryRequestDTO, existingClinicalHistory);
        ClinicalHistoryRequestDTO updatedClinicalHistory = convertToDTO(service.save(existingClinicalHistory));
        return new ResponseEntity<>(updatedClinicalHistory, HttpStatus.OK);
    }

    @Operation(summary = "Deactivate a clinical history")
    @ApiResponse(responseCode = "200", description = "Clinical history deactivated")
    @PutMapping("{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Integer id) {
        service.deactivate(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private ClinicalHistoryRequestDTO convertToDTO(ClinicalHistory clinicalHistory) {
        return modelMapper.map(clinicalHistory, ClinicalHistoryRequestDTO.class);
    }

    private ClinicalHistory convertToEntity(ClinicalHistoryRequestDTO clinicalHistoryRequestDTO) {
        return modelMapper.map(clinicalHistoryRequestDTO, ClinicalHistory.class);
    }
}