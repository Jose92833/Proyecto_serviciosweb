package com.veterinary.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "systemuser")
public class SystemUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @JsonProperty("user_id")
    private Integer id;

    @NotBlank(message = "The username is required")
    @Email(message = "Must be a valid email address")
    @Column(name = "user_name", unique = true, updatable = false)
    @JsonProperty("user_name")
    private String userName;

    @NotBlank(message = "The password is required")
    @Column(name = "password")
    @JsonProperty("password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    @JsonProperty("role_id")
    private SystemRole role;

    @Column(name = "active")
    @JsonProperty("active")
    private Boolean active = true;
}