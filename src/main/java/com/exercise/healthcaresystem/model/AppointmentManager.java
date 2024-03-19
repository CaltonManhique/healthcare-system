package com.exercise.healthcaresystem.model;

public class AppointmentManager {

    private Appointment appointment = new Appointment();

    private Patient patient = new Patient();

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
