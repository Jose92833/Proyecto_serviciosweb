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
import com.veterinary.dto.AppointmentOnlineRequestDTO;
import com.veterinary.model.AppointmentOnline;
import com.veterinary.service.AppointmentOnlineService;

@RestController
@RequestMapping("appointment_online")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@Tag(name = "Appointment Online", description = "Provides methods for managing online appointments")
public class AppointmentOnlineController {
    @Autowired
    private AppointmentOnlineService service;

    @Autowired
    private ModelMapper modelMapper;

    @Operation(summary = "Get all online appointments")
    @ApiResponse(responseCode = "200", description = "Found online appointments", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AppointmentOnlineRequestDTO.class))) })
    @GetMapping
    public List<AppointmentOnlineRequestDTO> getAll() {
        List<AppointmentOnline> appointments = service.getAll();
        return appointments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get an online appointment by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Online appointment found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentOnlineRequestDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid online appointment id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Online appointment not found", content = @Content) })
    @GetMapping("{id}")
    public ResponseEntity<AppointmentOnlineRequestDTO> getById(@PathVariable Integer id) {
        return new ResponseEntity<AppointmentOnlineRequestDTO>(convertToDTO(service.getById(id)), HttpStatus.OK);
    }

    @Operation(summary = "Get online appointments by pet")
    @ApiResponse(responseCode = "200", description = "Found online appointments", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AppointmentOnlineRequestDTO.class))) })
    @GetMapping("/pet/{petId}")
    public List<AppointmentOnlineRequestDTO> getByPetId(@PathVariable Integer petId) {
        List<AppointmentOnline> appointments = service.findByPetId(petId);
        return appointments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get online appointments by owner")
    @ApiResponse(responseCode = "200", description = "Found online appointments", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AppointmentOnlineRequestDTO.class))) })
    @GetMapping("/owner/{ownerId}")
    public List<AppointmentOnlineRequestDTO> getByOwnerId(@PathVariable Integer ownerId) {
        List<AppointmentOnline> appointments = service.findByOwnerId(ownerId);
        return appointments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Register an online appointment")
    @ApiResponse(responseCode = "201", description = "Registered online appointment", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentOnlineRequestDTO.class)) })
    @PostMapping
    public ResponseEntity<AppointmentOnlineRequestDTO> add(@Valid @RequestBody AppointmentOnlineRequestDTO appointmentOnlineRequestDTO) {
        AppointmentOnlineRequestDTO savedAppointment = convertToDTO(service.save(convertToEntity(appointmentOnlineRequestDTO)));
        return new ResponseEntity<AppointmentOnlineRequestDTO>(savedAppointment, HttpStatus.CREATED);
    }

    @Operation(summary = "Update an online appointment")
    @ApiResponse(responseCode = "200", description = "Updated online appointment", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentOnlineRequestDTO.class)) })
    @PutMapping("{id}")
    public ResponseEntity<AppointmentOnlineRequestDTO> update(@Valid @RequestBody AppointmentOnlineRequestDTO appointmentOnlineRequestDTO, @PathVariable Integer id) {
        AppointmentOnline existingAppointment = service.getById(id);
        modelMapper.map(appointmentOnlineRequestDTO, existingAppointment);
        AppointmentOnlineRequestDTO updatedAppointment = convertToDTO(service.save(existingAppointment));
        return new ResponseEntity<>(updatedAppointment, HttpStatus.OK);
    }

    private AppointmentOnlineRequestDTO convertToDTO(AppointmentOnline appointmentOnline) {
        return modelMapper.map(appointmentOnline, AppointmentOnlineRequestDTO.class);
    }

    private AppointmentOnline convertToEntity(AppointmentOnlineRequestDTO appointmentOnlineRequestDTO) {
        return modelMapper.map(appointmentOnlineRequestDTO, AppointmentOnline.class);
    }
}