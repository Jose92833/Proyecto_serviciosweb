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
import com.veterinary.dto.AppointmentNoteRequestDTO;
import com.veterinary.model.AppointmentNote;
import com.veterinary.service.AppointmentNoteService;

@RestController
@RequestMapping("appointment_note")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@Tag(name = "Appointment Note", description = "Provides methods for managing appointment notes")
public class AppointmentNoteController {
    @Autowired
    private AppointmentNoteService service;

    @Autowired
    private ModelMapper modelMapper;

    @Operation(summary = "Get all appointment notes")
    @ApiResponse(responseCode = "200", description = "Found appointment notes", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AppointmentNoteRequestDTO.class))) })
    @GetMapping
    public List<AppointmentNoteRequestDTO> getAll() {
        List<AppointmentNote> appointmentNotes = service.getAll();
        return appointmentNotes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get an appointment note by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appointment note found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentNoteRequestDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid appointment note id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Appointment note not found", content = @Content) })
    @GetMapping("{id}")
    public ResponseEntity<AppointmentNoteRequestDTO> getById(@PathVariable Integer id) {
        return new ResponseEntity<AppointmentNoteRequestDTO>(convertToDTO(service.getById(id)), HttpStatus.OK);
    }

    @Operation(summary = "Get appointment notes by appointment")
    @ApiResponse(responseCode = "200", description = "Found appointment notes", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AppointmentNoteRequestDTO.class))) })
    @GetMapping("/appointment/{appointmentId}")
    public List<AppointmentNoteRequestDTO> getByAppointmentId(@PathVariable Integer appointmentId) {
        List<AppointmentNote> appointmentNotes = service.findByAppointmentId(appointmentId);
        return appointmentNotes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Register an appointment note")
    @ApiResponse(responseCode = "201", description = "Registered appointment note", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentNoteRequestDTO.class)) })
    @PostMapping
    public ResponseEntity<AppointmentNoteRequestDTO> add(@Valid @RequestBody AppointmentNoteRequestDTO appointmentNoteRequestDTO) {
        AppointmentNoteRequestDTO savedAppointmentNote = convertToDTO(service.save(convertToEntity(appointmentNoteRequestDTO)));
        return new ResponseEntity<AppointmentNoteRequestDTO>(savedAppointmentNote, HttpStatus.CREATED);
    }

    @Operation(summary = "Update an appointment note")
    @ApiResponse(responseCode = "200", description = "Updated appointment note", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentNoteRequestDTO.class)) })
    @PutMapping("{id}")
    public ResponseEntity<AppointmentNoteRequestDTO> update(@Valid @RequestBody AppointmentNoteRequestDTO appointmentNoteRequestDTO, @PathVariable Integer id) {
        AppointmentNote existingAppointmentNote = service.getById(id);
        modelMapper.map(appointmentNoteRequestDTO, existingAppointmentNote);
        AppointmentNoteRequestDTO updatedAppointmentNote = convertToDTO(service.save(existingAppointmentNote));
        return new ResponseEntity<>(updatedAppointmentNote, HttpStatus.OK);
    }

    @Operation(summary = "Deactivate an appointment note")
    @ApiResponse(responseCode = "200", description = "Appointment note deactivated")
    @PutMapping("{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Integer id) {
        service.deactivate(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private AppointmentNoteRequestDTO convertToDTO(AppointmentNote appointmentNote) {
        return modelMapper.map(appointmentNote, AppointmentNoteRequestDTO.class);
    }

    private AppointmentNote convertToEntity(AppointmentNoteRequestDTO appointmentNoteRequestDTO) {
        return modelMapper.map(appointmentNoteRequestDTO, AppointmentNote.class);
    }
}
