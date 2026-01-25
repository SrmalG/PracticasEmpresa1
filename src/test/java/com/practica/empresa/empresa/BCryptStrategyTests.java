package com.practica.empresa.empresa;

import com.practica.empresa.empresa.security.impl.BCryptStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BCryptStrategyTests {

    private BCryptStrategy bCryptStrategy;

    @BeforeEach
    void setUp() {
        bCryptStrategy = new BCryptStrategy();
    }

    @Test
    void testHashIsNotNullOrEmpty() {
        String password = "miContraseña123";
        String hash = bCryptStrategy.hash(password);

        assertNotNull(hash);
        assertFalse(hash.isBlank());
    }

    @Test
    void testCheckReturnsTrueForCorrectPassword() {
        String password = "miContraseña123";
        String hash = bCryptStrategy.hash(password);
        assertTrue(bCryptStrategy.check(password, hash));
    }

    @Test
    void testCheckReturnsFalseForIncorrectPassword() {
        String password = "miContraseña123";
        String wrongPassword = "otraContraseña";
        String hash = bCryptStrategy.hash(password);
        assertFalse(bCryptStrategy.check(wrongPassword, hash));
    }

    @Test
    void testHashesAreDifferentForSamePassword() {
        String password = "miContraseña123";
        String hash1 = bCryptStrategy.hash(password);
        String hash2 = bCryptStrategy.hash(password);
        assertNotEquals(hash1, hash2, "BCrypt hashes should differ due to salt");
    }
}
