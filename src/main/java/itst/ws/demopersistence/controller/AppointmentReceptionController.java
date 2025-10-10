package com.veterinary.controller;

import java.sql.Timestamp;
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
import com.veterinary.dto.AppointmentReceptionRequestDTO;
import com.veterinary.model.AppointmentReception;
import com.veterinary.service.AppointmentReceptionService;

@RestController
@RequestMapping("appointment_reception")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@Tag(name = "Appointments Reception", description = "Provides methods for managing reception appointments")
public class AppointmentReceptionController {
    @Autowired
    private AppointmentReceptionService service;

    @Autowired
    private ModelMapper modelMapper;

    @Operation(summary = "Get all reception appointments")
    @ApiResponse(responseCode = "200", description = "Found reception appointments", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AppointmentReceptionRequestDTO.class))) })
    @GetMapping
    public List<AppointmentReceptionRequestDTO> getAll() {
        List<AppointmentReception> appointments = service.getAll();
        return appointments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get a reception appointment by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reception appointment found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentReceptionRequestDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid reception appointment id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Reception appointment not found", content = @Content) })
    @GetMapping("{id}")
    public ResponseEntity<AppointmentReceptionRequestDTO> getById(@PathVariable Integer id) {
        return new ResponseEntity<AppointmentReceptionRequestDTO>(convertToDTO(service.getById(id)), HttpStatus.OK);
    }

    @Operation(summary = "Get reception appointments by receptionist")
    @ApiResponse(responseCode = "200", description = "Found reception appointments", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AppointmentReceptionRequestDTO.class))) })
    @GetMapping("/receptionist/{receptionistId}")
    public List<AppointmentReceptionRequestDTO> getByReceptionistId(@PathVariable Integer receptionistId) {
        List<AppointmentReception> appointments = service.findByReceptionistId(receptionistId);
        return appointments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Register a reception appointment")
    @ApiResponse(responseCode = "201", description = "Registered reception appointment", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentReceptionRequestDTO.class)) })
    @PostMapping
    public ResponseEntity<AppointmentReceptionRequestDTO> add(@Valid @RequestBody AppointmentReceptionRequestDTO appointmentReceptionRequestDTO) {
        AppointmentReceptionRequestDTO savedAppointment = convertToDTO(service.save(convertToEntity(appointmentReceptionRequestDTO)));
        return new ResponseEntity<AppointmentReceptionRequestDTO>(savedAppointment, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a reception appointment")
    @ApiResponse(responseCode = "200", description = "Updated reception appointment", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentReceptionRequestDTO.class)) })
    @PutMapping("{id}")
    public ResponseEntity<AppointmentReceptionRequestDTO> update(@Valid @RequestBody AppointmentReceptionRequestDTO appointmentReceptionRequestDTO, @PathVariable Integer id) {
        AppointmentReception existingAppointment = service.getById(id);
        modelMapper.map(appointmentReceptionRequestDTO, existingAppointment);
        AppointmentReceptionRequestDTO updatedAppointment = convertToDTO(service.save(existingAppointment));
        return new ResponseEntity<>(updatedAppointment, HttpStatus.OK);
    }

    private AppointmentReceptionRequestDTO convertToDTO(AppointmentReception appointmentReception) {
        return modelMapper.map(appointmentReception, AppointmentReceptionRequestDTO.class);
    }

    private AppointmentReception convertToEntity(AppointmentReceptionRequestDTO appointmentReceptionRequestDTO) {
        return modelMapper.map(appointmentReceptionRequestDTO, AppointmentReception.class);
    }
}