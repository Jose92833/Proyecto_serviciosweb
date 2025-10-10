package com.veterinary.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class SystemUserRequestDTO {
    private Integer id;
    
    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Email(message = "Debe ser un correo electrónico válido")
    private String userName;
    
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;
    private Integer roleId;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Integer getRoleId() {
        return roleId;
    }
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}