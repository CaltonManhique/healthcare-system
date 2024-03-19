package com.exercise.healthcaresystem.repo;

import com.exercise.healthcaresystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {
}
