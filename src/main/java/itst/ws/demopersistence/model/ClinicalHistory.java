package com.veterinary.model;

import java.sql.Date;

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
@Table(name = "clinical_history")
public class ClinicalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    @JsonProperty("history_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pet_id", referencedColumnName = "pet_id")
    @JsonProperty("pet_id")
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "consultation_id", referencedColumnName = "consultation_id")
    @JsonProperty("consultation_id")
    private Consultation consultation;

    @ManyToOne
    @JoinColumn(name = "treatment_id", referencedColumnName = "treatment_id")
    @JsonProperty("treatment_id")
    private Treatment treatment;

    @Column(name = "history_date")
    @JsonProperty("history_date")
    private Date historyDate;

    @Column(name = "active")
    @JsonProperty("active")
    private Boolean active = true;
}
