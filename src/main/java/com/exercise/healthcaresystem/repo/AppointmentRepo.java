package com.exercise.healthcaresystem.repo;

import com.exercise.healthcaresystem.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepo extends JpaRepository<Appointment, Integer> {
}
