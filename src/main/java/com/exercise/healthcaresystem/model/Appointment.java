package com.exercise.healthcaresystem.model;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "medical_staff_id")
    private Integer medicalStaffId;

    @Column(name = "patient_id")
    private Integer patientId;

    @Column(name = "appointment_date")
    private LocalDate date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMedicalStaffId() {
        return medicalStaffId;
    }

    public void setMedicalStaffId(Integer medicalStaffId) {
        this.medicalStaffId = medicalStaffId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }
}
