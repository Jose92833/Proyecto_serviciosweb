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
import com.veterinary.dto.SystemUserRequestDTO;
import com.veterinary.model.SystemUser;
import com.veterinary.service.SystemUserService;

@RestController
@RequestMapping("systemuser")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@Tag(name = "System User", description = "Provides methods for managing system users")
public class SystemUserController {
    @Autowired
    private SystemUserService service;

    @Autowired
    private ModelMapper modelMapper;

    @Operation(summary = "Get all system users")
    @ApiResponse(responseCode = "200", description = "Found system users", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = SystemUserRequestDTO.class))) })
    @GetMapping
    public List<SystemUserRequestDTO> getAll() {
        List<SystemUser> systemUsers = service.getAll();
        return systemUsers.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get a system user by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "System user found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = SystemUserRequestDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid system user id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "System user not found", content = @Content) })
    @GetMapping("{id}")
    public ResponseEntity<SystemUserRequestDTO> getById(@PathVariable Integer id) {
        return new ResponseEntity<SystemUserRequestDTO>(convertToDTO(service.getById(id)), HttpStatus.OK);
    }

    @Operation(summary = "Register a system user")
    @ApiResponse(responseCode = "201", description = "Registered system user", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SystemUserRequestDTO.class)) })
    @PostMapping
    public ResponseEntity<SystemUserRequestDTO> add(@Valid @RequestBody SystemUserRequestDTO systemUserRequestDTO) {
        SystemUserRequestDTO savedSystemUser = convertToDTO(service.save(convertToEntity(systemUserRequestDTO)));
        return new ResponseEntity<SystemUserRequestDTO>(savedSystemUser, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a system user")
    @ApiResponse(responseCode = "200", description = "Updated system user", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SystemUserRequestDTO.class)) })
    @PutMapping("{id}")
    public ResponseEntity<SystemUserRequestDTO> update(@Valid @RequestBody SystemUserRequestDTO systemUserRequestDTO, @PathVariable Integer id) {
        SystemUser existingSystemUser = service.getById(id);
        modelMapper.map(systemUserRequestDTO, existingSystemUser);
        SystemUserRequestDTO updatedSystemUser = convertToDTO(service.save(existingSystemUser));
        return new ResponseEntity<>(updatedSystemUser, HttpStatus.OK);
    }

    @Operation(summary = "Deactivate a system user")
    @ApiResponse(responseCode = "200", description = "System user deactivated")
    @PutMapping("{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Integer id) {
        service.deactivate(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private SystemUserRequestDTO convertToDTO(SystemUser systemUser) {
        return modelMapper.map(systemUser, SystemUserRequestDTO.class);
    }

    private SystemUser convertToEntity(SystemUserRequestDTO systemUserRequestDTO) {
        return modelMapper.map(systemUserRequestDTO, SystemUser.class);
    }
}