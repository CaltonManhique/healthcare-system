package com.exercise.healthcaresystem.repo;

import com.exercise.healthcaresystem.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, String> {
}
