package com.exercise.healthcaresystem.repo;

import com.exercise.healthcaresystem.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepo extends JpaRepository<Doctor,Integer> {
}
