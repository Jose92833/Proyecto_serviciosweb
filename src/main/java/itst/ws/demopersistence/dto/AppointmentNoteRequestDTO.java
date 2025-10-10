package com.veterinary.dto;

import java.sql.Timestamp;

public class AppointmentNoteRequestDTO {
    private Integer id;
    private Integer appointmentId;
    private String note;
    private Timestamp noteDate;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getAppointmentId() {
        return appointmentId;
    }
    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public Timestamp getNoteDate() {
        return noteDate;
    }
    public void setNoteDate(Timestamp noteDate) {
        this.noteDate = noteDate;
    }
}