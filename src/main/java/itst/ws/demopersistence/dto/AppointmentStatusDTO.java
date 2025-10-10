package com.veterinary.dto;

import jakarta.validation.constraints.NotBlank;

public class AppointmentStatusDTO {
    private Integer id;
    
    @NotBlank(message = "El nombre del estado es obligatorio")
    private String name;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}