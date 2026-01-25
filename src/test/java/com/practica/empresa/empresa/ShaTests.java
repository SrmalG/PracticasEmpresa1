package com.practica.empresa.empresa;

import com.practica.empresa.empresa.security.impl.ShaStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShaTests {

    private ShaStrategy shaStrategy;

    @BeforeEach
    void setUp() {
        shaStrategy = new ShaStrategy();
    }

    @Test
    void testHashIsConsistent() {
        String password = "miContraseña123";
        String hash1 = shaStrategy.hash(password);
        String hash2 = shaStrategy.hash(password);

        // SHA-256 debe ser determinista: mismo input → mismo hash
        assertEquals(hash1, hash2);
    }

    @Test
    void testCheckReturnsTrueForCorrectPassword() {
        String password = "miContraseña123";
        String hash = shaStrategy.hash(password);

        assertTrue(shaStrategy.check(password, hash));
    }

    @Test
    void testCheckReturnsFalseForIncorrectPassword() {
        String password = "miContraseña123";
        String wrongPassword = "otraContraseña";
        String hash = shaStrategy.hash(password);

        assertFalse(shaStrategy.check(wrongPassword, hash));
    }

    @Test
    void testHashIsNotNullOrEmpty() {
        String password = "miContraseña123";
        String hash = shaStrategy.hash(password);

        assertNotNull(hash);
        assertFalse(hash.isBlank());
        assertEquals(64, hash.length(), "SHA-256 hash should have 64 hex characters");
    }
}
