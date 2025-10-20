package com.practica.empresa.empresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.practica.empresa.empresa.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}