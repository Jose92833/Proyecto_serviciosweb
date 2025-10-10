package com.veterinary.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "treatment")
public class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "treatment_id")
    @JsonProperty("treatment_id")
    private Integer id;

    @Column(name = "treatment_name")
    @JsonProperty("treatment_name")
    private String name;

    @Column(name = "description")
    @JsonProperty("description")
    private String description;

    @Column(name = "cost")
    @JsonProperty("cost")
    private Double cost;

    @Column(name = "frequency")
    @JsonProperty("frequency")
    private String frequency;

    @Column(name = "side_effects")
    @JsonProperty("side_effects")
    private String sideEffects;

    @Column(name = "active")
    @JsonProperty("active")
    private Boolean active = true;
}