package com.exercise.healthcaresystem.model;

public class AdminModel {

    private User user = new User();

    private Doctor doctor = new Doctor();

    private Role role = new Role();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
