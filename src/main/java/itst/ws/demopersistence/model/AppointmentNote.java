package com.veterinary.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "appointment_note")
public class AppointmentNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id")
    @JsonProperty("note_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "appointment_id", referencedColumnName = "appointment_id")
    @JsonBackReference
    private AppointmentOnline appointmentOnline;

    @Column(name = "note")
    @JsonProperty("note")
    private String note;

    @Column(name = "note_date")
    @JsonProperty("note_date")
    private Timestamp noteDate;

    @Column(name = "active")
    @JsonProperty("active")
    private Boolean active = true;
}
