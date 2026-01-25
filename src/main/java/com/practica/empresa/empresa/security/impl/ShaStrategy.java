package com.practica.empresa.empresa.security.impl;

import com.practica.empresa.empresa.security.HashStrategy;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ShaStrategy implements HashStrategy {

    /**
     * Generates the SHA-256 hash of a given password.
     *
     * <p>This method takes a plain-text password and converts it into a
     * hexadecimal string using the SHA-256 cryptographic algorithm.
     * SHA-256 is a one-way hashing function, meaning it is not possible
     * to reverse the hash to retrieve the original password.</p>
     * @param password the plain-text password to hash
     * @return a hexadecimal string representing the SHA-256 hash of the password
     * @throws RuntimeException if the SHA-256 algorithm is not available
     */
    public String hash(final String password) {
        try {
            final MessageDigest md = MessageDigest.getInstance("SHA-256");
            final byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
            final StringBuilder hexString = new StringBuilder(2 * hash.length);
            for (byte b : hash)
                hexString.append(String.format("%02x", b));

            return hexString.toString();

       } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


}
