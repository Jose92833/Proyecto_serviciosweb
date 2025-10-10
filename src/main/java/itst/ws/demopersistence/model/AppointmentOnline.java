package com.veterinary.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "appointment_online")
public class AppointmentOnline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    @JsonProperty("appointment_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pet_id", referencedColumnName = "pet_id")
    @JsonProperty("pet_id")
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "owner_id")
    @JsonProperty("owner_id")
    private Owner owner;

    @Column(name = "appointment_date")
    @JsonProperty("appointment_date")
    private Timestamp appointmentDate;

    @Column(name = "reason")
    @JsonProperty("reason")
    private String reason;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "status_id")
    @JsonProperty("status_id")
    private AppointmentStatus status;

    @Column(name = "created_at")
    @JsonProperty("created_at")
    private Timestamp createdAt;

    @Column(name = "active")
    @JsonProperty("active")
    private Boolean active;
}