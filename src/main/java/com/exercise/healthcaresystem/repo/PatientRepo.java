package com.exercise.healthcaresystem.repo;

import com.exercise.healthcaresystem.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepo extends JpaRepository<Patient, Integer> {

    Patient findPatientByPatientName(String name);
}
