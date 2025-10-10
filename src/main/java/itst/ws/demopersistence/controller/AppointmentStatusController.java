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
import com.veterinary.dto.AppointmentStatusDTO;
import com.veterinary.model.AppointmentStatus;
import com.veterinary.service.AppointmentStatusService;

@RestController
@RequestMapping("appointment_status")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@Tag(name = "Appointment Status", description = "Provides methods for managing appointment status")
public class AppointmentStatusController {
    @Autowired
    private AppointmentStatusService service;

    @Autowired
    private ModelMapper modelMapper;

    @Operation(summary = "Get all appointment status")
    @ApiResponse(responseCode = "200", description = "Found appointment status", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AppointmentStatusDTO.class))) })
    @GetMapping
    public List<AppointmentStatusDTO> getAll() {
        List<AppointmentStatus> appointmentStatus = service.getAll();
        return appointmentStatus.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get an appointment status by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appointment status found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentStatusDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid appointment status id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Appointment status not found", content = @Content) })
    @GetMapping("{id}")
    public ResponseEntity<AppointmentStatusDTO> getById(@PathVariable Integer id) {
        return new ResponseEntity<AppointmentStatusDTO>(convertToDTO(service.getById(id)), HttpStatus.OK);
    }

    @Operation(summary = "Register an appointment status")
    @ApiResponse(responseCode = "201", description = "Registered appointment status", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentStatusDTO.class)) })
    @PostMapping
    public ResponseEntity<AppointmentStatusDTO> add(@Valid @RequestBody AppointmentStatusDTO appointmentStatusDTO) {
        AppointmentStatusDTO savedAppointmentStatus = convertToDTO(service.save(convertToEntity(appointmentStatusDTO)));
        return new ResponseEntity<AppointmentStatusDTO>(savedAppointmentStatus, HttpStatus.CREATED);
    }

    @Operation(summary = "Update an appointment status")
    @ApiResponse(responseCode = "200", description = "Updated appointment status", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentStatusDTO.class)) })
    @PutMapping("{id}")
    public ResponseEntity<AppointmentStatusDTO> update(@Valid @RequestBody AppointmentStatusDTO appointmentStatusDTO, @PathVariable Integer id) {
        AppointmentStatus existingAppointmentStatus = service.getById(id);
        modelMapper.map(appointmentStatusDTO, existingAppointmentStatus);
        AppointmentStatusDTO updatedAppointmentStatus = convertToDTO(service.save(existingAppointmentStatus));
        return new ResponseEntity<>(updatedAppointmentStatus, HttpStatus.OK);
    }

    @Operation(summary = "Deactivate an appointment status")
    @ApiResponse(responseCode = "200", description = "Appointment status deactivated")
    @PutMapping("{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Integer id) {
        service.deactivate(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private AppointmentStatusDTO convertToDTO(AppointmentStatus appointmentStatus) {
        return modelMapper.map(appointmentStatus, AppointmentStatusDTO.class);
    }

    private AppointmentStatus convertToEntity(AppointmentStatusDTO appointmentStatusDTO) {
        return modelMapper.map(appointmentStatusDTO, AppointmentStatus.class);
    }
}