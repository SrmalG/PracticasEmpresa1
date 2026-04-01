package com.practica.empresa.empresa.security.password.impl;

import com.practica.empresa.empresa.security.password.HashStrategy;
import org.mindrot.jbcrypt.BCrypt;

public class BCryptStrategy implements HashStrategy {

    /**
     * Hashes a password using the BCrypt algorithm.
     *
     * <p>This method generates a secure hashed representation of the plain-text
     * password using BCrypt with a work factor of 12. BCrypt automatically
     * handles salting internally.</p>
     *
     * @param password the plain-text password to hash
     * @return a securely hashed string representing the password
     */
    @Override
    public String hash(final String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    /**
     * Verifies whether a plain-text password matches a previously hashed password.
     *
     * <p>This method uses BCrypt's secure comparison function to prevent timing attacks.</p>
     *
     * @param password the plain-text password to check
     * @param hashed the previously hashed password
     * @return {@code true} if the password matches the hash, {@code false} otherwise
     */
    @Override
    public boolean check(final String password, final String hashed) {
        return BCrypt.checkpw(password, hashed);
    }

}
