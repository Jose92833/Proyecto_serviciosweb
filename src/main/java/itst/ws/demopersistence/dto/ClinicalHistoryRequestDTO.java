package com.veterinary.dto;

import java.sql.Date;

public class ClinicalHistoryRequestDTO {
    private Integer id;
    private Integer petId;
    private Integer consultationId;
    private Integer treatmentId;
    private Date historyDate;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getPetId() {
        return petId;
    }
    public void setPetId(Integer petId) {
        this.petId = petId;
    }
    public Integer getConsultationId() {
        return consultationId;
    }
    public void setConsultationId(Integer consultationId) {
        this.consultationId = consultationId;
    }
    public Integer getTreatmentId() {
        return treatmentId;
    }
    public void setTreatmentId(Integer treatmentId) {
        this.treatmentId = treatmentId;
    }
    public Date getHistoryDate() {
        return historyDate;
    }
    public void setHistoryDate(Date historyDate) {
        this.historyDate = historyDate;
    }
}