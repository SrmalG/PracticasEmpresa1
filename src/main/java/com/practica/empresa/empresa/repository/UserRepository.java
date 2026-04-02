package com.practica.empresa.empresa.repository;

import com.practica.empresa.empresa.sql.Sql;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import com.practica.empresa.empresa.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(final String username);

    @Query("""
    SELECT CASE WHEN EXISTS (
        SELECT 1 FROM User u 
        WHERE u.username = :username OR u.email = :email
    ) THEN true ELSE false END
    """)
    boolean existsByUsernameOrEmail(@Param("username") String username,
                                    @Param("email") String email);

    @Modifying
    @Transactional
    @Query(Sql.UPDATE_LAST_LOGIN)
    int updateLastLogin(final String username, final LocalDateTime lastLogin);

    @Modifying
    @Transactional
    @Query(Sql.ERASE_USER)
    int deleteUser(final String username);
}