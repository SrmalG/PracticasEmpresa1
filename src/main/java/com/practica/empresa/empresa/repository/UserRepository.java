package com.practica.empresa.empresa.repository;

import com.practica.empresa.empresa.sql.Sql;
import org.springframework.data.jpa.repository.JpaRepository;
import com.practica.empresa.empresa.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(final String username);

    @Modifying
    @Transactional
    @Query(Sql.UPDATE_LAST_LOGIN)
    void updateLastLogin(String username, LocalDateTime lastLogin);

    @Modifying
    @Transactional
    @Query(Sql.ERASE_USER)
    void deleteUser(String username);
}